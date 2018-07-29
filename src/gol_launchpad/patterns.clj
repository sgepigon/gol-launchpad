(ns gol-launchpad.patterns)

;; empty

(def barren
  [[:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]])

;; still life

(def blinker
  [[:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :O :O :O :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]])

;; oscillators

(def beacon
  [[:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :O :O :_ :_ :_ :_ :_]
   [:_ :O :O :_ :_ :_ :_ :_]
   [:_ :_ :_ :O :O :_ :_ :_]
   [:_ :_ :_ :O :O :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]])

;; spaceships

(def glider
  [[:_ :O :_ :_ :_ :_ :_ :_]
   [:_ :_ :O :_ :_ :_ :_ :_]
   [:O :O :O :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]])