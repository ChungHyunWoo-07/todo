package com.sparta.todo.domain.todo.entity;

import com.sparta.todo.common.entity.BaseTimeStamp;
import com.sparta.todo.domain.todo.dto.TodoRequestDto;
import com.sparta.todo.domain.todo.dto.TodoResponseDto;
import com.sparta.todo.domain.user.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Todo extends BaseTimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member creator; // 일정을 생성하는 작성자

    @Column
    private String title;

    @Column
    private String description;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.REMOVE) //cascade = CascadeType.REMOVE 일정이 삭제되면 댓글도 삭제
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany  // todo를 주인으로 봄
    @JoinTable(name = "schedule",
            joinColumns = @JoinColumn(name = "todo_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")) // 반대 위치인 User Entity 에서 중간 테이블로 조인할 컬럼 설정
    private List<Member> memberList = new ArrayList<>();

    public static Todo from(TodoRequestDto requestDto, Member member) {
        Todo todo = new Todo();
        todo.initData(requestDto, member);
        return todo;
    }

    private void initData(TodoRequestDto requestDto, Member member) {
        this.creator = member;
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
    }

    public TodoResponseDto to() {
        return new TodoResponseDto(
                id,
                this.creator.getMemberName(),
                title,
                description,
                comments.stream().map(Comment::to).toList(),
                getCreatedAt(),
                getModifiedAt()
        );
    }

    public void updateData(TodoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
    }

    public void addMember(Member member){
        this.memberList.add(member);
        member.getTodoList().add(this);
    }
}
