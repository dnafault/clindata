FROM tomcat:7-jre7

ADD lib/mysql-connector-java-5.1.35-bin.jar $CATALINA_HOME/lib/
ADD conf/tomcat-users.xml $CATALINA_HOME/conf/
ADD conf/logs.xml $CATALINA_HOME/conf/Catalina/localhost/
ADD conf/web.xml $CATALINA_HOME/conf/

ADD clindata.war /usr/local/tomcat/webapps/clindata.war
