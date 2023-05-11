package com.open.mind.back.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.open.mind.back.entity.Commentary;
import com.open.mind.back.model.Post;
import com.open.mind.back.model.User;
import com.open.mind.back.repository.CommentaryRepository;
import com.open.mind.back.repository.PostRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {
  private PostInterface postService;
  @Mock private PostRepository postRepository;
  @Mock private CommentaryRepository commentaryRepository;
  @Mock private UserInterface userService;

  @BeforeEach
  public void init() {
    this.postService = new PostImpl(postRepository, commentaryRepository, userService);
  }
  @Test
  void alwaysFail(){
    fail();
  }

  @Test
  void testGetPostByIdReturnEmpty() {
    String mockPostId = "test";
    when(postRepository.findById(mockPostId)).thenReturn(Optional.empty());
    Post excpectedResult = new Post();
    Post actualResult = postService.getPostById(mockPostId);
    assertEquals(actualResult, excpectedResult);
  }

  @Test
  void testGetPostByIdReturnNonEmpty() {
    String mockContent = "MockContent";
    Integer id = 2;
    com.open.mind.back.entity.Post mockPostEntity =
        com.open.mind.back.entity.Post.builder().content(mockContent).id(id).build();
    when(postRepository.findById(id.toString())).thenReturn(Optional.of(mockPostEntity));
    Post excpectedResult = Post.builder().id(id.toString()).content(mockContent).build();
    Post actualResult = postService.getPostById(id.toString());
    assertEquals(actualResult, excpectedResult);
  }

  @Test
  void testGetAllPosts() {
    when(postRepository.findAll()).thenReturn(List.of());
    List<Post> actualResult = postService.getAllPosts();
    assertEquals(actualResult, List.of());
  }

  @Test
  void testCreatePost() {
    String mockContent = "MockContent";
    com.open.mind.back.entity.Post mockEntity =
        com.open.mind.back.entity.Post.builder().content(mockContent).build();
    Integer id = 2;
    User mockUserModel = User.builder().id(id).build();
    when(postRepository.save(any())).thenReturn(mockEntity);
    when(userService.getAuthenticatedUser()).thenReturn(mockUserModel);
    Post mockPost = Post.builder().content(mockContent).build();
    Post expectedResult = Post.builder().content(mockContent).build();
    Post actualResult = postService.createPost(mockPost);
    assertEquals(actualResult, expectedResult);
  }

  @Test
  void testCreatePostCommentary() {
    String mockCommentary = "mockCommentary";
    Integer id = 2;
    Commentary mockCommentaryEntity =
        Commentary.builder().commentary(mockCommentary).creatorId(id).build();
    com.open.mind.back.model.Commentary mockCommentaryModel =
        com.open.mind.back.model.Commentary.builder()
            .commentary(mockCommentary)
            .creatorId(id)
            .build();
    User mockUserModel = User.builder().id(id).build();
    when(userService.getAuthenticatedUser()).thenReturn(mockUserModel);
    when(commentaryRepository.save(any())).thenReturn(mockCommentaryEntity);
    com.open.mind.back.model.Commentary expectedResult =
        com.open.mind.back.model.Commentary.builder()
            .commentary(mockCommentary)
            .creatorId(id)
            .build();
    com.open.mind.back.model.Commentary actualResult =
        postService.createPostCommentary(mockCommentaryModel);
    assertEquals(actualResult, expectedResult);
  }
}
