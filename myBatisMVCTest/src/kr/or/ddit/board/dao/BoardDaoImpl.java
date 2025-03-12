package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MyBatisUtil;
import kr.or.ddit.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao {
	private static BoardDaoImpl dao;
	
	private BoardDaoImpl() { }
	
	public static BoardDaoImpl getInstance() {
		if(dao==null) dao = new BoardDaoImpl();
		return dao;
	}

	@Override
	public int insertBoard(BoardVO boardVo) {
		SqlSession session = null;
		int cnt = 0; // 반환값
		
		try {
			session = MyBatisUtil.getSqlSession();
			
			cnt = session.insert("board.insertBoard", boardVo);
			
			if(cnt>0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		SqlSession session = null;
		int cnt = 0; // 반환값
		
		try {
			session = MyBatisUtil.getSqlSession();
			
			cnt = session.delete("board.deleteBoard", boardNo);
			
			if(cnt>0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		SqlSession session = null;
		int cnt = 0; // 반환값
		
		try {
			session = MyBatisUtil.getSqlSession();
			
			cnt = session.update("board.updateBoard", boardVo);
			
			if(cnt>0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return cnt;
	}

	@Override
	public BoardVO getBoard(int boardNo) {
		SqlSession session = null;
		BoardVO bvo = null; 		// 반환값;
		
		try {
			session = MyBatisUtil.getSqlSession();
			
			bvo = session.selectOne("board.getBoard", boardNo);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return bvo;
	}

	@Override
	public List<BoardVO> getAllBoard() {
		SqlSession session = null;
		List<BoardVO> boadList = null; 		// 반환값;
		
		try {
			session = MyBatisUtil.getSqlSession();
			
			boadList = session.selectList("board.getAllBoard");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return boadList;
	}

	@Override
	public List<BoardVO> getSearchBoard(String title) {
		SqlSession session = null;
		List<BoardVO> boadList = null; 		// 반환값;
		
		try {
			session = MyBatisUtil.getSqlSession();
			
			boadList = session.selectList("board.getSearchBoard", title);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return boadList;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		SqlSession session = null;
		int cnt = 0; // 반환값
		
		try {
			session = MyBatisUtil.getSqlSession();
			
			cnt = session.update("board.setCountIncrement", boardNo);
			
			if(cnt>0) session.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		
		return cnt;
	}

}
