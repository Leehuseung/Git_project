package kr.co.bit_camp.boardcontroller;

import java.text.SimpleDateFormat;
import java.util.List;

import kr.co.bit_camp.dao.BoardMapper;
import kr.co.bit_camp.vo.Board;
import kr.co.bit_camp.vo.Comment;
import kr.co.bit_camp.vo.User;

public class DetailBoardController extends BoardControllers {
	private User logInUser = null;
	private BoardMapper boardMapper;
	BoardController b = new BoardController(logInUser);
	public DetailBoardController(User logInUser, BoardMapper boardMapper) {
		this.logInUser = logInUser;
		this.boardMapper = boardMapper;
	}	

	public void service() throws Exception  {
		int rowNum = 0;
		int postNo = Integer.parseInt(input("조회할 글번호를 입력하세요 : "));
		Board board = boardMapper.selectBoardByNo(postNo);
		List<Board> boardList = boardMapper.selectBoard();
		if (board == null) {
			System.out.println("입력된 번호는 존재하지 않습니다.");
			return;
		}
		for (Board b : boardList) {
			if (b.getPostNo() == board.getPostNo()) {
				rowNum = b.getRnum();
				continue;
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy.MM.dd HH:mm:ss"
				);
		System.out.println("---------------------------------");
		System.out.println("순번 : " + rowNum);
		System.out.println("글번호 : " + board.getPostNo());
		System.out.println("페이지번호 : " + (int) boardMapper.selectGetPageNo(postNo));
		System.out.println("글쓴이 : " + board.getPostWriter());
		System.out.println("제목 : " + board.getPostTitle());
		System.out.println("내용 : " + board.getPostContent());
		System.out.println(
				"작성일 : " + sdf.format(board.getPostRegDate())
				);
		System.out.println("댓글");
		System.out.println("---------------------------------");
		List<Comment> list = board.getCommentList();
		System.out.println("댓글번호	글쓴이	내용");
		for(Comment c : list) {
			System.out.println(c.getComNo() + "\t" + 
					c.getComWriter() + "\t" + c.getComContent() + "\t" + c.getComRegDate());
		}
		System.out.println("---------------------------------");
		System.out.println("1. 댓글작성");
		System.out.println("2. 댓글수정");
		System.out.println("3. 댓글삭제");
		System.out.println("0. 게시판화면");
		System.out.println("---------------------------------");
		System.out.print("메뉴 중 처리할 항목을 선택하세요 : ");
		int inputCom = Integer.parseInt(sc.nextLine());
		commentController(inputCom, postNo);
	}
	public void commentController(int inputCom, int postNo) throws Exception {
		Comment c = new Comment();
		while(true) {
			switch (inputCom) {
			case 1 :
				c.setNo(logInUser.getNo());
				c.setComWriter(logInUser.getName());
				c.setComContent(input("내용을 입력하세요 : "));
				c.setPostNo(postNo);
				boardMapper.insertComment(c);
				System.out.println("댓글이 작성되었습니다.");
				return;

			case 2 :
				c.setPostNo(postNo);
				c.setComNo(Integer.parseInt(input("수정할 댓글번호를 입력하세요 : ")));
				Comment upd = boardMapper.selectCommentByNo(c);
				if (upd == null) {
					System.out.println("입력된 번호는 존재하지 않습니다.");
					return;
				}
				if (logInUser.getNo() != upd.getNo() && logInUser.getNo() != 1) {
					System.out.println("권한이 없습니다.");
					return;
				}
				upd.setComContent(input("내용을 입력하세요 : "));
				boardMapper.updateComment(upd);
				System.out.println("댓글이 수정되었습니다.");
				return;

			case 3 :
				c.setPostNo(postNo);
				c.setComNo(Integer.parseInt(input("삭제할 댓글번호를 입력하세요 : ")));
				Comment del = boardMapper.selectCommentByNo(c);
				if (del == null) {
					System.out.println("입력된 번호는 존재하지 않습니다.");
					return;
				}
				if (logInUser.getNo() != del.getNo() && logInUser.getNo() != 1) {
					System.out.println("권한이 없습니다.");
					return;
				}
				boardMapper.deleteComment(c);
				System.out.println("댓글이 삭제되었습니다.");
				return;

			case 0 :
				return;

			default:
				System.out.println("잘못된 메뉴번호 입니다.");
				System.out.println("다시 선택해 주세요.");
			}
		}
	}
}