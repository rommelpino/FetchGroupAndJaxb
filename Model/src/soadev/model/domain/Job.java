package soadev.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@NamedQueries({
  @NamedQuery(name = "Job.findAll", query = "select o from Job o")
})
@Table(name = "JOBS")
@XmlType(name = "JobSDO", namespace = "http://app.soadev.com/model/domain")
@XmlRootElement(name="jobSDO")
@XmlAccessorType(XmlAccessType.FIELD)
public class Job extends EntityModel implements Serializable {
    @Id
    @Column(name="JOB_ID", nullable = false, length = 10)
    private String jobId;
    @Column(name="JOB_TITLE", nullable = false, length = 35)
    private String jobTitle;
    @Column(name="MAX_SALARY")
    private Long maxSalary;
//    @Column(name="MIN_SALARY")
//    private Long minSalary;

    public Job() {
    }

    public Job(String jobId, String jobTitle, Long maxSalary, Long minSalary) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.maxSalary = maxSalary;
//        this.minSalary = minSalary;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Long getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Long maxSalary) {
        this.maxSalary = maxSalary;
    }

//    public Long getMinSalary() {
//        return minSalary;
//    }
//
//    public void setMinSalary(Long minSalary) {
//        this.minSalary = minSalary;
//    }
}
