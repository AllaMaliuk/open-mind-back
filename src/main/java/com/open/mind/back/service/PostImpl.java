package com.open.mind.back.service;

import com.open.mind.back.mappers.Mapper;
import com.open.mind.back.model.Commentary;
import com.open.mind.back.model.Post;
import com.open.mind.back.repository.CommentaryRepository;
import com.open.mind.back.repository.PostRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/** PostServiceImpl. */
@Service
@AllArgsConstructor
public class PostImpl implements PostInterface {
  private PostRepository postRepository;
  private CommentaryRepository commentaryRepository;
  private UserInterface userInterface;

  @Override
  public Post getPostById(String postId) {
    return Mapper.I.postEntityToModel(
        postRepository.findById(postId).orElse(new com.open.mind.back.entity.Post()));
  }

  @Override
  public List<Post> getAllPosts() {
    return Mapper.I.postEntitiesToModels(postRepository.findAll());
  }

  @Override
  public Post createPost(Post postModel) {
    com.open.mind.back.entity.Post post =
        com.open.mind.back.entity.Post.builder()
            .creatorId(userInterface.getAuthenticatedUser().getId())
            .theme(postModel.getTheme())
            .content(postModel.getContent())
            .createdOn(LocalDateTime.now())
            .build();
    return Mapper.I.postEntityToModel(postRepository.save(post));
  }

  @Override
  public Commentary createPostCommentary(Commentary commentaryModel) {
    com.open.mind.back.entity.Commentary commentary =
        Mapper.I.commentaryModelToEntity(commentaryModel);
    commentary.setCreatorId(userInterface.getAuthenticatedUser().getId());
    commentary.setCreatedOn(LocalDateTime.now());
    return Mapper.I.commentaryEntityToModel(
        commentaryRepository.save(commentary));
  }
}
