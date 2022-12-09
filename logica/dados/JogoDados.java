package jogo.logica.dados;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import jogo.logica.Memento.IMementoOriginator;
import jogo.logica.Memento.Memento;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

class HistoricoTabuleiro {
    public final ArrayList<Circle [][]> historicoTabuleiro1;
    public final ArrayList<Circle [][]> historicoTabuleiro2;
    public final ArrayList<Circle [][]> historicoTabuleiro3;
    public final ArrayList<Circle [][]> historicoTabuleiro4;
    public final ArrayList<Circle [][]> historicoTabuleiro5;

    public HistoricoTabuleiro() {
        historicoTabuleiro1 = new ArrayList<>();
        historicoTabuleiro2 = new ArrayList<>();
        historicoTabuleiro3 = new ArrayList<>();
        historicoTabuleiro4 = new ArrayList<>();
        historicoTabuleiro5 = new ArrayList<>();
    }
}

public class JogoDados implements Serializable, IMementoOriginator {
    private final transient HistoricoTabuleiro Tabuleiros = new HistoricoTabuleiro();
    private final ArrayList<String> historicoJogadas1;
    private final ArrayList<String> historicoJogadas2;
    private final ArrayList<String> historicoJogadas3;
    private final ArrayList<String> historicoJogadas4;
    private final ArrayList<String> historicoJogadas5;
    private ArrayList<String> historico;
    private String []historicoJogosCompletos;
    private char [][]matrix;
    ArrayList<Jogador> players;
    private int Modo;
    private int jogadorAtivo;
    private Calculos valores;
    private String []palavras;
    private static int TotalJogadas;
    private static int TotalJogos;
    private static boolean VirouContador;

    public JogoDados()
    {
        historicoJogadas1 = new ArrayList<>();
        historicoJogadas2 = new ArrayList<>();
        historicoJogadas3 = new ArrayList<>();
        historicoJogadas4 = new ArrayList<>();
        historicoJogadas5 = new ArrayList<>();
        historico = new ArrayList<>();
        historicoJogosCompletos = new String[5];
        matrix = new char[6][7];
        players = new ArrayList<>();
        valores = new Calculos(0,0, ' ');
        palavras = new String[5];
        TotalJogadas = 1;
        TotalJogos = 0;
        VirouContador = false;




        for(int i=0; i<6; i++)
        {
            for(int j=0; j < 7; j++)
            {
                matrix[i][j] = '_';
            }
        }
    }

    public int getNFichasUndo(int player) { return players.get(player).getNFichasUndo(); }

    public void setFichasUndo(int player, int Num){ players.get(player).setNFichasUndo(Num); }

    public int getTotalJogos(){ return TotalJogos; }

    public boolean getVirouContador() { return VirouContador; }

    public String getPalavra(int num){
        return palavras[num];
    }

    public void setPalavrasMiniJogo(String p1, String p2, String p3, String p4, String p5)
    {
        this.palavras[0] = p1;
        this.palavras[1] = p2;
        this.palavras[2] = p3;
        this.palavras[3] = p4;
        this.palavras[4] = p5;
    }

    public String getNameJogador(int player) { return players.get(player).getName(); }

    public String getCorJogador(int player) { return players.get(player).getCor(); }

    public void setNpecasEspeciaisJogador(int player, int num)
    {
        players.get(player).setnPecasEspeciais(num);
    }

    public int getNpecasEspeciaisJogador(int player)
    {
       return players.get(player).getnPecasEspeciais();
    }

    public Calculos getValoresCalculos()
    {
        return valores;
    }

    public void setValoresCalculos(int v1, int v2, char op)
    {
        valores.setValores(v1, v2, op);
    }

    public int getJogadaMaquina()
    {
        return players.get(1).indicaJogada();
    }

    public void setModo(int x)
    {
        this.Modo = x;
    }

    public int getModo() { return this.Modo; }

    public char getSimboloJogador(int player){ return players.get(player).getSimbolo(); }

