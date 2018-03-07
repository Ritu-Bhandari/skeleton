To Run this project :
1) clone this repository.
2) run   "mvn clean install" inside main folder -> skeleton
3) go inside web folder "cd web"
4) run "mvn jetty:run"

To Debug :

Run this command on your command prompt :
export MAVEN_OPTS="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n"
You can change the port address here.set DEBUG configuration to enable this port for debugging.
configure debug in eclipse :
debug -> debug configuration -> remote java application -> new -> set below values
host : localhost
port : 8000 (value you kept in debug command)
choose source
in commons tab : select Debug and then apply. Debug will start. You can put debug points.


API Signature :

1. Healthcheck :
GET : http://localhost:8080/skeleton/v1/healthcheck

2. Get project name :
GET : http://localhost:8080/skeleton/v1/projectName

3. Change project name :
PUT : http://localhost:8080/skeleton/v1/projectName?q=changed
