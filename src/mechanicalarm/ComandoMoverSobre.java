
package mechanicalarm;

/**
 *
 * @author Ismael Rolando, Lopez Jhenier
 */
public class ComandoMoverSobre extends Comando {

    /**
     * Constructor de ComandoMoverSobre que recibe el valor del bloque inicial y el valor del bloque final que se desea mover
     * @param bloqueInicial el valor del bloque que se desea mover
     * @param bloqueFinal el valor del bloque al cual se desea mover el bloque correspondiente al bloqueInicial
     */
    public ComandoMoverSobre(int bloqueInicial, int bloqueFinal) {
        this.bloqueInicial = bloqueInicial;
        this.bloqueFinal = bloqueFinal;
    }
    /**
     * Funcion que que retorna el comando en forma de string
     * @return el comando en forma de string
     */
    public String TransformarEnString() {
        return "Mover " + String.valueOf(bloqueInicial) + " sobre " + String.valueOf(bloqueFinal);
    }
    /**
     * Funcion que verifica que se pueda realizar el comando, de ser asi, ordena todos los bloques apilados en el bloque con el valor igual al bloqueInicial a sus posiciones origianles correspondientes
     * y mueve el bloque con el valor igual al bloqueInicial sobre la pila donde se encuentra el bloque con el valor igual al bloqueFinal
     * @param el conjuto de bloques en el cual se desea realizar el comando
     */
    public void EjecutarComando(ConjuntoBloques bloques) {
        int posicionBloqueInicial = bloques.buscarPosicionDeBloque(bloqueInicial);
        int posicionBloqueFinal;
        int numeroBloquesSobreBloqueInicial = bloques.calcularNumeroDeBloquesApiladosSobre(bloqueInicial);

        if (bloqueInicial != bloqueFinal) {
            if (bloqueInicial >= 0 && bloqueInicial < bloques.numeroDeBloques() && bloqueFinal >= 0 && bloqueFinal < bloques.numeroDeBloques()) {
                for (int i = 0; i < numeroBloquesSobreBloqueInicial; ++i) {
                    bloques.retornarUltimoBloqueApiladoASuPosicionOriginal(posicionBloqueInicial);
                }
                posicionBloqueFinal = bloques.buscarPosicionDeBloque(bloqueFinal);
                bloques.popPosXAPosY(posicionBloqueInicial, posicionBloqueFinal);
            } else {
                throw new IllegalArgumentException("Comando invalido (no existen suficientes bloques para ejecutar el comando)");
            }
        } else {
            throw new IllegalArgumentException("Comando invalido (los bloques inicial y final son el mismo)");
        }
    }
}
