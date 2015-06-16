package com.airhacks.doit.business.reminders.boundary;

import com.airhacks.doit.business.reminders.entity.ToDo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author airhacks.com
 */
@Stateless
public class ToDoManager {

    @PersistenceContext
    EntityManager em;

    public ToDo findById(long id) {
        return this.em.find(ToDo.class, id);

    }

    public void delete(long id) {
        ToDo reference = this.em.getReference(ToDo.class, id);
        this.em.remove(reference);
    }

    public List<ToDo> all() {
        return this.em.createNamedQuery(ToDo.findAll, ToDo.class).
                getResultList();
    }

    public void save(ToDo todo) {
        this.em.merge(todo);
    }

}
