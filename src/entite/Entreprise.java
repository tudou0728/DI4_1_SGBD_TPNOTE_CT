package entite;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Entreprise 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int IdEntreprise;
	
	private String nomEntreprise;
	private String adresseWeb;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="entreprise")
	private List<Codec> codecs=new ArrayList<Codec>();

	public int getIdEntreprise() {
		return IdEntreprise;
	}

	public void setIdEntreprise(int idEntreprise) {
		IdEntreprise = idEntreprise;
	}

	public String getNomEntreprise() {
		return nomEntreprise;
	}

	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}

	public String getAdresseWeb() {
		return adresseWeb;
	}

	public void setAdresseWeb(String adresseWeb) {
		this.adresseWeb = adresseWeb;
	}

	public List<Codec> getCodecs() {
		return codecs;
	}

	public void setCodecs(List<Codec> codecs) {
		this.codecs = codecs;
	}
	
}
