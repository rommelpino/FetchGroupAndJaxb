package soadev;

import commonj.sdo.DataObject;
import commonj.sdo.helper.XMLDocument;
import commonj.sdo.helper.XSDHelper;

import java.util.ArrayList;
import java.util.Hashtable;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.xml.bind.JAXBContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;

import oracle.bpel.services.common.util.XMLUtil;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.queries.FetchGroup;

import org.eclipse.persistence.sdo.helper.jaxb.JAXBHelperContext;

import org.w3c.dom.Element;

import soadev.model.domain.Job;
import soadev.model.domain.Request;
import soadev.model.service.HRFacade;

public class HRFacadeClient {
    public static void main(String [] args) {
        try {
            final Context context = getInitialContext();
            HRFacade service = (HRFacade)context.lookup("FetchGroup-Model-HRFacade#soadev.model.service.HRFacade");
            String jpql = "Select o from Job o";
            FetchGroup group = new FetchGroup();
            group.addAttribute("jobTitle");
            group.addAttribute("jobId");
            group.addAttribute("maxSalary");
            List<Object[]> hints = new ArrayList<Object[]>();
            hints.add(new Object[]{QueryHints.FETCH_GROUP, group});
            hints.add(new Object[]{QueryHints.READ_ONLY, HintValues.TRUE});
            List<Job> jobList = (List<Job>)service.queryByRange(jpql, hints, 0, 0);
            for(Job job: jobList){
//                System.out.println(job.getJobId() + ": " + job.getJobTitle() + ": " + job.getMaxSalary());
//                testMarshallPojoToSdo(job);
                Request request = new Request();
                request.setJob(job);
                testMarshallPojoToSdo(request);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void testMarshallPojoToSdo(Job pojo){
        String elementName = "varSDO";
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Job.class);
            JAXBHelperContext jaxbHelperContext =
                new JAXBHelperContext(jaxbContext);
            XSDHelper xsdHelper = jaxbHelperContext.getXSDHelper();
            ClassLoader loader =
                Thread.currentThread().getContextClassLoader();
            xsdHelper.define(loader.getResourceAsStream("xsd/JobSDO.xsd"),
                            "xsd/");
            DataObject sdoFromPojo = jaxbHelperContext.wrap(pojo);
            DocumentBuilder docBuilder =
                DocumentBuilderFactory.newInstance().newDocumentBuilder();
            org.w3c.dom.Document document = docBuilder.newDocument();
            XMLDocument xmlDoc =
                jaxbHelperContext.getXMLHelper().createDocument(sdoFromPojo,
                                                                sdoFromPojo.getType().getURI(),
                                                                elementName);
            jaxbHelperContext.getXMLHelper().save(xmlDoc,
                                                  new DOMResult(document),
                                                  null);
            Element element = document.getDocumentElement();
            System.out.println(XMLUtil.toString(element));
            XMLDocument xmlDocument = 
                          jaxbHelperContext.getXMLHelper().load(new DOMSource(element), null, null);
            DataObject sdoFromElement = xmlDocument.getRootObject();
            Job pojoFromElement = (Job)jaxbHelperContext.unwrap(sdoFromElement);
            System.out.println(pojoFromElement.getMaxSalary());
        } catch (Exception e) {
            e.printStackTrace();
        }
           
    }
    
    public static void testMarshallPojoToSdo(Request pojo){
        String elementName = "varSDO";
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Request.class);
            JAXBHelperContext jaxbHelperContext =
                new JAXBHelperContext(jaxbContext);
            XSDHelper xsdHelper = jaxbHelperContext.getXSDHelper();
            ClassLoader loader =
                Thread.currentThread().getContextClassLoader();
            xsdHelper.define(loader.getResourceAsStream("xsd/JobSDO.xsd"),
                            "xsd/");
            xsdHelper.define(loader.getResourceAsStream("xsd/RequestSDO.xsd"),
                            "xsd/");
            DataObject sdoFromPojo = jaxbHelperContext.wrap(pojo);
            DocumentBuilder docBuilder =
                DocumentBuilderFactory.newInstance().newDocumentBuilder();
            org.w3c.dom.Document document = docBuilder.newDocument();
            XMLDocument xmlDoc =
                jaxbHelperContext.getXMLHelper().createDocument(sdoFromPojo,
                                                                sdoFromPojo.getType().getURI(),
                                                                elementName);
            jaxbHelperContext.getXMLHelper().save(xmlDoc,
                                                  new DOMResult(document),
                                                  null);
            Element element = document.getDocumentElement();
            System.out.println(XMLUtil.toString(element));
            XMLDocument xmlDocument = 
                          jaxbHelperContext.getXMLHelper().load(new DOMSource(element), null, null);
            DataObject sdoFromElement = xmlDocument.getRootObject();
            Request pojoFromElement = (Request)jaxbHelperContext.unwrap(sdoFromElement);
            System.out.println(pojoFromElement.getJob().getJobTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
           
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x connection details
        env.put( Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory" );
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext( env );
    }
}
