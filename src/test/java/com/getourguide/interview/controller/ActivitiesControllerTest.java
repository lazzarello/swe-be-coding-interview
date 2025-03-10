package com.getourguide.interview.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import com.getourguide.interview.dto.ActivityDto;
import com.getourguide.interview.service.ActivityService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ActivitiesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActivityService activityService;

    @Test
    void testGetActivities() throws Exception {
        ActivityDto testActivity = ActivityDto.builder()
            .id(1L)
            .title("Test Activity")
            .price(100)
            .currency("$")
            .rating(4.5)
            .specialOffer(false)
            .supplierName("Test Supplier")
            .build();

        when(activityService.getActivities()).thenReturn(List.of(testActivity));

        mockMvc.perform(get("/activities"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[0].title", is("Test Activity")))
            .andExpect(jsonPath("$[0].price", is(100)))
            .andExpect(jsonPath("$[0].currency", is("$")))
            .andExpect(jsonPath("$[0].rating", is(4.5)))
            .andExpect(jsonPath("$[0].specialOffer", is(false)))
            .andExpect(jsonPath("$[0].supplierName", is("Test Supplier")));
    }

    @Test
    void testGetActivityById() throws Exception {
        ActivityDto testActivity = ActivityDto.builder()
            .id(1L)
            .title("Test Activity")
            .price(100)
            .currency("$")
            .rating(4.5)
            .specialOffer(false)
            .supplierName("Test Supplier")
            .build();

        when(activityService.getActivities(1L)).thenReturn(testActivity);

        mockMvc.perform(get("/activities/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(1)))
            .andExpect(jsonPath("$.title", is("Test Activity")))
            .andExpect(jsonPath("$.price", is(100)))
            .andExpect(jsonPath("$.currency", is("$")))
            .andExpect(jsonPath("$.rating", is(4.5)))
            .andExpect(jsonPath("$.specialOffer", is(false)))
            .andExpect(jsonPath("$.supplierName", is("Test Supplier")));
    }

    @Test
    void testSearchActivities() throws Exception {
        ActivityDto testActivity = ActivityDto.builder()
            .id(1L)
            .title("Test Activity")
            .price(100)
            .currency("$")
            .rating(4.5)
            .specialOffer(false)
            .supplierName("Test Supplier")
            .build();

        when(activityService.searchActivities("Test")).thenReturn(List.of(testActivity));

        mockMvc.perform(get("/activities/search/Test"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[0].title", is("Test Activity")))
            .andExpect(jsonPath("$[0].price", is(100)))
            .andExpect(jsonPath("$[0].currency", is("$")))
            .andExpect(jsonPath("$[0].rating", is(4.5)))
            .andExpect(jsonPath("$[0].specialOffer", is(false)))
            .andExpect(jsonPath("$[0].supplierName", is("Test Supplier")));
    }
}
