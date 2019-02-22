package kr.co.bit_camp.usercontroller;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.ProjectMemberMapper;
import kr.co.bit_camp.dao.UserMapper;
import kr.co.bit_camp.db.MyAppSqlConfig;
import kr.co.bit_camp.vo.ProjectMember;
import kr.co.bit_camp.vo.User;



public class UserSelectListController implements Controller  {
	
	SqlSession session = MyAppSqlConfig.getSqlSession();
	ProjectMemberMapper pmapper = session.getMapper(ProjectMemberMapper.class);//나중에 list 가져오기
	Scanner sc = new Scanner(System.in);
	
	public void service(){
		int userNum = LogInController.logInUser.getNo();
		
		Scanner sc = new Scanner(System.in);
		
		SqlSession session = MyAppSqlConfig.getSqlSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
	     List<User> list = mapper.selectUserList();
		
	     
	     		System.out.println();
				System.out.println("프로젝트 별나의성적");
				System.out.println("--------------------");

	     //회원번호  데이터베이스에 넘겨주기
		List<ProjectMember> rList = pmapper.UserRecordOnly(userNum);
				System.out.println("학생번호	반이름 	이름");//한번반 출력하게 i <1 
					for(int i=0; i<1;i++) {
				System.out.printf("%d  %s  %s \r", rList.get(i).getNo(),rList.get(i).getClass_name(),rList.get(i).getUser_name());
				}//for
				
				System.out.println("프로젝트 이름, 성적, 등급 ");
				for(int h=0; h<rList.size();h++) {
				System.out.printf("%s   %d    %s \r", rList.get(h).getProject_name(),
						                           rList.get(h).getProject_score(),
						                           rList.get(h).getProject_grade());
				}//for
		
		
	}//service

}
