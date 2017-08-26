(ns physical2d.spec
  (:require #?(:clj [clojure.spec.alpha :as s]
               :cljs [cljs.spec.alpha :as s])))

(s/def ::non-negative (complement neg?))
(s/def ::2d-vector (s/and vector? #(= 2 (count %)) (s/cat :x number? :y number?)))

(s/def ::angle number?)
(s/def ::angular_velocity number?)
(s/def ::rotational_inertia number?)
(s/def ::mass (s/and number? ::non-negative))
(s/def ::location ::2d-vector)
(s/def ::velocity ::2d-vector)
(s/def ::acceleration ::2d-vector)
(s/def ::fixtures vector?)
(s/def ::body_type #{:dynamic :static :kinematic})
(s/def ::body (s/keys :req-un [::location ::velocity ::acceleration ::angle ::angular_velocity ::rotational_inertia ::mass ::fixtures ::body_type]))

(defn valid-body? [m]
  (s/explain ::body m)
  (s/valid? ::body m))
