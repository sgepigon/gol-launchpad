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

;; whaaaat

(def whaaaat
  [[:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :O :O :_ :_ :_ :_]
   [:_ :O :O :O :O :_ :_ :_]
   [:_ :_ :O :O :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]])

;; cross-tetris

(def cross-tetris
  [[:_ :_ :_ :_ :_ :_ :_ :O]
   [:_ :O :_ :_ :_ :_ :_ :_]
   [:_ :_ :O :_ :_ :_ :O :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :O :O :_ :_]
   [:_ :_ :_ :O :O :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :O :_ :_ :_ :O :_]])

(def peek-a-boo
  [[:_ :_ :_ :O :O :_ :_ :_]
   [:_ :_ :_ :O :O :_ :_ :_]
   [:_ :_ :_ :O :O :_ :_ :_]
   [:_ :_ :_ :O :O :_ :_ :_]
   [:_ :_ :_ :O :O :_ :_ :_]
   [:_ :_ :_ :O :O :_ :_ :_]
   [:_ :_ :_ :O :O :_ :_ :_]
   [:_ :_ :_ :O :O :_ :_ :_]])

(def blah
  [[:_ :O :_ :_ :_ :_ :O :_]
   [:O :_ :_ :_ :_ :_ :_ :O]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:_ :_ :_ :_ :_ :_ :_ :_]
   [:O :_ :_ :_ :_ :_ :_ :O]])
