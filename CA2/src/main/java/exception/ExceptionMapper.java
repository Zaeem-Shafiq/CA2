package exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable e) {
        return Response.status(500).entity("{\"code\": 500, \"message\": \"Internal server Error, we are very sorry for the inconvenience" + "\" }").build();
    }

}
