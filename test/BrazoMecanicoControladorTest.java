/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import mechanicalarm.BrazoMecanicoControlador;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author FireHunter
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
    public void AlCrearElBrazoMecanicoNoTieneComandos()
    {
        brazoMecanico = new BrazoMecanicoControlador();
        int numeroComandos = brazoMecanico.numeroComandos();
        assertEquals(0, numeroComandos);
    }

    @Test
    public void AlIngresarUnComandoElNumeroDeComnadosNoEsCero()
    {
        brazoMecanico.ingresarComando("Mover 1 en 2");
        int numeroComandos = brazoMecanico.numeroComandos();
        assertNotSame(0, numeroComandos);
    }

    @Test
    public void AlIngresarVariosComandosElNumeroDeComandosEsIgualAlNumeroIngresado()
    {
        brazoMecanico.ingresarComando("Mover 1 sobre 2");
        brazoMecanico.ingresarComando("Mover 3 en 2");
        brazoMecanico.ingresarComando("Apilar 2 en 4");
        int numeroComandos = brazoMecanico.numeroComandos();
        assertEquals(3,numeroComandos);
    }

    @Test
    public void AlIngresarUnComandoEsteEsIngresadoCorrectamente()
    {
        brazoMecanico.ingresarComando("Mover 1 sobre 2");
        String comando = brazoMecanico.retornarComando(0);
        assertEquals("Mover 1 sobre 2", comando);
    }

    @Test
    public void AlIngresarVariosComandosEstoSeIngresanCorrectamente()
    {
        String [] listaComandos = {"Mover 1 sobre 2", "Mover 3 en 4", "Apilar 2 en 4"};
        for(String comando: listaComandos)
        {
            brazoMecanico.ingresarComando(comando);
        }
        int iteradorComandos=0;
        for(String comando:listaComandos)
        {
            String actualComando=brazoMecanico.retornarComando(iteradorComandos);
            assertEquals(comando, actualComando);
            ++iteradorComandos;
        }
    }

}