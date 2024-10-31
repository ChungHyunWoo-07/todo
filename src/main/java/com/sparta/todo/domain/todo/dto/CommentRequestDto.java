package com.sparta.todo.domain.todo.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String comment;
    private Long memberId;
}