/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mechanicalarm;

/**
 *
 * @author FireHunter
 */
public abstract class Comando{

    int bloqueInicial;
    int bloqueFinal;

    public abstract void EjecutarComando(ConjuntoBloques bloques);

    public abstract String TransformarEnString();

    public static Comando ConvertirComando(String[] partesComando) {
        int inicialPos = Integer.parseInt(partesComando[1]);
        int finalPos = Integer.parseInt(partesComando[3]);
        if (partesComando[0].equals("mover")) {
            if (partesComando[2].equals("en")) {
                return new ComandoMoverEn(inicialPos, finalPos);
            } else {
                if (partesComando[2].equals("sobre")) {
                    return new ComandoMoverSobre(inicialPos, finalPos);
                }
            }
        } else {
            if (partesComando[0].equals("apilar")) {
                if (partesComando[2].equals("en")) {
                    return new ComandoApilarEn(inicialPos, finalPos);
                } else {
                    if (partesComando[2].equals("sobre")) {
                        return new ComandoApilarSobre(inicialPos, finalPos);
                    }
                }
            }
        }
        return null;
    }
}
