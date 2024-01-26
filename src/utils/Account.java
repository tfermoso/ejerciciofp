package utils;

/**
 *
 * BIC/SWIFT : Entidad (4) pais (2) localidad (2)
 * 
 * @author xavi
 */
public class Account {
    protected String straccount;
    protected Country country;
    protected String bank;
    protected String office;
    protected String location;
    protected String account;   // Numero de conta
    protected String check;
    protected String ccc;   // Numero ccc completo
        
    protected Account(Country c,String ccc) {
        this.straccount=ccc;
        this.country=c;
        this.ccc=ccc;
        this.office="";
        this.location="XX";
    }
    
    public Bank bank() {
        Bank b=Bank.get(bank);
        if (b!=null) return b;
        return Bank.UNKNOWN_BANK;
    }
    
    public String office() {
        return office;
    }
    
    public String account() {
        return account;
    }
    
    public String check() {
        return check;
    }
    
    @Override
    public String toString() {
        return straccount;
    }

    public String swift() {
        Bank b=Bank.get(bank);

        if (b==Bank.UNKNOWN_BANK) {
            if (country == Country.REINO_UNIDO) return bank+"GB--";
            else                                return b.swift()+country.isoCode()+"--";
        }
        return b.swift();
    }

}
