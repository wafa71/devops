package tn.esprit.rh.achat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.services.FactureServiceImpl;
import tn.esprit.rh.achat.services.IFactureService;
import tn.esprit.rh.achat.services.StockServiceImpl;
import org.mockito.MockitoAnnotations;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class FactureServiceImplTest {
    @Autowired
    IFactureService us;
    @InjectMocks
    static FactureServiceImpl frImpl;
    @Mock
    static FactureRepository fr;
    static List<Facture> facts;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        facts = new ArrayList<>();
        when(fr.findAll()).thenReturn(facts);
    }

    @Test
    @Order(1)
    public void testRetrieveAllFactures() {
        assertTrue(frImpl.retrieveAllFactures().isEmpty());

        facts.add(mock(Facture.class));

        assertFalse(frImpl.retrieveAllFactures().isEmpty());
    }

    @Test
    void testAddFacture() {
        // assertTrue(stockServiceImpl.addStock().isEmpty());
        frImpl.addFacture(mock(Facture.class));

        verify(fr).save(any());

    }

    @Test
    public void testRetrieveUser() {
        Mockito.when(fr.findById(Mockito.anyLong())).thenReturn(Optional.of(mock(Facture.class)));
        Facture fac = fr.findById((long) 1).get();
        Assertions.assertNotNull(fac);
    }
}
