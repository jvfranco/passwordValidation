package br.com.validation.application.controllers;

import br.com.validation.application.models.PasswordRequest;
import br.com.validation.application.validation.services.ValidationStepService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PasswordController.class)
@AutoConfigureMockMvc
class PasswordControllerTest {

    static String VALIDATION_API = "/password/validation";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ValidationStepService validationStepService;

    @Test
    @DisplayName("Should be return true if password meet the requirements.")
    void executeSuccessTest() throws Exception {
        String password = "AbTp9!fok";

        String json = new ObjectMapper().writeValueAsString(new PasswordRequest(password));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(VALIDATION_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}