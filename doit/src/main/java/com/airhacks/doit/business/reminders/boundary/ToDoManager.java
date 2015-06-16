package com.airhacks.doit.business.reminders.boundary;

import com.airhacks.doit.business.reminders.entity.ToDo;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author airhacks.com
 */
@Stateless
public class ToDoManager {

    public ToDo findById(long id) {
        return new ToDo("implement REST endpoint " + id, "...", 100);
    }

    public void delete(long id) {
        System.out.println("Deleted: " + id);
    }

    public List<ToDo> all() {
        List<ToDo> all = new ArrayList<>();
        all.add(findById(42));
        return all;

    }

    public void save(ToDo todo) {
        System.out.println("Saving = " + todo);
    }

}
