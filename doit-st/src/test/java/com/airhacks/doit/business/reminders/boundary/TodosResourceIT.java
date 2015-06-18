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
        Response postResponse = this.provider.target().request().
                post(Entity.json(todoToCreate));
        assertThat(postResponse.getStatus(), is(201));
        String location = postResponse.getHeaderString("Location");
        System.out.println("location = " + location);

        Response response = this.provider.target().
                request(MediaType.APPLICATION_JSON).
                get();
        assertThat(response.getStatus(), is(200));
        JsonArray allTodos = response.readEntity(JsonArray.class);
        System.out.println("payload " + allTodos);
        assertFalse(allTodos.isEmpty());

        JsonObject todo = allTodos.getJsonObject(0);
        assertTrue(todo.getString("caption").startsWith("impl"));

        //GET with id
        JsonObject dedicatedTodo = this.provider.target().
                path("42").
                request(MediaType.APPLICATION_JSON).
                get(JsonObject.class);
        assertTrue(dedicatedTodo.getString("caption").contains("42"));

        Response deleteResponse = this.provider.target().
                path("42").
                request(MediaType.APPLICATION_JSON).delete();
        assertThat(deleteResponse.getStatus(), is(204));

    }

}
