
package mechanicalarm;

/**
 *
 * @author Ismael Rolando, Lopez Jhenier
 */
public class ComandoSalir extends Comando {
    /**
     * Funcion que que retorna el comando en forma de string
     * @return el comando en forma de string
     */
    public String TransformarEnString()
    {
        return "Salir";
    }
    /**
     * Esta funcion tiene que ser declarada ya es es una funcion abstracta, pero no cumple ninguna funcion en especifico
     * @param el conjuto de bloques en el cual se desea realizar el comando
     */
    public void EjecutarComando(ConjuntoBloques bloques)
    {
    }

}
