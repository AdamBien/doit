package com.airhacks.doit.business.monitoring.boundary;

import com.airhacks.doit.business.monitoring.entity.CallEvent;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Path("boundary-invocations")
public class BoundaryInvocationsResource {

    @Inject
    MonitorSink ms;

    @GET
    public List<CallEvent> expose() {
        return this.ms.getRecentEvents();
    }

}
