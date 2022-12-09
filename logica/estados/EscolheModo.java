package jogo.logica.estados;

import jogo.logica.JogoSituacao;
import jogo.logica.dados.JogoDados;

public class EscolheModo extends JogoEstadoAdpter{

    public EscolheModo(JogoDados dados)
    {
       super(dados);
    }

    public IJogoEstado setModo(int x, String p1, String p2)
    {
            dados.setModo(x);
            dados.setNames(p1, p2);
            dados.setSimbolosJogadores('X', 'O');
            dados.setCores("Amarelo", "Vermelho");
            return new JogoNoDecorrer(dados);
    }

    public void sorteiaJogadores() {
        int temp = (int)(Math.random()*2);
        dados.setJogadorAtivo(temp);
    }

    public JogoSituacao getSituacaoAtual() {
        return JogoSituacao.Escolha_Modo;
    }
}
