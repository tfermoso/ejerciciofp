
public enum Provincia {
    ALAVA("01"),
    ALBACETE("02"),
    ALICANTE("03"),
    ALMERIA("04"),
    ASTURIAS("33"),
    AVILA("05"),
    BADAJOZ("06"),
    BARCELONA("08"),
    BURGOS("09"),
    CACERES("10"),
    CADIZ("11"),
    CANTABRIA("39"),
    CASTELLON("12"),
    CIUDAD_REAL("13"),
    CORDOBA("14"),
    CUENCA("15"),
    GERONA("17"),
    GRANADA("18"),
    GUADALAJARA("19"),
    GUIPUZCOA("20"),
    HUELVA("21"),
    HUESCA("22"),
    ISLAS_BALEARES("07"),
    JAEN("23"),
    A_CORUNA("15"),
    LA_RIOJA("26"),
    LAS_PALMAS("35"),
    LEON("24"),
    LLEIDA("25"),
    LUGO("27"),
    MADRID("28"),
    MALAGA("29"),
    MURCIA("30"),
    NAVARRA("31"),
    OURENSE("32"),
    PALENCIA("34"),
    PONTEVEDRA("36"),
    SALAMANCA("37"),
    SANTA_CRUZ_DE_TENERIFE("38"),
    SEGOVIA("40"),
    SEVILLA("41"),
    SORIA("42"),
    TARRAGONA("43"),
    TERUEL("44"),
    TOLEDO("45"),
    VALENCIA("46"),
    VALLADOLID("47"),
    VIZCAYA("48"),
    ZAMORA("49"),
    ZARAGOZA("50");

    private final String codigo;

    Provincia(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
    public static Provincia get(String valor){
        for(Provincia provincia: Provincia.values()){
            if(valor.equals(provincia.codigo) ){
                return provincia;
            }
        }
        return Provincia.valueOf(valor.toUpperCase());

    }

}
