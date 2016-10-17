package com.logzc.webzic.junit4;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

/**
 * Created by lishuang on 2016/10/17.
 */
public class WebzicJUnit4ClassRunner extends BlockJUnit4ClassRunner {

    Class<?> clazz;

    /**
     * Creates a BlockJUnit4ClassRunner to run {@code klass}
     *
     * @param clazz
     *  InitializationError if the test class is malformed.
     */
    public WebzicJUnit4ClassRunner(Class<?> clazz) throws InitializationError {
        super(clazz);

        this.clazz=clazz;
    }


    //Intercept @BeforeClass
    protected Statement withBeforeClasses(final Statement statement) {
        final Statement junitStatement = super.withBeforeClasses(statement);
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                System.out.println(" -> Before @BeforeClass: " + clazz.getName());
                junitStatement.evaluate();
                System.out.println(" -> After @BeforeClass: " + clazz.getName());
            }

        };
    }

    //Intercept @Before
    protected Statement withBefores(final FrameworkMethod method, Object target, final Statement statement) {

        final Statement junitStatement = super.withBefores(method, target, statement);
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                System.out.println(" -> Before @Before method: " + method.getName());
                junitStatement.evaluate();
                System.out.println(" -> After @Before method: " + method.getName());
            }
        };
    }

    //Intercept @after
    protected Statement withAfters(final FrameworkMethod method, Object target, final Statement statement) {
        final Statement junitStatement = super.withAfters(method, target, statement);
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                System.out.println(" -> Before @After method: " + method.getName());
                junitStatement.evaluate();
                System.out.println(" -> After @After method: " + method.getName());
            }

        };
    }

    //Intercept @AfterClass
    protected Statement withAfterClasses(final Statement statement) {
        final Statement junitStatement = super.withAfterClasses(statement);
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                System.out.println(" -> Before @AfterClass: " + clazz.getName());
                junitStatement.evaluate();
                System.out.println(" -> After @AfterClass: " + clazz.getName());
            }
        };
    }

}
