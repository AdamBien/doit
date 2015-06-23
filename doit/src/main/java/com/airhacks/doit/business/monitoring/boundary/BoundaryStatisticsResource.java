package com.airhacks.doit.business.monitoring.boundary;

import java.util.LongSummaryStatistics;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author airhacks.com
 */
@Stateless
@Path("boundary-statistics")
public class BoundaryStatisticsResource {

    @Inject
    MonitorSink ms;

    @GET
    public JsonObject get() {
        LongSummaryStatistics statistics = ms.getStatistics();
        return Json.createObjectBuilder().add("average-duration", statistics.getAverage()).
                add("invocation-count", statistics.getCount()).
                add("min-duration", statistics.getMin()).
                add("max-duration", statistics.getMax()).build();
    }

}
