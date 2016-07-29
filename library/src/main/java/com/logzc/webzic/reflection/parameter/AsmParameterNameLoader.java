package com.logzc.webzic.reflection.parameter;

import com.logzc.webzic.util.AsmUtil;
import com.logzc.webzic.util.CollectionUtil;
import lombok.Getter;
import org.objectweb.asm.*;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

import static org.objectweb.asm.Opcodes.ASM5;

/**
 * study the spirit org.apache.xbean.recipe.AsmParameterNameLoader
 * Created by lishuang on 2016/7/29.
 */
public class AsmParameterNameLoader implements ParameterNameLoader {

    private Map<Constructor, List<String>> constructorCache = new HashMap<>();
    private Map<Method, List<String>> methodCache = new HashMap<>();


    @Override
    public List<String> get(Method method) {


        //check cache.
        if (methodCache.containsKey(method)) {
            return methodCache.get(method);
        }

        //no cache. Fill cache.
        getAllMethodParameters(method.getDeclaringClass(), method.getName());

        return methodCache.get(method);


    }

    @Override
    public List<String> get(Constructor constructor) {
        //check cache.
        if (constructorCache.containsKey(constructor)) {
            return constructorCache.get(constructor);
        }

        //no cache. Fill cache.
        getAllConstructorParameters(constructor.getDeclaringClass());

        return constructorCache.get(constructor);

    }

    public Map<Constructor, List<String>> getAllConstructorParameters(Class clazz) {

        Constructor[] constructors = clazz.getDeclaredConstructors();

        if (constructors.length == 0) {
            return Collections.emptyMap();
        }

        if (constructorCache.size() > 0) {
            return CollectionUtil.copy(constructorCache);
        }

        //load parameter names using ASM.
        try {
            ClassReader reader = AsmUtil.createClassReader(clazz);
            ParameterNameClassVisitor parameterNameClassVisitor = new ParameterNameClassVisitor(clazz);

            reader.accept(parameterNameClassVisitor, 0);

            constructorCache = parameterNameClassVisitor.getConstructorParameters();

        } catch (IOException e) {
            e.printStackTrace();

            return Collections.emptyMap();
        }


        return CollectionUtil.copy(constructorCache);

    }

    public Map<Method, List<String>> getAllMethodParameters(Class clazz, String methodName) {

        Method[] methods = clazz.getDeclaredMethods();

        if (methods.length == 0) {
            return Collections.emptyMap();
        }

        if (methodCache.size() > 0) {
            return CollectionUtil.copy(methodCache);
        }

        //load parameter names using ASM.
        try {
            ClassReader reader = AsmUtil.createClassReader(clazz);
            ParameterNameClassVisitor parameterNameClassVisitor = new ParameterNameClassVisitor(clazz, methodName);

            reader.accept(parameterNameClassVisitor, 0);

            methodCache = parameterNameClassVisitor.getMethodParameters();
            return CollectionUtil.copy(methodCache);

        } catch (IOException e) {
            e.printStackTrace();

            return Collections.emptyMap();
        }


    }


    private static class ParameterNameClassVisitor extends ClassVisitor {

        @Getter
        private final Map<Constructor, List<String>> constructorParameters = new HashMap<>();
        @Getter
        private final Map<Method, List<String>> methodParameters = new HashMap<>();

        private final Map<String, Exception> exceptions = new HashMap<>();

        private final String methodName;
        private final Map<String, Method> methodMap = new HashMap<>();

        private final Map<String, Constructor> constructorMap = new HashMap<>();

        //method way
        public ParameterNameClassVisitor(Class clazz, String methodName) {
            super(ASM5);
            this.methodName = methodName;

            Method[] methods = clazz.getDeclaredMethods();

            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    methodMap.put(Type.getMethodDescriptor(method), method);
                }
            }
        }

        //constructor way.
        public ParameterNameClassVisitor(Class clazz) {
            super(ASM5);
            this.methodName = "<init>";

            Constructor[] constructors = clazz.getDeclaredConstructors();

            for (Constructor constructor : constructors) {
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                Type[] types = new Type[parameterTypes.length];
                for (int j = 0; j < types.length; j++) {
                    types[j] = Type.getType(parameterTypes[j]);
                }
                constructorMap.put(Type.getMethodDescriptor(Type.VOID_TYPE, types), constructor);
            }
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            if (!name.equals(this.methodName)) {
                return null;
            }

            final List<String> parameterNames;
            final boolean isStaticMethod;

            //constructor
            if (methodName.equals("<init>")) {

                Constructor constructor = constructorMap.get(desc);

                if (constructor == null) {
                    return null;
                }
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                parameterNames = new ArrayList<>(parameterTypes.length);
                parameterNames.addAll(Collections.<String>nCopies(parameterTypes.length, null));
                constructorParameters.put(constructor, parameterNames);

                isStaticMethod = false;


            }

            //method
            else {
                Method method = methodMap.get(desc);
                if (method == null) {
                    return null;
                }
                Class<?>[] parameterTyps = method.getParameterTypes();

                parameterNames = new ArrayList<>(parameterTyps.length);

                parameterNames.addAll(Collections.<String>nCopies(parameterTyps.length, null));
                methodParameters.put(method, parameterNames);
                isStaticMethod = Modifier.isStatic(method.getModifiers());

            }

            return new MethodVisitor(ASM5) {
                @Override
                public void visitLocalVariable(String name1, String desc1, String signature1, Label start, Label end, int index) {

                    if (isStaticMethod) {
                        parameterNames.set(index, name1);
                    }
                    // for non-static the 0th arg is "this" so we need to offset by -1
                    else if (index > 0) {
                        parameterNames.set(index - 1, name1);
                    }
                }
            };
        }
    }
}
