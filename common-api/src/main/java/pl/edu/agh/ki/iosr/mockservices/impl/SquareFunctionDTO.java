package pl.edu.agh.ki.iosr.mockservices.impl;


import pl.edu.agh.ki.iosr.mockservices.FunctionDTO;

public class SquareFunctionDTO extends FunctionDTO {

    public SquareFunctionDTO() { }

    public SquareFunctionDTO(double a, double b, FunctionDTO inner) {
        super(a, b, inner);
    }

    @Override
    public double getValue(double x) {
        double innerValue = inner.getValue(x);
        if (innerValue < 0) {
            throw new RuntimeException("Arg less than 0!!!");
        }
        return a*Math.sqrt(innerValue) + b;
    }

    @Override
    public String toString() {
        return a + " * sqrt(" + inner.toString() + ") + " + b;
    }
}
