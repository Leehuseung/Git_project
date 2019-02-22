package kr.co.bit_camp.dao;

import java.util.List;


import kr.co.bit_camp.vo.ProjectMember;

public interface ProjectMemberMapper {

	
	void insertProjectMember(ProjectMember projectMember);
	List<ProjectMember> selectProjectMember();
	void updateProjectMember(ProjectMember projectMember);
	void deleteProjectMember(int project_member_no);
	List<ProjectMember> recordProjectMember(ProjectMember projectMember);
	List<ProjectMember> selectNoClassNameUserName();
	List<ProjectMember> selectMemberNoUserNameProjectName();
	List<ProjectMember> UserRecordOnly(int userNum);
}
