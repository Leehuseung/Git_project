package kr.co.bit_camp.attcontroller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.AttendMapper;
import kr.co.bit_camp.db.MyAppSqlConfig;
import kr.co.bit_camp.usercontroller.LogInController;
import kr.co.bit_camp.vo.Attendence;

public class AttController implements Controller {
	Scanner sc = new Scanner(System.in);
	Calendar c = Calendar.getInstance();
	private AttendMapper mapper;
	private Attendence att = new Attendence();
	Date d = null;
	public AttController() {
		SqlSession session = MyAppSqlConfig.getSqlSession();
		mapper = session.getMapper(AttendMapper.class);
	}
	public int choiceMenu() {
		System.out.println("출결관리");
		System.out.println("---------------------------------");		
		System.out.println("1. 입실");
		System.out.println("2. 퇴실");
		System.out.println("3. 나의 출결 현황");
		System.out.println("4. 뒤로");
		System.out.println("---------------------------------");
		System.out.print("메뉴 중 처리할 항목을 선택하세요 : ");		
		return Integer.parseInt(sc.nextLine());		
	}
	public void service() throws Exception {		
		while(true) {			
			switch(choiceMenu()) {		
			case 1:	
				enter();
				break;				
			case 2:
				leave();
				break;				
			case 3:
				status();
			case 4:
				return;
			default:
				System.out.println("없는번호입니다.");
				System.out.println("메뉴를 확인해 주세요.");
			}
		}
	}
	public int enterMenu() {	
		System.out.println("입실하시겠습니까?");
		System.out.println("---------------------------------");
		System.out.println("1. 예");
		System.out.println("2. 아니요");		
		System.out.print("메뉴 중 처리할 항목을 선택하세요 : ");
		return Integer.parseInt(sc.nextLine());		
	}
	public void enteryes() {
		try {
			String start = "0940";
			SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
			Date start_time;
			start_time = sdf.parse(start);
			att.setAttEnterTime(new Date());
			if(att.getAttEnterTime() == null) {
				att.setAttStatus(4);
			}
			else if(att.getAttEnterTime().getTime() <= start_time.getTime()) {
				att.setAttStatus(1);						
			}else {
				att.setAttStatus(2);
				System.out.println("지각처리 되었습니다.");
				System.out.println("조퇴 및 지각 5번 이상 시 결석 1회로 처리됩니다.");
				System.out.println("---------------------------------");
			}
				
		} catch (ParseException e) {					
			e.printStackTrace();
		}								
		System.out.println("나의 출결 현황에 등록되었습니다.");
		System.out.println("09시 40분 이후부터 지각처리됩니다.");
		System.out.println("---------------------------------");
		att.setNo(LogInController.logInUser.getNo());
		mapper.insertAttendence(att);
		return;		
	}
	public void enter() {
		while(true) {
			switch(enterMenu()) {
			case 1:				
				enteryes();
				return;				
			case 2:
				return;				
			default:
				System.out.println("없는번호입니다.");
				System.out.println("메뉴를 확인해 주세요.");				
			}	
		}			
	}
	public int leaveMenu() {	
		System.out.println("퇴실하시겠습니까?");
		System.out.println("---------------------------------");
		System.out.println("1. 예");
		System.out.println("2. 아니요");		
		System.out.print("메뉴 중 처리할 항목을 선택하세요 : ");
		return Integer.parseInt(sc.nextLine());		
	}	
	public void leaveyes() {
		try {
			String end = "1820";
			SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
			Date end_time;
			end_time = sdf.parse(end);
			att.setAttEnterTime(new Date());
			att.setAttLeaveTime(new Date());
			att.setNo(LogInController.logInUser.getNo());
			
			Attendence att2=null;
			List<Attendence> list =  mapper.selectAttendenceList2(att);
			for(int i = 0 ;i < list.size() ; i++) {
				att2 = list.get(i);
				Date d = att.getAttLeaveTime();
				int k = d.getDate();
				Date d2 = att2.getAttEnterTime();
				int j = d2.getDate();
				if(k==j) {
					att.setAttNo(att2.getAttNo());
					break;
				}
			}
			
			
			
			if(att2.getAttStatus()==2&&att.getAttLeaveTime().getTime()>=end_time.getTime()) {
				att.setAttStatus(2);
			}else if(att.getAttLeaveTime().getTime() >= end_time.getTime()) {
				att.setAttStatus(1);
			}
			else {
				att.setAttStatus(3);
				System.out.println("조퇴처리 되었습니다.");
				System.out.println("조퇴 및 지각 5번 이상 시 결석 1회로 처리됩니다.");
			}
			
		} catch (ParseException e) {					
			e.printStackTrace();
		}								
		System.out.println("나의 출결 현황에 등록되었습니다.");
		System.out.println("18시 20분 이전은 조퇴처리됩니다.");
		att.setNo(LogInController.logInUser.getNo());
		mapper.updateAttendence(att);		
	}
	public void leave() {
		while(true) {
			switch(leaveMenu()) {
			case 1: 
				leaveyes();
				return;			
			case 2:
				return;
			default:
				System.out.println("없는번호입니다.");
				System.out.println("메뉴를 확인해 주세요.");
			}
		}
	}
	public int statusMenu() {		
		System.out.println("1. 월별 출석 현황");
		System.out.println("2. 전체 출석 현황");
		System.out.println("3. 뒤로");
		System.out.print("메뉴 중 처리할 항목을 선택하세요 : ");
		return Integer.parseInt(sc.nextLine());
	}
	public int status(int k,List<Attendence> list) {
		int status = 0;
		for(int i =0 ; i<list.size();i++) {
			Attendence a = list.get(i);
			Date d = a.getAttLeaveTime();
			int day1 = d.getDate();
			if(k==day1) {
				status = a.getAttStatus();
				return status;
			}
		}
		return status;
	}
	
