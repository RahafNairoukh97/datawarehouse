package com.bloomberg.datawarehouse.controller;

import com.bloomberg.datawarehouse.entity.Deal;
import com.bloomberg.datawarehouse.service.DealService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DealController.class)
public class DealControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DealService dealService;

    @Test
    public void givenInvalidTwoDeals_whenCreating_thenShouldThrowReturnBadRequest() throws Exception {
        mvc.perform(post("/deal").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"fromCurrency\":\"USD\",\n" +
                "    \"toCurrency\":\"JOD\",\n" +
                "    \"amount\": 1000.0\n" +
                "}")).andExpect(status().isBadRequest());

        mvc.perform(post("/deal").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"fromCurrency\":\"USD\",\n" +
                "    \"toCurrency\":\"JOD\",\n" +
                "    \"timestamp\":\"IsoCode\",\n" +
                "    \"amount\": 1000.0\n" +
                "}")).andExpect(status().isBadRequest());
    }

    @Test
    public void givenDeal_whenCreating_thenShouldSuccess() throws Exception {
        doNothing().when(dealService).createDeal(any(Deal.class));
        when(dealService.list()).thenReturn(new ArrayList<>());

        mvc.perform(post("/deal").contentType(MediaType.APPLICATION_JSON).content("{\n" +
                "    \"fromCurrency\":\"USD\",\n" +
                "    \"toCurrency\":\"JOD\",\n" +
                "    \"timestamp\":\"2016-11-09T11:44:44.797\",\n" +
                "    \"amount\": 1000.0\n" +
                "}")).andExpect(status().isCreated());

        mvc.perform(get("/deal"));

        verify(dealService, times(1)).createDeal(any(Deal.class));
        verify(dealService, times(1)).list();
    }

}