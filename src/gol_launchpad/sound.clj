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

(def ^:private lookup-table
  (let [root 50]
    (vector
      (vec (make-row root))
      (vec (make-row (p4 root)))
      (vec (make-row (p4 (p4 root))))
      (vec (make-row (p4 (p4 (p4 root)))))
      (vec (make-row (p4 (p4 (p4 (p4 root))))))
      (vec (make-row (p4 (p4 (p4 (p4 (p4 root)))))))
      (vec (make-row (p4 (p4 (p4 (p4 (p4 (p4 root))))))))
      (vec (make-row (p4 (p4 (p4 (p4 (p4 (p4 (p4 root))))))))))))

(defsynth s [freq 440]
          (let [factor 4
                mul 20
                env (perc)
                sig (* 0.5 (* (env-gen env :action FREE) (sin-osc (+ freq (* mul (sin-osc (* freq factor)))))))]
            (out 0 [sig sig])))

(defn play
  [board coords]
  (when (life/live? (life/cell board coords))
    (s (get-in lookup-table coords))))

(defn play-board
  [board]
  (doseq [coords life/coordinates] (play board coords)))
