//비밀번호 변경 기능
package kr.co.bit_camp.usercontroller;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.UserMapper;
import kr.co.bit_camp.db.MyAppSqlConfig;
import kr.co.bit_camp.vo.User;

public class UserInformationController implements Controller{
	Scanner sc = new Scanner(System.in);
	private void exit() {
		System.out.println("비트캠프를 종료합니다.");
		System.exit(0);
	}
	private UserMapper mapper;
	public String input(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}
	public UserInformationController() {
		SqlSession session = MyAppSqlConfig.getSqlSession();
		mapper = session.getMapper(UserMapper.class);
	}
	
	public void changePassword() {
		System.out.println("변경할 비밀번호를 입력하세요.");
		String passA = input("비밀번호 : ");
		String passB = input("비밀번호 확인 : ");
		if(passA.equals("")||passB.equals("")) {
			System.out.println("비밀번호를 입력하세요");
			return;
		}
		
		if(passA.length()>12||passB.length()>12||passA.length()<4||passB.length()<4) {	    //글자수 체크		
			System.out.println("비밀번호는 글자수를 확인하세요.");
			return;
		}
		
		if(passA.equals(passB)) {
			User user = new User();
			user.setId(LogInController.logInUser.getId());
			user.setPassword(passA);
			mapper.updatePassword(user);
			System.out.println("비밀번호 변경이 완료되었습니다.");
			return;
		}

		System.out.println("비밀번호가 일치하지 않습니다.");
	}
	
	
	public void service() {
		System.out.println("회원정보");
		
		
		try {
			
			while(true) {
				Controller ctrl = null; 
				System.out.println("---------------------------------");
				System.out.println("1.비밀번호변경");
				System.out.println("2.쪽지");
				System.out.println("3.게시글 보기");
				System.out.println("4.댓글 보기");
				System.out.println("5.뒤로");
				System.out.println("---------------------------------");
				System.out.print("메뉴중 처리할 항목을 선택하세요 : ");
				int no = Integer.parseInt(sc.nextLine());
				
				switch(no) {
				case 1 :
					changePassword();
					break;
				case 2 :
					ctrl = new MessageController();
					break;
				case 3 :
					ctrl = new UserBoardController();
					break;
				case 4 :
 					ctrl = new UserCommentController();
					break;
				case 5 :
					System.out.println("메인메뉴로 이동합니다.");
					return;
				default :
					System.out.println("없는번호입니다.");
				System.out.println("번호를 확인해 주세요.");
				}
				if (ctrl != null) {
					ctrl.service();
				}
			}
		}catch(Exception e){
		e.printStackTrace();
		}
	}
}
