package it.polito.tdp.spellchecker.model;
/**
 * Classe RichWord che contiente una parola.
 * Attraverso un attributo boolean possiamo settare il suo valore a true o false.
 * @author miticosmo
 *
 */


public class RichWord {
	
	private String parola;
	private boolean errata;
	
	public RichWord(String parola) {
		this.errata = false;
		this.parola = parola;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parola == null) ? 0 : parola.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RichWord other = (RichWord) obj;
		if (parola == null) {
			if (other.parola != null)
				return false;
		} else if (!parola.equals(other.parola))
			return false;
		return true;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public boolean isErrata() {
		return errata;
	}

	public void setErrata(boolean errata) {
		this.errata = errata;
	}
}
