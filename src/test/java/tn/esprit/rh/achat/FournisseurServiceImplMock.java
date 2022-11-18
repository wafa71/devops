package tn.esprit.rh.achat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.CategorieFournisseur;
import tn.esprit.rh.achat.entities.DetailFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.DetailFournisseurRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class FournisseurServiceImplMock {
    @Mock
    FournisseurRepository fournisseurRepository;
    @InjectMocks
    FournisseurServiceImpl fournisseurService ;

    Fournisseur fournisseur = Fournisseur.builder().categorieFournisseur(CategorieFournisseur.CONVENTIONNE).code("code1").libelle("libelle1").build();
    DetailFournisseur detailFournisseur = new DetailFournisseur(1L,"test@gmail.com", new Date(),"hhhhhh","hhhhhhh",this.fournisseur);

    List<Fournisseur> fournisseurList = new ArrayList<Fournisseur>(){
    {
        add(Fournisseur.builder().categorieFournisseur(CategorieFournisseur.CONVENTIONNE).code("code1").libelle("libelle1").build());
        add(Fournisseur.builder().categorieFournisseur(CategorieFournisseur.ORDINAIRE).code("code2").libelle("libelle2").build());
    }};

    @Test
    public void retrieveAllFournisseursTest() {
        Mockito.when(fournisseurRepository.findAll()).thenReturn(fournisseurList);
        List<Fournisseur> lst = fournisseurService.retrieveAllFournisseurs();
        Assertions.assertNotNull(lst);

    }

     @Test
    public void addFournisseurTest() {
         Mockito.when(fournisseurRepository.save(Mockito.any(Fournisseur.class))).thenReturn(fournisseur);
         Fournisseur fournisseur1 = fournisseurService.addFournisseur(Fournisseur.builder().libelle("cggcg").code("code3").build());
         Assertions.assertNotNull(fournisseur1);
     }

      @Test
    public void deleteFournisseurTest() {
          Mockito.when(fournisseurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(fournisseur));
          fournisseurService.deleteFournisseur(1L);
          Mockito.when(fournisseurRepository.findById(Mockito.anyLong())).thenReturn(null);

          Assertions.assertNull(fournisseurRepository.findById(1L));

      }
      DetailFournisseurRepository detailFournisseurRepository = Mockito.mock(DetailFournisseurRepository.class);

    @Test
    public void updateFournisseurTest() {
        fournisseur.setDetailFournisseur(detailFournisseur);
        Mockito.when(fournisseurRepository.save(Mockito.any(Fournisseur.class))).thenReturn(fournisseur);
        Mockito.when(detailFournisseurRepository.save(Mockito.any(DetailFournisseur.class))).thenReturn(detailFournisseur);
        Fournisseur fournisseurRes= fournisseurService.updateFournisseur(fournisseur);
        Assertions.assertEquals(fournisseurRes.getIdFournisseur(),fournisseur.getIdFournisseur());

    }
    @Test
    public void retrieveFournisseurTest() {
        Mockito.when(fournisseurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(fournisseur));
        Fournisseur fournisseur1= fournisseurService.retrieveFournisseur(1L);
        Assertions.assertNotNull(fournisseur1);

    }
    @Mock
    SecteurActiviteRepository secteurActiviteRepository ;
    @Test
    public void assignSecteurActiviteToFournisseurTest() {
    fournisseur.setIdFournisseur(2L);
    fournisseur.setSecteurActivites(new HashSet<>());
    SecteurActivite secteurActivite1 = new SecteurActivite();
    secteurActivite1.setIdSecteurActivite(1L);
    secteurActivite1.setCodeSecteurActivite("test");
    secteurActivite1.setLibelleSecteurActivite("hhhh");
     Mockito.when(fournisseurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(fournisseur));
     Mockito.when(secteurActiviteRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(secteurActivite1));
     Mockito.when(fournisseurRepository.save(Mockito.any(Fournisseur.class))).thenReturn(fournisseur);
     fournisseurService.assignSecteurActiviteToFournisseur(secteurActivite1.getIdSecteurActivite(),fournisseur.getIdFournisseur() );
      Assertions.assertNotNull(fournisseurService.retrieveFournisseur(fournisseur.getIdFournisseur()).getSecteurActivites());


    }

}
