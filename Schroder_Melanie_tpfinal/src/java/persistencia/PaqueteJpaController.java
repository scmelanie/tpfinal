
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Servicio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Paquete;
import persistencia.exceptions.NonexistentEntityException;

public class PaqueteJpaController implements Serializable {

    public PaqueteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    
    //CONSTRUCTOR CREADO
    public PaqueteJpaController() {
        emf = Persistence.createEntityManagerFactory("TP_FINALPU");
    }
    
    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Paquete paquete) {
        if (paquete.getListaServiciosPorPaquete() == null) {
            paquete.setListaServiciosPorPaquete(new ArrayList<Servicio>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Servicio> attachedListaServiciosPorPaquete = new ArrayList<Servicio>();
            for (Servicio listaServiciosPorPaqueteServicioToAttach : paquete.getListaServiciosPorPaquete()) {
                listaServiciosPorPaqueteServicioToAttach = em.getReference(listaServiciosPorPaqueteServicioToAttach.getClass(), listaServiciosPorPaqueteServicioToAttach.getCodigoServicio());
                attachedListaServiciosPorPaquete.add(listaServiciosPorPaqueteServicioToAttach);
            }
            paquete.setListaServiciosPorPaquete(attachedListaServiciosPorPaquete);
            em.persist(paquete);
            for (Servicio listaServiciosPorPaqueteServicio : paquete.getListaServiciosPorPaquete()) {
                listaServiciosPorPaqueteServicio.getPaquetesConElServicio().add(paquete);
                listaServiciosPorPaqueteServicio = em.merge(listaServiciosPorPaqueteServicio);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Paquete paquete) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paquete persistentPaquete = em.find(Paquete.class, paquete.getCodigoPaquete());
            List<Servicio> listaServiciosPorPaqueteOld = persistentPaquete.getListaServiciosPorPaquete();
            List<Servicio> listaServiciosPorPaqueteNew = paquete.getListaServiciosPorPaquete();
            List<Servicio> attachedListaServiciosPorPaqueteNew = new ArrayList<Servicio>();
            for (Servicio listaServiciosPorPaqueteNewServicioToAttach : listaServiciosPorPaqueteNew) {
                listaServiciosPorPaqueteNewServicioToAttach = em.getReference(listaServiciosPorPaqueteNewServicioToAttach.getClass(), listaServiciosPorPaqueteNewServicioToAttach.getCodigoServicio());
                attachedListaServiciosPorPaqueteNew.add(listaServiciosPorPaqueteNewServicioToAttach);
            }
            listaServiciosPorPaqueteNew = attachedListaServiciosPorPaqueteNew;
            paquete.setListaServiciosPorPaquete(listaServiciosPorPaqueteNew);
            paquete = em.merge(paquete);
            for (Servicio listaServiciosPorPaqueteOldServicio : listaServiciosPorPaqueteOld) {
                if (!listaServiciosPorPaqueteNew.contains(listaServiciosPorPaqueteOldServicio)) {
                    listaServiciosPorPaqueteOldServicio.getPaquetesConElServicio().remove(paquete);
                    listaServiciosPorPaqueteOldServicio = em.merge(listaServiciosPorPaqueteOldServicio);
                }
            }
            for (Servicio listaServiciosPorPaqueteNewServicio : listaServiciosPorPaqueteNew) {
                if (!listaServiciosPorPaqueteOld.contains(listaServiciosPorPaqueteNewServicio)) {
                    listaServiciosPorPaqueteNewServicio.getPaquetesConElServicio().add(paquete);
                    listaServiciosPorPaqueteNewServicio = em.merge(listaServiciosPorPaqueteNewServicio);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = paquete.getCodigoPaquete();
                if (findPaquete(id) == null) {
                    throw new NonexistentEntityException("The paquete with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paquete paquete;
            try {
                paquete = em.getReference(Paquete.class, id);
                paquete.getCodigoPaquete();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paquete with id " + id + " no longer exists.", enfe);
            }
            List<Servicio> listaServiciosPorPaquete = paquete.getListaServiciosPorPaquete();
            for (Servicio listaServiciosPorPaqueteServicio : listaServiciosPorPaquete) {
                listaServiciosPorPaqueteServicio.getPaquetesConElServicio().remove(paquete);
                listaServiciosPorPaqueteServicio = em.merge(listaServiciosPorPaqueteServicio);
            }
            em.remove(paquete);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Paquete> findPaqueteEntities() {
        return findPaqueteEntities(true, -1, -1);
    }

    public List<Paquete> findPaqueteEntities(int maxResults, int firstResult) {
        return findPaqueteEntities(false, maxResults, firstResult);
    }

    private List<Paquete> findPaqueteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paquete.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Paquete findPaquete(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paquete.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaqueteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Paquete> rt = cq.from(Paquete.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
