/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mechanicalarm;

import java.util.Stack;


/**
 *
 * @author Rolo
 */
public class BrazoMecanico {

    private Stack<Integer> [] bloques;
    private int numeroDeBloques = 0;

    public int numeroDeBloques() {
        return numeroDeBloques;
    }

    public void crearPosiciones(int numeroDeBloques) {
        if (numeroDeBloques > 0 && numeroDeBloques <= 25) {
            this.numeroDeBloques = numeroDeBloques;
            bloques = new Stack [this.numeroDeBloques];
            for (int i = 0; i < this.numeroDeBloques; ++i) {
                bloques[i] = new Stack<Integer>();
                bloques[i].push(i);
            }
        } else {
            throw new IllegalStateException("El numero de bloques tiene que ser mayor a 0 y menor a 26.");
        }
    }

    public void posicionarBloque(int valor, int posicion) {
        if (posicion < this.numeroDeBloques) {
            bloques[posicion].push(valor);
        } else {
            throw new IllegalArgumentException("La posicion destino no existe");
        }
    }

    public int mostrarUltimoBloqueApilado(int posicion) {
        if(bloques[posicion].empty())
        {
            return -1;
        }
        else
        {
            return bloques[posicion].peek();
        }
    }

    public void quitarUltimoBloqueApilado(int posicion) {
        if (posicion < this.numeroDeBloques) {
            bloques[posicion].pop();
        } else {
            throw new IllegalArgumentException("La posicion requerida no existe");
        }
    }

    public int numeroBloquesApilados(int posicion) {
        return bloques[posicion].size();
    }

    public int buscarBloque(int bloque) {
        if( bloque < this.numeroDeBloques && bloque >= 0 ){
            return bloque - 1;
        } else {
            throw new IllegalArgumentException("Ese bloque no existe.");
        }
    }
}
