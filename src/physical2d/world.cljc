(ns physical2d.world
  (:require [physical2d.math-utils :as math]))


(defn location-change [body time-spent]
  (let [velocity (get body :velocity)]
    (update body :location #(math/v+ % velocity))))

(defn update-body-locations [world]
  (let [update-body (fn [body] (location-change body (:unit-time world)))]
    (update world :bodies #(mapv update-body %))))

(def next-frame-func
  (comp update-body-locations))

(defn next-frame [world]
  (next-frame-func world))
