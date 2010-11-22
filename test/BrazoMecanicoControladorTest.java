
import java.util.ArrayList;
import java.util.List;
import mechanicalarm.BrazoMecanicoControlador;
import mechanicalarm.ComandoSalir;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ismael Rolando, Lopez Jhenier
 */
public class BrazoMecanicoControladorTest {

    BrazoMecanicoControlador brazoMecanico;

    public BrazoMecanicoControladorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        brazoMecanico = new BrazoMecanicoControlador();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void AlCrearElBrazoMecanicoNoTieneComandos() {
        brazoMecanico = new BrazoMecanicoControlador();
        int numeroComandos = brazoMecanico.numeroComandos();
        assertEquals(0, numeroComandos);
    }

    @Test
    public void AlIngresarUnComandoElNumeroDeComnadosNoEsCero() {
        brazoMecanico.ingresarComando("Mover 1 en 2");
        int numeroComandos = brazoMecanico.numeroComandos();
        assertNotSame(0, numeroComandos);
    }

    @Test
    public void AlIngresarVariosComandosElNumeroDeComandosEsIgualAlNumeroIngresado() {
        brazoMecanico.ingresarComando("Mover 1 sobre 2");
        brazoMecanico.ingresarComando("Mover 3 en 2");
        brazoMecanico.ingresarComando("Apilar 2 en 4");
        int numeroComandos = brazoMecanico.numeroComandos();
        assertEquals(3, numeroComandos);
    }

    @Test
    public void AlIngresarUnComandoEsteEsIngresadoCorrectamente() {
        brazoMecanico.ingresarComando("Mover 1 sobre 2");
        String comando = brazoMecanico.retornarComando(0);
        assertEquals("Mover 1 sobre 2", comando);
    }

    @Test
    public void AlIngresarVariosComandosEstoSeIngresanCorrectamente() {
        String[] listaComandos = {"Mover 1 sobre 2", "Mover 3 en 4", "Apilar 2 en 4"};
        for (String comando : listaComandos) {
            brazoMecanico.ingresarComando(comando);
        }
        int iteradorComandos = 0;
        for (String comando : listaComandos) {
            String actualComando = brazoMecanico.retornarComando(iteradorComandos);
            assertEquals(comando, actualComando);
            ++iteradorComandos;
        }
    }

    @Test
    public void AlIngresarUnComandoSeRetornaTrueSiElComandoEsValido() {
        String comando = "Mover 2 en 1";
        boolean esValido = this.brazoMecanico.ingresarComando(comando);
        assertTrue(esValido);
    }

    @Test
    public void AlIngresarUnComandoSeRetornaFalseSielComandoEsInvalido() {
        String comando = "Apilar el bloque 2 en 34";
        boolean esValido = brazoMecanico.ingresarComando(comando);
        assertFalse(esValido);
    }

    @Test
    public void AlCrearElBrazoMecanicoNoExisteComandoAEjecutar() {
        assertFalse(brazoMecanico.existeComando());
    }

    @Test
    public void AlIngresarUnComandoSiExisteUnSiguienteComandoAEjecutar() {
        String comando = "Apilar 2 en 4";
        brazoMecanico.ingresarComando(comando);
        assertTrue(brazoMecanico.existeComando());
    }

