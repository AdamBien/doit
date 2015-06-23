package com.airhacks.doit.business.logging.boundary;

/**
 *
 * @author airhacks.com
 */
@FunctionalInterface
public interface LogSink {

    void log(String msg);
}
