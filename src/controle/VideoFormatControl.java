package controle;

import javax.persistence.EntityManager;

import dao.FormatDAO;
import dao.VideoDAO;
import entite.Format;
import entite.Video;

public class VideoFormatControl 
{
	private VideoDAO videoDAO =new VideoDAO();
	private FormatDAO formatDAO =new FormatDAO();
	
	public void VideoAddFormat(EntityManager entityManager,String nomVideo,String nomFormat)
	{		
		if(videoDAO.selectVideo(entityManager, nomVideo)==null || videoDAO.selectVideo(entityManager, nomVideo).size()==0)
		{
			System.out.println("----- pas de cette video-----");
		}
		else if (formatDAO.selectFormat(entityManager, nomFormat)==null || formatDAO.selectFormat(entityManager, nomFormat).size()==0) 
		{
			System.out.println("----- pas de ce format-----");
		}
		else 
		{
			Video video=videoDAO.selectVideo(entityManager, nomVideo).get(0);
			Format format=formatDAO.selectFormat(entityManager, nomFormat).get(0);
			videoDAO.updateVideoFormat(entityManager, nomVideo, format);
			//formatDAO.addVideo(entityManager, nomFormat, video);
		}		
	}
	
	public void formatDeleteVideo(EntityManager entityManager,String nomVideo,String nomFormat)
	{
		if(videoDAO.selectVideo(entityManager, nomVideo)==null|| videoDAO.selectVideo(entityManager, nomVideo).size()==0)
		{
			System.out.println("----- pas de cette video-----");
		}
		else if (formatDAO.selectFormat(entityManager, nomFormat)==null|| formatDAO.selectFormat(entityManager, nomFormat).size()==0) 
		{
			System.out.println("----- pas de ce format-----");
		}
		else 
		{
			Video video=videoDAO.selectVideo(entityManager, nomVideo).get(0);
			Format format=formatDAO.selectFormat(entityManager, nomFormat).get(0);
			//formatDAO.deleteVideo(entityManager, nomFormat, video);
			videoDAO.deleteFormat(entityManager, nomVideo);
		}	
	}

	public void addVideo(EntityManager entityManager,String nomVideo,String nomFormat)
	{
		if (formatDAO.selectFormat(entityManager, nomFormat)==null || formatDAO.selectFormat(entityManager, nomFormat).size()==0) 
		{
			System.out.println("----- pas de ce format-----");
		}
		else
		{
			if(videoDAO.selectVideo(entityManager, nomVideo)==null || videoDAO.selectVideo(entityManager, nomVideo).size()==0)
			{
				Format format=formatDAO.selectFormat(entityManager, nomFormat).get(0);
				videoDAO.addVideo(entityManager, nomVideo, format);
			}
			else
			{
				System.out.println("----- video deja existe-----");
			}
		}
	}
	public VideoDAO getVideoDAO() {
		return videoDAO;
	}

	public void setVideoDAO(VideoDAO videoDAO) {
		this.videoDAO = videoDAO;
	}

	public FormatDAO getFormatDAO() {
		return formatDAO;
	}

	public void setFormatDAO(FormatDAO formatDAO) {
		this.formatDAO = formatDAO;
	}
	
	
}
