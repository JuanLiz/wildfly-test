FROM jboss/wildfly:22.0.1.Final
ADD target/wildfly-test.war /opt/jboss/wildfly/standalone/deployments/
RUN /opt/jboss/wildfly/bin/add-user.sh admin 1234 --silent

