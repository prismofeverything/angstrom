(ns angstrom.core
  (:require [angstrom.connect :as connect]
            [angstrom.parse :as parse]
            [angstrom.atomic :as atomic]))

(defrecord Rect [x y width height])

(defn rrange
  [min max]
  (+ min (rand-int (- max min))))

(defn rprop
  [min max prop]
  (rrange (get min prop) (get max prop)))

(defn random-rect
  ([min max] (random-rect min max 0))
  ([min max id]
     (let [rrect (partial rprop min max)]
       (Rect. (rrect :x) (rrect :y) (rrect :width) (rrect :height)))))

(defn rect-pile
  [number width height]
  (let [min (Rect. 0 0 10 10)
        max (Rect. 500 500 width height)]
    (map (partial random-rect min max) (range number))))

(defn rect-div!
  [rect]
  (let [div (.createElement js/document "div")
        style (.-style div)]
    (set! (.-top style) (str (:x rect) "px"))
    (set! (.-left style) (str (:y rect) "px"))
    (set! (.-width style) (str (:width rect) "px"))
    (set! (.-height style) (str (:height rect) "px"))
    (set! (.-backgroundColor style) (str "rgb(" (rand-int 255) "," (rand-int 255) "," (rand-int 255) ")"))
    (set! (.-className div) "rect")
    (.appendChild (.-body js/document) div)))

(defn scatter-rects
  []
  (let [pile (rect-pile 500 100 100)]
    (doseq [rect pile]
      (rect-div! rect))))

(connect/connect)

(scatter-rects)
