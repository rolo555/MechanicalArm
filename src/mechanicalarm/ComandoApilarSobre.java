/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mechanicalarm;

import java.util.Stack;

/**
 *
 * @author FireHunter
 */
public class ComandoApilarSobre extends Comando {

    public ComandoApilarSobre(int bloqueInicial, int bloqueFinal) {
        this.bloqueFinal = bloqueFinal;
        this.bloqueInicial = bloqueInicial;
    }

    public String TransformarEnString() {
        return "Apilar " + String.valueOf(bloqueInicial) + " sobre " + String.valueOf(bloqueFinal);
    }

    public void EjecutarComando(ConjuntoBloques bloques) {
        int posicionBloqueInicial = bloques.buscarPosicionDeBloque(bloqueInicial);
        int posicionBloqueFinal = bloques.buscarPosicionDeBloque(bloqueFinal);
        int numeroBloquesSobreBloqueInicial = bloques.calcularNumeroDeBloquesApiladosSobre(bloqueInicial);
        int numeroBloquesSobreBloqueFinal = bloques.calcularNumeroDeBloquesApiladosSobre(bloqueFinal);

        if (posicionBloqueInicial != posicionBloqueFinal) {
            if (bloqueInicial >= 0 && bloqueInicial < bloques.numeroDeBloques() && bloqueFinal >= 0 && bloqueFinal < bloques.numeroDeBloques()) {

                Stack<Integer> stackAuxiliar = new Stack<Integer>();
                for(int i = 0; i<=numeroBloquesSobreBloqueInicial; ++i)
                {
                    int bloqueActual = bloques.mostrarUltimoBloqueApilado(posicionBloqueInicial);
                    stackAuxiliar.push(bloqueActual);
                    bloques.quitarUltimoBloqueApilado(posicionBloqueInicial);
                }
                int stackOriginalSize = stackAuxiliar.size();

                for(int i=0; i<stackOriginalSize; ++i)
                {
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
