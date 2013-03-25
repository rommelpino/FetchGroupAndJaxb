package soadev.model.service;

import java.util.List;

import javax.ejb.Remote;

import soadev.model.domain.Job;

@Remote
public interface HRFacade {
    Object queryByRange(String jpqlStmt, List<Object[]> hints, int firstResult, int maxResults);

    Job persistJob(Job job);

    Job mergeJob(Job job);

    void removeJob(Job job);
}