    @Test
    public void SeEjecutaMover3en2CorrectamenteTeniendo5Posiciones() {
        String comando = "mover 3 en 2";
        brazoMecanico.ingresarNumeroPosiciones(5);
        brazoMecanico.ingresarComando(comando);
        brazoMecanico.ejecutarComando();
        List<Integer> listaBloquesEn3 = brazoMecanico.retornarBloquesDeUnaPosicion(3);
        List<Integer> listaBloquesEn2 = brazoMecanico.retornarBloquesDeUnaPosicion(2);
        assertEquals(0, listaBloquesEn3.size());

        assertEquals(0, listaBloquesEn3.size());
        int[] array = {2, 3};
        List<Integer> listaEsperada = crearUnaListaAPartirDeUnArray(array);

        assertTrue(dosListasSonIguales(listaEsperada, listaBloquesEn2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void NoSeEjecutaCorrectamenteMover6en2Teniendo5Posiciones() {
        String comando = "mover 6 en 2";
        brazoMecanico.ingresarNumeroPosiciones(5);
        brazoMecanico.ingresarComando(comando);
        brazoMecanico.ejecutarComando();
    }

    @Test(expected = IllegalArgumentException.class)
    public void NoSeEjecutaCorrectamenteMover2en2Teniendo5Posiciones() {
        String comando = "mover 2 en 2";
        brazoMecanico.ingresarNumeroPosiciones(5);
        brazoMecanico.ingresarComando(comando);
        brazoMecanico.ejecutarComando();
    }

    @Test
    public void SeEjecutaMover3Sobre2Teniendo5Posiciones() {
        String comando = "mover 3 sobre 2";
        brazoMecanico.ingresarNumeroPosiciones(5);
        brazoMecanico.ingresarComando(comando);
        brazoMecanico.ejecutarComando();

        List<Integer> listaBloquesEn3 = brazoMecanico.retornarBloquesDeUnaPosicion(3);
        List<Integer> listaBloquesEn2 = brazoMecanico.retornarBloquesDeUnaPosicion(2);

        assertEquals(0, listaBloquesEn3.size());
        int[] array = {2, 3};
        List<Integer> listaEsperada = crearUnaListaAPartirDeUnArray(array);

        assertTrue(dosListasSonIguales(listaEsperada, listaBloquesEn2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void NoSeEjecutaCorrectamenteMover6Sobre2Teniendo5Posiciones() {
        String comando = "mover 6 sobre 2";
        brazoMecanico.ingresarNumeroPosiciones(5);
        brazoMecanico.ingresarComando(comando);
        brazoMecanico.ejecutarComando();
    }

    @Test(expected = IllegalArgumentException.class)
    public void NoSeEjecutaCorrectamenteMover2Sobre2Teniendo5Posiciones() {
        String comando = "mover 2 sobre 2";
        brazoMecanico.ingresarNumeroPosiciones(5);
        brazoMecanico.ingresarComando(comando);
        brazoMecanico.ejecutarComando();
    }

    @Test
    public void SeEjecutaApilar4en2CorrectamenteTeniendo5Posiciones() {
        String comando = "apilar 4 en 2";
        brazoMecanico.ingresarNumeroPosiciones(5);
        brazoMecanico.ingresarComando(comando);
        brazoMecanico.ejecutarComando();

        List<Integer> listaBloquesEn4 = brazoMecanico.retornarBloquesDeUnaPosicion(4);
        List<Integer> listaBloquesEn2 = brazoMecanico.retornarBloquesDeUnaPosicion(2);

        assertEquals(0, listaBloquesEn4.size());
        int[] array = {2, 4};
        List<Integer> listaEsperada = crearUnaListaAPartirDeUnArray(array);

        assertTrue(dosListasSonIguales(listaEsperada, listaBloquesEn2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void NoSeEjecutaApilar7en3Teniendo6Posiciones() {
        String comando = "apilar 7 en 3";
        brazoMecanico.ingresarNumeroPosiciones(6);
        brazoMecanico.ingresarComando(comando);
        brazoMecanico.ejecutarComando();
    }

    @Test(expected = IllegalArgumentException.class)
    public void NoSeEjecutaApilar3en3Teniendo6Posiciones() {
        String comando = "apilar 3 en 3";
        brazoMecanico.ingresarNumeroPosiciones(6);
        brazoMecanico.ingresarComando(comando);
        brazoMecanico.ejecutarComando();
    }

    @Test
    public void SeEjecutaApilar5Sobre1Teniendo6Posiciones() {
        String comando = "apilar 5 sobre 1";
        brazoMecanico.ingresarNumeroPosiciones(6);
        brazoMecanico.ingresarComando(comando);
        brazoMecanico.ejecutarComando();

        List<Integer> listaBloquesEn5 = brazoMecanico.retornarBloquesDeUnaPosicion(5);
        List<Integer> listaBloquesEn1 = brazoMecanico.retornarBloquesDeUnaPosicion(1);

        assertEquals(0, listaBloquesEn5.size());
        int[] array = {1, 5};
        List<Integer> listaEsperada = crearUnaListaAPartirDeUnArray(array);

        assertTrue(dosListasSonIguales(listaEsperada, listaBloquesEn1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void NoSeEjecutaApilar3Sobre9Teniendo7Posiciones() {
        String comando = "apilar 3 sobre 9";
        brazoMecanico.ingresarNumeroPosiciones(7);
        brazoMecanico.ingresarComando(comando);
        brazoMecanico.ejecutarComando();
    }

    @Test(expected = IllegalArgumentException.class)
    public void NoSeEjecutaApilar2Sobre2Teniendo4Posiciones() {
        String comando = "apilar 2 sobre 2";
        brazoMecanico.ingresarNumeroPosiciones(4);
        brazoMecanico.ingresarComando(comando);
        brazoMecanico.ejecutarComando();
    }

    @Test
    public void SeEjecutanLos4TiposDeComandoYUnComandoInvalido() {
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
        brazoMecanico.ingresarComando(new ComandoSalir());

        while (brazoMecanico.existeComando()) {
            try {
                brazoMecanico.ejecutarComando();
            } catch (Exception exception) {
            }
        }

        int[] array0 = {};
        int[] array1 = {};
        int[] array2 = {};
        int[] array3 = {3, 0, 4, 2, 1};
        int[] array4 = {};

        assertTrue(dosListasSonIguales(crearUnaListaAPartirDeUnArray(array0), brazoMecanico.retornarBloquesDeUnaPosicion(0)));
        assertTrue(dosListasSonIguales(crearUnaListaAPartirDeUnArray(array1), brazoMecanico.retornarBloquesDeUnaPosicion(1)));
        assertTrue(dosListasSonIguales(crearUnaListaAPartirDeUnArray(array2), brazoMecanico.retornarBloquesDeUnaPosicion(2)));
        assertTrue(dosListasSonIguales(crearUnaListaAPartirDeUnArray(array3), brazoMecanico.retornarBloquesDeUnaPosicion(3)));
        assertTrue(dosListasSonIguales(crearUnaListaAPartirDeUnArray(array4), brazoMecanico.retornarBloquesDeUnaPosicion(4)));
    }

    private List<Integer> crearUnaListaAPartirDeUnArray(int[] array) {
        List<Integer> lista = new ArrayList<Integer>();
        for (int i = 0; i < array.length; ++i) {
            lista.add(array[i]);
        }
        return lista;
    }

    private boolean dosListasSonIguales(List<Integer> lista1, List<Integer> lista2) {
        if (lista1.size() != lista2.size()) {
            return false;
        }
        for (int i = 0; i < lista1.size(); ++i) {
            if (lista1.get(i) != lista2.get(i)) {
                return false;
            }
        }
        return true;
    }
//    @Test(expected = IllegalArgumentException.class)
//    public void AlEjecutarSeOmiteLosComandosQueTienenElMismoValorAyB()
//    {
//        String comando = "Apilar 2 en 2";
//        brazoMecanico.ingresarComando(comando);
//
//    }
}
