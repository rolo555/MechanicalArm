/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mechanicalarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
        if (partesComando.length == 4 || comandoAux.equals("salir")) {
            if (comandoAux.equals("salir")) {
                listaComandos.add(new ComandoSalir());
                return true;
            } else {
                Comando comandoActual = Comando.ConvertirComando(partesComando);
                if (comandoActual == null) {
                    listaComandos.add(new ComandoInvalido(comando));
                    return false;
                } else {
                    listaComandos.add(comandoActual);
                    return true;
                }
            }
        } else {
            listaComandos.add(new ComandoInvalido(comando));
            return false;
        }
    }

    public String retornarComando(int posicion) {
        return listaComandos.get(posicion).TransformarEnString();
    }

    public boolean existeSiguienteComando() {
        if(listaComandos.get(indiceComandoActual) instanceof ComandoSalir )
            return false;
        if(indiceComandoActual < listaComandos.size())
            return true;
        return false;
    }

    public void ejecutarSiguienteComando() {
        try {
            listaComandos.get(indiceComandoActual).EjecutarComando(bloques);
        } catch (Exception exp) {
            throw new IllegalArgumentException(exp.getMessage());
        } finally {
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

    public Stack<Integer>[] retornarTodosLosBloques() {
        return this.bloques.retornarConjuntoDeBloques();
    }
}
