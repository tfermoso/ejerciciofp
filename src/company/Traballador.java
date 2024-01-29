package company;

import utils.Iban;
import utils.NSS;
import utils.Nif;

import java.time.LocalDate;

public class Traballador extends Candidato{

    private NSS nss;
    private LocalDate fechaAlta;
    private Iban iban;
    private double salario;

    public Traballador(Nif dni, String apellidosNombre, String direccion, String telefono, String email, NSS nss, LocalDate fechaAlta, Iban iban, double salario) {
        super(dni, apellidosNombre, direccion, telefono, email);
        this.nss = nss;
        this.fechaAlta = fechaAlta;
        this.iban = iban;
        this.salario = salario;
    }
    public Traballador(Traballador trabajador){
        super(trabajador.getDni(),trabajador.apellidosNombre,trabajador.direccion,trabajador.telefono,trabajador.email);
        this.nss=trabajador.nss;
        this.fechaAlta=trabajador.fechaAlta;
        this.iban=trabajador.iban;
        this.salario=trabajador.salario;
    }

    public Traballador(Candidato candidato, LocalDate dataAlta, String nss, String iban, double salario) {
        super(candidato);
        this.fechaAlta=dataAlta;
        this.nss=new NSS(nss);
        this.iban=Iban.from(iban);
        this.salario=salario;
    }

    public NSS getNss() {
        return nss;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public Iban getIban() {
        return iban;
    }

    public double getSalario() {
        return salario;
    }

    public void setIban(Iban iban) {
        this.iban = iban;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public LocalDate getDataAlta() {
        return fechaAlta;
    }
}
