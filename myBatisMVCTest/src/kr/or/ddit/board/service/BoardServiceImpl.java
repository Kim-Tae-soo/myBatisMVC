package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	private IBoardDao dao;
	
	private static BoardServiceImpl service;  // 1
	
	private BoardServiceImpl() { 			// 2
		dao = BoardDaoImpl.getInstance();
	}
	
	public static BoardServiceImpl getInstance() {  // 3
		if(service==null) service = new BoardServiceImpl();
		return service;
	}

	@Override
	public int insertBoard(BoardVO boardVo) {
		// TODO Auto-generated method stub
		return dao.insertBoard(boardVo);
	}

	@Override
	public int deleteBoard(int boardNo) {
		// TODO Auto-generated method stub
		return dao.deleteBoard(boardNo);
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		// TODO Auto-generated method stub
		return dao.updateBoard(boardVo);
	}

	@Override
	public BoardVO getBoard(int boardNo) {
		// 조회수를 증가하고 그 결과를 검사
		if(setCountIncrement(boardNo)==0) {
			// 조회수 증가 작업이 실패하면 해당 게시글이 없다는 의미이므로 null을 반환한다.
			return null;
		}
		return dao.getBoard(boardNo);
	}

	@Override
	public List<BoardVO> getAllBoard() {
		// TODO Auto-generated method stub
		return dao.getAllBoard();
	}

	@Override
	public List<BoardVO> getSearchBoard(String title) {
		// TODO Auto-generated method stub
		return dao.getSearchBoard(title);
	}

	@Override
	public int setCountIncrement(int boardNo) {
		// TODO Auto-generated method stub
		return dao.setCountIncrement(boardNo);
	}

}
