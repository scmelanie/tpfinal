package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Venta;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-17T17:21:49")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, String> emailCliente;
    public static volatile SingularAttribute<Cliente, Integer> idCliente;
    public static volatile SingularAttribute<Cliente, String> nombreCliente;
    public static volatile SingularAttribute<Cliente, String> nacionalidadCliente;
    public static volatile ListAttribute<Cliente, Venta> listaDeCompras;
    public static volatile SingularAttribute<Cliente, Date> fechaNacCliente;
    public static volatile SingularAttribute<Cliente, String> apellidoCliente;
    public static volatile SingularAttribute<Cliente, String> direccionCliente;
    public static volatile SingularAttribute<Cliente, String> celularCliente;
    public static volatile SingularAttribute<Cliente, String> dniCliente;

}