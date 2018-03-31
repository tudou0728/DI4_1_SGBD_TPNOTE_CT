package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entite.Codec;
import entite.Entreprise;

public class EntrepriseDAO 
{
	public void addEntreprise(EntityManager entityManager,String nomEntreprise,String adresseWeb,List<Codec> codecs)
	{
		List<Entreprise> entreprises=select(entityManager, nomEntreprise);
		if(entreprises.isEmpty())
		{
			Entreprise entreprise=new Entreprise();
			entreprise.setNomEntreprise(nomEntreprise);
			entreprise.setAdresseWeb(adresseWeb);
			entreprise.setCodecs(codecs);
			entityManager.getTransaction().begin();
			entityManager.persist(entreprise);
			entityManager.getTransaction().commit();
			System.out.println("-----Ajouter Entreprise-----");
		}
		else
		{
			System.out.println("-----Ce nom d'entreprise deja existe, change le nom, SVP!-----");
		}
	}
	
	public List<Entreprise> select(EntityManager entityManager,String nomEntreprise)
	{
		String sql="from Entreprise entreprise where entreprise.nomEntreprise='"+nomEntreprise+"'";
		Query query = entityManager.createQuery(sql);
		List<Entreprise> resultList = new ArrayList<Entreprise>();
		resultList=query.getResultList();
		return resultList;
	}
	
	public void delete(EntityManager entityManager,String nomEntreprise)
	{
		List<Entreprise> entreprises=select(entityManager, nomEntreprise);
		if(entreprises.isEmpty())
		{
			System.out.println("-----cette entreprise n'existe pas, change le nom, SVP!-----");
		}
		else
		{
			Entreprise entreprise=entreprises.get(0);
			entityManager.getTransaction().begin();
			entityManager.remove(entreprise);
			entityManager.getTransaction().commit();
			System.out.println("-----supprimer Entreprise-----");
		}
	}
	
	public void addCodec(EntityManager entityManager,String nomEntreprise,Codec codec)
	{
		List<Entreprise> entreprises=select(entityManager, nomEntreprise);
		if(entreprises.isEmpty())
		{
			System.out.println("-----cette entreprise n'existe pas, change le nom, SVP!-----");
		}
		else
		{
			Entreprise entreprise=entreprises.get(0);
			List<Codec> codecs=new ArrayList<Codec>();
			if(entreprise.getCodecs()!=null)
			{
				codecs=entreprise.getCodecs();
			}			
			codecs.add(codec);
			entityManager.getTransaction().begin();
			entreprise.setCodecs(codecs);
			entityManager.getTransaction().commit();
			System.out.println("----- Entreprise ajoute codec-----");
		}
	}
	
	public void deleteCodec(EntityManager entityManager,String nomEntreprise,Codec codec)
	{
		List<Entreprise> entreprises=select(entityManager, nomEntreprise);
		if(entreprises.isEmpty())
		{
			System.out.println("-----cette entreprise n'existe pas, change le nom, SVP!-----");
		}
		else
		{
			Entreprise entreprise=entreprises.get(0);
			List<Codec> codecs=new ArrayList<Codec>();
			if(entreprise.getCodecs()==null)
			{
				System.out.println("----- pas de codec dans cette entreprise-----");
			}
			else
			{
				codecs=entreprise.getCodecs();
				int idx=codecs.indexOf(codec);
				if(idx<0)
				{
					System.out.println("-----pas de cette codec dans cette entreprise-----");
				}
				else
				{
					codecs.remove(idx);
					entityManager.getTransaction().begin();
					entreprise.setCodecs(codecs);
					entityManager.getTransaction().commit();
					System.out.println("-----entreprise supprime codec-----");
				}	
			}
		}
	}
}
