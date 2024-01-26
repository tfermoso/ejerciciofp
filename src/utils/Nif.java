package utils;

import java.util.Arrays;

public final class Nif {
    enum NifType { 
        A("Sociedade Anónima"),
        B("Sociedade de Responsabilididade Limitada"),
        C("Sociedade Colectiva"),
        D("Sociedade Comanditaria"),
        E("Comunidade de Bens"),
        F("Sociedade Cooperativa"),
        G("Asociación ou Fundación"),
        H("Comunidade de Propietarios Horizontal"),
        J("Sociedad Civil"),
        N("Entidade Extranxeira"),
        P("Corporación Local"),
        Q("Organismo Público"),
        R("Organización Relixiosa"),
        S("Administración Xeral do Estado / Comunidade Autónoma"),
        U("Unión Temporal de Empresas"),
        V("Non definido"),
        W("Establecemento Permanente Entidade non Residente"),
        K("Menor de 14 anos"),
        L("Residente no Extranxeiro"),
        M("Extranxeiro sen NIE"),
        NIE("Numero de Identificación de Extranxeiro (NIE)"),
        DNI("Documento Nacional de Identidade (DNI)");
                      
    
        String description;
        
        NifType(String description) {
            this.description=description;
        }
        
        public String description() {
            return description;
        }
        
        @Override
        public String toString() {
            return description;
        }
    }
    
    private static final String STARTNIF_X="ABCDEFGHJNPQRSUV";
    private static final String STARTNIE="XYZ";
    private static final String STARTNIF_F="KLM";
    private static final String DNICONTROL="TRWAGMYFPDXBNJZSQVHLCKE";
    private static final String NIFCONTROL="ABCDEFGHIJ";
    
    private final char[] nif;
    private final NifType type;
    private final char control;


    
    
    public Nif(String nif) {
        this.nif=nif.toUpperCase().toCharArray();

        this.control=this.nif[this.nif.length-1];
        if (isNif()) {
            type=NifType.valueOf(String.valueOf(this.nif[0]));
            if (!verificaNif(this.nif)) throw new IllegalArgumentException(nif+" non e un nif valido");
        } else {
            if (isNie()) {
                type=NifType.NIE;
            } else type=NifType.DNI;
            if (!verificaDni(this.nif)) throw new IllegalArgumentException(nif+" non e un dni/nie válido");
        }
    }
   
        
    public boolean isID() {
        return !isNif();
    }
    
    public boolean isDni() {
        return !isNif() && !isNie();
    }
    
    public boolean isNif() {
        return (STARTNIF_X.indexOf(nif[0])>=0 || STARTNIF_F.indexOf(nif[0])>=0);
    }
    
    public boolean isNie() {
        return STARTNIE.indexOf(nif[0])>=0;
    }
    
    public char getControl() {
        return control;
    }
    
    public NifType type() {
        return type;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Nif other = (Nif) obj;
        return new String(this.nif).equals(new String(other.nif));
    }
    
    @Override
    public String toString() {
        return new String(nif);
    }
    
    public static Nif checkNif(String nif) {
        try {
            return new Nif(nif);
        } catch(IllegalArgumentException e) {}
        return null;
    }
    
    private static boolean verificaDni(char[] letras) {
        if (STARTNIE.indexOf(letras[0])>=0)
            letras[0]=(char)(STARTNIE.indexOf(letras[0])+'0');
        
        if (letras.length!=9) return false;
        char control=letras[letras.length-1];
        try {
            int num=Integer.parseInt(new String(Arrays.copyOfRange(letras,0,letras.length-1)));
            return DNICONTROL.toCharArray()[num%23]==control;
        } catch(NumberFormatException e) {
            
        }
        return false;
    }

    private static boolean verificaNif(char[] letras) {
        try {
            // G19516020   -> 1951602 -> pares=9+1+0=10  impares=1+5+6+2=14 * 2 = 28
            Integer.valueOf(new String(Arrays.copyOfRange(letras,1,letras.length-1)));
            int suma_par=sumaPares(letras);
            int suma_impar=sumaImpares(letras);

            int total=suma_par+suma_impar;
            int cc=(10-(total%10))%10;
            if (Character.isDigit(letras[letras.length-1])) {
                return ((cc+'0')==letras[letras.length-1]);
            } else {
                return NIFCONTROL.toCharArray()[cc-1]==letras[letras.length-1];
            }
        } catch(NumberFormatException e) {
            
        }
        return false;
    }
    
    private static int sumaPares(char[] letras) {
        int suma=0;
        for(int idx=2;idx<letras.length-2;idx+=2) {
            suma=suma+(letras[idx]-'0');
        }
        return suma;
    }
    
    private static int sumaImpares(char[] letras) {
        int suma=0;
        for(int idx=1;idx<letras.length;idx+=2) {
            int n=(letras[idx]-'0')*2;
            n=(n/10)+(n%10);
            suma=suma+n;
        }
        return suma;
    }
    
    /**
     * TEST DA CLASE
     */
    public static void main(String[] args) {
        String[] nifs={"53081469E","61920037N","Y2827736S","X8946807Z","P3899811H","N1807239G","G19516020"};
        
        for(String n:nifs) {
            Nif nif=new Nif(n);
            System.out.println(nif+": "+nif.type());
        }
    }
}
