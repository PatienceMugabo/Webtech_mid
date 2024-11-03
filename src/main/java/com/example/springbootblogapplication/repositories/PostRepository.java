package com.example.springbootblogapplication.repositories;

import com.example.springbootblogapplication.models.Post;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE " +
            "LOWER(p.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(p.body) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Post> findBySearchTerm(String searchTerm, Pageable pageable);

    Page<Post> findAll(Pageable pageable);

    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.account WHERE p.id = :id")
    Optional<Post> findByIdWithAccount(@Param("id") Long id);
}
