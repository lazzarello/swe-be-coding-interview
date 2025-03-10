package com.getourguide.interview.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SuppliersControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetSuppliers() throws Exception {
        mockMvc.perform(get("/suppliers"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(greaterThan(0))))
            .andExpect(jsonPath("$[0].name", notNullValue()))
            .andExpect(jsonPath("$[0].address", notNullValue()));
    }

    @Test
    void testSearchSuppliers() throws Exception {
        // Test with existing supplier name "John Doe"
        mockMvc.perform(get("/suppliers/search/John Doe"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].name", is("John Doe")));

        // Test with non-existing supplier name
        mockMvc.perform(get("/suppliers/search/NonExistentSupplier"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(0)));
    }
}