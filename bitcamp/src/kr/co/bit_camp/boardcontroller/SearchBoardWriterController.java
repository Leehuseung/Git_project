package kr.co.bit_camp.boardcontroller;

import java.util.List;

import kr.co.bit_camp.dao.BoardMapper;
import kr.co.bit_camp.vo.Board;

public class SearchBoardWriterController extends SearchBoardControllers{
	private BoardMapper boardMapper;
	public SearchBoardWriterController(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}

	public void service() throws Exception {
		Board board = new Board();
		board.setPostWriter(input("검색할 글쓴이명을 입력하세요 : "));
		if (board.getPostWriter().equals("")) {
			System.out.println("내용을 입력하세요.");
			return;
		}

		List<Board> searchResult = boardMapper.selectSearch(board);
		System.out.println("---------------------------------");
		System.out.println("글번호\t작성자\t제목\t작성일");
		if (searchResult.isEmpty()) {
			System.out.println("검색 결과가 없습니다.");
		}
		for (Board r : searchResult) {
			System.out.printf(
					"%d\t%s\t%s\t%s%n", 
					r.getPostNo(), r.getPostWriter(), r.getPostTitle(), 
					sdf.format(r.getPostRegDate())
					);
		}
	}
}