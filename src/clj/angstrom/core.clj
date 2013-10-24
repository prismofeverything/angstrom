(ns angstrom.core
  (:require [clojure.java.io :as io]
            [ring.middleware.resource :as resource]))

(defn base-handler
  [request]
  {:status 200 :body (slurp (io/resource "public/index.html"))})

(defn init
  [])

(def handler
  (-> base-handler
      (resource/wrap-resource "public")))
