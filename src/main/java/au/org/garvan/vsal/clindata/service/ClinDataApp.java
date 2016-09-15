package au.org.garvan.vsal.clindata.service;

import au.org.garvan.vsal.clindata.util.CORSResponseFilter;
import au.org.garvan.vsal.clindata.util.LoggingResponseFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * Registers the components to be used by the JAX-RS application  
 */
public class ClinDataApp extends ResourceConfig {

    /**
	* Register JAX-RS application components.
	*/	
	public ClinDataApp(){
		register(RequestContextFilter.class);
		register(ClinDataRestService.class);
		register(JacksonFeature.class);	
		register(LoggingResponseFilter.class);
		register(CORSResponseFilter.class);
	}
}
