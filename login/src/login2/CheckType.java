package login2;

public enum CheckType
{
  DB_ERROR(0),  ID_ERROR(1), PW_ERROR(2), SUCCESS(3);


  private int num;

  private CheckType(int num) {
    this.num = num;
  }

  public int value() {
    return this.num;
  }
}