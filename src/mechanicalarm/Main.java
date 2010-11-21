/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mechanicalarm;

/**
 *
 * @author Rolo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BrazoMecanicoControlador brazoMecanico = new BrazoMecanicoControlador();
        String comando1 = "mover 0 en 3";
        String comando2 = "apilar 1 en 2";
        String comando3 = "apilar el bloque 3 en 2";
        String comando4 = "mover 4 sobre 3";
        String comando5 = "apilar 2 sobre 3";
        brazoMecanico.ingresarNumeroPosiciones(5);

        brazoMecanico.ingresarComando(comando1);
        brazoMecanico.ingresarComando(comando2);
        brazoMecanico.ingresarComando(comando3);
        brazoMecanico.ingresarComando(comando4);
        brazoMecanico.ingresarComando(comando5);

        while (brazoMecanico.existeSiguienteComando()) {
            try {
                brazoMecanico.ejecutarSiguienteComando();
            } catch (Exception exception) {
            }
        }

    }

}
