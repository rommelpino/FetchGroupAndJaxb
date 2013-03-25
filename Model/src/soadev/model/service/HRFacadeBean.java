package soadev.model.service;

import java.util.List;

import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;

import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import soadev.model.domain.Job;

@Stateless(name = "HRFacade", mappedName = "FetchGroup-Model-HRFacade")
public class HRFacadeBean implements HRFacade, HRFacadeLocal {
    @PersistenceContext(unitName="Model")
    private EntityManager em;

    public HRFacadeBean() {
    }
    @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
    public Object queryByRange(String jpqlStmt, List<Object[]> hints, int firstResult,
                               int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (hints != null && !hints.isEmpty()) {
            for (Object[] hint : hints) {
                query.setHint((String)hint[0], hint[1]);
            }
        }
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    public Job persistJob(Job job) {
        em.persist(job);
        return job;
    }

    public Job mergeJob(Job job) {
        return em.merge(job);
    }

    public void removeJob(Job job) {
        job = em.find(Job.class, job.getJobId());
        em.remove(job);
    }
}
