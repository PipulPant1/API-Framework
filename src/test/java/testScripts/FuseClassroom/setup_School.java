package testScripts.FuseClassroom;

import frameworkBase.TestBotBase;
import frameworkBase.TestBotServiceWrapper;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import serviceAPIs.FuseClassroom.Setup_School.SetupSchool_ConfigDP;
import serviceAPIs.FuseClassroom.Setup_School.SetupSchool_ConfigEP;
import serviceAPIs.FuseClassroom.Setup_School.SetupSchool_ConfigServices;

import java.io.UnsupportedEncodingException;

import static frameworkBase.TestBotServiceWrapper.assertionEquals;
import static io.restassured.RestAssured.expect;
import static serviceAPIs.FuseClassroom.Setup_School.SetupSchool_ConfigServices.return_ModifiedDate;
import static serviceAPIs.FuseClassroom.Setup_School.SetupSchool_ConfigServices.return_multi_DepartmentID;

public class setup_School extends TestBotBase {

    String multiDepartment_schoolID =null;

    @BeforeTest()
    public void Login() throws Exception {
        TestBotServiceWrapper.setNewUserSession(username,password);

    }
    @Epic("Setup School")
    @Test(description = "Setup school with valid data",dataProvider = "addSchoolValidData",dataProviderClass = SetupSchool_ConfigDP.class)
    @Description("Setting up the School with valid data")
    @Severity(SeverityLevel.BLOCKER)
    public void Test001_setupSchool_ValidData(String school_name ,String dept_name)
    {
        String school_Data="{\"departments\":[{\"departmentName\":\""+dept_name+"\"}],\"schoolOfOrgName\":\""+school_name+"\"}";


        ValidatableResponse schoolOfOrgResponse = expect()
                .given()
                .spec(requestSpecification)
                .headers( getAuthData)
                .body(school_Data)
                .when()
                .post(SetupSchool_ConfigEP.SCHOOL_OF_ORG_POST)
                .then()
                .spec(responseSpecification);

        assertionEquals(school_name,schoolOfOrgResponse.extract().jsonPath().getString("schoolOfOrgName"),"Assertion for School Name");
        assertionEquals(statusCode_200,""+schoolOfOrgResponse.extract().statusCode(),"Status Code Verification");
    }

    @Epic("Setup School")
    @Test(description = "Setup school with invalid data",dataProvider = "addSchoolInvalidData",dataProviderClass = SetupSchool_ConfigDP.class)
    @Description("Setting up the School with invalid school/department data")
    @Severity(SeverityLevel.BLOCKER)
    public void Test002_setupSchool_InvalidData(String school_name,String dept_name,String expected)
    {
        String school_Data="{\"departments\":[{\"departmentName\":\""+dept_name+"\"}],\"schoolOfOrgName\":\""+school_name+"\"}";


        ValidatableResponse schoolOfOrgResponse = expect()
                .given()
                .spec(requestSpecification)
                .headers( getAuthData)
                .body(school_Data)
                .when()
                .post(SetupSchool_ConfigEP.SCHOOL_OF_ORG_POST)
                .then()
                .spec(responseSpecification);

        assertionEquals(statusCode_400,""+schoolOfOrgResponse.extract().statusCode(),"Status Code Verification");
        assertionEquals(expected,schoolOfOrgResponse.extract().jsonPath().getString("message"),"Message String Verification");
    }

    @Epic("Setup School")
    @Test(description = "Setup School with valid multiple department data",dataProvider = "multiDepartmentValidData",dataProviderClass = SetupSchool_ConfigDP.class)
    @Description("Setting up the School with valid data for multiple Departments")
    @Severity(SeverityLevel.BLOCKER)
    public void Test003_setupSchool_MultipleDepartment_validData(String dept_name1,String dept_name2,String dept_name3, String school_name)
    {
        String multiDepartment_school_Data="{\"departments\":[{\"departmentName\":\""+dept_name1+"\"},{\"departmentName\":\""+dept_name2+"\"},{\"departmentName\":\""+dept_name3+"\"}],\"schoolOfOrgName\":\""+school_name+"\"}";


        ValidatableResponse schoolOfOrgResponse = expect()
                .given()
                .spec(requestSpecification)
                .headers( getAuthData)
                .body(multiDepartment_school_Data)
                .when()
                .post(SetupSchool_ConfigEP.SCHOOL_OF_ORG_POST)
                .then()
                .spec(responseSpecification);

        assertionEquals(school_name,schoolOfOrgResponse.extract().jsonPath().getString("schoolOfOrgName"),"Assertion for School Name");
        assertionEquals(statusCode_200,""+schoolOfOrgResponse.extract().statusCode(),"Status Code Verification");
    }

    @Epic("Setup School")
    @Test(description = "Adding invalid data with multiple departments ",dataProvider = "multiDepartmentInvalidData",dataProviderClass = SetupSchool_ConfigDP.class)
    @Description("Setting up the School with invalid data for multiple Departments")
    @Severity(SeverityLevel.BLOCKER)
    public void Test004_setupSchool_MultipleDepartment_InvalidData(String dept_name1,String dept_name2,String dept_name3, String school_name)
    {
        String multiDepartment_InvalidData="{\"departments\":[{\"departmentName\":\""+dept_name1+"\"},{\"departmentName\":\""+dept_name2+"\"},{\"departmentName\":\""+dept_name3+"\"}],\"schoolOfOrgName\":\""+school_name+"\"}";


        ValidatableResponse schoolOfOrgResponse = expect()
                .given()
                .spec(requestSpecification)
                .headers( getAuthData)
                .body(multiDepartment_InvalidData)
                .when()
                .post(SetupSchool_ConfigEP.SCHOOL_OF_ORG_POST)
                .then()
                .spec(responseSpecification);

        assertionEquals(statusCode_400,""+schoolOfOrgResponse.extract().statusCode(),"Status Code Verification");
    }

