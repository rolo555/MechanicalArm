/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mechanicalarm;

/**
 *
 * @author FireHunter
 */
public class ComandoMoverEn extends Comando {

    public ComandoMoverEn(int posicionInicial, int posicionFinal) {
        this.bloqueInicial = posicionInicial;
        this.bloqueFinal = posicionFinal;
    }

    public String TransformarEnString() {
        return "Mover " + String.valueOf(bloqueInicial) + " en " + String.valueOf(bloqueFinal);
    }

    public void EjecutarComando(ConjuntoBloques bloques) {
        int posicionBloqueInicial = bloques.buscarPosicionDeBloque(bloqueInicial);
        int posicionBloqueFinal = bloques.buscarPosicionDeBloque(bloqueFinal);
        int numeroBloquesSobreBloqueInicial = bloques.calcularNumeroDeBloquesApiladosSobre(bloqueInicial);
        int numeroBloquesSobreBloqueFinal = bloques.calcularNumeroDeBloquesApiladosSobre(bloqueFinal);

        if (posicionBloqueInicial != posicionBloqueFinal) {
            if (bloqueInicial >= 0 && bloqueInicial < bloques.numeroDeBloques() && bloqueFinal >= 0 && bloqueFinal < bloques.numeroDeBloques()) {
                for (int i = 0; i < numeroBloquesSobreBloqueInicial; ++i) {
                    bloques.retornarUltimoBloqueApiladoASuPosicionOriginal(posicionBloqueInicial);
                }

                for (int i = 0; i < numeroBloquesSobreBloqueFinal; ++i) {
                    bloques.retornarUltimoBloqueApiladoASuPosicionOriginal(posicionBloqueFinal);
                }

                bloques.popPosXAPosY(posicionBloqueInicial, posicionBloqueFinal);
            } else {
                throw new IllegalArgumentException("Comando invalido (no existen suficientes bloques para ejecutar el comando)");
            }
        } else {
            throw new IllegalArgumentException("Comando invalido (los bloques inicial y final se ubican en la misma posicion)");
        }

    }
}
