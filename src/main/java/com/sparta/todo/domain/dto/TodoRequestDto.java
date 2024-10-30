package com.sparta.todo.domain.dto;

import lombok.Getter;

@Getter
public class TodoRequestDto {
    private String userName;
    private String title;
    private String description;
}
