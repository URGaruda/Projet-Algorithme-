#!/bin/bash
# Check if exactly two arguments are provided
if [ "$#" -ne 3 ]; then
    echo "Usage: $0 <x> <y>"
    exit 1
fi
x=$1
y=$2
z=$3
# test l'existence du fichier
if test ! -f "$y" ; then
    echo le fichier $y "n'existe "pas
    exit 1
fi
# Check if x is either 0 or 1
if [ "$x" -eq 0 ]; then
    echo "Running Patricia Trie with argument $y $z"
    make Test_Patricia.class
    java Test_Patricia prefixe $y $z

elif [ "$x" -eq 1 ]; then
    echo "Running Hybrid Trie with argument $y $z"
    make fatiMain.class
    java fatiMain prefixe $y $z
    
else
    echo "Error: x must be 0 or 1"
    exit 1
fi

make clean