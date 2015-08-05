package com.airhacks.doit.business.reminders.boundary;

import com.airhacks.doit.business.encoders.JsonEncoder;
import com.airhacks.doit.business.reminders.entity.ToDo;
import java.io.IOException;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author airhacks.com
 */
@Singleton
@ServerEndpoint(value = "/changes", encoders = {JsonEncoder.class})
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class ToDoChangeTracker {

    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }

    @OnClose
    public void onClose() {
        this.session = null;
    }

    public void onToDoChange(@Observes(during = TransactionPhase.AFTER_SUCCESS) @ChangeEvent(ChangeEvent.Type.CREATION) ToDo todo) throws EncodeException {
        if (this.session != null && this.session.isOpen()) {
            try {
                JsonObject event = Json.createObjectBuilder().
                        add("id", todo.getId()).
                        add("cause", "creation").
                        build();
                this.session.getBasicRemote().sendObject(event);
            } catch (IOException ex) {
                //we ignore this
            }
        }
    }

}
