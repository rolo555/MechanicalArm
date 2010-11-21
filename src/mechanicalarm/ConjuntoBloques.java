
package mechanicalarm;

import java.util.List;
import java.util.Stack;

/**
 *
 * @author Ismael Rolando, Lopez Jhenier
 */
public class ConjuntoBloques {
    /**
     * Atributos privados del ConjuntoBloques que es una pila de listas de numeros llamaso bloques y el numero de bloques que tiene el conjunto
     */
    private Stack<Integer>[] bloques;
    private int numeroDeBloques = 0;
    /**
     * Funcion para que regresa el numero de bloques que contiene el cojuntoBloques
     * @return el numero de bloques del conjunto
     */
    public int numeroDeBloques() {
        return numeroDeBloques;
    }
    /**
     * Funcion que verifica que el numero del conjunto sea mayor a 0 y menor a 26, de ser asi, crea las posiciones correspondientes.
     * @param el numero de posiciones que se desea que tenga el conjunto
     */
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
    /**
     * Funcino que ingresa el bloque con el valor correspondiente a su posicion
     * @param valor correspondiente a un bloque
     * @param posicion correspondientre a un bloque
     */
    public void posicionarBloque(int valor, int posicion) {
        if (posicion < this.numeroDeBloques) {
            bloques[posicion].push(valor);
        } else {
            throw new IllegalArgumentException("La posicion destino no existe");
        }
    }
    /**
     * Funcion que regresa el valor del ultimo bloque apilado en una posicion, si esta esta vacia regresa "-1"
     * @param posicion del conjunto que se desea ver
     * @return el valor del ultimo bloque apilado(si la posicion esta vacia regresa "-1")
     */
    public int mostrarUltimoBloqueApilado(int posicion) {
        if (bloques[posicion].empty()) {
            return -1;
        } else {
            return bloques[posicion].peek();
        }
    }
    /**
     * Funcion que elimina el ultimo bloque apilado en una posicion dada
     * @param posicion del conjunto que se desea eliminar
     */
    public void quitarUltimoBloqueApilado(int posicion) {
        if (posicion < this.numeroDeBloques) {
            bloques[posicion].pop();
        } else {
            throw new IllegalArgumentException("La posicion requerida no existe");
        }
    }
    /**
     * Funcion que regresa el numero de bloques apilados en una posicon dada del conjunto
     * @param posicion del junto que se desea conocer
     * @return el numero de bloques apilados en la posicion dada
     */
    public int numeroBloquesApilados(int posicion) {
        return bloques[posicion].size();
    }
    /**
     * Funcion que busca la posicion del conjunto donde se encuentra un bloque dado
     * @param valor del bloque que se desea conocer posicion
     * @return poscicion donde se encuentra ubicado en el conjuntod el bloque con el valor dado
     */
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
    /**
     * Funcion que calcula el numero de bloques apilados sobre un bloque con el valor igual al dado
     * @param valor del bloque del cual se desea conocer el numero de bloques apilados sobre el
     * @return el numero de bloques apilados sobre el bloque con el valor igual al dado
     */
    public int calcularNumeroDeBloquesApiladosSobre(int bloque) {
        if (bloque < this.numeroDeBloques && bloque >= 0) {
            int posicion = this.buscarPosicionDeBloque(bloque);
            int numeroDeBloquesApiladosSobreBloque = this.bloques[posicion].search(bloque) - 1;
            return numeroDeBloquesApiladosSobreBloque;
        } else {
            throw new IllegalArgumentException("Ese bloque no existe.");
        }
    }
    /**
     * Funcion que mueve el ultimo bloque apilado en la posicion "posX" sobre la pila que se encuentre en la posicion"posY"
     * @param posicion que se desea desapilar
     * @param posicion que se desea apilar
     */
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
    /**
     * Funcion que retorna el ultimo bloque apilado en una posicion dada del conjunto a su posicion original correspondiente
     * @param posicion del conjunto que se desea ordenar
     */
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
    /**
     * Funcion que retorna una lista con el valor de todos los bloques apilados en una posicon dada del conjunto
     * @param posicion del conjunto del cual se desea saber el valor de los bloques apilados en el
     * @return una lista con el valor de todos los bloques apilados en la posicion del conjunto dado
     */
    public List<Integer> retornarBloquesDeUnaPosicion(int posicion) {
        return bloques[posicion].subList(0,bloques[posicion].size() );
    }
    /**
     * Funcion que retorna todo el conjunto de bloques
     * @return el conjunto de bloques
     */
    public Stack<Integer>[] retornarConjuntoDeBloques()
    {
        return this.bloques;
    }

}
