package ru.smartcoder.members;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.smartcoder.members.web.MembersController;

import static com.jayway.restassured.RestAssured.given;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class MembersControllerTest {

    @Test
    public void testThatWeGetXmlWithAcceptHeaderXml() {
        given().header("Accept", "application/xml").
                when().
                get(MembersController.REST_URL).
                then().statusCode(HttpStatus.OK.value()).contentType("application/xml;charset=UTF-8");
    }

    @Test
    public void testThatWeGetJsonWithAcceptHeaderJson() {
        given().header("Accept", "application/json").
                when().
                get(MembersController.REST_URL).
                then().statusCode(HttpStatus.OK.value()).contentType("application/json;charset=UTF-8");
    }


}
