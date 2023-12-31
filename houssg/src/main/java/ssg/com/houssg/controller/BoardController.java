package ssg.com.houssg.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import ssg.com.houssg.dto.ParticipantBoardDto;
import ssg.com.houssg.dto.ReviewDto;
import ssg.com.houssg.service.BoardService;
import ssg.com.houssg.service.UserService;

@RestController
@RequestMapping("board")
public class BoardController {

	@Autowired
	private BoardService service;
	
	@Autowired
	private UserService userService;

	// 게시글 작성
	@PostMapping("/saveBoard")
	public ResponseEntity<?> saveBoard(@RequestBody BoardDto boardDto) {
		String userId = (String) boardDto.getUserId();
		String nickname = (String) userService.getUserProfile(userId).getNickname(); 
			

		if (userId != null) {
			// 현재 날짜로 createDate 설정
			boardDto.setCreateDate(new Date());

			// 세션에서 가져온 userId로 설정
			boardDto.setUserId(userId);
			boardDto.setNickname(nickname);

			// 게시글 저장
			service.saveBoard(boardDto);

			System.out.println("게시글 저장 성공");

			return ResponseEntity.ok().build(); // 게시글 저장 성공 응답
		} else {
			return ResponseEntity.badRequest().build(); // 세션에 사용자 아이디가 없으면 실패 응답
		}
	}

	@PostMapping("/saveStudyProjectBoard")
	public ResponseEntity<?> saveStudyProjectBoard(@RequestBody BoardDto boardDto) {
		// 현재 날짜로 createDate 설정
		boardDto.setCreateDate(new Date());
		String userId = (String) boardDto.getUserId();
		String nickname = (String) userService.getUserProfile(userId).getNickname(); 
		boardDto.setNickname(nickname);
		
		// 게시글 저장
		service.saveBoard(boardDto);
		System.out.println(boardDto.toString());

		// 게시글 저장 후, 기술 스택 및 포지션 삽입
		System.out.println(boardDto.getUserId());
		int boardId = (int) service.findBoardId(boardDto.getUserId());

		System.out.println("Saved boardId: " + boardId);
		
		// 기술 스택 삽입
		if (boardDto.getTechStack() != null) {
			for (String tech : boardDto.getTechStack()) {
				service.insertTechStack(boardId, tech);
			}
		}

		if (boardDto.getPositions() != null) {
			for (String position : boardDto.getPositions()) {
				service.insertNeedPosition(boardId, position);
			}
		}
		System.out.println("게시글 저장 및 기술 스택, 포지션 삽입 성공");

		return ResponseEntity.ok().build(); // 게시글 저장 성공 응답

	}

	// 게시글 수정
	@PatchMapping("/update")
	public ResponseEntity<?> updateBoard(@RequestParam int boardId, @RequestBody BoardDto boardDto) {
		// 게시글 수정
		boardDto.setBoardId(boardId);
		service.updateBoard(boardDto);

		System.out.println("게시글 수정 완료");
		return ResponseEntity.ok().build(); // 게시글 수정 성공 응답
	}

	// 홈화면 게시글 조회
	@GetMapping("/home")
	public ResponseEntity<List<BoardDto>> getHomeBoardList() {
		List<BoardDto> boardList = service.getHomeBoardList();
		return ResponseEntity.ok(boardList);
	}

	// 리뷰작성
	@PostMapping("/saveReview")
	public ResponseEntity<?> saveReview(@RequestParam int boardId, @RequestBody ReviewDto reviewDto) {
		String userId = (String) reviewDto.getUserId();

		if (userId != null) {
			// 현재 날짜로 createDate 설정
			reviewDto.setCreateDate(new Date());

			// 세션에서 가져온 userId로 설정
			reviewDto.setUserId(userId);

			// boardId 설정
			reviewDto.setBoardId(boardId);

			// 리뷰 저장
			service.saveReview(reviewDto);

			System.out.println("리뷰 저장 성공");

			return ResponseEntity.ok().build(); // 리뷰 저장 성공 응답
		} else {
			return ResponseEntity.badRequest().build(); // 세션에 사용자 아이디가 없으면 실패 응답
		}
	}

