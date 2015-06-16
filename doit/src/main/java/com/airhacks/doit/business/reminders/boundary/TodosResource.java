package com.airhacks.doit.business.reminders.boundary;

import com.airhacks.doit.business.reminders.entity.ToDo;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author airhacks.com
 */
@Path("todos")
public class TodosResource {

    @GET
    public ToDo hello() {
        return new ToDo("implement REST endpoint", "...", 100);
    }

}
