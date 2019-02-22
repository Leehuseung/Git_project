package kr.co.bit_camp.dao;

import java.util.List;

import kr.co.bit_camp.vo.Project;
import kr.co.bit_camp.vo.ProjectMember;

public interface ProjectMapper {
	void insertProject(Project project);
	List<Project> selectProject();
	
}
