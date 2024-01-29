package utils;
public enum Mes {
    ENERO(31),
    FEBRERO(28),
    MARZO(31),
    ABRIL(30),
    MAYO(31),
    JUNIO(30),
    JULIO(31),
    AGOSTO(31),
    SEPTIEMBRE(30),
    OCTUBRE(31),
    NOVIEMBRE(30),
    DICIEMBRE(31);

    private final int dias;
    Mes(int dias){
        this.dias=dias;
    }

    public int dias(int ano){
        if(this.dias==28){
            if(ano%400==0 || (ano%4==0 & ano%100!=0)){
                return dias+1;
            }
        }
        return this.dias;
    }
}
