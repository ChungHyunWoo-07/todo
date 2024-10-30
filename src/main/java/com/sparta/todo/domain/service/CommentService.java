package com.sparta.todo.domain.service;

import com.sparta.todo.domain.dto.CommentRequestDto;
import com.sparta.todo.domain.dto.CommentResponseDto;
import com.sparta.todo.domain.entity.Comment;
import com.sparta.todo.domain.entity.Todo;
import com.sparta.todo.domain.repository.CommentRepository;
import com.sparta.todo.domain.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentResponseDto createComment(CommentRequestDto requestDto, Long todoId) {
        Todo todo = todoRepository.findTodoById(todoId);
        Comment comment = Comment.from(requestDto, todo);
        Comment savedComment = commentRepository.save(comment);
        return savedComment.to();
    }

    @Transactional
    public void updateComment(CommentRequestDto requestDto, Long todoId, Long commentId) {
        todoRepository.findTodoById(todoId);
        Comment comment = commentRepository.findCommentById(commentId);
        comment.updateData(requestDto);
    }

    @Transactional
    public void deleteComment(Long todoId, Long commentId) {
        todoRepository.findTodoById(todoId);
        commentRepository.findCommentById(commentId);
        commentRepository.deleteById(commentId);
    }
}