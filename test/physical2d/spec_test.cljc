(ns physical2d.spec-test
  (:require [clojure.test :refer :all]
            [physical2d.spec :refer :all]))

(deftest a-test
  (testing "new world"
    (is (true? (valid-body? 
                 {:location [111 1] 
                  :velocity [111 1] 
                  :acceleration [111 1] 
                  :angle 3 
                  :angular_velocity -3
                  :rotational_inertia 3
                  :mass 1
                  :fixtures [3]
                  :body_type :dynamic
                  
                  })))))
