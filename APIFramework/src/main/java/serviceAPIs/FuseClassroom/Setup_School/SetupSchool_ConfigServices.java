package serviceAPIs.FuseClassroom.Setup_School;

import io.restassured.response.ValidatableResponse;

import java.util.List;

import static frameworkBase.TestBotBase.*;
import static frameworkBase.TestBotServiceWrapper.random_Text;
import static io.restassured.RestAssured.expect;

public class SetupSchool_ConfigServices {

    public static String return_ID() {
        ValidatableResponse schoolOfOrgResponse = expect()
                .given()
                .spec(requestSpecification)
                .headers(getAuthData)
                .when()
                .get(SetupSchool_ConfigEP.SCHOOL_OF_ORG_GET)
                .then()
                .spec(responseSpecification);

        List<String> school_ID = schoolOfOrgResponse.extract().jsonPath().getList("content.id");
        String setupID = school_ID.get(0);

        return setupID;
    }

    public static String return_JsonSchoolData(String setupID) {
        String randomText = random_Text();
        ValidatableResponse schoolJsonData = expect()
                .given()
                .spec(requestSpecification)
                .headers(getAuthData)
                .when()
                .get(SetupSchool_ConfigEP.SCHOOL_SETUP_DATA_GET + setupID)
                .then()
                .spec(responseSpecification);

        String jsonObjectData = schoolJsonData.extract().jsonPath().prettify();
        String updatedJsonData = jsonObjectData.replaceAll("\"departmentName\": \"", "\"departmentName\": \""+randomText+"");
        return updatedJsonData;
    }

    /*public static String invalidMultiData()
    {
        String school_Data="{\"departments\":[{\"departmentName\":\""+ random_Text()+"\"},{\"departmentName\":\""+ random_Text()+"\"}],\"schoolOfOrgName\":\""+random_Text()+"\"}";

        ValidatableResponse schoolOfOrgResponse = expect()
                .given()
                .spec(requestSpecification)
                .headers( getAuthData)
                .body(school_Data)
                .when()
                .post(SetupSchool_ConfigEP.SCHOOL_OF_ORG_POST)
                .then()
                .spec(responseSpecification);
        String schoolID = schoolOfOrgResponse.extract().jsonPath().getString("id");

       return schoolID;
    }
*/
   /* public static String returnSchoolID(String name)
    {
        ValidatableResponse getSchoolResponse= expect()
                .given()
                .spec(requestSpecification)
                .headers(getAuthData)
                .when()
                .get(SetupSchool_ConfigEP.SCHOOL_OF_ORG_GET)
                .then()
                .spec(responseSpecification);

        String returnSchoolID = getSchoolResponse.extract().jsonPath().getString("content.find{it.schoolOfOrgName==\"" + name + "\"}.id");
        return returnSchoolID;
    }*/


    public static String return_multi_DepartmentID() {
        ValidatableResponse get_School_OrgResponse = expect()
                .given()
                .spec(requestSpecification)
                .headers(getAuthData)
                .when()
                .get(SetupSchool_ConfigEP.SCHOOL_OF_ORG_GET)
                .then()
                .spec(responseSpecification);
        List<String> department_list = get_School_OrgResponse.extract().jsonPath().getList("content.find{it.id==\"5fd0837a65fd2b005667721d\"}.departments.departmentId");
        String department_id=department_list.get(1);
        return department_id;
    }

public static String return_ModifiedDate()
{
    ValidatableResponse get_School_OrgResponse1 = expect()
            .given()
            .spec(requestSpecification)
            .headers(getAuthData)
            .when()
            .get(SetupSchool_ConfigEP.SCHOOL_SETUP_DATA_GET+"5fd0837a65fd2b005667721d")
            .then()
            .spec(responseSpecification);
    String modifiedDate = get_School_OrgResponse1.extract().jsonPath().getString("modifiedDate");
return modifiedDate;
}

public static void add_Department(String modifiedDate)
{
    String schoolData="{\"id\":\"5fd0837a65fd2b005667721d\",\"schoolOfOrgName\":\"Fuse-master\",\"createdDate\":1607500665955,\"modifiedDate\":"+modifiedDate+",\"programs\":[],\"departments\":[{\"departmentId\":\"5fd2f29ac679f10059c899d6\",\"departmentName\":\"Fuse-qa1\"},{\"departmentName\":\"Fuse-qa2\"}],\"degrees\":[],\"totalSchoolOfOrg\":0}";
    ValidatableResponse put_School = expect()
            .given()
            .spec(requestSpecification)
            .headers(getAuthData)
            .body(schoolData)
            .when()
            .put(SetupSchool_ConfigEP.SCHOOL_SETUP_DATA_GET+"5fd0837a65fd2b005667721d")
            .then()
            .spec(responseSpecification);

}

}

