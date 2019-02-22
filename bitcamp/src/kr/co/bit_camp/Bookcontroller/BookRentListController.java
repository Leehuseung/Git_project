package kr.co.bit_camp.Bookcontroller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.BookMapper;
import kr.co.bit_camp.db.MyAppSqlConfig;

import kr.co.bit_camp.vo.User;
import kr.co.bit_camp.vo.UserRental;

public class BookRentListController implements Controller{
	private BookMapper mapper;
	private User logInUser = null;
	
	public BookRentListController(User logInUser){
		this.logInUser = logInUser;
		SqlSession session = MyAppSqlConfig.getSqlSession();
		mapper = session.getMapper(BookMapper.class);
	}
	
	public void service(){
	  int no = 0;
	  List<UserRental> list =  mapper.selectDetailRentList(logInUser.getNo());
	  for(UserRental b : list){
		  if(b.getRentalRent()==1)no++;
		  }
	  System.out.println("빌린 책 현황");
	  System.out.printf("빌린 권수 %d권%n", no);
	  System.out.println("---------------------------------");
	  System.out.println("번호     저자      제목      반납날짜");
	  System.out.println("---------------------------------");
	  if(list.isEmpty()){
		  System.out.println("빌린 책이 없습니다.");
		  return;
	  }
	  for(UserRental b : list){
		  if(b.getRentalRent()==1){
	      System.out.printf("%s   %s   %s   %s%n",
			        b.getBookNo(),b.getBookWriter(),
			        b.getBookTitle(),b.getReturnDate()+"까지"
			   );
		  }
	  }
	  System.out.println("---------------------------------");
	  System.out.println();
	}

}
