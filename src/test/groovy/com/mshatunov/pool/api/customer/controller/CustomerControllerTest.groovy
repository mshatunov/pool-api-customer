package com.mshatunov.pool.api.customer.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.mshatunov.pool.api.customer.BaseIntegrationTest
import com.mshatunov.pool.api.customer.controller.dto.CustomerResponse
import com.mshatunov.pool.api.customer.domain.ContactType
import com.mshatunov.pool.api.customer.repository.CustomerRepository
import com.mshatunov.pool.api.customer.repository.model.Customer
import org.junit.Before
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

    @Autowired
    ObjectMapper mapper

    def classLoader = Thread.currentThread().getContextClassLoader()

    @Before
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
    void 'Successful all clients retrieval'() {
        saveClient('1')
        saveClient('2')
        saveClient('3')

        def response = mockMvc.perform(MockMvcRequestBuilders
                .get('/')
        ).andReturn()

        assertEquals(HttpStatus.OK.value(), response.getResponse().status)

        List<CustomerResponse> clients = mapper.readValue(response.getResponse().contentAsString,
                new TypeReference<List<CustomerResponse>>() {})
        assertEquals(3, clients.size())
    }

    @Test
    void 'Successful single client retrieval'() {
        def clientName = 'John'

        saveClient(clientName)

        def response = mockMvc.perform(MockMvcRequestBuilders
                .get("/customerId_${clientName}")
        ).andReturn()

        assertEquals(HttpStatus.OK.value(), response.getResponse().status)

        CustomerResponse client = mapper.readValue(response.getResponse().contentAsString, CustomerResponse.class)
        assertEquals('customerId_' + clientName, client.id)
        assertEquals('name_' + clientName, client.name)
    }

    private String saveClient(String name) {
        return repository.save(Customer.builder()
                .id("customerId_${name}")
                .name("name_${name}")
                .build()).id
    }

}