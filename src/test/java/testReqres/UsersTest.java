package testReqres;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class UsersTest {
    @Test
    @DisplayName("Создание пользователя")
    void createUsersTestName(){
        String body = "{ \"name\": \"neo\", \"job\": \"manager\"}";

        given()
                .log().uri()
                .contentType(JSON)
                .body(body)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("neo"))
                .body("job", is("manager"));

    }
    @Test
    @DisplayName("Неверный формат создания пользователя")
    void createUsersTestFail(){

        given()
                .log().uri()
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(415);



    }
    @Test
    @DisplayName("Получение пользователя по id")
    void getUserId(){
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.first_name", is("Janet"));

    }
    @Test
    @DisplayName("Пользователь не найден")
    void getUserIdNotFound(){
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users/464848")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);

    }
    @Test
    @DisplayName("Удаление пользователя")
    void deleteUserId() {
        given()
                .log().uri()
                .when()
                .delete("https://reqres.in/api/users/5")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
}

