
package mechanicalarm;

import java.util.Stack;

/**
 *
 * @author Ismael Rolando, Lopez Jhenier
 */
public class ComandoApilarEn extends Comando {
    /**
     * Constructor de ComandoApilarEn que recibe el valor del bloque inicial y el valor del bloque final que se desean ser apilados
     * @param bloqueInicial el valor del bloque que se desea ser apilado
     * @param bloqueFinal el valor del bloque al cual se desea apilar todo los bloques apilados en el bloque inicial (incluyendo al bloque inicial)
     */
    public ComandoApilarEn(int bloqueInicial, int bloqueFinal) {
        this.bloqueFinal = bloqueFinal;
        this.bloqueInicial = bloqueInicial;
    }
    /**
     * Funcion que que retorna el comando en forma de string
     * @return el comando en forma de string
     */
    public String TransformarEnString() {
        return "Apilar " + String.valueOf(bloqueInicial) + " en " + String.valueOf(bloqueFinal);
    }
    /**
     * Funcion que verifica que se pueda realizar el comando, de ser asi, ordena todos los bloques apilados en el bloque final a sus posiciones originales correspondientes
     * y apila todos los bloques que se encuentran en el bloque inicial(incluyendo el bloque inicial) sobre el bloque final
     * @param el conjuto de bloques en el cual se desea realizar el comando
     */
    public void EjecutarComando(ConjuntoBloques bloques) {
        if (bloqueInicial != bloqueFinal) {
            if (bloqueInicial >= 0 && bloqueInicial < bloques.numeroDeBloques() && bloqueFinal >= 0 && bloqueFinal < bloques.numeroDeBloques()) {
                int posicionBloqueInicial = bloques.buscarPosicionDeBloque(bloqueInicial);
                int posicionBloqueFinal = bloques.buscarPosicionDeBloque(bloqueFinal);
                int numeroBloquesSobreBloqueInicial = bloques.calcularNumeroDeBloquesApiladosSobre(bloqueInicial);
                int numeroBloquesSobreBloqueFinal = bloques.calcularNumeroDeBloquesApiladosSobre(bloqueFinal);
                if ((posicionBloqueInicial == posicionBloqueFinal && numeroBloquesSobreBloqueFinal > numeroBloquesSobreBloqueInicial) || posicionBloqueInicial != posicionBloqueFinal) {

                    for (int i = 0; i < numeroBloquesSobreBloqueFinal; ++i) {
                        bloques.retornarUltimoBloqueApiladoASuPosicionOriginal(posicionBloqueFinal);
                    }

                    posicionBloqueInicial = bloques.buscarPosicionDeBloque(bloqueInicial);
                    numeroBloquesSobreBloqueInicial = bloques.calcularNumeroDeBloquesApiladosSobre(bloqueInicial);
                    Stack<Integer> stackAuxiliar = new Stack<Integer>();
                    for (int i = 0; i <= numeroBloquesSobreBloqueInicial; ++i) {
                        int bloqueActual = bloques.mostrarUltimoBloqueApilado(posicionBloqueInicial);
                        stackAuxiliar.push(bloqueActual);
                        bloques.quitarUltimoBloqueApilado(posicionBloqueInicial);
                    }
                    int stackOriginalSize = stackAuxiliar.size();
                    for (int i = 0; i < stackOriginalSize; ++i) {
                        int bloqueActual = stackAuxiliar.pop();
                        bloques.posicionarBloque(bloqueActual, posicionBloqueFinal);
                    }
                } else {
                    throw new IllegalArgumentException("El bloque final no puede estar encima del bloque inicial");
                }

            } else {
                throw new IllegalArgumentException("Comando invalido (no existen suficientes bloques para ejecutar el comando)");
            }
        } else {
            throw new IllegalArgumentException("Comando invalido (los bloques inicial y final son los mismos)");
        }
    }
}
