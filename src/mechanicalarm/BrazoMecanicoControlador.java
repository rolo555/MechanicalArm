
package mechanicalarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Ismael Rolando, Lopez Jhenier
 */
public class BrazoMecanicoControlador {

    /**
     * Atributos privados de la clase BrazoMecanicoControlador
     */

    private List<Comando> listaComandos;
    private int indiceComandoActual;
    private ConjuntoBloques bloques;


    /**
     * Constructor vacio de la clase BrazoMecanicoControlador
     * Inicia un ArrayList de comandos vacios y un indiceComandoActual igual a 0
     *
     */
    public BrazoMecanicoControlador() {
        listaComandos = new ArrayList<Comando>();
        indiceComandoActual = 0;
    }
    /**
     * Indica el numero de comandos que contiene la lista de comandos
     * @return el tamaño de la lista "listaComandos"
     */
    public int numeroComandos() {
        return listaComandos.size();
    }
    /**
     * Funcion que recibe un string y lo convierte a un comando:
     * [comandoApilarEn, comandoApilarSobre, ComandoInvalido, ComandoMoverEn, ComandoMoverSobre o ComandoSalir],
     * dicho comando es introducido en la lista de comandos e indica si fue un comando valido o invalido
     *
     * @param "comando" para ser categorizado e introducido a la lista de comandos
     * @return "false" si se trata de un ComandoInvalido "true" en otro caso
     */
    public boolean ingresarComando(String comando) {
        String comandoAux = comando.toLowerCase();
        String[] partesComando = comandoAux.split(" ");
        if (partesComando.length == 4 || comandoAux.equals("salir")) {
            if (comandoAux.equals("salir")) {
                listaComandos.add(new ComandoSalir());
                return true;
            } else {
                Comando comandoActual = Comando.ConvertirComando(partesComando);
                if (comandoActual == null) {
                    listaComandos.add(new ComandoInvalido(comando));
                    return false;
                } else {
                    listaComandos.add(comandoActual);
                    return true;
                }
            }
        } else {
            listaComandos.add(new ComandoInvalido(comando));
            return false;
        }
    }
    /**
     * Funcion que regresa el comando en forma de string que se encuentra en la posicion dada de la lista de comandos
     * @param posicion de la lista de comandos
     * @return el comando en forma de string
     */

    public String retornarComando(int posicion) {
        return listaComandos.get(posicion).TransformarEnString();
    }
    /**
     * Funcino que indica si el indice de comandos se encuentra dentro de la lista de comandos o en el comando "Salir"
     * @return "true" si el indice se encuntre dentro de la lista de comandos "false"  en otro caso
     */
    public boolean existeComando() {
        if(listaComandos.get(indiceComandoActual) instanceof ComandoSalir )
            return false;
        else if (indiceComandoActual < listaComandos.size())
            return true;
        else
            return false;
    }
    /**
     * Funcion que ejecuta el comando que se encuentra en la lista de comandos indicada por el indice de comando actual
     */
    public void ejecutarComando() {
        try {
            listaComandos.get(indiceComandoActual).EjecutarComando(bloques);
        } catch (Exception exp) {
            throw new IllegalArgumentException(exp.getMessage());
        } finally {
            ++indiceComandoActual;
        }
    }
    /**
     * Funcion que retorna todos los bloques apilados en una posicion dada
     * @param la posicion del cual se quiere saber los bloques apilados
     * @return una lista con todos los bloques apilados de la posicion dada
     */
    public List<Integer> retornarBloquesDeUnaPosicion(int posicion) {
        return bloques.retornarBloquesDeUnaPosicion(posicion);
    }
    /**
     * Funcion que crea un nuevo conjunto de bloques con la cantidad de posiciones dada
     * @param numero de posiciones que se desa que tenga el conjunto de bloques
     */
    public void ingresarNumeroPosiciones(int numeroPosiciones) {
        bloques = new ConjuntoBloques();
        bloques.crearPosiciones(numeroPosiciones);
    }
    /**
     * Funcion que retorna todo el conjunto de bloques.
     * @return el conjunto de bloques con sus respectivos bloques apilados.
     */
    public Stack<Integer>[] retornarTodosLosBloques() {
        return this.bloques.retornarConjuntoDeBloques();
    }
}
