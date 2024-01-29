package utils;
public class NSS {
    public String numss;

    public NSS(String numss) {

        int digitos_control;
        Long numero;
        try {
            numero = Long.valueOf(numss.substring(0, 10));
            digitos_control = Integer.valueOf(numss.substring(10));
            if(numero%97==digitos_control){
                this.numss = numss;
            }else{
                throw new IllegalArgumentException();
            }
        }catch (Exception e){
            throw new IllegalArgumentException();
        }

    }

    public static boolean checkNSS(String numss) {
        int digitos_control;
        Long numero;
        try {
            numero = Long.valueOf(numss.substring(0, 10));
            digitos_control = Integer.valueOf(numss.substring(10));
            if(numero%97==digitos_control){
              return true;
            }else{
                throw new IllegalArgumentException();
            }
        }catch (Exception e){
            throw new IllegalArgumentException();
        }
    }

    public String control(){
        return numss.substring(10);
    }

    public int numero(){
        return Integer.valueOf(numss.substring(2,10));
    }
    public Provincia provincia(){
        String codigo_provincia=numss.substring(0,2);
        return  Provincia.get(codigo_provincia);
    }

    @Override
    public String toString() {
        return "NSS{" +
                "numss='" + numss + '\'' +
                '}';
    }
}
