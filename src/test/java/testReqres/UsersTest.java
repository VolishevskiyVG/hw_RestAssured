package testReqres;

import io.qameta.allure.restassured.AllureRestAssured;
import models.lombok.UserBodyRequestModel;
import models.lombok.UserBodyResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.assertj.core.api.Assertions.assertThat;


public class UsersTest {
    @Test
    @DisplayName("Создание пользователя")
    void createUsersTestName() {
        UserBodyRequestModel data = new UserBodyRequestModel();
        data.setName("morpheus");
        data.setJob("leader");

        UserBodyResponseModel response = given()
                .log().uri()
                .log().headers()
                .log().body()
                .filter(withCustomTemplates())
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().as(UserBodyResponseModel.class);
        assertThat(response.getName()).isEqualTo("morpheus");
        assertThat(response.getJob()).isEqualTo("leader");
        assertThat(response.getId() != null);
        assertThat(response.getCreatedAt() != null);
    }

    @Test
    @DisplayName("Неверный формат создания пользователя")
    void createUsersTestFail() {

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
    void getUserId() {
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
    void getUserIdNotFound() {
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

