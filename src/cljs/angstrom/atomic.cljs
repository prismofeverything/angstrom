(ns angstrom.atomic)

(def default-radius 1.0)

(defn object
  [m]
  (let [obj (js/Object.)]
    (doseq [[k v] m]
      (aset obj (name k) v))
    obj))

(def elements
  (let [all {:H {:name "Hydrogen"
                 :number 1
                 :color 0xffffff
                 :radius 0.23}

             :Li {:name "Lithium"
                  :number 3
                  :color 0xb22222
                  :radius default-radius}

             :Be {:name "Beryllium"
                  :number 4
                  :radius default-radius}

             :B {:name "Boron"
                 :number 5
                 :color 0x00ff00
                 :radius 0.83}

             :C {:name "Carbon"
                 :number 6
                 :color 0xc8c8c8
                 :radius 0.68}

             :N {:name "Nitrogen"
                 :number 7
                 :color 0x8f8fff
                 :radius 0.68}

             :O {:name "Oxygen"
                 :number 8
                 :color 0xf00000
                 :radius 0.68}

             :F {:name "Fluorine"
                 :number 9
                 :color 0xdaa520
                 :radius 0.64}

             :Mg {:name "Magnesium"
                  :number 12
                  :radius default-radius}

             :Al {:name "Aluminium"
                  :number 13
                  :color 0x808090
                  :radius default-radius}

             :Si {:name "Silicon"
                  :number 14
                  :color 0xdaa520
                  :radius 1.20}

             :P {:name "Phosphorus"
                 :number 15
                 :color 0xffa500
                 :radius 1.05}

             :S {:name "Sulfur"
                 :number 16
                 :color 0xffc832
                 :radius 1.02}

             :Cl {:name "Chlorine"
                  :number 17
                  :color 0x00ff00
                  :radius 0.99}

             :Ar {:name "Argon"
                  :number 18
                  :radius default-radius}

             :V {:name "Vanadium"
                 :number 23
                 :radius default-radius}

             :Cr {:name "Chromium"
                  :number 24
                  :color 0x808090
                  :radius default-radius}

             :Mn {:name "Manganese"
                  :number 25
                  :color 0x808090
                  :radius default-radius}

             :Co {:name "Cobalt"
                  :number 27
                  :radius default-radius}

             :Cu {:name "Copper"
                  :number 29
                  :color 0xa52a2a
                  :radius default-radius}

             :Zn {:name "Zinc"
                  :number 30
                  :color 0xa52a2a
                  :radius default-radius}

             :Ga {:name "Gallium"
                  :number 31
                  :radius default-radius}

             :As {:name "Arsenic"
                  :number 33
                  :radius 1.21}

             :Se {:name "Selenium"
                  :number 34
                  :radius 1.22}

             :Br {:name "Bromine"
                  :number 35
                  :color 0xa52a2a
                  :radius 1.21}

             :Kr {:name "Krypton"
                  :number 36
                  :radius default-radius}

             :Rb {:name "Rubidium"
                  :number 37
                  :radius default-radius}

             :Sr {:name "Strontium"
                  :number 38
                  :radius default-radius}

             :Y {:name "Yttrium"
                 :number 39
                 :radius default-radius}

             :Mo {:name "Molybdenum"
                  :number 42
                  :radius default-radius}

             :Ag {:name "Silver"
                  :number 47
                  :color 0x808090
                  :radius default-radius}

             :Cd {:name "Cadmium"
                  :number 48
                  :radius default-radius}

             :In {:name "Indium"
                  :number 49
                  :radius default-radius}

             :Sb {:name "Antimony"
                  :number 51
                  :radius default-radius}

             :Te {:name "Tellurium"
                  :number 52
                  :radius 1.47}

             :I {:name "Iodine"
                 :number 53
                 :color 0xa020f0
                 :radius 1.40}

             :Xe {:name "Xenon"
                  :number 54
                  :radius default-radius}

             :Cs {:name "Caesium"
                  :number 55
                  :radius default-radius}

             :Ba {:name "Barium"
                  :number 56
                  :color 0xffa500
                  :radius default-radius}

             :La {:name "Lanthanum"
                  :number 57
                  :radius default-radius}

             :Ce {:name "Cerium"
                  :number 58
                  :radius default-radius}

             :Sm {:name "Samarium"
                  :number 62
                  :radius default-radius}

             :Eu {:name "Europium"
                  :number 63
                  :radius default-radius}

             :Gd {:name "Gadolinium"
                  :number 64
                  :radius default-radius}

             :Tb {:name "Terbium"
                  :number 65
                  :radius default-radius}

             :Ho {:name "Holmium"
                  :number 67
                  :radius default-radius}

             :Yb {:name "Ytterbium"
                  :number 70
                  :radius default-radius}

             :Lu {:name "Lutetium"
                  :number 71
                  :radius default-radius}

             :W {:name "Tungsten"
                 :number 74
                 :radius default-radius}

             :Re {:name "Rhenium"
                  :number 75
                  :radius default-radius}

             :Os {:name "Osmium"
                  :number 76
                  :radius default-radius}

             :Ir {:name "Iridium"
                  :number 77
                  :radius default-radius}

             :Pt {:name "Platinum"
                  :number 78
                  :radius default-radius}

             :Au {:name "Gold"
                  :number 79
                  :color 0xdaa520
                  :radius default-radius}

             :Hg {:name "Mercury"
                  :number 80
                  :radius default-radius}

             :Tl {:name "Thallium"
                  :number 81
                  :radius default-radius}

             :Pb {:name "Lead"
                  :number 82
                  :radius default-radius}

             :U {:name "Uranium"
                 :number 92
                 :radius default-radius}}]
    (into 
     {}
     (map 
      (fn [[sym element]]
        (let [material (js/THREE.MeshPhongMaterial. (object {:color (or (:color element) 0xff1493)}))
              geometry (js/THREE.SphereGeometry. (:radius element) 16 16)]
          [sym (assoc element 
                 :material material
                 :geometry geometry)]))
      all))))

(defn atomic-sphere
  [atom]
  (let [element (get elements (:element atom))
        sphere (js/THREE.Mesh. (:geometry element) (:material element))]
    (.set (.-position sphere) (:x atom) (:y atom) (:z atom))
    sphere))

(defn molecular-geometry
  [atoms]
  (reduce
   (fn [molecule segment]
     (let [node (reduce
                 (fn [node atom]
                   (.add node (atomic-sphere atom))
                   node)
                 (js/THREE.Object3D.) segment)]
       (.add molecule node)
       molecule))
   (js/THREE.Object3D.) atoms))
