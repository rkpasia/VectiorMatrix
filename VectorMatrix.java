class VectorMatrix {
  public static void main(String[] args){
    Matrice m = new Matrice(2,2);
    Matrice m1 = new Matrice(2,1);
    Matrice sommata;
    Matrice moltiplicata;
    m.insertValue();
    //m1.insertValue();
    //m.somma(m1);
    m1.insertValue();
    /*sommata = m.somma(m1);
    sommata.printMatrice();
    moltiplicata = m.moltiplica(m1);
    moltiplicata.printMatrice();*/
    //m1.printMatrice();
    moltiplicata = m.moltiplica(m1);
    moltiplicata.printMatrice();
  }
}