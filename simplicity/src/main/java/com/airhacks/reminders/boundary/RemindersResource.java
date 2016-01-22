package com.airhacks.reminders.boundary;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Path("reminders")
public class RemindersResource {

    @Inject
    RemindersManager rm;

}
