package entite;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PlayerFormat
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int IdPlayerFormat;
	
	@ManyToOne(fetch=FetchType.LAZY)//cascade=CascadeType.ALL,
	private Format format2;
	
	@ManyToOne(fetch=FetchType.LAZY) //cascade=CascadeType.ALL,
	private Player player2;

	public int getIdPlayerFormat() {
		return IdPlayerFormat;
	}

	public void setIdPlayerFormat(int idPlayerFormat) {
		IdPlayerFormat = idPlayerFormat;
	}

	public Format getFormat2() {
		return format2;
	}

	public void setFormat2(Format format2) {
		this.format2 = format2;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}
	
	
}
