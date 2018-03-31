package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entite.Codec;
import entite.CodecType;

public class CodecTypeDAO 
{
	public void add(EntityManager entityManager,String type,List<Codec> codecs)
	{
		List<CodecType> types=select(entityManager, type);
		if(types.isEmpty())
		{
			CodecType codecType=new CodecType();
			codecType.setType(type);
			codecType.setCodecs(codecs);
			entityManager.getTransaction().begin();
			entityManager.persist(codecType);
			entityManager.getTransaction().commit();
			System.out.println("-----ajoute une type de codec -----");
		}
		else
		{
			System.out.println("-----Cette type de codec deja existe-----");
		}
	}

	
	public List<CodecType> select(EntityManager entityManager,String type)
	{
		String sql="from CodecType codecType where codecType.type='"+type+"'";
		Query query = entityManager.createQuery(sql);
		List<CodecType> resultList = new ArrayList<CodecType>();
		List<CodecType> temps=query.getResultList();
		if(temps!=null)
		{
			resultList=temps;
		}
		return resultList;
	}
	
	public void addCodec(EntityManager entityManager,String type,Codec codec)
	{
		List<CodecType> codecTypes=select(entityManager, type);
		if(codecTypes.isEmpty())
		{
			System.out.println("-----Cette type de codec n'existe pas-----");
		}
		else
		{
			CodecType codecType =codecTypes.get(0);
			List<Codec> codecs=new ArrayList<Codec>();
			if(codecType.getCodecs()!=null)
			{
				codecs=codecType.getCodecs();
			}			
			codecs.add(codec);
			entityManager.getTransaction().begin();
			codecType.setCodecs(codecs);
			entityManager.getTransaction().commit();
			System.out.println("-----CodecType ajoute codec-----");
		}
	}
	
	public void deleteCodec(EntityManager entityManager,String type,Codec codec)
	{
		List<CodecType> codecTypes=select(entityManager, type);
		if(codecTypes.isEmpty())
		{
			System.out.println("-----Cette type de codec n'existe pas-----");
		}
		else
		{
			CodecType codecType =codecTypes.get(0);
			if(codecType.getCodecs()==null)
			{
				System.out.println("-----pas de codec dans cette typeCodec-----");
			}
			else
			{
				List<Codec> codecs=codecType.getCodecs();
				int idx=codecs.indexOf(codec);
				if(idx<0)
				{
					System.out.println("-----pas de cette codec dans cette typeCodec-----");
				}
				else
				{
					codecs.remove(idx);
					entityManager.getTransaction().begin();
					codecType.setCodecs(codecs);
					entityManager.getTransaction().commit();
					System.out.println("-----CodecType supprime codec-----");
				}	
			}				
		}
	}
	
	public void delete(EntityManager entityManager,String type)
	{
		List<CodecType> codecTypes=select(entityManager, type);
		if(codecTypes.isEmpty())
		{
			System.out.println("-----Cette type de codec n'existe pas-----");
		}
		else
		{
			CodecType codecType =codecTypes.get(0);
			entityManager.getTransaction().begin();
			entityManager.remove(codecType);
			entityManager.getTransaction().commit();
			System.out.println("-----supprimer une type de codec-----");
		}
	}
	public List<CodecType> selectTousLesType(EntityManager entityManager)
	{
		String sql="from CodecType codecType ";
		Query query = entityManager.createQuery(sql);
		List<CodecType> resultList = new ArrayList<CodecType>();
		List<CodecType> temps=query.getResultList();
		if(temps!=null)
		{
			resultList=temps;
		}
		return resultList;
	}
	
	public void changeType(EntityManager entityManager,String type,String nouveau)
	{
		List<CodecType> codecTypes=select(entityManager, type);
		if(codecTypes.isEmpty())
		{
			System.out.println("-----Cette type de codec n'existe pas-----");
		}
		else
		{
			CodecType codecType =codecTypes.get(0);
			entityManager.getTransaction().begin();
			codecType.setType(nouveau);
			entityManager.getTransaction().commit();
			System.out.println("-----change codecType-----");
		}
	}
}
