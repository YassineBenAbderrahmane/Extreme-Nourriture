export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64/
export JBOSS_HOME=/tmp/wildfly-14.0.1.Final
bash $JBOSS_HOME/bin/standalone.sh
CLASSPATH=./src:$JBOSS_HOME/modules/system/layers/base/javax/servlet/api/main/jboss-servlet-api_4.0_spec-1.0.0.Final.jar

