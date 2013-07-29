var renderer, camera;
var scene, element;
var ambient, point;
var aspectRatio, windowHalf;
var mouse, time;

var controls;
var clock;

var ground, groundGeometry, groundMaterial;
var atoms, pdb, spheres;
var molecule;

function initScene() {
  clock = new THREE.Clock();
  mouse = new THREE.Vector2(0, 0);

  windowHalf = new THREE.Vector2(window.innerWidth / 2, window.innerHeight / 2);
  aspectRatio = window.innerWidth / window.innerHeight;
  
  scene = new THREE.Scene();  

  camera = new THREE.PerspectiveCamera(45, aspectRatio, 1, 10000);
  camera.position.z = 400;
  camera.lookAt(scene.position);

  // Initialize the renderer
  renderer = new THREE.WebGLRenderer();
  renderer.setSize(window.innerWidth, window.innerHeight);
  renderer.shadowMapEnabled = true;
  renderer.shadowMapType = THREE.PCFShadowMap;

  element = document.getElementById('viewport');
  element.appendChild(renderer.domElement);

  controls = new THREE.OrbitControls(camera);
  
  time = Date.now();
}

function initLights(){

  ambient = new THREE.AmbientLight(0x001111);
  scene.add(ambient);

  pointPosition = new THREE.Vector3(-250, 250, 150);
  point = new THREE.SpotLight( 0xffffff, 1, 0, Math.PI, 1 );
  point.position.set(pointPosition.x, pointPosition.y, pointPosition.z);
  point.target.position.set( 0, 0, 0 );

  // Set shadow parameters for the spotlight.
  point.castShadow = true;
  point.shadowCameraNear = 50;
  point.shadowCameraFar = 1000;
  point.shadowCameraFov = 50;
  point.shadowBias = 0.0001;
  point.shadowDarkness = 0.5;

  // Larger shadow map size means better looking shadows but impacts performance and texture memory usage.
  point.shadowMapWidth = 1024;
  point.shadowMapHeight = 1024;

  scene.add(point);
}

function initGeometry(){
  groundMaterial = new THREE.MeshBasicMaterial({
    map: THREE.ImageUtils.loadTexture("img/birth.jpg")
  });
  
  groundGeometry = new THREE.PlaneGeometry( 1028, 1028, 4, 4 );
  ground = new THREE.Mesh(groundGeometry, groundMaterial);
  
  // rotate the ground plane so it's horizontal
  ground.rotation.x = Math.PI * 1.5;
  ground.position.set(15, -50, 200);
  ground.castShadow = false;
  ground.receiveShadow = true;

  scene.add(ground);
}

function initMolecule() {
  PDB.fetch('1e79', function(response) {
  // PDB.fetch('2dgc', function(response) {
    pdb = response; 
    atoms = PDB.parse(pdb)
    molecule = atomic.atomicGeometry(atoms);
    scene.add(molecule);
    console.log(atomic.listElements(atoms));
  });
}

function init(){
  document.addEventListener('keydown', onKeyDown, false);
  document.addEventListener('keyup', onKeyUp, false);
  document.addEventListener('mousedown', onMouseDown, false);
  document.addEventListener('mousemove', onMouseMove, false);

  window.addEventListener('resize', onResize, false);

  initScene();
  initLights();
  // initGeometry();
  initMolecule();
}

function onResize() {
  windowHalf = new THREE.Vector2(window.innerWidth / 2, window.innerHeight / 2);
  aspectRatio = window.innerWidth / window.innerHeight;
  camera.aspect = aspectRatio;
  camera.updateProjectionMatrix();
  renderer.setSize(window.innerWidth, window.innerHeight);
}

function onMouseMove(event) {
  mouse.set( (event.clientX / window.innerWidth - 0.5) * 2, (event.clientY / window.innerHeight - 0.5) * 2);
}

function onMouseDown(event) {
}

function onKeyDown(event) {
}

function onKeyUp(event) {
}

function animate() {
  requestAnimationFrame(animate);
  render();
}

function render() {
  var delta = clock.getDelta();
  time += delta;

  point.position.set(pointPosition.x * Math.cos(time * 0.2), pointPosition.y * Math.sin(time * 0.2), pointPosition.z);

  controls.update();
  renderer.render(scene, camera);
}

window.onload = function() {
  init();
  animate();
}
