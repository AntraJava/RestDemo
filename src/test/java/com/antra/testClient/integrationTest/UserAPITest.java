package com.antra.testClient.integrationTest;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserAPITest {

    @Value("http://localhost:${local.server.port}/api")
    private String REST_SERVICE_URI ;

//    @Test
//    public void listAllUsers(){
//        get(REST_SERVICE_URI + "/user").peek().then()
//                .statusCode(200)
//                .body("body.size()", Matchers.greaterThan(0));
//    }
      
    @Test
    public void getExistingUserByID(){
        when().
                get(REST_SERVICE_URI + "/user/1").peek().
        then().assertThat()
                .statusCode(200)
                .body("id", Matchers.notNullValue())
                .body("name",Matchers.equalTo("david"));
    }

    @Test
    public void getNoneExistingUserByID(){
        when().
                get(REST_SERVICE_URI + "/user/100").peek().
                then().assertThat()
                .statusCode(404)
                .body("errorCode",Matchers.equalTo(404));
    }

    @Test
    public void tryToSendPostRequestToFindUserById(){
        when().
                post(REST_SERVICE_URI + "/user/1").peek().
                then().assertThat()
                .statusCode(405);
    }

    @Test
    public void getUserUsingPagination(){
        when().
                get(REST_SERVICE_URI + "/user?page={1}&rows={2}&orderBy={3}",0,1,"name").peek().
                then().assertThat()
                .statusCode(200)
                .body("page",Matchers.equalTo(0))
                .body("body", Matchers.notNullValue());
    }
      /*
     @Test
     public void createUser() {
        System.out.println("Testing create User API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = new User(0,"Sarah",51,134);
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/user/", user, User.class);
        System.out.println("Location : "+uri.toASCIIString());
    }
  
     @Test
     public void updateUser() {
        System.out.println("Testing update User API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user  = new User(1,"Tomy",33, 70000);
        restTemplate.put(REST_SERVICE_URI+"/user/1", user);
        System.out.println(user);
    }
  
     @Test
     public void deleteUser() {
        System.out.println("Testing delete User API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/user/3");
    }
  
  
     @Test
     public void deleteAllUsers() {
        System.out.println("Testing all delete Users API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/user/");
    }*/

}
