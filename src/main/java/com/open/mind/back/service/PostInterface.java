package com.open.mind.back.service;

import com.open.mind.back.model.Commentary;
import com.open.mind.back.model.Post;
import java.util.List;

/** PostService. */
public interface PostInterface {
  Post getPostById(String postId);

  List<Post> getAllPosts();

  Post createPost(Post post);

  Commentary createPostCommentary(Commentary commentary);
}
