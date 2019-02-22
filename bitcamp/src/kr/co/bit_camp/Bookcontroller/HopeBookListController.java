package kr.co.bit_camp.Bookcontroller;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.BookMapper;
import kr.co.bit_camp.db.MyAppSqlConfig;
import kr.co.bit_camp.vo.Hopebook;

public class HopeBookListController implements Controller{
private BookMapper mapper;
private Scanner sc= new Scanner(System.in);
	
	public HopeBookListController(){
	SqlSession session = MyAppSqlConfig.getSqlSession();
	mapper = session.getMapper(BookMapper.class);
	
	}
	
	public int choiceMenu() {
		  System.out.println();
		  System.out.println("---------------------------------");
		  System.out.println("1.삭제");
		  System.out.println("2.뒤로");
		  System.out.println("---------------------------------");
		  System.out.print("메뉴 중 처리할 항목을 선택하세요:");
		return Integer.parseInt(sc.nextLine());
	}
	
	public void service(){
	    List<Hopebook> list = mapper.selectHopeBookList();
	    System.out.println("희망도서 쪽지함");
	    System.out.println("---------------------------------");
	    System.out.println("번호  회원아이디   도서제목   저자   보낸날짜");
	    System.out.println("---------------------------------");
	    
	    SimpleDateFormat sdf = new SimpleDateFormat(
	    		"yyyy-MM-dd HH:mm:ss"
	    		);
	    for(Hopebook h : list){
	         
	    	System.out.printf("%d  %s  %s  %s  %s%n", h.getHopebookNo(),
	    			h.getId(),h.getHopebookTitle(),
	    			h.getHopebookWriter(), 
	    			sdf.format(h.getHopebookDate())
	    			);
	    }
	    try {
	        while(true) {
	         switch(choiceMenu()) {
	          case 1:
	        	  Controller ctrl = new HopeBookListDeleteController();
	        	  ctrl.service();
	        	 break;
	          case 2:
	        	  return;
	        }
	      }
	    
	    }
	     catch (Exception e) {
	    	 e.printStackTrace();
	    }
	}

	}
