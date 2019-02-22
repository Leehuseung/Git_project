package kr.co.bit_camp.usercontroller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.BoardMapper;
import kr.co.bit_camp.vo.Board;
import kr.co.bit_camp.vo.Comment;

public class UserCommentController implements Controller {
	Scanner sc = new Scanner(System.in);
	public void service() throws Exception {
		BoardMapper boardMapper = new BoardMapper();
		List<Comment> list = 
				boardMapper.selectCommentById(LogInController.logInUser.getNo());
		System.out.println("댓글번호\t글쓴이\t내용");
		System.out.println("---------------------------------");
		for (Comment c : list) {
			System.out.printf("%d\t%s\t%s\n", 
					c.getComNo(), c.getComWriter(), c.getComContent());
		}
		System.out.println("---------------------------------");
		System.out.print("댓글의 게시물을 보고싶으면 댓글 번호를 입력하세요(이전으로는 0) : ");
		int input2 = Integer.parseInt(sc.nextLine());

		//게시물 정보 가져오기
		if (input2 == 0) {
			return;
		}
		Comment comment = boardMapper.selectCommentByComNo(input2);
		if (comment == null) {
			System.out.println("---------------------------------");
			System.out.println("잘못된 번호입니다.");
			return;
		}
		Board board = boardMapper.selectBoardByNo(comment.getPostNo());
		List<Board> boardList = boardMapper.selectBoard();
		int rowNum = 0;
		for (Board b : boardList) {
			if (b.getPostNo() == board.getPostNo()) {
				rowNum = b.getRnum();
				continue;
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy.MM.dd HH:mm"
				);

		System.out.println("---------------------------------");
		System.out.println("순번 : " + rowNum);
		System.out.println("글번호 : " + board.getPostNo());
		System.out.println("페이지번호 : " + 
				(int) boardMapper.selectGetPageNo(comment.getPostNo()));
		System.out.println("글쓴이 : " + board.getPostWriter());
		System.out.println("제목 : " + board.getPostTitle());
		System.out.println("내용 : " + board.getPostContent());
		System.out.println(
				"작성일 : " + sdf.format(board.getPostRegDate())
				);
		System.out.println("댓글");
		System.out.println("---------------------------------");
		List<Comment> list3 = board.getCommentList();
		System.out.println("댓글번호	글쓴이	내용");
		for(Comment e : list3) {
			System.out.println(e.getComNo() + "\t" + 
					e.getComWriter() + "\t" + e.getComContent() + "\t" +
					sdf.format(e.getComRegDate()));
		}
	}
}