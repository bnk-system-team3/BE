package ssg.com.houssg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import ssg.com.houssg.dto.CommentDto;
import ssg.com.houssg.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/save")
	public ResponseEntity<?> saveComment(@RequestBody CommentDto commentDto, @RequestParam int boardId,
			HttpSession session) {
		String userId = (String) session.getAttribute("userId");

		if (userId != null) {
			// 세션에서 가져온 userId로 설정
			commentDto.setUserId(userId);
			// 리퀘스트 파라미터로 받은 boardId로 설정
			commentDto.setBoardId(boardId);

			// 댓글 저장
			commentService.saveComment(commentDto);

			return ResponseEntity.ok().build(); // 댓글 저장 성공 응답
		} else {
			return ResponseEntity.badRequest().build(); // 세션에 사용자 아이디가 없으면 실패 응답
		}
	}

	@PatchMapping("/update")
	public ResponseEntity<?> updateComment(@RequestParam int cmtId, @RequestBody CommentDto commentDto) {
		// 댓글 수정
		commentDto.setCmtId(cmtId);
		commentService.updateComment(commentDto);

		return ResponseEntity.ok().build(); // 댓글 수정 성공 응답
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteComment(@RequestParam int cmtId) {
		// 댓글 삭제
		commentService.deleteComment(cmtId);

		return ResponseEntity.ok().build(); // 댓글 삭제 성공 응답
	}

	@GetMapping("/findCmt")
	public ResponseEntity<List<CommentDto>> getCommentsByBoardId(@RequestParam int boardId) {
		// 해당 게시글의 댓글 조회
		List<CommentDto> comments = commentService.getCommentsByBoardId(boardId);

		return ResponseEntity.ok(comments);
	}
}
