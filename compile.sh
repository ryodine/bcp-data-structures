#/bin/bash
javac $1
dotJava=".java";
class=${1%$dotJava};
java "${class}" $2 $3 $4 $5 $6 $7 $8;
rm -- *.class;