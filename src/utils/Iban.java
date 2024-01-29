package utils;

import java.math.BigInteger;
import static utils.Country.ESPAÑA;
import static utils.Country.FRANCIA;
import static utils.Country.REINO_UNIDO;

/**
 * Esta clase representa un IBAN bancario
 */
public class Iban {
    protected String iban;          // IBAN
    protected Account account;
    
   
    private Iban(Account account) {
        try {
            this.account=account;
            String ccode=account.country.isoCode();
            String straccount=account.ccc+ccode+"00";
            StringBuilder sb=new StringBuilder();

            char[] let=straccount.toCharArray();
            for(char l:let) {
                if (l>='A' && l<='Z') sb.append(Integer.toString(l-'A'+10));
                else                  sb.append(l);
            }
            BigInteger number=new BigInteger(sb.toString());
            int check_iban=98-number.mod(BigInteger.valueOf(97)).intValue();
            if (check_iban<10) ccode+="0"+check_iban;
            else               ccode+=check_iban;
            iban=ccode+account.ccc;
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("Invalid IBAN");
        }
    }
    
    public String getIban() {
        return iban;
    }
    
    public String getCCC() {
        return account.ccc;
    }
    
    public Country getCountry() {
        return account.country;
    }
    
    public Bank getBank() {
        return account.bank();
    }
    
    @Override
    public String toString() {
        return iban;//+" ["+getBank()+"]";
    }

    public static Iban from(String ibanstr) {
        if ((ibanstr.length()<34)&&(ibanstr.length()>=6)) {
            Country country=Country.get(ibanstr.substring(0,2));
            String ccc=ibanstr.substring(4);
            Iban iban=Iban.from(country,ccc);
            if (ibanstr.equals(iban.toString())) return iban;
        }
        return null;
    }
    
    public static Iban from(Country country,String ccc) {
        Account account;
        BigInteger number;

            switch(country) {
                case REINO_UNIDO:
                    account=new GB_Account(ccc);
                    break;
                case ESPAÑA:
                    account=new ES_Account(ccc);
                    break;
                case FRANCIA:
                    account=new FR_Account(ccc);
                    break;
                default:
                    account=new UNKNOWN_Account(country,ccc);
            }      
            return new Iban(account);
    }    
    
    /**
     * TEST DA CLASE
     * @param args 
     */
    public static void main(String[] args) {
        System.out.println("IBAN: "+Iban.from("ES6621000418401234567891"));
        System.out.println("IBAN: "+Iban.from("ES6000491500051234567892"));
        System.out.println("IBAN: "+Iban.from("ES7100302053091234567895"));
        System.out.println("IBAN: "+Iban.from("ES8221004337830200044675"));
        System.out.println("IBAN: "+Iban.from("GB26MIDL40051512345674"));

        System.out.println("IBAN: "+Iban.from("GB33BUKB20201555555555"));
        System.out.println("IBAN: "+Iban.from("US64SVBKUS6S3300958879"));
        System.out.println("IBAN: "+Iban.from("GB94BARC10201530093459"));
        System.out.println("IBAN: "+Iban.from("GB02BARC20201530093451"));
    }
}
