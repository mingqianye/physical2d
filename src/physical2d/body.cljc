(ns physical2d.body)

(def default-body
  {:location           [0 0]
   :velocity           [0 0]
   :acceleration       [0 0]
   :angle              0
   :angular_velocity   0
   :rotational_inertia 0
   :mass               1
   :fixtures           []
   :body_type          :dynamic
   }
  )

