
package mechanicalarm;

/**
 *
 * @author Ismael Rolando, Lopez Jhenier
 */
public abstract class Comando{
    /**
     * Atributos de Comando, que seran heredados por todos los comandos
     */
    int bloqueInicial;
    int bloqueFinal;
    /**
     * Funcion abstracta para ser declarada en los comandos que hereden de esta clase, esta funcion realizara los movimientos correspondientes al comando
     * @param el conjunto de bloques que sera modificado por el comando
     */
    public abstract void EjecutarComando(ConjuntoBloques bloques);
    /**
     * Funcion abstracta para ser declarada en los comandos que hereden de esta clase, esta funcion retornara el comando en forma de string
     * @return el comando en forma de string
     */
    public abstract String TransformarEnString();
    /**
     * Funcion que recibe un array de strings y los evalua para convertirlo en un comando.
     * Los posibles comandos son: [ComandoApilarEn, ComandoApilarSobre, ComandoInvalido, ComandoMoverEn, ComandoMoverSobre y ComandoSalir]
     * @param array de strings para ser evaluados
     * @return el comando al que corresponde el array de strings dado
     */
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
