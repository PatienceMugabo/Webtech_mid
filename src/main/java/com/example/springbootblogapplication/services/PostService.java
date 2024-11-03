package com.example.springbootblogapplication.services;

import com.example.springbootblogapplication.models.Post;
import com.example.springbootblogapplication.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Optional<Post> getById(Long id) {
        return postRepository.findById(id);
    }

    public Page<Post> getAll(int page, int size, String sortField, String sortDirection, String searchTerm) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortField);
        Pageable pageable = PageRequest.of(page, size, sort);
        
        if (searchTerm != null && !searchTerm.isEmpty()) {
            return postRepository.findBySearchTerm(searchTerm, pageable);
        } else {
            return postRepository.findAll(pageable);
        }
    }

    public Post save(Post post) {
        if (post.getId() == null) {
            post.setCreatedAt(LocalDateTime.now());
        }
        post.setUpdatedAt(LocalDateTime.now());
        return postRepository.save(post);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    @Transactional(readOnly = true)
    public Optional<Post> getByIdWithAccount(Long id) {
        return postRepository.findByIdWithAccount(id);
    }
}
