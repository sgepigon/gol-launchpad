(ns gol-launchpad.core
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as sgen]
            [clojure.spec.test.alpha :as stest]
            [expound.alpha :as expound]
            [gol-launchpad.life :as life]
            [gol-launchpad.patterns :as patterns]
            [gol-launchpad.lp :as lp]
            [overtone.live :as overtone :refer :all]))

(def board (atom patterns/barren))

;; midi

(defn- print-board
  "Print `board` on the Launchpad."
  [board]
  (doseq [coords life/coordinates] (lp/toggle-cell board coords)))

(defn main-loop
  "TODO"
  [m]
  (let [beat (m)]
    (do (print-board @board)
        (swap! board life/next)
        (apply-by (m (inc beat)) #'main-loop [m]))))

(defn toggle-cell
  [board coords]
  (update-in board coords #(if (life/live? %)
                             life/dead
                             life/live)))

(on-event [:midi :note-on]
          (fn [{:keys [note]}]
            (swap! board #(toggle-cell % (lp/midi-note->coords note))))
          ::lp-handler)

(comment

  (event-debug-on)

  (remove-event-handler ::lp-handler)


  (reset! board [])

  (main-loop (metronome 200))
  (stop)

  @board


  (doseq [board (take 5 (iterate life/next patterns/blinker))] (print-board board))

  (print-board patterns/barren)

  (stop)

  (set! s/*explain-out* (expound/custom-printer {:show-valid-values? true
                                                 :print-specs? false
                                                 :theme :figwheel-theme}))
  )
