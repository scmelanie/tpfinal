package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Paquete;
import logica.Venta;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-17T17:21:49")
@StaticMetamodel(Servicio.class)
public class Servicio_ { 

    public static volatile SingularAttribute<Servicio, String> nombreServicio;
    public static volatile SingularAttribute<Servicio, String> descripcionServicio;
    public static volatile SingularAttribute<Servicio, Integer> codigoServicio;
    public static volatile ListAttribute<Servicio, Paquete> paquetesConElServicio;
    public static volatile ListAttribute<Servicio, Venta> listaDeVentasDelServicio;
    public static volatile SingularAttribute<Servicio, String> destinoServicio;
    public static volatile SingularAttribute<Servicio, Double> costoServicio;
    public static volatile SingularAttribute<Servicio, Date> fechaServicio;

}