package jogo.logica.estados;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import jogo.logica.JogoSituacao;
import jogo.logica.dados.JogoDados;

public class JogoNoDecorrer extends JogoEstadoAdpter{

   public JogoNoDecorrer(JogoDados dados)
   {
       super(dados);
   }

    public IJogoEstado verificaEmpate()
    {
        char [][]tempMatrix = dados.getMatrix();

        for(int i=0; i< 6; i++)
        {
            for(int j=0; j< 7; j++)
            {
                if(tempMatrix[i][j] == '_')
                    return this;
            }
        }

        dados.setJogadorAtivo(3);
        return new TerminoDeJogo(dados);
    }

    public boolean VerificaColunaPreenchida(int coluna)
    {
        char [][]tempMatrix = dados.getMatrix();

        if(tempMatrix[0][coluna] == '_')
            return false;
        else
            return true;
    }

    public boolean avaliaJogada(char [][]matrix,int row, int column)
    {
        int streak = 0;
        int z, linha, coluna, x=0 , y=3, stopPoint, linhaFinal=0;
        char anterior;

        anterior = dados.getSimboloJogador(dados.getJogadorAtivo());

        //Check Vertical
        for(int i=0; i < 6; i++)
        {
            if(matrix[i][column] == anterior)
            {
                streak++;
                if(streak == 4) {
                    dados.addPassagem("Jogador: " + dados.getNameJogador(dados.getJogadorAtivo()) +  " // Linha: " + (row+1) + "  // Coluna: " + (column+1) + " // Cor: " + dados.getCorJogador(dados.getJogadorAtivo()) + " // (Peça Normal)");
                    dados.GuardaHistoricoJogo();
                    return true;
                }
            }
            else
                streak = 0;
        }

        streak = 0;

        //Check horizontal
        for(int j=0; j < 7; j++)
        {
            if(matrix[row][j] == anterior)
            {
                streak++;
                if(streak == 4) {
                    dados.addPassagem("Jogador: " + dados.getNameJogador(dados.getJogadorAtivo()) +  " // Linha: " + (row+1) + "  // Coluna: " + (column+1) + " // Cor: " + dados.getCorJogador(dados.getJogadorAtivo()) + " // (Peça Normal)");
                    dados.GuardaHistoricoJogo();
                    return true;
                }
            }
            else
                streak = 0;
        }

        //Check Diagonal Esquerda->Direita
        for(z=1; z<=6; z++)
        {
            coluna = x;
            linha = y;
            stopPoint = 100;
            streak = 0;

            while(stopPoint >= linhaFinal)
            {
                if(matrix[linha][coluna] == anterior)
                {
                    streak++;
                    if(streak == 4) {
                        dados.addPassagem("Jogador: " + dados.getNameJogador(dados.getJogadorAtivo()) +  " // Linha: " + (row+1) + "  // Coluna: " + (column+1) + " // Cor: " + dados.getCorJogador(dados.getJogadorAtivo()) + " // (Peça Normal)");
                        dados.GuardaHistoricoJogo();
                        return true;
                    }
                }
                else
                    streak = 0;

                linha--;
                coluna++;
                stopPoint=linha;
            }

            if(z>=3)
            {
                x++;
                if(z>=4)
                linhaFinal++;
            }
            else
                y++;
        }

        x=3;
        y=0;
        linhaFinal=3;

        //Check Diagonal Direita->Esquerda
        for(z=1; z<=6; z++)
        {
            coluna = x;
            linha = y;
            stopPoint = -100;
            streak = 0;

            while(stopPoint <= linhaFinal)
            {
                if(matrix[linha][coluna] == anterior)
                {
                    streak++;
                    if(streak == 4) {
                        dados.addPassagem("Jogador: " + dados.getNameJogador(dados.getJogadorAtivo()) +  " // Linha: " + (row+1) + "  // Coluna: " + (column+1) + " // Cor: " + dados.getCorJogador(dados.getJogadorAtivo()) + " // (Peça Normal)");
                        dados.GuardaHistoricoJogo();
                        return true;
                    }
                }
                else
                    streak = 0;

                linha++;
                coluna++;
                stopPoint=linha;
            }

            if(z>=3)
            {
                if(z==3)
                  x--;
                if(z>=4)
                  y++;
            }
            else
            {
                linhaFinal++;
                x--;
            }
        }


        return false;
    }