	// 지원자 참가신청
	@PostMapping("/apply")
	public ResponseEntity<?> applyForParticipation(@RequestBody BoardDto boardDto) {
		String userId = (String) boardDto.getUserId();
		int boardId = (int) boardDto.getBoardId();

		if (userId != null) {
			// 모임 참가 신청 정보 생성
			ParticipantBoardDto participantDto = new ParticipantBoardDto();
			participantDto.setBoardId(boardId);
			participantDto.setUserId(userId);
			participantDto.setCaptainFlag(0); // 기본값으로 설정
			participantDto.setJoinFlag(2); // 모임 참가 상태를 나타내는 값

			// 모임 참가 신청
			service.applyForParticipation(participantDto);

			System.out.println("모임 참가 신청 성공");

			return ResponseEntity.ok().build(); // 성공 응답
		} else {
			return ResponseEntity.badRequest().build(); // 실패 응답
		}
	}

	// 지원상태 업데이트
	@PatchMapping("/updateApplyStatus")
	public ResponseEntity<?> updateParticipantStatus(
			@RequestParam int boardId, 
			@RequestParam int joinFlag,
			@RequestParam String userId) {

		service.updateParticipantStatus(boardId, joinFlag, userId);
		System.out.println("지원상태 업데이트");
		return ResponseEntity.ok().build(); // 업데이트 성공 응답
	}

	// 내가 참가중인 모임 조회
	@GetMapping("/myBoards")
	public ResponseEntity<List<BoardDto>> getMyProjects(@RequestParam String userId) {
//		String userId = (String) session.getAttribute("userId");
		if (userId != null) {
			List<BoardDto> myProjects = service.getMyProjects(userId);
			return ResponseEntity.ok(myProjects);
		} else {
			return ResponseEntity.badRequest().build(); // 세션에 사용자 아이디가 없으면 실패 응답
		}
	}

	// 내 모임에 지원한 유저 조회
	@GetMapping("/applicants")
	public ResponseEntity<List<ParticipantBoardDto>> getApplicantsForProject(@RequestParam int boardId) {
		List<ParticipantBoardDto> applicants = service.getApplicantsForProject(boardId);
		return ResponseEntity.ok(applicants);
	}

	// 카테고리별 검색
	@GetMapping("/searchByCategory")
	public ResponseEntity<List<BoardDto>> getBoardByCategory(@RequestParam String category) {
		List<BoardDto> boardList = service.getBoardByCategory(category);
		return ResponseEntity.ok(boardList);
	}

	// 키워드별 검색
	@GetMapping("/searchByKeyword")
	public ResponseEntity<List<BoardDto>> searchByKeyword(@RequestParam String keyword) {
		List<BoardDto> boardList = service.getBoardByKeyword(keyword);
		return ResponseEntity.ok(boardList);
	}

	// viewCnt가 가장 높은 순서로 게시글 조회
	@GetMapping("/searchByViewCnt")
	public ResponseEntity<List<BoardDto>> getBoardByViewCnt() {
		List<BoardDto> boardList = service.getBoardByViewCnt();
		return ResponseEntity.ok(boardList);
	}

	// 팀 컨텐츠 작성 및 수정
	@PostMapping("/updateTeamContent")
	public void updateTeamContent(@RequestParam int boardId, @RequestBody BoardDto boardDto) {

		boardDto.setBoardId(boardId);

		service.updateTeamContent(boardDto);
	}

	@GetMapping("/getBoardDetails")
	public ResponseEntity<?> getBoardDetails(@RequestParam int boardId) {
		// 게시글 상세 정보 및 기술 스택, 포지션 정보 조회
		service.incrementViewCount(boardId);
		Map<String, Object> boardDetails = service.getBoardDetails(boardId);

		if (boardDetails != null && !boardDetails.isEmpty()) {
			return ResponseEntity.ok(boardDetails);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