    public void setSimbolosJogadores(char Sp1, char Sp2)
    {
        players.get(0).setSimbolo(Sp1);
        players.get(1).setSimbolo(Sp2);
    }

    public void setCores(String color1, String color2) {
        players.get(0).setCor(color1);
        players.get(1).setCor(color2);
    }

    public int getNjogadasJogador(int player){ return players.get(player).getnJogadas();}

    public void setNjogadasJogador(int player, int njogadas) { players.get(player).setnjogadas(njogadas); }

    public int getJogadorAtivo()
    {
        return this.jogadorAtivo;
    }

    public char[][] getMatrix()
    {
        return this.matrix;
    }

    public void setMatrix(char [][]m)
    {
        this.matrix = m;
    }

    public void addPassagem(String passagem)
    {
        StringBuilder msgMatrix = new StringBuilder();

        //msgMatrix.append("\nEstado do tabuleiro: ");
        //for(int i=0; i<6; i++)
        //{
            //msgMatrix.append("\n").append(matrix[i][0]).append(" ").append(matrix[i][1]).append(" ").append(matrix[i][2]).append(" ").append(matrix[i][3]).append(" ").append(matrix[i][4]).append(" ").append(matrix[i][5]).append(" ").append(matrix[i][6]);
        //}

        //String msg = TotalJogadas + "ª Jogada: \n" + passagem + "\n" + msgMatrix + "\n\n";
        String msg = "\n " + TotalJogadas + "ª Jogada: \n" + " " + passagem;

        if(TotalJogos == 0) {
            historicoJogadas1.add(msg);
        }
        if(TotalJogos == 1) {
            historicoJogadas2.add(msg);
        }
        if(TotalJogos == 2) {
            historicoJogadas3.add(msg);
        }
        if(TotalJogos == 3) {
            historicoJogadas4.add(msg);
        }
        if(TotalJogos == 4) {
            historicoJogadas5.add(msg);
        }

        GuardaTablueiroJogo();

        TotalJogadas++;
    }

    public void GuardaTablueiroJogo(){

        Circle [][] circle = new Circle[6][7];

        for(int k=0; k<6; k++)
        {
            for(int h=0; h < 7; h++)
            {
                circle [k][h] = new Circle(7, Color.WHITE);
            }
        }
        for(int i=0; i<6; i++)
        {
            for(int j=0; j < 7; j++)
            {
                if(matrix[i][j] == 'X')
                    circle[i][j].setFill(Color.YELLOW);
                if(matrix[i][j] == 'O')
                    circle[i][j].setFill(Color.RED);
               if(matrix[i][j] == '_')
                    circle[i][j].setFill(Color.WHITE);
            }
        }

        if(TotalJogos == 0) {
            Tabuleiros.historicoTabuleiro1.add(circle);
        }
        if(TotalJogos == 1) {
           Tabuleiros.historicoTabuleiro2.add(circle);
        }
        if(TotalJogos == 2) {
            Tabuleiros.historicoTabuleiro3.add(circle);
        }
        if(TotalJogos == 3) {
            Tabuleiros.historicoTabuleiro4.add(circle);
        }
        if(TotalJogos == 4) {
           Tabuleiros.historicoTabuleiro5.add(circle);
        }
    }

    public ArrayList<String> getHistoricoJogadas(int jogo){
        if(jogo == 1)
            return historicoJogadas1;
        if(jogo == 2)
            return historicoJogadas2;
        if(jogo == 3)
            return historicoJogadas3;
        if(jogo == 4)
            return historicoJogadas4;
        if(jogo == 5)
            return historicoJogadas5;

        return null;
    }

    public ArrayList<Circle [][]> getHistoricoTabuleiro(int jogo){
        if(jogo == 1)
            return Tabuleiros.historicoTabuleiro1;
        if(jogo == 2)
            return Tabuleiros.historicoTabuleiro2;
        if(jogo == 3)
            return Tabuleiros.historicoTabuleiro3;
        if(jogo == 4)
            return Tabuleiros.historicoTabuleiro4;
        if(jogo == 5)
            return Tabuleiros.historicoTabuleiro5;

        return null;
    }

