package com.mshatunov.pool.api.customer

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = [CustomerApiApplication])
class BaseIntegrationTest {

    @Autowired
    WebApplicationContext context

    protected MockMvc mockMvc

    @Before
    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build()
    }

    @Test
    void contextLoads() {
    }

}
