package utils;
public enum DiaSemana {
    LUNES(1,"lunes"),
    MARTES(2,"martes"),
    MIERCOLES(3,"miercoles"),
    JUEVES(4,"jueves"),
    VIERNES(5,"viernes"),
    SABADO(6,"sabado"),
    DOMINGO(7,"domingo");
    private final int codigo;
    private final String texto;

    DiaSemana(int codigo, String texto) {
        this.codigo = codigo;
        this.texto = texto;
    }

    public int getCodigo() {

        return codigo;
    }
    public String getTexto(){
        return texto;
    }
}
