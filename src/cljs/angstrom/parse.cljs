(ns angstrom.parse
  (:require [clojure.string :as string]
            [angstrom.ajax :as ajax]))

(defn safe-op
  [op s]
  (let [n (op s)]
    (if-not (js/isNaN n)
      n)))

(def safe-int (partial safe-op js/parseInt))
(def safe-float (partial safe-op js/parseFloat))

(defn fetch
  [format id handler]
  (let [url (str "http://www.rcsb.org/pdb/files/" id "." (name format))]
    (.log js/console "Fetching" url)
    (ajax/GET url handler)))

(defn atom-line
  [s]
  (= "ATOM" (subs s 0 4)))

(defn terminator-line
  [s]
  (= "TER" (subs s 0 3)))

(defn parse-pdb-atom
  [line]
  {:number (safe-int (subs line 6 11))
   :label (string/trim (subs line 12 16))
   :alternate-location (string/trim (subs line 16 17))
   :residue (string/trim (subs line 17 20))
   :chain (string/trim (subs line 21 22))
   :sequence (string/trim (subs line 22 26))
   :insertion (safe-int (subs line 26 27))
   :x (safe-float (subs line 30 38))
   :y (safe-float (subs line 38 46))
   :z (safe-float (subs line 46 54))
   :occupancy (safe-float (subs line 54 60))
   :temperature (safe-float (subs line 60 66))
   :segment (string/trim (subs line 72 76))
   :element (keyword (string/trim (subs line 76 78)))
   :charge (string/trim (subs line 78 80))})

(defn pdb
  [s]
  (let [lines (string/split s #"\n")
        segments (take-nth 2 (partition-by terminator-line lines))]
    (remove
     empty?
     (map 
      (fn [segment] 
        (map parse-pdb-atom (filter atom-line segment)))
      segments))))

(defn parse-sdf-atom
  [n line]
  {:number (inc n)
   :element (keyword (string/trim (subs line 30 32)))
   :x (safe-float (subs line 0 10))
   :y (safe-float (subs line 10 20))
   :z (safe-float (subs line 20 30))})

(defn sdf
  [s]
  (let [lines (drop 3 (string/split s #"\n"))
        header (first lines)
        data (rest lines)
        atom-count (safe-int (subs header 0 3))
        atom-data (take atom-count data)]
    (list (map-indexed parse-sdf-atom atom-data))))

(def parse-format
  {:pdb pdb
   :sdf sdf})

(defn fetch-atoms
  [format id handler]
  (fetch 
   format id 
   (fn [raw] 
     (let [parse (get parse-format (keyword format))
           atoms (parse raw)]
       (handler atoms)))))
