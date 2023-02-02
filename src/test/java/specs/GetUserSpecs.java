package specs;

import io.restassured.specification.RequestSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class GetUserSpecs {
        public static RequestSpecification GetUserRequestSpec = with()
                .log().uri()
                .log().headers()
                .log().body()
                .filter(withCustomTemplates())
                .baseUri("https://reqres.in")
                .basePath("/api");
}
