package kr.co.bit_camp.Bookcontroller;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.BookMapper;
import kr.co.bit_camp.db.MyAppSqlConfig;
import kr.co.bit_camp.vo.Book;
import kr.co.bit_camp.vo.Rental;
import kr.co.bit_camp.vo.User;

public class BookRentController implements Controller{
	
	Scanner sc = new Scanner(System.in);
	private User logInUser = null;
	private BookMapper mapper;
	
	public BookRentController(User logInUser){
		this.logInUser = logInUser;
	SqlSession session = MyAppSqlConfig.getSqlSession();
	mapper = session.getMapper(BookMapper.class);
	
	}
	public void service(){
		List<Book> list = mapper.selectBookList();
		List<Rental> rentallist = mapper.selectRental();
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
		System.out.print("대여할 책 번호를 입력하세요:");
		int no = Integer.parseInt(sc.nextLine());
		for(Rental r : rentallist){
			if(r.getBookNo()==no && r.getRentalRent()==1){
				System.out.println();
				System.out.println("이미 대여중인 책입니다.");
				return;
			}
		}
		Rental rental = new Rental();
		for(Book b : list){
			if(b.getBookNo() == no){
				rental.setBookNo(b.getBookNo());
				rental.setNo(logInUser.getNo());
				mapper.insertRental(rental);
				System.out.println("대여가 완료 되었습니다.");
				return;
			}
		}
		System.out.println("없는 책번호 입니다.");
		
	}

}
