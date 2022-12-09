package jogo.logica.dados;

import java.io.Serializable;

public class Palavras implements Serializable {

    String word;

    private static final String [] palavras = {"AMANHA","ARQUIMEDES","PANTERA","TIGRE","ZEBRA","PRATO", //6
            "CASTANHO","LARANJA","ERVILHA","LONTRA","LASTRO","ORANGOTANGO","DISCIPLINA","PROGRAMACAO", //14
            "BATATA","PALHACO","CEREBRO","POLVO","PORTUGAL","SILVESTRE","ANIMAL","IRRACIONAL","MATEMATICA", //23
            "DISCRETO","EFICAZ","EFICIENTE","MARAVILHA","SINOPSE","DISPOSITIVO","LINEAR","LIMAO","LAMPADA", //32
            "ORELHA","BUFALO","SAPATO","LAGOSTA","ARRISCADO","RISCADO","CARNAVAL","ARROJADO","LIBERTADO", //41
            "SIMPLES","COMPLEXO","AGRAFADOR","MONITOR","TECLADO","CHAVE","RELOGIO","LENCO","JANELA","CORDA", //51
            "VIOLA","GUITARRA","PONTEIRO","ARGUENTE","SAGAZ","ERRONEO","INSTITUTO","SUPERIOR","ENGENHARIA", //60
            "DEPARTAMENTO","INFORMATICA","SISTEMA","CONVOCATORIA","PRESIDENTE","FEVEREIRO","AUMENTO","SALARIO", //68
            "DINHEIRO","IMEDIATO","FLAMINGO","RINOCERONTE","HIPOPOTAMO","BACALHAU","PARGO","SARDINHA","CARACOL", //77
            "INSECTO","VOADOR","LARANJA","ASPERSAO","EXTINTO","EXTERIOR","AMBIVALENTE", "AVIAO", "CARRO", "MOTORIZADA", //87
            "RODADO", "MOTOR", "JAVALI", "ESQUILO", "UNIVERSIDADE", "CAMELO", "CASAMENTO", "DECADA", "SECULO", "MILENIO", //97
            "PAR", "DUZIA", "DEZENA", "CENTENA" //101
    };

    public Palavras(String word)
    {
        this.word= word;
    }

    public String getPalavraAleatoria()
    {
        return palavras[(int) (Math.random()*101)];
    }
}
