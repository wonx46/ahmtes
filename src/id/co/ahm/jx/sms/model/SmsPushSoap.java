
package id.co.ahm.jx.sms.model;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "SmsPushSoap", targetNamespace = "http://tempuri.org/AHMWSPush/SmsPush")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface SmsPushSoap {


    /**
     * 
     * @param division
     * @param batchName
     * @param uploadBy
     * @param serviceType
     * @param msisdn
     * @param message
     * @return 
     */
    @WebMethod(operationName = "MGMSendMessage", action = "http://tempuri.org/AHMWSPush/SmsPush/MGMSendMessage")
    @WebResult(name = "MGMSendMessageResult", targetNamespace = "http://tempuri.org/AHMWSPush/SmsPush")
    @RequestWrapper(localName = "MGMSendMessage", targetNamespace = "http://tempuri.org/AHMWSPush/SmsPush", className = "org.tempuri.ahmwspush.smspush.MGMSendMessage")
    @ResponseWrapper(localName = "MGMSendMessageResponse", targetNamespace = "http://tempuri.org/AHMWSPush/SmsPush", className = "org.tempuri.ahmwspush.smspush.MGMSendMessageResponse")
    public String mgmSendMessage(
        @WebParam(name = "msisdn", targetNamespace = "http://tempuri.org/AHMWSPush/SmsPush")
        String msisdn,
        @WebParam(name = "message", targetNamespace = "http://tempuri.org/AHMWSPush/SmsPush")
        String message,
        @WebParam(name = "division", targetNamespace = "http://tempuri.org/AHMWSPush/SmsPush")
        String division,
        @WebParam(name = "batchName", targetNamespace = "http://tempuri.org/AHMWSPush/SmsPush")
        String batchName,
        @WebParam(name = "uploadBy", targetNamespace = "http://tempuri.org/AHMWSPush/SmsPush")
        String uploadBy,
        @WebParam(name = "serviceType", targetNamespace = "http://tempuri.org/AHMWSPush/SmsPush")
        String serviceType);

}
