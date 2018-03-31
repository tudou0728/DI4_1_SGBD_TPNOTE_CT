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
public class Player 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int IdPlayer;
	
	private String nomPlayer;
	private Integer koctetsPlayer;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="player2")
	private List<PlayerFormat> playerFormats=new ArrayList<PlayerFormat>();
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="player1")
	private List<PlayerCodec> playerCodecs=new ArrayList<PlayerCodec>();

	public int getIdPlayer() {
		return IdPlayer;
	}

	public void setIdPlayer(int idPlayer) {
		IdPlayer = idPlayer;
	}

	public String getNomPlayer() {
		return nomPlayer;
	}

	public void setNomPlayer(String nomPlayer) {
		this.nomPlayer = nomPlayer;
	}

	public Integer getKoctetsPlayer() {
		return koctetsPlayer;
	}

	public void setKoctetsPlayer(Integer koctetsPlayer) {
		this.koctetsPlayer = koctetsPlayer;
	}

	public List<PlayerFormat> getPlayerFormats() {
		return playerFormats;
	}

	public void setPlayerFormats(List<PlayerFormat> playerFormats) {
		this.playerFormats = playerFormats;
	}

	public List<PlayerCodec> getPlayerCodecs() {
		return playerCodecs;
	}

	public void setPlayerCodecs(List<PlayerCodec> playerCodecs) {
		this.playerCodecs = playerCodecs;
	}
	
	
}
