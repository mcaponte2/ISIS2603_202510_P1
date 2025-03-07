package co.edu.uniandes.dse.parcial1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.uniandes.dse.parcial1.entities.EstadioEntity;
import co.edu.uniandes.dse.parcial1.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.repositories.EstadioRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EstadioService {

    @Autowired
    EstadioRepository estadioRepository;

    @Transactional
    public EstadioEntity createEstadio(EstadioEntity estadio) throws IllegalOperationException, EntityNotFoundException{
        log.info("Inicia proceso de creación de un estadio");
        if(estadio.getNombreCiudad().length()<3)
            throw new IllegalOperationException("El nombre de la ciudad debe seruna cadena de caracteres de mínimo 3 letras");
        if(estadio.getCapacidadMaxima()<1000)
            throw new IllegalOperationException("La capacidad máxima del estadio debe ser superior a 1000 personas");
        if(estadio.getPrecioAlquiler()<100000)
            throw new IllegalOperationException("El precio del alquiler debe ser mayor a 100000 dólares");
        
        log.info("Termina proceso de creación de un estadio");
        return estadioRepository.save(estadio);
    }

}
