package jogo.logica.dados;

import java.io.Serializable;

public class Calculos implements Serializable {

    protected int valor1;
    protected int valor2;
    protected char operador;

    public Calculos(int v1, int v2, char op)
    {
        this.valor1 = v1;
        this.valor2 = v2;
        this.operador = op;
    }

    public void setValores(int value1, int value2, char operator)
    {
        this.valor1 = value1;
        this.valor2 = value2;
        this.operador = operator;
    }

    public int getValor1()
    {
        return valor1;
    }

    public int getValor2()
    {
        return valor2;
    }

    public char getOperador()
    {
        return operador;
    }
}
