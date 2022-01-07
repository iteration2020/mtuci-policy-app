package ru.mtuci.policy.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StatusControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldShowStatus() throws Exception {
        this.mockMvc
                .perform(get("/api/v1/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.helloWord").exists())
                .andExpect(jsonPath("$.helloWord", is("Привет!")));
    }
}
