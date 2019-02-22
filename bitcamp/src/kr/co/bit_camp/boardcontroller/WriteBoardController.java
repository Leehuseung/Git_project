package kr.co.bit_camp.boardcontroller;

import kr.co.bit_camp.dao.BoardMapper;
import kr.co.bit_camp.vo.Board;
import kr.co.bit_camp.vo.User;

public class WriteBoardController extends BoardControllers {
	private User logInUser = null;
	private BoardMapper boardMapper;
	public WriteBoardController(User logInUser, BoardMapper boardMapper) {
		this.logInUser = logInUser;
		this.boardMapper = boardMapper;
	}

	public void service() throws Exception  {
		Board b = new Board();
		b.setNo(logInUser.getNo());
		b.setPostTitle(input("제목을 입력하세요 : "));
		b.setPostWriter(logInUser.getName());
		b.setPostContent(input("내용을 입력하세요 : "));
		if (b.getPostTitle().equals("") || b.getPostContent().equals("")) {
			System.out.println("게시물의 내용을 입력하세요");
			return;
		}

		boardMapper.insertBoard(b);
		System.out.println("게시글이 등록되었습니다.");
	}
}