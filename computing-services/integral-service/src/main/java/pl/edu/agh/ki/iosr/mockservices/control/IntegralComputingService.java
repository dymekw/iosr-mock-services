package pl.edu.agh.ki.iosr.mockservices.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.ki.iosr.mockservices.FunctionDTO;

@Service
public class IntegralComputingService {

    private final FunctionValueClient client;

    @Autowired
    public IntegralComputingService(FunctionValueClient client) {
        this.client = client;
    }

    public double computeIntegral(FunctionDTO f, double a, double b, double e) {
        double P1 = (b-a) * (client.getValue(f, a) + client.getValue(f, b)) / 2;
        double P2 = (b-a) * (client.getValue(f, a)/2 + client.getValue(f, b)/2 + client.getValue(f, (a+b)/2))/2;

        return computeIntegral(f, a, b ,e, P1, P2);
    }

    private double computeIntegral(FunctionDTO f, double a, double b, double e, double P1, double P2) {
        if (Math.abs(P1 - P2) <= e) {
            return (P1 + P2) / 2;
        }
        return computeIntegral(f, a, (a+b)/2, e) + computeIntegral(f, (a+b)/2, b, e);
    }
}
