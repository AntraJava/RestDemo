package com.antra.testClient.karate.getUser;

import com.intuit.karate.junit5.Karate;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserAPIKarateTest {


    @Karate.Test
    Karate testAll() {
        return Karate.run("user").relativeTo(getClass());
    }

}
