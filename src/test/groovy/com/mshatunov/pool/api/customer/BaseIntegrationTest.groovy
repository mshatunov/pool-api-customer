package com.mshatunov.pool.api.customer

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

import javax.annotation.PostConstruct

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = [CustomerApiApplication])
class BaseIntegrationTest {

    @Autowired
    WebApplicationContext context

    def mockMvc

    @PostConstruct
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
    }

    @Test
    void contextLoads() {
    }

}
