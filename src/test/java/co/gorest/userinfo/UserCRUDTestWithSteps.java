package co.gorest.userinfo;

import co.gorest.testbase.TestBase;
import co.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepProvider;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;
@RunWith(SerenityRunner.class)
public class UserCRUDTestWithSteps extends TestBase {

    static String name = "Dhoni" + TestUtils.getRandomValue();
    static String email = "mahish" + TestUtils.getRandomValue();
    static String gender = "male";
    static String status= "active";

    static int userId;

    @Steps
    UserSteps userSteps;

    @Title("this method will get all users")
    @Step
    public  void test001(){
        userSteps.getUserById(userId);
    }
    @Title("It will create new User")
    @Test

    public void test01(){
        ValidatableResponse getId =userSteps.createUser(name,email,gender,status);
        Object id = getId.extract().path("id");
        ValidatableResponse response = userSteps.getUserById(userId);
        ArrayList<?> userId = response.extract().path("id");
        Assert.assertTrue(userId.contains(id));
    }

    @Title("Update User details and verify the added details")
    @Test

    public void test03(){
       status ="inactive";
        userSteps.updateUser(userId,name,email,gender,status);
        ValidatableResponse response = userSteps.getUserById(userId).statusCode(200);

        HashMap<String,?> userRecord=response.extract().path("");
        Assert.assertThat(userRecord, hasValue("inactive"));
    }
    @Title("Delete the User and verify if the User is deleted")
    @Test

    public void test04(){
        userSteps.getUserById(userId).statusCode(404);
        userSteps.deleteUser(userId).statusCode(204);
    }


}