    public void setNames(String p1, String p2)
    {
        Jogador temp1, temp2 ;
        if(Modo == 1)
        {
            temp1 = new Pessoa(p1, 0, 0);
            temp2 = new Pessoa(p2, 0 , 0);
            players.add(temp1);
            players.add(temp2);
        }
        if(Modo == 2)
        {
            temp1 = new Pessoa(p1, 0, 0);
            temp2 = new Maquina(p2, 0);
            players.add(temp1);
            players.add(temp2);
        }
        if(Modo == 3)
        {
            temp1 = new Maquina(p1, 0);
            temp2 = new Maquina(p2, 0);
            players.add(temp1);
            players.add(temp2);
        }
    }

    public void setJogadorAtivo(int jogadorAtivo) {
        this.jogadorAtivo = jogadorAtivo;
    }

    public void trocaJogadorAtivo() {
        if(jogadorAtivo == 1)
            this.jogadorAtivo = 0;
        else
            this.jogadorAtivo = 1;
    }

    public void resetDados() {
        for(int i=0; i<6; i++)
        {
            for(int j=0; j < 7; j++)
            {
                this.matrix[i][j] = '_';
            }
        }

        this.players.remove(1);
        this.players.remove(0);

        if(VirouContador && TotalJogos == 0){
            for(int i=historicoJogadas1.size()-1; i>=0; i--) {
                this.historicoJogadas1.remove(i);
                this.Tabuleiros.historicoTabuleiro1.remove(i);
            }
        }

        if(VirouContador && TotalJogos == 1){
            for(int i=historicoJogadas2.size()-1; i>=0; i--) {
                this.historicoJogadas2.remove(i);
                this.Tabuleiros.historicoTabuleiro2.remove(i);
            }
        }

        if(VirouContador && TotalJogos == 2){
            for(int i=historicoJogadas3.size()-1; i>=0; i--) {
                this.historicoJogadas3.remove(i);
                this.Tabuleiros.historicoTabuleiro3.remove(i);
            }
        }

        if(VirouContador && TotalJogos == 3){
            for(int i=historicoJogadas4.size()-1; i>=0; i--) {
                this.historicoJogadas4.remove(i);
                this.Tabuleiros.historicoTabuleiro4.remove(i);
            }
        }

        if(VirouContador && TotalJogos == 4){
            for(int i=historicoJogadas5.size()-1; i>=0; i--) {
                this.historicoJogadas5.remove(i);
                this.Tabuleiros.historicoTabuleiro5.remove(i);
            }
        }


        TotalJogadas = 1;
    }


    public void GuardaHistoricoJogo()
    {
        StringBuilder temp = new StringBuilder();

        temp.append("\n\n").append("Histórico do ").append(TotalJogos + 1).append("º Jogo: \n\n");
        for(int i=0; i<historico.size(); i++)
            temp.append(historico.get(i));

        historicoJogosCompletos[TotalJogos] = temp.toString();

        TotalJogos++;

        if(TotalJogos == 5){
            TotalJogos = 0;
            VirouContador = true;
        }
    }

    public String getHistoricoCompletoJogo(int opcao) {
        return historicoJogosCompletos[opcao];
    }



    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        //sb.append("\n\n\nDETALHES DE JOGO:\n\n");
        for(Jogador a : players)
            sb.append(a.toString());


        //sb.append("\nEstado atual do tabuleiro:");
        //for(int i=0; i<6; i++)
        //{
          //  sb.append("\n").append(matrix[i][0]).append(" ").append(matrix[i][1]).append(" ").append(matrix[i][2]).append(" ").append(matrix[i][3]).append(" ").append(matrix[i][4]).append(" ").append(matrix[i][5]).append(" ").append(matrix[i][6]);
        //}

