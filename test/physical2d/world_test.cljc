(ns physical2d.world-test
  (:require [clojure.test :refer :all]
            [physical2d.spec :refer :all]
            [physical2d.world :refer :all]
            [physical2d.math-utils :refer :all]
            ))

(deftest next-frame-test
  (testing "next frame consumes a world and returns a world"
    (is (true? (validate-world (next-frame (generate-world)))))))

(deftest update-body-location-test
  (testing "body location is updated by velocity*time"
    (let [old-body     (generate-body)
          old-velocity (:velocity old-body)
          old-position (:location old-body)
          new-body     (update-body-location old-body 1)
          new-location (:location new-body)]
      (is (= (sum old-position old-velocity) new-location))
      )))


(deftest combine-velocities-test
  (testing "test we can combine velocities"
    (let [bodies [{:blah :a :velocity [1 2]} 
                  {:blah :a :velocity [2 3]}
                  {:blah :a :velocity [4 3]}
         ]]  
      (is (= {:blah :a :velocity [7 8]} (combine-velocities bodies)))
      )))

(deftest reduce-new-bodies-test
  (testing "test new bodies can be group by body-id and sum their velocities"
    (let [bodies [{:body-id 1 :velocity [1 2]}
                  {:body-id 2 :velocity [3 4]}
                  {:body-id 1 :velocity [5 6]}
                  {:body-id 3 :velocity [7 8]}]]
      (is (= [{:body-id 1 :velocity [6 8]}
              {:body-id 2 :velocity [3 4]}
              {:body-id 3 :velocity [7 8]}] (reduce-new-bodies bodies))))))
