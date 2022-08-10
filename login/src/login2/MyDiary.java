package login2;

import java.util.Scanner;

public class MyDiary {

	private static CheckType flag;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Check check = new Check();
		Check2 check2 = new Check2();
		String id = null, pw = null;
		int q;
		String w;
		String e;

		System.out.println("+++++++++++++++++++++++++++++++++++");
		System.out.println("++++++++++++MY DIARY+++++++++++++++");
		System.out.println("+++++++++++++++++++++++++++++++++++");
		System.out.println();

		for (;;) {
			System.out.println("로그인을 하려면(1)을 회원가입을 하려면 (2)를 회원삭제는 (3)을 입력하세요.");
			int menu = sc.nextInt();
			System.out.println();
			sc.nextLine();

			if (menu == 1) {
				System.out.println("아이디를 입력해 주세요");
				id = sc.nextLine();
				System.out.println("비밀번호를 입력해 주세요 ");
				pw = sc.nextLine();
				System.out.println();

				CheckType flag = check.login(id, pw); // 핵심은 로그인 이하 뮨자 두개를 받게 설계
				if (flag == CheckType.DB_ERROR) {
					System.out.println("DB접속오류입니다");
				}
				if (flag == CheckType.ID_ERROR) {
					System.out.println("아이디 오류입니다");
				}
				if (flag == CheckType.PW_ERROR) {
					System.out.println("비밀번호 오류입니다");
				}

				if (flag == CheckType.SUCCESS) {
					System.out.println("******************");
					System.out.println("*로그인 성공입니다*");
					System.out.println("******************");

				}
				if (flag == CheckType.SUCCESS) {
					System.out.println("일기를 적으시려면 1번을 눌러주세요 종료는 2번");
					int dd = sc.nextInt();
					if (dd == 1) {
						System.out.println("날짜를 입력해 주세요 ex)22/01/01");
						q = sc.nextInt();
						System.out.println("제목을 입력해 주세요");
						w = sc.nextLine();
						sc.nextLine();
						System.out.println("내용을 입력해 주세요");
						e = sc.nextLine();

						System.out.println("아이디를 다시한번 확인하겠습니다");
						id = sc.nextLine();

						CheckType flag1 = check.write(q, w, e, id);
						System.out.println("일기작성이 완료되었습니다");

					} else {
						break;
					}
				}

			} else if (menu == 2) {
				System.out.println("회원가입을 시작합니다.");

				System.out.print("아이디를 입력해주세요:");

				id = sc.nextLine();
				int qq = check.aa(id);

				if (qq == 1) {
					System.out.println("동일한 아이디가 존재합니다 다시 입력해 주세요");

				} else {
					System.out.print("패스워드를 입력해주세요:");
					pw = sc.nextLine();
					check.signUp(id, pw);
					System.out.println("회원가입완료");
				}

			} else if (menu == 3) {

				CheckType flag;
				int a;

				System.out.println("정말 삭제 하실건가요? ㅠ.ㅠ? 삭제(1) 취소(2) <숫자를 입력해 주세요>");
				a = sc.nextInt();
				sc.nextLine();
				if (a == 1) {

					for (;;) {
						System.out.println("회원삭제를 하려면 아이디를 입력해주세요");
						id = sc.nextLine();

						System.out.println("비밀번호를 확인하겠습니다");
						pw = sc.nextLine();
						flag = check.login(id, pw);

						System.out.println(flag);

						if (flag == CheckType.PW_ERROR) {// 비번이 다르면
							System.out.println("비밀번호가 다릅니다 다시 시도해 주세요");
						} else if (flag == CheckType.ID_ERROR) {
							System.out.println("아이디가 존재하지 않습니다");
						} else {
							break;
						}
					}

					if (flag == CheckType.SUCCESS) {// 회원삭제전ㄷㅇ용매소드
						check.del(id, pw);
						System.out.println("회원삭제가 완료되었습니다");

						break;
					}
				} else if (a == 2) {
					System.out.println("취소하였습니다  *>_<*");
				} else {
					System.out.println("그런 선택지는 없는걸요.. 초기화 할게요");
				}

			} else {
				System.out.println("<<그런 선택지는 없는걸요.. 숫자만 입력해주세요 초기화 할게요>>");
				System.out.println();
			}

		}
	}

}
