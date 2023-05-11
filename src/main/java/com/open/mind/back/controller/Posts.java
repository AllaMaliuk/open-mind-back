package com.open.mind.back.controller;

import com.open.mind.back.dto.CommentaryRequest;
import com.open.mind.back.dto.CommentaryResponse;
import com.open.mind.back.dto.PostRequest;
import com.open.mind.back.dto.PostResponse;
import com.open.mind.back.dto.UserPostsResponse;
import com.open.mind.back.mappers.Mapper;
import com.open.mind.back.service.PostInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** PostsController. */
@AllArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class Posts {
  private PostInterface postInterface;

  /**
   * getAllPosts.
   *
   * @return UserPostsResponse.
   */
  @GetMapping
  @Operation(security = @SecurityRequirement(name = "Authorization"))
  public ResponseEntity<UserPostsResponse> getAllPosts() {
    return ResponseEntity.ok(
        UserPostsResponse.builder()
            .userPosts(Mapper.I.postModelsToResponseDto(postInterface.getAllPosts()))
            .build());
  }

  @GetMapping("/{postId}")
  @Operation(security = @SecurityRequirement(name = "Authorization"))
  public ResponseEntity<PostResponse> getPostById(@PathVariable(value = "postId") String postId) {
    return ResponseEntity.ok(Mapper.I.postModelToResponseDto(postInterface.getPostById(postId)));
  }

  /**
   * createNewPost.
   *
   * @param requestDto PostRequest.
   * @return PostResponse.
   */
  @PostMapping
  @Operation(security = @SecurityRequirement(name = "Authorization"))
  public ResponseEntity<PostResponse> createNewPost(@Valid @RequestBody PostRequest requestDto) {
    return ResponseEntity.ok(
        Mapper.I.postModelToResponseDto(
            postInterface.createPost(Mapper.I.postRequestDtoToModel(requestDto))));
  }

  /**
   * createNewPostCommentary.
   *
   * @param requestDto CommentaryRequest.
   * @return CommentaryResponse.
   */
  @PostMapping("/commentary")
  @Operation(security = @SecurityRequirement(name = "Authorization"))
  public ResponseEntity<CommentaryResponse> createNewPostCommentary(
      @Valid @RequestBody CommentaryRequest requestDto) {
    return ResponseEntity.ok(
        Mapper.I.commentaryModelToResponseDto(
            postInterface.createPostCommentary(Mapper.I.commentaryRequestDtoToModel(requestDto))));
  }
}
