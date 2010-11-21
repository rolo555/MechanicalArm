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

    List<Comando> listaComandos;
    int indiceComandoActual;
    ConjuntoBloques bloques;


    public BrazoMecanicoControlador() {
        listaComandos = new ArrayList<Comando>();
        indiceComandoActual = 0;
    }

    public int numeroComandos() {
        return listaComandos.size();
    }

    public boolean ingresarComando(String comando) {
        String comandoAux = comando.toLowerCase();
        String[] partesComando = comandoAux.split(" ");
        if(partesComando.length==4)
        {
            Comando comandoActual = Comando.ConvertirComando(partesComando);
            if(comandoActual==null)
            {
                listaComandos.add(new ComandoInvalido(comando));
                return false;
            }
            else
            {
                listaComandos.add(comandoActual);
                return true;
            }
        }
        else
        {
            listaComandos.add(new ComandoInvalido(comando));
            return false;
        }
    }

    public String retornarComando(int posicion) {
        return listaComandos.get(posicion).TransformarEnString();
    }

    public boolean existeSiguienteComando()
    {
        return indiceComandoActual<listaComandos.size();
    }

    public void ejecutarSiguienteComando() {
        try {
            listaComandos.get(indiceComandoActual).EjecutarComando(bloques);
        } catch (Exception exp) {
            throw new IllegalArgumentException(exp.getMessage());
        }
        finally
        {
            ++indiceComandoActual;
        }
    }

    public List<Integer> retornarBloquesDeUnaPosicion(int i) {
        return bloques.retornarBloquesDeUnaPosicion(i);
    }

    public void ingresarNumeroPosiciones(int numeroPosiciones) {
        bloques = new ConjuntoBloques();
        bloques.crearPosiciones(numeroPosiciones);
    }
}
