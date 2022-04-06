package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
@Slf4j
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	public int ajouterEntreprise(Entreprise entreprise) {
		log.info("Ajouter l'entreprise");
		entrepriseRepoistory.save(entreprise);
		log.info("L'entreprise " + entreprise.getName()+"avec ID "  + entreprise.getId()+ "a été ajoutée avec succés");
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		log.info("Ajouter le departement");
		deptRepoistory.save(dep);
		log.info("Le departement " +dep.getName()+"avec ID "  + dep.getId()+ "a été ajouté avec succés");
		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		log.info("Affecter Departement a Entreprise");
		    try{
				Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
				Departement depManagedEntity = deptRepoistory.findById(depId).get();
				
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);
	           }
				catch (Exception ex) {
					log.error("L'affectation a été échoué");
				}
	       }
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		log.info("Departements by entreprise");
		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
		List<String> depNames = new ArrayList<>();
		for(Departement dep : entrepriseManagedEntity.getDepartements()){
			depNames.add(dep.getName());
		}
		
		return depNames;
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		log.info("delete entreprise by Id="+ entrepriseId);
		try
		 {
				entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).get());	
		 } catch (Exception ex) {
				log.error("cannot delete entreprise by Id="+ entrepriseId);
			}
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		log.info("delete departement by Id="+ depId);
		try
		 {			
		deptRepoistory.delete(deptRepoistory.findById(depId).get());	
		 }
		catch (Exception ex) {
			log.error("cannot delete departement by Id="+ depId);
		}
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		log.info("Get Entreprise");
		return entrepriseRepoistory.findById(entrepriseId).get();
		
	}

}
