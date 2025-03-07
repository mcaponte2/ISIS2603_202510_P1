package co.edu.uniandes.dse.parcial1.entities;

import java.time.LocalDateTime;

import co.edu.uniandes.dse.parcial1.podam.DateStrategy;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;


@Data
@Entity
public class ConciertoEntity extends BaseEntity {

    private String nombre;
    private Long presupuesto;
    private String nombreArtista;
    private int aforo;
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private LocalDateTime fecha;

    @PodamExclude
    @ManyToOne
    private EstadioEntity estadio;
}
