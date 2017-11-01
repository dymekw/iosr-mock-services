package pl.edu.agh.ki.iosr.mockservices.impl;

import pl.edu.agh.ki.iosr.mockservices.FunctionDTO;

public class LinearFunctionDTO extends FunctionDTO {

    public LinearFunctionDTO() { }

    public LinearFunctionDTO(double a, double b, FunctionDTO inner) {
        super(a, b, inner);
    }

    @Override
    public double getValue(double x) {
        return a * inner.getValue(x) + b;
    }

    @Override
    public String toString() {
        return a + " * (" + inner.toString() + ") + " + b;
    }
}
