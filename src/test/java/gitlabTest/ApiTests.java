package gitlabTest;

import io.qameta.allure.testng.Tag;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
@Tag("API")
public class ApiTests {

    @BeforeSuite
    public static void setBaseUrl(){
        RestAssured.baseURI = "https://api.spacexdata.com/v4";
    }

    @Test(groups = "ap")
    public void checkCeoIsElonMusk(){
        given().get("/company")
                .then().log().body()
                .body("ceo", equalTo("Elon Musk"));
    }

    @Test(groups = "ap")
    public void checkLinksIsFour(){
        LinkedHashMap<String, String> links = given().get("/company")
                .then().log().body()
                .extract().body().jsonPath().get("links");
        Assert.assertEquals(4, links.size());
    }

    @Test(groups = "ap")
    public void crewMembersIs30(){
        ArrayList members = given().get("/crew")
                .then().log().body()
                .extract().body().as(ArrayList.class);
        Assert.assertTrue(members.size() == 30);
    }

    @Test(groups = "ap")
    public void firstMemberIsRobert(){
        ArrayList members = given().get("/crew")
                .then()
                .extract().body().as(ArrayList.class);

        LinkedHashMap firstMember = (LinkedHashMap) members.get(0);
        String id = (String) firstMember.get("id");

        Response response = given().get("/crew/"+id)
                .then().log().body()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        String name = jsonPath.getString("name");

        Assert.assertEquals("Robert Behnken", name);
    }

    @Test(groups = "ap")
    public void lastMemberHasNoLaunches(){
        List<MemberPojo> members = given().get("/crew")
                .then()
                .extract().body().jsonPath().getList("",MemberPojo.class);

        String lastMemberId = members.get(members.size()-1).getId();

        MemberPojo lastMember = given().get("/crew/"+lastMemberId)
                .then().log().body()
                .extract().as(MemberPojo.class);

        Assert.assertEquals(lastMember.getName(), "Anna Kikina");
    }

}
