
package mechanicalarm;

/**
 *
 * @author Ismael Rolando, Lopez Jhenier
 */
public class ComandoMoverEn extends Comando {
    /**
     * Constructor de ComandoMoverEn que recibe el valor del bloque inicial y el valor del bloque final que se desea mover
     * @param bloqueInicial el valor del bloque que se desea mover
     * @param bloqueFinal el valor del bloque al cual se desea mover el bloque correspondiente al bloqueInicial
     */
    public ComandoMoverEn(int posicionInicial, int posicionFinal) {
        this.bloqueInicial = posicionInicial;
        this.bloqueFinal = posicionFinal;
    }
    /**
     * Funcion que que retorna el comando en forma de string
     * @return el comando en forma de string
     */
    public String TransformarEnString() {
        return "Mover " + String.valueOf(bloqueInicial) + " en " + String.valueOf(bloqueFinal);
    }
    /**
     * Funcion que verifica que se pueda realizar el comando, de ser asi, ordena todos los bloques apilados en los bloque con el valor igual al bloqueInicial e igual al bloqueFinal a sus posiciones origianles correspondientes
     * y mueve el bloque con el valor igual al bloqueInicial sobre el bloque con el valor igual al bloqueFinal
     * @param el conjuto de bloques en el cual se desea realizar el comando
     */
    public void EjecutarComando(ConjuntoBloques bloques) {
        int posicionBloqueInicial = bloques.buscarPosicionDeBloque(bloqueInicial);
        int posicionBloqueFinal;
        int numeroBloquesSobreBloqueInicial = bloques.calcularNumeroDeBloquesApiladosSobre(bloqueInicial);
        int numeroBloquesSobreBloqueFinal;

        if (bloqueInicial != bloqueFinal) {
            if (bloqueInicial >= 0 && bloqueInicial < bloques.numeroDeBloques() && bloqueFinal >= 0 && bloqueFinal < bloques.numeroDeBloques()) {
                for (int i = 0; i < numeroBloquesSobreBloqueInicial; ++i) {
                    bloques.retornarUltimoBloqueApiladoASuPosicionOriginal(posicionBloqueInicial);
                }

                posicionBloqueInicial = bloques.buscarPosicionDeBloque(bloqueInicial);
                posicionBloqueFinal = bloques.buscarPosicionDeBloque(bloqueFinal);
                numeroBloquesSobreBloqueInicial = bloques.calcularNumeroDeBloquesApiladosSobre(bloqueInicial);
                numeroBloquesSobreBloqueFinal = bloques.calcularNumeroDeBloquesApiladosSobre(bloqueFinal);
                for (int i = 0; i < numeroBloquesSobreBloqueFinal; ++i) {
                    bloques.retornarUltimoBloqueApiladoASuPosicionOriginal(posicionBloqueFinal);
                }

                bloques.popPosXAPosY(posicionBloqueInicial, posicionBloqueFinal);
            } else {
                throw new IllegalArgumentException("Comando invalido (no existen suficientes bloques para ejecutar el comando)");
            }
        } else {
            throw new IllegalArgumentException("Comando invalido (los bloques inicio y final son el mismo)");
        }
    }
}
