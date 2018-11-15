package io.bettoni.contactlist;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import io.bettoni.contactlist.person.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonApiListApplicationTests {

    private static final Person HOMER = new Person("Homer Simpson", " https://vignette.wikia.nocookie.net/simpsons/images/b/bd/Homer_Simpson.png/revision/latest/scale-to-width-down/72?cb=20140126234206");
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_return_a_list_of_person() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/api/people?page_size=1&page=1")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(HOMER).isEqualTo(objectFrom(response.getContentAsString()));


    }

    private Person objectFrom(String contentAsString) {
        JsonArray jsonValues = Json.parse(contentAsString).asArray();
        JsonObject jsonObject = jsonValues.get(0).asObject();
        return new Person(jsonObject.get("name").asString(), jsonObject.get("photoUrl").asString());

    }

}
