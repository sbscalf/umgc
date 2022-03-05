"use strict";

// Shaders
const vertexShaderSource = `
  precision mediump float;

  attribute vec2 a_position;
  varying vec4 color;

  uniform vec3 u_color;
  uniform mat4 u_matrix;

  void main() {
    gl_Position = u_matrix * vec4(a_position, 0, 1);
    color = vec4(u_color,1);
  }
`;
const fragmentShaderSource = `
  precision mediump float;

  varying vec4 color;

  void main() {
    gl_FragColor = color;
  }
`;

// Global Constants
const mat4 = glMatrix.mat4;
const THETA = 0.52359877559;

// Heart-Shape
const vertices = [
  0, -1,
  -.75, -.25,
  -.875, 0,
  -1, 0.25,
  -1, 0.5,
  -.875, .75,
  -.75, .875,
  -.5, 1,
  -.25, .875,
  -.125, .75,
  0, .5,
  .125, .75,
  .25, .875,
  .5, 1,
  .75, .875,
  .875, .75,
  1, .5,
  1, .25,
  .875, 0,
  .75, -.25
];
const smallHeart = vertices.map((x, index) => {
  return x / 3;
});

// Global Variables
let gl;
let prog;

let a_position_loc;
let a_position_buffer;
let u_color_loc;
let u_matrix_loc;
let matrix;

window.onload = init;
function init() {
  initContext();
  initGL();
  initMatrix();
  animate();
}

function initContext() {
  let canvas = document.getElementById('glCanvas');
  canvas.width = window.innerWidth;
  canvas.height = window.innerHeight;
  gl = canvas.getContext('webgl2');
  if (!gl) {
    throw new Error('WebGL 2.0 not supported');
  }
}

function initGL() {
  prog = createProgram(gl);
  gl.useProgram(prog);

  a_position_loc = gl.getAttribLocation(prog, "a_position");
  a_position_buffer = gl.createBuffer();
  gl.bindBuffer(gl.ARRAY_BUFFER, a_position_buffer);
  gl.vertexAttribPointer(a_position_loc, 2, gl.FLOAT, false, 0, 0);
  gl.enableVertexAttribArray(a_position_loc);
  gl.bufferData(gl.ARRAY_BUFFER, new Float32Array(smallHeart), gl.STATIC_DRAW);

  u_matrix_loc = gl.getUniformLocation(prog, "u_matrix");

  u_color_loc = gl.getUniformLocation(prog, "u_color");
  gl.uniform3fv(u_color_loc, [Math.random(), Math.random(), Math.random()]);
}

function createProgram(gl) {
  let vsh = gl.createShader(gl.VERTEX_SHADER);
  gl.shaderSource(vsh, vertexShaderSource);
  gl.compileShader(vsh);
  if (!gl.getShaderParameter(vsh, gl.COMPILE_STATUS)) {
    throw new Error("Error in vertex shader: " + gl.getShaderInfoLog(vsh));
  }
  let fsh = gl.createShader(gl.FRAGMENT_SHADER);
  gl.shaderSource(fsh, fragmentShaderSource);
  gl.compileShader(fsh);
  if (!gl.getShaderParameter(fsh, gl.COMPILE_STATUS)) {
    throw new Error("Error in fragment shader: " + gl.getShaderInfoLog(vsh));
  }
  let prog = gl.createProgram();
  gl.attachShader(prog, vsh);
  gl.attachShader(prog, fsh);
  gl.linkProgram(prog);
  if (!gl.getProgramParameter(prog, gl.LINK_STATUS)) {
    throw new Error("Link error in program: " + gl.getProgramInfoLog(prog));
  }
  return prog;
}

function initMatrix() {
  matrix = mat4.create();
  mat4.translate(matrix, matrix, [0.5, 0.5, 0]);
}

function animate() {
  requestAnimationFrame(animate);
  changeColor();
  mat4.rotateZ(matrix, matrix, Math.PI / 2 / 69);
  moveHeart();
  gl.uniformMatrix4fv(u_matrix_loc, false, matrix);
  gl.drawArrays(gl.TRIANGLE_FAN, 0, vertices.length);
}

function changeColor() {
  if (Math.random() < 0.02) {
    gl.uniform3fv(u_color_loc, [Math.random(), Math.random(), Math.random()]);
  }
}

let x = 0;
let y = 0;
let moveLeft = true;
let moveUp = true;
function moveHeart() {
  let limit = 0.0625;
  let step = 0.001;
  let move = 0.012;
  if (moveLeft === true) {
    if (x < -limit) {
      moveLeft = false;
      x = 0;
    }
    x -= step;
    mat4.translate(matrix, matrix, [-move, 0, 0]);
  } else {
    if (x > limit) {
      moveLeft = true;
      x = 0;
    }
    x += step;
    mat4.translate(matrix, matrix, [move, 0, 0]);
  }
  if (moveUp === true) {
    if (y < -limit) {
      moveUp = false;
      y = 0;
    }
    y -= step;
    mat4.translate(matrix, matrix, [0, -move, 0]);
  } else {
    if (y > limit) {
      moveUp = true;
      y = 0;
    }
    y += step;
    mat4.translate(matrix, matrix, [0, move, 0]);
  }
}
