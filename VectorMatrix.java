class VectorMatrix {
  public static void main(String[] args){
    Matrice m = new Matrice(3,3);
    //Matrice m1 = new Matrice(3,3);
    m.insertValue();
    //m1.insertValue();
    //m.somma(m1);
    m.printMatrice();
    m.insertValueByCol();
    m.printMatrice();
  }
}