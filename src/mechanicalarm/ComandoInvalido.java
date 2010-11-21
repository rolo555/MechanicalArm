
package mechanicalarm;

/**
 *
 * @author Ismael Rolando, Lopez Jhenier
 */
public class ComandoInvalido extends Comando{
    /**
     * Atributo privado de ComandoInvalido que contiene el comando invalido en forma de string
     */
    private String comandoOriginal;
    /**
     * Constructor de ComandoInvalido que recibe el comando invalido en forma de string
     * @param comandoOriginal
     */
    public ComandoInvalido(String comandoOriginal)
    {
        this.comandoOriginal = comandoOriginal;
    }
    /**
     * Funcion que que retorna el comando en forma de string
     * @return el comando en forma de string
     */
    public String TransformarEnString()
    {
        return this.comandoOriginal;
    }
    /**
     * Funcion que arroja una excepcion
     * @param recibe el conjunto de bloques al que se intentaba realizar el comando
     */
    public void EjecutarComando(ConjuntoBloques bloques)
    {
        throw new IllegalArgumentException("El comando es invalido (El comando no existe)");
    }

}
