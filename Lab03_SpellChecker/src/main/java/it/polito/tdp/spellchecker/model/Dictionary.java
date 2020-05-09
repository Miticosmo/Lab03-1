package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Struttura dati contenente le parole di una lingua specificata, caricata tramite un apposito
 * metodo che permette di caricare la tipologia di lingua.
 * @author miticosmo
 *
 */

public class Dictionary {

	private Set<String> dizionario;

	public Dictionary() {
		this.dizionario = new HashSet<>();
	}
	
	/**
	 * Metodo che permette di caricare in struttura dati la lingua selezionata
	 * @param language tipologia di lingua da selezionare
	 */
	
	
	public void localDictionary(String language) {
		try {
			FileReader fr = new FileReader("src/main/resources/"+ language + ".txt");
			BufferedReader br = new BufferedReader(fr);

			String word;
			while ((word = br.readLine()) != null) {
				dizionario.add(word);
			}
			br.close();
		} catch (Exception e) {
			System.out.println("Errore nella lettura del file");
		}
	}
	
	/**
	 * Esegue il controllo ortografico sul testo in input e restituisce una lista di RichWord
	 * @param inputTextList
	 * @return
	 */
	
	public List<RichWord> spellCheckTest(List<String> inputTextList){
		
		List<RichWord> paroleErrate = new ArrayList<>();
		
		for(String s : inputTextList) {
			if(!dizionario.contains(s)) {
				RichWord r = new RichWord(s);
				r.setErrata(true);
				paroleErrate.add(r);
			}	
		}
		if(!paroleErrate.isEmpty())
			return paroleErrate;
		else
			return null;
	}
	
	
	
	
	

}
