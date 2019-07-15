package de.schoeffm.streamoutput.boundary;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

/**
 *
 * @author airhacks.com
 */
@Path("resource")
public class StreamingResource {

    @GET
    @Path("stream")
    @Produces(MediaType.TEXT_PLAIN)
    public Response streamMe() {

        StreamingOutput stream = output -> {
            Writer writer = new BufferedWriter(new OutputStreamWriter(output));
            for (int i = 0; i < 1000; i++) {
                writer.write("This is the " + i + "th input for the result stream." + "\n");
                pause(10L);
            }
            writer.flush();
        };

        return Response.ok(stream).build();
    }

    @GET
    @Path("normal")
    @Produces(MediaType.TEXT_PLAIN)
    public Response normal() {

        StringBuilder b = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            b.append("This is the " + i + "th input for the result stream." + "\n");
            pause(10L);
        }

        return Response.ok(b.toString()).build();
    }


    private void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
