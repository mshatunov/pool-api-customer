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

    @Test
    void 'Successful all clients retrieval'() {
        saveClient('1')
        saveClient('2')
        saveClient('3')

        def response = mockMvc.perform(MockMvcRequestBuilders
                .get('/')
        ).andReturn()

        assertTrue(response.getResponse().status == HttpStatus.OK.value())

        List<CustomerResponse> clients = mapper.readValue(response.getResponse().contentAsString,
                new TypeReference<List<CustomerResponse>>() {})
        assertTrue(clients.size() == 3)
    }

    @Test
    void 'Successful single client retrieval'() {
        def clientName = 'John'

        saveClient(clientName)

        def response = mockMvc.perform(MockMvcRequestBuilders
                .get("/customerId_${clientName}")
        ).andReturn()

        assertEquals(response.getResponse().status, HttpStatus.OK.value())

        CustomerResponse client = mapper.readValue(response.getResponse().contentAsString, CustomerResponse.class)
        assertEquals(client.id, 'customerId_' + clientName)
        assertEquals(client.name, 'name_' + clientName)
    }

    private String saveClient(String name) {
        return repository.save(Customer.builder()
                .id("customerId_${name}")
                .name("name_${name}")
                .build()).id
    }

}