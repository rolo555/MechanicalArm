
package mechanicalarm;

import java.util.Stack;

/**
 *
 * @author Ismael Rolando, Lopez Jhenier
 */
public class ComandoApilarSobre extends Comando {
    /**
     * Constructor de ComandoApilarSobre que recibe el valor del bloque inicial y el valor del bloque final que se desean ser apilados
     * @param bloqueInicial el valor del bloque que se desea ser apilado
     * @param bloqueFinal el valor del bloque al cual se desea apilar todo los bloques apilados en el bloque inicial (incluyendo al bloque inicial)
     */
    public ComandoApilarSobre(int bloqueInicial, int bloqueFinal) {
        this.bloqueFinal = bloqueFinal;
        this.bloqueInicial = bloqueInicial;
    }
    /**
     * Funcion que que retorna el comando en forma de string
     * @return el comando en forma de string
     */
    public String TransformarEnString() {
        return "Apilar " + String.valueOf(bloqueInicial) + " sobre " + String.valueOf(bloqueFinal);
    }
    /**
     * Funcion que verifica que se pueda realizar el comando, de ser asi, apila todos los bloques apilados en el bloque inicial(incluyendo el bloque inicial)
     * sobre la pila donde se encuentra el bloque final
     * @param el conjuto de bloques en el cual se desea realizar el comando
     */
    public void EjecutarComando(ConjuntoBloques bloques) {
        int posicionBloqueInicial = bloques.buscarPosicionDeBloque(bloqueInicial);
        int posicionBloqueFinal = bloques.buscarPosicionDeBloque(bloqueFinal);
        int numeroBloquesSobreBloqueInicial = bloques.calcularNumeroDeBloquesApiladosSobre(bloqueInicial);
        if (posicionBloqueInicial != posicionBloqueFinal) {
            if (bloqueInicial >= 0 && bloqueInicial < bloques.numeroDeBloques() && bloqueFinal >= 0 && bloqueFinal < bloques.numeroDeBloques()) {
                Stack<Integer> stackAuxiliar = new Stack<Integer>();
                for(int i = 0; i<=numeroBloquesSobreBloqueInicial; ++i){
                    int bloqueActual = bloques.mostrarUltimoBloqueApilado(posicionBloqueInicial);
                    stackAuxiliar.push(bloqueActual);
                    bloques.quitarUltimoBloqueApilado(posicionBloqueInicial);
                }
                int stackOriginalSize = stackAuxiliar.size();
                for(int i=0; i<stackOriginalSize; ++i){
                    int bloqueActual = stackAuxiliar.pop();
                    bloques.posicionarBloque(bloqueActual, posicionBloqueFinal);
                }
            } else {
                throw new IllegalArgumentException("Comando invalido (no existen suficientes bloques para ejecutar el comando)");
            }
        } else {
            throw new IllegalArgumentException("Comando invalido (los bloques inicial y final se ubican en la misma posicion)");
        }
    }
}
