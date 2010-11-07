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
    public void SeCrearonLasCajasCorrectamente()
    {
        //Without implementation
        assertTrue(false);
    }


}