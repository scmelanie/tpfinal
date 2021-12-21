
package persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Paquete;
import logica.Servicio;
import logica.Cliente;
import logica.Usuario;
import logica.Venta;
import persistencia.exceptions.NonexistentEntityException;

public class VentaJpaController implements Serializable {

    public VentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    //CONSTRUCTOR CREADO
    public VentaJpaController() {
        emf = Persistence.createEntityManagerFactory("TP_FINALPU");
    }
    
    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
  
    public void create(Venta venta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paquete paquete = venta.getPaquete();
            if (paquete != null) {
                paquete = em.getReference(paquete.getClass(), paquete.getCodigoPaquete());
                venta.setPaquete(paquete);
            }
            Servicio servicio = venta.getServicio();
            if (servicio != null) {
                servicio = em.getReference(servicio.getClass(), servicio.getCodigoServicio());
                venta.setServicio(servicio);
            }
            Cliente cliente = venta.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getIdCliente());
                venta.setCliente(cliente);
            }
            Usuario usuario = venta.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getIdUsuario());
                venta.setUsuario(usuario);
            }
            em.persist(venta);
            if (paquete != null) {
                paquete.getListaDeVentasDePaquetes().add(venta);
                paquete = em.merge(paquete);
            }
            if (servicio != null) {
                servicio.getListaDeVentasDelServicio().add(venta);
                servicio = em.merge(servicio);
            }
            if (cliente != null) {
                cliente.getListaDeCompras().add(venta);
                cliente = em.merge(cliente);
            }
            if (usuario != null) {
                usuario.getListadeVentas().add(venta);
                usuario = em.merge(usuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Venta venta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta persistentVenta = em.find(Venta.class, venta.getNroVenta());
            Paquete paqueteOld = persistentVenta.getPaquete();
            Paquete paqueteNew = venta.getPaquete();
            Servicio servicioOld = persistentVenta.getServicio();
            Servicio servicioNew = venta.getServicio();
            Cliente clienteOld = persistentVenta.getCliente();
            Cliente clienteNew = venta.getCliente();
            Usuario usuarioOld = persistentVenta.getUsuario();
            Usuario usuarioNew = venta.getUsuario();
            if (paqueteNew != null) {
                paqueteNew = em.getReference(paqueteNew.getClass(), paqueteNew.getCodigoPaquete());
                venta.setPaquete(paqueteNew);
            }
            if (servicioNew != null) {
                servicioNew = em.getReference(servicioNew.getClass(), servicioNew.getCodigoServicio());
                venta.setServicio(servicioNew);
            }
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getIdCliente());
                venta.setCliente(clienteNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getIdUsuario());
                venta.setUsuario(usuarioNew);
            }
            venta = em.merge(venta);
            if (paqueteOld != null && !paqueteOld.equals(paqueteNew)) {
                paqueteOld.getListaDeVentasDePaquetes().remove(venta);
                paqueteOld = em.merge(paqueteOld);
            }
            if (paqueteNew != null && !paqueteNew.equals(paqueteOld)) {
                paqueteNew.getListaDeVentasDePaquetes().add(venta);
                paqueteNew = em.merge(paqueteNew);
            }
            if (servicioOld != null && !servicioOld.equals(servicioNew)) {
                servicioOld.getListaDeVentasDelServicio().remove(venta);
                servicioOld = em.merge(servicioOld);
            }
            if (servicioNew != null && !servicioNew.equals(servicioOld)) {
                servicioNew.getListaDeVentasDelServicio().add(venta);
                servicioNew = em.merge(servicioNew);
            }
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getListaDeCompras().remove(venta);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getListaDeCompras().add(venta);
                clienteNew = em.merge(clienteNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getListadeVentas().remove(venta);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getListadeVentas().add(venta);
                usuarioNew = em.merge(usuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = venta.getNroVenta();
                if (findVenta(id) == null) {
                    throw new NonexistentEntityException("The venta with id " + id + " no longer exists.");
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
            Venta venta;
            try {
                venta = em.getReference(Venta.class, id);
                venta.getNroVenta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venta with id " + id + " no longer exists.", enfe);
            }
            Paquete paquete = venta.getPaquete();
            if (paquete != null) {
                paquete.getListaDeVentasDePaquetes().remove(venta);
                paquete = em.merge(paquete);
            }
            Servicio servicio = venta.getServicio();
            if (servicio != null) {
                servicio.getListaDeVentasDelServicio().remove(venta);
                servicio = em.merge(servicio);
            }
            Cliente cliente = venta.getCliente();
            if (cliente != null) {
                cliente.getListaDeCompras().remove(venta);
                cliente = em.merge(cliente);
            }
            Usuario usuario = venta.getUsuario();
            if (usuario != null) {
                usuario.getListadeVentas().remove(venta);
                usuario = em.merge(usuario);
            }
            em.remove(venta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venta> findVentaEntities() {
        return findVentaEntities(true, -1, -1);
    }

    public List<Venta> findVentaEntities(int maxResults, int firstResult) {
        return findVentaEntities(false, maxResults, firstResult);
    }

    private List<Venta> findVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venta.class));
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

    public Venta findVenta(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venta.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venta> rt = cq.from(Venta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
