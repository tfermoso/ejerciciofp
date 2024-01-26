package utils;

/**
 * GB33 BUKB20201555555555
 * GB33 BUKB 202015 
 * Código bancario 202015
 * Número de cuenta 55555555
 * BIC/SWIFT BUKBGB22 --- Entidad (4) pais (2) localidad (2) oficina (3 - opcional)
 * 
 *  GB33 BUKB 202015 55555555 (8)
 * @author xavi
 */
public class GB_Account extends Account {
        
    GB_Account(String ccc) {
        super(Country.REINO_UNIDO,ccc);
        if (ccc.length()!=18) throw new IllegalArgumentException("Invalid Account Number");
        this.bank=ccc.substring(0,4);
        this.office=ccc.substring(4,9);
        this.account=ccc.substring(9);
        //this.ccc=ccc.substring(4);
    }
}
