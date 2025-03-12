package kr.or.ddit.board.controller;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVO;

public class JdbcBoardController {
	private Scanner scan;
	private IBoardService service;
	
	public JdbcBoardController() {
		scan = new Scanner(System.in);
		service = BoardServiceImpl.getInstance();
	}

	public static void main(String[] args) {
		new JdbcBoardController().startBoard();
	}
	
	// 시작 메서드
	public void startBoard() {
		while(true) {
			int choice = displayMenu();
			switch(choice) {
				case 1 :			// 새글 쓰기
					insertBoard(); 	break;
				case 2 :			// 게시글 보기
					viewBoard(); 	break;
				case 3 :			// 수정
					updateBoard(); 	break;
				case 4 :			// 삭제
					deleteBoard();	break;
				case 5 :			// 검색
					searchBoard();	break;
				case 6 :			// 전체 출력
					displayAllBoard(); break;
				case 0 :
					System.out.println();
					System.out.println("게시판 프로그램 끝...");
					return;
				default : 
					System.out.println("작업 번호를 잘못입력했습니다.");
					System.out.println("다시 입력하세요.");
			}
		}
	}
	
	// 검색 작업
	private void searchBoard() {
		scan.nextLine();	// 버퍼 비우기
		System.out.println();
		System.out.println(" 검색 작업");
		System.out.println("-------------------------------------");
		System.out.print("검색할 제목 입력 >> ");
		String title = scan.nextLine();
		
		List<BoardVO> boardList = service.getSearchBoard(title);
		
		displayBoardList(boardList);
		
	}
	
	// 게시글 삭제
	private void deleteBoard() {
		System.out.println();
		System.out.println(" 삭제 작업 하기");
		System.out.println("------------------------------------------");
		System.out.print("삭제할 게시글 번호 >> ");
		int no = scan.nextInt();
		
		int cnt = service.deleteBoard(no);
		
		if(cnt>0) {
			System.out.println(no + "번 게시글이 삭제되었습니다...");
		}else {
			System.out.println(no + "번 글이 없거나 삭제 작업 실패!!!");
		}
		
	}
	
	// 게시글 수정하기
	private void updateBoard() {
		System.out.println();
		System.out.println(" 수정 작업 하기");
		System.out.println("------------------------------------------");
		System.out.print("수정할 게시글 번호 >> ");
		int no = scan.nextInt();
		
		scan.nextLine();  // 버퍼 비우기
		System.out.print("새로운 제 목 >> ");
		String title = scan.nextLine();
		
		System.out.print("새로운 내 용 >> ");
		String content = scan.nextLine();
		
		// 입력한 값 VO에 저장
		BoardVO boardVo = new BoardVO();
		boardVo.setBoard_no(no);
		boardVo.setBoard_title(title);
		boardVo.setBoard_content(content);
		
		int cnt = service.updateBoard(boardVo);
		if(cnt>0) {
			System.out.println(no + "번 글이 수정되었습니다.");
		}else {
			System.out.println(no + "번 글이 없거나 수정 작업 실패!!!");
		}
		
	}
	
	// 게시글 보기
	private void viewBoard() {
		System.out.println();
		System.out.print("보기를 원하는 게시글 번호 입력 >> ");
		int no = scan.nextInt();
		
		BoardVO boardVo = service.getBoard(no);
		
		if(boardVo==null) {
			System.out.println(no + "번 게시글은 존재하지 않습니다...");
			return;
		}
		
		System.out.println();
		System.out.println(no + "번 게시글의 내용");
		System.out.println("-----------------------------------");
		System.out.println(" 제 목 : " + boardVo.getBoard_title());
		System.out.println(" 작성자 : " + boardVo.getBoard_writer());
		System.out.println(" 내 용 : " + boardVo.getBoard_content());
		System.out.println(" 조회수 : " + boardVo.getBoard_cnt());
		System.out.println(" 작성일 : " + boardVo.getBoard_date());
		System.out.println("-----------------------------------");
		System.out.println("출력 끝...");
		
	}
	
	
	// 전체 게시글 목록 출력하기
	private void displayAllBoard() {
		List<BoardVO> boardList = service.getAllBoard();
		displayBoardList(boardList);
	}
	
	// 게시글 목록을 매개변수로 받아서 출력하는 메서드
	private void displayBoardList(List<BoardVO> boardList) {
		
		System.out.println();
		System.out.println("---------------------------------------");
		System.out.println("  NO      제  목      작성자       조회수");
		System.out.println("---------------------------------------");
		if(boardList==null || boardList.size()==0) {
			System.out.println(" 게시글이 하나도 없습니다...");
		}else {
			for(BoardVO bVo : boardList) {
				System.out.println(bVo.getBoard_no() + "\t" 
							+ bVo.getBoard_title() + "\t"
							+ bVo.getBoard_writer() + "\t"
							+ bVo.getBoard_cnt() );
			}
		}
		System.out.println("---------------------------------------");
		System.out.println("출력 끝...");
	}
	
	
	// 새글을 작성하는 메서드
	private void insertBoard() {
		scan.nextLine(); // 버퍼 비우기
		
		System.out.println();
		System.out.println("   새 글 작성하기");
		System.out.println("-----------------------------------");
		System.out.print(" 제 목 >> ");
		String title = scan.nextLine();
		
		System.out.print(" 작성자 >> ");
		String writer = scan.nextLine();
		
		System.out.print(" 내 용 >> ");
		String content = scan.nextLine();
		
		// 입력 받은 자료를 VO객체에 저장한다.
		BoardVO boardVo = new BoardVO();
		boardVo.setBoard_title(title);
		boardVo.setBoard_writer(writer);
		boardVo.setBoard_content(content);
		
		int cnt = service.insertBoard(boardVo);
		
		System.out.println();
		if(cnt>0) {
			System.out.println("새글이 추가되었습니다...");
		}else {
			System.out.println("새글 추가 실패!!!");
		}
		
	}
	
	// 메뉴를 출력하고 작업 번호를 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println("1. 새글 작성");
		System.out.println("2. 게시글 보기");
		System.out.println("3. 게시글 수정");
		System.out.println("4. 게시글 삭제");
		System.out.println("5. 게시글 검색");
		System.out.println("6. 전체 목록 출력");
		System.out.println("0. 작업 끝.");
		System.out.println("=================");
		System.out.print("작업 번호 >> ");
		return scan.nextInt();
	}

}
