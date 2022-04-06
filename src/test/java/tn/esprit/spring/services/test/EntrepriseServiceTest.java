package tn.esprit.spring.services.test;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEntrepriseService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EntrepriseServiceTest {
	@Autowired
	private IEntrepriseService entrepriseService;
	private Employe employe;
	private Departement department;
	private Entreprise entreprise;
	

	@Before
	public void ajoutEntreprise() {
		entreprise = new Entreprise();
		entreprise.setId(1);
		entreprise.setName("Vermeg");
		entreprise.setRaisonSocial("SARL");
	}
	@Test
	public void checkEntrepriseId() {
		log.info("ID de l'entreprise");
		Assert.assertEquals(1, entreprise.getId());
	}

	@Test
	public void checkEntrepriseNom() {
		log.info("Nom de l'entreprise");
		Assert.assertEquals("Vermeg", entreprise.getName());
	}
	@Test
	public void checkEntrepriseRaisonSocial() {
		log.info("Raison social de l'entreprise");
		Assert.assertEquals("SARL", entreprise.getRaisonSocial());
	}
	
	@Test
	public void checkEntrepriseName() {
		log.info("Nom de l'entreprise");
		Assert.assertFalse(entreprise.getName().equals("Sofrecom"));
	}
	@Test
	public void checkEntrepriseRaison() {
		log.info("Nom de l'entreprise");
		Assert.assertFalse(entreprise.getRaisonSocial().equals("SNC"));
	}
	@Before
	public void ajoutDepartement() {
		department = new Departement();
		department.setId(1);
		department.setName("Megara");
		department.setEntreprise(entreprise);
	}
	@Test
	public void DepartmentNom() {
		log.info("Departement name");
		Assert.assertEquals("Megara", department.getName());
	}
	@Test
	public void DepartmentName() {
		log.info("Nom de l'entreprise");
		Assert.assertFalse(department.getEntreprise().equals("Sofrecom"));
	}


}