package com.airhacks.doit.business.monitoring.boundary;

import com.airhacks.doit.business.logging.boundary.LogSink;
import com.airhacks.doit.business.monitoring.entity.CallEvent;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 *
 * @author airhacks.com
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class MonitorSink {

    @Inject
    LogSink LOG;

    public void onCallEvent(@Observes CallEvent event) {
        LOG.log(event.toString());
    }

}
