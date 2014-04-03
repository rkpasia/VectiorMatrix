import java.util.*;

class Matrice{
  
  private double[][] matr;
  private List<Pivot> pivots = new ArrayList<Pivot>();

  public Matrice(int row){
    matr = new double[row][row];
  }

  public Matrice(int row, int col){
    matr = new double[row][col];
  }

  public Matrice(double[][] matr){
    this.matr = matr;
  }

  public void insertValueUniversal(){
    System.out.println("Inserisci valori della Matrice scrivendo i valori delle righe");
    for(int i = 0; i<matr.length; i++){
      for(int j = 0; j<matr[i].length; j++){
        matr[i][j] = Leggi.unDouble();
      }
    }
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
          return new Pivot(j,i,matr[j][i]);
        }
      }
    }
    return new Pivot(0,0,0);
  }

  private void addPivot(Pivot pivot){
    pivots.add(pivot);
  }

  private void placePivot(Pivot pivot){
    scambioRiga(Pivot.getNPivot(),pivot.getRow());
    pivot.setRow(Pivot.getNPivot());
  }

  private void reducePivot(Pivot pivot){
    for(int i = 0; i < matr[0].length; i++){
     matr[pivot.getRow()][i] /= pivot.getValue();
    }
  }

  private void azzeraCol(Pivot pivot){
    for(int i = pivot.getRow()+1; i < matr.length; i++){
      if(matr[i][pivot.getCol()] == 0) continue;
      sottrazioneRiga(i,pivot.getRow(),matr[i][pivot.getCol()]);
    }
  }

  private void azzeraColInverse(Pivot pivot){
    for(int i = pivot.getRow()-1; i >= 0; i--){
      if(this.matr[i][pivot.getCol()] == 0) continue;
      sottrazioneRiga(i,pivot.getRow(),this.matr[i][pivot.getCol()]);
    }
  }

  private void setPivot(Pivot pivot){
    if(pivot.getRow() != pivot.getCol()) placePivot(pivot);
    if(pivot.getCol() != matr.length-1) reducePivot(pivot);
    azzeraCol(pivot);
    riduzioneScala(pivot.getCol()+1);
  }

  public void riduzioneScala(){
    Pivot pivot = findPivot(0);
    addPivot(pivot);
    setPivot(pivot);
  }

  private void riduzioneScala(int col){
    Pivot pivot = findPivot(col);
    addPivot(pivot);
    if (pivot.getValue() != 0 ) setPivot(pivot);
  }

  private void moltiplicaRiga(int destRow, double value){
    for(int i = 0; i < matr[0].length; i++){
      matr[destRow][i] *= value;
    }
  }

  private void sottrazioneRiga(int destRow, int srcRow, double value){
    for(int i = 0; i < matr[0].length; i++){
      matr[destRow][i] -= matr[srcRow][i]*value;
    }
  }

  private void sottrazioneRiga(int destRow, int srcRow){
    for(int i = 0; i < matr[0].length; i++){
      matr[destRow][i] -= matr[srcRow][i];
    }
  }

  private void addizioneRiga(int destRow, int srcRow, double value){
    for(int i = 0; i < matr[0].length; i++){
      matr[destRow][i] += matr[srcRow][i]*value;
    }
  }

  private void addizioneRiga(int destRow, int srcRow){
    for(int i = 0; i < matr[0].length; i++){
      matr[destRow][i] += matr[srcRow][i];
    }
  }

  private void scambioRiga(int destRow, int srcRow){
    for(int i = 0; i < matr[0].length; i++){
      double appoggio = matr[destRow][i];
      matr[destRow][i] = matr[srcRow][i];
      matr[srcRow][i] = appoggio;
    }
  }

  private void setIdentita(){
    if(matr.length == matr[0].length) for(int i = 0; i < matr.length; i++) matr[i][i] = 1;
    else {
      for (int i = 0; i < matr.length; i++){
        for(int j = i; j < matr[i].length; j++){
          matr[i][j] = 1;
        }
      }
    } 
  }

  private void riduzioneIdentita(){
    for(int i = pivots.size()-1; i >= 0; i--){
      if(pivots.get(i).getValue() == 0) continue;
      azzeraColInverse(pivots.get(i));
    }
  }

  private void inputData(Matrice inversa, double[][] fetchMatr, int startCol, int colLimit){
    for(int i = startCol; i < colLimit; i++){
      for(int j = 0; j < fetchMatr.length; j++){
        inversa.matr[j][i] = fetchMatr[j][i-startCol];
      }
    }
  }

  private double[][] getSubMatr(double[][] matr, int startCol, int endCol){
    double[][] newMatr = new double[startCol][startCol];
    for(int i = startCol; i < endCol; i++){
      for(int j = 0; j < matr.length; j++){
        newMatr[j][i-startCol] = matr[j][i];
      }
    }
    return newMatr;
  }

  private void setInversa(Matrice inversa){
    Matrice identita = new Matrice(matr.length);
    identita.setIdentita();
    inputData(inversa,matr,0,matr.length);
    inputData(inversa,identita.getValue(),matr.length,inversa.matr[0].length);
    inversa.riduzioneScala();
    inversa.reducePivot(inversa.pivots.get(inversa.pivots.size()-2));
    inversa.riduzioneIdentita();
    inversa.matr = inversa.getSubMatr(inversa.getValue(),matr.length,inversa.getValue()[0].length);
  }

  public Matrice inversa(){
    Matrice inversa = new Matrice(matr.length,matr.length*2);
    setInversa(inversa);
    return inversa;
  }


}