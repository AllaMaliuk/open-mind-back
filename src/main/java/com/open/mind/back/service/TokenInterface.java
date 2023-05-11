package com.open.mind.back.service;

import com.open.mind.back.model.JwtType;

/** Interface for Jwt operations. */
public interface TokenInterface {

  /**
   * Create Jwt.
   *
   * @param email User email.
   * @return Jwt token.
   */
  String createJwt(String email, JwtType type);

  /**
   * Get email from Jwt.
   *
   * @param jwt Jwt token.
   * @return User email extracted from jwt.
   */
  String getEmailFromJwt(String jwt);

  /**
   * Check if token is authorization token.
   *
   * @param jwt Jwt token.
   * @return true or false
   */
  Boolean isAuthorizationToken(String jwt);

  /**
   * Check if token is email activation token.
   *
   * @param jwt Jwt token.
   * @return true or false
   */
  Boolean isEmailActivationToken(String jwt);

  /**
   * Check if token is password forgotten token.
   *
   * @param jwt Jwt token.
   * @return true or false
   */
  Boolean isPasswordForgottenToken(String jwt);
}
