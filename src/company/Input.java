package company;

import java.time.LocalDate;
import utils.CancelException;
import utils.Filter;
import utils.Iban;
//import utils.NSS;
import utils.Nif;


public class Input extends utils.Input {

    public static String inputNif(String msg) throws CancelException {
        return input(msg,new Filter() {
           @Override 
           public boolean isValid(Object obj) {
               return (Nif.checkNif((String)obj)!=null);
           } 
        });
    }

    // VER: public boolean matches(String regex) na clase String
    // regex:  "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"
    public static String inputEMail(String msg) throws CancelException {
        return input(msg,new Filter() {
           @Override 
           public boolean isValid(Object obj) {
               String str=(String) obj;
               return (str.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"));
           } 
        });
    }

    public static String inputIBAN(String msg) throws CancelException {
        return input(msg,new Filter() {
           @Override 
           public boolean isValid(Object obj) {
               String str=(String) obj;
               return Iban.from(str)!=null;
           } 
        });
    }

    public static String inputNSS(String msg) throws CancelException {
        return input(msg,new Filter() {
           @Override 
           public boolean isValid(Object obj) {
               String str=(String) obj;
               return NSS.checkNSS(str)!=null;
           } 
        });
    }

    public static double inputDouble(String msg) throws CancelException {
        NumFilter filter=new NumFilter();
        input(msg,filter);
        return filter.getDouble();
    }    

    public static double inputDouble(String msg,Double min) throws CancelException {
        NumFilter filter=new NumFilter(min);
        input(msg,filter);
        return filter.getDouble();
    }
    
    public static double inputDouble(String msg,Double min,Double max) throws CancelException {
        NumFilter filter=new NumFilter(min,max);
        input(msg,filter);
        return filter.getDouble();    
    }
    
    
    public static int inputInt(String msg) throws CancelException {
        NumFilter filter=new NumFilter();
        input(msg,filter);
        return filter.getInt();
    }    

    public static int inputInt(String msg,int min) throws CancelException {
        NumFilter filter=new NumFilter(min);
        input(msg,filter);
        return filter.getInt();
    }
    
    public static int inputInt(String msg,int min,int max) throws CancelException {
        NumFilter filter=new NumFilter(min,max);
        input(msg,filter);
        return filter.getInt();        
    }

    public static LocalDate inputDate(String msg, LocalDate min, LocalDate max, String dateformat) throws CancelException {
        DateFilter filter=new DateFilter(min,max,dateformat);
        input(msg,filter);
        return filter.getDate();
    }
}
