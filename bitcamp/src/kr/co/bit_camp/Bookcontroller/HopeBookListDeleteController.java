package kr.co.bit_camp.Bookcontroller;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.BookMapper;
import kr.co.bit_camp.db.MyAppSqlConfig;
import kr.co.bit_camp.vo.Hopebook;

public class HopeBookListDeleteController implements Controller {
	private Scanner sc= new Scanner(System.in);
    private BookMapper mapper;
	
	public HopeBookListDeleteController(){
	SqlSession session = MyAppSqlConfig.getSqlSession();
	mapper = session.getMapper(BookMapper.class);
	
	}
	
	public String input(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}
	
	public void service(){
		 Hopebook b = new Hopebook();
		 List<Hopebook> list = mapper.selectHopeBookList();
		try {
			b.setHopebookNo(Integer.parseInt(input("삭제할 번호를 입력하세요:")));
			} catch(NumberFormatException e) {
				System.out.println("번호를 입력하세요!");
				return;
			}
		    for(Hopebook h : list) {
		    if(b.getHopebookNo()==h.getHopebookNo()) {
		    	mapper.deleteHopeBook(b.getHopebookNo());
		    	System.out.println("삭제되었습니다.");
		    	return;
		    }
		    }
		    System.out.println("번호를 다시입력하세요!");
			
			
		}

	}


