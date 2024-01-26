package utils;

import java.math.BigInteger;
/**
 * 
 *  EEEE OOOO DD NNNNNNNNNN
 *  Entidade-Oficina-DixitoControl-NumeroConta
 * 
 * @author xavi
 */
public class ES_Account extends Account {
   
    ES_Account(String ccc) {
        super(Country.ESPAÑA,ccc);
        if (ccc.length()>20 || ccc.length()< 11)
            throw new IllegalArgumentException("Invalid Account Number");
        bank=ccc.substring(0,4);
        office=ccc.substring(4,8);
        check=ccc.substring(8,10);
        account=ccc.substring(10);
        if (accountControl(bank,office,account)!=Integer.parseInt(check))
            throw new IllegalArgumentException("Invalid IBAN. Wrong account number");
    }
            
    private static int accountControl(String strbank,String stroffice,String straccount) {
        int dc,r1,r2;
        
        BigInteger number=new BigInteger(straccount);
        String strn=String.format("%010d",number);
        
        int bank=Integer.parseInt(strbank);
        int office=Integer.parseInt(stroffice);
        
        r1=11-(cControl(new int[] {4,8,5,10},String.format("%04d",bank))+
               cControl(new int[] {9,7,3,6},String.format("%04d",office)))%11;
        if (r1==10) r1=1;
        else if (r1==11) r1=0;    
        
        r2=11-cControl(new int[] {1,2,4,8,5,10,9,7,3,6},strn)%11;
        if (r2==10) r2=1;
        else if (r2==11) r2=0;

        dc=r1*10+r2;
        return dc;
    }
    
    private static int cControl(int[] factores,String number) {
        int idx=0;
        int result=0;

        while(idx<factores.length) {
            result+=(number.charAt(idx)-'0')*factores[idx];
            idx++;
        }
        return result;
    }
    
        
    public static void main(String[] args) {
        Account acc=new ES_Account("21000418401234567891");
        System.out.println(acc+": "+acc.bank());
        
        acc=new ES_Account("00491500051234567892");
        System.out.println(acc+": "+acc.bank());
        
        acc=new ES_Account("00302053091234567895");  // Banesto, ahora santander.
        System.out.println(acc+": "+acc.bank());
        
        acc=new ES_Account("21004337830200044675");
        System.out.println(acc+": "+acc.bank());
    }
}
