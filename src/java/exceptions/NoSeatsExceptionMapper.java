/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.ServletContext;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author kaspe
 */
@Provider
public class NoSeatsExceptionMapper implements ExceptionMapper<NoSeatsException>
{

    Gson g = new GsonBuilder().setPrettyPrinting().create();
    @Context
    ServletContext context;

    @Override
    public Response toResponse(NoSeatsException e)
    {

        ErrorMessage err = new ErrorMessage(e, Response.Status.BAD_REQUEST.getStatusCode());
        err.setMessage("Not enough seats, we are sorry");
        err.setErrorCode(2);
        err.setHttpError(400);
        Response out = Response.status(400).entity(g.toJson(err)).type(MediaType.APPLICATION_JSON).build();
        return out;

    }

}