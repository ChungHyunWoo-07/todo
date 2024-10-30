package com.sparta.todo.domain.controller;

import com.sparta.todo.domain.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
}
