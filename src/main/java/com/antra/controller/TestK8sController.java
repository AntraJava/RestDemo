package com.antra.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class TestK8sController {
    // a test api for connect to external service

    RestTemplate rt = new RestTemplate();

    @GetMapping("/external")
    public String test1() {
        String response = rt.getForObject("https://catfact.ninja/fact", String.class);
        return response;
    }

    @GetMapping("/internal")
    public String test2(@RequestParam(required = false, defaultValue = "http://authService/auth") String url) {
        String response = rt.getForObject(url, String.class);
        return response;
    }
}

