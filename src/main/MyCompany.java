package main;

import company.Candidato;
import company.Curriculos;
import company.Forms;
import company.Plantilla;
import utils.CancelException;
import company.Input;
import company.Sheets;
import company.Traballador;
import utils.Filter;
import utils.Nif;
import utils.StorageException;

/**

 */
public class MyCompany {
    private static final Plantilla plantilla=new Plantilla();
    private static final Curriculos curriculos=new Curriculos();
    
    
    public static void candidatos() throws CancelException {
        Candidato candidato;
        String dni;
        
        String[] menu={"Listado de Candidatos","Alta de Candidatos","Ver Candidato","Contrato"};
        try {
            while(true) {
                int op=Input.select("Menú de Selección de Persoal",menu," (Pulsa V para volver ao menú principal)","V");
                try {
                    switch(op) {
                        case 1:
                            System.out.println("Listado de Candidatos");
                            Sheets.listSheet(curriculos.getAll());
                            Input.wait("\nPulsa ENTER para continuar\n");
                            break;
                        case 2:
                            candidato=Forms.formCandidato();
                            curriculos.add(candidato);
                            System.out.println("Engadido candidato: "+candidato);
                            Input.wait("\nPulsa ENTER para continuar\n");
                            break;
                        case 3:
                            dni=Input.inputNif("DNI do candidato: ");
                            candidato=(Candidato)curriculos.get(new Nif(dni));
                            if (candidato==null) System.out.println("Non se atopa o candidato");
                            else {
                                System.out.println("\nDatos do Candidato: ");
                                Sheets.curriculumSheet(candidato);
                            }
                            Input.wait("\nPulsa ENTER para continuar\n");
                            break;
                        case 4:
                            dni=Input.inputNif("DNI do candidato: ");
                            candidato=(Candidato)curriculos.get(new Nif(dni));
                            if (candidato==null) System.out.println("Non se atopa o candidato");
                            else {
                                Traballador traballador=Forms.formTraballador(candidato);
                                plantilla.add(traballador);
                                curriculos.del(candidato);
                                System.out.println("\nEngadido novo traballador a plantilla: "+traballador);
                            }
                            Input.wait("\nPulsa ENTER para continuar\n");                            
                    }
                } catch(StorageException ex) {
                    System.out.println("Erro no almacen de datos: "+ex.getMessage());
                } catch(CancelException ex) {
                    System.out.println("\t *** "+ex.getMessage()+" ***");
                }
            }
        } catch(IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        } catch(CancelException ex) {}

    }

    public static void traballadores() throws CancelException {
        Traballador[] lista;
        
        String[] menu={"Lista da Plantilla","Busca de Traballador por DNI","Busca de traballadores por nome"};
        try {
            while(true) {
                int op=Input.select("Menú de Persoal",menu," (Pulsa V para volver ao menú principal)","V");
                try {
                    switch(op) {
                        case 1:
                            System.out.println("Listado de Traballadores");
                            Sheets.listSheet(plantilla.getAll());
                            Input.wait("\nPulsa ENTER para continuar\n");
                            break;
                       case 2:
                            String str=Input.inputNif("DNI do traballador: ");
                            Traballador traballador=(Traballador)plantilla.get(new Nif(str));
                            if (traballador==null) System.out.println("Non se atopa o traballador");
                            else {
                                System.out.println("Datos do Traballador: ");
                                Sheets.workerSheet(traballador);
                            }
                            Input.wait("\nPulsa ENTER para continuar\n");
                            break;

                        case 3:
                            final String strdata=Input.input("Escribe parte dos apelidos ou nome: ");
                            lista=(Traballador[])plantilla.find(new Filter() {
                                @Override
                                public boolean isValid(Object obj) {
                                    Traballador t=(Traballador) obj;
                                    return t.getAnome().contains(strdata);
                                }
                            });
                            if (lista.length==0) System.out.println("Non se atopan traballadores similares");
                            else {
                                Sheets.listSheet(lista);
                            }                            
                            Input.wait("\nPulsa ENTER para continuar\n");
                            break;
                    }
                    
                } catch(CancelException ex) {
                    System.out.println("\t *** "+ex.getMessage()+" ***");
                }
            }
            
        } catch(IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
        } catch(CancelException ex) {
        }
        
    }
    

    public static void main(String[] args) {
        String[] menu={"Xestión de Candidatos","Xestión de Traballadores"};

        try {
            while(true) {
                System.out.println();
                int op=Input.select("Menu Principal", menu," (Pulsa S para Saír)","S");
                try {
                    switch(op) {
                        case 1:
                            candidatos();
                            break;
                        case 2:
                            traballadores();
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
