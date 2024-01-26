package company;

import java.time.LocalDate;
import utils.CancelException;


public class Forms {
    public static Candidato formCandidato() throws CancelException {
        String dni;
        String anome;
        String direccion;
        String telefono;
        String email;
        
        System.out.println("Novo Candidato");
        System.out.println("----------------");
        dni=Input.inputNif("DNI: ");
        anome=Input.input("Apelidos e Nome: ");
        direccion=Input.input("Dirección: ");
        telefono=Input.input("Telefono: ");
        
        
        email=Input.inputEMail("Email: ");     
        return new Candidato(dni,anome,direccion,telefono,email);
    }
    
    public static Traballador formTraballador(Candidato candidato) throws CancelException {
        System.out.println("Contrato de Traballador");
        System.out.println("-----------------------");
        System.out.println(candidato.getDni());
        System.out.println(candidato.getAnome());
        System.out.println(candidato.getDireccion());
        System.out.println(candidato.getEmail());
        String nss=Input.inputNSS("Número da SS: ");
        String iban=Input.inputIBAN("IBAN da Conta Bancaria: ");
        double salario=Input.inputDouble("Salario bruto mensual: ",1080.0);
        LocalDate dataAlta=Input.inputDate("Data de Alta: ",null,LocalDate.now(),"d/M/y");
        return new Traballador(candidato,dataAlta,nss,iban,salario);
    }

}
