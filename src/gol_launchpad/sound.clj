(ns gol-launchpad.sound
  (:require [overtone.live :refer :all]
            [gol-launchpad.life :as life]))

(defn- p4
  [x]
  (float (* x 4/3)))

(defn- p5
  [x]
  (float (* x 3/2)))

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
  (let [tonic (sin-osc freq)
        h1 (* 1/2 (sin-osc (* freq 2)))
        h2 (* 1/4 (sin-osc (* freq 4)))
        h3 (* 1/8 (sin-osc (* freq 8/3)))
        syn (+ tonic h1 h2 h3)
        sig (lpf (* 0.3
                    (* (env-gen (perc) :action FREE) syn))
                 1600)]
    (out 0 [sig (delay-c sig 0.01 0.01)])))

;; (defsynth s [freq 440]
;;           (let [factor 2
;;                 mul 20
;;                 env (perc)
;;                 sig (* 0.3 (* (env-gen env :action FREE) (sin-osc (+ freq (* mul (sin-osc (* freq factor)))))))]
;;             (out 0 [sig (delay-c sig 0.01 0.01)])))

(defn play
  [board coords]
  (when (life/live? (life/cell board coords))
    (s (get-in lookup-table coords))))

(defn play-board
  [board]
  (doseq [coords life/coordinates] (play board coords)))
