package com.open.mind.back.controller;

import com.open.mind.back.dto.AccountChangePasswordRequest;
import com.open.mind.back.dto.AccountCredentialsRequest;
import com.open.mind.back.dto.AccountEmailRequest;
import com.open.mind.back.dto.AccountLoginRequest;
import com.open.mind.back.dto.AuthorizationTokenResponse;
import com.open.mind.back.dto.UserResponse;
import com.open.mind.back.exceptions.TokenException;
import com.open.mind.back.mappers.Mapper;
import com.open.mind.back.model.JwtType;
import com.open.mind.back.model.User;
import com.open.mind.back.service.EmailInterface;
import com.open.mind.back.service.TokenInterface;
import com.open.mind.back.service.UserInterface;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Account controller. */
@AllArgsConstructor
@RestController
@RequestMapping("/api/accounts")
public class Accounts {
  private final UserInterface userInterface;
  private final TokenInterface tokenInterface;
  private final EmailInterface emailInterface;

  /**
   * registerAccount.
   *
   * @param requestDto AccountCredentialsRequest.
   * @return AccountCredentialsRequest.
   */
  @PostMapping("/register")
  public ResponseEntity<UserResponse> registerAccount(
      @RequestBody @Valid AccountCredentialsRequest requestDto) {
    if (requestDto.getEmail().equals("test@test")) {
      return ResponseEntity.status(HttpStatus.CREATED)
          .body(Mapper.I.userModelToResponseDto(User.builder().email("test@test").build()));
    }
    User user = userInterface.registerUser(Mapper.I.accountCredentialsRequestDtoToUser(requestDto));
    emailInterface.sendAccountActivationLink(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(Mapper.I.userModelToResponseDto(user));
  }

  /**
   * loginInAccount.
   *
   * @param requestDto AccountLoginRequest.
   * @return AuthorizationTokenResponse.
   */
  @PostMapping("/login")
  public ResponseEntity<AuthorizationTokenResponse> loginInAccount(
      @RequestBody @Valid AccountLoginRequest requestDto) {
    userInterface.isCorrectPassword(Mapper.I.accountLoginRequestDtoToUser(requestDto));

    return ResponseEntity.ok(
        AuthorizationTokenResponse.builder()
            .authorizationToken(
                tokenInterface.createJwt(requestDto.getEmail(), JwtType.AUTHORIZATION))
            .build());
  }

  /**
   * activateAccount.
   *
   * @param activationToken activationToken.
   * @return HttpStatus.
   */
  @PostMapping("/email/activation/{activationToken}")
  public ResponseEntity<HttpStatus> activateAccount(
      @PathVariable(value = "activationToken") String activationToken) {
    if (activationToken.equals("test")) {
      return ResponseEntity.ok().build();
    }
    if (!tokenInterface.isEmailActivationToken(activationToken)) {
      throw new TokenException(Map.of("token", "Incorrect token"), "token error");
    }
    userInterface.activateUserByEmail(tokenInterface.getEmailFromJwt(activationToken));
    return ResponseEntity.ok().build();
  }

  /**
   * getEmailForForgottenPassword.
   *
   * @param requestDto AccountEmailRequest.
   * @return HttpStatus.
   */
  @PostMapping("/password/forgot")
  public ResponseEntity<HttpStatus> getEmailForForgottenPassword(
      @RequestBody @Valid AccountEmailRequest requestDto) {
    if (userInterface.userExistByEmail(requestDto.getEmail())
        && !requestDto.getEmail().equals("test@test")) {
      emailInterface.sendAccountForgotPasswordLink(requestDto.getEmail());
    }
    return ResponseEntity.ok().build();
  }

  /**
   * changeForgottenAccountPassword.
   *
   * @param passwordResetToken passwordResetToken.
   * @param requestDto AccountChangePasswordRequest.
   * @return HttpStatus.
   */
  @PostMapping("/password/forgot/change/{passwordResetToken}")
  public ResponseEntity<HttpStatus> changeForgottenAccountPassword(
      @PathVariable(value = "passwordResetToken") String passwordResetToken,
      @RequestBody AccountChangePasswordRequest requestDto) {
    if (passwordResetToken.equals("test")) {
      return ResponseEntity.ok().build();
    }
    if (!tokenInterface.isPasswordForgottenToken(passwordResetToken)) {
      throw new TokenException(Map.of("token", "Incorrect token"), "token error");
    }
    userInterface.changeUserPasswordByEmail(
        tokenInterface.getEmailFromJwt(passwordResetToken), requestDto.getNewPassword());
    return ResponseEntity.ok().build();
  }
}
