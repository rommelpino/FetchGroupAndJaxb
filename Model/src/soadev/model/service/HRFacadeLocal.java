package soadev.model.service;

import java.util.List;

import javax.ejb.Local;

import soadev.model.domain.Job;

@Local
public interface HRFacadeLocal {
    Object queryByRange(String jpqlStmt, List<Object[]> hints, int firstResult, int maxResults);

    Job persistJob(Job job);

    Job mergeJob(Job job);

    void removeJob(Job job);
}
