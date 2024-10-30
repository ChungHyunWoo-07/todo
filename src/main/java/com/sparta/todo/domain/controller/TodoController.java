package com.sparta.todo.domain.controller;

import com.sparta.todo.domain.dto.TodoRequestDto;
import com.sparta.todo.domain.dto.TodoResponseDto;
import com.sparta.todo.domain.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;

    // 일정생성
    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(@RequestBody TodoRequestDto requestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(todoService.createTodo(requestDto));
    }

    //전체일정 조회

    //선택일정 조회

    //선택 일정 수정

    //선택 일정 삭제



}
