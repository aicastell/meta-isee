
PR := "${PR}.1"

inherit update-rc.d

INITSCRIPT_NAME = "ppp"
INITSCRIPT_PARAMS = "start 60 5 2 . stop 60 0 1 6 ."
