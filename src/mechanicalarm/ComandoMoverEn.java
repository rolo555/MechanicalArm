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
        int posicionBloqueFinal;
        int numeroBloquesSobreBloqueInicial = bloques.calcularNumeroDeBloquesApiladosSobre(bloqueInicial);
        int numeroBloquesSobreBloqueFinal;

        if(bloqueInicial!=bloqueFinal)
        {
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
        }
        else
        {
            throw new IllegalArgumentException("Comando invalido (los bloques inicio y final son el mismo)");
        }
    }
}
