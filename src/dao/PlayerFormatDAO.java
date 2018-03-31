package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entite.Format;
import entite.Player;
import entite.PlayerFormat;

public class PlayerFormatDAO 
{
	public void addPlayerFormat(EntityManager entityManager,Player  player,Format format)
	{
		List<PlayerFormat> playerFormats=select(entityManager, player.getNomPlayer(), format.getNomFormat());
		if(playerFormats.isEmpty())
		{
			PlayerFormat playerFormat =new PlayerFormat();
			playerFormat.setPlayer2(player);
			playerFormat.setFormat2(format);
			entityManager.getTransaction().begin();
			entityManager.persist(playerFormat);
			entityManager.getTransaction().commit();
			System.out.println("-----playerFormat ajoute playerFormat-----");
		}
		else
		{
			System.out.println("-----playerFormat deja existe!-----");
		}
	}
	
	public List<PlayerFormat> select(EntityManager entityManager,String nomPlayer,String nomFormat)
	{
		String sql="from PlayerFormat playerFormat where playerFormat.format2.nomFormat='"+nomFormat+"' and playerFormat.player2.nomPlayer='"+nomPlayer+"'";
		Query query = entityManager.createQuery(sql);
		List<PlayerFormat> resultList = new ArrayList<PlayerFormat>();
		resultList=query.getResultList();
		return resultList;
	}
	
	public void deletePlayerFormat(EntityManager entityManager,String nomPlayer,String nomFormat)
	{
		List<PlayerFormat> playerFormats=select(entityManager, nomPlayer, nomFormat);
		if(playerFormats.isEmpty())
		{
			System.out.println("-----playerFormat n'existe pas!-----");
		}
		else
		{
			PlayerFormat playerFormat=playerFormats.get(0);
			entityManager.getTransaction().begin();
			entityManager.remove(playerFormat);
			entityManager.getTransaction().commit();
			System.out.println("-----supprimer playerFormat-----");
		}
	}
	
	public List<Player> selectPlayer(EntityManager entityManager,String nomFormat)
	{
		String sql="select playerFormat.player2 from PlayerFormat playerFormat where playerFormat.format2.nomFormat='"+nomFormat+"'";
		Query query = entityManager.createQuery(sql);
		List<Player > resultList = new ArrayList<Player>();
		resultList=query.getResultList();
		return resultList;
	}
	
	public List<Format> selectFormat(EntityManager entityManager,String nomPlayer)
	{
		String sql="select playerFormat.format2 from PlayerFormat playerFormat where playerFormat.player2.nomPlayer='"+nomPlayer+"'";
		Query query = entityManager.createQuery(sql);
		List<Format> resultList = new ArrayList<Format>();
		resultList=query.getResultList();
		return resultList;
	}
}
