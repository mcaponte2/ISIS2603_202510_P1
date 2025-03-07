package co.edu.uniandes.dse.parcial1.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcial1.entities.ConciertoEntity;
import co.edu.uniandes.dse.parcial1.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.ConciertoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConciertoService {

    @Autowired
    ConciertoRepository conciertoRepository;

    @Transactional
    public ConciertoEntity createConcierto(ConciertoEntity concierto) throws EntityNotFoundException, IllegalOperationException{
        log.info("Inicia proceso de creación de concierto");
        if(concierto.getFecha().compareTo(LocalDateTime.now())<0) 
            throw new IllegalOperationException("Fecha del concierto es en el pasado");
        
        if (concierto.getAforo()<10)
            throw new IllegalOperationException("La capacidad del concierto debe ser un número mayor a 10 personas");

        if(concierto.getPresupuesto()<1000)
            throw new IllegalOperationException("El presupuesto del concierto debe ser un número mayor a 1000 dolares");

        log.info("Termina proceso de creación de un concierto");
        return conciertoRepository.save(concierto);
        }
    
    
    
    
    }



