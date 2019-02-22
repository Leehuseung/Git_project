package kr.co.bit_camp.controller;

import java.util.Scanner; 

import kr.co.bit_camp.usercontroller.LogInController;
import kr.co.bit_camp.usercontroller.UserController;
import kr.co.bit_camp.usercontroller.UserController;

public class BitController {
	
	private void exit() {
		System.out.println("비트캠프를 종료합니다.");
		System.exit(0);
	}
	
	public void service() {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("비트캠프");
			
			while(true) {
				Controller ctrl = null; 
				System.out.println("메인메뉴");
				System.out.println("---------------------------------");
				System.out.println("---------------------------------");
				System.out.println("1.로그인");
				System.out.println("2.회원가입");
				System.out.println("3.자유게시판");
				System.out.println("4.출결관리");
				System.out.println("5.학점관리");
				System.out.println("6.도서관");
				System.out.println("7.종료");
				System.out.println("---------------------------------");
				System.out.print("메뉴중 처리할 항목을 선택하세요 : ");
				int no = Integer.parseInt(sc.nextLine());
				
				switch(no) {
				case 1 :
					ctrl = new LogInController();
					break;
				case 2 :
					ctrl = new UserController();
					break;
				case 3 : 
						System.out.println("로그인 후 이용가능합니다.");
						break;
				case 4 :
						System.out.println("로그인 후 이용가능합니다.");
						break;
				case 5 :
						System.out.println("로그인 후 이용가능합니다.");
						break;
				case 6 :
						System.out.println("로그인 후 이용가능합니다.");
						break;
				case 7 :
					exit();
					default :
						System.out.println("없는번호입니다.");
						System.out.println("번호를 확인해 주세요.");
				}
				if (ctrl != null) {
					ctrl.service();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			
		}
	}
}
