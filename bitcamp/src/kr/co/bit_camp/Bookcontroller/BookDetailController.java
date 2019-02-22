package kr.co.bit_camp.Bookcontroller;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.BookMapper;
import kr.co.bit_camp.db.MyAppSqlConfig;
import kr.co.bit_camp.vo.Book;

public class BookDetailController implements Controller{
	
	private Scanner sc= new Scanner(System.in);
    private BookMapper mapper;
	
	public BookDetailController(){
		
	SqlSession session = MyAppSqlConfig.getSqlSession();
	mapper = session.getMapper(BookMapper.class);
	
	}
	public String input(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}
	
    public int choiceMenu() {
    	System.out.println("1.저자로 검색");
    	System.out.println("2.도서제목으로 검색");
    	System.out.println("0.뒤로");
    	System.out.println("---------------------------------");

    	return Integer.parseInt(input("번호를 입력하세요:"));
    }
    
    public void titleSearch() {
    	Book book = new Book();
    	book.setBookTitle(input("검색할 도서 제목을 입력하세요 :"));
    	System.out.println();
    	List<Book> list = mapper.selectDetailList(book.getBookTitle());
    	if(list.isEmpty()){
    		System.out.println("검색어가 존재하지 않습니다.");
    		return;
    	}
    	
    	for(Book bs : list){
    		System.out.println("도서 번호: " + bs.getBookNo());
    		System.out.println("저자: " + bs.getBookWriter());
    		System.out.println("도서 제목: " + bs.getBookTitle());
    		System.out.println("---------------------------------");
    		
    	}
    }
	
    public void writerSearch() {
    	
    	Book book = new Book();
    	book.setBookWriter(input("검색할 저자을 입력하세요 :"));
    	System.out.println();
    	List<Book> list = mapper.selectDetailList1(book.getBookWriter());
    	if(list.isEmpty()){
    		System.out.println("검색어가 존재하지 않습니다.");
    		return;
    	}
    	
    	for(Book bs : list){
    		System.out.println("도서 번호: " + bs.getBookNo());
    		System.out.println("저자: " + bs.getBookWriter());
    		System.out.println("도서 제목: " + bs.getBookTitle());
    		System.out.println("---------------------------------");
    		
    	}
    }
    
	public void service(){
		
		while(true) {
			switch(choiceMenu()) {
			case 1:
				writerSearch();
				break;
			case 2:
			    titleSearch();
				break;
			case 0:
				
				return;
			default :
				System.out.println("없는 번호 입니다.");
				break;
			}
		}
		
	}

}
