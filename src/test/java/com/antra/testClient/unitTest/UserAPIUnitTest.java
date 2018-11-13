package com.antra.testClient.unitTest;

import com.antra.controller.UserRestController;
import com.antra.service.UserService;
import com.antra.util.Constants;
import com.antra.vo.User;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = RANDOM_PORT)
public class UserAPIUnitTest {

    @Mock
    UserService userService;

    @Mock
    Constants messages;

    @Before
    public void configMock() {
        MockitoAnnotations.initMocks(this);
        RestAssuredMockMvc.standaloneSetup(new UserRestController(userService, messages));
        Mockito.when(messages.getMessage(anyObject())).thenReturn("Mocked Message");
    }

    @Test
    public void testGetUserFromDB(){
        Mockito.when(userService.findById(anyInt())).thenReturn(new User(1l,"test name", 20, 10000d));
        given().accept("application/json").get("/api/user/1").peek().
                then().assertThat()
                .statusCode(200)
                .body(Matchers.equalTo("{\"id\":1,\"name\":\"test name\",\"age\":20,\"salary\":10000.0}"));
    }

    @Test
    public void testGetUserFromEmptyDB(){
        Mockito.when(userService.findById(anyInt())).thenReturn(null);
        given().accept("application/json").get("/api/user/1").peek().
                then().assertThat()
                .statusCode(404)
                .body("errorCode",Matchers.equalTo(404));
    }

    @Test
    public void createUser(){
        User testUser = new User(null,"test name", 20, 10000d);
        User savedUser = new User(1l,"test name", 20, 10000d);
        Mockito.when(userService.saveUser(anyObject())).thenReturn(savedUser);
        given().accept("application/json").contentType("application/json").body(testUser).post("/api/user").peek().
                then().assertThat()
                .statusCode(201)
                .body("body.id",Matchers.is(1));
    }

    @Test
    public void createUserButExceptionRaised(){
        User testUser = new User(null,"test name", 20, 10000d);
        User savedUser = new User(1l,"test name", 20, 10000d);
        Mockito.when(userService.saveUser(anyObject())).thenThrow(new RuntimeException("dummy error"));
        given().accept("application/json").contentType("application/json").body(testUser).post("/api/user").peek().
                then().assertThat()
                .statusCode(500)
                .body("message",Matchers.is("dummy error"));
    }
}
