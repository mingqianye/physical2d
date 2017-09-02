(ns physical2d.world
  (:require [physical2d.math-utils :as math]))

;;;;;; updates the velocity for all bodies ;;;;;;

(defn compute-velocity-update [body1 body2]
  (let [body1-update {:body-id (:body-id body1) :velocity-delta [0 0]}
        body2-update {:body-id (:body-id body2) :velocity-delta [0 0]}]
    [body1-update body2-update]))

(defn get-velocity-updates [bodies]
  (let [paired-bodies (math/combos-of-2 bodies)
        velocity-update-for-pairs (fn [body-pair] (compute-velocity-update (first body-pair) (last body-pair)))]
    (mapcat velocity-update-for-pairs paired-bodies)))

(defn merge-velocity-updates [& updates]
  {:body-id (:body-id (first updates)) 
   ;:velocity-delta (math/sum (map :velocity-delta updates))}
   :velocity-delta (->> updates
                        (map :velocity-delta)
                        (reduce math/sum)
)}

)

(defn velocity-change [bodies]
  (let [all-velocity-updates (get-velocity-updates bodies)
        collapse-updates (->> all-velocity-updates 
                              (group-by :body-id))]
    bodies
))

(defn update-collided-bodies [world]
  (update world :bodies #(velocity-change %)))


;;;;;;; updates locations for all bodies ;;;;;;;;

(defn location-change [body time-spent]
  (let [velocity (get body :velocity)]
    (update body :location #(math/sum % velocity))))

(defn update-body-locations [world]
  (let [update-body (fn [body] (location-change body (:unit-time world)))]
    (update world :bodies #(mapv update-body %))))

;;;;;;; main functions ;;;;;;;

(def next-frame-func
  (comp update-body-locations 
        update-collided-bodies))

(defn next-frame [world]
  (next-frame-func world))
