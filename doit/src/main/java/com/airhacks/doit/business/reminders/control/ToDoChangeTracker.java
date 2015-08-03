package com.airhacks.doit.business.reminders.control;

import com.airhacks.doit.business.reminders.entity.ToDo;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;

/**
 *
 * @author airhacks.com
 */
public class ToDoChangeTracker {

    public void onToDoChange(@Observes(during = TransactionPhase.AFTER_SUCCESS) ToDo todo) {
        System.out.println("############ todo changed and committed = " + todo);
    }

}
