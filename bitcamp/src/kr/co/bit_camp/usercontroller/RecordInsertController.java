package kr.co.bit_camp.usercontroller;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.ClassInfoMapper;
import kr.co.bit_camp.dao.ProjectMapper;
import kr.co.bit_camp.dao.ProjectMemberMapper;
import kr.co.bit_camp.dao.UserMapper;
import kr.co.bit_camp.db.MyAppSqlConfig;
import kr.co.bit_camp.vo.ClassInfo;
import kr.co.bit_camp.vo.Project;
import kr.co.bit_camp.vo.ProjectMember;
import kr.co.bit_camp.vo.User;

public class RecordInsertController implements Controller {
	Scanner sc = new Scanner(System.in);
	
	
	public void service()  {
		
		SqlSession session = MyAppSqlConfig.getSqlSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		List<User> uList = mapper.selectUserNoName();
		
		ProjectMemberMapper pmapper = session.getMapper(ProjectMemberMapper.class);//나중에 인서트 추가
		
		ProjectMember pjm = new ProjectMember();
		
		System.out.println("보기 목록");
		System.out.println("---------------------------");
		for(int i =0; i<uList.size();i++) {
			System.out.printf("%d.  %s\r", uList.get(i).getNo(), uList.get(i).getName() );
		}//for
		System.out.println("---------------------------");
			
		System.out.println("성적을 추가할 회원의 번호를 입력하세요.");
			int num = Integer.parseInt(sc.nextLine());
			pjm.setNo(num);
			
			
			System.out.print("성적을 추가할 회원의 이름을 입력하세요: ");
	         String userName = sc.nextLine();
	         pjm.setUser_name(userName);
	         System.out.println("회원의 이름과 번호가 등록되었습니다");
	         System.out.println();
		
			//완료
			
			//반 보기목록
			System.out.println("반 목록");
			System.out.println("---------------------------");
			ClassInfoMapper cmapper = session.getMapper(ClassInfoMapper.class);
			List<ClassInfo> cList = cmapper.selectClassInfo();
			for(int j =1; j<cList.size();j++) {
				System.out.printf("%d.  %s%n", cList.get(j).getClassInfoNo(), cList.get(j).getClassInfoName() );
		
			}//for
			System.out.println("---------------------------");
			
			//반 보기
			
			//반 선택 시작
			
			
		System.out.println("성적을 입력할 반의 번호를 선택하세요.");
		int classNo = Integer.parseInt(sc.nextLine());
		pjm.setClass_no(classNo);
		
		
		
		for(int k=0; k<cList.size();k++) {
		pjm.setClass_name(cList.get(classNo-1).getClassInfoName());
		}
		System.out.println("반 번호와 이름이 등록되었습니다.");
		
		
		//반 선택 완료
	
	//프로젝트 선택시작
		System.out.println();
		System.out.println("프로젝트 목록");
		System.out.println("---------------------------");
		//프로젝트 목록
		ProjectMapper pjmapper = session.getMapper(ProjectMapper.class);
		List<Project> pList = pjmapper.selectProject();
		for(int m=0; m< pList.size();m++) {
			System.out.printf("%d.  %s%n", pList.get(m).getProject_no(), pList.get(m).getProject_name() );
	
			
		}//for
		System.out.println("---------------------------");
		System.out.println("프로젝트를 번호를 선택하세요");
		int projectNo = Integer.parseInt(sc.nextLine());
		pjm.setProject_no(projectNo);	
		
		for(int n=0; n<pList.size();n++) {
			pjm.setProject_name(pList.get(projectNo-1).getProject_name());
			}
		System.out.println("프로젝트 번호와 이름이 등록되었어요");
		//프로젝트 선택 완료
		
		//점수 선택
		System.out.println();
		System.out.println("학생의 성적을 입력하세요");
		String grade= null;
		
		int score = Integer.parseInt(sc.nextLine());
	    pjm.setProject_score(score);
	
	
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
       
	pmapper.insertProjectMember(pjm);
	System.out.println("성적이 입력되었습니다.");
	}//service

	
}
