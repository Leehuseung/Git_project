package kr.co.bit_camp.boardcontroller;

import kr.co.bit_camp.dao.BoardMapper;
import kr.co.bit_camp.vo.Board;
import kr.co.bit_camp.vo.User;

public class DeleteBoardController extends BoardControllers {
	private BoardMapper boardMapper;
	private User logInUser = null;
	public DeleteBoardController(User logInUser, BoardMapper boardMapper) {
		this.logInUser = logInUser;
		this.boardMapper = boardMapper;
	}

	public void service() throws Exception {
		int delNo = Integer.parseInt(input("삭제할 글번호를 입력하세요 : "));
		Board board = boardMapper.selectBoardByNo(delNo);
		if (board == null) {
			System.out.println("입력된 번호는 존재하지 않습니다.");
			return;
		}
		if (board.getNo() != logInUser.getNo() && logInUser.getNo() != 1) {
			System.out.println("\n권한이 없습니다.");
			return;
		}
		boardMapper.deleteBoard(delNo);
		System.out.println("\n게시물이 삭제되었습니다.");
	}
}