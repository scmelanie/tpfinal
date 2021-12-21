package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.Servicio;
import logica.Venta;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-17T17:21:49")
@StaticMetamodel(Paquete.class)
public class Paquete_ { 

    public static volatile SingularAttribute<Paquete, Integer> codigoPaquete;
    public static volatile ListAttribute<Paquete, Venta> listaDeVentasDelPaquete;
    public static volatile SingularAttribute<Paquete, Double> costoPaquete;
    public static volatile ListAttribute<Paquete, Servicio> listaServiciosPorPaquete;

}