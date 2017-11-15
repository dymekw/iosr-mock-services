package pl.edu.agh.ki.iosr.mockservices;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.edu.agh.ki.iosr.mockservices.control.FunctionValueClient;
import pl.edu.agh.ki.iosr.mockservices.control.IntegralComputingService;
import pl.edu.agh.ki.iosr.mockservices.impl.IdentityFunctionDTO;

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

    private static class MockFunctionValueClient implements FunctionValueClient {

        @Override
        public double getValue(FunctionDTO f, double x) {
            return f.getValue(x);
        }
    }
}