package com.sparta.todo.domain.todo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class TodoRequestDto {
    @NotNull
    private Long memberId;
    @Size(min = 2, max = 15, message = "2글자 이상, 15글자 이하만 가능")
    private String title;
    private String description;
}
