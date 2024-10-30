package com.sparta.todo.domain.entity;

import com.sparta.todo.common.entity.BaseTimeStamp;
import com.sparta.todo.domain.dto.TodoRequestDto;
import com.sparta.todo.domain.dto.TodoResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Todo extends BaseTimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;

    @Column
    private String title;

    @Column
    private String description;


    public static Todo from(TodoRequestDto requestDto) {
        Todo todo = new Todo();
        todo.initData(requestDto);
        return todo;
    }

    private void initData(TodoRequestDto requestDto) {
        this.userName = requestDto.getUserName();
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
    }

    public TodoResponseDto to() {
        return new TodoResponseDto(
                id,
                userName,
                title,
                description,
//                comments.stream().map(Comment::to).toList(),
                getCreatedAt(),
                getModifiedAt()
        );
    }

    public void updateData(TodoRequestDto requestDto) {
        this.userName= requestDto.getUserName();
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
    }
}
