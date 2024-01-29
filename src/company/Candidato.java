package company;

import utils.Nif;

public class Candidato {
    private Nif dni;
    protected String apellidosNombre;
    protected String direccion;
    protected String telefono;
    protected String email;

    public Candidato(Nif dni, String apellidosNombre, String direccion, String telefono, String email) {
        this.dni = dni;
        this.apellidosNombre = apellidosNombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }
    public Candidato(String dni, String apellidosNombre, String direccion, String telefono, String email) {
        this.dni = new Nif(dni);
        this.apellidosNombre = apellidosNombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }
    public Candidato(Candidato candidato) {
        this.dni=candidato.dni;
        this.apellidosNombre=candidato.apellidosNombre;
        this.direccion=candidato.direccion;
        this.telefono=candidato.telefono;
        this.email=candidato.email;
    }

    public Nif getDni() {
        return dni;
    }

    public String getApellidosNombre() {
        return apellidosNombre;
    }

    public void setApellidosNombre(String apellidosNombre) {
        this.apellidosNombre = apellidosNombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "[" + dni +"] "+ apellidosNombre;
    }

    public String getAnome() {
        return "[" + dni +"] "+ apellidosNombre;
    }
}
