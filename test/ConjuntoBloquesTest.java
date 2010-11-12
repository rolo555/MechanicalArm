/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.lang.IllegalStateException;
import mechanicalarm.ConjuntoBloques;
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
public class ConjuntoBloquesTest {

    private ConjuntoBloques bloques;

    @Before
    public void setUp(){
        bloques = new ConjuntoBloques();
    }

    @Test
    public void nuevoTieneCeroNumeroDePosiciones(){
        assertEquals(0, bloques.numeroDePosiciones());
    }

    @Test
    public void alIngresarNumeroDePosicionesElNumeroDePosicionesNoEsCero(){
        bloques.ingresarPosiciones(1);
        assertNotSame(0, bloques.numeroDePosiciones());
    }

    @Test
    public void alIngresarNumeroDePosicionesElNumeroDePosicionesEsIgualAlIngresado(){
        bloques.ingresarPosiciones(5);
        assertEquals(5, bloques.numeroDePosiciones());
    }

    @Test (expected = IllegalStateException.class)
    public void elNumeroDePosicionesEsMayorACeroYMenorOIgualA25(){
        bloques.ingresarPosiciones(0);
        bloques.ingresarPosiciones(26);
    }

    @Test
    public void losBloquesSeCreanDeManeraAscendenteYEnLasPosicionesCorrectas()
    {
        bloques.ingresarPosiciones(10);
        for(int i=0; i<10; ++i)
        {
            int actual = bloques.retornarValorBloque(i);
            assertEquals(i, actual);
        }
    }

    @Test
    public void seQuitoUnBloqueDeUnaPosicionDadaValida()
    {
        bloques.ingresarPosiciones(10);
        bloques.quitarBloque(4);
        int actual = bloques.retornarValorBloque(4);
        assertEquals(-1,actual);
    }

    @Test (expected = IllegalArgumentException.class )
    public void SeQuitoUnBloqueDeUnaPosicionDadaInvalida()
    {
        bloques.ingresarPosiciones(10);
        bloques.quitarBloque(11);
    }

    @Test
    public void sePosicionoElBloqueEnElLugarCorrectoValido()
    {
        bloques.ingresarPosiciones(10);
        bloques.posicionarBloque(5, 0);
        int actualCaja = bloques.retornarValorBloque(0);
        int expect = 5;
        assertEquals(expect, actualCaja);
    }

    @Test (expected=IllegalArgumentException.class)
    public void sePosicionoElBloqueEnUnLugarInvalido()
    {
        bloques.ingresarPosiciones(10);
        bloques.posicionarBloque(5, 11);
    }

    @Test
    public void alPosicionarUnBloqueEnUnLugarYaOcupadoElNumeroBloquesEnEsaPosicionNoEsUno()
    {
        bloques.ingresarPosiciones(10);
        bloques.posicionarBloque(5, 4);
        int actual = bloques.numeroBloquesApilados(4);
        assertNotSame(1, actual);
    }

    @Test
    public void alPosicionarVariosBloquesEnUnLugarYaOcupadoElNumeroDeBloquesEsIgualALaCantidadDeBloquesPosicionadas()
    {
        bloques.ingresarPosiciones(10);
        bloques.posicionarBloque(5, 4);
        bloques.posicionarBloque(3, 4);
        bloques.posicionarBloque(6,4);
        int actual = bloques.numeroBloquesApilados(4);
        int expect = 4;
        assertEquals(expect, actual);
    }



}