	public void agoMonth(Date date) {
		att.setAttLeaveTime(date);
		att.setNo(LogInController.logInUser.getNo());		
		List<Attendence> list = mapper.selectAttendenceList(att);
		
		System.out.printf("%d년 %2d월\n", c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1);
		System.out.println("----------------------------------------------");
		System.out.println("일        월         화         수         목         금         토");
		int lastDate = c.getActualMaximum(Calendar.DAY_OF_MONTH); 
		c.set(Calendar.DAY_OF_MONTH, 1); 
		int dayWeek = c.get(Calendar.DAY_OF_WEEK); 					
		int nlCnt = 0;  
		for (int j = 1; j < dayWeek; j++) {
			System.out.print("     ");
			nlCnt++;
		}	   
		 
		for (int k = 1; k <= lastDate; k++) {			
				System.out.printf("%-3d",k );
				Attendence a = list.get(list.size()-1);
				Date d = a.getAttLeaveTime();
				int day = d.getDate();
				if(k<= day) {
					
				switch(status(k,list)) {
					case 1 :
						System.out.print((++nlCnt % 7 == 0) ? "\n"  : 
							             (nlCnt % 7 == 1 ?"  " : "O "));
						break;
					case 2 :
						System.out.print((++nlCnt % 7 == 0) ? "\n"  :  
							(nlCnt % 7 == 1 ?"  " : "△ "));
						break;
					case 3 :
						System.out.print((++nlCnt % 7 == 0) ? "\n"  :  
							(nlCnt % 7 == 1 ?"  " : "▲ "));
						break;
					case 4 :
						System.out.print((++nlCnt % 7 == 0) ? "\n"  : 
							(nlCnt % 7 == 1 ?"  " : "X "));
						break;
					case 0 : 
						System.out.print((++nlCnt % 7 == 0) ? "\n"  : "  ");
						break;
					
					}
				}else{
					System.out.print((++nlCnt % 7 == 0) ? "\n"  : "  ");
					}
			}
			
			System.out.println();
	}
	public void listMonth() {
		d = new Date();
		att.setAttLeaveTime(d);
		att.setNo(LogInController.logInUser.getNo());		
		List<Attendence> list = mapper.selectAttendenceList(att);
		
		System.out.printf("%d년 %2d월\n", c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1);
		System.out.println("----------------------------------------------");
		System.out.println("일        월         화         수         목         금         토");
		int lastDate = c.getActualMaximum(Calendar.DAY_OF_MONTH); 
		c.set(Calendar.DAY_OF_MONTH, 1); 
		int dayWeek = c.get(Calendar.DAY_OF_WEEK); 					
		int nlCnt = 0;  
		for (int j = 1; j < dayWeek; j++) {
			System.out.print("     ");
			nlCnt++;
		}	   
		 
		for (int k = 1; k <= lastDate; k++) {			
				System.out.printf("%-3d",k );
				Attendence a = list.get(list.size()-1);
				Date d = a.getAttLeaveTime();
				int day = d.getDate();
				if(k<= day) {
					
				switch(status(k,list)) {
					case 1 :
						System.out.print((++nlCnt % 7 == 0) ? "\n"  : 
							             (nlCnt % 7 == 1 ?"  " : "O "));
						break;
					case 2 :
						System.out.print((++nlCnt % 7 == 0) ? "\n"  :  
							(nlCnt % 7 == 1 ?"  " : "△ "));
						break;
					case 3 :
						System.out.print((++nlCnt % 7 == 0) ? "\n"  :  
							(nlCnt % 7 == 1 ?"  " : "▲ "));
						break;
					case 4 :
						System.out.print((++nlCnt % 7 == 0) ? "\n"  : 
							(nlCnt % 7 == 1 ?"  " : "X "));
						break;
					case 0 : 
						System.out.print((++nlCnt % 7 == 0) ? "\n"  : "  ");
						break;
					
					}
				}else{
					System.out.print((++nlCnt % 7 == 0) ? "\n"  : "  ");
					}
			}
			
			System.out.println();
		}
	public void month() {
		System.out.printf("%d년 %2d월\n", c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1);
		System.out.println("----------------------------------------------");
		System.out.println("일\t월\t화\t수\t목\t금\t토");
		int lastDate = c.getActualMaximum(Calendar.DAY_OF_MONTH);  
		
		c.set(Calendar.DAY_OF_MONTH, 1);  
		int dayWeek = c.get(Calendar.DAY_OF_WEEK);  
		
		c.add(Calendar.MONTH,-1); 
		
		int nlCnt = 0;  
		for (int j = 1; j < dayWeek; j++) {
			System.out.print("\t");
			nlCnt++;
		}
		for (int k = 1; k <= lastDate; k++) {
			System.out.print(k + ((++nlCnt % 7 == 0) ? "\n" : "\t"));
		}
		System.out.println();
		System.out.println("---------------------------------------------");		
		c.add(Calendar.MONTH,+1);
		
	}
	
	
	public void calendar() {
		while(true) {			
			System.out.print("항목을 선택하세요(1. 현재달 출석현황  2. 이전달 출석현황  3. 다음달 출석현황  4. 뒤로) :");
			int num =  Integer.parseInt(sc.nextLine());
			switch(num) {
			case 1:			
				att.setNo(LogInController.logInUser.getNo());
				d = new Date();
				c = Calendar.getInstance();
				d=c.getTime();	
				att.setAttEnterTime(d);
				
				listMonth();
				
				System.out.println("----------------------------------------------");
			
				c.set(c.get(Calendar.YEAR),	c.get(Calendar.MONTH), 1);
				System.out.println();
				System.out.println("출석 : ○");
				System.out.println("지각 : △");
				System.out.println("조퇴 : ▲");
				System.out.println("결석 : X");
				
				System.out.println("출석일 수 : " + mapper.selectAttendenceMonthCount1(att));
				System.out.println("지각일 수 : " + mapper.selectAttendenceMonthCount2(att));
				System.out.println("조퇴일 수 : " + mapper.selectAttendenceMonthCount3(att));
				System.out.println("결석일 수 : " + mapper.selectAttendenceMonthCount4(att));
				break;						
			case 2:	
				att.setNo(LogInController.logInUser.getNo());
				d = new Date();
				c.add(Calendar.MONTH, -1);
				
				d=c.getTime();	
				att.setAttEnterTime(d);
				int agoDay = d.getMonth();
				if(agoDay == 0)	agoMonth(d);
				else month();
				
				System.out.println();
				System.out.println("출석 : ○");
				System.out.println("지각 : △");
				System.out.println("조퇴 : ▲");
				System.out.println("결석 : X");
				
				System.out.println("출석일 수 : " + mapper.selectAttendenceMonthCount1(att));
				System.out.println("지각일 수 : " + mapper.selectAttendenceMonthCount2(att));
				System.out.println("조퇴일 수 : " + mapper.selectAttendenceMonthCount3(att));
				System.out.println("결석일 수 : " + mapper.selectAttendenceMonthCount4(att));				
				
				break;
			case 3:
				att.setNo(LogInController.logInUser.getNo());
				d = new Date();
				c.add(Calendar.MONTH, +1);
				d=c.getTime();
				att.setAttEnterTime(d);
				month();
				System.out.println();
				System.out.println("출석 : ○");
				System.out.println("지각 : △");
				System.out.println("조퇴 : ▲");
				System.out.println("결석 : X");				

				System.out.println("출석일 수 : " + mapper.selectAttendenceMonthCount1(att));
				System.out.println("지각일 수 : " + mapper.selectAttendenceMonthCount2(att));
				System.out.println("조퇴일 수 : " + mapper.selectAttendenceMonthCount3(att));
				System.out.println("결석일 수 : " + mapper.selectAttendenceMonthCount4(att));				
				break;
			case 4:
				return;
			default:
				System.out.println("없는번호입니다.");
				System.out.println("메뉴를 확인해 주세요.");
			}
		}
	}	

