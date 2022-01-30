#!/bin/bash

cd ./sources/
javac -nowarn -Xlint -cp json-20211205.jar -d . game-files/*.java
jar cf game.jar p1/*.class
#javac -nowarn -Xlint -cp .:sources/json-20211205.jar -d ./sources/ ./sources/game-files/*.java
#jar cf ./sources/game.jar ./sources/p1/*.class
#rm -r ./sources/p1

#javac -nowarn -Xlint -cp .:sources/game.jar:sources/json-20211205.jar -d . sources/Test.java
javac -nowarn -Xlint -cp game.jar:json-20211205.jar -d . Test.java
