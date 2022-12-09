package jogo.logica;

import javafx.scene.shape.Circle;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class JogoObs {

    private Jogo game = new Jogo();
    private PropertyChangeSupport props = new PropertyChangeSupport(game);



    private void disparaEventos(List<PropsID> eventos){
        eventos.forEach((e)-> {
            props.firePropertyChange(e.toString(), null, null);
        });
    }

    public void registaPropertyChangeListener(PropsID prop, PropertyChangeListener listener){
        props.addPropertyChangeListener(prop.toString(), listener);
    }

    public JogoSituacao getSituacao() {
        return game.getSituacaoAtual();
    }

    public void indicaModo(int x, String p1, String p2) {
        disparaEventos(game.IndicaModo(x, p1, p2));
    }

    public void ColocaPeça(int coluna, Circle[][] circulos) {

        if(game.getModoJogo() == 1) {
            disparaEventos(game.colocaPeca(coluna-1, 0, circulos));
        }

        if(game.getModoJogo() == 2){
            if(game.getJogadorAtivo() == 0)
                disparaEventos(game.colocaPeca(coluna-1, 0, circulos));
            if(game.getJogadorAtivo() == 1)
                disparaEventos(game.colocaPeca(coluna-1, 1, circulos));
        }

        if(game.getModoJogo() == 3){
            disparaEventos(game.colocaPeca(coluna-1, 1, circulos));
        }
    }
    public void VerificaEmpate() { disparaEventos(game.VerificaEmpate()); }


    public boolean ColocaPeçaEspecial(int coluna, Circle[][] circulos) {

        if(game.VericaJogadorTemPeca()){
            game.colocaPecaEspecial(coluna-1, circulos);
            return true;
        }

        return false;
    }

    public int getModo() {
        return game.getModoJogo();
    }

    public String toStringPlayers() {
        return game.toString();
    }

    public void GuardaJogo() {
        game.GuardaJogo();
    }

    public boolean CarregaJogo(Circle[][] circulos){
        return game.CarregaJogo(circulos);
    }

    public boolean Undo(int pecas ,Circle[][] circulos) {
        return game.undo(pecas, circulos);
    }

    public void SaveMemento() { game.saveMemento(); }

    public boolean VerificaMiniJogo() {
        if(game.getModoJogo() == 2 && game.getJogadorAtivo() == 0){
            return game.VerificaMiniJogo();
        }
        if(game.getModoJogo() ==  1)
            return game.VerificaMiniJogo();

        return false;
    }

    public void executaOpcao(int Opcao){ disparaEventos(game.ExecutaOpcao(Opcao)); }

    public int SorteiaMiniJogo() { return game.SorteiaJogo(); }

    public void GeraCalculos() { game.GeraCalculos(); }

    public String toStringMiniJogoCalculos() { return game.toStringMiniJogoCalculos(); }

    public void GeraPalavras() { game.GeraPalvras(); }

    public  String toStringMiniJogoPalavras(int n) { return game.toStringMiniJogoPalavras(n); }

    public void IniciaTimerMiniJogoPalavras() { game.IniciaTimerMiniJogoPalavras(); }

    public void IniciaTimerMiniJogoCalculos() { game.IniciaTimerMiniJogoCalculos(); }

    public boolean AvaliaResposta(int resposta){ return game.AvaliaResposta(resposta); }

    public void VerificaVitoriaMiniJogoCalculos() {
        disparaEventos(game.VerificaVitoriaMiniJogoCalculos());
    }

    public boolean VerificaResposta(String resposta, int n) { return game.VerificaResposta(resposta, n); }

    public void VerificaVitoriaMiniJogoPalavras() {
        disparaEventos(game.VerificaVitoriaMiniJogoPalavras());
    }

    public String toStringTerminoDeJogo(Circle[][] circulos) { return game.toStringTerminoDeJogo(circulos); }

    public void IniciaNovoJogo() { disparaEventos(game.IniciaNovoJogo()); }

    public ArrayList<String> consultaHistoricoJogadas(int i){
        return game.consultaHistoricoJogadas(i);
    }

    public ArrayList<Circle[][]> consultaHistoricoTabuleiro(int i){
        return game.consultaHistoricoTabuleiro(i);
    }

    public int getJogadorAtivo(){
        return game.getJogadorAtivo();
    }
    public boolean VerificaIntegridadeJogoAconsultar(int numJogo){ return game.VerificaIntegridadeJogoAconsultar(numJogo-1); }
}
