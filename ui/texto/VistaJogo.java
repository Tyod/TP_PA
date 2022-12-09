package jogo.ui.texto;

import jogo.logica.Jogo;

import java.util.Scanner;

public class VistaJogo {

    final private Jogo jogo;
    final Scanner sc = new Scanner(System.in);
    boolean sair = false;
    int z=0, h=2, ronda = 1, peças=0;

    public VistaJogo(Jogo jogo)
    {
        this.jogo = jogo;
    }

    private void askForInputEscolhaModo() {

        int temp;
        String p1, p2;
        System.out.println("\nEscolha o modo como pretende executar a app: \n");


            System.out.println("1 - Player vs Player");
            System.out.println("2 - Player vs Machine");
            System.out.println("3 - Machine vs Machine");
        do{
            System.out.print("\nInsira a sua opção: ");
            temp = sc.nextInt();
        }while (temp != 1 && temp!=2 && temp != 3);

        if(temp == 1)
        {
            System.out.println("\nModo Player vs Player Selecionado!");
            System.out.print("Insira o nome do primeiro jogador: ");
            p1 = sc.next();
            do {
                System.out.print("Insira o nome do segundo jogador: ");
                p2 = sc.next();
            }while(p2.equals(p1));
            jogo.IndicaModo(temp, p1, p2);
        }
        if(temp == 2)
        {
            System.out.println("\nModo Player vs Machine Selecionado!");
            System.out.print("Insira o nome do jogador: ");
            p1 = sc.next();
            jogo.IndicaModo(temp, p1, "Machine");
        }
        if(temp == 3)
        {
            System.out.println("\nModo Machine vs Machine Selecionado!");
            jogo.IndicaModo(temp,"Machine1", "Machine2");
        }

        jogo.sortiaJogadorInicial();
    }




    private void askForInputJogoNoDecorrer1() {
        int temp, opcao=0, escolha=0,peçasEliminadas=0;

        if(z==0) {
            jogo.saveMemento();
            System.out.print("\n\n\nJOGO INCIADO!!");
            System.out.println("\n\nPretende Carregar um jogo passado?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            do{
                System.out.print("Resposta: ");
                escolha = sc.nextInt();
            }while(escolha != 1 && escolha!=2);
            if(escolha == 1)
                //if(jogo.CarregaJogo())
                  //  System.out.println("\n\nJogo Carregado com sucesso!\n");
                //else
                  // System.out.println("\nNão foi possivel carregar o jogo! \n\nJogo inciado segundo os standards!");
            z++;
        }

        if(h==2)
        System.out.print("\n\n--------------------- RONDA " + ronda + " ---------------------");

        if(h==2) {
            jogo.saveMemento();

            h = 0;
            ronda++;
        }

        System.out.println(jogo);

        if(jogo.VerificaMiniJogo()) {
            askForInputMiniJogo();
            return;
        }

        do{
            System.out.println("\nQual das seguintes operações pretende efetuar?");
            System.out.println("1 - Jogar Peça Normal");
            System.out.println("2 - Jogar peça especial");
            System.out.println("3 - Consultar Histórico");
            System.out.println("4 - Retroceder uma ronda");
            System.out.println("5 - Gravar Jogo");
            System.out.print("Resposta: ");
            opcao = sc.nextInt();
        }while(opcao != 1 && opcao != 2 && opcao != 3 && opcao != 4 && opcao!=5);

        if(opcao == 1) {
            do {
                System.out.println("\nQual a coluna em pretende jogar(1-7)? ");
                System.out.print("Resposta: ");
                temp = sc.nextInt();
            } while (temp < 1 || temp > 7 || jogo.VerificaColunaPreenchida(temp - 1));
            //jogo.colocaPeca((temp-1), 0);
            System.out.println("\n\nFoi Colocada a Peça Normal! \n\n");
            peças++;
            h++;
        }

        if(opcao == 2){
            if(jogo.VericaJogadorTemPeca()) {
                do {
                    System.out.println("\nQual a coluna em pretende jogar a peça especial(1-7)? ");
                    System.out.print("Resposta: ");
                    temp = sc.nextInt();
                } while (temp < 1 || temp > 7);
                //peçasEliminadas = jogo.colocaPecaEspecial((temp-1));
                peças = peças - peçasEliminadas;
                System.out.println("\n\nFoi Colocada a Peça Especial! \n\n");
                h++;
            }
            else
                System.out.println("\nVoce não possui peças especiais para jogar!");
        }

        if(opcao == 3)
            System.out.println(jogo.toStringHistorico());

        if(opcao == 4) {
            //if (ronda > 2)
                //if(jogo.undo(peças)) {
                  //  if(peças >= 2 && peças % 2 ==0)
                    //    peças = peças -2;
                    //if(peças>2 && peças%2 != 0)
                      //  peças = peças - 3;
                    //if(peças==1)
                      //  peças--;
                    //h=2;
                   // System.out.println("\n\n A retroceder para a Ronda Anterior! \n\n");
                   // return;
               // }
                //else
                  //  System.out.println("\nNão foi possivel retroceder ou não há mais para retroceder!");
            //else
              //  System.out.println("\nDemasiado cedo para ser possivél retroceder!");
        }

        if(opcao == 5)
            if(jogo.GuardaJogo())
                System.out.println("Jogo gravado com sucesso!");
            else
                System.out.println("Não foi possivel gravar o jogo!");

        jogo.VerificaEmpate();
    }




