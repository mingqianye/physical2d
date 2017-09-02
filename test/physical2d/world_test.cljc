(ns physical2d.world-test
  (:require [clojure.test :refer :all]
            [physical2d.spec :refer :all]
            [physical2d.world :refer :all]
            [physical2d.math-utils :refer :all]
            ))

(deftest next-frame-test
  (testing "next frame consumes a world and returns a world"
    (is (true? (validate-world (next-frame (generate-world)))))))

(deftest location-change-test
  (testing "body location is updated by velocity*time"
    (let [old-body     (generate-body)
          old-velocity (:velocity old-body)
          old-position (:location old-body)
          new-body     (location-change old-body 1)
          new-location (:location new-body)]
      (is (= (v+ old-position old-velocity) new-location))
      )))

