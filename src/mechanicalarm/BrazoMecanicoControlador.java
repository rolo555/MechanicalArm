/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mechanicalarm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FireHunter
 */
public class BrazoMecanicoControlador {

    List<String> listaComandos;

    public BrazoMecanicoControlador()
    {
        listaComandos= new ArrayList<String>();
    }

    public int numeroComandos() {
        return listaComandos.size();
    }

    public void ingresarComando(String comando) {
        listaComandos.add(comando);
    }

    public String retornarComando(int posicion) {
        return listaComandos.get(posicion);
    }

    

}
