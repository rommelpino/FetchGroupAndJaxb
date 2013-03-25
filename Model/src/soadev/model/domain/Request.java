package soadev.model.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlType(name = "RequestSDO", namespace = "http://app.soadev.com/model/domain")
@XmlRootElement(name="requestSDO")
@XmlAccessorType(XmlAccessType.FIELD)

public class Request {
    private Job job;

    public void setJob(Job job) {
        this.job = job;
    }

    public Job getJob() {
        return job;
    }
}
