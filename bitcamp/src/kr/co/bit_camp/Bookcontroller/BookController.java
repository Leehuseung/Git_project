package kr.co.bit_camp.Bookcontroller;


import java.util.Scanner;


import kr.co.bit_camp.controller.Controller;

import kr.co.bit_camp.vo.User;




public class BookController implements Controller{
	public Scanner sc = new Scanner(System.in);
	private User logInUser = null;
	
	public BookController(User logInUser){
		this.logInUser = logInUser;
	}
	
	//관리자 도서관 페이지
     public int choiceMenu(){
		
		System.out.println("---------------------------------");
		System.out.println("---------------------------------");
		System.out.println("1.도서 목록");
		System.out.println("2.도서 추가");
		System.out.println("3.도서 삭제");
		System.out.println("4.희망 도서 목록");
		System.out.println("5.이달의 도서왕");
		System.out.println("0.메인화면");
		System.out.println("--------------");
		System.out.print("메뉴 중 처리할 항목을 선택하세요:");
		return Integer.parseInt(sc.nextLine());
	}

   //유저 도서관 페이지
	public int choiceMenu1(){
		
		
		System.out.println("---------------------------------");
		System.out.println("---------------------------------");
		System.out.println("1.베스트셀러");
		System.out.println("2.도서 대여");
		System.out.println("3.도서 반납");
		System.out.println("4.도서 검색");
		System.out.println("5.희망 도서 신청");
		System.out.println("6.현황(반납 날짜,빌린 권수)");
		System.out.println("7.이달의 도서왕");
		System.out.println("0.메인화면");
		System.out.println("--------------");
		System.out.print("메뉴 중 처리할 항목을 선택하세요:");
		return Integer.parseInt(sc.nextLine());
		
	}
	
	public void service() {
		System.out.println(logInUser.getNo());
		if(logInUser.getNo()==1){
		try{
			System.out.println("도서관");
		while(true){
			Controller ctrl = null;
			switch(choiceMenu()){
			case 1:
				ctrl = new BookListController();
				break;
			case 2:
				ctrl = new BookInsertController();
				break;
			case 3:
				ctrl = new BookDeleteController();
				break;
			case 4:
				ctrl = new HopeBookListController();
				break;
			case 5:
				 ctrl = new BookRentBestController();
				break;
			case 0:
				return;
			default :
				System.out.println("없는번호입니다.");
				System.out.println("메뉴를 확인해 주세요.");
				break;
			}
			
				if (ctrl != null) {
					ctrl.service();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		}else{
			try{
			  while(true){
				System.out.println("도서관");
				Controller ctrl = null;
				switch(choiceMenu1()){
				 case 1:
					ctrl = new BookBestListController();
					break;
				 case 2:
					ctrl = new BookRentController(logInUser);
					break;
				 case 3:
					 ctrl = new BookReturnController(logInUser);
					break;
				 case 4:
					 ctrl = new BookDetailController();
					break;
				 case 5:
					 ctrl = new BookHopeController(logInUser);
					break;
				 case 6:
					 ctrl = new BookRentListController(logInUser);
					break;
				 case 7:
					 ctrl = new BookRentBestController();
					 break;
				 case 0:
					 return;

				 default :
					System.out.println("없는번호입니다.");
					System.out.println("메뉴를 확인해 주세요.");
					break;
					}
					
						if (ctrl != null) {
							ctrl.service();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		
	}
	
	
	

}
