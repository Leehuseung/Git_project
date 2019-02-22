package kr.co.bit_camp.usercontroller;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.ClassInfoMapper;
import kr.co.bit_camp.dao.ProjectMemberMapper;
import kr.co.bit_camp.db.MyAppSqlConfig;
import kr.co.bit_camp.vo.ClassInfo;
import kr.co.bit_camp.vo.ProjectMember;

public class RecordUpdateController implements Controller {
	SqlSession session = MyAppSqlConfig.getSqlSession();
	ProjectMemberMapper pmapper = session.getMapper(ProjectMemberMapper.class);//나중에 업데이트 추가
	Scanner sc = new Scanner(System.in);

	public void service() {
		//학생 목록 가져오기
		System.out.println("학생목록");
		System.out.println("학생 목록");
		System.out.println("---------------------------");
		List<ProjectMember> pList = pmapper.selectMemberNoUserNameProjectName();
		System.out.println("고유번호	이름	 프로젝트 이름");
		for(int j =0; j<pList.size();j++) {
			
			System.out.printf("%d	%s	%s\r ", pList.get(j).getProject_member_no(), 
					                        pList.get(j).getUser_name(),
					                        pList.get(j).getProject_name()
		);
	
		}//for
		System.out.println("---------------------------");
	
		ProjectMember pjm = new ProjectMember();
		System.out.println();
		System.out.println("성적을 수정할 학생의 고유번호를 입력하세요");
	    int uniqueNum = Integer.parseInt(sc.nextLine());
	    pjm.setProject_member_no(uniqueNum);
	    System.out.println(pjm.getProject_member_no());
	    System.out.println("학생의 성적을 입력하세요.");
	    int score = Integer.parseInt(sc.nextLine());

	    pjm.setProject_score(score);
	    
	    String grade=null;
	    
	    if(score<=100 && score>=90) {
	    	   grade = "A";
	       }
	       if(score<90 && score>=80) {
	    	   grade = "B";
	       }
	       if(score<80 && score>=70) {
	    	   grade = "C";
	       }
	       
		    pjm.setProject_grade(grade);
		
		    pmapper.updateProjectMember(pjm);
		    System.out.println("성적이 수정었습니다.");
		   System.out.println();
		    // update문 쿼리 고치기
	
		    
		    
	}//service











}//Controller
