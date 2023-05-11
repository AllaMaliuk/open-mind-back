package com.open.mind.back.repository;

import com.open.mind.back.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PostRepository.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, String> {
  List<Post> findAllByCreatorId(String creatorId);
}
