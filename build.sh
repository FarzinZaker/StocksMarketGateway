export JAVA_HOME=/usr/java/default/
export GRAILS_HOME=/home/src/grails-2.2.3/
export JAVA_OPTS="-Xmx2024m -XX:MaxPermSize=512m"
/home/src/grails-2.2.3/bin/grails war
cp -uf target/Stocks-0.1.war ../deploy/stocks.war

