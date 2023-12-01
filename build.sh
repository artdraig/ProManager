#! /bin/bash
cd "$(dirname "$0")"
DATE=$(date +%Hh%Mm%Ss)

if javac -d bin -g *.java ; then
    echo Build successful $DATE

    echo $SECONDS seconds to compile
    cd bin
    java ProManager
fi
