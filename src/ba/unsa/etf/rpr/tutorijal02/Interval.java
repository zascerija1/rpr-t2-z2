package ba.unsa.etf.rpr.tutorijal02;


public class Interval {
    private double pocetna, krajnja;
    private boolean pocetna_pripada, krajnja_pripada;
    public Interval(double pocetna, double krajnja, boolean pocetna_pripada, boolean krajnja_pripada)
            throws IllegalArgumentException{
        if(pocetna>krajnja) throw new IllegalArgumentException();
        this.pocetna=pocetna;
        this.krajnja=krajnja;
        this.pocetna_pripada=pocetna_pripada;
        this.krajnja_pripada=krajnja_pripada;
    }
    public Interval(){
        pocetna_pripada=false;krajnja_pripada=false;
        pocetna=0;krajnja=0;
    }
    public boolean isNull(){
        if(  !pocetna_pripada && !krajnja_pripada && pocetna==0 && krajnja==0) return true;
        return false;
    }

    public boolean isIn(double s){
        if((s>pocetna || (s>=pocetna && pocetna_pripada)) && (s<krajnja || (s<=krajnja && krajnja_pripada)))
            return true;
        return false;
    }


    public Interval intersect(Interval s){
        if((s.pocetna>pocetna && s.pocetna>krajnja) || (s.pocetna<pocetna && pocetna>s.krajnja)) return new Interval();
        Interval novi= new Interval(pocetna, krajnja, false, false);
        if(s.pocetna> pocetna) novi.pocetna=s.pocetna;
        if(s.krajnja<krajnja) novi.krajnja=s.krajnja;
        if(isIn(novi.pocetna) && s.isIn(novi.pocetna)) novi.pocetna_pripada=true;
        if(isIn(novi.krajnja) && s.isIn(novi.krajnja)) novi.krajnja_pripada=true;
        return novi;

    }

    public static Interval intersect(Interval prvi, Interval drugi){

        return prvi.intersect(drugi);

    }
    @Override
    public String toString(){
        String s=new String();
        if(this.equals(new Interval())) return "()";
        if(pocetna_pripada) s+="[";
        else s+="(";
        s+=pocetna+","+krajnja;
        if(krajnja_pripada) s+="]";
        else s+=")";
        return s;
    }
    @Override
    public boolean equals(Object s){
        Interval p=(Interval)s;
        if(pocetna_pripada==p.pocetna_pripada && krajnja_pripada==p.krajnja_pripada
                && pocetna==p.pocetna && krajnja==p.krajnja) return true;
        return false;
    }

}

