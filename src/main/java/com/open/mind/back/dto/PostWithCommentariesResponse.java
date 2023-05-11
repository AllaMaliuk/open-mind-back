package com.open.mind.back.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PostWithCommentariesResponseDto.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostWithCommentariesResponse {
  private String id;
  private UserResponse creator;
  private String theme;
  private String content;
  private LocalDateTime createdOn;
}
