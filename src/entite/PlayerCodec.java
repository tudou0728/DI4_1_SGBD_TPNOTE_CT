package entite;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PlayerCodec 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int IdPlayerCodec;
	
	@ManyToOne(fetch=FetchType.LAZY)//cascade=CascadeType.ALL,
	private Player player1;
	
	@ManyToOne(fetch=FetchType.LAZY)//cascade=CascadeType.ALL,
	private Codec codec;

	public int getIdPlayerCodec() {
		return IdPlayerCodec;
	}

	public void setIdPlayerCodec(int idPlayerCodec) {
		IdPlayerCodec = idPlayerCodec;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Codec getCodec() {
		return codec;
	}

	public void setCodec(Codec codec) {
		this.codec = codec;
	}
	
}
