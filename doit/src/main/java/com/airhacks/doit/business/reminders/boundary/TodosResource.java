package com.airhacks.doit.business.reminders.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author airhacks.com
 */
@Path("todos")
public class TodosResource {

    @GET
    public String hello() {
        return "hey " + System.currentTimeMillis();
    }

}
