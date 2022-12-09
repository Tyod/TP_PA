package jogo.logica.estados;

import jogo.logica.JogoSituacao;
import jogo.logica.dados.JogoDados;

public class TerminoDeJogo extends JogoEstadoAdpter{

    public TerminoDeJogo(JogoDados dados)
    {
        super(dados);
    }

    public IJogoEstado IniciaNovoJogo(){

        dados.resetDados();
        return new EscolheModo(dados);
    }
    public boolean VerificaIntegridadeJogoAconsultar(int numJogo){
        if(numJogo > (dados.getTotalJogos()-1) && !dados.getVirouContador())
            return false;
        else
            return true;
    }

    public String ConsultaHistóricoJogoPassado(int numJogo){
        String temp;

        if(VerificaIntegridadeJogoAconsultar(numJogo))
            temp = dados.getHistoricoCompletoJogo(numJogo);
        else
            temp = "\n\nO jogo a consultar ainda não decorreu! \n\n\n";

        return temp;
    }

    public JogoSituacao getSituacaoAtual() {
        return JogoSituacao.Termino_de_Jogo;
    }
}
