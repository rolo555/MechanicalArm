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
    public void nuevoTieneCeroNumeroDeCajas(){
        assertEquals(0, brazo.numeroDeCajas());
    }

    @Test
    public void alIngresarNumeroDeCajasElNumeroDeCajasNoEsCero(){
        brazo.crearPosiciones(1);
        assertNotSame(0, brazo.numeroDeCajas());
    }

    @Test
    public void alIngresarNumeroXDeCajasElNumeroDeCajasEsIgualAX(){
        brazo.crearPosiciones(5);
        assertEquals(5, brazo.numeroDeCajas());
    }

    @Test (expected = IllegalStateException.class)
    public void elNumeroDeCajasEsMayorACeroYMenorOIgualA25(){
        brazo.crearPosiciones(0);
        brazo.crearPosiciones(26);
    }

    @Test
    public void lasCajasSeCreanDeManeraAscendenteYEnLasPosicionesCorrectas()
    {
        brazo.crearPosiciones(10);
        for(int i=0; i<10; ++i)
        {
            int actual = brazo.retornarCaja(i);
            assertEquals(i, actual);
        }
    }

    @Test
    public void seQuitoUnaCajaDeUnaPosicionDadaValida()
    {
        brazo.crearPosiciones(10);
        brazo.quitarCaja(4);
        int actual = brazo.retornarCaja(4);
        assertEquals(-1,actual);
    }

    @Test (expected = IllegalArgumentException.class )
    public void SeQuitoUnaCajaDeUnaPosicionDadaInvalida()
    {
        brazo.crearPosiciones(10);
        brazo.quitarCaja(11);
    }

    @Test
    public void sePosicionoLaCajaEnElLugarCorrectoValido()
    {
        brazo.crearPosiciones(10);
        brazo.posicionarCaja(5, 0);
        int actualCaja = brazo.retornarCaja(0);
        int expect = 5;
        assertEquals(expect, actualCaja);
    }

    @Test (expected=IllegalArgumentException.class)
    public void sePosicionoLaCajaEnUnLugarInvalido()
    {
        brazo.crearPosiciones(10);
        brazo.posicionarCaja(5, 11);
    }

    @Test
    public void alPosicionarUnaCajaEnUnLugarYaOcupadoElNumeroCajasEnEsaPosicionNoEsUno()
    {
        brazo.crearPosiciones(10);
        brazo.posicionarCaja(5, 4);
        int actual = brazo.numeroCajasApiladas(4);
        assertNotSame(1, actual);
    }

    @Test
    public void alPosicionarVariasCajasEnUnLugarYaOcupadoElNumeroDeCajasEsIgualALaCantidadDeCajasPosicionadas()
    {
        brazo.crearPosiciones(10);
        brazo.posicionarCaja(5, 4);
        brazo.posicionarCaja(3, 4);
        brazo.posicionarCaja(6,4);
        int actual = brazo.numeroCajasApiladas(4);
        int expect = 4;
        assertEquals(expect, actual);
    }



}