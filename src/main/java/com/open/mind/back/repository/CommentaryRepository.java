package com.open.mind.back.repository;

import com.open.mind.back.entity.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** CommentaryRepository. */
@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, String> {}
