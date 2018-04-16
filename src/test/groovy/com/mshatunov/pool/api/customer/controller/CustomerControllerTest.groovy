package com.mshatunov.pool.api.customer.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.mshatunov.pool.api.customer.BaseIntegrationTest
import com.mshatunov.pool.api.customer.controller.dto.CustomerResponse
import com.mshatunov.pool.api.customer.domain.ContactType
import com.mshatunov.pool.api.customer.repository.CustomerRepository
import com.mshatunov.pool.api.customer.repository.model.Customer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.platform.commons.util.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.web.util.NestedServletException

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue
import static org.junit.jupiter.api.Assertions.assertThrows

class CustomerControllerTest extends BaseIntegrationTest {

    @Autowired
    CustomerRepository repository

    @Autowired
    ObjectMapper mapper

    def classLoader = Thread.currentThread().getContextClassLoader()

    @AfterEach
    void clearMongo() {
        repository.deleteAll()
    }

    @Test
    void 'Successful client saving'() {
        def response = mockMvc.perform(MockMvcRequestBuilders
                .post('/')
                .content(classLoader.getResourceAsStream("json/successPostRequest.json").text)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andReturn()

        assertEquals(HttpStatus.OK.value(), response.getResponse().status)

        def customerId = response.getResponse().contentAsString
        assertTrue(StringUtils.isNotBlank(customerId))

        Customer customer = repository.findById(customerId).get()

        assertEquals("name", customer.name)
        assertEquals("middleName", customer.middleName)
        assertEquals("lastName", customer.lastName)
        assertEquals("phone", customer.contacts.get(ContactType.PHONE))
        assertEquals("email", customer.contacts.get(ContactType.EMAIL))
        assertEquals("parentName", customer.parents.get(0).name)
        assertEquals("parentMiddleName", customer.parents.get(0).middleName)
        assertEquals("parentLastName", customer.parents.get(0).lastName)
        assertEquals("parentPhone", customer.parents.get(0).contacts.get(ContactType.PHONE))
        assertEquals("parentEmail", customer.parents.get(0).contacts.get(ContactType.EMAIL))
    }

    @Test
    void 'Successful all customers retrieval'() {
        saveCustomer('1')
        saveCustomer('2')
        saveCustomer('3')

        def response = mockMvc.perform(MockMvcRequestBuilders
                .get('/')
        ).andReturn()

        assertEquals(HttpStatus.OK.value(), response.getResponse().status)

        List<CustomerResponse> customers = mapper.readValue(response.getResponse().contentAsString,
                new TypeReference<List<CustomerResponse>>() {})
        assertEquals(3, customers.size())
    }

    @Test
    void 'Successful single customer retrieval'() {
        def customerName = 'John'

        saveCustomer(customerName)

        def response = mockMvc.perform(MockMvcRequestBuilders
                .get("/customerId_${customerName}")
        ).andReturn()

        assertEquals(HttpStatus.OK.value(), response.getResponse().status)

        CustomerResponse customer = mapper.readValue(response.getResponse().contentAsString, CustomerResponse.class)
        assertEquals('customerId_' + customerName, customer.id)
        assertEquals('name_' + customerName, customer.name)
    }

    @Test
    void 'Fail on single customer retrieval - customer does not exist'() {
        assertThrows(NestedServletException.class, { ->
            mockMvc.perform(MockMvcRequestBuilders
                    .get("/1"))
        })
    }

    @Test
    void 'Successful update of existing customer'() {
        def customerName = 'John'
        saveCustomer(customerName)

        def customerId = "customerId_${customerName}".toString() //GString is not supported in method args
        def response = mockMvc.perform(MockMvcRequestBuilders
                .put("/" + customerId)
                .content(classLoader.getResourceAsStream("json/successPostRequest.json").text)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        ).andReturn()

        assertEquals(HttpStatus.OK.value(), response.getResponse().status)

        Customer customer = repository.findById(customerId).get()
        assertEquals("name", customer.name)

    }

    private String saveCustomer(String id) {
        return repository.save(Customer.builder()
                .id("customerId_${id}")
                .name("name_${id}")
                .build()).id
    }

}