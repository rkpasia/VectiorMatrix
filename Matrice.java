class Matrice{
  
  private double[][] matr;

  public Matrice(int row){
    matr = new double[row][row];
  }

  public Matrice(int row, int col){
    matr = new double[row][col];
  }

  public Matrice(double[][] matr){
    this.matr = matr;
  }

  public void insertValue(){
    System.out.println("Inserisci valori della Matrice scrivendo i valori delle righe");
    for(int i = 0; i<matr.length; i++){
      String rowValues = Leggi.unoString();
      for(int j = 0; j<matr[i].length; j++){
        matr[i][j] = Double.parseDouble(String.valueOf(rowValues.charAt(j)));
      }
    }
  }

  public void insertValueByCol(){
    for(int j = 0; j<matr[0].length; j++){
      String colValues = Leggi.unoString(); 
      for(int i = 0; i<matr.length; i++){
        matr[i][j] = Double.parseDouble(String.valueOf(colValues.charAt(i)));
      }
    }
  }

  private double[][] getValue(){
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
    double[][] matrSomma = m.matr;
    double[][] result;
    if(matrSomma.length == matr.length && matrSomma[0].length == matr[0].length){
      result = new double[matrSomma.length][matrSomma[0].length];
      for(int i = 0; i < matr.length; i++){
        for(int j = 0; j < matr[i].length; j++){
          result[i][j] = matr[i][j] + matrSomma[i][j];
        }
      }
      return new Matrice(result);
    }else return new Matrice(1);
  }

  public Matrice moltiplica(int r){
    double[][] result = new double[matr.length][matr[0].length];
    for(int i = 0; i < matr.length; i++){
      for(int j = 0; j < matr[i].length; j++){
        result[i][j] = matr[i][j] * r;
      }
    }
    return new Matrice(result);
  }

  public Matrice moltiplica(Matrice m){
    double[][] matrMoltiplicazione = m.matr;
    double[][] result = new double[this.matr.length][m.matr[0].length];
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

  private Pivot findPivot(int startCol){
    for(int i = startCol; i < matr[0].length; i++){
      for(int j = 0; j < matr.length; j++){
        if(matr[j][i] != 0 && Pivot.getNPivot() < matr[0].length-1 && j > Pivot.getNPivot()){
          Pivot.incPivot();
          System.out.println("Numero pivot: "+Pivot.getNPivot()+" Riga pivot: "+j+" Colonna pivot: "+i);
          return new Pivot(j,i,matr[j][i]);
        }
      }
    }
    return new Pivot(0,0,0);
  }

  private void placePivot(Pivot pivot){
    for(int i = 0; i < matr[0].length; i++){
      double appoggio = matr[Pivot.getNPivot()][i];
      matr[Pivot.getNPivot()][i] = matr[pivot.getRow()][i];
      matr[pivot.getRow()][i] = appoggio;
    }
    pivot.setRow(Pivot.getNPivot());
  }

  private void reducePivot(Pivot pivot){//OK
    for(int i = 0; i < matr[0].length; i++){
     matr[pivot.getRow()][i] /= pivot.getValue();
    }
  }

  private void sottrazioneRiga(int destRow, int srcRow, double value){//OK
    System.out.println("Riga da cambiare: "+destRow + " Riga che effettuera' la differenza: " + srcRow);
    for(int i = 0; i < matr[0].length; i++){
      matr[destRow][i] -= matr[srcRow][i]*value;
    }
  }

  private void azzeraCol(Pivot pivot){//OK
    for(int i = pivot.getRow()+1; i < matr.length; i++){
      if(matr[i][pivot.getCol()] == 0) continue;
      sottrazioneRiga(i,pivot.getRow(),matr[i][pivot.getCol()]);
    }
  }

  private void setPivot(Pivot pivot){
    if(pivot.getRow() != pivot.getCol()) placePivot(pivot);
    if(pivot.getCol() != matr.length-1) reducePivot(pivot);
    azzeraCol(pivot);
    System.out.println("Risultato:\n");
    printMatrice();
    riduzioneScala(pivot.getCol()+1);
  }

  public void riduzioneScala(){
    Pivot pivot = findPivot(0);
    if (pivot.getValue() != 0 ) setPivot(pivot);
  }

  private void riduzioneScala(int col){
    Pivot pivot = findPivot(col);
    if (pivot.getValue() != 0 ) setPivot(pivot);
  }


}