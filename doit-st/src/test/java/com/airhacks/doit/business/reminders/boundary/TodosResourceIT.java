package com.airhacks.doit.business.reminders.boundary;

import com.airhacks.rulz.jaxrsclient.JAXRSClientProvider;
import static com.airhacks.rulz.jaxrsclient.JAXRSClientProvider.buildWithURI;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;

/**
 *
 * @author airhacks.com
 */
public class TodosResourceIT {

    @Rule
    public JAXRSClientProvider provider = buildWithURI("http://localhost:8080/doit/api/todos");

    @Test
    public void crud() {
        JsonObjectBuilder todoBuilder = Json.createObjectBuilder();
        JsonObject todoToCreate = todoBuilder.
                add("caption", "implement").
                add("priority", 42).
                build();

        //create
        Response postResponse = this.provider.target().request().
                post(Entity.json(todoToCreate));
        assertThat(postResponse.getStatus(), is(201));
        String location = postResponse.getHeaderString("Location");
        System.out.println("location = " + location);

        //find
        JsonObject dedicatedTodo = this.provider.client().
                target(location).
                request(MediaType.APPLICATION_JSON).
                get(JsonObject.class);
        assertTrue(dedicatedTodo.getString("caption").contains("implement"));

        //update
        JsonObjectBuilder updateBuilder = Json.createObjectBuilder();
        JsonObject updated = updateBuilder.
                add("caption", "implemented").
                build();

        this.provider.client().
                target(location).
                request(MediaType.APPLICATION_JSON)
                .put(Entity.json(updated));

        //find it again
        //find
        JsonObject updatedTodo = this.provider.client().
                target(location).
                request(MediaType.APPLICATION_JSON).
                get(JsonObject.class);
        assertTrue(updatedTodo.getString("caption").contains("implemented"));

        //update status
        //update
        JsonObjectBuilder statusBuilder = Json.createObjectBuilder();
        JsonObject status = statusBuilder.
                add("done", true).
                build();

        this.provider.client().
                target(location).
                path("status").
                request(MediaType.APPLICATION_JSON)
                .put(Entity.json(status));

        //verify status
        updatedTodo = this.provider.client().
                target(location).
                request(MediaType.APPLICATION_JSON).
                get(JsonObject.class);
        assertThat(updatedTodo.getBoolean("done"), is(true));

        //update not existing status
        JsonObjectBuilder notExistingBuilder = Json.createObjectBuilder();
        status = notExistingBuilder.
                add("done", true).
                build();

        Response response = this.provider.target().
                path("-42").
                path("status").
                request(MediaType.APPLICATION_JSON)
                .put(Entity.json(status));
        assertThat(response.getStatus(), is(400));
        assertFalse(response.getHeaderString("reason").isEmpty());

        //update malformed status
        notExistingBuilder = Json.createObjectBuilder();
        status = notExistingBuilder.
                add("something wrong", true).
                build();

        response = this.provider.
                client().
                target(location).
                path("status").
                request(MediaType.APPLICATION_JSON)
                .put(Entity.json(status));
        assertThat(response.getStatus(), is(400));
        assertFalse(response.getHeaderString("reason").isEmpty());

        //findAll
        response = this.provider.target().
                request(MediaType.APPLICATION_JSON).
                get();
        assertThat(response.getStatus(), is(200));
        JsonArray allTodos = response.readEntity(JsonArray.class);
        System.out.println("payload " + allTodos);
        assertFalse(allTodos.isEmpty());

        JsonObject todo = allTodos.getJsonObject(0);
        assertTrue(todo.getString("caption").startsWith("impl"));

        //deleting not-existing
        Response deleteResponse = this.provider.target().
                path("42").
                request(MediaType.APPLICATION_JSON).delete();
        assertThat(deleteResponse.getStatus(), is(204));

    }

}
