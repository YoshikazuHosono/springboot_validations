package com.example.demo.controller;

import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Validated
@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/hello/{name}")
    public String getHello(@PathVariable @Size(min = 2, max = 10) @Pattern(regexp = "^[a-z]+$") String name) {
        return "hello " + name + "!";
    }

    @PostMapping(value = "/hello", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String postHello(@RequestBody PostHelloRequest request) {
        return "hello " + request.getName() + "!";
    }

}

@Data
class PostHelloRequest {
    private String name;
}