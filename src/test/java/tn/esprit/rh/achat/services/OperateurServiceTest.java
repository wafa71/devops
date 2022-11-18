package tn.esprit.rh.achat.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;


@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class OperateurServiceTest {

	@MockBean
	private OperateurRepository operateurRepository;

	  
	@Autowired
	IOperateurService operateurService;
	
    
    @Test
	@Order(1)
	public void addOperateurTest() {
		Operateur operateur1 = new Operateur(null, "Omar","Bnr","123456", null);
    	when(operateurRepository.save(operateur1)).thenReturn(operateur1);
    	assertNotNull(operateur1);
    	
    	Operateur persisted = operateurService.addOperateur(operateur1);
		assertEquals(operateur1, persisted);

		Operateur operateur2 = new Operateur(null, "Flen","Fouleni","123456", null);
		when(operateurRepository.save(operateur2)).thenReturn(operateur2);
		assertNotNull(operateur2);

		Operateur persisted2 = operateurService.addOperateur(operateur2);
		assertEquals(operateur2, persisted2);

		System.out.println("Add operator works !");
	}
    
    @Test
	@Order(2)
    public void retrieveAllOperateursTest() {
		Operateur operateur1 = new Operateur(null, "Omar","Bnr","123456", null);
		Operateur operateur2 = new Operateur(null, "Flen","Fouleni","123456", null);
    	when(operateurRepository.findAll()).thenReturn(Stream
    			.of(operateur1,operateur2)
    			.collect(Collectors.toList()));
    	
    	assertEquals(2,operateurService.retrieveAllOperateurs().size());
    	System.out.println("Retrieve operators works !");
    }
    
   
    
    
    @Test
	@Order(3)
    public void UpdateOperateurTest() {

		Operateur operateur1 = operateurRepository.findById(1L).get();
    	when(operateurRepository.save(operateur1)).thenReturn(operateur1);
    	assertNotNull(operateur1);
    	assertEquals(operateur1, operateurService.updateOperateur(operateur1));
    	System.out.println("Update operator works !");
    }
    
    @Test
	@Order(4)
    public void retrieveOperateurTest() {
		Operateur operateur1 = new Operateur(null, "Omar","Bnr","123456", null);
    	when(operateurRepository.findById(1L)).thenReturn(Optional.of(operateur1));
    	assertEquals(operateur1, operateurService.retrieveOperateur(operateur1.getIdOperateur()));
    	System.out.println("Retrieve operator works !");
    }
}
