package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entite.Player;
import entite.PlayerCodec;
import entite.PlayerFormat;

public class PlayerDAO 
{
	public void addPlayer(EntityManager entityManager,String nomPlayer,Integer koctetsPlayer,List<PlayerFormat> playerFormats,List<PlayerCodec> playerCodecs)
	{
		List<Player> players=selectPlayer(entityManager, nomPlayer);
		if(!players.isEmpty())
		{
			System.out.println("-----Ce nom de player deja existe, change le nom, SVP!-----");
		}
		else
		{
			Player player=new Player();
			player.setKoctetsPlayer(koctetsPlayer);
			player.setNomPlayer(nomPlayer);
			player.setPlayerCodecs(playerCodecs);
			player.setPlayerFormats(playerFormats);
			entityManager.getTransaction().begin();
			entityManager.persist(player);
			entityManager.getTransaction().commit();
			System.out.println("-----Ajoute a player-----");
		}	
	}
	
	public List<Player> selectPlayer(EntityManager entityManager,String nomPlayer)
	{
		String sql="from Player player where player.nomPlayer='"+nomPlayer+"'";
		Query query = entityManager.createQuery(sql);
		List<Player> resultList = new ArrayList<Player>();
		resultList=query.getResultList();
		return resultList;
	}
	
	public void addPlayerFormat(EntityManager entityManager,String nomPlayer,PlayerFormat playerFormat )
	{
		List<Player> players=selectPlayer(entityManager, nomPlayer);
		if(players.isEmpty())
		{
			System.out.println("-----ce player n'existe pas, change le nom, SVP!-----");
		}
		else 
		{
			Player player=players.get(0);
			List<PlayerFormat> playerFormats=new ArrayList<PlayerFormat>();
			if(player.getPlayerFormats()!=null)
			{
				playerFormats=player.getPlayerFormats();
			}			
			playerFormats.add(playerFormat);
			entityManager.getTransaction().begin();
			player.setPlayerFormats(playerFormats);
			entityManager.getTransaction().commit();
			System.out.println("-----player ajoute playerFormat-----");
		}
	}
	
	public void deletePlayerFormat(EntityManager entityManager,String nomPlayer,PlayerFormat playerFormat )
	{
		List<Player> players=selectPlayer(entityManager, nomPlayer);
		if(players.isEmpty())
		{
			System.out.println("-----ce player n'existe pas, change le nom, SVP!-----");
		}
		else 
		{
			Player player=players.get(0);
			if(player.getPlayerFormats()==null)
			{
				System.out.println("-----pas de playerFormat dans player-----");
			}
			else
			{
				List<PlayerFormat> playerFormats=player.getPlayerFormats();
				int idx=playerFormats.indexOf(playerFormat);
				if(idx<0)
				{
					System.out.println("-----PlayerFormat n'existe pas dans player-----");
				}
				else
				{
					playerFormats.remove(idx);
					entityManager.getTransaction().begin();
					player.setPlayerFormats(playerFormats);
					entityManager.getTransaction().commit();
					System.out.println("-----supprimer playerFormat dans player-----");
				}
			}
		}
	}
	
	public void deletePlayer(EntityManager entityManager,String nomPlayer)
	{
		List<Player> players=selectPlayer(entityManager, nomPlayer);
		if(players.isEmpty())
		{
			System.out.println("-----ce player n'existe pas, change le nom, SVP!-----");
		}
		else
		{
			Player player=players.get(0);
			entityManager.getTransaction().begin();
			entityManager.remove(player);
			entityManager.getTransaction().commit();
			System.out.println("-----supprimer player-----");
		}
	}
	
	public void addPlayerCodec(EntityManager entityManager,String nomPlayer,PlayerCodec playerCodec)
	{
		List<Player> players=selectPlayer(entityManager, nomPlayer);
		if(players.isEmpty())
		{
			System.out.println("-----ce player n'existe pas, change le nom, SVP!-----");
		}
		else
		{
			Player player=players.get(0);
			List<PlayerCodec> playerCodecs=new ArrayList<PlayerCodec>();
			if(player.getPlayerCodecs()!=null)
			{
				playerCodecs=player.getPlayerCodecs();
			}				
			playerCodecs.add(playerCodec);
			entityManager.getTransaction().begin();
			player.setPlayerCodecs(playerCodecs);
			entityManager.getTransaction().commit();
			System.out.println("-----player add PlayerCodec-----");
		}
	}
	
	public void deletePlayerCodec(EntityManager entityManager,String nomPlayer,PlayerCodec playerCodec)
	{
		List<Player> players=selectPlayer(entityManager, nomPlayer);
		if(players.isEmpty())
		{
			System.out.println("-----ce player n'existe pas, change le nom, SVP!-----");
		}
		else
		{
			Player player=players.get(0);
			if(player.getPlayerCodecs()==null)
			{
				System.out.println("-----pas de PlayerCodec dans ce player-----");
			}
			else
			{
				List<PlayerCodec> playerCodecs=player.getPlayerCodecs();
				int idx=playerCodecs.indexOf(playerCodec);
				if(idx<0)
				{
					System.out.println("-----pas de ce PlayerCodec dans ce player-----");
				}
				else
				{
					playerCodecs.remove(idx);
					entityManager.getTransaction().begin();
					player.setPlayerCodecs(playerCodecs);
					entityManager.getTransaction().commit();
					System.out.println("-----player delete PlayerCodec-----");
				}
			}
		}
	}
	
}
