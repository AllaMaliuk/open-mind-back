package com.open.mind.back.controller;

import com.open.mind.back.dto.AccountChangePasswordRequest;
import com.open.mind.back.dto.AuthorizationTokenResponse;
import com.open.mind.back.dto.UserDataRequest;
import com.open.mind.back.dto.UserResponse;
import com.open.mind.back.mappers.Mapper;
import com.open.mind.back.model.JwtType;
import com.open.mind.back.service.TokenInterface;
import com.open.mind.back.service.UserInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** UserController. */
@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class User {
  private UserInterface userInterface;
  private TokenInterface tokenInterface;

  /**
   * getUser.
   *
   * @return UserResponse.
   */
  @GetMapping
  @Operation(security = @SecurityRequirement(name = "Authorization"))
  public ResponseEntity<UserResponse> getUser() {
    return ResponseEntity.ok(Mapper.I.userModelToResponseDto(userInterface.getAuthenticatedUser()));
  }

  /**
   * changeUserData.
   *
   * @param requestDto UserDataRequest.
   * @return AuthorizationTokenResponse.
   */
  @PatchMapping("/data")
  @Operation(security = @SecurityRequirement(name = "Authorization"))
  public ResponseEntity<AuthorizationTokenResponse> changeUserData(
      @Valid @RequestBody UserDataRequest requestDto) {
    userInterface.updateUser(Mapper.I.userDataRequestDtoToUserModel(requestDto));
    return ResponseEntity.ok(
        AuthorizationTokenResponse.builder()
            .authorizationToken(
                tokenInterface.createJwt(
                    (requestDto.getEmail() == null)
                        ? userInterface.getAuthenticatedUser().getEmail()
                        : requestDto.getEmail(),
                    JwtType.AUTHORIZATION))
            .build());
  }

  /**
   * changeUserPassword.
   *
   * @param requestDto AccountChangePasswordRequest.
   * @return HttpStatus.
   */
  @PatchMapping("/password")
  @Operation(security = @SecurityRequirement(name = "Authorization"))
  public ResponseEntity<HttpStatus> changeUserPassword(
      @Valid @RequestBody AccountChangePasswordRequest requestDto) {
    userInterface.changeUserPasswordByEmail(
        userInterface.getAuthenticatedUser().getEmail(), requestDto.getNewPassword());
    return ResponseEntity.ok().build();
  }
}
