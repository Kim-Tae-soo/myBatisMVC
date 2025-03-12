package kr.or.ddit.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.dao.IMemberDao;
import kr.or.ddit.dao.MemberDaoImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	private IMemberDao dao;		// DAO객체가 저장될 변수 선언
	
	// 1번
	private static MemberServiceImpl service;
	
	// 2번
	private MemberServiceImpl() {
//	public MemberServiceImpl() {
//		dao = new MemberDaoImpl();		// DAO 객체 생성
		dao = MemberDaoImpl.getInstance();		// DAO 객체 생성
	}
	
	// 3번
	public static MemberServiceImpl getInstance() {
		if(service==null) service = new MemberServiceImpl();
		return service;
	}
	
	
	@Override
	public int insertMember(MemberVO memVo) {
		// TODO Auto-generated method stub
		return dao.insertMember(memVo);
	}

	@Override
	public int deleteMember(String memId) {
		// TODO Auto-generated method stub
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVo) {
		// TODO Auto-generated method stub
		return dao.updateMember(memVo);
	}

	@Override
	public List<MemberVO> getAllMember() {
		// TODO Auto-generated method stub
		return dao.getAllMember();
	}

	@Override
	public int getMemberIdCount(String memId) {
		// TODO Auto-generated method stub
		return dao.getMemberIdCount(memId);
	}


	@Override
	public int updateMember2(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return dao.updateMember2(paramMap);
	}

}
