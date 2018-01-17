import React, { Component } from 'react';
import NumericInput from 'react-numeric-input';
import {RadioGroup, Radio} from 'react-radio-group';
import * as THREE from 'three';
import React3 from 'react-three-renderer';
import './App.css';

const URL_WITHOUT_PORT = 'http://34.241.58.25';
const URL_PREFIX = URL_WITHOUT_PORT + ':8765';

class FunctionComposer extends Component {

  constructor(props) {
    super(props);
    this.cameraPosition = new THREE.Vector3(0, 0, 5);
    this.state = {function: "x", selectedValue: 'linear', a: 0, b: 0, offset: 0};
    this.funObj = {"@type": "identity", "type": "identity"};
    this.maxVal = 1;
    this.minVal = 0;
  }

  handleChangeFun(e) {
    this.setState({selectedValue: e});
  }

  handleChangeA(e) {
    this.setState({a: e});
  }

  handleChangeB(e) {
    this.setState({b: e});
  }

  applyFunction() {
    let newFunc = "";
    if (this.state.a !== 1) {
      newFunc = this.state.a + " * ";
    }

    switch(this.state.selectedValue){
      case "linear": 	newFunc += "(" + this.state.function + ")"; break;
      case "quadratic":	newFunc += "(" + this.state.function + ")^2"; break;
      case "sqrt": 	newFunc += "sqrt(" + this.state.function + ")"; break;
      default: break;
    }
    if (this.state.b > 0) {
      newFunc += " + " + this.state.b;
    }

    //modify funObj
    this.funObj = {
      "@type": 	this.state.selectedValue,
      "type": 	this.state.selectedValue,
      "a":	this.state.a,
      "b":	this.state.b,
      "inner":	this.funObj
    };
    // ~ modify funObj


    this.setState({function: newFunc});
  }

  reset() {
    let newFunc = "x";
    this.funObj = {"@type": "identity", "type": "identity"};
    this.setState({function: newFunc});
  }
  
  getHeaders() {
    return {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*'
    };
  }

  computeIntegral() {
    let body = JSON.stringify(this.funObj);
    fetch(URL_PREFIX + '/integral-service/integral', {method: 'POST', headers: this.getHeaders(), body: body})
      .then(response => response.json())
      .then(json => this.setState({result: json}));
  }
  
  computeValue() {
    let body = JSON.stringify(this.funObj);
    fetch(URL_PREFIX + '/function-value-service/rest/functions/get-value?x=0.5', {method: 'POST', headers: this.getHeaders(), body: body})
      .then(response => response.json())
      .then(json => this.setState({result: json}));
  }
  
  generateTasks() {
    fetch(URL_PREFIX + '/client-service-two/generate-tasks', {method: 'GET', headers: this.getHeaders()})
      .then(response => response.text())
      .then(text => this.setState({result: text}));
  }
  
  randomTask() {
    fetch(URL_PREFIX + '/client-service-one/random', {method: 'GET', headers: this.getHeaders()})
      .then(response => response.text())
      .then(text => this.setState({result: text}));
  }
  
  randomOffset() {
    fetch(URL_PREFIX + '/client-service-two/random-offset', {method: 'GET', headers: this.getHeaders()})
      .then(response => response.text())
      .then(text => this.setState({offset: text}));
  }

  getFuncValue(x, func) {
    let inner = 0;
    switch(func.type){
      case "linear": 	inner = this.getFuncValue(x, func.inner);		break;
      case "quadratic":	inner = Math.pow(this.getFuncValue(x, func.inner), 2);	break;
      case "sqrt": 	inner = Math.sqrt(this.getFuncValue(x, func.inner));	break;
      case "identity": 	return x;
      default: break;
    }
    return func.a * inner + func.b;
  }

  recomputeFunction() {
    let vertices = [];
    this.maxVal = Number.NEGATIVE_INFINITY;
    this.minVal = Number.POSITIVE_INFINITY;
    for (let i = 0; i <= 1; i+=0.001) {
       let val = this.getFuncValue(i, this.funObj);
       vertices.push(new THREE.Vector3(i, val, 0));
       if (val > this.maxVal) this.maxVal = val;
       if (val < this.minVal) this.minVal = val;
    }
    return (<points key={Math.random()}><geometry vertices={vertices} /><meshBasicMaterial color={0xa6cee3} /></points>);
  }

  render() {
    let points = this.recomputeFunction();
    const width = window.innerWidth*0.4; // canvas width
    const height = window.innerHeight*0.4; // canvas height

    return (
      <div>
	<div>
	    <a href={URL_PREFIX + '/zipkin/'}>ZIPKIN</a>
	    <br></br>
	    <a href={URL_PREFIX + '/discovery/'}>EUREKA</a>
	    <br></br>
	    <a href={URL_WITHOUT_PORT+ ':5601'}>KIBANA</a>
        </div>
        <div className="FunctionComposer">
		<p>{this.state.offset}</p>
        	<div className="ParamsPicker">
        	  <p>a=<NumericInput step={0.01} precision={2} value={this.state.a} min={-1} max={1} onChange={this.handleChangeA.bind(this)}/></p>
        	  <p>b=<NumericInput step={0.01} precision={2} value={this.state.b} min={0} max={1} onChange={this.handleChangeB.bind(this)}/></p>
        	</div>
          <div>
          	<form>
          	  <RadioGroup className="FunctionChooser" name="fun" selectedValue={this.state.selectedValue} onChange={this.handleChangeFun.bind(this)}>
          	    <label>
          	      <Radio value="linear" />{this.state.a} * ({this.state.function}) + {this.state.b}
          	    </label>
          	    <label>
          	      <Radio value="quadratic" />{this.state.a} * ({this.state.function})^2 + {this.state.b}
          	    </label>
          	    <label>
          	      <Radio value="sqrt" />{this.state.a} * sqrt({this.state.function}) + {this.state.b}
          	    </label>
          	  </RadioGroup>
          	</form>
          </div>
          <div>
            <button onClick={this.applyFunction.bind(this)}>APPLY</button>
          	<button onClick={this.reset.bind(this)}>RESET</button>
          </div>
        </div>

        <div className="FunctionVisualiser">
	  <div>
	    <button onClick={this.computeIntegral.bind(this)}>COMPUTE INTEGRAL</button>
	    <button onClick={this.computeValue.bind(this)}>COMPUTE VALUE (x=0.5)</button>
          </div>
          <div>
	    <button onClick={this.generateTasks.bind(this)}>GENERATE TASKS</button>
	    <button onClick={this.randomTask.bind(this)}>RANDOM TASK</button>
	    <button onClick={this.randomOffset.bind(this)}>RANDOM OFFSET</button>
          </div>
          <p>{this.state.function}</p>
          <p>{this.state.result}</p>

          <React3
                  mainCamera="camera"
                  width={width}
                  height={height}
                >
                  <scene>
                    <orthographicCamera
                      name="camera"
                      left={0}
                      right={1}
                      top={this.maxVal}
                      bottom={this.minVal}

                      position={this.cameraPosition}
                    />
                    {points}
                  </scene>
          </React3>
        </div>
      </div>
    );
  }
}

export default FunctionComposer;
