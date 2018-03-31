package controle;

import java.util.List;

import javax.persistence.EntityManager;

import dao.FormatDAO;
import dao.PlayerDAO;
import dao.PlayerFormatDAO;
import entite.Format;
import entite.Player;
import entite.PlayerFormat;

public class FormatPlayerControl 
{
	private FormatDAO formatDAO=new FormatDAO();
	private PlayerDAO playerDAO=new PlayerDAO();
	private PlayerFormatDAO playerFormatDAO=new PlayerFormatDAO();
	
	public void FormatAddPlayer(EntityManager entityManager,String nomFormat,String nomPlayer)
	{	
		if(formatDAO.selectFormat(entityManager, nomFormat)==null || formatDAO.selectFormat(entityManager, nomFormat).size()==0)
		{
			System.out.println("----- pas de ce format-----");
		}
		else if(playerDAO.selectPlayer(entityManager, nomPlayer)==null || playerDAO.selectPlayer(entityManager, nomPlayer).size()==0)
		{
			System.out.println("----- pas de ce player-----");
		}
		else
		{
			Format format=formatDAO.selectFormat(entityManager, nomFormat).get(0);
			Player player=playerDAO.selectPlayer(entityManager, nomPlayer).get(0);
			playerFormatDAO.addPlayerFormat(entityManager, player, format);
			List<PlayerFormat> playerFormats=playerFormatDAO.select(entityManager, nomPlayer, nomFormat);
			if(playerFormats.isEmpty())
			{
				playerFormatDAO.addPlayerFormat(entityManager, player, format);
			}
			playerFormats=playerFormatDAO.select(entityManager, nomPlayer, nomFormat);
			PlayerFormat playerFormat=playerFormats.get(0);
			//formatDAO.addPlayerFormat(entityManager, nomFormat, playerFormat);
			//playerDAO.addPlayerFormat(entityManager, nomPlayer, playerFormat);
		}		
	}

	public FormatDAO getFormatDAO() {
		return formatDAO;
	}

	public void setFormatDAO(FormatDAO formatDAO) {
		this.formatDAO = formatDAO;
	}

	public PlayerDAO getPlayerDAO() {
		return playerDAO;
	}

	public void setPlayerDAO(PlayerDAO playerDAO) {
		this.playerDAO = playerDAO;
	}

	public PlayerFormatDAO getPlayerFormatDAO() {
		return playerFormatDAO;
	}

	public void setPlayerFormatDAO(PlayerFormatDAO playerFormatDAO) {
		this.playerFormatDAO = playerFormatDAO;
	}
	
	
}
