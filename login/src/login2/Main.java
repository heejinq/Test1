package login2;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class Main { public static void main(String[] args)
  {
    Scanner sc = new Scanner(System.in);
    Check check = new Check();
    while (true) {
      System.out.print("목록은(4) 로그인을 할려면(1), 회원가입을 할려면(2), 회원삭제는(3) 입력하세요.");
      int menu = sc.nextInt();
      sc.nextLine();
      if (menu == 1) {
        System.out.print("아이디를 입력해주세요:");
        String id = sc.nextLine();
        System.out.print("패스워드를 입력해주세요:");
        String pw = sc.nextLine();

        CheckType flag = check.login(id, pw);

        if (flag == CheckType.SUCCESS)
          System.out.println("정해놓은 값을 꺼내서 사용도 가능" + flag.value());
        System.out.println("로그인 성공");
      }
      else if (menu == 2) {
        System.out.println("회원가입 시작합니다.");
        System.out.print("아이디를 입력해주세요:");
        String id = sc.nextLine();
        System.out.print("패스워드를 입력해주세요:");
        String pw = sc.nextLine();
        check.signUp(id, pw);
        System.out.println("회원가입완료");
      }
      else {
        List list = check.list();
        for (User temp : list) {
          System.out.print(temp.id + "   ");
          System.out.println(temp.pw);
        }
      }
    }
  }
}