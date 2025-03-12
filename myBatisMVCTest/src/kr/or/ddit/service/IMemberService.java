package kr.or.ddit.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.MemberVO;

/**
 * Service 객체는 Dao에 만들어진 메서드를 원하는 작업에 맞게 호출하여
 * 실행하고 결과를 받아서 이것을 다시 Controller 에게 보내주는 역할을 한다.
 * 
 * 보통 Dao의 메서드 구조와 같다.
 */
public interface IMemberService {
	/**
	 * MemeberVO객체에 담겨진 자료를 DB에 insert하는 메서드
	 * 
	 * @param memVo insert할 자료가 저장된 MemberVO객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int insertMember(MemberVO memVo);
	
	/**
	 * 회원ID를 매개변수로 받아서 해당 회원 정보를 삭제하는 메서드
	 * 
	 * @param memId 삭제할 회원ID
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int deleteMember(String memId);
	
	/**
	 * 변경할 자료가 저장된 MemberVO객체를 받아서 DB에 update하는 메서드
	 * 
	 * @param memVo update할 정보가 저장된 MemberVO객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updateMember(MemberVO memVo);
	
	/**
	 * DB의 전체 회원 정보를 가져와 List에 담아서 반환하는 메서드
	 *  
	 * @return 검색한 결과가 저장된 List객체
	 */
	public List<MemberVO> getAllMember();
	
	/**
	 * 회원ID를 매개변수로 받아서 해당 회원ID의 개수를 반환하는 메서드
	 * 
	 * @param memId 검색할 회원ID
	 * @return 검색된 회원ID의 갯수
	 */
	public int getMemberIdCount(String memId);
	
	/**
	 * 매개변수로 받은 Map의 정보를 이용하여 회원 정보 중 원하는 컬럼을 수정하는 메서드
	 * 		Map의 key값 정보 ==> 회원ID(MEMID), 수정할컬럼명(FIELD), 변경될데이터(DATA)
	 * 
	 * @param paramMap 회원ID, 수정할컬럼명, 변경될데이터가 저장된 Map객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updateMember2( Map<String, String> paramMap);
	
	
}
