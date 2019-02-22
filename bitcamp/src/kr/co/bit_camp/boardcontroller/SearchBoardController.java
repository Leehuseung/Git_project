package kr.co.bit_camp.boardcontroller;

import java.text.SimpleDateFormat;
import kr.co.bit_camp.dao.BoardMapper;

public class SearchBoardController extends BoardControllers{
	private BoardMapper boardMapper;
	public SearchBoardController(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}
	SimpleDateFormat sdf = new SimpleDateFormat(
			"MM/dd HH:mm");

	private int choicemenu() {
		System.out.println("---------------------------------");
		System.out.println("1. 작성자명 검색");
		System.out.println("2. 내용 검색");
		System.out.println("0. 이전화면");
		System.out.println("---------------------------------");
		System.out.print("검색할 종류를 선택하세요 : ");
		return Integer.parseInt(sc.nextLine());
	}
	public void service() throws Exception {
		SearchBoardControllers alt = null;
		while(true) {
			switch(choicemenu()) {
			case 1: 
				alt = new SearchBoardWriterController(boardMapper);
				break;
			case 2:
				alt = new SearchBoardContentController(boardMapper);
				break;
			case 0:
				return;
			default :
				System.out.println("잘못된 번호입니다. \n 다시 입력하세요.");
			}
			if (alt != null) {
				alt.service();
			}
		}
	}
}