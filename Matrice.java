class Matrice{
  
  private int[][] matr;

  public Matrice(int row){
    matr = new int[row][row];
  }

  public Matrice(int row, int col){
    matr = new int[row][col];
  }

  public Matrice(int[][] matr){
    this.matr = matr;
  }

  public void insertValue(){
    System.out.println("Inserisci valori della Matrice scrivendo i valori delle righe");
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

  public Matrice somma(Matrice m){
    int[][] matrSomma = m.matr;
    int[][] result;
    if(matrSomma.length == matr.length && matrSomma[0].length == matr[0].length){
      result = new int[matrSomma.length][matrSomma[0].length];
      for(int i = 0; i < matr.length; i++){
        for(int j = 0; j < matr[i].length; j++){
          result[i][j] = matr[i][j] + matrSomma[i][j];
        }
      }
      return new Matrice(result);
    }else return new Matrice(1);
  }

  public Matrice moltiplica(int r){
    int[][] result = new int[matr.length][matr[0].length];
    for(int i = 0; i < matr.length; i++){
      for(int j = 0; j < matr[i].length; j++){
        result[i][j] = matr[i][j] * r;
      }
    }
    return new Matrice(result);
  }

  public Matrice moltiplica(Matrice m){
    int[][] matrMoltiplicazione = m.matr;
    int[][] result = new int[this.matr.length][m.matr[0].length];
    int partial = 0;
    for(int i = 0; i < m.matr[0].length; i++){
      for(int j = 0; j < this.matr.length; j++){
        for(int k = 0; k < this.matr[i].length; k++){
          partial += matr[j][k] * m.matr[k][i];
        }
        result[j][i] = partial;
        partial = 0;
      }
    }
    return new Matrice(result);
  }
}