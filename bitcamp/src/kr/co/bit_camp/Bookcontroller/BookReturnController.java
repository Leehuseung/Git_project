package kr.co.bit_camp.Bookcontroller;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.BookMapper;
import kr.co.bit_camp.db.MyAppSqlConfig;
import kr.co.bit_camp.vo.Rental;
import kr.co.bit_camp.vo.User;

public class BookReturnController implements Controller{
	User logInUser = null;
	Scanner sc = new Scanner(System.in);
	private BookMapper mapper;
	
	public BookReturnController(User logInUser){
		this.logInUser = logInUser;
		SqlSession session = MyAppSqlConfig.getSqlSession();
		mapper = session.getMapper(BookMapper.class);
	}
	
	public String input(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}
	
	
	public void service(){
		List<Rental> rentallist = mapper.selectRental();
		Rental rental = new Rental();
		int no = Integer.parseInt(input("반납할 책번호를 입력하세요:"));
		for(Rental r : rentallist){
			if(r.getBookNo()==no && r.getRentalRent() == 1){
				rental.setBookNo(no);
				rental.setNo(logInUser.getNo());
				mapper.updateReturnRental(rental);
				System.out.println();
				System.out.println("반납하였습니다.");
				return;
				
			}
		}
		System.out.println("반납할 책이 없습니다.");
		
		
	}

}
