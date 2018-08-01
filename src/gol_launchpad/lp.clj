(ns gol-launchpad.lp
  (:require [clojure.spec.alpha :as s]
            [overtone.studio.midi :as midi]
            [gol-launchpad.life :as life]))

(def colors {:green 120})

(def ^:private lp (first (midi/midi-connected-receivers)))

(s/fdef coords->midi-note
        :args (s/cat :coords ::life/coords)
        :ret nat-int?)
(defn coords->midi-note
  "Translate `coords` to its place on the Launchpad."
  [[row col :as coords]]
  (+ (* 16 row) col))

(s/fdef midi-note->coords
        :args (s/cat :midi-note nat-int?)
        :ret ::life/coords)
(defn midi-note->coords
  [midi-note]
  (apply (juxt quot rem) [midi-note 16]))

(defn toggle-on
  [[row col :as coords]]
  (midi/midi-note-on lp (coords->midi-note coords) (:green colors)))

(defn toggle-off
  [[row col :as coords]]
  (midi/midi-note-off lp (coords->midi-note coords)))

(defn toggle-cell
  [board [row col :as coords]]
  (if (life/live? (life/cell board coords))
    (toggle-on coords)
    (toggle-off coords)))
