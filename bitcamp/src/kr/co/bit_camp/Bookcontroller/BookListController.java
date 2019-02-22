package kr.co.bit_camp.Bookcontroller;

import java.util.List;


import org.apache.ibatis.session.SqlSession;

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.BookMapper;
import kr.co.bit_camp.db.MyAppSqlConfig;
import kr.co.bit_camp.vo.Book;

public class BookListController implements Controller{
	private BookMapper mapper;
	
	public BookListController(){
	SqlSession session = MyAppSqlConfig.getSqlSession();
	mapper = session.getMapper(BookMapper.class);
	
	}
	public void service(){
		List<Book> list = mapper.selectBookList();
		System.out.println();
		System.out.printf("전체 %d권%n",list.size());
		System.out.println("---------------------------------");
		System.out.println("도서번호   저자   책제목");
		System.out.println("---------------------------------");
		for(Book b : list) {
		System.out.println(b.getBookNo()+"\t"+"    " + b.getBookWriter()+"\t"
		+"    "  + b.getBookTitle()+"\t"
		                
				);
	}
		
		
	}

}
