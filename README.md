# jee10sec

run the following JBoss-CLI commands:

```
/subsystem=ejb3/application-security-domain=test:add(security-domain=ApplicationDomain
/subsystem=undertow/application-security-domain=test:add(security-domain=ApplicationDomain
```

add a user with name 'test' and password 'test' to the jboss app-realm:

```
jboss-eap-8.0/bin/add-user.sh -a -u test -p test -g testgroup
```

deploy the ear file

do some testing:

```
curl --user test:test http://localhost:8080/rstest/whoami
curl --user test:test http://localhost:8080/rstest/whoami2
```

connect the application to your keycloak (ensure to have a test:test-user with role testgroup there)

```
/subsystem=elytron-oidc-client/secure-deployment=jee10sec-rest.war:add(provider-url=...,client-id=...,ssl-required=external,principal-attribute=preferred_username,enable-basic-auth=true)
/subsystem=elytron-oidc-client/secure-deployment=jee10sec-rest.war/credential=secret:add(secret=...)
:reload
```

re-run the above tests (curl)



```
/subsystem=elytron-oidc-client/secure-deployment=jee10sec-rest.war:remove
:reload
```

and everything works again!