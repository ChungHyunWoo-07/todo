package com.sparta.todo.domain.user.controller;

import com.sparta.todo.domain.user.dto.MemberRequestDto;
import com.sparta.todo.domain.user.dto.MemberResponseDto;
import com.sparta.todo.domain.user.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable Long memberId){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberService.getMember(memberId));
    }

    @PostMapping
    public ResponseEntity<MemberResponseDto> createMember(@RequestBody MemberRequestDto memberRequestDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberService.createMember(memberRequestDto));
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<Void> updateMember(@RequestBody MemberRequestDto memberRequestDto, @PathVariable Long memberId){
        memberService.updateMember(memberRequestDto, memberId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId){
        memberService.deleteMember(memberId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}