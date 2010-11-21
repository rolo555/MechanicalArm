/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mechanicalarm;

/**
 *
 * @author FireHunter
 */
public class ComandoMoverSobre extends Comando {

    public ComandoMoverSobre(int bloqueInicial, int bloqueFinal) {
        this.bloqueInicial = bloqueInicial;
        this.bloqueFinal = bloqueFinal;
    }

    public String TransformarEnString() {
        return "Mover " + String.valueOf(bloqueInicial) + " sobre " + String.valueOf(bloqueFinal);
    }

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
