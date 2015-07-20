package com.airhacks.doit.presentation;

import com.airhacks.doit.business.reminders.boundary.ToDoManager;
import com.airhacks.doit.business.reminders.entity.ToDo;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
 *
 * @author airhacks.com
 */
@Model
public class Index {

    @Inject
    ToDoManager boundary;

    ToDo todo;

    @Inject
    Validator validator;

    @PostConstruct
    public void init() {
        this.todo = new ToDo();
    }

    public ToDo getTodo() {
        return todo;
    }

    public List<ToDo> getToDos() {
        return this.boundary.all();
    }

    public void showValidationError(String content) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, content, content);
        FacesContext.getCurrentInstance().addMessage("", message);

    }

    public Object save() {
        Set<ConstraintViolation<ToDo>> violations = this.validator.validate(this.todo);
        for (ConstraintViolation<ToDo> violation : violations) {
            this.showValidationError(violation.getMessage());
        }
        if (violations.isEmpty()) {
            this.boundary.save(todo);
        }
        return null;
    }

}
