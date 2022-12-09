package jogo.logica.estados;

import jogo.logica.JogoSituacao;
import jogo.logica.dados.Calculos;
import jogo.logica.dados.JogoDados;
import jogo.logica.dados.Palavras;

import java.util.Timer;
import java.util.TimerTask;


public class MiniJogo extends JogoEstadoAdpter{

    timerC cronometroC;
    timerP cronometroP;
    int segundos = 0;
    int Ncertas=0;
    int TotalTimer = 0;

    class timerC extends Thread{

        private int secs_atm, secs_max;
        private Timer myTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(secs_atm == secs_max-1)
                    task.cancel();

                segundos++;
                secs_atm++;
                //System.out.println("\n Segundos " + secs_atm + "\n");
            }
        };

        public timerC(int secs)
        {
            secs_max = secs;
        }

        public void run()
        {
                myTimer.scheduleAtFixedRate(task, 1000, 1000);
        }
    }

    class timerP extends Thread{

        private int secs_atm;
        private Timer myTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(Ncertas == 5)
                    task.cancel();

                secs_atm++;
                //System.out.println("\n Segundos " + secs_atm + "\n");
            }
        };

        int getSecs_atm()
        {
            return secs_atm;
        }

        public void run()
        {
            myTimer.scheduleAtFixedRate(task, 1000, 1000);
        }
    }

    public MiniJogo(JogoDados dados)
    {
        super(dados);
    }

    public int SorteiaJogo(){
        return (int) (Math.random()*2);
    }

    public void IniciaTimerMiniJogoCalculos(){
        cronometroC = new timerC(30);
        cronometroC.start();
    }

    public void GeraDados(){
        int value1 = (int) (Math.random()*9) + 1;
        int value2 = (int) (Math.random()*9) + 1;
        char operando = ' ';
        int num = (int) (Math.random()*4) + 1;


        if(num == 1)
            operando = '-';
        if(num == 2)
            operando = '+';
        if(num == 3)
            operando = '*';
        if(num == 4)
            operando = '/';

        dados.setValoresCalculos(value1, value2, operando);
    }

    public boolean AvaliaResposta(int resposta){

        Calculos temp = dados.getValoresCalculos();

        if(temp.getOperador() == '-')
            if(temp.getValor1() - temp.getValor2() == resposta && segundos != 30)
                Ncertas++;
        if(temp.getOperador() == '+')
            if(temp.getValor1() + temp.getValor2() == resposta && segundos != 30)
                Ncertas++;
        if(temp.getOperador() == '*')
            if(temp.getValor1() * temp.getValor2() == resposta && segundos != 30)
                Ncertas++;
        if(temp.getOperador() == '/')
            if(temp.getValor1()/temp.getValor2() == resposta && segundos != 30)
                Ncertas++;

        if(segundos != 30)
            return false;
        else
            return true;
    }

    public IJogoEstado VerificaVitoriaMiniJogoCalculos(){

        dados.setNjogadasJogador(dados.getJogadorAtivo(), 0);

        if(Ncertas >= 5)
            dados.setNpecasEspeciaisJogador(dados.getJogadorAtivo(), (dados.getNpecasEspeciaisJogador(dados.getJogadorAtivo()) + 1));
        else
            dados.trocaJogadorAtivo();

        segundos = 0;
        Ncertas = 0;
        return new JogoNoDecorrer(dados);
    }

    public void GeraPalavrasMiniJogo(){
        String p1, p2, p3, p4, p5;
        Palavras temp = new Palavras(" ");

        p1 = temp.getPalavraAleatoria();

        do {
            p2 = temp.getPalavraAleatoria();
        }while(p2.equals(p1));

        do {
            p3 = temp.getPalavraAleatoria();
        }while(p3.equals(p1) || p3.equals(p2));

        do {
            p4 = temp.getPalavraAleatoria();
        }while(p4.equals(p1) || p4.equals(p2) || p4.equals(p3));

        do {
            p5 = temp.getPalavraAleatoria();
        }while(p5.equals(p1) || p5.equals(p2) || p5.equals(p3) || p5.equals(p4));

        dados.setPalavrasMiniJogo(p1, p2, p3, p4, p5);
    }

    public boolean VerificaResposta(String temp, int num){

        if(temp.equals(dados.getPalavra(num))) {
            TotalTimer += (cronometroP.getSecs_atm() - segundos);
            Ncertas++;
            segundos = cronometroP.getSecs_atm();
            return true;
        }
        else
            return false;

    }

    public IJogoEstado VerificaVitoriaMiniJogoPalavras() {

        int TotalSizePalavras = 0;

        dados.setNjogadasJogador(dados.getJogadorAtivo(), 0);

        for(int i=0; i<5; i++)
            TotalSizePalavras += dados.getPalavra(i).length();

        //System.out.println("\n\n\n TEMPO TOTAL: " + TotalTimer + " Tamanho palavras a dividir por 2: " + (TotalSizePalavras/2) + "\n\n\n");
        if(TotalTimer <= TotalSizePalavras/2) {
            dados.setNpecasEspeciaisJogador(dados.getJogadorAtivo(), (dados.getNpecasEspeciaisJogador(dados.getJogadorAtivo()) + 1));
        }
        else
            dados.trocaJogadorAtivo();


        segundos = 0;
        Ncertas = 0;
        return new JogoNoDecorrer(dados);
    }

    public void IniciaTimerMiniJogoPalavras(){
        cronometroP = new timerP();
        cronometroP.start();
        segundos = cronometroP.getSecs_atm();
    }

    public JogoSituacao getSituacaoAtual() {
        return JogoSituacao.Mini_jogo;
    }
}

