package jogo.logica.dados;

import java.io.Serializable;

public abstract class Jogador implements Serializable {

    protected  String name;
    protected int nJogadas;
    protected char simbolo;
    protected int NFichasUndo;
    protected String cor;

    public Jogador(String name, int jogadas)
    {
        this.name = name;
        this.nJogadas = jogadas;
        NFichasUndo = 5;
    }

    public  char getSimbolo() {return this.simbolo; }
    public String getName(){ return this.name; }
    public String getCor() { return this.cor; }
    public int getnJogadas(){ return this.nJogadas; }
    public int getNFichasUndo(){ return this.NFichasUndo; }
    public void setNFichasUndo(int Num) { this.NFichasUndo = Num; }
    public void setnjogadas(int x)
    {
        if(x == 5)
            x=0;
        this.nJogadas = x;
    }

    public void setName(String x) { this.name = x; }
    public void setSimbolo(char x) { this.simbolo = x; }
    public void setCor(String color) { this.cor = color; }

    public abstract int indicaJogada();
    public abstract int getnPecasEspeciais();
    public abstract void setnPecasEspeciais(int nPecasEspeciais);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append(name).append(" -> ").append(" Rondas até Mini Jogo: ").append(nJogadas).append("/4 ").append(" -> ").append(" Quantidade de peças especiais: ").append(getnPecasEspeciais()).append(" -> ").append(" Nº de fichas para retroceder: ").append(NFichasUndo).append(" -> ").append(" Cor: ").append(cor).append(" \n");
        return sb.toString();
    }


}
