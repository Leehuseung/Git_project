package kr.co.bit_camp.dao;

import java.util.Date;
import java.util.List;

import kr.co.bit_camp.vo.Attendence;

public interface AttendMapper {		
	void insertAttendence(Attendence attend);	
	void insertManager(Attendence attend);	
	void updateAttendence(Attendence attend);
	void deleteAttendence(int attNo);
	Attendence selectAttendence(Attendence attend);
	int selectAttendenceCount(Attendence attend); // 총 출석
	List<Attendence> selectAttendenceList(Attendence attend); // 월별 출결현황 리스트
	List<Attendence> selectAttendenceList2(Attendence attend); // 출석 데이터 가져오기 출석값만 있는거 퇴실찍기전에 사용
	int selectAttendenceMonthCount1(Attendence attend); // 월별 출석
	int selectAttendenceMonthCount2(Attendence attend); // 월별 지각
	int selectAttendenceMonthCount3(Attendence attend); // 월별 조퇴
	int selectAttendenceMonthCount4(Attendence attend); // 월별 결석
	
}
