package io.github.linktosriram.s3lite.api.exception;

import java.util.Objects;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * Reference: https://docs.aws.amazon.com/AmazonS3/latest/API/ErrorResponses.html
 */
//@XmlRootElement(name = "Error")
public class ErrorResponse {

    private String code;

    private String message;

    private String requestId;

    private String hostId;

    //@XmlElement(name = "Code", required = true)
    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    //@XmlElement(name = "Message", required = true)
    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    //@XmlElement(name = "RequestId", required = true)
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(final String requestId) {
        this.requestId = requestId;
    }

    //@XmlElement(name = "HostId", required = true)
    public String getHostId() {
        return hostId;
    }

    public void setHostId(final String hostId) {
        this.hostId = hostId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ErrorResponse that = (ErrorResponse) o;
        return Objects.equals(code, that.code) &&
            Objects.equals(message, that.message) &&
            Objects.equals(requestId, that.requestId) &&
            Objects.equals(hostId, that.hostId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, requestId, hostId);
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
            "code='" + code + '\'' +
            ", message='" + message + '\'' +
            ", requestId='" + requestId + '\'' +
            ", hostId='" + hostId + '\'' +
            '}';
    }
    
    public static ErrorResponse parseResponse(XMLEventReader rdr) throws XMLStreamException {
    	ErrorResponse rsp = new ErrorResponse();
    	while(rdr.hasNext()) {
    		XMLEvent nxt = rdr.nextEvent();
    		if (nxt.isStartElement()) {
    			StartElement selem = nxt.asStartElement();
    			switch (selem.getName().getLocalPart()) {
    				case "Code":
    					nxt = rdr.nextEvent();
    					rsp.setCode(nxt.asCharacters().getData());
    					break;
    				case "Message":
    					nxt = rdr.nextEvent();
    					rsp.setMessage(nxt.asCharacters().getData());
    					break;
    				case "RequestId":
    					nxt = rdr.nextEvent();
    					rsp.setRequestId(nxt.asCharacters().getData());
    					break;
    				case "HostId":
    					nxt = rdr.nextEvent();
    					rsp.setHostId(nxt.asCharacters().getData());
    					break;    					
    			}
    		}
    	}
    	return rsp;
    }
}
