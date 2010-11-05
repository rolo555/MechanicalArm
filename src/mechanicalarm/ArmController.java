/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mechanicalarm;

/**
 *
 * @author Rolo
 */
public class ArmController {

    private int numeroDeCajas = 0;

    public int numeroDeCajas(){
        return numeroDeCajas;
    }

    public void ingresarCajas(int numeroDeCajas) {
        if( numeroDeCajas > 0 && numeroDeCajas <= 25  )
            this.numeroDeCajas = numeroDeCajas;
        else
            throw new IllegalStateException("El numero de cajas tiene que ser mayor a 0 y menor a 26.");
    }
}
