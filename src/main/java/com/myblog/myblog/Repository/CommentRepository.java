package com.myblog.myblog.Repository;

import com.myblog.myblog.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

   List<Comment> findByPostId(long postId);

   @Modifying
   @Query("DELETE FROM Comment c")
   void deleteAllComments();

}

