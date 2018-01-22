package pl.edu.agh.ki.iosr.mockservices.control;

import org.springframework.stereotype.Service;
import pl.edu.agh.ki.iosr.mockservices.FunctionDTO;

import java.util.Random;

@Service
public class FunctionValueComputingService {

    public double getValue(FunctionDTO function, double x) {
        return getValueInner(function, x);
    }

    private double getValueInner(FunctionDTO function, double x) {
        try {
            Thread.sleep(new Random().nextInt(1000));
            return function.getValue(x) + OffsetProvider.getOffset();
        } catch (Exception e) {
            return 0;
        }
    }

}
