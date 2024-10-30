package com.sparta.todo.domain.entity;

import com.sparta.todo.common.entity.BaseTimeStamp;
import com.sparta.todo.domain.dto.CommentRequestDto;
import com.sparta.todo.domain.dto.CommentResponseDto;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Comment extends BaseTimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String comment;

    @Column
    private String userName;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    private Todo todo;

    public static Comment from(CommentRequestDto requestDto, Todo todo) {
        Comment comment = new Comment();
        comment.initData(requestDto, todo);
        return comment;
    }

    private void initData(CommentRequestDto requestDto, Todo todo) {
        this.comment= requestDto.getComment();
        this.userName= requestDto.getUserName();
        this.todo = todo;
    }

    public CommentResponseDto to() {
        return new CommentResponseDto(
                this.id,
                this.comment,
                this.userName
        );
    }

    public void updateData(CommentRequestDto requestDto) {
        this.comment = requestDto.getComment();
        this.userName = requestDto.getUserName();
    }
}