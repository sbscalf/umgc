"use strict";

let canvas;
let gl;   // The webgl context.

let animatedCheck = document.getElementById("animCheck");
let speedSlider = document.getElementById('speedSlider');
let scaleSlider = document.getElementById('scaleSlider');;
let solarSpeed = 1;
let solarScale = 0.5;

let a_coords_loc;         // Location of the a_coords attribute variable in the shader program.
let a_normal_loc;         // Location of a_normal attributeperspective

let u_modelview;       // Locations for uniform matrices
let u_projection;
let u_normalMatrix;

let u_material;     // An object tolds uniform locations for the material.
let u_lights;       // An array of objects that holds uniform locations for light properties.

let projection = mat4.create();    // projection matrix
let modelview;                     // modelview matrix; value comes from rotator
let normalMatrix = mat3.create();  // matrix, derived from modelview matrix, for transforming normal vectors

let rotator;  // A TrackballRotator to implement rotation by mouse.

let frameNumber = 0;  // frame number during animation (actually only goes up by 0.5 per frame)

let sphere;  // basic objects, created using function createModel
let innerBelt = [];

let matrixStack = [];           // A stack of matrices for implementing hierarchical graphics.

let currentColor = [1, 1, 1, 1];   // The current diffuseColor

function draw() {
  gl.clearColor(0.05, 0.05, 0.05, 1);
  gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

  gl.viewport(0, 0, gl.canvas.width, gl.canvas.height);

  mat4.perspective(projection, Math.PI / 4, gl.canvas.width/gl.canvas.height, 1, 500);
  gl.uniformMatrix4fv(u_projection, false, projection);

  modelview = rotator.getViewMatrix();


  solarSpeed = speedSlider.value;
  solarScale = scaleSlider.value;

  pushMatrix();
  mat4.rotateY(modelview, modelview, 3*Math.PI/2);
  scaleModelView(solarScale);
  sun();
  solarSystem();
  popMatrix();
}

let sunlight = {
  enabled: 1,
  emissiveColor: [1, 0.9, 0],
}

function sun() {
  gl.uniform1i(u_lights[0].enabled, sunlight.enabled);
  gl.uniform3f(u_lights[0].color, 1, 1, 1);

  pushMatrix();
  mat4.translate(modelview, modelview, [0, 0, 0]);
  scaleModelView(4);
  setLightPosition(u_lights[0].position, modelview, [0, 0, 0, 1]);
  gl.uniform3f(u_material.emissiveColor, ...sunlight.emissiveColor);
  sphere.render();
  gl.uniform3f(u_material.emissiveColor, 0, 0, 0);
  popMatrix();

  currentColor = [0.3, 0.3, 0.3, 1];
}

function setLightPosition(u_position_loc, modelview, lightPosition) {
  let transformedPosition = new Float32Array(4);
  vec4.transformMat4(transformedPosition, lightPosition, modelview);
  gl.uniform4fv(u_position_loc, transformedPosition);
}

function solarSystem() {
  pushMatrix();
  renderPlanet(5, 0.04, 0.25, 132, 131, 131); // mercury
  renderPlanet(7, 0.09, 0.6, 196, 112, 39);     // venus

  renderPlanet(9, 0.1, 1, 128, 128, 254, [{     // earth + moon
    distance: 2.5,
    scale: 0.1,
    speed: 1 / 280,
  }]);
  popMatrix();

  renderPlanet(11, 0.05, 1.88, 183, 98, 71);     // mars
  renderAsteroids(innerBelt, 8, 0.5);         // inner asteroid belt
  renderPlanet(18, 1.1, 11.87, 166, 160, 109, [ // jupiter + 4 moons
    {
      distance: 1.01,
      scale: .05,
      speed: .025,
    },
    {
      distance: 1.1,
      scale: .05,
      speed: 0.05
    },
    {
      distance: 1.3,
      scale: .05,
      speed: 0.075
    },
    {
      distance: 1.4,
      scale: .05,
      speed: 0.1
    }
  ]);
  renderPlanet(27, 0.9, 29.477, 207, 192, 162); // saturn
  renderPlanet(38, 0.4, 84.074, 155, 202, 209); // uranus
  renderPlanet(49, 0.3, 164.904, 53, 79, 167);  // neptune

  pushMatrix();
  mat4.rotateX(modelview, modelview, 0.1)
  renderPlanet(59, 0.2, 200, 100, 100, 100);       // pluto -- even though not really planet
  popMatrix();

  popMatrix();
}

