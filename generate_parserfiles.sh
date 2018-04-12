#!/bin/bash

# This script needs to be run only when something has changed in the directory structure of the
# grammar files. In that case the IDE does not yet have the correct packages, and coding will become
# quite difficult.

# May differ from system to system
ANTLR_CMD="java -jar /usr/local/lib/antlr-4.7.1-complete.jar"


SRC_DIR="src/main/antlr4/nl/jkoetsier/uva/dbbench/parser/tsql/grammar"
DST_DIR="src/main/java/nl/jkoetsier/uva/dbbench/parser/tsql/grammar"

# Generates Antlr grammar java source files and copies to project source
$ANTLR_CMD -visitor $SRC_DIR/TSqlLexer.g4
$ANTLR_CMD -visitor $SRC_DIR/TSqlParser.g4

for file in $SRC_DIR/*; do
    if [[ ! $file = *".g4" ]]; then
        echo -e "package nl.jkoetsier.uva.dbbench.parser.tsql.grammar;\n" > /tmp/tmpfile
        cat $file >> /tmp/tmpfile
        cp /tmp/tmpfile $file
        mv $file $DST_DIR
    fi
done