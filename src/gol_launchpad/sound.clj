(ns gol-launchpad.sound
  (:require [overtone.live :refer :all]
            [gol-launchpad.life :as life]))

(defn- p4
  [x]
  (float (* x (/ 4 3))))

(defn- p5
  [x]
  (float (* x (/ 3 2))))

(defn- make-row
  [tonic]
  (take 8 (iterate p5 tonic)))

(def ^:private root 30)

(def ^:private lookup-table
  (vector
    (vec (make-row root))
    (vec (make-row (p4 root)))
    (vec (make-row (p4 (p4 root))))
    (vec (make-row (p4 (p4 (p4 root)))))
    (vec (make-row (p4 (p4 (p4 (p4 root))))))
    (vec (make-row (p4 (p4 (p4 (p4 (p4 root)))))))
    (vec (make-row (p4 (p4 (p4 (p4 (p4 (p4 root))))))))
    (vec (make-row (p4 (p4 (p4 (p4 (p4 (p4 (p4 root)))))))))))

(defn play
  [board coords]
  (when (life/live? (life/cell board coords))
    (demo (sin-osc (get-in lookup-table coords)))))

(defn play-board
  [board]
  (doseq [coords life/coordinates] (play board coords)))
