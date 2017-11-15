package pl.edu.agh.ki.iosr.mockservices;


import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.ki.iosr.mockservices.control.FunctionValueComputingService;
import pl.edu.agh.ki.iosr.mockservices.impl.IdentityFunctionDTO;
import pl.edu.agh.ki.iosr.mockservices.impl.LinearFunctionDTO;
import pl.edu.agh.ki.iosr.mockservices.impl.QuadraticFunctionDTO;
import pl.edu.agh.ki.iosr.mockservices.impl.SquareFunctionDTO;

public class FunctionValueTest {

    FunctionValueComputingService service = new FunctionValueComputingService();

    @Test
    public void testAbstractFunctionDTO() {
        FunctionDTO f = new IdentityFunctionDTO();

        Assert.assertEquals(1, f.getA(), 0);
        Assert.assertEquals(0, f.getB(), 0);
        Assert.assertEquals(123, f.getValue(123), 0);
        Assert.assertNull(f.getInner());
    }

    @Test
    public void testIdentityFunction() {
        FunctionDTO f = new IdentityFunctionDTO();
        Assert.assertEquals(0, service.getValue(f, 0), 0);
        Assert.assertEquals(10, service.getValue(f, 10), 0);
        Assert.assertEquals(-10, service.getValue(f, -10), 0);
        Assert.assertEquals(10000, service.getValue(f, 10000), 0);
    }

    @Test
    public void testQuadraticFunction() {
        final double delta = 0.001;
        FunctionDTO f = new QuadraticFunctionDTO(1,0, new IdentityFunctionDTO());
        Assert.assertEquals(0, service.getValue(f, 0), delta);
        Assert.assertEquals(100, service.getValue(f, 10), delta);
        Assert.assertEquals(100, service.getValue(f, -10), delta);
        Assert.assertEquals(0.01, service.getValue(f, 0.1), delta);
    }

    @Test
    public void testLinearFunction() {
        final double delta = 0.001;
        FunctionDTO f = new LinearFunctionDTO(2,10, new IdentityFunctionDTO());
        Assert.assertEquals(10, service.getValue(f, 0), delta);
        Assert.assertEquals(30, service.getValue(f, 10), delta);
        Assert.assertEquals(-10, service.getValue(f, -10), delta);
        Assert.assertEquals(10.2, service.getValue(f, 0.1), delta);
    }

    @Test
    public void testSqrtFunction() {
        final double delta = 0.001;
        FunctionDTO f = new SquareFunctionDTO(1, 0, new IdentityFunctionDTO());
        Assert.assertEquals(0, service.getValue(f, 0), delta);
        Assert.assertEquals(3, service.getValue(f, 9), delta);
        Assert.assertEquals(5, service.getValue(f, 25), delta);
        Assert.assertEquals(10, service.getValue(f, 100), delta);
        Assert.assertEquals(0, service.getValue(f, -10), delta);
    }

    @Test(expected = RuntimeException.class)
    public void testSqrtFunctionException() {
        FunctionDTO f = new SquareFunctionDTO(1, 0, new IdentityFunctionDTO());
        f.getValue(-10);
    }
}
