
<author-instance>/WEB-INF/config/default/magnolia.properties
magnolia.home=/home/megion/magnoliaHome/megion-site 
magnolia.bootstrap.authorInstance=true

<public-instance>/WEB-INF/config/default/magnolia.properties
magnolia.home=/home/megion/magnoliaHome/megion-sitePublic 
magnolia.bootstrap.authorInstance=false

tomcat/conf/server.xml
<Connector URIEncoding ="UTF-8" connectionTimeout="20000" port="8080" protocol="HTTP/1.1" redirectPort="8443"/>

tomcat/bin/setenv.sh
export CATALINA_OPTS="$CATALINA_OPTS -XX:MaxPermSize=256m -Xms64M -Xmx1512M -Djava.awt.headless=true"

author instance to public instance:
http://documentation.magnolia-cms.com/display/DOCS45/Changing+author+to+public+instance
http://documentation.magnolia-cms.com/display/DOCS45/Subscribers

Start
/home/ilya/servers/magnolia-4.5.6/apache-tomcat-6.0.32/bin/magnolia_control.sh start && tail -f /home/ilya/servers/magnolia-4.5.6/apache-tomcat-6.0.32/logs/catalina.out
Stop
/home/ilya/servers/magnolia-4.5.6/apache-tomcat-6.0.32/bin/magnolia_control.sh stop && tail -f /home/ilya/servers/magnolia-4.5.6/apache-tomcat-6.0.32/logs/catalina.out

mvn dependency:tree -Dverbose -Dincludes=org.bouncycastle
