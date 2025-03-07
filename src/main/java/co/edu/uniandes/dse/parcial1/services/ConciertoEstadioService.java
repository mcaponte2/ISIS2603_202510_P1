package co.edu.uniandes.dse.parcial1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcial1.entities.ConciertoEntity;
import co.edu.uniandes.dse.parcial1.entities.EstadioEntity;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.ConciertoRepository;
import co.edu.uniandes.dse.parcial1.repositories.EstadioRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConciertoEstadioService {

    @Autowired
    private ConciertoRepository conciertoRepository;

    @Autowired
    private EstadioRepository estadioRepository;


    @Transactional
    public ConciertoEntity addConcierto(Long conciertoId, Long estadioId) throws IllegalOperationException{
        log.info("Inicia el proceso de crear la asociacion entre concierto y estadio");
        Optional<ConciertoEntity> conciertoEntity= conciertoRepository.findById(conciertoId);

        Optional<EstadioEntity> estadioEntity = estadioRepository.findById(estadioId);

        if(conciertoEntity.get().getAforo()>estadioEntity.get().getCapacidadMaxima())
            throw new IllegalOperationException("La capacidad de un concierto no debe superar la capacidad del estadio");

        if(estadioEntity.get().getPrecioAlquiler()> conciertoEntity.get().getPresupuesto())
            throw new IllegalOperationException("El precio de alquiler del estadio no debe superar el presupuesto del concierto.");
        //for (ConciertoEntity concierto: conciertoEntity){
        //    Optional<ConciertoEntity> c= conciertoRepository.findById(concierto.getId()));
        //    if(Duration.between(c.get().getFecha(), c.get().getFecha())<2 || Duration.between(c.get().getFecha(), c.get().getFecha()) > -2)
        //        throw new IllegalOperationException("Tienen que haber mínimo 2 dias entre conciertos")
        //}
     
    return conciertoEntity.get();       
    }
}
