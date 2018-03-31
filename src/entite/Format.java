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
public class Format 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int IdFormat;
	
	private String nomFormat;
	private String entensionFormat;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="format")
	private List<Video> videos=new ArrayList<Video>();
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="format2")
	private List<PlayerFormat> playerFormatListes=new ArrayList<PlayerFormat>();

	public int getIdFormat() {
		return IdFormat;
	}

	public void setIdFormat(int idFormat) {
		IdFormat = idFormat;
	}

	public String getNomFormat() {
		return nomFormat;
	}

	public void setNomFormat(String nomFormat) {
		this.nomFormat = nomFormat;
	}

	public String getEntensionFormat() {
		return entensionFormat;
	}

	public void setEntensionFormat(String entensionFormat) {
		this.entensionFormat = entensionFormat;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	public List<PlayerFormat> getPlayerFormatListes() {
		return playerFormatListes;
	}

	public void setPlayerFormatListes(List<PlayerFormat> playerFormatListes) {
		this.playerFormatListes = playerFormatListes;
	}
	
	
}
