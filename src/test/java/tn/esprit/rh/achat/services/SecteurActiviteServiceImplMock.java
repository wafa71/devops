package tn.esprit.rh.achat.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SecteurActiviteServiceImplMock {
	
	@Mock
	SecteurActiviteRepository SecteurRepository;
	
	@InjectMocks
	SecteurActiviteServiceImpl SecteurService;
	
	@Test
	public void testRetrieveSecteur() {

		SecteurActivite secteur = new SecteurActivite();
		secteur.setCodeSecteurActivite("test");
		secteur.setFournisseurs(null);
		secteur.setLibelleSecteurActivite("test");
		secteur.setIdSecteurActivite(SecteurService.retrieveAllSecteurActivite().get(0).getIdSecteurActivite());
		
	Mockito.when(SecteurRepository.findById(SecteurService.retrieveAllSecteurActivite().get(0).getIdSecteurActivite())).thenReturn(Optional.of(secteur));
	SecteurService.retrieveSecteurActivite(SecteurService.retrieveAllSecteurActivite().get(0).getIdSecteurActivite());
	Assertions.assertNotNull(secteur);
	
	System.out.println(secteur); 
	System.out.println(" Retrieve sector works !");
	
	}
	
	
	@Test
	public void createSecteurTest()
	{


		SecteurActivite secteur2 = new SecteurActivite();
		secteur2.setCodeSecteurActivite("test");
		secteur2.setFournisseurs(null);
		secteur2.setLibelleSecteurActivite("test");
		
		SecteurService.addSecteurActivite(secteur2);
		verify(SecteurRepository, times(1)).save(secteur2);
		System.out.println(secteur2); 
		System.out.println(" Add sector works !!");
	}
	@Test
	public void getAllSecteursTest()
	{
		List<SecteurActivite> SecteurList = new ArrayList<SecteurActivite>() {

			{

		SecteurActivite sa = new SecteurActivite();
		sa.setCodeSecteurActivite("test1");
		sa.setFournisseurs(null);
		sa.setLibelleSecteurActivite("salut");


		SecteurActivite sa2 = new SecteurActivite();
		sa.setCodeSecteurActivite("test2");
		sa.setFournisseurs(null);
		sa.setLibelleSecteurActivite("hello");


		SecteurActivite sa3 = new SecteurActivite();
		sa.setCodeSecteurActivite("test3");
		sa.setFournisseurs(null);
		sa.setLibelleSecteurActivite("hi");

		add(sa);
		add(sa2);
		add(sa3);
			}};
			
			
		when(SecteurService.retrieveAllSecteurActivite()).thenReturn(SecteurList);
		//test
		List<SecteurActivite> sList = SecteurService.retrieveAllSecteurActivite();
		assertEquals(3, sList.size());
		System.out.println(" Retrieve all is working correctly...!!");  
	
	}
	
	@Test
	public void TestDeleteSecteur(){

	SecteurActivite secteur1 = new SecteurActivite();
	secteur1.setCodeSecteurActivite("test");
	secteur1.setFournisseurs(null);
	secteur1.setLibelleSecteurActivite("test");

	secteur1.setIdSecteurActivite(SecteurService.retrieveAllSecteurActivite().get(0).getIdSecteurActivite());

	doReturn(Optional.of(secteur1)).when(SecteurRepository.findById(secteur1.getIdSecteurActivite()));

	SecteurService.deleteSecteurActivite(SecteurService.retrieveAllSecteurActivite().get(0).getIdSecteurActivite());
	verify(SecteurRepository).deleteById(secteur1.getIdSecteurActivite());
	
	System.out.println(secteur1);
	System.out.println(" Delete is working correctly...!!");  
	}

}
