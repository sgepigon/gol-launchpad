(ns gol-launchpad.core
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as sgen]
            [clojure.spec.test.alpha :as stest]
            [expound.alpha :as expound]
            [gol-launchpad.life :as life]
            [gol-launchpad.patterns :as patterns]
            [overtone.live :as overtone :refer :all]))

(def colors {:green 120})

;; midi

(def lp (first (midi-connected-receivers)))

(s/fdef ->midi-note
  :args (s/cat :coords ::life/coords)
  :ret nat-int?)
(defn- ->midi-note
  "Translate `coords` to its place on the Launchpad."
  [[row col :as coords]]
  (+ (* 16 row) col))

(defn- toggle-on
  [[row col :as coords]]
  (midi-note-on lp (->midi-note coords) (:green colors)))

(defn- toggle-off
  [[row col :as coords]]
  (midi-note-off lp (->midi-note coords)))

(defn- print-cell
  [board [row col :as coords]]
  (if (life/live? (life/cell board coords))
    (toggle-on coords)
    (toggle-off coords)))

(defn- print-board
  "Print `board` on the Launchpad."
  [board]
  (for [i (range life/num-rows), j (range life/num-cols)]
    (print-cell board [i j])))

(defn main-loop
  "TODO"
  [m]
  (let [beat (m)]
    (do (println beat)
        (apply-by (m (inc beat)) #'main-loop [m]))))

(comment

  (set! s/*explain-out* (expound/custom-printer {:show-valid-values? true
                                                 :print-specs? false
                                                 :theme :figwheel-theme}))
  )
