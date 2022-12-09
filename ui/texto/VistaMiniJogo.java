package jogo.ui.texto;

import jogo.logica.Jogo;

import java.util.Scanner;

public class VistaMiniJogo {

    final private Jogo jogo;
    final Scanner sc = new Scanner(System.in);


    public VistaMiniJogo(Jogo jogo){
        this.jogo = jogo;
    }

    public void iniciaVistaMiniJogo(){
        System.out.println("\n\n\nBEM VINDO AO MINI JOGO!! ");


        if(jogo.SorteiaJogo() == 1) {
            System.out.println("\nFoi sorteado o jogo dos calculos! \n");
            System.out.println("Breve Resumo: \nSerão propostos cálculos matemáticos simples usando os operadores básicos (+,-,/,x) e dois inteiros positivos\n" +
                    "(de 1 ou 2 dígitos cada um). Os números e os operadores a usar são sorteados. Caso o jogador acerte em 5\n" +
                    "cálculos em menos de 30 segundos ganha um peça especial; \n\n");
            System.out.println("Precione a tecla ENTER para começar...");
            try
            {
                System.in.read();
            }
            catch(Exception e)
            {}
            jogo.IniciaTimerMiniJogoCalculos();
            IniciaVistaMiniJogoCalculos();
        }
        else {
            System.out.println("\nFoi sorteado o jogo das Palavras! \n");
            System.out.println("Breve Resumo: \nSerão apresentadas 5 palavras escolhidas aleatoriamente, cada uma de 5 ou\n" +
                    "mais letras. O jogador deve escrever essas palavras e será avaliada a sua\n" +
                    "rapidez. O jogador ganha a peça especial se digitar corretamente as palavras\n" +
                    "num período inferior ou igual ao número de segundos que corresponde a metade \n" +
                    "do número de caracteres apresentados.\n");
            System.out.println("Precione a tecla ENTER para começar...");
            try
            {
                System.in.read();
            }
            catch(Exception e)
            {}
            jogo.GeraPalvras();
            jogo.IniciaTimerMiniJogoPalavras();
            IniciaVistaMiniJogoPalavras();
        }
    }

    private void IniciaVistaMiniJogoCalculos() {
        int temp;

        do {
            jogo.GeraCalculos();
            System.out.println(jogo.toStringMiniJogoCalculos());
            System.out.print("Resposta: ");
            temp = sc.nextInt();
        }while(!jogo.AvaliaResposta(temp));

        jogo.VerificaVitoriaMiniJogoCalculos();
    }

    private void IniciaVistaMiniJogoPalavras() {
        String temp;
        int num = 0;

        do{
            System.out.println(jogo.toStringMiniJogoPalavras(num));
            do {
                System.out.print("Resposta: ");
                temp = sc.next();
            }while(!jogo.VerificaResposta(temp, num));
            num++;
        }while(num < 5);

        jogo.VerificaVitoriaMiniJogoPalavras();
    }
}
