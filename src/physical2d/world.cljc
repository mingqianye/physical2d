(ns physical2d.world
  (:require [physical2d.math-utils :as math]))

;;;;;; updates the velocity for all bodies ;;;;;;

(defn compute-new-bodies [bodies]
  "given a tuple of bodies e.g. [body1 body2], return a tuple of new bodies [body1-new body2-new] due to collision"
  (let [[body1 body2] bodies]
    [body1 body2]))

(defn combine-velocities [bodies]
  (-> (first bodies)
      (assoc :velocity (apply math/sum (map :velocity bodies)))))

(defn reduce-new-bodies [bodies]
  "Combine the bodies with the same body-id: sum their velocities"
  (->> bodies
    (group-by :body-id)
    (vals)
    (map combine-velocities)
  )
)
    
  

(defn update-velocity-for-all-bodies [bodies]
  "Given a collection of bodies, compute the new bodies after collision"
  (->> bodies
    (math/combos-of-2)
    (mapcat compute-new-bodies)
    (reduce-new-bodies)))

(defn update-collided-bodies [world]
  (update world :bodies #(update-velocity-for-all-bodies %)))


;;;;;;; updates locations for all bodies ;;;;;;;;

(defn update-body-location [body time-spent]
  "Given a body and the unit of time passed, compute the its new location"
  (let [velocity (get body :velocity)]
    (update body :location #(math/sum % velocity))))

(defn update-location-for-all-bodies [world]
  (let [update-body (fn [body] (update-body-location body (:unit-time world)))]
    (update world :bodies #(mapv update-body %))))

;;;;;;; main functions ;;;;;;;

(def next-frame-func
  (comp update-location-for-all-bodies 
        update-collided-bodies))

(defn next-frame [world]
  (next-frame-func world))
