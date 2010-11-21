/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mechanicalarm;

import java.util.List;
import java.util.Stack;

/**
 *
 * @author Rolo
 */
public class ConjuntoBloques {

    private Stack<Integer>[] bloques;
    private int numeroDeBloques = 0;

    public int numeroDeBloques() {
        return numeroDeBloques;
    }

    public void crearPosiciones(int numeroDeBloques) {
        if (numeroDeBloques > 0 && numeroDeBloques <= 25) {
            this.numeroDeBloques = numeroDeBloques;
            bloques = new Stack[this.numeroDeBloques];
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
        if (bloques[posicion].empty()) {
            return -1;
        } else {
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

    public int buscarPosicionDeBloque(int bloque) {
        if (bloque < this.numeroDeBloques && bloque >= 0) {
            int resp = -1;
            for (int i = 0; i < this.numeroDeBloques && resp == -1; i++) {
                if (this.bloques[i].search(bloque) != -1) {
                    resp = i;
                }
            }
            return resp;
        } else {
            throw new IllegalArgumentException("Ese bloque no existe.");
        }
    }

    public int calcularNumeroDeBloquesApiladosSobre(int bloque) {
        if (bloque < this.numeroDeBloques && bloque >= 0) {
            int posicion = this.buscarPosicionDeBloque(bloque);
            int numeroDeBloquesApiladosSobreBloque = this.bloques[posicion].search(bloque) - 1;
            return numeroDeBloquesApiladosSobreBloque;
        } else {
            throw new IllegalArgumentException("Ese bloque no existe.");
        }
    }

    public void popPosXAPosY(int posX, int posY) {
        if (posX < this.numeroDeBloques && posX >= 0) {
            if (posY < this.numeroDeBloques && posY >= 0) {
                this.bloques[posY].push(this.bloques[posX].pop());
            } else {
                throw new IllegalArgumentException("posY invalido.");
            }
        } else {
            throw new IllegalArgumentException("posX invalido.");
        }
    }
    
    public void retornarUltimoBloqueApiladoASuPosicionOriginal(int posicion)
    {
        if(posicion>=0 && posicion<this.numeroDeBloques)
        {
            int ultimoBloqueApilado = this.bloques[posicion].pop();
            int posicionOriginal = ultimoBloqueApilado;
            this.bloques[posicionOriginal].push(ultimoBloqueApilado);
        }
        else
        {
            throw new IllegalArgumentException("La posicion es invalida");
        }
    }

    public List<Integer> retornarBloquesDeUnaPosicion(int i) {
        return bloques[i].subList(0,bloques[i].size() );
    }

}
