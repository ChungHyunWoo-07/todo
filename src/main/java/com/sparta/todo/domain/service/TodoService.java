package com.sparta.todo.domain.service;

import com.sparta.todo.domain.dto.TodoRequestDto;
import com.sparta.todo.domain.dto.TodoResponseDto;
import com.sparta.todo.domain.entity.Todo;
import com.sparta.todo.domain.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public TodoResponseDto createTodo(TodoRequestDto requestDto) {
        //일정 생성
        //DTO -> ENTITY
        Todo todo = Todo.from(requestDto);

        //일정 DB에 저장
        Todo savedTodo = todoRepository.save(todo);

        return savedTodo.to();

    }
}
