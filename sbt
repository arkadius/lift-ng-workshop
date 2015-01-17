#!/bin/sh

INTERNAL_OPTS="-Dfile.encoding=UTF-8 -Xmx512m -noverify -XX:ReservedCodeCacheSize=296m -XX:+CMSClassUnloadingEnabled -XX:+UseConcMarkSweepGC -XX:MaxPermSize=812m"

# Add 64bit specific option
exec java -version 2>&1 | grep -q "64-Bit" && INTERNAL_OPTS="${INTERNAL_OPTS} -XX:+UseCompressedOops -XX:ReservedCodeCacheSize=328m"

java $INTERNAL_OPTS -jar `dirname $0`/sbt-launch-0.13.1.jar "$@"