	public void status() {	
		while(true) {			
			switch(statusMenu()) {
			case 1:				
				calendar();
				break;																
			case 2:				
				att.setNo(LogInController.logInUser.getNo());
				System.out.println("전체 출석 현황");
				System.out.println("---------------------------------");
				att.setAttStatus(1);
				System.out.printf("총 출석일 수 : %d%n" ,mapper.selectAttendenceCount(att) );
				att.setAttStatus(2);
				System.out.printf("총 지각일 수 : %d%n", mapper.selectAttendenceCount(att));
				att.setAttStatus(3);
				System.out.printf("총 조퇴 수 : %d%n", mapper.selectAttendenceCount(att));
				att.setAttStatus(4);
				System.out.printf("총 결석 수 : %d%n", mapper.selectAttendenceCount(att));
				System.out.println("결석 10번 이상시 강제 수강철회 됩니다.");
				System.out.println("조퇴 및 지각 5번 이상 시 결석 1회로 처리됩니다.");
				System.out.printf("총 결석 : %d/10%n", mapper.selectAttendenceCount(att));
				System.out.println("---------------------------------");
				break;
			case 3:
				return;
			default:
				System.out.println("없는번호입니다.");
				System.out.println("메뉴를 확인해 주세요.");
			}
		}
	}
}