    private void askForInputJogoNoDecorrer2() {

        int temp, opcao, escolha=0,peçaEliminadas;
        int flag = jogo.getJogadorAtivo();

        if(z==0) {
            System.out.print("\n\n\nJOGO INCIADO!!");
            System.out.println("\n\nPretende Carregar um jogo passado?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            do{
                System.out.print("Resposta: ");
                escolha = sc.nextInt();
            }while(escolha != 1 && escolha!=2);
            if(escolha == 1)
                //if(jogo.CarregaJogo())
                 //   System.out.println("\n\nJogo Carregado com sucesso!\n");
                //else
                    //System.out.println("\nNão foi possivel carregar o jogo! \n\nJogo inciado segundo os standards!");
            z++;
        }

        if(h==2)
            System.out.print("\n\n--------------------- RONDA " + ronda + " ---------------------");

        if(h==2) {
            jogo.saveMemento();
            h = 0;
            ronda++;
        }

        System.out.println(jogo);

        if(flag == 0) {
            if(jogo.VerificaMiniJogo()) {
                askForInputMiniJogo();
                return;
            }
            do{
                System.out.println("\nQual das seguintes operações pretende efetuar?");
                System.out.println("1 - Jogar Peça Normal");
                System.out.println("2 - Jogar peça especial");
                System.out.println("3 - Consultar Historico");
                System.out.println("4 - Retroceder uma ronda");
                System.out.println("5 - Gravar Jogo");
                System.out.print("Resposta: ");
                opcao = sc.nextInt();
            }while(opcao != 1 && opcao != 2 && opcao != 3 && opcao!=4 && opcao!=5);

            if(opcao == 1) {
                do {
                    System.out.println("\nQual a coluna em pretende jogar(1-7)? ");
                    System.out.print("Resposta: ");
                    temp = sc.nextInt();
                } while (temp < 1 || temp > 7 || jogo.VerificaColunaPreenchida(temp - 1));
                //jogo.colocaPeca((temp-1), 0);
                peças++;
                System.out.println("\n\nFoi Colocada a Peça Normal! \n\n");
                h++;
            }

            if(opcao == 2){
                if(jogo.VericaJogadorTemPeca()) {
                    do {
                        System.out.println("\nQual a coluna em pretende jogar a peça especial(1-7)? ");
                        System.out.print("Resposta: ");
                        temp = sc.nextInt();
                    } while (temp < 1 || temp > 7);
                    //peçaEliminadas = jogo.colocaPecaEspecial(temp-1);
                    //peças = peças -peçaEliminadas;

                    System.out.println("\n\nFoi Colocada a Peça Especial! \n\n");
                    h++;
                }
                else
                    System.out.println("\nVoce não possui peças especiais para jogar!");
            }
            if(opcao == 3)
                System.out.println(jogo.toStringHistorico());

            if(opcao == 4) {
               // if (ronda > 2)
                 //   if(jogo.undo(peças)) {
                   //     if(peças >= 2 && peças % 2 ==0)
                     //       peças = peças -2;
                       // if(peças>2 && peças%2 != 0)
                         //   peças = peças - 3;
                        //if(peças==1)
                          //  peças = peças -1;
                        //h=2;
                        //System.out.println("\n\n A retroceder para a Ronda Anterior! \n\n");
                        //return;
                    //}
                    //else
                      //  System.out.println("\nNão foi possivel retroceder!");
                //else
                  //  System.out.println("\nDemasiado cedo para ser possivél retroceder!");
            }

            if(opcao == 5)
                if(jogo.GuardaJogo())
                    System.out.println("Jogo gravado com sucesso!");
                else
                    System.out.println("Não foi possivel gravar o jogo!");
        }
        else {
            //jogo.colocaPeca(0, flag);
            peças++;
            h++;

            try{Thread.sleep(2000);}catch(InterruptedException e){System.out.println(e);}
        }

        jogo.VerificaEmpate();
    }




