package com.example.demo.repository;

import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;



@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    //SELECT * FROM as p WHERE USER='user" SORT DESC
    List<Post> findAllByUserOrderByCreateDateDesc(User user);

    List<Post> findAllByOrderByCreateDateDesc();

    Optional<Post> findPostByIdAndUser(Long id, User user) ;
}
