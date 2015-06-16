package com.airhacks.doit.business.reminders.boundary;

import com.airhacks.rulz.jaxrsclient.JAXRSClientProvider;
import static com.airhacks.rulz.jaxrsclient.JAXRSClientProvider.buildWithURI;
import javax.json.JsonArray;
import javax.json.JsonObject;
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
        Response response = this.provider.target().request(MediaType.APPLICATION_JSON).get();
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
