package pl.edu.agh.ki.iosr.mockservices;


import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.ki.iosr.mockservices.impl.IdentityFunctionDTO;
import pl.edu.agh.ki.iosr.mockservices.impl.LinearFunctionDTO;
import pl.edu.agh.ki.iosr.mockservices.impl.QuadraticFunctionDTO;

public class FunctionValueTest {

    @Test
    public void testIdentityFunction() {
        FunctionDTO f = new IdentityFunctionDTO();
        Assert.assertEquals(0, f.getValue(0), 0);
        Assert.assertEquals(10, f.getValue(10), 0);
        Assert.assertEquals(-10, f.getValue(-10), 0);
        Assert.assertEquals(10000, f.getValue(10000), 0);
    }

    @Test
    public void testQuadraticFunction() {
        final double delta = 0.001;
        FunctionDTO f = new QuadraticFunctionDTO(1,0, new IdentityFunctionDTO());
        Assert.assertEquals(0, f.getValue(0), delta);
        Assert.assertEquals(100, f.getValue(10), delta);
        Assert.assertEquals(100, f.getValue(-10), delta);
        Assert.assertEquals(0.01, f.getValue(0.1), delta);
    }

    @Test
    public void testLinearFunction() {
        final double delta = 0.001;
        FunctionDTO f = new LinearFunctionDTO(2,10, new IdentityFunctionDTO());
        Assert.assertEquals(10, f.getValue(0), delta);
        Assert.assertEquals(30, f.getValue(10), delta);
        Assert.assertEquals(-10, f.getValue(-10), delta);
        Assert.assertEquals(10.2, f.getValue(0.1), delta);
    }
}
