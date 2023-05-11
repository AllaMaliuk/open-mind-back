package com.open.mind.back.configuration;

import com.open.mind.back.exceptions.CredentialsException;
import com.open.mind.back.exceptions.TokenException;
import com.open.mind.back.exceptions.UserException;
import com.open.mind.back.model.SecurityUser;
import com.open.mind.back.service.TokenInterface;
import com.open.mind.back.service.UserInterface;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/** Jwt authorization filter. */
@RequiredArgsConstructor
public class TokenFilter extends OncePerRequestFilter {
  private static final String HEADER = "Authorization";
  private static final String PREFIX = "Bearer ";
  private final UserInterface userInterface;
  private final TokenInterface tokenInterface;

  /** Filter which checks if jwt token is presented and authenticates user. */
  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    getAndAuthenticateUserFromJwt(getAndValidateJwtToken(request));
    chain.doFilter(request, response);
  }

  /**
   * Method to extract Jwt from request.
   *
   * @param request HttpServletRequest.
   * @return Jwt token.
   */
  private String getAndValidateJwtToken(HttpServletRequest request) {
    String authenticationHeader = request.getHeader(HEADER);
    if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX)) {
      throw new TokenException(Map.of("token", "Authorization token required"), "Token error");
    }
    String jwt = authenticationHeader.replace(PREFIX, "");
    if (!tokenInterface.isAuthorizationToken(jwt)) {
      throw new TokenException(Map.of("token", "Authorization jwt token required"), "Token error");
    }
    return jwt;
  }

  /**
   * Method to get user info from db using jwt and authenticate him.
   *
   * @param jwt Jwt token.
   */
  private void getAndAuthenticateUserFromJwt(String jwt) {
    try {
      SecurityUser securityUser = userInterface.getUserByEmail(tokenInterface.getEmailFromJwt(jwt));

      if (!securityUser.isEnabled()) {
        throw new UserException(
            Map.of("email", "Please activate your account"), "User not activated");
      }
      UsernamePasswordAuthenticationToken auth =
          new UsernamePasswordAuthenticationToken(securityUser, "", securityUser.getAuthorities());

      SecurityContextHolder.getContext().setAuthentication(auth);
    } catch (CredentialsException e) {
      throw new TokenException(Map.of("token", "Token verification error"), "Jwt error");
    }
  }
}
