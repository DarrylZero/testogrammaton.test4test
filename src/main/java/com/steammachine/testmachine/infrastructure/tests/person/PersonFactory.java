package com.steammachine.testmachine.infrastructure.tests.person;

import com.steammachine.testmachine.sdk.ContextHook;
import com.steammachine.testmachine.sdk.TestedObjectFactory;

public class PersonFactory implements ContextHook, Person {


    private static TestedObjectFactory factory;

    @Override
    public void install(TestedObjectFactory factory) {
        PersonFactory.factory = factory;
    }

    public static Person instance(Class<Person> clazz) {
        return factory == null ? new PersonFactory() : factory.get(clazz);
    }

    @Override
    public String getName() {
        return "Vito Corleone";
    }




}
