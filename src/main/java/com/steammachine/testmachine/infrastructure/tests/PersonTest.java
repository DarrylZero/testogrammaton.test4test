package com.steammachine.testmachine.infrastructure.tests;

import com.steammachine.testmachine.infrastructure.tests.person.Person;
import com.steammachine.testmachine.infrastructure.tests.person.PersonFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("person")
class PersonTest {

    private final Person instance = PersonFactory.instance(Person.class);

    @Test
    @DisplayName("Person name is not null")
    void personMustBeCorrect() {
        assertNotNull(instance.getName());
    }

    @Test
    @DisplayName("Person name must be shorter than 20")
    void personNameMustBeShrterThan20() {
        assertTrue(instance.getName().length() < 20);
    }

    @Test
    @DisplayName("Person name must be Vito Corleone")
    void personNameMustBeVitoCorleone() {
        assertEquals("Vito Corleone",  instance.getName());
    }


}
