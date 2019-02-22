package kr.co.bit_camp.usercontroller;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.ProjectMapper;
import kr.co.bit_camp.dao.UserMapper;
import kr.co.bit_camp.db.MyAppSqlConfig;
import kr.co.bit_camp.vo.Project;
import kr.co.bit_camp.vo.User;


public class RecordController implements Controller {
	
	
	public void mainSerivceMenu() {
		
		
	}

	public void service() throws Exception {
    
		while(true) {
			Controller ctrl = null;
		System.out.println("학생 성적 관리");
		System.out.println("---------");
		
		switch(RecordMain()) {
		case 1:
	    ctrl = new RecordListController();
		break;
		case 2:
		ctrl = new RecordInsertController();	
		break;
		case 3:
		ctrl = new 	RecordUpdateController();
		break;
		case 4:
		ctrl = new RecordDeleteController();	
		break;
		case 0:
		exit();
		break;
		
		default: 
		System.out.println("잘못된 메뉴입니다.");
		System.out.println("다시 선택해주세요.");
		break;
		
		}//switch
		if(ctrl != null) {
			ctrl.service();
		}
		
		}//while
		
}//service

	


	public int RecordMain() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1. 학생 성적 조회");
		System.out.println("2. 학생 성적 추가");
		System.out.println("3. 학생 성적 수정");
		System.out.println("4. 학생 성적 삭제");
		System.out.println("0. 종료");

	    System.out.printf("메뉴중 처리할 항목을 선택하세요");
	    return Integer.parseInt(sc.nextLine());  
	
	}//main		

//0을 눌렀을때 종료 메뉴
	public void exit() {
		System.out.println("성적 시스템을 종료합니다.");
		System.exit(0);
	}











	
	
	


}
