
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
import logica.Cliente;
import persistencia.exceptions.NonexistentEntityException;

public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    
    //CONSTRUCTOR CREADO
    public ClienteJpaController() {
        emf = Persistence.createEntityManagerFactory("TP_FINALPU");
    }
    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getListaDeCompras() == null) {
            cliente.setListaDeCompras(new ArrayList<Venta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Venta> attachedListaDeCompras = new ArrayList<Venta>();
            for (Venta listaDeComprasVentaToAttach : cliente.getListaDeCompras()) {
                listaDeComprasVentaToAttach = em.getReference(listaDeComprasVentaToAttach.getClass(), listaDeComprasVentaToAttach.getNroVenta());
                attachedListaDeCompras.add(listaDeComprasVentaToAttach);
            }
            cliente.setListaDeCompras(attachedListaDeCompras);
            em.persist(cliente);
            for (Venta listaDeComprasVenta : cliente.getListaDeCompras()) {
                Cliente oldClienteOfListaDeComprasVenta = listaDeComprasVenta.getCliente();
                listaDeComprasVenta.setCliente(cliente);
                listaDeComprasVenta = em.merge(listaDeComprasVenta);
                if (oldClienteOfListaDeComprasVenta != null) {
                    oldClienteOfListaDeComprasVenta.getListaDeCompras().remove(listaDeComprasVenta);
                    oldClienteOfListaDeComprasVenta = em.merge(oldClienteOfListaDeComprasVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdCliente());
            List<Venta> listaDeComprasOld = persistentCliente.getListaDeCompras();
            List<Venta> listaDeComprasNew = cliente.getListaDeCompras();
            List<Venta> attachedListaDeComprasNew = new ArrayList<Venta>();
            for (Venta listaDeComprasNewVentaToAttach : listaDeComprasNew) {
                listaDeComprasNewVentaToAttach = em.getReference(listaDeComprasNewVentaToAttach.getClass(), listaDeComprasNewVentaToAttach.getNroVenta());
                attachedListaDeComprasNew.add(listaDeComprasNewVentaToAttach);
            }
            listaDeComprasNew = attachedListaDeComprasNew;
            cliente.setListaDeCompras(listaDeComprasNew);
            cliente = em.merge(cliente);
            for (Venta listaDeComprasOldVenta : listaDeComprasOld) {
                if (!listaDeComprasNew.contains(listaDeComprasOldVenta)) {
                    listaDeComprasOldVenta.setCliente(null);
                    listaDeComprasOldVenta = em.merge(listaDeComprasOldVenta);
                }
            }
            for (Venta listaDeComprasNewVenta : listaDeComprasNew) {
                if (!listaDeComprasOld.contains(listaDeComprasNewVenta)) {
                    Cliente oldClienteOfListaDeComprasNewVenta = listaDeComprasNewVenta.getCliente();
                    listaDeComprasNewVenta.setCliente(cliente);
                    listaDeComprasNewVenta = em.merge(listaDeComprasNewVenta);
                    if (oldClienteOfListaDeComprasNewVenta != null && !oldClienteOfListaDeComprasNewVenta.equals(cliente)) {
                        oldClienteOfListaDeComprasNewVenta.getListaDeCompras().remove(listaDeComprasNewVenta);
                        oldClienteOfListaDeComprasNewVenta = em.merge(oldClienteOfListaDeComprasNewVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cliente.getIdCliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<Venta> listaDeCompras = cliente.getListaDeCompras();
            for (Venta listaDeComprasVenta : listaDeCompras) {
                listaDeComprasVenta.setCliente(null);
                listaDeComprasVenta = em.merge(listaDeComprasVenta);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
