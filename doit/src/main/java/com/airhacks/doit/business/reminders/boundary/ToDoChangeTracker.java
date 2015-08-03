package com.airhacks.doit.business.reminders.boundary;

import com.airhacks.doit.business.reminders.entity.ToDo;
import java.io.IOException;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author airhacks.com
 */
@Singleton
@ServerEndpoint("/changes")
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

    public void onToDoChange(@Observes(during = TransactionPhase.AFTER_SUCCESS) ToDo todo) {
        if (this.session != null && this.session.isOpen()) {
            try {
                this.session.getBasicRemote().sendText(todo.toString());
            } catch (IOException ex) {
                //we ignore this
            }
        }
    }

}
