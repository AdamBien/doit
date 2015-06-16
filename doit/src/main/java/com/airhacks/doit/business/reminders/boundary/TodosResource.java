package com.airhacks.doit.business.reminders.boundary;

import com.airhacks.doit.business.reminders.entity.ToDo;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Path("todos")
public class TodosResource {

    @Inject
    ToDoManager manager;

    @GET
    @Path("{id}")
    public ToDo find(@PathParam("id") long id) {
        return manager.findById(id);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") long id) {
        manager.delete(id);
    }

    @GET
    public List<ToDo> all() {
        return this.manager.all();
    }

    @POST
    public void save(ToDo todo) {
        this.manager.save(todo);
    }

}
