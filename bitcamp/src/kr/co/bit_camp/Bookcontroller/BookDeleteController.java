package kr.co.bit_camp.Bookcontroller;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.BookMapper;
import kr.co.bit_camp.db.MyAppSqlConfig;
import kr.co.bit_camp.vo.Book;

public class BookDeleteController implements Controller{
	private Scanner sc= new Scanner(System.in);
    private BookMapper mapper;
	
	public BookDeleteController(){
	SqlSession session = MyAppSqlConfig.getSqlSession();
	mapper = session.getMapper(BookMapper.class);
	
	}
	
	public String input(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}
	
	public void service() {
		List<Book> list = mapper.selectBookList();
		System.out.println();
		System.out.printf("전체 %d권%n",list.size());
		System.out.println("---------------------------------");
		System.out.println("도서번호   저자   도서제목");
		System.out.println("---------------------------------");
		for(Book b : list) {
		System.out.println(b.getBookNo()+"\t"+"    " + b.getBookWriter()+"\t"
		+"    "  + b.getBookTitle()+"\t"
		                
				);
	}
		System.out.println("---------------------------------");
		Book b = new Book();
		try {
		b.setBookNo(Integer.parseInt(input("삭제할 책번호를 입력하세요:")));
		} catch(NumberFormatException e) {
			System.out.println("번호를 입력하세요!");
			return;
		}
		for(Book k : list) {
		if(b.getBookNo()==k.getBookNo()) {
			mapper.deleteBook(b.getBookNo());
			System.out.println("삭제되었습니다.");
			return;
		}
		}
		System.out.println("없는도서입니다. 다시 입력하세요!");
		
		
	}

}
