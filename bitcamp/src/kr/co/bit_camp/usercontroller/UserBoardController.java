package kr.co.bit_camp.usercontroller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.BoardMapper;
import kr.co.bit_camp.vo.Board;
import kr.co.bit_camp.vo.Comment;

public class UserBoardController implements Controller{
	private BoardMapper boardMapper = new BoardMapper();
	Scanner sc = new Scanner(System.in);

	SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm");

	public void service() throws Exception {
		List<Board> list = 
				boardMapper.selectBoardById(LogInController.logInUser.getNo());
		System.out.println("---------------------------------");
		System.out.println("글번호\t글쓴이\t제목 \t작성일");
		for (Board b : list) {
			System.out.printf(
					"%d\t%s\t%s\t%s%n", 
					b.getPostNo(), b.getPostWriter(), b.getPostTitle(), 
					sdf.format(b.getPostRegDate())
					);
		}

		//게시물 상세조회

		System.out.print("상세조회할 글 번호를 입력하세요(이전으로는 0) : ");
		int input = Integer.parseInt(sc.nextLine());

		if (input == 0) {
			return;
		}

		Board board = boardMapper.selectBoardByNo(input);
		if (board == null) {
			System.out.println("---------------------------------");
			System.out.println("잘못된 번호입니다.");
			return;
		}

		List<Board> boardList = boardMapper.selectBoard();

		int rowNum = 0;
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
		System.out.println("페이지번호 : " + (int) boardMapper.selectGetPageNo(input));
		System.out.println("글쓴이 : " + board.getPostWriter());
		System.out.println("제목 : " + board.getPostTitle());
		System.out.println("내용 : " + board.getPostContent());
		System.out.println(
				"작성일 : " + sdf.format(board.getPostRegDate())
				);
		System.out.println("댓글");
		System.out.println("---------------------------------");
		List<Comment> list2 = board.getCommentList();
		System.out.println("댓글번호	글쓴이	내용");
		for(Comment c : list2) {
			System.out.println(c.getComNo() + "\t" + 
					c.getComWriter() + "\t" + c.getComContent() + "\t" +
					sdf.format(c.getComRegDate()));
		}
	}
}