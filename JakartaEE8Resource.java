package com.mycompany.ejaassignment.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author 
 */
@Path("rest")
public class JakartaEE8Resource extends Application {
    
    @GET
    public Response ping(){
        return Response
                .ok("ping")
                .build();
    }
}
