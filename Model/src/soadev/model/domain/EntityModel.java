package soadev.model.domain;

import java.io.Serializable;

import javax.persistence.Column;

import javax.persistence.MappedSuperclass;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;


@MappedSuperclass
@XmlAccessorType(XmlAccessType.NONE)
@XmlTransient
public class EntityModel implements Serializable{
    @Column(name="MIN_SALARY")
    private Long minSalary;
    
    public Long getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Long minSalary) {
        this.minSalary = minSalary;
    }
}
