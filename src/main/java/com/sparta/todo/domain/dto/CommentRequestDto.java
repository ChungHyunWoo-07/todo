package com.sparta.todo.domain.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private String comment;
    private String userName;
}