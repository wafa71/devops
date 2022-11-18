//package tn.esprit.rh.achat;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import tn.esprit.rh.achat.entities.CategorieFournisseur;
//import tn.esprit.rh.achat.entities.Fournisseur;
//import tn.esprit.rh.achat.entities.SecteurActivite;
//import tn.esprit.rh.achat.services.IFournisseurService;
//import tn.esprit.rh.achat.services.ISecteurActiviteService;
//
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class FournisseurServiceImplTest  {
//    @Autowired
//    IFournisseurService fournisseurService;
//    Fournisseur addF(){
//        return fournisseurService.addFournisseur(Fournisseur
//                .builder().categorieFournisseur(CategorieFournisseur.CONVENTIONNE).code("hhhh").libelle("ddddd").build());
//    }
//    @Order(1)
//    @Test
//    public void retrieveAllFournisseursTest() {
//    int size = fournisseurService.retrieveAllFournisseurs().size();
//    addF();
//    int size1=fournisseurService.retrieveAllFournisseurs().size();
//        Assertions.assertEquals(size1,size+1);
//    }
//
//    @Order(2)
//    @Test
//    public void addFournisseurTest() {
//       Fournisseur f=addF();
//       Assertions.assertNotNull(f);
//    }
//
//    @Order(4)
//    @Test
//    public void deleteFournisseurTest() {
//     Fournisseur f=   addF();
//    fournisseurService.deleteFournisseur(f.getIdFournisseur());
//    Assertions.assertNull(fournisseurService.retrieveFournisseur(f.getIdFournisseur()));
//    }
//
//    @Order(5)
//    @Test
//    public void updateFournisseurTest() {
//    Fournisseur f = addF();
//        f.setCode("testupdate");
//        f.setLibelle("libelle update");
//        Fournisseur code= fournisseurService.updateFournisseur(f);
//        Assertions.assertEquals("testupdate",code.getCode());
//        Assertions.assertEquals("libelle update",code.getLibelle());
//
//    }
//    @Order(3)
//    @Test
//    public void retrieveFournisseurTest() {
//        Fournisseur f=   addF();
//        Assertions.assertEquals(f.getIdFournisseur(),fournisseurService.retrieveFournisseur(f.getIdFournisseur()).getIdFournisseur());
//    }
//    @Autowired
//    ISecteurActiviteService secteurActiviteService ;
//
//    public SecteurActivite addS(){
//        SecteurActivite secteurActivite1 = new SecteurActivite();
//        secteurActivite1.setCodeSecteurActivite("test");
//        secteurActivite1.setLibelleSecteurActivite("hhhh");
//        SecteurActivite secteurActivite=secteurActiviteService.addSecteurActivite(secteurActivite1);
//        return  secteurActivite ;
//
//    }
//    @Order(6)
//    @Test
//    public void assignSecteurActiviteToFournisseurTest() {
//   Fournisseur f1 = addF() ;
//// (cascade=CascadeType.ALL, fetch=FetchType.EAGER) LazyInitializationException
//   fournisseurService.assignSecteurActiviteToFournisseur(addS().getIdSecteurActivite(),f1.getIdFournisseur() );
//    Assertions.assertNotNull(fournisseurService.retrieveFournisseur(f1.getIdFournisseur()).getSecteurActivites());
//    }
//}
