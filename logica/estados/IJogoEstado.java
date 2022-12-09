package jogo.logica.estados;

import javafx.scene.shape.Circle;
import jogo.logica.JogoSituacao;

public interface IJogoEstado {

    IJogoEstado setModo(int x, String p1, String p2);
    void sorteiaJogadores();
    IJogoEstado colocaPeca(int x, int flag, Circle[][] circulos);
    IJogoEstado verificaEmpate();
    boolean VerificaColunaPreenchida(int coluna);
    boolean VerificaMiniJogo();
    IJogoEstado ExecutaOpcao(int opcao);
    int SorteiaJogo();
    void IniciaTimerMiniJogoCalculos();
    void GeraDados();
    boolean AvaliaResposta(int temp);
    IJogoEstado VerificaVitoriaMiniJogoCalculos();
    void GeraPalavrasMiniJogo();
    boolean VerificaResposta(String temp, int num);
    IJogoEstado VerificaVitoriaMiniJogoPalavras();
    void IniciaTimerMiniJogoPalavras();
    boolean VericaJogadorTemPeca();
    int colocaPecaEspecial(int i, Circle[][] circulos);
    IJogoEstado IniciaNovoJogo();
    String ConsultaHistóricoJogoPassado(int numJogo);
    boolean VerificaIntegridadeJogoAconsultar(int numJogo);

    JogoSituacao getSituacaoAtual();
}
