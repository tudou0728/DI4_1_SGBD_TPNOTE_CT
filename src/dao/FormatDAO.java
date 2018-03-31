package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entite.Format;
import entite.PlayerFormat;
import entite.Video;

public class FormatDAO 
{
	public void addFormat(EntityManager entityManager,String nomFormat,String entensionFormat,List<Video> videos,List<PlayerFormat> playerFormatListes)
	{
		List<Format> formats=selectFormat(entityManager, nomFormat);
		if(formats.isEmpty())
		{
			Format format = new Format();
			format.setNomFormat(nomFormat);
			format.setEntensionFormat(entensionFormat);
			format.setVideos(videos);
			format.setPlayerFormatListes(playerFormatListes);
			entityManager.getTransaction().begin();
			entityManager.persist(format);
			entityManager.getTransaction().commit();
			System.out.println("-----Ajoute une format-----");
		}
		else
		{
			System.out.println("-----Ce nom de format deja existe, change le nom, SVP!-----");
		}
	}
	
	public List<Format> selectFormat(EntityManager entityManager,String nomFormat)
	{
		String sql="from Format format where format.nomFormat='"+nomFormat+"'";
		Query query = entityManager.createQuery(sql);
		List<Format> resultList = new ArrayList<Format>();
		resultList=query.getResultList();
		return resultList;
	}
	
	public void updateFormatNom(EntityManager entityManager,String nomFormat,String nouveauNom)
	{
		List<Format> formats=selectFormat(entityManager, nomFormat);
		if(formats.isEmpty())
		{
			System.out.println("-----Ce format n'exsite pas!-----");
		}
		else 
		{
			Format format=formats.get(0);
			entityManager.getTransaction().begin();
			format.setNomFormat(nouveauNom);
			entityManager.getTransaction().commit();
		}
	}
	
	public void addVideo(EntityManager entityManager,String nomFormat,Video video)
	{
		List<Format> formats=selectFormat(entityManager, nomFormat);
		if(formats.isEmpty())
		{
			System.out.println("-----Ce format n'exsite pas!-----");
		}
		else
		{
			Format format=formats.get(0);
			List<Video> videos=new ArrayList<Video>();
			int idx=-1;
			if(format.getVideos()!=null)
			{
				videos=format.getVideos();
				idx=videos.indexOf(video);
			}			
			if(idx<0)
			{
				videos.add(video);
				entityManager.getTransaction().begin();
				format.setVideos(videos);
				entityManager.getTransaction().commit();
				System.out.println("-----ajouter video dans un format-----");
			}
			else
			{
				System.out.println("-----video deja existe dans format-----");
			}
		}
	}
	public void deleteFormat(EntityManager entityManager,String nomFormat)
	{
		List<Format> formats=selectFormat(entityManager, nomFormat);
		if(formats.isEmpty())
		{
			System.out.println("-----Ce format n'exsite pas!-----");
		}
		else
		{
			Format format=formats.get(0);
			entityManager.getTransaction().begin();
			entityManager.remove(format);
			entityManager.getTransaction().commit();
			System.out.println("-----supprimer un format-----");
		}
	}
	
	public void deleteVideo(EntityManager entityManager,String nomFormat,Video video)
	{
		List<Format> formats=selectFormat(entityManager, nomFormat);
		if(formats.isEmpty())
		{
			System.out.println("-----Ce format n'exsite pas!-----");
		}
		else
		{
			Format format=formats.get(0);
			if(format.getVideos()==null)
			{
				System.out.println("-----pas de video dans ce format!-----");
			}
			else
			{
				List<Video> videos=format.getVideos();
				int indexDeVideo=videos.indexOf(video);
				if(indexDeVideo<0)
				{
					System.out.println("-----Ce video n'exsite pas dans ce format!-----");
				}
				else
				{
					videos.remove(indexDeVideo);
					entityManager.getTransaction().begin();
					format.setVideos(videos);
					entityManager.getTransaction().commit();
					System.out.print("-----supprimer cette video dans ce format!-----");
				}
			}		
		}
	}
	
	public void addPlayerFormat(EntityManager entityManager,String nomFormat,PlayerFormat playerFormat)
	{
		List<Format> formats=selectFormat(entityManager, nomFormat);
		if(formats.isEmpty())
		{
			System.out.println("-----Ce format n'exsite pas!-----");
		}
		else
		{
			Format format=formats.get(0);
			List<PlayerFormat> playerFormats =new ArrayList<PlayerFormat>();
			if(format.getPlayerFormatListes()!=null)
			{
				playerFormats=format.getPlayerFormatListes();
			}			
			playerFormats.add(playerFormat);
			entityManager.getTransaction().begin();
			format.setPlayerFormatListes(playerFormats);
			entityManager.getTransaction().commit();
			System.out.println("-----format ajoute playerFormat-----");
		}
	}
	
	public void deletePlayerFormat(EntityManager entityManager,String nomFormat,PlayerFormat playerFormat)
	{
		List<Format> formats=selectFormat(entityManager, nomFormat);
		if(formats.isEmpty())
		{
			System.out.println("-----Ce format n'exsite pas!-----");
		}
		else
		{
			Format format=formats.get(0);
			if(format.getPlayerFormatListes()==null)
			{
				System.out.println("-----pas de playerFormat dans ce format!-----");
			}
			else
			{
				List<PlayerFormat> playerFormats =format.getPlayerFormatListes();
				int idx=playerFormats.indexOf(playerFormat);
				if(idx<0)
				{
					System.out.println("-----PlayerFormat n'exsite pas dans ce format!-----");
				}
				else 
				{
					playerFormats.remove(idx);
					entityManager.getTransaction().begin();
					format.setPlayerFormatListes(playerFormats);
					entityManager.getTransaction().commit();
					System.out.println("-----supprimer playerFormat dans un format-----");
				}
			}
		}
	}
}
