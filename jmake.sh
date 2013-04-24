#!/bin/sh

# Simple script qui rajoute deux alias au bashrc:
# jmake émule un make
# et jclean émule un make clean

# Penser à fermer/relancer toutes les instances du terminale pour que les alias prennent
# effets

echo "alias jmake='javac *.java'" >> ~/.bashrc
echo "alias jclean='rm *.class'" >> ~/.bashrc


