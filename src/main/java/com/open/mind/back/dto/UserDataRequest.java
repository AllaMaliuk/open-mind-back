package com.open.mind.back.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserDataRequestDto.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDataRequest {
  @Email(
      message = "Email is not valid",
      regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
  private String email;

  private String username;
}
