package kr.co.bit_camp.Bookcontroller;

import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.BookMapper;
import kr.co.bit_camp.db.MyAppSqlConfig;
import kr.co.bit_camp.vo.Hopebook;
import kr.co.bit_camp.vo.User;

public class BookHopeController implements Controller{
	private User logInUser = null;
	private Scanner sc= new Scanner(System.in);
    private BookMapper mapper;
	
	public BookHopeController(User logInUser){
		this.logInUser = logInUser;
		SqlSession session = MyAppSqlConfig.getSqlSession();
		mapper = session.getMapper(BookMapper.class);
		
	}
	
	public String input(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}
	
	public void service(){
		Hopebook hopebook= new Hopebook();
		hopebook.setId(logInUser.getId());
		hopebook.setHopebookTitle(input("희망도서의 제목을 입력하세요:"));
		hopebook.setHopebookWriter(input("희망도서의 저자를 입력하세요:"));
		if(hopebook.getHopebookTitle().equals("")||
				hopebook.getHopebookWriter().equals("")){
			System.out.println();
			System.out.println("내용을 입력하세요");
			return;
		}
		mapper.insertHopeBook(hopebook);
		System.out.println("희망도서가 신청되었습니다.");
		
		
	}

}
