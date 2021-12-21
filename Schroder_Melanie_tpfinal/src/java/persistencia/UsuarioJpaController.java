
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import logica.Empleado;
import logica.Venta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import logica.Usuario;
import persistencia.exceptions.NonexistentEntityException;

public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    
    //CONSTRUCTOR CREADO
    public UsuarioJpaController() {
        emf = Persistence.createEntityManagerFactory("TP_FINALPU");
    }
    
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getListadeVentas() == null) {
            usuario.setListadeVentas(new ArrayList<Venta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleado = usuario.getEmpleado();
            if (empleado != null) {
                empleado = em.getReference(empleado.getClass(), empleado.getIdEmpleado());
                usuario.setEmpleado(empleado);
            }
            List<Venta> attachedListadeVentas = new ArrayList<Venta>();
            for (Venta listadeVentasVentaToAttach : usuario.getListadeVentas()) {
                listadeVentasVentaToAttach = em.getReference(listadeVentasVentaToAttach.getClass(), listadeVentasVentaToAttach.getNroVenta());
                attachedListadeVentas.add(listadeVentasVentaToAttach);
            }
            usuario.setListadeVentas(attachedListadeVentas);
            em.persist(usuario);
            if (empleado != null) {
                Usuario oldUsuarioOfEmpleado = empleado.getUsuario();
                if (oldUsuarioOfEmpleado != null) {
                    oldUsuarioOfEmpleado.setEmpleado(null);
                    oldUsuarioOfEmpleado = em.merge(oldUsuarioOfEmpleado);
                }
                empleado.setUsuario(usuario);
                empleado = em.merge(empleado);
            }
            for (Venta listadeVentasVenta : usuario.getListadeVentas()) {
                Usuario oldUsuarioOfListadeVentasVenta = listadeVentasVenta.getUsuario();
                listadeVentasVenta.setUsuario(usuario);
                listadeVentasVenta = em.merge(listadeVentasVenta);
                if (oldUsuarioOfListadeVentasVenta != null) {
                    oldUsuarioOfListadeVentasVenta.getListadeVentas().remove(listadeVentasVenta);
                    oldUsuarioOfListadeVentasVenta = em.merge(oldUsuarioOfListadeVentasVenta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
            Empleado empleadoOld = persistentUsuario.getEmpleado();
            Empleado empleadoNew = usuario.getEmpleado();
            List<Venta> listadeVentasOld = persistentUsuario.getListadeVentas();
            List<Venta> listadeVentasNew = usuario.getListadeVentas();
            if (empleadoNew != null) {
                empleadoNew = em.getReference(empleadoNew.getClass(), empleadoNew.getIdEmpleado());
                usuario.setEmpleado(empleadoNew);
            }
            List<Venta> attachedListadeVentasNew = new ArrayList<Venta>();
            for (Venta listadeVentasNewVentaToAttach : listadeVentasNew) {
                listadeVentasNewVentaToAttach = em.getReference(listadeVentasNewVentaToAttach.getClass(), listadeVentasNewVentaToAttach.getNroVenta());
                attachedListadeVentasNew.add(listadeVentasNewVentaToAttach);
            }
            listadeVentasNew = attachedListadeVentasNew;
            usuario.setListadeVentas(listadeVentasNew);
            usuario = em.merge(usuario);
            if (empleadoOld != null && !empleadoOld.equals(empleadoNew)) {
                empleadoOld.setUsuario(null);
                empleadoOld = em.merge(empleadoOld);
            }
            if (empleadoNew != null && !empleadoNew.equals(empleadoOld)) {
                Usuario oldUsuarioOfEmpleado = empleadoNew.getUsuario();
                if (oldUsuarioOfEmpleado != null) {
                    oldUsuarioOfEmpleado.setEmpleado(null);
                    oldUsuarioOfEmpleado = em.merge(oldUsuarioOfEmpleado);
                }
                empleadoNew.setUsuario(usuario);
                empleadoNew = em.merge(empleadoNew);
            }
            for (Venta listadeVentasOldVenta : listadeVentasOld) {
                if (!listadeVentasNew.contains(listadeVentasOldVenta)) {
                    listadeVentasOldVenta.setUsuario(null);
                    listadeVentasOldVenta = em.merge(listadeVentasOldVenta);
                }
            }
            for (Venta listadeVentasNewVenta : listadeVentasNew) {
                if (!listadeVentasOld.contains(listadeVentasNewVenta)) {
                    Usuario oldUsuarioOfListadeVentasNewVenta = listadeVentasNewVenta.getUsuario();
                    listadeVentasNewVenta.setUsuario(usuario);
                    listadeVentasNewVenta = em.merge(listadeVentasNewVenta);
                    if (oldUsuarioOfListadeVentasNewVenta != null && !oldUsuarioOfListadeVentasNewVenta.equals(usuario)) {
                        oldUsuarioOfListadeVentasNewVenta.getListadeVentas().remove(listadeVentasNewVenta);
                        oldUsuarioOfListadeVentasNewVenta = em.merge(oldUsuarioOfListadeVentasNewVenta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Empleado empleado = usuario.getEmpleado();
            if (empleado != null) {
                empleado.setUsuario(null);
                empleado = em.merge(empleado);
            }
            List<Venta> listadeVentas = usuario.getListadeVentas();
            for (Venta listadeVentasVenta : listadeVentas) {
                listadeVentasVenta.setUsuario(null);
                listadeVentasVenta = em.merge(listadeVentasVenta);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
