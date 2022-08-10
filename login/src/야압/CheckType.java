package 야압;

public enum CheckType {
	//0,1,2,3
	//0:db에러 1:id에러 2:pw에러 3:성공

	DB_ERROR(0),ID_ERROR(1),PW_ERROR(2),SUCCESS(3);
	//생성자라 필드 사용가능
	private int num;
	
	CheckType(int num){
		this.num=num;
	}
	public int value() {
		return num;
	}
	
}
