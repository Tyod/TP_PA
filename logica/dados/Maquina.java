package jogo.logica.dados;

public class Maquina extends Jogador{

    public Maquina(String name, int jogadas)
    {
        super(name, jogadas);
    }


    public int getnPecasEspeciais() { return 0; }

    public int indicaJogada()
    {
        int x;
        x = (int) (Math.random()*7);
        return x;
    }

    public void setnPecasEspeciais(int nPecasEspeciais) { return; }
}
