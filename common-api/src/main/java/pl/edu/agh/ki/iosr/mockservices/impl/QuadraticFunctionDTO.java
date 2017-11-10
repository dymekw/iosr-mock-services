package pl.edu.agh.ki.iosr.mockservices.impl;

import pl.edu.agh.ki.iosr.mockservices.FunctionDTO;

public class QuadraticFunctionDTO extends FunctionDTO {

    public QuadraticFunctionDTO() { }

    public QuadraticFunctionDTO(double a, double b, FunctionDTO inner) {
        super(a, b, inner);
    }

    @Override
    public double getValue(double x) {
        return a * Math.pow(inner.getValue(x),2) + b;
    }

    @Override
    public String toString() {
        return a + " * (" + inner.toString() + ")^2 + " + b;
    }
}
