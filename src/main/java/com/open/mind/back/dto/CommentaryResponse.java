package com.open.mind.back.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CommentaryResponseDto.
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CommentaryResponse {
  private String id;
  private String commentary;
  private UserResponse creator;
  private String creatorId;
  private LocalDateTime createdOn;
}
