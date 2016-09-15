package au.org.garvan.vsal.clindata.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import au.org.garvan.vsal.clindata.dao.ClinDataDao;
import au.org.garvan.vsal.clindata.entities.ClinData;
import au.org.garvan.vsal.clindata.entities.Error;
import au.org.garvan.vsal.clindata.entities.SampleIDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/")
public class ClinDataRestService {

	@Autowired
	private ClinDataDao clinDataDao;

    @GET
    @Path("/find")
	@Produces(MediaType.APPLICATION_JSON)
	public Response find(@QueryParam("gender") String gender,
                         @QueryParam("yobStart") Integer yobStart,
                         @QueryParam("yobEnd") Integer yobEnd,
                         @QueryParam("sbpStart") Integer sbpStart,
                         @QueryParam("sbpEnd") Integer sbpEnd,
                         @QueryParam("heightStart") Float heightStart,
                         @QueryParam("heightEnd") Float heightEnd,
                         @QueryParam("weightStart") Float weightStart,
                         @QueryParam("weightEnd") Float weightEnd,
                         @QueryParam("abdCircStart") Integer abdCircStart,
                         @QueryParam("abdCircEnd") Integer abdCircEnd,
                         @QueryParam("glcStart") Float glcStart,
                         @QueryParam("glcEnd") Float glcEnd) {

        List<String> results = clinDataDao.findIDs(gender, yobStart, yobEnd, sbpStart, sbpEnd, heightStart, heightEnd,
                weightStart, weightEnd,  abdCircStart, abdCircEnd, glcStart, glcEnd);

        if (results != null && !results.isEmpty()) {
            SampleIDs samples = new SampleIDs(results);
            return Response.status(200).entity(samples).build();
        } else {
            return Response
                    .status(404)
                    .entity(new Error("No data", "No clinical data"))
                    .build();
        }
    }

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getById(@PathParam("id") String id) {
		ClinData clinDataById = clinDataDao.getClinDataById(id);
		if (clinDataById != null) {
			return Response.status(200).entity(clinDataById).build();
		} else {
			return Response
					.status(404)
					.entity(new Error("No data", "No clinical data for id: " + id))
					.build();
		}
	}

	@GET
	@Path("/check")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response check() {
		List<ClinData> clinData = clinDataDao.getClinData();
		List<String> missed = new ArrayList<String>();

        if (clinData == null) {
            return Response
                    .status(404)
                    .entity(new Error("No data", "No clinical data at all."))
                    .build();
        }

		for (int i=1; i <= 3375; ++i) {
			String id = String.format("GA%04d", i);
            boolean found = false;
			for (ClinData cd : clinData) {
                if (cd.getId().equals(id)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                missed.add(id);
            }
		}

		if (missed.isEmpty()) {
			return Response.status(200)
					.entity("ok")
					.build();
		} else {
			return Response
					.status(404)
                    .entity(new Error("No data", "No clinical data for id: " + missed))
                    .build();
		}
	}
}
