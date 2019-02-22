package kr.co.bit_camp.boardcontroller;

import java.text.SimpleDateFormat;
import java.util.List;

import kr.co.bit_camp.dao.BoardMapper;
import kr.co.bit_camp.vo.Board;

public class PageListBoardController extends BoardControllers {
	private BoardMapper boardMapper;
	public PageListBoardController(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}
	
	public void service() throws Exception {
		Board board = new Board();
		
		int movePage = Integer.parseInt(input("보고싶은 페이지를 입력하세요 : "));
		
		board.setPageNo(movePage);
		List<Board> list = boardMapper.selectPagePostList(board);
		
		System.out.println("---------------------------------");
		System.out.printf("전체 %d개%n", list.size());
		System.out.println();
		System.out.println("순번\t글쓴이\t제목 \t글번호\t작성일");
		System.out.println("---------------------------------");
		
		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy.MM.dd"
		);
		for (Board b : list) {
			System.out.printf(
				"%d\t%s\t%s\t%s\t%s%n", 
				b.getRnum(), b.getPostWriter(), b.getPostTitle(), 
				b.getPostNo(), sdf.format(b.getPostRegDate())
			);
		}
		if (list.isEmpty()) {
			System.out.println("게시물이 존재하지 않습니다.");
		}
		System.out.println("---------------------------------");
		
		/*
		while (true) {
			System.out.println("1. 다음페이지");
			System.out.println("2. 이전페이지");
			System.out.println("0. 이전으로");
			int input = Integer.parseInt(input("번호를 입력하세요 :")); 
			switch (input) {
			case 1 : 
				movePage++;
				if (movePage == 0) {
					System.out.println("첫 페이지입니다.");
					break;
				} else {
				}
				
			case 2 : 
				movePage--;
				if (movePage == 0) {
					System.out.println("첫 페이지입니다.");
					break;
				} else {
				}
				service();
				break;
				
			case 0 : 
				return;
				
			default :
				System.out.println("잘못된 번호입니다.");
				System.out.println("다시 입력하세요.");
			}
		}
		*/
	}
}
