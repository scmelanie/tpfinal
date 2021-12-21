
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Venta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Paquete;
import logica.Servicio;
import persistencia.exceptions.NonexistentEntityException;

public class ServicioJpaController implements Serializable {

    public ServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    
    //CONSTRUCTOR CREADO
    public ServicioJpaController() {
        emf = Persistence.createEntityManagerFactory("TP_FINALPU");
    }
    
    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicio servicio) {
        if (servicio.getListaDeVentasDelServicio() == null) {
            servicio.setListaDeVentasDelServicio(new ArrayList<Venta>());
        }
        if (servicio.getPaquetesConElServicio() == null) {
            servicio.setPaquetesConElServicio(new ArrayList<Paquete>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Venta> attachedListaDeVentasDelServicio = new ArrayList<Venta>();
            for (Venta listaDeVentasDelServicioVentaToAttach : servicio.getListaDeVentasDelServicio()) {
                listaDeVentasDelServicioVentaToAttach = em.getReference(listaDeVentasDelServicioVentaToAttach.getClass(), listaDeVentasDelServicioVentaToAttach.getNroVenta());
                attachedListaDeVentasDelServicio.add(listaDeVentasDelServicioVentaToAttach);
            }
            servicio.setListaDeVentasDelServicio(attachedListaDeVentasDelServicio);
            List<Paquete> attachedPaquetesConElServicio = new ArrayList<Paquete>();
            for (Paquete paquetesConElServicioPaqueteToAttach : servicio.getPaquetesConElServicio()) {
                paquetesConElServicioPaqueteToAttach = em.getReference(paquetesConElServicioPaqueteToAttach.getClass(), paquetesConElServicioPaqueteToAttach.getCodigoPaquete());
                attachedPaquetesConElServicio.add(paquetesConElServicioPaqueteToAttach);
            }
            servicio.setPaquetesConElServicio(attachedPaquetesConElServicio);
            em.persist(servicio);
            for (Venta listaDeVentasDelServicioVenta : servicio.getListaDeVentasDelServicio()) {
                Servicio oldServicioOfListaDeVentasDelServicioVenta = listaDeVentasDelServicioVenta.getServicio();
                listaDeVentasDelServicioVenta.setServicio(servicio);
                listaDeVentasDelServicioVenta = em.merge(listaDeVentasDelServicioVenta);
                if (oldServicioOfListaDeVentasDelServicioVenta != null) {
                    oldServicioOfListaDeVentasDelServicioVenta.getListaDeVentasDelServicio().remove(listaDeVentasDelServicioVenta);
                    oldServicioOfListaDeVentasDelServicioVenta = em.merge(oldServicioOfListaDeVentasDelServicioVenta);
                }
            }
            for (Paquete paquetesConElServicioPaquete : servicio.getPaquetesConElServicio()) {
                paquetesConElServicioPaquete.getListaServiciosPorPaquete().add(servicio);
                paquetesConElServicioPaquete = em.merge(paquetesConElServicioPaquete);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicio servicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio persistentServicio = em.find(Servicio.class, servicio.getCodigoServicio());
            List<Venta> listaDeVentasDelServicioOld = persistentServicio.getListaDeVentasDelServicio();
            List<Venta> listaDeVentasDelServicioNew = servicio.getListaDeVentasDelServicio();
            List<Paquete> paquetesConElServicioOld = persistentServicio.getPaquetesConElServicio();
            List<Paquete> paquetesConElServicioNew = servicio.getPaquetesConElServicio();
            List<Venta> attachedListaDeVentasDelServicioNew = new ArrayList<Venta>();
            for (Venta listaDeVentasDelServicioNewVentaToAttach : listaDeVentasDelServicioNew) {
                listaDeVentasDelServicioNewVentaToAttach = em.getReference(listaDeVentasDelServicioNewVentaToAttach.getClass(), listaDeVentasDelServicioNewVentaToAttach.getNroVenta());
                attachedListaDeVentasDelServicioNew.add(listaDeVentasDelServicioNewVentaToAttach);
            }
            listaDeVentasDelServicioNew = attachedListaDeVentasDelServicioNew;
            servicio.setListaDeVentasDelServicio(listaDeVentasDelServicioNew);
            List<Paquete> attachedPaquetesConElServicioNew = new ArrayList<Paquete>();
            for (Paquete paquetesConElServicioNewPaqueteToAttach : paquetesConElServicioNew) {
                paquetesConElServicioNewPaqueteToAttach = em.getReference(paquetesConElServicioNewPaqueteToAttach.getClass(), paquetesConElServicioNewPaqueteToAttach.getCodigoPaquete());
                attachedPaquetesConElServicioNew.add(paquetesConElServicioNewPaqueteToAttach);
            }
            paquetesConElServicioNew = attachedPaquetesConElServicioNew;
            servicio.setPaquetesConElServicio(paquetesConElServicioNew);
            servicio = em.merge(servicio);
            for (Venta listaDeVentasDelServicioOldVenta : listaDeVentasDelServicioOld) {
                if (!listaDeVentasDelServicioNew.contains(listaDeVentasDelServicioOldVenta)) {
                    listaDeVentasDelServicioOldVenta.setServicio(null);
                    listaDeVentasDelServicioOldVenta = em.merge(listaDeVentasDelServicioOldVenta);
                }
            }
            for (Venta listaDeVentasDelServicioNewVenta : listaDeVentasDelServicioNew) {
                if (!listaDeVentasDelServicioOld.contains(listaDeVentasDelServicioNewVenta)) {
                    Servicio oldServicioOfListaDeVentasDelServicioNewVenta = listaDeVentasDelServicioNewVenta.getServicio();
                    listaDeVentasDelServicioNewVenta.setServicio(servicio);
                    listaDeVentasDelServicioNewVenta = em.merge(listaDeVentasDelServicioNewVenta);
                    if (oldServicioOfListaDeVentasDelServicioNewVenta != null && !oldServicioOfListaDeVentasDelServicioNewVenta.equals(servicio)) {
                        oldServicioOfListaDeVentasDelServicioNewVenta.getListaDeVentasDelServicio().remove(listaDeVentasDelServicioNewVenta);
                        oldServicioOfListaDeVentasDelServicioNewVenta = em.merge(oldServicioOfListaDeVentasDelServicioNewVenta);
                    }
                }
            }
            for (Paquete paquetesConElServicioOldPaquete : paquetesConElServicioOld) {
                if (!paquetesConElServicioNew.contains(paquetesConElServicioOldPaquete)) {
                    paquetesConElServicioOldPaquete.getListaServiciosPorPaquete().remove(servicio);
                    paquetesConElServicioOldPaquete = em.merge(paquetesConElServicioOldPaquete);
                }
            }
            for (Paquete paquetesConElServicioNewPaquete : paquetesConElServicioNew) {
                if (!paquetesConElServicioOld.contains(paquetesConElServicioNewPaquete)) {
                    paquetesConElServicioNewPaquete.getListaServiciosPorPaquete().add(servicio);
                    paquetesConElServicioNewPaquete = em.merge(paquetesConElServicioNewPaquete);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = servicio.getCodigoServicio();
                if (findServicio(id) == null) {
                    throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.");
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
            Servicio servicio;
            try {
                servicio = em.getReference(Servicio.class, id);
                servicio.getCodigoServicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.", enfe);
            }
            List<Venta> listaDeVentasDelServicio = servicio.getListaDeVentasDelServicio();
            for (Venta listaDeVentasDelServicioVenta : listaDeVentasDelServicio) {
                listaDeVentasDelServicioVenta.setServicio(null);
                listaDeVentasDelServicioVenta = em.merge(listaDeVentasDelServicioVenta);
            }
            List<Paquete> paquetesConElServicio = servicio.getPaquetesConElServicio();
            for (Paquete paquetesConElServicioPaquete : paquetesConElServicio) {
                paquetesConElServicioPaquete.getListaServiciosPorPaquete().remove(servicio);
                paquetesConElServicioPaquete = em.merge(paquetesConElServicioPaquete);
            }
            em.remove(servicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicio> findServicioEntities() {
        return findServicioEntities(true, -1, -1);
    }

    public List<Servicio> findServicioEntities(int maxResults, int firstResult) {
        return findServicioEntities(false, maxResults, firstResult);
    }

    private List<Servicio> findServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicio.class));
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

    public Servicio findServicio(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicio> rt = cq.from(Servicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
