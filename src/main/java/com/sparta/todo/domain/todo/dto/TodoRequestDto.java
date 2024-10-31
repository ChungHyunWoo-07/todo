package com.sparta.todo.domain.todo.dto;

import lombok.Getter;

@Getter
public class TodoRequestDto {
    private Long memberId;
    private String title;
    private String description;
}
