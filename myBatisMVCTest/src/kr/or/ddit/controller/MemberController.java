package kr.or.ddit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.service.IMemberService;
import kr.or.ddit.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberController {
	private Scanner scan;
	private IMemberService service;  // Service 객체가 저장될 변수 선언
	
	public MemberController() {
		scan = new Scanner(System.in);
//		service = new MemberServiceImpl();  // Service 객체 생성
		service = MemberServiceImpl.getInstance();  // Service 객체 생성
	}
	
	
	public static void main(String[] args) {
		new MemberController().startMember();
	}

	// 시작 메서드
	public void startMember() {
		while(true) {
			int choice = displayMenu();
			
			switch(choice) {
				case 1 : 	// 추가
					insertMember(); 	break;
				case 2 : 	// 삭제
					deleteMember(); 	break;
				case 3 : 	// 수정
					updateMember();		break;
				case 4 : 	// 전체 출력
					displayAllMember();	break;
				case 5 : 	// 수정
					updateMember2();	break;
				case 0 : 	// 종료
					System.out.println("작업을 마칩니다...");
					return;
				default :
					System.out.println("작업 번호를 잘못 입력했습니다.");
					System.out.println("다시 입력하세요...");
			}
			
		}
	}
	
	// 회원 정보 수정 - 원하는 항목만...
	private void updateMember2() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("수정할 회원ID >> ");
		String memId = scan.next();
		
		int count = service.getMemberIdCount(memId);
		
		if(count==0) {
			System.out.println("회원ID가 " + memId + "인 회원은 존재하지 않습니다...");
			System.out.println();
			return;
		}
		
		String updateField = null; 		// 수정할 항목의 컬럼명이 저장될 변수
		String updateTitle = null;		// 수정할 항목의 제목이 저장될 변수
		int num;		// 수정할 항목의 번호가 저장될 변수
		do {
			System.out.println();
			System.out.println("수정할 항목을 선택하세요...");
			System.out.println("1.비밀번호   2.회원이름   3.전화번호   4.회원주소");
			System.out.println("-----------------------------------------");
			System.out.print("수정할 항목 선택 >> ");
			num = scan.nextInt();
			
			switch(num) {
				case 1 : updateTitle = "비밀번호"; updateField = "mem_pass"; break;
				case 2 : updateTitle = "회원이름"; updateField = "mem_name"; break;
				case 3 : updateTitle = "전화번호"; updateField = "mem_tel"; break;
				case 4 : updateTitle = "회원주소"; updateField = "mem_addr"; break;
				default : 
					System.out.println("수정 항목 선택이 잘못되었습니다. 다시 선택하세요...");
			}
			
		}while(num<1 || num>4);
		
		scan.nextLine();		// 버퍼 비우기
		System.out.println();
		System.out.print("새로운 " + updateTitle + " >> ");
		String updateData = scan.nextLine();
		
		// 입력 받은 자료들을 Map객체에 저장한다.
		// Map의 key값 정보 ==> 회원ID(MEMID), 수정할컬럼명(FIELD), 변경될데이터(DATA)
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("MEMID", memId);
		paramMap.put("FIELD", updateField);
		paramMap.put("DATA", updateData);
		
		int cnt = service.updateMember2(paramMap);
		
		if(cnt>0) {
			System.out.println("수정 작업 완료!!!");
		}else {
			System.out.println("수정 작업 실패~~~");
		}
		
	}
	
	// 전체 회원 출력
	private void displayAllMember() {
		List<MemberVO> memList = service.getAllMember();
		
		System.out.println();
		System.out.println("------------------------------------------------");
		System.out.println("ID      비밀번호    이 름    전화번호    주 소");
		System.out.println("------------------------------------------------");
		
		if(memList==null || memList.size()==0) {
			System.out.println("\t등록된 회원 정보가 하나도 없습니다...");
		}else {
			for(MemberVO memVo : memList) {
				String memId = memVo.getMem_id();
				String memPass = memVo.getMem_pass();
				String memName = memVo.getMem_name();
				String memTel = memVo.getMem_tel();
				String memAddr = memVo.getMem_addr();
				
				System.out.println(memId + "\t" + memPass + "\t" + memName +
						"\t" + memTel + "\t" + memAddr);
			}
		}
		System.out.println("------------------------------------------------");
		
	}
	
	
	// 수정 메서드
	private void updateMember() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요...");
		System.out.print("수정할 회원ID >> ");
		String memId = scan.next();
		
		int count = service.getMemberIdCount(memId);
		
		if(count==0) {
			System.out.println("회원ID가 " + memId + "인 회원은 존재하지 않습니다...");
			System.out.println();
			return;
		}
		
		System.out.println();
		System.out.print("새로운 비밀번호 >> ");
		String newPass = scan.next();
		
		System.out.print("새로운 회원이름 >> ");
		String newName = scan.next();
		
		System.out.print("새로운 전화번호 >> ");
		String newTel = scan.next();
		
		scan.nextLine();  // 버퍼 비우기
		System.out.print("새로운 회원주소 >> ");
		String newAddr = scan.nextLine();
		
		// 입력한 자료를 VO에 저장한다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_pass(newPass);
		memVo.setMem_name(newName);
		memVo.setMem_tel(newTel);
		memVo.setMem_addr(newAddr);
		
		int cnt = service.updateMember(memVo);
		
		if(cnt>0) {
			System.out.println("수정 작업 완료!!!");
		}else {
			System.out.println("수정 작업 실패~~~");
		}
		
	}
	
	
	// 삭제 메서드
	private void deleteMember() {
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요...");
		System.out.print("삭제할 회원ID >> ");
		String memId = scan.next();
		
		int count = service.getMemberIdCount(memId);
		
		if(count==0) {
			System.out.println("회원ID가 " + memId + "인 회원은 존재하지 않습니다...");
			System.out.println();
			return;
		}
		
		int cnt = service.deleteMember(memId);
		
		if(cnt>0) {
			System.out.println("삭제 작업 성공!!!");
		}else {
			System.out.println("삭제 작업 실패~~~");
		}
		
	}
	
	// 회원 정보를 추가하는 메서드
	private void insertMember() {
		System.out.println();
		System.out.println("추가할 회원 정보를 입력하세요...");
		
		int count = 0;			// 회원ID의 개수가 저장될 변수
		String memId = null;	// 화원ID가 저장될 변수
		
		do {
			System.out.print("회원ID >> ");
			memId = scan.next();
			count = service.getMemberIdCount(memId);
			
			if(count>0) {
				System.out.println("입력한 " + memId + "은(는) 이미 등록된 회원ID 입니다.");
				System.out.println("다른 회원ID를 입력하세요...");
				System.out.println();
			}
			
		}while(count>0);
		
		System.out.print("비밀번호 >> ");
		String pass = scan.next();
		
		System.out.print("이   름 >> ");
		String name = scan.next();
		
		System.out.print("전화번호 >> ");
		String tel = scan.next();
		
		scan.nextLine();	// 입력 버퍼 비우기
		System.out.print("주   소 >> ");
		String addr = scan.nextLine();
		
		// 입력 받은 자료를 VO객체에 저장한다.
		MemberVO memberVo = new MemberVO();
		memberVo.setMem_id(memId);
		memberVo.setMem_pass(pass);
		memberVo.setMem_name(name);
		memberVo.setMem_tel(tel);
		memberVo.setMem_addr(addr);
		
		int cnt = service.insertMember(memberVo);
		
		if(cnt>0) {
			System.out.println("insert 작업 성공!!");
		}else {
			System.out.println("insert 작업 실패~~");
		}
		
	}
	
	
	// 메뉴를 출력하고 작업 번호를 입력 받아 반환하는 메서드
	private int displayMenu() {
		System.out.println();
		System.out.println("1. 자료 추가 	");
		System.out.println("2. 자료 삭제 	");
		System.out.println("3. 자료 수정	");
		System.out.println("4. 전체 자료 출력 ");
		System.out.println("5. 자료 수정 2 ");
		System.out.println("0. 작업 끝.    ");
		System.out.println("--------------------");
		System.out.print("작업 선택 >> ");
		return scan.nextInt();
	}
}
