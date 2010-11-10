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

    private Stack<Integer> [] cajas;
    private int numeroDeCajas = 0;

    public int numeroDeCajas() {
        return numeroDeCajas;
    }

    public void crearPosiciones(int numeroDeCajas) {
        if (numeroDeCajas > 0 && numeroDeCajas <= 25) {
            this.numeroDeCajas = numeroDeCajas;
            cajas = new Stack [this.numeroDeCajas];
            for (int i = 0; i < this.numeroDeCajas; ++i) {
                cajas[i] = new Stack<Integer>();
                cajas[i].push(i);
            }
        } else {
            throw new IllegalStateException("El numero de cajas tiene que ser mayor a 0 y menor a 26.");
        }
    }

    public void posicionarCaja(int valor, int posicion) {
        if (posicion < this.numeroDeCajas) {
            cajas[posicion].push(valor);
        } else {
            throw new IllegalArgumentException("La posicion destino no existe");
        }
    }

    public int retornarCaja(int posicion) {
        if(cajas[posicion].empty())
        {
            return -1;
        }
        else
        {
            return cajas[posicion].peek();
        }
    }

    public void quitarCaja(int posicion) {
        if (posicion < this.numeroDeCajas) {
            cajas[posicion].pop();
        } else {
            throw new IllegalArgumentException("La posicion requerida no existe");
        }
    }

    public int numeroCajasApiladas(int posicion) {
        return cajas[posicion].size();
    }
}
