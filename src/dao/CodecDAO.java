package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entite.Codec;
import entite.CodecType;
import entite.Entreprise;
import entite.PlayerCodec;

public class CodecDAO 
{
	public void addCodec(EntityManager entityManager,String nomCodec,String fichierCodec,Entreprise entreprise,List<PlayerCodec> playerCodecs,CodecType codecType)
	{
		List<Codec> codecs=select(entityManager, nomCodec);
		if(codecs.isEmpty())
		{
			Codec codec=new Codec();
			codec.setNomCodec(nomCodec);
			codec.setFichierCodec(fichierCodec);
			codec.setEntreprise(entreprise);
			codec.setPlayerCodecs(playerCodecs);
			codec.setCodecType(codecType);
			entityManager.getTransaction().begin();
			entityManager.persist(codec);
			entityManager.getTransaction().commit();
			System.out.println("-----Ajouter codec-----");
		}
		else
		{
			System.out.println("-----Ce nom de codec deja existe, change le nom, SVP!-----");
		}
	}
	
	public List<Codec> select(EntityManager entityManager,String nomCodec)
	{
		String sql="from Codec codec where codec.nomCodec='"+nomCodec+"'";
		Query query = entityManager.createQuery(sql);
		List<Codec> resultList = new ArrayList<Codec>();
		resultList=query.getResultList();
		return resultList;
	}
	
	public void delete(EntityManager entityManager,String nomCodec)
	{
		List<Codec> codecs=select(entityManager, nomCodec);
		if(codecs.isEmpty())
		{
			System.out.println("-----codec n'existe pas-----");
		}
		else
		{
			Codec codec=codecs.get(0);
			entityManager.getTransaction().begin();
			entityManager.remove(codec);
			entityManager.getTransaction().commit();
			System.out.println("-----supprimer codec-----");
		}
	}
	
	public void updateEntreprise(EntityManager entityManager,String nomCodec,Entreprise entreprise)
	{
		List<Codec> codecs=select(entityManager, nomCodec);
		if(codecs.isEmpty())
		{
			System.out.println("-----codec n'existe pas-----");
		}
		else
		{
			Codec codec=codecs.get(0);
			entityManager.getTransaction().begin();
			codec.setEntreprise(entreprise);
			entityManager.getTransaction().commit();
			System.out.println("-----codec update entreprise-----");
		}
	}
	
	public void addPlayerCodec(EntityManager entityManager,String nomCodec,PlayerCodec playerCodec)
	{
		List<Codec> codecs=select(entityManager, nomCodec);
		if(codecs.isEmpty())
		{
			System.out.println("-----codec n'existe pas-----");
		}
		else
		{
			Codec codec=codecs.get(0);
			List<PlayerCodec> playerCodecs=new ArrayList<PlayerCodec>();
			int idx=-1;
			if(codec.getPlayerCodecs()!=null)
			{
				idx=playerCodecs.indexOf(playerCodec);
				playerCodecs=codec.getPlayerCodecs();				
			}		
			if(idx<0)
			{
				playerCodecs.add(playerCodec);
				entityManager.getTransaction().begin();
				codec.setPlayerCodecs(playerCodecs);
				entityManager.getTransaction().commit();
				System.out.println("-----codec add PlayerCodec-----");
			}
			else
			{
				System.out.println("-----playerCodec deja existe-----");
			}
		}
	}
	
	public void deletePlayerCodec(EntityManager entityManager,String nomCodec,PlayerCodec playerCodec)
	{
		List<Codec> codecs=select(entityManager, nomCodec);
		if(codecs.isEmpty())
		{
			System.out.println("-----codec n'existe pas-----");
		}
		else
		{
			Codec codec=codecs.get(0);
			if(codec.getPlayerCodecs()==null)
			{
				System.out.println("-----pas de players-----");
			}
			else
			{
				List<PlayerCodec> playerCodecs=codec.getPlayerCodecs();
				int idx=playerCodecs.indexOf(playerCodec);
				if(idx<0)
				{
					System.out.println("-----playerCodec n'existe pas-----");
				}
				else
				{
					playerCodecs.remove(idx);
					entityManager.getTransaction().begin();
					codec.setPlayerCodecs(playerCodecs);
					entityManager.getTransaction().commit();
					System.out.println("-----codec supprime PlayerCodec-----");
				}
			}		
		}
	}
	
	public void updateCodecType(EntityManager entityManager,String nomCodec,CodecType codecType)
	{
		List<Codec> codecs=select(entityManager, nomCodec);
		if(codecs.isEmpty())
		{
			System.out.println("-----codec n'existe pas-----");
		}
		else
		{
			Codec codec=codecs.get(0);
			entityManager.getTransaction().begin();
			codec.setCodecType(codecType);
			entityManager.getTransaction().commit();
			System.out.println("-----codec update type-----");
		}
	}
	

	public List<Codec> selectCodecByType(EntityManager entityManager,String codecType)
	{
		String sql="from Codec codec where codec.codecType.type='"+codecType+"'";
		Query query = entityManager.createQuery(sql);
		List<Codec> resultList = new ArrayList<Codec>();
		resultList=query.getResultList();
		return resultList;
	}
}