    public IJogoEstado colocaPeca(int x, int flag, Circle[][] circulos) {
        int i, k, j;
        char [][]tempMatrix = dados.getMatrix();
        char simboloJogadorAtivo = dados.getSimboloJogador(dados.getJogadorAtivo());

        if(flag == 1){
            do {
                x = dados.getJogadaMaquina();
            }while (VerificaColunaPreenchida(x));
        }

        for(i=5; i>=0; i--)
        {
            if(tempMatrix[i][x] == '_')
            {
                    tempMatrix[i][x] = simboloJogadorAtivo;

                    for(k=0; k<6; k++)
                    {
                       for(j=0; j < 7; j++)
                       {
                           if(tempMatrix[k][j] == 'X')
                               circulos[k][j].setFill(Color.YELLOW);
                           if(tempMatrix[k][j] == 'O')
                               circulos[k][j].setFill(Color.RED);
                       }
                   }

                    dados.setNjogadasJogador(dados.getJogadorAtivo(), (dados.getNjogadasJogador(dados.getJogadorAtivo())+1));
                    dados.setMatrix(tempMatrix);
                    if (avaliaJogada(tempMatrix, i, x))
                        return new TerminoDeJogo(dados);

                    dados.addPassagem("Jogador: " + dados.getNameJogador(dados.getJogadorAtivo()) +  " // Linha: " + (i+1) + "  // Coluna: " + (x+1) + " // Cor: " + dados.getCorJogador(dados.getJogadorAtivo()) + " // (Peça Normal)");
                    dados.trocaJogadorAtivo();
                    break;
            }
        }

        return this;
    }

    public boolean VerificaMiniJogo(){
       if(dados.getNjogadasJogador(dados.getJogadorAtivo()) == 4)
           return true;
       else
           return false;
    }

    public IJogoEstado ExecutaOpcao(int opcao){
       if(opcao == 1)
           return new MiniJogo(dados);
       else
           dados.setNjogadasJogador(dados.getJogadorAtivo(), 0);
           return this;
    };

    public boolean VericaJogadorTemPeca(){
        if(dados.getNpecasEspeciaisJogador(dados.getJogadorAtivo()) >= 1)
            return true;
        else
            return false;
    }

    public int colocaPecaEspecial(int coluna, Circle[][] circulos){
        int j=0, h, k;
        char[][]temp = dados.getMatrix();

        for(int i=0; i < 6; i++) {
            if(temp[i][coluna] != '_')
                j++;
            temp[i][coluna] = '_';
        }

        for(h=0; h<6; h++)
        {
            for(k=0; k < 7; k++)
            {
                if(temp[h][k] == 'X')
                    circulos[h][k].setFill(Color.YELLOW);
                if(temp[h][k] == 'O')
                    circulos[h][k].setFill(Color.RED);
                if(temp[h][k] == '_')
                    circulos[h][k].setFill(Color.WHITE);
            }
        }

        dados.addPassagem("Jogador: " + dados.getNameJogador(dados.getJogadorAtivo()) + " // Coluna: " + (coluna+1) + " // (Peça Especial)");
        dados.setNpecasEspeciaisJogador(dados.getJogadorAtivo(), (dados.getNpecasEspeciaisJogador(dados.getJogadorAtivo()) - 1));
        dados.trocaJogadorAtivo();
        dados.setMatrix(temp);

        return j;
    }

    public JogoSituacao getSituacaoAtual() {
        return JogoSituacao.Jogo_Decorrer;
    }
}
