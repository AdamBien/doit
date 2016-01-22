package com.airhacks.reminders.boundary;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author airhacks.com
 */
@Stateless
public class RemindersManager {

    @PersistenceContext
    EntityManager em;

}
