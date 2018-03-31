package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entite.Format;
import entite.Video;

public class VideoDAO 
{
	public void addVideo(EntityManager entityManager,String nomVideo,Format format)
	{
		List<Video> videos=selectVideo(entityManager,nomVideo);
		if(videos.isEmpty())
		{
			Video video = new Video();
			video.setNomVideo(nomVideo);
			video.setFormat(format);
			entityManager.getTransaction().begin();
			entityManager.persist(video);
			entityManager.getTransaction().commit();
			System.out.println("-----Ajoute une video-----");
		}
		else
		{
			System.out.println("-----Ce nom de video deja existe, change le nom, SVP!-----");
		}
	}
	
	public List<Video> selectVideo(EntityManager entityManager,String nomVideo)
	{
		String sql="from Video video where video.nomVideo='"+nomVideo+"'";
		Query query = entityManager.createQuery(sql);
		List<Video> resultList = new ArrayList<Video>();
		resultList=query.getResultList();
		return resultList;
	}
	
	public void updateVideoNom(EntityManager entityManager,String nomVideo,String nouveauNom)
	{
		List<Video> videos=selectVideo(entityManager,nomVideo);
		if(videos.isEmpty())
		{
			System.out.println("-----Cette video n'exsite pas!-----");
		}
		else
		{
			Video video =videos.get(0);
			entityManager.getTransaction().begin();
			video.setNomVideo(nouveauNom);
			entityManager.getTransaction().commit();
		}
	}
	
	public void updateVideoFormat(EntityManager entityManager,String nomVideo, Format nouveauFormat)
	{
		List<Video> videos=selectVideo(entityManager,nomVideo);
		if(videos.isEmpty())
		{
			System.out.println("-----Cette video n'exsite pas!-----");
		}
		else
		{
			Video video =videos.get(0);
			entityManager.getTransaction().begin();
			video.setFormat(nouveauFormat); 
			entityManager.getTransaction().commit();
			System.out.println("-----update une video-----");
		}
	}
	
	public void deleteVideo(EntityManager entityManager,String nomVideo)
	{
		List<Video> videos=selectVideo(entityManager,nomVideo);
		if(videos.isEmpty())
		{
			System.out.println("-----Cette video n'exsite pas!-----");
		}
		else
		{
			Video video=videos.get(0);
			entityManager.getTransaction().begin();
			entityManager.remove(video);
			entityManager.getTransaction().commit();
			System.out.println("-----supprimer une video-----");
		}
	}
	
	public List<Video> getVideoByFormat(EntityManager entityManager,String nomFormat)
	{
		String sql="select video from Video video where video.format.nomFormat='"+nomFormat+"'";
		Query query = entityManager.createQuery(sql);
		List<Video> resultList = new ArrayList<Video>();
		resultList=query.getResultList();
		return resultList;
	}
	
	public void deleteFormat(EntityManager entityManager,String nomVideo)
	{
		List<Video> videos=selectVideo(entityManager,nomVideo);
		if(videos.isEmpty())
		{
			System.out.println("-----Cette video n'exsite pas!-----");
		}
		else
		{
			Video video=videos.get(0);
			entityManager.getTransaction().begin();
			video.setFormat(null);
			entityManager.getTransaction().commit();
			System.out.println("-----video supprime format-----");
		}
	}
}
