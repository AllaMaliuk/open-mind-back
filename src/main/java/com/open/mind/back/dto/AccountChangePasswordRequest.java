package com.open.mind.back.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** AccountChangePasswordRequestDto. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountChangePasswordRequest {
  @NotEmpty(message = "Password cannot be empty")
  private String newPassword;
}
