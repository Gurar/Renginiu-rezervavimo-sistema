package lt.viko.eif.eventsystem.mapper;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lt.viko.eif.eventsystem.exception.CustomException;

import java.util.Map;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<CustomException> {

    @Override
    public Response toResponse(CustomException exception) {
        return Response .status(Response.Status.BAD_REQUEST) .entity(
                Map.of( "message", exception.getMessage() ) ) .type(MediaType.APPLICATION_JSON) .build();
    }
}