function renderPlanet(distance, scale, speed, r, g, b, moons = []) {
  pushMatrix();
  translatePlanet(distance, scale, speed);
  currentColor = [r / 255, g / 255, b / 255, 1];
  sphere.render();
  for (const moon of moons) {
    pushMatrix();
    translatePlanet(moon.distance, moon.scale, moon.speed);
    currentColor = [0.75, 0.75, 0.75, 1];
    sphere.render();
    popMatrix();
  }
  popMatrix();
}

function translatePlanet(distance, scale, speed, r, g, b) {
  speed = 5 / Math.sqrt(speed) * solarSpeed;
  mat4.rotate(modelview, modelview, frameNumber * speed / 180 * Math.PI, [0, 1, 0]);
  let theta = 0 * Math.PI / 180;
  let x = distance * Math.sin(theta);
  let y = 0;
  let z = distance * Math.cos(theta);
  mat4.translate(modelview, modelview, [x, y, -z]);
  scaleModelView(scale);
}

function renderAsteroids(belt, speed, height) {
  pushMatrix();
  speed = 5 / Math.sqrt(speed) * solarSpeed;
  mat4.rotate(modelview, modelview, frameNumber * speed / 180 * Math.PI, [0, 1, 0]);
  mat4.scale(modelview, modelview, [1, height, 1]);
  for (let asteroid of belt) {
    pushMatrix();
    mat4.translate(modelview, modelview, asteroid);
    currentColor = [1, 1, 1, 1];
    scaleModelView(0.01);
    sphere.render();
    popMatrix();
  }
  popMatrix();
}

function pushMatrix() {
  matrixStack.push(mat4.clone(modelview));
}

function popMatrix() {
  modelview = matrixStack.pop();
}

function scaleModelView(scale) {
  mat4.scale(modelview, modelview, [scale, scale, scale]);
}

/**
 *  Create one of the basic objects.  The modelData holds the data for
 *  an IFS using the structure from basic-objects-IFS.js.  This function
 *  creates VBOs to hold the coordinates, normal vectors, and indices
 *  from the IFS, and it loads the data into those buffers.  The function
 *  creates a new object whose properties are the identifies of the
 *  VBOs.  The new object also has a function, render(), that can be called to
 *  render the object, using all the data from the buffers.  That object
 *  is returned as the value of the function.  (The second parameter,
 *  xtraTranslate, is there because this program was ported from a Java
 *  version where cylinders were created in a different position, with
 *  the base on the xy-plane instead of with their center at the origin.
 *  The xtraTranslate parameter is a 3-vector that is applied as a
 *  translation to the rendered object.  It is used to move the cylinders
 *  into the position expected by the code that was ported from Java.)
 */
function createModel(modelData, xtraTranslate) {
  let model = {};
  model.coordsBuffer = gl.createBuffer();
  model.normalBuffer = gl.createBuffer();
  model.indexBuffer = gl.createBuffer();
  model.count = modelData.indices.length;
  if (xtraTranslate)
    model.xtraTranslate = xtraTranslate;
  else
    model.xtraTranslate = null;
  gl.bindBuffer(gl.ARRAY_BUFFER, model.coordsBuffer);
  gl.bufferData(gl.ARRAY_BUFFER, modelData.vertexPositions, gl.STATIC_DRAW);
  gl.bindBuffer(gl.ARRAY_BUFFER, model.normalBuffer);
  gl.bufferData(gl.ARRAY_BUFFER, modelData.vertexNormals, gl.STATIC_DRAW);
  gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, model.indexBuffer);
  gl.bufferData(gl.ELEMENT_ARRAY_BUFFER, modelData.indices, gl.STATIC_DRAW);
  model.render = function () {  // This function will render the object.
    // Since the buffer from which we are taking the coordinates and normals
    // change each time an object is drawn, we have to use gl.vertexAttribPointer
    // to specify the location of the data. And to do that, we must first
    // bind the buffer that contains the data.  Similarly, we have to
    // bind this object's index buffer before calling gl.drawElements.
    gl.bindBuffer(gl.ARRAY_BUFFER, this.coordsBuffer);
    gl.vertexAttribPointer(a_coords_loc, 3, gl.FLOAT, false, 0, 0);
    gl.bindBuffer(gl.ARRAY_BUFFER, this.normalBuffer);
    gl.vertexAttribPointer(a_normal_loc, 3, gl.FLOAT, false, 0, 0);
    gl.uniform4fv(u_material.diffuseColor, currentColor);
    if (this.xtraTranslate) {
      pushMatrix();
      mat4.translate(modelview, modelview, this.xtraTranslate);
    }
    gl.uniformMatrix4fv(u_modelview, false, modelview);
    mat3.normalFromMat4(normalMatrix, modelview);
    gl.uniformMatrix3fv(u_normalMatrix, false, normalMatrix);
    gl.bindBuffer(gl.ELEMENT_ARRAY_BUFFER, this.indexBuffer);
    gl.drawElements(gl.TRIANGLES, this.count, gl.UNSIGNED_SHORT, 0);
    if (this.xtraTranslate) {
      popMatrix();
    }
  }
  return model;
}

