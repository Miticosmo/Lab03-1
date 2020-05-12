/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {

	Dictionary dizionario;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="buttonMenuTendina"
	private ChoiceBox<String> buttonMenuTendina = new ChoiceBox<>();

	@FXML // fx:id="txtInput"
	private TextArea txtInput; // Value injected by FXMLLoader

	@FXML // fx:id="txtoutput"
	private TextArea txtoutput; // Value injected by FXMLLoader

	@FXML // fx:id="TxtError"
	private Label TxtError; // Value injected by FXMLLoader

	@FXML // fx:id="txtTempoImpiegato"
	private Label txtTempoImpiegato; // Value injected by FXMLLoader

	@FXML
	void doClearText(ActionEvent event) {
		txtoutput.clear();
		TxtError.setText("");
		txtInput.clear();
	}

	/**
	 * @param event
	 */
	@FXML
	void doSpellCheck(ActionEvent event) {
		// 1.Ripulisco tutte le variabili

		txtoutput.clear();

		// 2.Leggo la lingua selezionata e creo il dizionario associato
		String language = buttonMenuTendina.getValue();
		dizionario = new Dictionary();
		dizionario.localDictionary(language);

		// 3.Leggo il testo digitato, lo filtro e chiamo il metodo che mi restituisce le
		// parole errate
		String testoDigitato = txtInput.getText();
		
		if(testoDigitato.length()==0) {
			txtInput.setText("Inserire del testo valido");
			return;
		}
			
		System.out.println(testoDigitato);
//    	
//    	String filtrata = filtraDaCaratteriSpeciali(testoDigitato);
//    	
//    	System.out.println(filtrata);

		List<String> listaParole = creaListaDiParole(testoDigitato);

		List<RichWord> paroleErrate = dizionario.spellCheckTest(listaParole);

		int errori = 0;
		// Se la struttura dati è vuota, allora ci sono parole errate, altrimenti è
		// tutto ok
		if (paroleErrate != null) {
			for (RichWord r : paroleErrate) {

				if (r.isErrata()) {
					txtoutput.appendText(r.getParola() + "\n");
					errori++;
				}
			}
			TxtError.setText("Il testo contiene " + errori + " errori");
		} else {
			TxtError.setText("Nessun errrore rilevato");
			errori = 0;
		}
		dizionario.pulisciDizionario();
	}

	private List<String> creaListaDiParole(String testoDigitato) {
		List<String> l = new ArrayList<>();

		// Esempio: Ciao mondo come stai?
		// Devo iterare sulla stringa, finquando non ha spazi

		while (testoDigitato.contains(" ")) {
			String messaggio = testoDigitato.substring(0, testoDigitato.indexOf(" ")).toLowerCase();
			testoDigitato = testoDigitato.substring(messaggio.length() + 1, testoDigitato.length());
			l.add(messaggio);

			if (!testoDigitato.contains(" ")) {
				l.add(testoDigitato.toLowerCase());
				break;
			}

		}

		return l;
	}

	private String filtraDaCaratteriSpeciali(String testoDigitato) {
		String filtrata = testoDigitato.replaceAll("@!#", " ");
		return filtrata;
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert buttonMenuTendina != null : "fx:id=\"buttonMenuTendina\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtoutput != null : "fx:id=\"txtoutput\" was not injected: check your FXML file 'Scene.fxml'.";
		assert TxtError != null : "fx:id=\"TxtError\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtTempoImpiegato != null : "fx:id=\"txtTempoImpiegato\" was not injected: check your FXML file 'Scene.fxml'.";
		buttonMenuTendina.getItems().add("English");
		buttonMenuTendina.getItems().add("Italian");
		buttonMenuTendina.setValue("English");
		txtTempoImpiegato.setText("Spell check");
		TxtError.setText("Number error");
	}
}
