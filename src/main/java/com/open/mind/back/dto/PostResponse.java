package com.open.mind.back.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** PostResponseDto. */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class PostResponse {
  private String id;
  private UserResponse creator;
  private List<CommentaryResponse> commentaries;
  private String theme;
  private String content;
  private LocalDateTime createdOn;
}
