package com.example.blogapi.service;

import com.example.blogapi.dto.PostRequest;
import com.example.blogapi.exception.NotFoundException;
import com.example.blogapi.model.Post;
import com.example.blogapi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post createPost(PostRequest request) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCategory(request.getCategory());
        post.setTags(request.getTags());
        return postRepository.save(post);
    }

    public List<Post> getAllPosts(String term) {
        if (term != null && !term.isBlank()) {
            return postRepository.searchPosts(term.toLowerCase());
        }
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post not found"));
    }

    public Post updatePost(Long id, PostRequest request) {
        Post post = getPostById(id);
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCategory(request.getCategory());
        post.setTags(request.getTags());
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        Post post = getPostById(id);
        postRepository.delete(post);
    }
}