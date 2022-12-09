package jogo.logica;

import javafx.scene.shape.Circle;
import jogo.logica.Memento.ICareTaker;
import jogo.logica.Memento.Memento;
import jogo.logica.dados.JogoDados;
import jogo.logica.estados.EscolheModo;
import jogo.logica.estados.IJogoEstado;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Jogo implements ICareTaker {
    private IJogoEstado estado;
    private JogoDados dados;
    private Stack<Memento> stackHist = new Stack<>();
    private static final String file = "Loads.txt";
    private List<PropsID> eventos = new ArrayList<>();

    public Jogo() {
        dados = new JogoDados();
        estado = new EscolheModo(dados);
    }

    private boolean defineEstado(IJogoEstado prox){
        IJogoEstado anterior = estado;
        estado = prox;
        if(anterior!=prox){
            eventos.add(PropsID.PROP_ESTADO);
            return true;
        }
        return false;
    }

    public List<PropsID> IndicaModo(int x, String p1, String p2) {
        eventos.clear();
        defineEstado(estado.setModo(x, p1, p2));
        return eventos;
    }

    public JogoSituacao getSituacaoAtual() {
        return estado.getSituacaoAtual();
    }

    public int getModoJogo() {
        return dados.getModo();
    }

    public int getJogadorAtivo() {
        return dados.getJogadorAtivo();
    }

    public void sortiaJogadorInicial() {
        estado.sorteiaJogadores();
    }

    public List<PropsID> colocaPeca(int temp, int flag, Circle[][] circulos) {
        eventos.clear();
        if(!defineEstado(estado.colocaPeca(temp, flag, circulos)))
            eventos.add(PropsID.PROP_PLAY);
        return eventos;
    }

    public List<PropsID> VerificaEmpate() {
        eventos.clear();
        defineEstado(estado.verificaEmpate());
        return eventos;
    }

    public boolean VerificaColunaPreenchida(int coluna) {
        return estado.VerificaColunaPreenchida(coluna);
    }

    public boolean VerificaMiniJogo() {
        return estado.VerificaMiniJogo();
    }

    public List<PropsID> ExecutaOpcao(int opcao) {
        eventos.clear();
        defineEstado(estado.ExecutaOpcao(opcao));
        return eventos;
    }

    public int SorteiaJogo() {
        return estado.SorteiaJogo();
    }

    public void IniciaTimerMiniJogoCalculos() {
        estado.IniciaTimerMiniJogoCalculos();
    }

    public void GeraCalculos() {
        estado.GeraDados();
    }

    public boolean AvaliaResposta(int temp) {
        return estado.AvaliaResposta(temp);
    }

    public List<PropsID> VerificaVitoriaMiniJogoCalculos() {
        eventos.clear();
       defineEstado(estado.VerificaVitoriaMiniJogoCalculos());
       return eventos;
    }

    public void GeraPalvras() {
        estado.GeraPalavrasMiniJogo();
    }

    public boolean VerificaResposta(String temp, int num) {
        return estado.VerificaResposta(temp, num);
    }

    public List<PropsID> VerificaVitoriaMiniJogoPalavras() {
        eventos.clear();
       defineEstado(estado.VerificaVitoriaMiniJogoPalavras());
       return eventos;
    }

    public void IniciaTimerMiniJogoPalavras() {
        estado.IniciaTimerMiniJogoPalavras();
    }

    public boolean VericaJogadorTemPeca() {
        return estado.VericaJogadorTemPeca();
    }

    public int colocaPecaEspecial(int i, Circle[][] circulos) { return estado.colocaPecaEspecial(i, circulos); }

    public List<PropsID> IniciaNovoJogo() {
        eventos.clear();
        defineEstado(estado.IniciaNovoJogo());
        return eventos;
    }


    public String toString() {
        return dados.toString();
    }

    public String toStringTerminoDeJogo(Circle[][] circulos) {
        return dados.toString2(circulos);
    }

    public String toStringMiniJogo() {
        return dados.toString3();
    }

    public String toStringMiniJogoCalculos() {
        return dados.toString4();
    }

    public String toStringMiniJogoPalavras(int num) {
        return dados.toString5(num);
    }

    public String toStringHistorico() {
        return dados.toString6();
    }

    public String ConsultaHistóricoJogoPassado(int numjogo) {
        return estado.ConsultaHistóricoJogoPassado(numjogo);
    }





    @Override
    public void saveMemento() {
        try {
            stackHist.push(dados.getMemento());
        } catch (IOException ex) {
            System.err.println("gravaMemento: " + ex);
            stackHist.clear();
        }
    }

    @Override
    public boolean undo(int flag, Circle[][] circulos) {
        int tirar=0;
        if (stackHist.isEmpty() || dados.getNFichasUndo(dados.getJogadorAtivo()) <= 0 || flag < 2) {
            return false;
        }

        //for(int i=0; i<2; i++) {
            if (stackHist.isEmpty() || dados.getNFichasUndo(dados.getJogadorAtivo()) <= 0 || flag == 0) {
                return false;
            }
            //if (i==1)
                tirar=1;
           try {
               Memento anterior = stackHist.pop();
               dados.setMemento(anterior, (flag%2), tirar, circulos);
           } catch (IOException | ClassNotFoundException ex) {
               System.err.println("undo: " + ex);
               stackHist.clear();
           }
        //}


        return true;
    }

    public void LimpaUndo(){
        stackHist.clear();
    }

    public boolean CarregaJogo(Circle[][] circulos) {
        if(dados.CarregaDadosJogo(file, circulos))
            return true;
        else
            return false;
    }

    public boolean GuardaJogo(){
        try {

            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(dados);
            objectOut.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public ArrayList<String> consultaHistoricoJogadas(int i){
        return dados.getHistoricoJogadas(i);
    }

    public ArrayList<Circle[][]> consultaHistoricoTabuleiro(int i){
        return dados.getHistoricoTabuleiro(i);
    }

    public boolean VerificaIntegridadeJogoAconsultar(int numJogo){ return estado.VerificaIntegridadeJogoAconsultar(numJogo); }
}




