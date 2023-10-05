package test.ejb;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.LocalBean;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;

@Stateless
@LocalBean
public class TestService {

    @Resource
    private SessionContext sessionContext;

    @PermitAll
    public String getName() {
        return sessionContext.getCallerPrincipal().getName();
    }

    @RolesAllowed("testgroup")
    public String getNameRestricted() {
        return sessionContext.getCallerPrincipal().getName();
    }

}
