package co.gorest.userinfo;

import co.gorest.constants.EndPoints;
import co.gorest.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.yecht.Data;

import javax.xml.ws.Endpoint;
import java.util.HashMap;

public class UserSteps {
 static final String token ="Bearer 4b23cf2956abd9c30acd1ed39e644dd4716c8a85c1a6acde3b4f5070bd79f4b3";
    @Step("Creating User with name:{0},email:{1},gender:{2},status:{3}")

    public ValidatableResponse createUser(String name, String email, String gender, String status) {

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .when()
                .body(userPojo)
                .when()
                .post("/users")
                .then().log().all().statusCode(201);
    }

    @Step("Getting detail of Users")

    public ValidatableResponse getUserDetailByName(String name) {
        return SerenityRest.given().log().all()
                .header("Authorization", token)
                .header("Connection", "keep-alive")
                .when()
                .get(EndPoints.Get_all_Users)
                .then().log().all()
                .statusCode(200);


    }
    @Step("Updating the detail of User with userId{0},name{1},email{2},gender{3},status{4}")

    public ValidatableResponse updateUser(int userId,String name,String email,String gender,String status){

        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
         return SerenityRest.given().log().all()
                 .header("Authorization", token)
                .header("Content-Type","application/json")
                 .header("Connection", "keep-alive")
                .pathParam("userId",userId)
                .body(userPojo)
                .when()
                .put(EndPoints.Update_User_By_Id)
                .then().log().all().statusCode(200);


    }

    @Step("Getting User detail with UserId :{0}")

    public ValidatableResponse getUserById(int userId){
        return SerenityRest.given().log().all()
                .header("Authorization", token)
                .header("Connection", "keep-alive")
                .pathParam("userId",userId)
                .when()
                .get(EndPoints.Get_Single_User_By_Id)
                .then();
    }
    @Step("Deleting User detail with UserId :{0}")

    public ValidatableResponse deleteUser(int userId){
        return SerenityRest.given().log().all()
                .header("Authorization", token)
                .header("Connection", "keep-alive")
                .pathParam("userId",userId)
                .when()
                .delete(EndPoints.Delete_User_By_Id)
                .then().log().all();
    }
    }
