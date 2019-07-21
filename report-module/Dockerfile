FROM jboss/wildfly

MAINTAINER "am3 - team"

RUN wildfly/bin/add-user.sh admin admin --silent

ADD /target/report-module.war wildfly/standalone/deployments/

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
