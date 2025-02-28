package com.example.blogapi.controller;

import com.example.blogapi.dto.PostRequest;
import com.example.blogapi.model.Post;
import com.example.blogapi.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@Valid @RequestBody PostRequest request) {
        Post post = postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(
            @RequestParam(required = false) String term
    ) {
        return ResponseEntity.ok(postService.getAllPosts(term));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(
            @PathVariable Long id,
            @Valid @RequestBody PostRequest request
    ) {
        return ResponseEntity.ok(postService.updatePost(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
