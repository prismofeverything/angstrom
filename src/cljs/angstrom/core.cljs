(ns angstrom.core
  (:require [angstrom.connect :as connect]
            [angstrom.parse :as parse]
            [angstrom.atomic :as atomic]))

(def world (atom {}))

(defn window-size
  []
  [(.-innerWidth js/window) (.-innerHeight js/window)])

(defn aspect-ratio
  []
  (let [[width height] (window-size)]
    (/ width height)))

(defn init-camera
  [[x y z] look-at]
  (let [camera (js/THREE.PerspectiveCamera. 45 (aspect-ratio) 1 10000)]
    (.set (.-position camera) x y z)
    (.lookAt camera look-at)
    camera))

(defn init-renderer
  []
  (let [renderer (js/THREE.WebGLRenderer.)
        [width height] (window-size)]
    (.setSize renderer width height)
    (set! (.-shadowMapEnabled renderer) true)
    (set! (.-shadowMapType renderer) js/THREE.PCFShadowMap)
    renderer))

(defn init-viewport
  [renderer]
  (let [viewport (.getElementById js/document "viewport")]
    (.appendChild viewport (.-domElement renderer))
    viewport))

(defn init-lights
  []
  (let [ambient (js/THREE.AmbientLight. 0x0011111)
        point (js/THREE.SpotLight. 0xffffff 1 0 js/Math.PI 1)]
    (.set (.-position point) -250 250 150)
    (.set (.-position (.-target point)) 0 0 0)
    [ambient point]))

(defn init-molecule
  [scene]
  (parse/fetch-atoms 
   :pdb "2dgc" 
   (fn [atoms]
     (let [molecule (atomic/molecular-geometry atoms)]
       (.log js/console molecule)
       (.add scene molecule)))))

(defn on-key-down [event])
(defn on-key-up [event])
(defn on-mouse-down [event])
(defn on-mouse-move [event])

(defn on-resize
  []
  (let [[width height] (window-size)
        {:keys [camera renderer]} @world]
    (set! (.-aspect camera) (/ width height))
    (.updateProjectionMatrix camera)
    (.setSize renderer width height)))

(defn init-listeners
  []
  (.addEventListener js/document "keydown" on-key-down false)
  (.addEventListener js/document "keyup" on-key-up false)
  (.addEventListener js/document "mousedown" on-mouse-down false)
  (.addEventListener js/document "mousemove" on-mouse-move false)
  (.addEventListener js/window "resize" on-resize false))

(defn init-scene
  []
  (let [scene (js/THREE.Scene.)
        camera (init-camera [0 0 400] (.-position scene))
        renderer (init-renderer)
        viewport (init-viewport renderer)
        controls (js/THREE.OrbitControls. camera)
        lights (init-lights)]
    (init-listeners)
    (doseq [light lights]
      (.add scene light))
    (init-molecule scene)
    {:clock (js/THREE.Clock.)
     :mouse (js/THREE.Vector2. 0 0)
     :scene scene
     :camera camera
     :renderer renderer
     :viewport viewport
     :controls controls
     :time (.now js/Date)}))

(defn init
  []
  (let [scene (init-scene)]
    (swap! world merge scene)))

(defn render
  []
  (let [{:keys [clock time controls renderer scene camera]} @world
        delta (.getDelta clock)
        time (+ delta time)]
    (.update controls)
    (.render renderer scene camera)
    (swap! world assoc :time time)))

(defn animate
  []
  (.requestAnimationFrame js/window animate)
  (render))

(set! 
 (.-onload js/window) 
 (fn [] 
   (init)
   (animate)))

(connect/connect)
