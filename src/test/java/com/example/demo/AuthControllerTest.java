package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    @Test
    public void testRegisterNewUser() throws Exception {
        Map<String, String> request = Map.of(
                "email", "test@example.com",
                "password", "password123"
        );

        when(userRepository.existsByEmail("test@example.com")).thenReturn(false);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPass");

mockMvc.perform(post("/api/auth/register")...

                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void sampleTest() {
        System.out.println("Running sample test...");
        assert(2 == 1 + 1);
    }

@Test
public void testLoginSuccess() throws Exception {
    Map<String, String> request = Map.of(
        "email", "login@example.com",
        "password", "password123"
    );

    User mockUser = new User();
    mockUser.setEmail("login@example.com");
    mockUser.setPassword("encodedPass");

    when(userRepository.findByEmail("login@example.com"))
        .thenReturn(Optional.of(mockUser));
    when(passwordEncoder.matches("password123", "encodedPass"))
        .thenReturn(true);

    mockMvc.perform(post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(request)))
        .andExpect(status().isOk());
}































}

