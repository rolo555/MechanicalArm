/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mechanicalarm;

/**
 *
 * @author FireHunter
 */
public class ComandoInvalido extends Comando{

    String comandoOriginal;
    public ComandoInvalido(String comandoOriginal)
    {
        this.comandoOriginal = comandoOriginal;
    }

    public String TransformarEnString()
    {
        return this.comandoOriginal;
    }

    public void EjecutarComando(ConjuntoBloques bloques)
    {
        throw new IllegalArgumentException("El comando es invalido (El comando no existe)");
    }

}
