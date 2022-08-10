package login;

import java.util.Scanner;


//앞뒤공백 제거??/회원가입시 글자수 체크/랜덤숫자/

public class Main {

	public static void main(String[] args) {
// 로그인을 하려면 1 회원가입은 2를 눌러
// 아이디 입력해주세요 패스워드 입력해주세요-1
// 작업중 -2
		
		Scanner sc=new Scanner(System.in);
		int b =(int)(Math.random()*100); //랜덤한 숫자 뽑기
		String id,pw,ready;
		
		Check check = new Check(); //체크라는 객체
		
		for(;;) { //루~프
		
		System.out.println("로그인을 하려면 '1'을 회원가입을 하려면 '2'를 "+ "회원삭제를 하려면 '3'을 눌러주세요!" );
		int menu =sc.nextInt();
		System.out.println();
		sc.nextLine();
		if (menu == 1) {
			System.out.println("아이디를 입력해 주세요");
			id =sc.nextLine();
			String trim =id.trim();
			System.out.println("비밀번호를 입력해 주세요 ");
			pw =sc.nextLine();
			String trim1 =pw.trim();
			System.out.println();
			
		//객체(체크)의 메소드 로그인(스트링아이디 스트링 패스워드):int 
		//1:아이디틀림 2: 비밀번호 틀림 3: 로그인 성공 0:db접속실패

			
			
			int flag=check.login(id,pw); //핵심은 로그인 이하 뮨자 두개를 받게 설계
			if(flag==0) {
				System.out.println("DB접속오류입니다");				
			}
			if(flag==1) {
				System.out.println("아이디 오류입니다");				
			}
			if(flag==2) {
				System.out.println("비밀번호 오류입니다");				
			}
			if(flag==3) {
				System.out.println("******************");	
				System.out.println("*로그인 성공입니다*");	
				System.out.println("******************");	
				
			}
			
				if(flag == 3 ) {
//					break;
				}// 무한루프속에서 로그인 성공시 종료
			
			
			
		}else if (menu==2){
			System.out.println("회원가입을 시작합니다.");
			System.out.println("공백 없이 아이디를 입력해 주세요 )");
			id =sc.nextLine();
			String trim =id.trim();
			int length = id.length();
			System.out.println("만들어지는 아이디의 길이는 "+length+" 글자 입니다");
			
			
			System.out.println("공백 없이 비밀번호를 입력해 주세요 ");
			pw =sc.nextLine();
			String trim1 =pw.trim();
			int length1 = pw.length();
			System.out.println("만들어지는 비밀번호의 길이는 "+length1+" 글자 입니다");
			System.out.println();
			System.out.println();
			
			check.signUp(id,pw);
			
			System.out.println("[회원가입이 완료되었습니다.]");
			System.out.println("당신의 회원번호는 "+b+" 입니다"
					+ "<<회원번호는 랜덤하게 정해집니다.>>");
			System.out.println();
			System.out.println("회원번호 : <" +b+"> 아이디 : <" +id+"> 비밀번호  :<" +pw+"> 입니다.");

			
			
			ready =sc.nextLine();
		}
		
		else if(menu==3){
			
			int flag;
			int a;
			
			
			System.out.println("정말 삭제 하실건가요? ㅠ.ㅠ? 삭제(1) 취소(2) <숫자를 입력해 주세요>");
			a=sc.nextInt();
			sc.nextLine();
			if(a==1) {
			
				for(;;) {
				System.out.println("회원삭제를 하려면 아이디를 입력해주세요");
				id =sc.nextLine();
				String trim =id.trim();
				
				
				
				
				System.out.println("비밀번호를 확인하겠습니다");
				pw =sc.nextLine();
				String trim1 =pw.trim();
				flag=check.login(id,pw);
				
	//			System.out.println(flag+"a");
				
					if(flag == 2) {//비번이 다르면
						System.out.println("비밀번호가 다릅니다 다시 시도해 주세요" );
					}
					else if(flag ==1){
							System.out.println("아이디가 존재하지 않습니다");
						}
					else {
						break;
					}
				}
				
				check.del(id, pw);
				
				
				if(flag == 3)
				{System.out.println("회원삭제가 완료되었습니다");
				break;}
						}
			else if(a==2) {
				System.out.println("감사합니다 회원님  *>_<*");
			}else {
				System.out.println("그런 선택지는 없는걸요.. 초기화 할게요");}
				
			}
		else {
			System.out.println("<<그런 선택지는 없는걸요.. 숫자만 입력해주세요 초기화 할게요>>");
			System.out.println();
			}	
		
		}
						}
		
		
		
		
		}

	