        sb.append("\nNeste Turno Joga: ").append(players.get(jogadorAtivo).getName()).append("\t Cor: ").append(players.get(jogadorAtivo).getCor()).append("\n\n\n");
        return sb.toString();
    }

    public String toString2(Circle[][] circulos){
        StringBuilder sb = new StringBuilder();

        //sb.append("\nDETALHES FINAIS DE JOGO:\n");
       // sb.append("\nEstado final do tabuleiro:");
        //for(int i=0; i<6; i++)
        //{
          //  sb.append("\n").append(matrix[i][0]).append(" ").append(matrix[i][1]).append(" ").append(matrix[i][2]).append(" ").append(matrix[i][3]).append(" ").append(matrix[i][4]).append(" ").append(matrix[i][5]).append(" ").append(matrix[i][6]);
        //}
        if(jogadorAtivo == 3)
            sb.append("\nNinguem venceu o jogo! ").append(" EMPATE!!");
        else
            sb.append("\nO vencedor do jogo é: ").append(players.get(jogadorAtivo).getName()).append("\t\t Cor: ").append(players.get(jogadorAtivo).getCor());

        int i, j;
        for(i=0; i<6; i++)
        {
            for(j=0; j < 7; j++)
            {
                if(matrix[i][j] == 'X')
                    circulos[i][j].setFill(Color.YELLOW);
                if(matrix[i][j] == 'O')
                    circulos[i][j].setFill(Color.RED);
                if(matrix[i][j] == '_')
                    circulos[i][j].setFill(Color.WHITE);
            }
        }

        return sb.toString();
    }

    public String toString3(){
        StringBuilder sb = new StringBuilder();

        sb.append("\n\n\nOPORTUNIDADE DE JOGAR MINI JOGO PARA GANHAR UMA PEÇA ESPECIAL!!\n");
        sb.append(" \nO jogador '").append(players.get(jogadorAtivo).getName()).append("' pretende jogar?");

        return sb.toString();
    }

    public String toString4(){
        StringBuilder sb = new StringBuilder();

        //sb.append("Qual o resultado de ").append(valores.valor1).append(" ").append(valores.operador).append(" ").append(valores.valor2).append(" ?");
        sb.append("Cálculo: ").append(valores.valor1).append(" ").append(valores.operador).append(" ").append(valores.valor2).append(" = ");

        return sb.toString();
    }

    public String toString5(int num)
    {
        StringBuilder sb = new StringBuilder();

        //sb.append("\nInsira a seguinte palavra: ").append(palavras[num]).append("\n");
        sb.append("Palavra a inserir: ").append(palavras[num]);

        return sb.toString();
    }

    public String toString6()
    {
        StringBuilder sb = new StringBuilder();
        if(historico.isEmpty())
        {
            sb.append("\n\n\n\n\n\n\nHISTÓRICO DO JOGO: \n\n");
            sb.append("Ainda não existem jogadas a registar!");
            sb.append("\n\n\n\n\n\n\n");
            return sb.toString();
        }
        else {
            sb.append("\n\n\n\n\n\n\nHISTÓRICO DO JOGO: \n\n");
            for (int i = 0; i < historico.size(); i++) {
                sb.append("\n").append(historico.get(i));
            }
            sb.append("\n\n\n\n\n\n\n");
            return sb.toString();
        }
    }



    @Override
    public Memento getMemento() throws IOException {
       Memento m = new Memento(this);
        return m;
    }

    @Override
    public void setMemento(Memento m, int flag, int tirar, Circle[][] circulos) throws IOException, ClassNotFoundException {
        int PEspeciais0 = players.get(0).getnPecasEspeciais();
        int PEspeciais1 = players.get(1).getnPecasEspeciais();
        int Nfichas0 = players.get(0).getNFichasUndo();
        int Nfichas1 = players.get(1).getNFichasUndo();
        int Njogadas0 = players.get(0).getnJogadas();
        int Njogadas1 = players.get(1).getnJogadas();
        ArrayList<String> tempHistorico = historico;
        int JogadorActive = jogadorAtivo;

        JogoDados temp;
        temp = (JogoDados) m.getSnapshot();

        historico = tempHistorico;
        historicoJogosCompletos = temp.historicoJogosCompletos;
        matrix = temp.matrix;
        players = temp.players;
        Modo = temp.Modo;
        jogadorAtivo = temp.jogadorAtivo;
        valores = temp.valores;
        palavras = temp.palavras;

        int i, j;
        for(i=0; i<6; i++)
        {
            for(j=0; j < 7; j++)
            {
                if(matrix[i][j] == 'X')
                    circulos[i][j].setFill(Color.YELLOW);
                if(matrix[i][j] == 'O')
                    circulos[i][j].setFill(Color.RED);
                if(matrix[i][j] == '_')
                    circulos[i][j].setFill(Color.WHITE);
            }
        }

        if(flag == 0 && tirar == 1) {
            if(JogadorActive == 0) {
                //O que jogou
                Nfichas0 = Nfichas0 - 1;
                temp.setFichasUndo(JogadorActive, Nfichas0);
                temp.setNjogadasJogador(JogadorActive, 0);
                temp.setNpecasEspeciaisJogador(JogadorActive, PEspeciais0);

                //O que não jogou
                temp.setFichasUndo(1, Nfichas1);
                temp.setNjogadasJogador(1, Njogadas1);
                temp.setNpecasEspeciaisJogador(1, PEspeciais1);
            }
            else {
                //O que jogou
                Nfichas1 = Nfichas1 - 1;
                temp.setFichasUndo(JogadorActive, Nfichas1);
                temp.setNjogadasJogador(JogadorActive, 0);
                temp.setNpecasEspeciaisJogador(JogadorActive, PEspeciais1);

                //O que não jogou
                temp.setFichasUndo(0, Nfichas0);
                temp.setNjogadasJogador(0, Njogadas0);
                temp.setNpecasEspeciaisJogador(0, PEspeciais0);
            }
        }
        if(flag == 1 && tirar == 1) {
            if(JogadorActive == 0) {

                //O que jogou
                Nfichas1 = Nfichas1 - 1;
                temp.setFichasUndo(1, Nfichas1);
                temp.setNjogadasJogador(1, 0);
                temp.setNpecasEspeciaisJogador(1, PEspeciais1);

                //O que não jogou
                temp.setFichasUndo(0, Nfichas0);
                temp.setNjogadasJogador(0, (Njogadas0+1));
                temp.setNpecasEspeciaisJogador(0, PEspeciais0);
            }
            else {

                //O que jogou
                Nfichas0 = Nfichas0 - 1;
                temp.setFichasUndo(0, Nfichas0);
                temp.setNjogadasJogador(0, 0);
                temp.setNpecasEspeciaisJogador(0, PEspeciais0);

                //O que não jogou
                temp.setFichasUndo(1, Nfichas1);
                temp.setNjogadasJogador(1, (Njogadas1+1));
                temp.setNpecasEspeciaisJogador(1, PEspeciais1);
            }
        }
    }

    public boolean CarregaDadosJogo(String file, Circle[][] circulos) {
        int i, j;
        String Player1 = players.get(0).getName();
        String Player2 = players.get(1).getName();
        try {
            JogoDados temp;
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            temp = (JogoDados) objectIn.readObject();

            if(temp.Modo != this.Modo)
                return false;
            else{
                historico = temp.historico;
                matrix = temp.matrix;
                players = temp.players;
                players.get(0).setName(Player1);
                players.get(1).setName(Player2);
                Modo = temp.Modo;
                jogadorAtivo = temp.jogadorAtivo;
                valores = temp.valores;
                palavras = temp.palavras;
            }

            for(i=0; i<6; i++)
            {
                for(j=0; j < 7; j++)
                {
                    if(matrix[i][j] == 'X')
                        circulos[i][j].setFill(Color.YELLOW);
                    if(matrix[i][j] == 'O')
                        circulos[i][j].setFill(Color.RED);
                    if(matrix[i][j] == '_')
                        circulos[i][j].setFill(Color.WHITE);
                }
            }

            objectIn.close();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


}


