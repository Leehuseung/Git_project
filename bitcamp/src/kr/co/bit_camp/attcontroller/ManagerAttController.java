package kr.co.bit_camp.attcontroller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.AttendMapper;
import kr.co.bit_camp.dao.UserMapper;
import kr.co.bit_camp.db.MyAppSqlConfig;
import kr.co.bit_camp.usercontroller.LogInController;
import kr.co.bit_camp.vo.Attendence;
import kr.co.bit_camp.vo.User;

public class ManagerAttController  implements Controller{
	Scanner sc = new Scanner(System.in);
	private UserMapper mapper2;
	private AttendMapper mapper;
	Attendence att = new Attendence();
	public ManagerAttController() {
		SqlSession session = MyAppSqlConfig.getSqlSession();
		mapper = session.getMapper(AttendMapper.class);
		mapper2 = session.getMapper(UserMapper.class);
	}	
	public void service() {
		while(true) {
			System.out.println("관리자 화면 ");
			System.out.println("---------------------------------");
			System.out.println("1. 결석 처리");
			System.out.println("2. 뒤로");
			System.out.print("메뉴중 처리할 항목을 선택하세요 : ");	
			int manager = Integer.parseInt(sc.nextLine());
			
			switch(manager) {
			case 1:				
				List<User> list = mapper2.selectUserList();				
				System.out.println("결석 처리");
				System.out.println("---------------------------------");
				System.out.println("번호\t아이디\t이름");
				for(int i = 1; i < list.size() ; i++) {
					User user = list.get(i);
					System.out.printf("%d\t%-8s%-8s%n",i,user.getId(),user.getName());
				}
				System.out.println("---------------------------------");
				System.out.print("결석 처리할 학생의 번호를 입력하세요 : ");
				int student = Integer.parseInt(sc.nextLine());
				if(student >=list.size()) {
					System.out.println("입력 번호를 확인해주세요.");
					return;
				}				
				
				
				
				
				
				
				User user = list.get(student); //학생 불러온다
				
				 att.setNo(user.getNo());  //att에 학생번호 입력		
				 
				    Date d = new Date();
				    d.setHours(3);			//d에 3시 입력
					att.setAttLeaveTime(d); // att에 3시 입력(3시는 결석임)
					att.setAttStatus(4);    // att에 4입력 4는 결석임
					             
					Attendence att2=new Attendence();	//비교할수있는 att2 선언
					//att2에는 not null인값을 세팅해줘 야대
					//null이아닌거는 no,
					att2.setNo(user.getNo());
					att2.setAttLeaveTime(d);
					List<Attendence> list2 =  mapper.selectAttendenceList(att);
					for(int i = 0 ;i < list2.size(); i++) {
						att2 = list2.get(i);
						if(att2.getAttEnterTime()==null) {
							System.out.println("이미 결석처리 되었습니다.");
						  
							return;
						}
					}
					mapper.insertManager(att);	
					System.out.println("결석처리 되었습니다.");
				break;
			case 2:
				return;
				
				
			}
		}
		
	
		
	}
	
	
	
}
