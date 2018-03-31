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
public class CodecType 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int IdCodecType;
	
	private String type;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="codecType")
	private List<Codec> codecs=new ArrayList<Codec>();

	public int getIdCodecType() {
		return IdCodecType;
	}

	public void setIdCodecType(int idCodecType) {
		IdCodecType = idCodecType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Codec> getCodecs() {
		return codecs;
	}

	public void setCodecs(List<Codec> codecs) {
		this.codecs = codecs;
	}
	
}
