package jogo.logica.dados;

public class Pessoa extends Jogador{

    private int nPecasEspeciais;

    public Pessoa(String name, int jogadas, int nPecasEspeciais)
    {
        super(name, jogadas);
        this.nPecasEspeciais = nPecasEspeciais;
    }

    public int indicaJogada() {
        return 0;
    }

    public int getnPecasEspeciais() { return nPecasEspeciais; }

    public void setnPecasEspeciais(int nPecasEspeciais) { this.nPecasEspeciais = nPecasEspeciais; }
}