function getAsteroidBeltPoints(radius, width, slices, density) {
  function getPoint(r, xOffset, zOffset) {
    let randNum = function () { return Math.random() * r * 2.0 - r; };
    let d = r * r + 1;
    let x, y, z;
    while (d > r * r) {
      x = randNum();
      y = randNum();
      z = randNum();
      d = x * x + y * y + z * z;
    }
    return [x + xOffset, y, z + zOffset];
  }
  const step = 360 / slices;
  let points = [];
  for (let s = 0; s < 360; s += step) {
    let theta = s * Math.PI / 180;
    let x = radius * Math.cos(theta);
    let z = radius * Math.sin(theta);
    for (let p = 0; p < density; p++) {
      points.push(getPoint(width, x, z));
    }
  }
  return points;
}


/* Creates a program for use in the WebGL context gl, and returns the
 * identifier for that program.  If an error occurs while compiling or
 * linking the program, an exception of type String is thrown.  The error
 * string contains the compilation or linking error.  If no error occurs,
 * the program identifier is the return value of the function.
 *    The second and third parameters are the id attributes for <script>
 * elementst that contain the source code for the vertex and fragment
 * shaders.
 */
function createProgram(gl, vertexShaderID, fragmentShaderID) {
  function getTextContent(elementID) {
    // This nested function retrieves the text content of an
    // element on the web page.  It is used here to get the shader
    // source code from the script elements that contain it.
    let element = document.getElementById(elementID);
    let node = element.firstChild;
    let str = "";
    while (node) {
      if (node.nodeType == 3) // this is a text node
        str += node.textContent;
      node = node.nextSibling;
    }
    return str;
  }

  let vertexShaderSource;
  let fragmentShaderSource;
  try {
    vertexShaderSource = getTextContent(vertexShaderID);
    fragmentShaderSource = getTextContent(fragmentShaderID);
  }
  catch (e) {
    throw "Error: Could not get shader source code from script elements.";
  }
  let vsh = gl.createShader(gl.VERTEX_SHADER);
  gl.shaderSource(vsh, vertexShaderSource);
  gl.compileShader(vsh);
  if (!gl.getShaderParameter(vsh, gl.COMPILE_STATUS)) {
    throw "Error in vertex shader:  " + gl.getShaderInfoLog(vsh);
  }
  let fsh = gl.createShader(gl.FRAGMENT_SHADER);
  gl.shaderSource(fsh, fragmentShaderSource);
  gl.compileShader(fsh);
  if (!gl.getShaderParameter(fsh, gl.COMPILE_STATUS)) {
    throw "Error in fragment shader:  " + gl.getShaderInfoLog(fsh);
  }
  let prog = gl.createProgram();
  gl.attachShader(prog, vsh);
  gl.attachShader(prog, fsh);
  gl.linkProgram(prog);
  if (!gl.getProgramParameter(prog, gl.LINK_STATUS)) {
    throw "Link error in program:  " + gl.getProgramInfoLog(prog);
  }
  return prog;
}


