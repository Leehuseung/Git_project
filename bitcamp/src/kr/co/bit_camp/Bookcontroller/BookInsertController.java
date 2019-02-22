package kr.co.bit_camp.Bookcontroller;

import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.BookMapper;
import kr.co.bit_camp.db.MyAppSqlConfig;
import kr.co.bit_camp.vo.Book;

  public class BookInsertController implements Controller{
	private Scanner sc= new Scanner(System.in);
    private BookMapper mapper;
	
	public BookInsertController(){
	SqlSession session = MyAppSqlConfig.getSqlSession();
	mapper = session.getMapper(BookMapper.class);
	
	}
	public String input(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}
	public void service(){
		Book book = new Book();
		book.setBookTitle(input("도서 제목을 입력하세요:"));
		book.setBookWriter(input("도서 저자를 입력하세요:"));
		if(book.getBookTitle().equals("")||
			book.getBookWriter().equals("")){
			System.out.println();
			System.out.println("내용을 입력하세요");
			return;
		}
		mapper.insertBook(book);
		System.out.println("도서 등록이 완료되었습니다.");
		
		
		
		
	}

}
