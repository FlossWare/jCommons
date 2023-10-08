package org.flossware.java.soap;

import jakarta.xml.ws.Service;

/**
 * Record type to hold a SOAP service and the port class type.
 * 
 * @author sfloess
 */
public record CxfSoap(Service service, Class portType) {
}
