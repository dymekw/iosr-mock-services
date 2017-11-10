package pl.edu.agh.ki.iosr.mockservices.control;

import pl.edu.agh.ki.iosr.mockservices.FunctionDTO;

public interface FunctionValueClient {
    double getValue (FunctionDTO f, double x);
}
