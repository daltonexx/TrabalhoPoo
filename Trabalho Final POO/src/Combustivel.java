public enum Combustivel{
    ALCOOL(0,"Alcool"), DIESEL(1,"Diesel"), GASOLINA(2,"Gasolina");
    private int valor;
    private String combustivel;
    Combustivel(int valor, String combustivel) {
        this.valor = valor;
        this.combustivel = combustivel;
    }

    public int getValor(){
        return valor;
    }
    public String getCombustivel(){
        return combustivel;
    }
    public static Combustivel getEnum(int index){
        for(Combustivel c : values()){
            if(c.valor == index){
                return c;
            }
        }
        return null;
    }
}
