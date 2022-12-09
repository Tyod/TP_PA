package jogo.logica.estados;

import javafx.scene.shape.Circle;
import jogo.logica.dados.JogoDados;

public abstract class JogoEstadoAdpter implements IJogoEstado
{
    protected JogoDados dados;

    protected JogoEstadoAdpter(JogoDados dados)
    {
        this.dados = dados;
    }

    public IJogoEstado verificaEmpate(){ return this; }
    public IJogoEstado colocaPeca(int x, int flag, Circle[][] circulos) { return this; }
    public void sorteiaJogadores(){}
    public boolean avaliaJogada(char [][]matrix,int row,int column){ return false; }
    public IJogoEstado setModo(int x, String p1, String p2)
    {
        return this;
    }
    public boolean VerificaColunaPreenchida(int coluna){ return false; }
    public boolean VerificaMiniJogo(){ return false; }
    public IJogoEstado ExecutaOpcao(int opcao){ return this; }
    public int SorteiaJogo(){ return 0; }
    public void IniciaTimerMiniJogoCalculos(){}
    public void GeraDados(){}
    public boolean AvaliaResposta(int temp){ return false; }
    public IJogoEstado VerificaVitoriaMiniJogoCalculos(){ return this; }
    public void GeraPalavrasMiniJogo(){}
    public boolean VerificaResposta(String temp, int num){ return false; }
    public IJogoEstado VerificaVitoriaMiniJogoPalavras() { return this; }
    public void IniciaTimerMiniJogoPalavras(){}
    public boolean VericaJogadorTemPeca(){ return false; }
    public int colocaPecaEspecial(int coluna, Circle[][] circulos){ return 0; }
    public IJogoEstado IniciaNovoJogo(){ return this; }
    public String ConsultaHistóricoJogoPassado(int numJogo){ return "";}
    public boolean VerificaIntegridadeJogoAconsultar(int numJogo){ return false; }
}
