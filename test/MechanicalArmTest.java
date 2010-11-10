/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.lang.IllegalStateException;
import mechanicalarm.ArmController;
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
public class MechanicalArmTest {

    private ArmController controlador;

    @Before
    public void setUp(){
        controlador = new ArmController();
    }

    @Test
    public void nuevoTieneCeroNumeroDeCajas(){
        assertEquals(0, controlador.numeroDeCajas());
    }

    @Test
    public void alIngresarNumeroDeCajasElNumeroDeCajasNoEsCero(){
        controlador.ingresarCajas(1);
        assertNotSame(0, controlador.numeroDeCajas());
    }

    @Test
    public void alIngresarNumeroXDeCajasElNumeroDeCajasEsIgualAX(){
        controlador.ingresarCajas(5);
        assertEquals(5, controlador.numeroDeCajas());
    }

    @Test (expected = IllegalStateException.class)
    public void elNumeroDeCajasEsMayorACeroYMenorOIgualA25(){
        controlador.ingresarCajas(0);
        controlador.ingresarCajas(26);
    }

    @Test
    public void lasCajasSeCreanDeManeraAscendenteYEnLasPosicionesCorrectas()
    {
        controlador.ingresarCajas(10);
        for(int i=0; i<10; ++i)
        {
            int actual = controlador.retornarCaja(i);
            assertEquals(i, actual);
        }
    }

    @Test
    public void seQuitoUnaCajaDeUnaPosicionDadaValida()
    {
        controlador.ingresarCajas(10);
        controlador.quitarCaja(4);
        int actual = controlador.retornarCaja(4);
        assertEquals(-1,actual);
    }

    @Test (expected = IllegalArgumentException.class )
    public void SeQuitoUnaCajaDeUnaPosicionDadaInvalida()
    {
        controlador.ingresarCajas(10);
        controlador.quitarCaja(11);
    }

    @Test
    public void sePosicionoLaCajaEnElLugarCorrectoValido()
    {
        controlador.ingresarCajas(10);
        controlador.posicionarCaja(5, 0);
        int actualCaja = controlador.retornarCaja(0);
        int expect = 5;
        assertEquals(expect, actualCaja);
    }

    @Test (expected=IllegalArgumentException.class)
    public void sePosicionoLaCajaEnUnLugarInvalido()
    {
        controlador.ingresarCajas(10);
        controlador.posicionarCaja(5, 11);
    }

    @Test
    public void alPosicionarUnaCajaEnUnLugarYaOcupadoElNumeroCajasEnEsaPosicionNoEsUno()
    {
        controlador.ingresarCajas(10);
        controlador.posicionarCaja(5, 4);
        int actual = controlador.numeroCajasApiladas(4);
        assertNotSame(1, actual);
    }

    @Test
    public void alPosicionarVariasCajasEnUnLugarYaOcupadoElNumeroDeCajasEsIgualALaCantidadDeCajasPosicionadas()
    {
        controlador.ingresarCajas(10);
        controlador.posicionarCaja(5, 4);
        controlador.posicionarCaja(3, 4);
        controlador.posicionarCaja(6,4);
        int actual = controlador.numeroCajasApiladas(4);
        int expect = 4;
        assertEquals(expect, actual);
    }



}