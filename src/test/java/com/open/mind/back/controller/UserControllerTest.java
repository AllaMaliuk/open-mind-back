package com.open.mind.back.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.open.mind.back.dto.AccountChangePasswordRequest;
import com.open.mind.back.dto.AuthorizationTokenResponse;
import com.open.mind.back.dto.UserDataRequest;
import com.open.mind.back.dto.UserResponse;
import com.open.mind.back.model.JwtType;
import com.open.mind.back.service.TokenInterface;
import com.open.mind.back.service.UserInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = User.class)
public class UserControllerTest {
  private final ObjectMapper objectMapper = new ObjectMapper();
  @MockBean private UserInterface userInterface;
  @MockBean private TokenInterface tokenInterface;
  @Autowired private MockMvc mockMvc;

  @Test
  void getUserTest() throws Exception {
    Integer id = 1;
    com.open.mind.back.model.User mockUserModel =
        com.open.mind.back.model.User.builder().id(id).build();
    when(userInterface.getAuthenticatedUser()).thenReturn(mockUserModel);
    MvcResult result = mockMvc.perform(get("/api/user")).andExpect(status().isOk()).andReturn();
    UserResponse resultDto =
        objectMapper.readValue(result.getResponse().getContentAsString(), UserResponse.class);
    assertEquals(resultDto, new UserResponse());
  }

  @Test
  void changeUserDataTest() throws Exception {
    String jwt = "jwt";
    when(userInterface.updateUser(any())).thenReturn(new com.open.mind.back.model.User());
    when(tokenInterface.createJwt("test@test", JwtType.AUTHORIZATION)).thenReturn(jwt);
    UserDataRequest request = UserDataRequest.builder().email("test@test").build();
    MvcResult result =
        mockMvc
            .perform(
                patch("/api/user/data")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andReturn();
    AuthorizationTokenResponse resultDto =
        objectMapper.readValue(
            result.getResponse().getContentAsString(), AuthorizationTokenResponse.class);
    AuthorizationTokenResponse expectedResult =
        AuthorizationTokenResponse.builder().authorizationToken(jwt).build();
    assertEquals(resultDto, expectedResult);
  }

  @Test
  void changeUserPasswordTest() throws Exception {
    Integer id = 1;
    String newPass = "new";
    String email = "test@test";
    com.open.mind.back.model.User mockUserModel =
        com.open.mind.back.model.User.builder().id(id).email(email).build();
    when(userInterface.getAuthenticatedUser()).thenReturn(mockUserModel);
    doNothing().when(userInterface).changeUserPasswordByEmail(email, newPass);
    AccountChangePasswordRequest requestDto =
        AccountChangePasswordRequest.builder().newPassword(newPass).build();
    MvcResult result =
        mockMvc
            .perform(
                patch("/api/user/password")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(requestDto)))
            .andExpect(status().isOk())
            .andReturn();
  }
}
