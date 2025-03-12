package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.vo.BoardVO;

public interface IBoardService {

	/**
	 * insert할 자료가 저장된 BoardVO객체를 매개변수로 받아서
	 * DB에 insert하는 메서드
	 * 
	 * @param boardVo 저장할 자료가 저장된 BoardVO객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int insertBoard(BoardVO boardVo);
	
	/**
	 * 삭제할 게시글 번호를 매개변수로 받아서 해당 자료를 삭제하는 메서드
	 * 
	 * @param boardNo 삭제할 게시글 번호
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int deleteBoard(int boardNo);
	
	/**
	 * 수정할 내용이 저장된 BoardVO객체를 매개변수로 받아서 DB자료를
	 * 변경하는 메서드
	 * 
	 * @param boardVo 수정할 자료가 저장된 BoardVO객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updateBoard(BoardVO boardVo);
	
	/**
	 * 게시글 번호를 매개변수로 받아서 해당 게시글 정보를 반환하는 메서드
	 * 
	 * @param boardNo 검색할 게시글 번호
	 * @return 게시글 번호에 맞는 자료가 있으면 게시글 정보를 담고 있는 BoardVO객체,
	 * 			자료가 없으면 null 반환
	 */
	public BoardVO getBoard(int boardNo);
	
	/**
	 * 게시글 전체를 가져와 List에 담아서 반환하는 메서드
	 *  
	 * @return BoardVO객체를 갖고 있는 List객체
	 */
	public List<BoardVO> getAllBoard();
	
	/**
	 * 게시글 제목을 이용하여 게시글을 검색하는 메서드
	 * 
	 * @param title 검색할 게시글 제목
	 * @return 검색 결과를 갖는 List 객체
	 */
	public List<BoardVO> getSearchBoard(String title);
	
	/**
	 * 게시글 번호를 매개변수로 받아서 해당 게시글의 조회수를 증가시키는 메서드
	 * 
	 * @param boardNo 조회수를 증가할 게시글 번호
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int setCountIncrement(int boardNo);

	
}
