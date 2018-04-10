#!/bin/bash

ANTLR_CMD="java -jar /usr/local/lib/antlr-4.7.1-complete.jar"

# Generates Antlr grammar java source files and copies to project source
$ANTLR_CMD -visitor src/main/antlr4/nl/jkoetsier/uva/dbbench/input/schema/tsql/grammar/TSqlLexer.g4
$ANTLR_CMD -visitor src/main/antlr4/nl/jkoetsier/uva/dbbench/input/schema/tsql/grammar/TSqlParser.g4

for file in src/main/antlr4/nl/jkoetsier/uva/dbbench/input/schema/tsql/grammar/*; do
    if [[ ! $file = *".g4" ]]; then
#        echo -e "package nl.jkoetsier.uva.dbbench.input.schema.tsql.grammar;\n$(cat $file)" > $file
        echo -e "package nl.jkoetsier.uva.dbbench.input.schema.tsql.grammar;\n" > /tmp/tmpfile
        cat $file >> /tmp/tmpfile
        cp /tmp/tmpfile $file
        mv $file src/main/java/nl/jkoetsier/uva/dbbench/input/schema/tsql/grammar
    fi
done