    private void askForInputJogoNoDecorrer3() {
        if(z==0) {
            System.out.print("\n\n\nJOGO INCIADO!!");
            z++;
        }

        if(h==2)
            System.out.print("\n\n--------------------- RONDA " + ronda + " ---------------------");

        System.out.println(jogo.toString());
        //jogo.colocaPeca( 0, 1);
        System.out.println("\n\nFoi Colocada a Peça Normal! \n\n");

        if(h==2) {
            h = 0;
            ronda++;
        }

        h++;

        try{Thread.sleep(2000);}catch(InterruptedException e){System.out.println(e);}

        jogo.VerificaEmpate();
    }

    private void askForInputMiniJogo()
    {
        int temp;
        System.out.println(jogo.toStringMiniJogo());
        System.out.println("1 - Sim");
        System.out.println("2 - Não");
        do {
            System.out.print("Resposta: ");
            temp = sc.nextInt();
        }while(temp != 1 && temp != 2);

        jogo.ExecutaOpcao(temp);
    }

    private void askForInputTerminoDeJogo() {
        int temp, opcao;
        System.out.println("\n\n\nO JOGO TERMINOU!!!");
        //System.out.println(jogo.toStringTerminoDeJogo());

        System.out.println("\n\nEscolha uma das seguintes opções: ");
        System.out.println("1 - Criar Novo jogo");
        System.out.println("2 - Consultar o historico de um dos 5 ultimos jogos decorridos");
        System.out.println("3 - Terminar Programa");
        do {
            System.out.print("Resposta: ");
            temp = sc.nextInt();
        }while(temp!= 1 && temp!=2 && temp!=3);


        if(temp == 1) {
            System.out.println("\n\n\n\n\n\n\nNOVO JOGO ESCOLHIDO!!! \n");
            jogo.LimpaUndo();
            jogo.IniciaNovoJogo();
        }
        if(temp == 2){
            System.out.println("\nQual dos jogos decorridos pretende consultar? (1-5)");
            do{
                System.out.print("Resposta: ");
                opcao = sc.nextInt();
            }while(opcao<0 || opcao>5);
            System.out.println("\n\n\n\n\nCONSULTA DE JOGO PASSADO!! ");
            System.out.println(jogo.ConsultaHistóricoJogoPassado((opcao-1)));
        }
        if(temp == 3)
          sair = true;
    }

    public void Run() {
        askForInputEscolhaModo();

        do{
            switch (jogo.getSituacaoAtual())
            {
                case Escolha_Modo:
                    askForInputEscolhaModo();
                    break;
                case Jogo_Decorrer:
                    if(jogo.getModoJogo() == 1)
                        askForInputJogoNoDecorrer1();
                    if(jogo.getModoJogo() == 2)
                        askForInputJogoNoDecorrer2();
                    if(jogo.getModoJogo() == 3)
                        askForInputJogoNoDecorrer3();
                    break;
                case Mini_jogo:
                    VistaMiniJogo UIminijogo = new VistaMiniJogo(jogo);
                    UIminijogo.iniciaVistaMiniJogo();
                    break;
                case Termino_de_Jogo:
                    z=0; h=2; ronda=1; peças=0;
                   askForInputTerminoDeJogo();
                    break;
            }
        }while(!sair);

        System.exit(0);
    }
}
