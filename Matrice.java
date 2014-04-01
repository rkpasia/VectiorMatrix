class Matrice{
  
  private int[][] matr;

  public Matrice(int row){
    matr = new int[row][row];
  }

  public Matrice(int row, int col){
    matr = new int[row][col];
  }

  public void insertValue(){
    for(int i = 0; i<matr.length; i++){
      String rowValues = Leggi.unoString();
      for(int j = 0; j<matr[i].length; j++){
        matr[i][j] = Integer.parseInt(String.valueOf(rowValues.charAt(j)));
      }
    }
  }

  public void insertValueByCol(){
    for(int j = 0; j<matr[0].length; j++){
      String colValues = Leggi.unoString(); 
      for(int i = 0; i<matr.length; i++){
        matr[i][j] = Integer.parseInt(String.valueOf(colValues.charAt(i)));
      }
    }
  }

  private int[][] getValue(){
    return matr;
  }

  public void printMatrice(){
    for(int i = 0; i<matr.length; i++){
      for(int j = 0; j<matr[i].length; j++){
        System.out.print(matr[i][j]+" ");
      }
      System.out.println();
    }
  }

  public void somma(Matrice m){
    m.printMatrice();
  }


}