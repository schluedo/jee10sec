package test.service;

import jakarta.ejb.EJB;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import test.ejb.TestService;

@Path("")
public class TestRestService {

    @EJB
    private TestService testEJB;

    @Context
    private HttpServletRequest request;

    @GET
    @Produces(MediaType.WILDCARD)
    @Path("whoami")
    public String whoami() {
        String web = request.getUserPrincipal().getName();
        String ejb = testEJB.getName();
        return String.format("web-user: %s, ejb-user: %s", web, ejb);
    }

    @GET
    @Produces(MediaType.WILDCARD)
    @Path("whoami2")
    public String whoami2() {
        String web = request.getUserPrincipal().getName();
        String ejb = testEJB.getNameRestricted();
        return String.format("web-user: %s, ejb-user: %s", web, ejb);
    }

}
