/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package company;

import java.time.format.DateTimeFormatter;

/**
 *
 * @author xavi
 */
public class Sheets {
    public static void workerSheet(Traballador t) {
        curriculumSheet(t);
        System.out.println("Numero de Seguridade Social: "+t.getNss());
        System.out.println("Conta Bancaria: "+t.getIban()+" ["+t.getIban().getBank()+"]");
        System.out.println("Data de Alta: "+t.getDataAlta().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        System.out.printf("Salario Mensual: %.4f",t.getSalario());
    }
    
    public static void curriculumSheet(Candidato t) {
        System.out.println("NIF: "+t.getDni());
        System.out.println("Apelidos e Nome: "+t.getAnome());
        System.out.println("Dirección: "+t.getDireccion());
        System.out.println("Teléfono: "+t.getTelefono());
        System.out.println("Email: "+t.getEmail());
    }
    
    public static void listSheet(Object[] data) {
        if (data==null || data.length==0) System.out.println("Non existen elementos");
        else            for(Object o: data) System.out.println(data);
    }
}
