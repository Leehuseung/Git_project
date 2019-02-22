package kr.co.bit_camp.Bookcontroller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.BookMapper;
import kr.co.bit_camp.db.MyAppSqlConfig;
import kr.co.bit_camp.vo.Book;

public class BookBestListController implements Controller{
	
private BookMapper mapper;
	
	public BookBestListController(){
	SqlSession session = MyAppSqlConfig.getSqlSession();
	mapper = session.getMapper(BookMapper.class);
	
	}
	public void service(){
		List<Book> list = mapper.selectBestList();
		int i = 0;
		System.out.println();
		System.out.printf("베스트셀러"+"%n");
		System.out.println("---------------------------------");
		System.out.println("순위    책제목    저자   ");
		System.out.println("---------------------------------");
		for(Book b : list) {
		System.out.println((++i)+"    "+ b.getBookTitle()
		     +"    "+ b.getBookWriter()
		
		                
				);
	}
		System.out.println("---------------------------------");
	   
	}

}
