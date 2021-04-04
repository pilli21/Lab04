package it.polito.tdp.lab04;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
	List<Corso> corsi=new LinkedList<Corso>();
	List<Studente> studenti=new LinkedList<Studente>();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboboxCorsi;

    @FXML
    private Button btnCercaIscritti;
    
    @FXML
    private Button btnCompleta;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button btnReset;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	int matricola;
    	txtRisultato.clear();
    	List<Corso> risultato=new LinkedList<Corso>();
    	try {
    		matricola=Integer.parseInt(txtMatricola.getText());
    	}
    	catch(NumberFormatException e) {
    		e.printStackTrace();
    		return;
    	}
    	catch(NullPointerException e) {
    		e.printStackTrace();
    		return;
    	}
    	for(Studente s:studenti) {
    		if(s.getMatricola()==matricola) {
    			risultato=model.getCorsiStudente(matricola);
    		}
    	}
    	for(Corso c:corsi) {
			txtRisultato.appendText(c.toString()+"\n");
		}
    	}

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	txtRisultato.clear();
    	String corsoselezionato=comboboxCorsi.getValue();
    	List<Studente> risultato=new LinkedList<Studente>();
    	if(corsoselezionato.equals("")||corsoselezionato==null) {
    		txtRisultato.setText("Selezionare un corso!");
    		return;
    	}
    	else {
    		String cod="";
    		for(Corso c:corsi) {
    			if(corsoselezionato.equals(c.getNome())) {
    				cod=c.getCodins();
    			}
    		}
    		risultato=model.getStudentiCorso(cod);
    		for(Studente s:risultato) {
    			txtRisultato.appendText(s.toString()+"\n");
    		}
    	}
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }
    
    @FXML
    void doCompleta(ActionEvent event) {
    	int matricola;
    	try {
    		matricola=Integer.parseInt(txtMatricola.getText());
    	}
    	catch(NumberFormatException e) {
    		e.printStackTrace();
    		return;
    	}
    	catch(NullPointerException e) {
    		e.printStackTrace();
    		return;
    	}
    	for(Studente s:studenti) {
    		if(matricola==s.getMatricola()) {
    			txtNome.setText(s.getNome());
    			txtCognome.setText(s.getCognome());
    		}
    	}
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtCognome.clear();
    	txtNome.clear();
    	txtMatricola.clear();
    	txtRisultato.clear();
    }

    @FXML
    void initialize() {
        assert comboboxCorsi != null : "fx:id=\"comboboxCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCompleta != null : "fx:id=\"btnCompleta\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	corsi=model.getTuttiICorsi();
    	studenti=model.getTuttiStudenti();
    	comboboxCorsi.getItems().add("");
    	comboboxCorsi.setValue("");
    	for(Corso c:corsi) {
    		comboboxCorsi.getItems().add(c.getNome());
    	}
    }
    
}
