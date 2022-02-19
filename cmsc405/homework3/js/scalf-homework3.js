const WIDTH = 800;
const HEIGHT = 500;

const colorToHex = {
  WHITE: new THREE.Color(0xFFFFFF),
  RED: new THREE.Color(0xFF0000),
  GREEN: new THREE.Color(0x00FF00),
  BLUE: new THREE.Color(0x0000FF),
  YELLOW: new THREE.Color(0xFFFF00),
  BLACK: new THREE.Color(0x000000)
}

const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera(75, WIDTH / HEIGHT, 0.1, 1000);
const renderer = new THREE.WebGLRenderer();
const objects = new THREE.Object3D();
const light1 = new THREE.PointLight(0xFFFFFF, 2, 50, 2);
const light2 = new THREE.PointLight(0xFFFFFF, 2, 50, 2);

function rotateWithPI(multiplier) {
  return (Math.round(Math.PI * 1000 * multiplier) / 1000);
}

initializeView();
buildScene();
renderScene();

function initializeView() {
  renderer.setSize(WIDTH, HEIGHT);
  renderer.setClearColor(0x999999)
  document.getElementById("content").appendChild(renderer.domElement);
  window.addEventListener('resize', () => {
    camera.updateProjectionMatrix();
  });
  camera.position.set(0, 0.2, 1);
  controls = new THREE.OrbitControls(camera, renderer.domElement);
}

function buildScene() {
  addObjectsToScene();
  addLightsToScene();
}

function addObjectsToScene() {
  objects.add(getGrass());
  objects.add(getHouse());
  objects.add(getTree());
  objects.add(getJungleGym());
  // objects.rotation.y = rotateWithPI(1);
  scene.add(objects);
}

function getGrass() {
  const geometry = new THREE.PlaneGeometry();
  const material = new THREE.MeshLambertMaterial({
    color: 0x00450a,
    side: THREE.DoubleSide
  });
  const grassMesh = new THREE.Mesh(geometry, material);
  grassMesh.rotation.x = rotateWithPI(-0.5);
  return grassMesh;
}

function getHouse() {
  const house = new THREE.Object3D();
  house.add(getHouseWalls());
  house.add(getHouseRoof());
  house.position.y = 0.1001;
  house.position.x = -0.25;
  house.position.z = -0.25;
  house.scale.set(0.2, 0.2, 0.2);
  return house;
}

function getHouseWalls() {
  const wallsGeo = new THREE.BoxGeometry(1, 1, 1);
  const wallMaterial = new THREE.MeshLambertMaterial({ color: 0x614121 });
  return new THREE.Mesh(wallsGeo, wallMaterial);
}

function getHouseRoof() {
  const roofGeo = new THREE.CylinderGeometry(0, 1, 1, 4, 1);
  const roofMaterial = new THREE.MeshLambertMaterial({ color: 0x2b2723 })
  const roof = new THREE.Mesh(roofGeo, roofMaterial);
  roof.rotation.y = rotateWithPI(0.25);
  roof.position.y = 0.8;
  return roof;
}

function getTree() {
  const tree = new THREE.Object3D();
  tree.add(getTreeTrunk());
  tree.add(getTreeTop());
  tree.position.y = 0.1255;
  tree.position.x = 0.25;
  return tree;
}

function getTreeTrunk() {
  const trunkGeo = new THREE.CylinderGeometry(0.03, 0.03, 0.25, 20);
  const trunkMaterial = new THREE.MeshLambertMaterial({ color: 0x261402 });
  return new THREE.Mesh(trunkGeo, trunkMaterial);
}

function getTreeTop() {
  const treeTopGeo = new THREE.ConeGeometry(0.15, 0.4, 20);
  const treeTopMaterial = new THREE.MeshLambertMaterial({ color: 0x00340b });
  const treeTop = new THREE.Mesh(treeTopGeo, treeTopMaterial);
  treeTop.position.y = 0.23;
  return treeTop;
}

function getJungleGym() {
  const gymGeo = buildGymGeometry();
  const gymMaterial = new THREE.MeshLambertMaterial({
    color: 0xFFFF00,
    wireframe: true
  });
  const gym = new THREE.Mesh(gymGeo, gymMaterial);
  gym.scale.set(0.01, 0.005, 0.01);
  gym.rotation.x = rotateWithPI(1);
  gym.position.set(-0.1, 0.044, 0.25)
  return gym;
}

function buildGymGeometry() {
  // From THREE.js documentation
  // https://threejs.org/docs/#api/en/geometries/LatheGeometry
  const points = [];
  for (let i = 0; i < 10; i++) {
    points.push(new THREE.Vector2(Math.sin(i * 0.135) * 10 + 5, (i - 5) * 2));
  }
  return new THREE.LatheGeometry(points);
}

function addLightsToScene() {
  addAmbientLights();
  addLight1();
  addLight2();
}

function addAmbientLights() {
  const ambientLight = new THREE.AmbientLight(0xFFFFFF, 0.2);
  scene.add(ambientLight);
}

function addLight1() {
  light1.position.set(5, 3, -5);
  scene.add(light1);
}

function addLight2() {
  light2.position.set(-5, 3, 5);
  scene.add(light2);
}

function renderScene() {
  requestAnimationFrame(renderScene);
  update()
  renderer.render(scene, camera);
}

function update() {
  checkLight1();
  checkLight2();
  rotateScene();
}

function checkLight1() {
  checkLightSwitch(light1, 'switch1');
  checkLightColor(light1, 'color1');
  checkLightIntensity(light1, 'intensity1');
}

function checkLight2() {
  checkLightSwitch(light2, 'switch2');
  checkLightColor(light2, 'color2');
  checkLightIntensity(light2, 'intensity2');
}

function checkLightSwitch(light, switchID) {
  const lightSwitch = document.getElementById(switchID);
  if (!lightSwitch.checked) {
    scene.remove(light);
  } else {
    scene.add(light);
  }
}

function checkLightColor(light, colorID) {
  const lightColor = document.getElementById(colorID);
  light.color = colorToHex[lightColor.value];
}

function checkLightIntensity(light, intensityID) {
  const intensity = document.getElementById(intensityID);
  light.intensity = intensity.value;
}

function rotateScene() {
  objects.rotation.y += rotateWithPI(0.001);
}
