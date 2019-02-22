package kr.co.bit_camp.usercontroller;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import kr.co.bit_camp.controller.Controller;
import kr.co.bit_camp.dao.ProjectMemberMapper;
import kr.co.bit_camp.db.MyAppSqlConfig;
import kr.co.bit_camp.vo.ProjectMember;

public class RecordDeleteController implements Controller {
//성적 삭제

	SqlSession session = MyAppSqlConfig.getSqlSession();
	ProjectMemberMapper pmapper = session.getMapper(ProjectMemberMapper.class);//나중에 list 가져오기
	Scanner sc = new Scanner(System.in); //성적 목록 삭제하기
	
	public void service()  {
		System.out.println();
		System.out.println("학생 목록");
		System.out.println("---------------------------");
		List<ProjectMember> sList = pmapper.selectMemberNoUserNameProjectName();
		System.out.println("프로젝트 번호	학생이름	프로젝트 이름");
		for(int j =0; j<sList.size();j++) {
			
			System.out.printf("%d	%s	%s   \r ", sList.get(j).getProject_member_no(),
													sList.get(j).getUser_name(),
													sList.get(j).getProject_name()
		   );
	    }//for	

		
		System.out.println("삭제할 프로젝트성적의 고유번호를 입력하세요");
		int deleteNum = Integer.parseInt(sc.nextLine());
		pmapper.deleteProjectMember(deleteNum);
		System.out.println("해당번호의 프로젝트 성적이 삭제되었습니다.");
		
	}//service
}