/* Initialize the WebGL context.  Called from init() */
function initGL() {
  let prog = createProgram(gl, "vshader-source", "fshader-source");
  gl.useProgram(prog);
  gl.enable(gl.DEPTH_TEST);

  /* Get attribute and uniform locations */

  a_coords_loc = gl.getAttribLocation(prog, "a_coords");
  a_normal_loc = gl.getAttribLocation(prog, "a_normal");
  gl.enableVertexAttribArray(a_coords_loc);
  gl.enableVertexAttribArray(a_normal_loc);

  u_modelview = gl.getUniformLocation(prog, "modelview");
  u_projection = gl.getUniformLocation(prog, "projection");
  u_normalMatrix = gl.getUniformLocation(prog, "normalMatrix");
  u_material = {
    diffuseColor: gl.getUniformLocation(prog, "material.diffuseColor"),
    specularColor: gl.getUniformLocation(prog, "material.specularColor"),
    emissiveColor: gl.getUniformLocation(prog, "material.emissiveColor"),
    specularExponent: gl.getUniformLocation(prog, "material.specularExponent"),
    image: gl.getUniformLocation(prog, "material.image")
  };
  u_lights = new Array(4);
  for (let i = 0; i < 4; i++) {
    u_lights[i] = {
      enabled: gl.getUniformLocation(prog, "lights[" + i + "].enabled"),
      position: gl.getUniformLocation(prog, "lights[" + i + "].position"),
      color: gl.getUniformLocation(prog, "lights[" + i + "].color")
    };
  }

  gl.uniform3f(u_material.specularColor, 0.1, 0.1, 0.1);  // specular properties don't change
  gl.uniform1f(u_material.specularExponent, 16);
  gl.uniform3f(u_material.emissiveColor, 0, 0, 0);  // default, will be changed temporarily for some objects


  for (let i = 1; i < 4; i++) { // set defaults for lights
    gl.uniform1i(u_lights[i].enabled, 0);
    gl.uniform4f(u_lights[i].position, 0, 0, 0, 0);
    gl.uniform3f(u_lights[i].color, 1, 1, 1);
  }

} // end initGL()



//--------------------------------- animation framework ------------------------------


let animating = false;

/*
This is where you control the animation by changing positions,
and rotations values as needed.
Trial and error works on the numbers. Graph paper design is more efficient.
*/

function frame() {
  if (animating) {
    frameNumber += 1;
    draw();
    requestAnimationFrame(frame);
  }
}

function setAnimating(run) {
  if (run != animating) {
    animating = run;
    if (animating)
      requestAnimationFrame(frame);
  }
}

//-------------------------------------------------------------------------

function setSunlight(enabled) {
  if (enabled) {
    sunlight = {
      enabled: 1,
      emissiveColor: [1, 0.9, 0],
    }
  } else {
    sunlight = {
      enabled: 0,
      emissiveColor: [0.1, 0.1, 0],
    }
  }
  draw();
}

function resizeWindow() {
  canvas.width = window.innerWidth;
  canvas.height = window.innerHeight;
  gl.viewport(0, 0, gl.canvas.width, gl.canvas.height);
}

/**
 * initialization function that will be called when the page has loaded
 */
function init() {
  try {
    canvas = document.getElementById("webglcanvas");
    gl = canvas.getContext("webgl") || canvas.getContext("experimental-webgl");
    if (!gl) {
      throw "Browser does not support WebGL";
    }
  }
  catch (e) {
    document.getElementById("message").innerHTML =
      "<p>Sorry, could not get a WebGL graphics context.</p>";
    return;
  }
  try {
    initGL();  // initialize the WebGL graphics context
  }
  catch (e) {
    document.getElementById("message").innerHTML =
      "<p>Sorry, could not initialize the WebGL graphics context:" + e + "</p>";
    return;
  }

  gl.canvas.width = window.innerWidth;
  gl.canvas.height = window.innerHeight;
  gl.viewport(0, 0, gl.canvas.width, gl.canvas.height);
  window.addEventListener('resize', resizeWindow, false);

  const viewDistance = 30;
  const viewLookAt = [0, 0.2, 1];

  animatedCheck.checked = false;
  document.getElementById("reset").onclick = function () {
    rotator.setView(viewDistance, viewLookAt);
    frameNumber = 0;
    animating = false;
    animatedCheck.checked = false;
    speedSlider.value = 0.5;
    scaleSlider.value = 0.3;
    draw();
  };

  sphere = createModel(uvSphere(1));
  innerBelt = getAsteroidBeltPoints(13, 1.2, 360, 20);

  // This controls the zoom and initial placement
  rotator = new TrackballRotator(canvas, function () {
    if (!animating)
      draw();
  }, viewDistance, viewLookAt);
  draw();
}

init();
