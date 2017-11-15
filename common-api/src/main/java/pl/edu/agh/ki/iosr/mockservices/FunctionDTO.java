package pl.edu.agh.ki.iosr.mockservices;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import pl.edu.agh.ki.iosr.mockservices.impl.IdentityFunctionDTO;
import pl.edu.agh.ki.iosr.mockservices.impl.LinearFunctionDTO;
import pl.edu.agh.ki.iosr.mockservices.impl.QuadraticFunctionDTO;
import pl.edu.agh.ki.iosr.mockservices.impl.SquareFunctionDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = IdentityFunctionDTO.class, name = "identity"),
        @JsonSubTypes.Type(value = QuadraticFunctionDTO.class, name = "quadratic"),
        @JsonSubTypes.Type(value = LinearFunctionDTO.class, name = "linear"),
        @JsonSubTypes.Type(value = SquareFunctionDTO.class, name = "sqrt"),
    }
)
public abstract class FunctionDTO {
    protected double a;
    protected double b;
    protected FunctionDTO inner;

    public FunctionDTO(){}

    public FunctionDTO(double a, double b, FunctionDTO inner) {
        this.a = a;
        this.b = b;
        this.inner = inner;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public FunctionDTO getInner() {
        return inner;
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setB(double b) {
        this.b = b;
    }

    public void setInner(FunctionDTO inner) {
        this.inner = inner;
    }

    public abstract double getValue(double x);
}
