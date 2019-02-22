package kr.co.bit_camp.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.bit_camp.db.MyAppSqlConfig;
import kr.co.bit_camp.vo.Board;
import kr.co.bit_camp.vo.Comment;

public class BoardMapper {
	
	private final SqlSession session = MyAppSqlConfig.getSqlSession();
	private final static String NS = 
			"kr.co.bit_camp.dao.BoardMapper.";
	public List<Board> selectBoard() throws Exception {
		return session.selectList(NS + "selectBoard");
	}
	public Board selectBoardByNo(int no) throws Exception {
		return session.selectOne(NS + "selectBoardByNo", no);
	}
	public void insertBoard(Board board) throws Exception {
		session.insert(NS + "insertBoard", board);
	}
	public void updateBoard(Board board) throws Exception {
		session.update(NS + "updateBoard", board);
	}
	public void deleteBoard(int no) throws Exception {
		session.delete(NS + "deleteBoard", no);
	}
	public Comment selectCommentListByNo(int postNo) throws Exception {
		return session.selectOne(NS + "selectCommentListByNo", postNo);
	}
	public Comment selectCommentByNo(Comment comment) throws Exception {
		return session.selectOne(NS + "selectCommentByNo", comment);
	}
	public void insertComment(Comment comment) throws Exception {
		session.insert(NS + "insertComment", comment);
	}
	public void updateComment(Comment comment) throws Exception {
		session.update(NS + "updateComment", comment);
	}
	public void deleteComment(Comment comment) throws Exception {
		session.update(NS + "deleteComment", comment);
	}
	public List<Board> selectBoardById(int no) throws Exception {
		return session.selectList(NS + "selectBoardById", no);
	}
	public List<Board> selectSearch(Board board) throws Exception {
		return session.selectList(NS + "selectSearch", board);
	}
	public List<Board> selectPagePostList(Board board) throws Exception {
		return session.selectList(NS + "selectPagePostList", board);
	}
	public int selectLastPostRownum() throws Exception {
		return session.selectOne(NS + "selectLastPostRownum");
	}
	public int selectCountPost() throws Exception {
		return session.selectOne(NS + "selectCountPost");
	}
	public double selectGetPageNo(int no) throws Exception {
		return session.selectOne(NS + "selectGetPageNo", no);
	}
	public List<Comment> selectCommentById(int no) throws Exception {
		return session.selectList(NS + "selectCommentById", no);
	}
	public Comment selectCommentByComNo(int no) throws Exception {
		return session.selectOne(NS + "selectCommentByComNo", no);
	}
}