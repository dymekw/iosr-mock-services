package pl.edu.agh.ki.iosr.mockservices.impl;

import pl.edu.agh.ki.iosr.mockservices.FunctionDTO;

public class IdentityFunctionDTO extends FunctionDTO {
    public IdentityFunctionDTO() {
        super(1, 0, null);
    }

    @Override
    public double getValue(double x) {
        return x;
    }

    @Override
    public String toString() {
        return "x";
    }
}
