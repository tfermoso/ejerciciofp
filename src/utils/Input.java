package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Input {
    public static void wait(String msg) {
        System.out.print(msg);
        new Scanner(System.in).nextLine();
    }
    
    
    public static String input(String title,Filter f,String msg,String cancel) throws CancelException {
        Scanner scn=new Scanner(System.in);
        boolean ok=true;

        String line;
        do {
            System.out.print(title+" "+msg);
            line=scn.nextLine();
            if (line.equalsIgnoreCase(cancel)) throw new CancelException("Operación Cancelada");
            ok=f.isValid(line);
            if (!ok) System.out.println("\t** Entrada non válida **");
        } while(!ok);
        return line;
    }
    
    public static String input(String title,Filter f) throws CancelException {
        return Input.input(title,f,"(* para cancelar): ","*");
    }
    
    
    public static String input(String title) throws CancelException {
        return Input.input(title,new Filter(),"","");
    }
    
    public static int select(String title,Object[] args,String msg,String cancel) throws CancelException {
        Scanner scn=new Scanner(System.in);
        String line;
        int op=0;
        
        do {
            line=title+" "+msg;
            System.out.println(line);
            for(int idx=0;idx < title.length(); idx++) System.out.print("-");
            System.out.println();
            for(int idx=0;idx < args.length;idx++) {
                System.out.println((idx+1)+": "+args[idx]);
            }
            System.out.print("Selecciona unha opción [1-"+args.length+"]: ");
            line=scn.nextLine();
            if (line.equalsIgnoreCase(cancel)) throw new CancelException("Operación Cancelada");
            try {
                op=Integer.parseInt(line);
            } catch(NumberFormatException e) {}
        } while(op < 1 || op > args.length);
        System.out.println("\nSeleccionado "+args[op-1]+"\n");
        return op;
    }
    
    public static int select(String title,Object[] args) throws CancelException {
        return Input.select(title,args,"(* para cancelar)","*");
    }
    
    
    // ###########################  Filters
    
    /**
     * DateFilter
     */
    protected static class DateFilter extends Filter {
        private final LocalDate min;
        private final LocalDate max;
        private DateTimeFormatter format=DateTimeFormatter.ofPattern("d-M-y");
        private LocalDate inputdate;
        
        public DateFilter(LocalDate min,LocalDate max) {
            this.min=min;
            this.max=max;
        }
        
        public DateFilter(LocalDate min) {
            this(min,(LocalDate)null);
        }
        
        public DateFilter(String min,String max) {
            if (min!=null) this.min=LocalDate.parse(min,format);
            else           this.min=null;
            if (max!=null) this.max=LocalDate.parse(max,format);
            else           this.max=null;
        }
        
        public DateFilter(String min) {
            if (min!=null) this.min=LocalDate.parse(min,format);
            else           this.min=null;
            this.max=null;
        }
        
        public DateFilter(String min,String max,String format) {
            this(min,max);
            this.format=DateTimeFormatter.ofPattern(format);
        }
        
        public DateFilter(LocalDate min,LocalDate max,String format) {
            this(min,max);
            this.format=DateTimeFormatter.ofPattern(format);
        }
        
        public DateFilter(LocalDate min,String format) {
            this(min);
            this.format=DateTimeFormatter.ofPattern(format);
        }
        
        public LocalDate getDate() {
            return inputdate;
        }
        
        public DateTimeFormatter getFormatter() {
            return format;
        }
   
        @Override
        public boolean isValid(Object obj) {
            boolean ok=false;
            try {
                String date=(String) obj;
                inputdate=LocalDate.parse(date,format);
                ok=(min==null || inputdate.isAfter(min) || inputdate.isEqual(min));
                ok=ok && (max==null || inputdate.isBefore(max) || inputdate.isEqual(max));
            } catch(Exception e) {
            }
            return ok;
        }
    }
    
    /**
     * NumFilter
     */
    protected static class NumFilter extends Filter {
        private final Double min;
        private final Double max;
        double num;
        
        public NumFilter() {
            this.min=null;
            this.max=null;
        }
        
        public NumFilter(Double min,Double max) {
            this.min=min;
            this.max=max;
        }
        
        public NumFilter(Double min) {
            this.min=min;
            this.max=null;
        }
        
        public NumFilter(Integer min,Integer max) {
            this.min=Double.valueOf(min);
            this.max=Double.valueOf(max);
        }
        
        public NumFilter(Integer min) {
            this.min=Double.valueOf(min);
            this.max=null;
        }
        
        public int getInt() {
            return (int)num;
        }
        
        public double getDouble() {
            return num;
        }
        
        @Override
        public boolean isValid(Object obj) {
            boolean ret=false;
            try {
                String line=(String) obj;
                num=Double.parseDouble(line);
                ret=(min==null) || (num >= min);
                ret=ret && ((max==null) || (max<=min));
            } catch(NumberFormatException e) {}
            return ret;
        }
    }
    
    /******     TEST DA CLASE 
     * 
     * @param args 
     */
    public static void main(String[] args) {
        String[] menu={"Elexir dia da Semana","Ver dias dun mes dun ano","Entrada de Data","Saudar"};
        try {
            while(true) {
                System.out.println();
                int op=Input.select("Menu Principal", menu," (Pulsa S para Saír)","S");
                try {
                    switch(op) {
                        case 1:
                            DiaSemana d=DiaSemana.values()[Input.select("Elixe un día da semana:",DiaSemana.values())-1];
                            break;
                        case 2:
                            try {
                                int ano=Integer.parseInt(input("Ano (1900-2100)",new NumFilter(1900,2100)));
                                Mes m=Mes.values()[Input.select("Elixe un mes:",Mes.values())-1];
                                System.out.println("O mes de "+m+" no ano "+ano+" ten "+m.dias(ano)+" dias");
                            } catch(NumberFormatException e) {
                                System.out.println("Entrada non válida");
                            }
                            break;
                        case 3:
                            String date=input("Introduce unha data de este ano: ",new DateFilter("01-01-2023","31-12-2023"));
                            break;
                        case 4:
                            String nome=input("Como te chamas? ");
                            System.out.println("Hola, "+nome);
                            break;
                    }
                } catch(CancelException ex) {
                    System.out.println("** "+ex.getMessage()+" **");
                }
            }
        } catch (CancelException ex) {
            System.out.println("Programa Finalizado");
        }
    }
}
