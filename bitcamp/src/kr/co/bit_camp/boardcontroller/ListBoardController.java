package kr.co.bit_camp.boardcontroller;

import java.text.SimpleDateFormat;
import java.util.List;

import kr.co.bit_camp.dao.BoardMapper;
import kr.co.bit_camp.vo.Board;

public class ListBoardController extends BoardControllers {
	private BoardMapper boardMapper;
	public ListBoardController(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}
	public void service() throws Exception {
		double index = 0.9;
		List<Board> list = boardMapper.selectBoard();

		System.out.printf("전체 %d개%n", list.size());
		System.out.println();
		System.out.println("순번\t글쓴이\t제목 \t글번호\t작성일\t페이지번호");
		System.out.println("---------------------------------");

		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy/MM/dd");

		for (Board b : list) {
			System.out.printf(
					"%d\t%s\t%s\t%s\t%s\t%.0f%n", 
					b.getRnum(), b.getPostWriter(), b.getPostTitle(), 
					b.getPostNo(), sdf.format(b.getPostRegDate()), Math.ceil(index/10)
					);
			index++;
		}
		if (list.isEmpty()) {
			System.out.println("게시물이 존재하지 않습니다.");}
		System.out.println("---------------------------------");	
	}
}