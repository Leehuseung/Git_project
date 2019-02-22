package kr.co.bit_camp.boardcontroller;

import kr.co.bit_camp.dao.BoardMapper;
import kr.co.bit_camp.vo.Board;
import kr.co.bit_camp.vo.User;

public class UpdateBoardController extends BoardControllers {
	private BoardMapper boardMapper;
	private User logInUser = null;
	public UpdateBoardController(User logInUser, BoardMapper boardMapper) {
		this.logInUser = logInUser;
		this.boardMapper = boardMapper;
	}

	public void service() throws Exception {
		int postNo = Integer.parseInt(input("수정할 글번호를 입력하세요 : "));
		Board board = boardMapper.selectBoardByNo(postNo);
		if (board == null) {
			System.out.println("입력된 번호는 존재하지 않습니다.");
			return;
		}
		if (board.getNo() != logInUser.getNo() && logInUser.getNo() != 1) {
			System.out.println("\n권한이 없습니다.");
			return;
		}

		Board param = new Board();
		param.setPostNo(postNo);
		param.setPostTitle(input("제목을 입력하세요 : "));
		param.setPostContent(input("내용을 입력하세요 : "));

		boardMapper.updateBoard(param);

		System.out.println();
		System.out.println("게시물이 수정되었습니다.");
	}
}