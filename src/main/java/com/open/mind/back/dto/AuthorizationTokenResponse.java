package com.open.mind.back.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** AuthorizationTokenResponseDto. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationTokenResponse {
  private String authorizationToken;
}
