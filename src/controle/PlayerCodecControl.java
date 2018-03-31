package controle;

import java.util.List;

import javax.persistence.EntityManager;

import dao.CodecDAO;
import dao.PlayerCodecDAO;
import dao.PlayerDAO;
import entite.Codec;
import entite.Player;
import entite.PlayerCodec;

public class PlayerCodecControl 
{
	private PlayerDAO playerDAO=new PlayerDAO();
	private CodecDAO codecDAO=new CodecDAO();
	private PlayerCodecDAO playerCodecDAO=new PlayerCodecDAO();
	
	public void playerAddCodec(EntityManager entityManager,String nomPlayer,String nomCodec)
	{
		if(playerDAO.selectPlayer(entityManager, nomPlayer)==null || playerDAO.selectPlayer(entityManager, nomPlayer).size()==0)
		{
			System.out.println("----- pas de ce player-----");
		}
		else if(codecDAO.select(entityManager, nomCodec)==null || codecDAO.select(entityManager, nomCodec).size()==0)
		{
			System.out.println("----- pas de ce codec-----");
		}
		else
		{
			Player player=playerDAO.selectPlayer(entityManager, nomPlayer).get(0);
			Codec codec=codecDAO.select(entityManager, nomCodec).get(0);
			List<PlayerCodec> playerCodecs=playerCodecDAO.select(entityManager, nomPlayer, nomCodec);
			if(playerCodecs.isEmpty())
			{
				playerCodecDAO.addPlayerCodec(entityManager, player, codec);
			}
			else
			{
				System.out.print("-----deja existe -----");
			}
			//playerCodecs=playerCodecDAO.select(entityManager, nomPlayer, nomCodec);
			//PlayerCodec playerCodec=playerCodecs.get(0);
			//playerDAO.addPlayerCodec(entityManager, nomPlayer, playerCodec);
			//codecDAO.addPlayerCodec(entityManager, nomCodec, playerCodec);
		}		
	}

	public void playerDeleteCodec(EntityManager entityManager,String nomPlayer,String nomCodec)
	{
		if(playerDAO.selectPlayer(entityManager, nomPlayer)==null || playerDAO.selectPlayer(entityManager, nomPlayer).size()==0)
		{
			System.out.println("----- pas de ce player-----");
		}
		else if(codecDAO.select(entityManager, nomCodec)==null || codecDAO.select(entityManager, nomCodec).size()==0)
		{
			System.out.println("----- pas de ce codec-----");
		}
		else {
			//Player player=playerDAO.selectPlayer(entityManager, nomPlayer).get(0);
			//Codec codec=codecDAO.select(entityManager, nomCodec).get(0);
			//PlayerCodec playerCodec=playerCodecDAO.select(entityManager, nomPlayer, nomCodec).get(0);
			playerCodecDAO.delete(entityManager, nomPlayer, nomCodec);
		}		
	}
	
	public PlayerDAO getPlayerDAO() {
		return playerDAO;
	}

	public void setPlayerDAO(PlayerDAO playerDAO) {
		this.playerDAO = playerDAO;
	}

	public CodecDAO getCodecDAO() {
		return codecDAO;
	}

	public void setCodecDAO(CodecDAO codecDAO) {
		this.codecDAO = codecDAO;
	}

	public PlayerCodecDAO getPlayerCodecDAO() {
		return playerCodecDAO;
	}

	public void setPlayerCodecDAO(PlayerCodecDAO playerCodecDAO) {
		this.playerCodecDAO = playerCodecDAO;
	}
	
	
}
