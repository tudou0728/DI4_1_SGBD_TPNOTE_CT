package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entite.Codec;
import entite.Player;
import entite.PlayerCodec;

public class PlayerCodecDAO 
{
	public void addPlayerCodec(EntityManager entityManager,Player player,Codec codec)
	{
		List<PlayerCodec> playerCodecs=select(entityManager, player.getNomPlayer(), codec.getNomCodec());
		if(playerCodecs.isEmpty())
		{
			PlayerCodec playerCodec=new PlayerCodec();
			playerCodec.setCodec(codec);
			playerCodec.setPlayer1(player);
			entityManager.getTransaction().begin();
			entityManager.persist(playerCodec);
			entityManager.getTransaction().commit();
			System.out.println("-----Ajoute PlayerCodec-----");
		}
		else
		{
			System.out.println("-----PlayerCodec deja existe-----");
		}
	}
	
	public List<PlayerCodec> select(EntityManager entityManager,String nomPlayer,String nomCodec)
	{
		String sql="from PlayerCodec playerCodec where playerCodec.player1.nomPlayer='"+nomPlayer+"' and playerCodec.codec.nomCodec='"+nomCodec+"'";
		Query query = entityManager.createQuery(sql);
		List<PlayerCodec> resultList = new ArrayList<PlayerCodec>();
		resultList=query.getResultList();
		return resultList;
	}
	
	public void delete(EntityManager entityManager,String nomPlayer,String nomCodec)
	{
		List<PlayerCodec> playerCodecs=select(entityManager, nomPlayer, nomCodec);
		if(playerCodecs.isEmpty())
		{
			System.out.println("-----PlayerCodec n'existe pas-----");
		}
		else
		{
			PlayerCodec playerCodec=playerCodecs.get(0);
			entityManager.getTransaction().begin();
			entityManager.remove(playerCodec);
			entityManager.getTransaction().commit();
			System.out.println("-----supprimer PlayerCodec-----");
		}
	}
	
	public List<Codec> selectCodec(EntityManager entityManager,String nomPlayer)
	{
		String sql="select playerCodec.codec from PlayerCodec playerCodec where playerCodec.player1.nomPlayer='"+nomPlayer+"'";
		Query query = entityManager.createQuery(sql);
		List<Codec> resultList = new ArrayList<Codec>();
		resultList=query.getResultList();
		return resultList;
	}
	
}
