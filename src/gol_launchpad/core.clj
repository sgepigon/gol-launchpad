(ns gol-launchpad.core
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as sgen]
            [clojure.spec.test.alpha :as stest]
            [expound.alpha :as expound]
            [gol-launchpad.life :as life]
            [gol-launchpad.patterns :as patterns]
            [overtone.live :as overtone :refer :all]))

(event-debug-on)
(on-event [:midi :note-on]
          (fn [{:keys [note]}]
            (do (print note)
                (swap! #(toggle-cell % (midi-note->coords note)) board)))
          ::lp-handler)

(def board (atom patterns/barren))

(def colors {:green 120})

;; midi

(def lp (first (midi-connected-receivers)))

(s/fdef coords->midi-note
  :args (s/cat :coords ::life/coords)
  :ret nat-int?)
(defn- coords->midi-note
  "Translate `coords` to its place on the Launchpad."
  [[row col :as coords]]
  (+ (* 16 row) col))

(s/fdef midi-note->coords
  :args (s/cat :midi-note nat-int?)
  :ret ::life/coords)
(defn- midi-note->coords
  [midi-note]
  (apply (juxt quot rem) [midi-note 16]))

(defn- toggle-on
  [[row col :as coords]]
  (midi-note-on lp (coords->midi-note coords) (:green colors)))

(defn- toggle-off
  [[row col :as coords]]
  (midi-note-off lp (coords->midi-note coords)))

(defn- toggle-cell
  [board [row col :as coords]]
  (if (life/live? (life/cell board coords))
    (toggle-on coords)
    (toggle-off coords)))

(defn- print-board
  "Print `board` on the Launchpad."
  [board]
  (doseq [coords life/coordinates] (toggle-cell board coords)))

(defn main-loop
  "TODO"
  [m]
  (let [beat (m)]
    (do (print-board @board)
        (swap! board life/next)
        (apply-by (m (inc beat)) #'main-loop [m]))))




(comment


  (reset! board [])

  (main-loop (metronome 120))
  (stop)

  @board


  (doseq [board (take 5 (iterate life/next patterns/blinker))] (print-board board))

  (print-board patterns/barren)

  (stop)

  (set! s/*explain-out* (expound/custom-printer {:show-valid-values? true
                                                 :print-specs? false
                                                 :theme :figwheel-theme}))
  )
