package com.open.mind.back.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** CommentaryModel. */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Commentary {
  private String id;
  private String commentary;
  private User creator;
  private Integer postId;
  private Integer creatorId;
  private LocalDateTime createdOn;
}
