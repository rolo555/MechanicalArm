/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.lang.IllegalStateException;
import mechanicalarm.BrazoMecanico;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rolo
 */
public class BrazoMecanicoTest {

    private BrazoMecanico brazo;

    @Before
    public void setUp(){
        brazo = new BrazoMecanico();
    }

    @Test
    public void nuevoTieneCeroNumeroDeBloques(){
        assertEquals(0, brazo.numeroDeBloques());
    }

    @Test
    public void alIngresarNumeroDeBloquesElNumeroDeBloquesNoEsCero(){
        brazo.crearPosiciones(1);
        assertNotSame(0, brazo.numeroDeBloques());
    }

    @Test
    public void alIngresarNumeroXDeBloquesElNumeroDeBloquesEsIgualAX(){
        brazo.crearPosiciones(5);
        assertEquals(5, brazo.numeroDeBloques());
    }

    @Test (expected = IllegalStateException.class)
    public void elNumeroDeBloquesEsMayorACeroYMenorOIgualA25(){
        brazo.crearPosiciones(0);
        brazo.crearPosiciones(26);
    }

    @Test
    public void losBloquesSeCreanDeManeraAscendenteYEnLasPosicionesCorrectas()
    {
        brazo.crearPosiciones(10);
        for(int i=0; i<10; ++i)
        {
            int actual = brazo.mostrarUltimoBloqueApilado(i);
            assertEquals(i, actual);
        }
    }

    @Test
    public void esPosibleQuitarUnBloqueDeUnaPosicionDadaValida()
    {
        brazo.crearPosiciones(10);
        brazo.quitarUltimoBloqueApilado(4);
        int actual = brazo.mostrarUltimoBloqueApilado(4);
        assertEquals(-1,actual);
    }

    @Test (expected = IllegalArgumentException.class )
    public void noSePuedeQuitarUnBloqueDeUnaPosicionDadaInvalida()
    {
        brazo.crearPosiciones(10);
        brazo.quitarUltimoBloqueApilado(11);
    }

    @Test
    public void esPosibleMoverUnBloqueAUnaPosicionDadaValida()
    {
        brazo.crearPosiciones(10);
        brazo.posicionarBloque(5, 0);
        int actualCaja = brazo.mostrarUltimoBloqueApilado(0);
        int expect = 5;
        assertEquals(expect, actualCaja);
    }

    @Test (expected=IllegalArgumentException.class)
    public void noEsPosibleMoverUnBloqueAUnaPosicionDadaInvalida()
    {
        brazo.crearPosiciones(10);
        brazo.posicionarBloque(5, 11);
    }

    @Test
    public void alPosicionarUnBloqueEnUnLugarYaOcupadoElNumeroDeBloquesEnEsaPosicionAumentaUno()
    {
        brazo.crearPosiciones(10);
        int actual = brazo.numeroBloquesApilados(4);
        brazo.posicionarBloque(5, 4);
        int expect = actual + 1;
        assertEquals(expect, brazo.numeroBloquesApilados(4));
    }

    @Test
    public void alPosicionarVariosBloquesEnUnLugarYaOcupadoElNumeroDeBloquesEsIgualALaCantidadDeBloquesOcupadosMasLosRecienPosicionados()
    {
        brazo.crearPosiciones(10);
        int actual = brazo.numeroBloquesApilados(4);
        brazo.posicionarBloque(5, 4);
        brazo.posicionarBloque(3, 4);
        brazo.posicionarBloque(6,4);
        int expect = actual + 3;
        assertEquals(expect, brazo.numeroBloquesApilados(4));
    }

    @Test
    public void alBuscarLaPosicionDelBloqueXEnPosicionesNuevaEsteSeEncuentraEnX(){
        brazo.crearPosiciones(5);
        int bloqueX = 3;
        int expect = bloqueX;
        assertEquals(expect, brazo.buscarPosicionDeBloque(bloqueX));
    }

    @Test (expected=IllegalArgumentException.class)
    public void noSePuedeBuscarUnBloqueInvalido(){
        brazo.crearPosiciones(5);
        brazo.buscarPosicionDeBloque(5);
        brazo.buscarPosicionDeBloque(-1);
    }

    @Test
    public void esPosibleBuscarLaPosicionDeUnBloqeQueFueMovido(){
        brazo.crearPosiciones(5);
        brazo.posicionarBloque(3, 0);
        int expect = 0;
        assertEquals(expect, brazo.buscarPosicionDeBloque(3));
    }

    @Test
    public void esPosibleCalcularElNumeroDeBloquesApiladosSobreElBloqueX(){
        brazo.crearPosiciones(5);
        brazo.posicionarBloque(1, 0);
        brazo.posicionarBloque(2, 0);
        brazo.posicionarBloque(3, 0);
        brazo.posicionarBloque(4, 0);
        int expect = 3;
        assertEquals(expect, brazo.calcularNumeroDeBloquesApiladosSobre(1));
    }

    @Test (expected=IllegalArgumentException.class)
    public void noEsPosibleCalcularElNumeroDeBloquesApiladosSobreUnBloqueXNoExistene(){
        brazo.crearPosiciones(5);
        brazo.calcularNumeroDeBloquesApiladosSobre(5);
    }


}