package com.airhacks.doit.business.reminders.boundary;

import com.airhacks.doit.business.logging.boundary.BoundaryLogger;
import com.airhacks.doit.business.reminders.entity.ToDo;
import java.util.List;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Interceptors(BoundaryLogger.class)
public class ToDoManager {

    @PersistenceContext
    EntityManager em;

    public ToDo findById(long id) {
        return this.em.find(ToDo.class, id);

    }

    public void delete(long id) {
        try {
            ToDo reference = this.em.getReference(ToDo.class, id);
            this.em.remove(reference);
        } catch (EntityNotFoundException e) {
            //we want to remove it...
        }
    }

    public List<ToDo> all() {
        return this.em.createNamedQuery(ToDo.findAll, ToDo.class).
                getResultList();
    }

    public ToDo save(ToDo todo) {
        return this.em.merge(todo);
    }

    public ToDo updateStatus(long id, boolean done) {
        ToDo todo = this.findById(id);
        if (todo == null) {
            return null;
        }
        todo.setDone(done);
        return todo;
    }

}
