package co.edu.uniandes.dse.parcial1.services;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import co.edu.uniandes.dse.parcial1.entities.EstadioEntity;
import co.edu.uniandes.dse.parcial1.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcial1.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcial1.services.EstadioService;
import jakarta.transaction.Transactional;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


@DataJpaTest
@Transactional
@Import(EstadioService.class)
public class EstadioServiceTest {
    @Autowired
    private EstadioService estadioService;

    @Autowired
    private TestEntityManager entityManager;

    private PodamFactory factory = new PodamFactoryImpl();

	private List<EstadioEntity> conciertoList = new ArrayList<>();

    @BeforeEach
	void setUp() {
		clearData();
		insertData();
	}

	private void clearData() {
		entityManager.getEntityManager().createQuery("delete from EstadioEntity");
	}

    private void insertData() {

		for (int i = 0; i < 3; i++) {
			EstadioEntity estadioEntity = factory.manufacturePojo(EstadioEntity.class);
			entityManager.persist(estadioEntity);
			conciertoList.add(estadioEntity);
		}
	}

    @Test
	void testCreateEstadio() throws IllegalOperationException {
		EstadioEntity newEntity = factory.manufacturePojo(EstadioEntity.class);
		EstadioEntity result = estadioService.createEstadio(newEntity);
		assertNotNull(result);

		EstadioEntity entity = entityManager.find(EstadioEntity.class, result.getId());
		assertEquals(newEntity.getId(), entity.getId());
	}


}