package kr.co.bit_camp.boardcontroller;

import java.util.Scanner;

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.BoardMapper;
import kr.co.bit_camp.usercontroller.LogInController;
import kr.co.bit_camp.vo.User;



public class BoardController implements Controller{
	private User logInUser = null;
	private BoardMapper boardMapper;
	public Scanner sc = new Scanner(System.in);
	public BoardController(User logInUser) {
		this.logInUser = logInUser;
		boardMapper = new BoardMapper();
	}

	private int choiceMenu() {
		System.out.println("---------------------------------");
		System.out.println("1. 전체 게시물 조회");
		System.out.println("2. 페이지별 게시물 조회");
		System.out.println("3. 글 상세 조회");
		System.out.println("4. 글 등록");
		System.out.println("5. 글 수정");
		System.out.println("6. 글 삭제");
		System.out.println("7. 검색");
		System.out.println("0. 메인화면");
		System.out.println("---------------------------------");
		System.out.print("메뉴 중 처리할 항목을 선택하세요 : ");
		return Integer.parseInt(sc.nextLine());
	}

	public void service() {
		try {
			System.out.println("자유게시판");
			System.out.println("---------------------------------");
			while (true) {
				BoardControllers ctrl = null;
				switch (choiceMenu()) {
				case 1:  // 전체 게시물 조회
					ctrl = new ListBoardController(boardMapper);
					break;
				case 2:  // 페이지별 조회
					ctrl = new PageListBoardController(boardMapper);
					break;
				case 3:  // 상세조회
					ctrl = new DetailBoardController(logInUser, boardMapper);
					break;
				case 4:  // 작성
					ctrl = new WriteBoardController(logInUser, boardMapper);
					break;
				case 5:  // 수정
					ctrl = new UpdateBoardController(logInUser, boardMapper);
					break;
				case 6:  // 삭제
					ctrl = new DeleteBoardController(logInUser, boardMapper);
					break;
				case 7:  // 검색
					ctrl = new SearchBoardController(boardMapper);
					break;
				case 0:
					return;
				default:
					System.out.println("잘못된 메뉴번호 입니다.");
					System.out.println("다시 선택해 주세요.");
				}

				if (ctrl != null) {
					ctrl.service();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