    @Epic("Setup School")
    @Test(description = "Modify School with valid data")
    @Description("Modifying the school with valid department,School")
    @Severity(SeverityLevel.BLOCKER)
    public void Test005_modifySchool_ValidData()
    {
        String schoolId = SetupSchool_ConfigServices.return_ID();
        String jsonSchoolData = SetupSchool_ConfigServices.return_JsonSchoolData(schoolId);

        ValidatableResponse schoolOfOrgResponse = expect()
                .given()
                .spec(requestSpecification)
                .headers( getAuthData)
                .body(jsonSchoolData)
                .when()
                .put(SetupSchool_ConfigEP.SCHOOL_OF_ORG_PUT+schoolId)
                .then()
                .spec(responseSpecification);

        assertionEquals(schoolId,schoolOfOrgResponse.extract().jsonPath().getString("id"),"Assertion for School ID");
        TestBotServiceWrapper.assertionEquals(statusCode_200,""+schoolOfOrgResponse.extract().statusCode(),"Status Code Verification");
    }

    @Epic("Setup School")
    @Test(description = "Modify School with Invalid data",dataProvider = "modifyInvalidData",dataProviderClass = SetupSchool_ConfigDP.class)
    @Description("Modifying the school with Invalid data")
    @Severity(SeverityLevel.NORMAL)
    public void Test006_modifySchool_InvalidData(String school_name,String dept_name1,String dept_name2, String expected)
    {
        String test="{\"id\":\"5fcef4bde31ad00042e62ba1\",\"schoolOfOrgName\":\""+school_name+"\",\"createdDate\":1607398589626,\"modifiedDate\":1607398589626,\"programs\":[],\"departments\":[{\"departmentId\":\"5fcef4bde31ad00042e62ba2\",\"departmentName\":\""+dept_name1+"\"},{\"departmentId\":\"5fcef4bde31ad00042e62ba3\",\"departmentName\":\""+dept_name2+"\"}],\"degrees\":[],\"totalSchoolOfOrg\":0}";
        //assertionEquals("5fcef4bde31ad00042e62ba1",multiData_schoolID,"assertion");
        ValidatableResponse schoolOfOrgResponse = expect()
                .given()
                .spec(requestSpecification)
                .headers( getAuthData)
                .body(test)
                .when()
                .put(SetupSchool_ConfigEP.SCHOOL_OF_ORG_PUT+ multiDepartment_schoolID)
                .then()
                .spec(responseSpecification);

        assertionEquals(statusCode_400,""+schoolOfOrgResponse.extract().statusCode(),"Status Code Verification");
        assertionEquals(expected,schoolOfOrgResponse.extract().jsonPath().getString("message"),"Message String Verification");
    }

    //discuss for get SCHOOL api automation
    //delete API automation

    @Epic("Setup School")
    @Test(description = "Delete School with valid data")
    @Description("Modifying the school with valid data")
    @Severity(SeverityLevel.NORMAL)
    public void Test007_deleteDepartment_ValidData() throws UnsupportedEncodingException {

        String multiDepartmentID = return_multi_DepartmentID();

        ValidatableResponse schoolOfOrgResponses = expect()
                .given()
                .spec(requestSpecification)
                .header("Accept","application/json")
                .headers( getAuthData)
                .when()
                .delete(SetupSchool_ConfigEP.SCHOOL_OF_ORG_DELETE + multiDepartmentID)
                .then()
                .spec(responseSpecification);

        assertionEquals("Department deleted successfully",schoolOfOrgResponses.extract().response().prettyPrint(),"Assertion for deletion");
        assertionEquals(statusCode_200,""+schoolOfOrgResponses.extract().statusCode(),"Assertion for status code");

        //Adding the deleted department id for next test scenario
        SetupSchool_ConfigServices.add_Department(return_ModifiedDate());

    }
    @Epic("Setup School")
    @Test(description = "Delete School with Invalid data",dataProvider = "InvalidDepartmentID",dataProviderClass = SetupSchool_ConfigDP.class)
    @Description("Delete the school with Invalid data")
    @Severity(SeverityLevel.NORMAL)
    public void Test008_deleteDepartment_InvalidData(String department_ID,String expected) {

        ValidatableResponse schoolOfOrgResponses = expect()
                .given()
                .spec(requestSpecification)
                .header("Accept","application/json")
                .headers( getAuthData)
                .when()
                .delete(SetupSchool_ConfigEP.SCHOOL_OF_ORG_DELETE + department_ID)
                .then()
                .spec(responseSpecification);

        assertionEquals(expected,schoolOfOrgResponses.extract().response().prettyPrint(),"Assertion for deletion");
        assertionEquals(statusCode_400,""+schoolOfOrgResponses.extract().statusCode(),"Assertion for status code");;

    }
}
