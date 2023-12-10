package com.myblog.myblog.Service;

import com.myblog.myblog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    public CommentDto createComment(long postId, CommentDto commentDto);

    public void deleteCommentById(long postId,long commentId);

    List<CommentDto> getCommentsByPostId(long post);

    CommentDto updateComment(long commentId, CommentDto commentDto);

   // public void deleteAllComments();
}
