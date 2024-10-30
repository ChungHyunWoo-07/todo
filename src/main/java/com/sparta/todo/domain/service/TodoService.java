package com.sparta.todo.domain.service;

import com.sparta.todo.domain.dto.TodoRequestDto;
import com.sparta.todo.domain.dto.TodoResponseDto;
import com.sparta.todo.domain.entity.Todo;
import com.sparta.todo.domain.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    // 전체 목록 조회
    public List<TodoResponseDto> getTodoList() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream()
                .map(Todo::to)
                .collect(Collectors.toList());
    }

    public TodoResponseDto getTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new IllegalArgumentException("Todo not found with id: " + todoId));
        return todo.to();
    }

    @Transactional //엔티티의 변경이 발생하면 자동으로 업데이트 쿼리가 발생 -> 따로 저장하는 로직 필요없음
    public void updateTodo(Long todoId, TodoRequestDto requestDto) {
        Todo todo = todoRepository.findTodoById(todoId);
        todo.updateData(requestDto);
    }

    @Transactional
    public void deleteTodo(Long todoId) {
        todoRepository.findTodoById(todoId);
        todoRepository.deleteById(todoId);
    }
}
