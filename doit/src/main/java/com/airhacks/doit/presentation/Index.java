package com.airhacks.doit.presentation;

import com.airhacks.doit.business.reminders.boundary.ToDoManager;
import com.airhacks.doit.business.reminders.entity.ToDo;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author airhacks.com
 */
@Model
public class Index {

    @Inject
    ToDoManager boundary;

    ToDo todo;

    @PostConstruct
    public void init() {
        this.todo = new ToDo();
    }

    public ToDo getTodo() {
        return todo;
    }

    public Object save() {
        this.boundary.save(todo);
        return null;
    }

}
