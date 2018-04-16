package com.mshatunov.pool.api.customer.controller

import com.mshatunov.pool.api.customer.BaseIntegrationTest
import com.mshatunov.pool.api.customer.domain.ContactType
import com.mshatunov.pool.api.customer.repository.CustomerRepository
import com.mshatunov.pool.api.customer.repository.model.Customer
import org.junit.Test
import org.junit.platform.commons.util.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

class CustomerControllerTest extends BaseIntegrationTest {

    @Autowired
    CustomerRepository repository

    def classLoader = Thread.currentThread().getContextClassLoader()

    @Test
    void 'Successful client saving'() {

        def response = mockMvc.perform(MockMvcRequestBuilders
                .post('/')
                .content(classLoader.getResourceAsStream("json/successPostRequest.json").text)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andReturn()

        assertTrue(response.getResponse().status == HttpStatus.OK.value())

        def customerId = response.getResponse().contentAsString
        assertTrue(StringUtils.isNotBlank(customerId))

        Customer customer = repository.findById(customerId).get()

        assertEquals(customer.name, "name")
        assertEquals(customer.middleName, "middleName")
        assertEquals(customer.lastName, "lastName")
        assertEquals(customer.contacts.get(ContactType.PHONE), "phone")
        assertEquals(customer.contacts.get(ContactType.EMAIL), "email")
        assertEquals(customer.parents.get(0).name, "parentName")
        assertEquals(customer.parents.get(0).middleName, "parentMiddleName")
        assertEquals(customer.parents.get(0).lastName, "parentLastName")
        assertEquals(customer.parents.get(0).contacts.get(ContactType.PHONE), "parentPhone")
        assertEquals(customer.parents.get(0).contacts.get(ContactType.EMAIL), "parentEmail")
    }

}