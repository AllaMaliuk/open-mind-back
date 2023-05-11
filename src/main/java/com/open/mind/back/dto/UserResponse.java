package com.open.mind.back.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** UserResponseDto. */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
  private String email;
  private String username;
  private LocalDateTime createdOn;
}
