package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;
	
	public Model() {
		corsoDao = new CorsoDAO();
		studenteDao=new StudenteDAO();
	}
	
	public List<Corso> getTuttiICorsi(){
		return corsoDao.getTuttiICorsi();
	}
	
	public List<Studente> getTuttiStudenti(){
		return studenteDao.getTuttiStudenti();
	}
	
	public List<Studente> getStudentiCorso(String cod){
		return studenteDao.getStudentiCorso(cod);
	}
	
	public List<Corso> getCorsiStudente(Integer matricola){
		return corsoDao.getCorsiStudente(matricola);
	}

}