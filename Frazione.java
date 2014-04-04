public class Frazione {

    private int numeratore;
    private int denominatore;

    public Frazione(int numeratore, int denominatore){
        this.numeratore = numeratore;
        this.denominatore = denominatore;
    }
	
    public Frazione(int numeratore){
        this(numeratore,1);
    }	

    static int getNumeratore(Frazione f){
        return f.numeratore;
    }

    static int getDenominatore(Frazione f){
        return f.denominatore;
    }

    static void setNumeratore(Frazione f, int numeratore){
        f.numeratore = numeratore;
    }
    static void setDenominatore(Frazione f, int denominatore){
        f.denominatore = denominatore;
    }

    static void setValue(Frazione f, int numeratore, int denominatore){
        setNumeratore(f,numeratore);
        setDenominatore(f,denominatore);
    }

    static String getString(Frazione f){
        String codifica = "";
        if(f.denominatore == 1){
            return codifica = codifica + f.numeratore;
        }else{
            return codifica = codifica + f.numeratore +"/"+f.denominatore;
        }
    }

    private static int mcd(int a, int b){
        if(a>1&&b>1){
            if(a==b){
                return a;
            }else if(a>b){
                return mcd(b,a-b);
            }else{
                return mcd(a,b-a);
            }
        }else{
            return 1;
        }
    }

    private static int mcm(int a, int b){
        return (a*b)/(mcd(a,b));
    }

    static void semplifica(Frazione f){
        setValue(f,f.numeratore/mcd(f.numeratore,f.denominatore),f.denominatore/mcd(f.numeratore,f.denominatore));
    }

    public static void moltiplica(Frazione f1, Frazione f2){
        setValue(f1, f1.numeratore*f2.numeratore,f1.denominatore*f2.denominatore);
    }
    public static void somma(Frazione f1, Frazione f2){
        int mcm = mcm(f1.denominatore,f2.denominatore);
        setValue(f1,((mcm/f1.denominatore)*f1.numeratore)+((mcm/f2.denominatore)*f2.numeratore),mcm);
    }  
    public static void sottrazione(Frazione f1, Frazione f2){
        int mcm = mcm(f1.denominatore,f2.denominatore);
        setValue(f1,((mcm/f1.denominatore)*f1.numeratore)-((mcm/f2.denominatore)*f2.numeratore),mcm);
    }
    public static void divisione(Frazione f1, Frazione f2){
        setValue(f2,f2.denominatore,f2.numeratore);
        moltiplica(f1,f2);
        setValue(f2,f2.denominatore,f2.numeratore);
    }
}