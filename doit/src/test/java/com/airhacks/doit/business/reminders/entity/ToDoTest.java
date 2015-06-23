package com.airhacks.doit.business.reminders.entity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class ToDoTest {

    @Test
    public void validTodo() {
        ToDo valid = new ToDo("", "available", 11);
        assertTrue(valid.isValid());
    }

    @Test
    public void invalidTodo() {
        ToDo valid = new ToDo("", null, 11);
        assertFalse(valid.isValid());
    }

    @Test
    public void todoWithoutDescription() {
        ToDo valid = new ToDo("implement", null, 10);
        assertTrue(valid.isValid());

    }

}
