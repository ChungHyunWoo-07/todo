package com.sparta.todo.domain.todo.service;

import com.sparta.todo.domain.todo.dto.CommentRequestDto;
import com.sparta.todo.domain.todo.dto.CommentResponseDto;
import com.sparta.todo.domain.todo.entity.Comment;
import com.sparta.todo.domain.todo.entity.Todo;
import com.sparta.todo.domain.todo.repository.CommentRepository;
import com.sparta.todo.domain.todo.repository.TodoRepository;
import com.sparta.todo.domain.user.entity.Member;
import com.sparta.todo.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public CommentResponseDto createComment(CommentRequestDto requestDto, Long todoId) {
        Todo todo = todoRepository.findTodoById(todoId);
        Member member = memberRepository.findById(requestDto.getMemberId()).orElseThrow(() -> new IllegalArgumentException("Todo not found with id: " + requestDto.getMemberId()));
        Comment comment = Comment.from(requestDto, todo, member);
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