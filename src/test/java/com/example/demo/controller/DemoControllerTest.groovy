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
    def "test"() {
        when:
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/demo/hello/" + name)
        def actual = mockMvc.perform(request).andReturn().getResponse()

        then:
        actual.getStatus() == 200
        actual.getContentAsString() == text

        where:
        name        | text
        "hosono"    | "hello hosono!" // success
        "yoshikazu" | "hello yoshikaze!" // error
    }

}
