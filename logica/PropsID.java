package jogo.logica;

public enum PropsID {
    PROP_ESTADO("prop_estado"),
    PROP_PLAY("prop_play");
    String valor;
    PropsID(String s) { valor = s; }
    @Override
    public String toString() {
        return valor;
    }
}
