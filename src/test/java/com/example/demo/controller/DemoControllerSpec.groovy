package com.example.demo.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
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
    def "get /demo/hello/{name} 200"() {
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
    def "get /demo/hello/{name} 400"() {
        when:
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/demo/hello/" + name)
        def actual = mockMvc.perform(request).andReturn().getResponse()

        then:
        actual.getStatus() == 400
        actual.getContentAsString() == text

        where:
        name          | text
        "a"           | "getHello.name: size must be between 2 and 10"
        "aaaaaaaaaaa" | "getHello.name: size must be between 2 and 10"
        "00000"       | "getHello.name: must match \"^[a-z]+\$\""
    }

    @Unroll
    def "post /demo/hello 200"() {
        when:
        PostHelloRequest param = new PostHelloRequest()
        param.setName(name)
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/demo/hello")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(param))
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
    def "post /demo/hello 400"() {
        when:
        PostHelloRequest param = new PostHelloRequest()
        param.setName(name)
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/demo/hello")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(param))
        def actual = mockMvc.perform(request).andReturn().getResponse()

        then:
        actual.getStatus() == 400
        actual.getContentAsString() == text

        where:
        name          | text
        "a"           | "name : size must be between 2 and 10"
        "aaaaaaaaaaa" | "name : size must be between 2 and 10"
        "00000"       | "name : must match \"^[a-z]+\$\""
        null          | "name : must not be null"
    }

    @Unroll
    def "get /demo/hello/GroupedAnnotation/{name} 200"() {
        when:
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/demo/hello/GroupedAnnotation/" + name)
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
    def "get /demo/hello/GroupedAnnotation/{name} 400"() {
        when:
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/demo/hello/GroupedAnnotation/" + name)
        def actual = mockMvc.perform(request).andReturn().getResponse()

        then:
        actual.getStatus() == 400
        actual.getContentAsString() == text

        where:
        name          | text
        "a"           | "getHelloGroupedAnnotation.name: size must be between 2 and 10"
        "aaaaaaaaaaa" | "getHelloGroupedAnnotation.name: size must be between 2 and 10"
        "00000"       | "getHelloGroupedAnnotation.name: must match \"^[a-z]+\$\""
    }


    @Unroll
    def "post /demo/hello/GroupedAnnotation 200"() {
        when:
        PostHelloRequest param = new PostHelloRequest()
        param.setName(name)
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/demo/hello/GroupedAnnotation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(param))
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
    def "post /demo/hello/GroupedAnnotation 400"() {
        when:
        PostHelloRequest param = new PostHelloRequest()
        param.setName(name)
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/demo/hello/GroupedAnnotation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(param))
        def actual = mockMvc.perform(request).andReturn().getResponse()

        then:
        actual.getStatus() == 400
        actual.getContentAsString() == text

        where:
        name          | text
        "a"           | "name : size must be between 2 and 10"
        "aaaaaaaaaaa" | "name : size must be between 2 and 10"
        "00000"       | "name : must match \"^[a-z]+\$\""
        null          | "name : must not be null"
    }

    @Unroll
    def "post /demo/CorrelationCheck/passwordCheck 200"() {
        when:
        PostHelloCorrelationCheckRequest param = new PostHelloCorrelationCheckRequest()
        param.setPassword(password)
        param.setConfirmPassword(confirmPassword)
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/demo/CorrelationCheck/passwordCheck")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(param))
        def actual = mockMvc.perform(request).andReturn().getResponse()

        then:
        actual.getStatus() == 200
        actual.getContentAsString() == text

        where:
        password     | confirmPassword | text
        "password01" | "password01"    | "check ok!"
        "password02" | "password02"    | "check ok!"
    }

    @Unroll
    def "post /demo/CorrelationCheck/passwordCheck 400"() {
        when:
        PostHelloCorrelationCheckRequest param = new PostHelloCorrelationCheckRequest()
        param.setPassword(password)
        param.setConfirmPassword(confirmPassword)
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/demo/CorrelationCheck/passwordCheck")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(param))
        def actual = mockMvc.perform(request).andReturn().getResponse()

        then:
        actual.getStatus() == 400
        actual.getContentAsString() == text

        where:
        id | password      | confirmPassword | text
        0  | null          | "password01"    | "password : must not be null"
        1  | "password01"  | null            | "confirmPassword : must not be null"
        5  | "password01"  | "password02"    | "passwordCheckResult : must be true"
        6  | "password02"  | "password01"    | "passwordCheckResult : must be true"
    }

}
