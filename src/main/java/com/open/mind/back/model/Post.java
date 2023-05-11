package com.open.mind.back.model;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** PostModel. */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Post {
  private String id;
  private User creator;
  private String theme;
  private String content;
  private List<Commentary> commentaries;
  private LocalDateTime createdOn;
}
