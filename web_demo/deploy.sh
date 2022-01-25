#!/bin/sh
set -e

if [ ! -n "$JAVA_OPTS" ]; then
  JAVA_OPTS="-Xmx1g -Xms1g"
fi
echo "JAVA_OPTS: $JAVA_OPTS"

if [ -n "$APM" ]; then
  echo "The skywalking apm is enabled."
  exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.config.location=classpath:/,file:/ -javaagent:skywalking/skywalking-agent.jar=agent.service_name=$APM -jar app.jar
else
  echo "The skywalking apm is disabled."
  exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.config.location=classpath:/,file:/ -jar app.jar
fi
