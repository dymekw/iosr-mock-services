package pl.edu.agh.ki.iosr.mockservices;

import pl.edu.agh.ki.iosr.mockservices.impl.IdentityFunctionDTO;
import pl.edu.agh.ki.iosr.mockservices.impl.LinearFunctionDTO;
import pl.edu.agh.ki.iosr.mockservices.impl.QuadraticFunctionDTO;

import java.util.Random;

public class RandomFunctionProvider {

    private static final int N = 3;

    public static FunctionDTO generateFunction() {
        return generateFunction(new IdentityFunctionDTO());
    }

    private static FunctionDTO generateFunction(FunctionDTO inner) {
        Random r = new Random();
        int rand = r.nextInt() % N;

        FunctionDTO result;
        if (rand == 0) {
            return inner;
        } else if (rand == 1) {
            result = new LinearFunctionDTO(r.nextFloat(), r.nextFloat(), inner);
        } else if (rand == 2) {
            result = new QuadraticFunctionDTO(r.nextFloat(), r.nextFloat(), inner);
        }else {
            result = new IdentityFunctionDTO();
        }

        return generateFunction(result);
    }

}
