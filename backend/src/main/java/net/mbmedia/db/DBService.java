package net.mbmedia.db;

import net.mbmedia.db.entities.RKI;
import net.mbmedia.db.entities.RkiUpdate;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBService extends BaseJPA{

    public ArrayList<RKI> getAll(){
        EntityManager em = getEntityManager();

        ArrayList<RKI> results = (ArrayList<RKI>) em.createQuery("SELECT r from RKI r where r.id.lastUpdateId = :id")
                .setParameter("id", getLastUpdateId())
                .getResultList();

        closeEntitiyManager(em);
        return results;
    }

    public RkiUpdate getLastUpdate(){
        EntityManager em = getEntityManager();
        try{
            return em.find(RkiUpdate.class, getLastUpdateId());
        }catch (Exception e){
            return new RkiUpdate(0, new Date());
        }finally {
            closeEntitiyManager(em);
        }
    }

    private int getLastUpdateId(){
        EntityManager em = getEntityManager();
        Session session = em.unwrap(Session.class);
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<RkiUpdate> cq = cb.createQuery(RkiUpdate.class);
        Root<RkiUpdate> root = cq.from(RkiUpdate.class);

        cq.select(root).orderBy(cb.desc(root.get("id")));

        List<RkiUpdate> results = session.createQuery(cq)
                .setMaxResults(1)
                .getResultList();

        return results.get(0).getId();
    }

    public void persistRKIs(List<RKI> list){
        EntityManager em = getEntityManager();
        EntityTransaction entityTransaction = startTransaction(em);
        list.forEach(em::persist);
        entityTransaction.commit();
        closeEntitiyManager(em);
    }

    public void persistLastUpdate(RkiUpdate update){
        EntityManager em = getEntityManager();
        EntityTransaction et = startTransaction(em);
        em.merge(update);
        et.commit();
        closeEntitiyManager(em);
    }

}
