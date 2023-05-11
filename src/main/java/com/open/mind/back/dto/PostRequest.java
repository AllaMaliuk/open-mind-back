package com.open.mind.back.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** PostRequestDto. */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostRequest {
  @NotEmpty(message = "Theme cannot be empty")
  private String theme;

  @NotEmpty(message = "Content cannot be empty")
  private String content;
}
