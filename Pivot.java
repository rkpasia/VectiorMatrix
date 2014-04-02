class Pivot{

  private int riga;
  private int colonna;
  private double pivotValue;
  static int nPivot = -1;

  public Pivot(int x){
    riga = x;
    colonna = x;
  }

  public Pivot(int x, int y, double pivotValue){
    riga = x;
    colonna = y;
    this.pivotValue = pivotValue;
    this.nPivot = nPivot;
  }

  public static void incPivot(){
    nPivot += 1;
  }

  public void setRow(int riga){
    this.riga = riga;
  }


  public int getRow(){
    return riga;
  }
  public int getCol(){
    return colonna;
  }
  public double getValue(){
    return pivotValue;
  }
  public static int getNPivot(){
    return nPivot;
  }
}