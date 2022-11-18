package tn.esprit.rh.achat.services;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.rh.achat.entities.SecteurActivite;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
public class SecteurActiviteServiceImplTest {

	@Autowired
	ISecteurActiviteService secteurService;


	@Test
	@Order(1)
	public void testAddSecteur() {
		SecteurActivite sa = new SecteurActivite();
		sa.setCodeSecteurActivite("test");
		sa.setFournisseurs(null);
		sa.setLibelleSecteurActivite("test");
		SecteurActivite savedSecteur = secteurService.addSecteurActivite(sa);
		assertEquals(sa.getLibelleSecteurActivite(), savedSecteur.getLibelleSecteurActivite());
	}

	@Test
	@Order(2)
	public List<SecteurActivite> testRetrieveAllStocks() {
		List<SecteurActivite> allSActivite = secteurService.retrieveAllSecteurActivite();
		assertEquals(0, allSActivite.size());
		return allSActivite;
	}


	@Test
	@Order(3)
	public void testDeleteStock() {
		secteurService.deleteSecteurActivite(secteurService.retrieveAllSecteurActivite().get(0).getIdSecteurActivite());
	}
}
