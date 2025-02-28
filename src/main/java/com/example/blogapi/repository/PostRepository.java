package com.example.blogapi.repository;

import com.example.blogapi.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE " +
            "LOWER(p.title) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(p.content) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(p.category) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "EXISTS (SELECT t FROM p.tags t WHERE LOWER(t) LIKE LOWER(CONCAT('%', :term, '%')))")
    List<Post> searchPosts(@Param("term") String term);
}
