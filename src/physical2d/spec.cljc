(ns physical2d.spec
  (:require #?(:clj [clojure.spec.alpha :as s]
               :cljs [cljs.spec.alpha :as s])
            [clojure.spec.gen.alpha :as gen]))

;;;;;;; common ;;;;;;;
(s/def ::fraction (s/and number? #(<= 0 % 1)))
(s/def ::non-negative (s/and number? (complement neg?)))
(s/def ::2d-vector (s/tuple number? number?))
(s/def ::location ::2d-vector)

;;;;;;; shape ;;;;;;;

(s/def ::shape-type #{:circle})
;(s/def ::vertices (s/and (s/+ ::2d-vector) #(<= 2 (count %) 8)))
;(s/def ::polygon  (s/keys :req-un [::shape-type ::vertices]))
(s/def ::radius (s/and number? pos?))
(s/def ::circle (s/keys :req-un [::shape-type ::radius ::location]))
(s/def ::shape ::circle)

;;;;;;; fixture ;;;;;;;
(s/def ::restitution ::fraction)
(s/def ::friction ::fraction)
(s/def ::density ::non-negative)
(s/def ::fixture (s/keys :req-un [::restitution
                                  ::friction
                                  ::density
                                  ::shape
                                  ]))

;;;;;;; body ;;;;;;;
(s/def ::angle number?)
(s/def ::angular_velocity number?)
(s/def ::rotational_inertia number?)
(s/def ::mass (s/and number? ::non-negative))
(s/def ::velocity ::2d-vector)
(s/def ::acceleration ::2d-vector)
(s/def ::fixtures (s/coll-of ::fixture :count 1))
(s/def ::body_type #{:dynamic :static :kinematic})
(s/def ::body (s/keys :req-un [::location 
                               ::velocity 
                               ::acceleration 
                               ::angle 
                               ::angular_velocity 
                               ::rotational_inertia 
                               ::mass 
                               ::fixtures 
                               ::body_type]))

;;;;;;; world ;;;;;;;
(s/def ::origin ::location)
(s/def ::gravity ::2d-vector)
(s/def ::bodies (s/coll-of ::body :count 1))
(s/def ::world (s/keys :req-un [::origin ::gravity ::bodies]))

(defn gen-world []
  (gen/generate (s/gen ::world)))

(defn sanity-check []
  (s/valid? ::world (gen-world)))
