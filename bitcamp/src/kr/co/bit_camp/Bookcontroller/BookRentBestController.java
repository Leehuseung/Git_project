package kr.co.bit_camp.Bookcontroller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.BookMapper;
import kr.co.bit_camp.db.MyAppSqlConfig;
import kr.co.bit_camp.vo.User;

public class BookRentBestController implements Controller{
private BookMapper mapper;
	
	public BookRentBestController(){
	SqlSession session = MyAppSqlConfig.getSqlSession();
	mapper = session.getMapper(BookMapper.class);
	
	}
	public void service(){
		List<User> list = mapper.selectRentalBestList();
		int i = 0;
		System.out.println();
		System.out.printf("이달의 독서왕"+"%n");
		System.out.println("---------------------------------");
		System.out.println("순위    이름   빌린권수   ");
		System.out.println("---------------------------------");
		for(User b : list) {
		System.out.println((++i)+"    "+ b.getName()
		     +"    "+ b.getTotal()
		
		                
				);
	}
		System.out.println("---------------------------------");
	   
	}

}