package utils;

/**
 * IBAN 27 letras
 * 
 * la clave de control RIB, la clave IBAN es siempre el número 76 
 * para las cuentas bancarias francesas que solo tienen números
 * Y 14 para los que tienen letras ?
 * 
 * 20041 01005 0500013M026 06 (RIB)
 * 20001 00001 00000000001 37
 * BANCO ------ cuenta ------
 * 
 * FR14 20041 01005 0500013M026 06
 * FR76 30027 17252 00020545001 21
 * 
 * FRCC BANKC SUCUR 12345678901 VV
 * 
 * BIC: CMCI FR PP XXX
 * Entidade Pais Localidade Oficina
 * @author xavi
 */
public class FR_Account extends Account {

    
    FR_Account(String rib) {
        super(Country.FRANCIA,rib.toUpperCase());
        System.out.println("Verificando "+rib);
        if (rib.length()!=23)
            throw new IllegalArgumentException("Invalid Account Number");
        bank=rib.substring(0,5);
        office=rib.substring(5,10);
        check=rib.substring(21);
        account=rib.substring(10,21);
        ccc=subst(account);
        if (accountControl(bank,office,ccc)!=Integer.parseInt(check))
            throw new IllegalArgumentException("Invalid Account Number");    
    }
    
    
    private static int accountControl(String strbank,String stroffice,String straccount) {
        try {
            int b=Integer.parseInt(strbank);
            int g=Integer.parseInt(stroffice);
            long c=Long.parseLong(straccount);
            return (int)(97-((89*b+15*g+3*c) % 97));
        } catch(NumberFormatException e) {
        }
        throw new IllegalArgumentException("Invalid Account Number");
    }
    
    
    private static String subst(String string) {
        char[][] values={ {'A','J'},{'B','K','S'},{'C','L','T'},{'D','M','U'},{'E','N','V'},{'F','O','W'},
                          {'G','P','X'},{'H','Q','Y'},{'I','R','Z'}};
        
        for(int idx=0;idx<values.length;idx++) {
            for(int l=0;l<values[idx].length;l++) string=string.replace(values[idx][l],(char)(idx+'1'));
        }
        return string;
    }
    
    public static void main(String[] args) {
        //Account acc=new FR_Account("30003030200005012345674");
        //System.out.println(acc+": "+acc.bank());
        
       // Account acc=new FR_Account("30004028379876543210943");
       // System.out.println(acc+": "+acc.bank());

        Account acc=new FR_Account("20041010050500013M02606");
        System.out.println(acc+": "+acc.bank());
        
        acc=new FR_Account("10096000307974521789B88");
        System.out.println(acc+": "+acc.bank());
        
        acc=new FR_Account("14508000404332126439D92");
        System.out.println(acc+": "+acc.bank());
        
        acc=new FR_Account("17569000404717764481U85");
        System.out.println(acc+": "+acc.bank());
        
        acc=new FR_Account("12739000409239554376J50");
        System.out.println(acc+": "+acc.bank());
        
        acc=new FR_Account("30003000505586346295R91");
        System.out.println(acc+": "+acc.bank());
        
        acc=new FR_Account("30003000302279572999I53");
        System.out.println(acc+": "+acc.bank());
        /*


        FR7814508000404332126439D92
                FR0717569000404717764481U85
                FR5112739000409239554376J50
                        FR3530003000505586346295R91
                        FR6630003000302279572999I53
*/
    }
}
