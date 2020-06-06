package com.example.demo.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest
@AutoConfigureMockMvc
class DemoControllerSpec extends Specification {

    @Autowired
    private MockMvc mockMvc

    def setup() {
    }

    @Unroll
    def "/demo/hello/{name} 200"() {
        when:
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/demo/hello/" + name)
        def actual = mockMvc.perform(request).andReturn().getResponse()

        then:
        actual.getStatus() == 200
        actual.getContentAsString() == text

        where:
        name        | text
        "hosono"    | "hello hosono!"
        "yoshikazu" | "hello yoshikazu!"
    }

    @Unroll
    def "/demo/hello/{name} 400"() {
        when:
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/demo/hello/" + name)
        def actual = mockMvc.perform(request).andReturn().getResponse()

        then:
        actual.getStatus() == 400
        actual.getContentAsString() == text

        where:
        name          | text
        "a"           | "hello.name: size must be between 2 and 10"
        "aaaaaaaaaaa" | "hello.name: size must be between 2 and 10"
        "00000"       | "hello.name: must match \"^[a-z]+\$\""
    }

}
