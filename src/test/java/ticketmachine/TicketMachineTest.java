package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
                // Les montants ont été correctement additionnés  
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");              
	}

	// S3 : on n’imprime pas le ticket si le montant inséré est insuffisant
	void NotEnoughMoney() {
		machine.insertMoney(PRICE);
		// Le montant inséré n'est pas suffisant
		assertFalse(machine.printTicket(), "Le montant n'est pas suffisant");
	}

	// S4 : on imprime le ticket si le montant inséré est insuffisant
	void EnoughMoneyTicket() {
		machine.insertMoney(PRICE);
		// Le montant inséré est suffisant
		assertTrue(machine.printTicket(), "Le montant n'est pas suffisant");
	}

	// S5 : Quand on imprime un ticket la balance est décrémentée du prix du ticket
	void balanceUpdate() {
		machine.insertMoney(80);
		machine.printTicket();
		assertEquals(80 - PRICE , machine.getBalance(), "La balance a été décrémentée");
	}

	// S6 : le montant collecté est mis à jour quand on imprime un ticket (pas avant)
	void updatedTotal() {
		machine.insertMoney(60);
		assertEquals(0, machine.getTotal(), "total nul");
		machine.printTicket();
		assertEquals(PRICE , machine.getTotal(), "Le montant a été mis à jour");
	}

	// S7 : refund() rend correctement la monnaie
	void giveMoneyBack() {
		machine.insertMoney(70);
		assertEquals(70-PRICE , machine.refund(), "Le rendu monnaie est bon");
	}

	// S8 : refund() remet la balance à zéro
	void balanceZero() {
		assertEquals(0 , machine.getBalance(), "La balance est remise à zéro");
	}

	// S9 : on ne peut pas insérer un montant négatif
	void noNegative() {
		machine.insertMoney(-30);
		assertEquals(-30 , machine.getBalance(), "Le montant ne peut pas être négatif");
	}


		// S10 : on ne peut pas créer de machine qui délivre des tickets dont le prix est négatif
	void negativeNoTicket(){
		assertThrows(IllegalArgumentException.class, () -> {new TicketMachine(-1);}, "");
	}
}
