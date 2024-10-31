package com.sparta.todo.domain.todo.service;

import com.sparta.todo.domain.todo.dto.TodoRequestDto;
import com.sparta.todo.domain.todo.dto.TodoResponseDto;
import com.sparta.todo.domain.todo.dto.TodoResponsePage;
import com.sparta.todo.domain.todo.entity.Todo;
import com.sparta.todo.domain.todo.repository.TodoRepository;
import com.sparta.todo.domain.user.entity.Member;
import com.sparta.todo.domain.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public TodoResponseDto createTodo(TodoRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Todo not found with id: " + requestDto.getMemberId()));
        Todo todo = todoRepository.save(Todo.from(requestDto, member));
        return todo.to();

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

    @Transactional
    public void assignMember(Long todoId, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Todo not found with id: " + memberId));
        Todo todo = todoRepository.findTodoById(todoId);
        todo.addMember(member);
    }

    //페이징 적용 조회
    public TodoResponsePage getTodoListWithPaging(int page, int size, String criteria) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, criteria));
        Page<Todo> todos = todoRepository.findAll(pageable);
        return new TodoResponsePage(todos);
    }
}