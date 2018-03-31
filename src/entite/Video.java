package entite;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Video 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int IdVideo;
	
	private String nomVideo;
	
	@ManyToOne(fetch=FetchType.LAZY)//cascade=CascadeType.ALL,
	private Format format;

	public int getIdVideo() {
		return IdVideo;
	}

	public void setIdVideo(int idVideo) {
		IdVideo = idVideo;
	}

	public String getNomVideo() {
		return nomVideo;
	}

	public void setNomVideo(String nomVideo) {
		this.nomVideo = nomVideo;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}
	
	
	
}
