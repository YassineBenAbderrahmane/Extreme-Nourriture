export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64/

export JBOSS_HOME=/tmp/wildfly-14.0.1.Final

CLASSPATH=./src:$JBOSS_HOME/modules/system/layers/base/javax/servlet/api/main/jboss-servlet-api_4.0_spec-1.0.0.Final.jar


javac -cp $CLASSPATH -sourcepath src -d ./WEB-INF/classes src/bean/*.java src/db/*.java src/servlet/*.java
jar cf extremenourriture.war WEB-INF style.css  web_rsc
cp extremenourriture.war $JBOSS_HOME/standalone/deployments
