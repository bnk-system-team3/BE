package ssg.com.houssg.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import ssg.com.houssg.dto.BoardDto;
import ssg.com.houssg.service.BoardService;

@RestController
@RequestMapping("board")
public class BoardController {

	@Autowired
	private BoardService service;

	@PostMapping("/save")
	public ResponseEntity<?> saveBoard(@RequestBody BoardDto boardDto, HttpSession session) {
		String userId = (String) session.getAttribute("userId");

		if (userId != null) {
			// 현재 날짜로 createDate 설정
			boardDto.setCreateDate(new Date());

			// 세션에서 가져온 userId로 설정
			boardDto.setUserId(userId);

			// 게시글 저장
			service.saveBoard(boardDto);

			System.out.println("게시글 저장 성공");

			return ResponseEntity.ok().build(); // 게시글 저장 성공 응답
		} else {
			return ResponseEntity.badRequest().build(); // 세션에 사용자 아이디가 없으면 실패 응답
		}
	}

	@PatchMapping("/update")
	public ResponseEntity<?> updateBoard(@RequestParam int boardId, @RequestBody BoardDto boardDto) {
		// 게시글 수정
		boardDto.setBoardId(boardId);
		service.updateBoard(boardDto);

		System.out.println("게시글 수정 완료");
		return ResponseEntity.ok().build(); // 게시글 수정 성공 응답
	}
	
	@GetMapping("/home")
    public ResponseEntity<List<BoardDto>> getHomeBoardList() {
        List<BoardDto> boardList = service.getHomeBoardList();
        return ResponseEntity.ok(boardList);
    }
}
