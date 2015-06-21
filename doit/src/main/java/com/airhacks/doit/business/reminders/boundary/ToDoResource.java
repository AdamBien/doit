package com.airhacks.doit.business.reminders.boundary;

import com.airhacks.doit.business.reminders.entity.ToDo;
import javax.json.JsonObject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author airhacks.com
 */
public class ToDoResource {

    long id;
    ToDoManager manager;

    public ToDoResource(long id, ToDoManager manager) {
        this.id = id;
        this.manager = manager;
    }

    @PUT
    public ToDo delete(ToDo todo) {
        todo.setId(id);
        return manager.save(todo);
    }

    @GET
    public ToDo find() {
        return manager.findById(id);
    }

    @DELETE
    public void delete() {
        manager.delete(id);
    }

    @PUT
    @Path("/status")
    public Response statusUpdate(JsonObject statusUpdate) {
        if (!statusUpdate.containsKey("done")) {
            return Response.status(Response.Status.BAD_REQUEST).
                    header("reason", "JSON should contains field done").
                    build();
        }
        boolean done = statusUpdate.getBoolean("done");
        ToDo todo = manager.updateStatus(id, done);
        if (todo == null) {
            return Response.status(Response.Status.BAD_REQUEST).
                    header("reason", "todo with id " + id + " does not exist").
                    build();
        } else {
            return Response.ok(todo).build();
        }
    }

}
