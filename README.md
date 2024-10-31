API 명세
TodoController
일정 생성(Post): "/api/todo/"
전체 일정 조회(Get): "/api/todo/"
선택 일정 조회(Get): "/api/todo/{todoId}"
선택 일정 수정(PUT): "/api/todo/{todoId}"
선택 일정 삭제(Delete): "/api/todo/{todoId}"
일정 담당자(Post): "/api/todo/{todoId}/assign/{memberId}"

CommentController
댓글 생성(Post): "/api/todo/{todoId}/comment"
댓글 수정(Put): "/api/todo/{todoId}/comment/{commentId}"
댓글 삭제(delete): "/api/todo/{todoId}/comment/{commentId}"

MemberController
멤버 조회(Get): "/api/member/{memberId}"
멤버 생성(Post): "/api/member"
멤버 수정(Put): "/api/member/{memberId}"
멤버 삭제(Delete): "/api/member/{memberId}"


todo ERD 작성

![image](https://github.com/user-attachments/assets/ad32b40a-78b9-4c31-836e-07f231115f90)
