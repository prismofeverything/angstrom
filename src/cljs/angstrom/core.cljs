(ns angstrom.core
  (:require [angstrom.connect :as connect]
            [angstrom.parse :as parse]
            [angstrom.atomic :as atomic]))

(def world (atom {}))

(def point-position (js/THREE.Vector3. -250 250 150))

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
    (.copy (.-position point) point-position)
    (.set (.-position (.-target point)) 0 0 0)
    {:ambient ambient
     :point point}))

(defn init-molecule
  [scene]
  (parse/fetch-atoms 
   :pdb "2dgc" 
   (fn [atoms]
     (let [molecule (atomic/molecular-geometry atoms)]
       (.log js/console molecule)
       (.add scene molecule)
       (swap! world assoc :molecule molecule)))))

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
    (doseq [[key light] (seq lights)]
      (.add scene light))
    (init-molecule scene)
    {:clock (js/THREE.Clock.)
     :mouse (js/THREE.Vector2. 0 0)
     :scene scene
     :camera camera
     :renderer renderer
     :viewport viewport
     :controls controls
     :lights lights
     :time (.now js/Date)}))

(defn update
  [state]
  (let [{:keys [lights time]} state]
    (.set
     (.-position (:point lights))
     (* (.-x point-position) (js/Math.cos (* time 0.2)))
     (* (.-y point-position) (js/Math.sin (* time 0.2)))
     (.-z point-position))
    state))

(defn render
  [state]
  (let [state (update state)
        {:keys [time controls renderer scene camera]} state]
    (.update controls)
    (.render renderer scene camera)
    state))

(defn update-time
  [state]
  (let [delta (.getDelta (:clock state))]
    (update-in state [:time] #(+ delta %))))

(defn animate
  []
  (.requestAnimationFrame js/window animate)
  (let [state (update-time @world)
        state (render state)]
    (swap! world merge state)))

(set!
 (.-onload js/window)
 (fn []
   (let [state (init-scene)]
     (swap! world merge state)
     (animate))))

(connect/connect)
