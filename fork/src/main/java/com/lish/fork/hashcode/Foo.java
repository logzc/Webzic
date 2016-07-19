package com.lish.fork.hashcode;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lishuang on 2016/7/19.
 */
public class Foo {
    private String name;
    private String address;
    public Set<String> hobbies=new HashSet<>();

    public Foo(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this,"hobbies");
    }

    @Override
    public boolean equals(Object object) {
        return EqualsBuilder.reflectionEquals(this, object,"hobbies");
    }


}
