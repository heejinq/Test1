package 야압;

public class EnumTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gender aa;
		aa= Gender.MALE;
		aa= Gender.FEMALE;
		System.out.println(aa);
		
		CheckType bb;
		bb=CheckType.DB_ERROR;
		System.out.println(bb.value());
	}

}
