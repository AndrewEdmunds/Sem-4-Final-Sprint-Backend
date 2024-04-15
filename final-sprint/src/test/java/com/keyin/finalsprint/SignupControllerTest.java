package com.keyin.finalsprint;

import com.keyin.finalsprint.Controller.SignupController;
import com.keyin.finalsprint.Service.UserService;
import com.keyin.finalsprint.User.SignupRequest;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;

import org.mockito.InjectMocks;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;




@WebMvcTest(SignupController.class)
class SignupControllerTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

        public static String asJsonString(final Object obj) {
            try {
                return objectMapper.writeValueAsString(obj);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @InjectMocks
    private SignupController signupController;

    @Test
    void testSignupUsernameExists() throws Exception {
        SignupRequest signupRequest = new SignupRequest("existingUsername", "email@example.com", "password");

        when(userService.isUsernameUnique(signupRequest.getUsername())).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"existingUsername\",\"email\":\"email@example.com\",\"password\":\"password\"}"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
                }

    @Test
    void testSignupEmailExists() throws Exception {
        SignupRequest signupRequest = new SignupRequest("username", "existingemail@example.com", "password");

        when(userService.isUsernameUnique(signupRequest.getUsername())).thenReturn(true);
        when(userService.isEmailUnique(signupRequest.getEmail())).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"username\",\"email\":\"existingemail@example.com\",\"password\":\"password\"}"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
                }

}