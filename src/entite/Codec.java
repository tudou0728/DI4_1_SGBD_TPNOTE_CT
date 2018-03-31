package entite;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Codec 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int IdCodec;
	
	private String nomCodec;
	private String fichierCodec;
	
	@ManyToOne(fetch=FetchType.LAZY)//cascade=CascadeType.ALL,
	private Entreprise entreprise;
	
	@ManyToOne(fetch=FetchType.LAZY)//cascade=CascadeType.ALL,
	private CodecType codecType;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="codec")
	private List<PlayerCodec> playerCodecs=new ArrayList<PlayerCodec>();

	public int getIdCodec() {
		return IdCodec;
	}

	public void setIdCodec(int idCodec) {
		IdCodec = idCodec;
	}

	public String getNomCodec() {
		return nomCodec;
	}

	public void setNomCodec(String nomCodec) {
		this.nomCodec = nomCodec;
	}

	public String getFichierCodec() {
		return fichierCodec;
	}

	public void setFichierCodec(String fichierCodec) {
		this.fichierCodec = fichierCodec;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public List<PlayerCodec> getPlayerCodecs() {
		return playerCodecs;
	}

	public void setPlayerCodecs(List<PlayerCodec> playerCodecs) {
		this.playerCodecs = playerCodecs;
	}
	public CodecType getCodecType() {
		return codecType;
	}

	public void setCodecType(CodecType codecType) {
		this.codecType = codecType;
	}
}
