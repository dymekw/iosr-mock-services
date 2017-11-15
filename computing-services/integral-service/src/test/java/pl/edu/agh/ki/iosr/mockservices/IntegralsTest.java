package pl.edu.agh.ki.iosr.mockservices;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.edu.agh.ki.iosr.mockservices.control.FunctionValueClient;
import pl.edu.agh.ki.iosr.mockservices.control.IntegralComputingService;
import pl.edu.agh.ki.iosr.mockservices.impl.IdentityFunctionDTO;
import pl.edu.agh.ki.iosr.mockservices.impl.QuadraticFunctionDTO;

public class IntegralsTest {

    private static final Double EPSILON = 0.001;
    private static IntegralComputingService integralComputingService;

    @BeforeClass
    public static void init() {
        integralComputingService = new IntegralComputingService(new MockFunctionValueClient());
    }


    @Test
    public void identityIntegralTest() {
        FunctionDTO f = new IdentityFunctionDTO();
        Assert.assertEquals(2, integralComputingService.computeIntegral(f, 0, 2, EPSILON), EPSILON);
        Assert.assertEquals(8, integralComputingService.computeIntegral(f, 0, 4, EPSILON), EPSILON);
        Assert.assertEquals(50, integralComputingService.computeIntegral(f, 0, 10, EPSILON), EPSILON);
        Assert.assertEquals(1.5, integralComputingService.computeIntegral(f, -1, 2, EPSILON), EPSILON);
    }

    @Test
    public void identityIntegralTest2() {
        FunctionDTO f = new QuadraticFunctionDTO(1, 0, new IdentityFunctionDTO());
        Assert.assertEquals(8.0/3.0, integralComputingService.computeIntegral(f, 0, 2, EPSILON), 0.01);
        Assert.assertEquals(64.0/3.0, integralComputingService.computeIntegral(f, 0, 4, EPSILON), 0.01);
        Assert.assertEquals(1000.0/3.0, integralComputingService.computeIntegral(f, 0, 10, EPSILON), 0.1);
        Assert.assertEquals(3.0, integralComputingService.computeIntegral(f, -1, 2, EPSILON), 0.02);
    }

    private static class MockFunctionValueClient implements FunctionValueClient {

        @Override
        public double getValue(FunctionDTO f, double x) {
            return f.getValue(x);
        }
    }
}