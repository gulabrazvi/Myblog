package com.myblog.myblog.Service.Impl;

import com.myblog.myblog.Entity.Comment;
import com.myblog.myblog.Entity.Post;
import com.myblog.myblog.Exception.ResourceNotFound;
import com.myblog.myblog.Repository.CommentRepository;
import com.myblog.myblog.Repository.PostRepository;
import com.myblog.myblog.Service.CommentService;
import com.myblog.myblog.payload.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
   private CommentRepository commentRepo;

   private PostRepository postRepo;

    public CommentServiceImpl(CommentRepository commentRepo, PostRepository postRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post Not Found with id:" + postId)
        );

        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        Comment c = commentRepo.save(comment);

        return mapToDto(c);


    }

    @Override
    public void deleteCommentById(long postId,long commentId) {
        Post post = postRepo.findById(postId).orElseThrow(
                ()-> new ResourceNotFound("Post Not Found With id:"+ postId)
        );
        commentRepo.deleteById(commentId);
    }


    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> comments = commentRepo.findByPostId(postId);
        List<CommentDto> dtos = comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public CommentDto updateComment(long commentId, CommentDto commentDto) {
        Comment com = commentRepo.findById(commentId).get();
        Post post = postRepo.findById(com.getPost().getId()).get();
        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        comment.setId(commentId);

        Comment savedComment = commentRepo.save(comment);
        CommentDto dto = mapToDto(savedComment);
        return dto;
    }

//    @Override
//    public void deleteAllComments() {
//        commentRepo.deleteAllComments();
//    }

    Comment mapToEntity(CommentDto dto){
        Comment comment = new Comment();
        comment.setName(dto.getName());
        comment.setEmail(dto.getEmail());
        comment.setBody(dto.getBody());

        return comment;
    }

    CommentDto mapToDto(Comment comment){
        CommentDto dto = new CommentDto();
        dto.setName(comment.getName());
        dto.setEmail(comment.getEmail());
        dto.setBody(comment.getBody());

        return dto;
    }


}
