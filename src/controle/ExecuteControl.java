package controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import entite.Codec;
import entite.CodecType;
import entite.Entreprise;
import entite.Format;
import entite.Player;
import entite.Video;

public class ExecuteControl 
{
	private CodecEntrepriseControl codecEntrepriseControl=new CodecEntrepriseControl();
	private CodecTypeControl codecTypeControl=new CodecTypeControl();
	private FormatPlayerControl formatPlayerControl=new FormatPlayerControl();
	private PlayerCodecControl playerCodecControl=new PlayerCodecControl();
	private VideoFormatControl videoFormatControl =new VideoFormatControl();
	
	//insert function
	public void insert(int commande,EntityManager entityManager)
	{	
		if(commande==1) //1-video
		{
			System.out.println("add video:");
			System.out.println("video name (string):");
			Scanner in = new Scanner(System.in);
			String nomVideo=in.nextLine();
			System.out.println("format name (string):");
			String nomFormat=in.nextLine();
			if(!nomFormat.equals("null"))
			{
				//videoFormatControl.getVideoDAO().addVideo(entityManager, nomVideo, null);
				videoFormatControl.addVideo(entityManager, nomVideo, nomFormat);
			}
			else
			{
				System.out.println("-----error: format doesn't exist, we can't add the video -----");
			}
		}
		else if(commande==2) //2- player
		{
			System.out.println("add player:");
			System.out.println("player name (string): ");
			Scanner in = new Scanner(System.in);
			String nomPlayer=in.nextLine();
			System.out.println("player koctetsPlayer (int): ");
			int koctetsPlayer=in.nextInt();
			formatPlayerControl.getPlayerDAO().addPlayer(entityManager, nomPlayer, koctetsPlayer, null, null);
		}
		else if(commande==3)//3- format
		{
			System.out.println("add format");
			System.out.println("nomFormat (string): ");
			Scanner in = new Scanner(System.in);
			String nomFormat=in.nextLine();
			System.out.println("entensionFormat (string): ");
			String entensionFormat=in.nextLine();
			videoFormatControl.getFormatDAO().addFormat(entityManager, nomFormat, entensionFormat, null, null);
		}
		else if(commande==4) //4- codec
		{
			System.out.println("add codec :");
			System.out.println("nomCodec (string): ");
			Scanner in = new Scanner(System.in);
			String nomCodec=in.nextLine();
			System.out.println("fichierCodec (string): ");
			String fichierCodec=in.nextLine();
			System.out.println("open ou commercial (open/commercial): ");
			String ch=in.nextLine();
			if(ch.equals("open"))
			{
				codecEntrepriseControl.addCodec(entityManager, nomCodec, fichierCodec, null);
			}
			else if(ch.equals("commercial"))
			{
				System.out.println("entreprise (string)");
				String nomEntreprise=in.nextLine();
				codecEntrepriseControl.addCodec(entityManager, nomCodec, fichierCodec, nomEntreprise);
			}
			else {
				System.out.println("-----error: wrong command -----");
			}
		}
		else if(commande==5) //5- codecType
		{
			System.out.println("add codectype :");
			System.out.println("type de Codec (string): ");
			Scanner in = new Scanner(System.in);
			String type=in.nextLine();
			codecTypeControl.getCodecTypeDAO().add(entityManager, type, null);
		}
		else if(commande==6) //6- entreprise
		{
			System.out.println("add entreprise :");
			System.out.println("nomEntreprise (string): ");
			Scanner in = new Scanner(System.in);
			String nomEntreprise=in.nextLine();
			System.out.println("adresseWeb (string): ");
			String adresseWeb=in.nextLine();
			codecEntrepriseControl.getEntrepriseDAO().addEntreprise(entityManager, nomEntreprise, adresseWeb, null);
		}
	}
	
	//select function
	public void delete(int commande,EntityManager entityManager)
	{
		if(commande==1) //1-video
		{
			System.out.println("video name (string)");
			Scanner in = new Scanner(System.in);
			String nomVideo=in.nextLine();
			List<Video> videos=new ArrayList<Video>();
			if(videoFormatControl.getVideoDAO().selectVideo(entityManager, nomVideo)==null || videoFormatControl.getVideoDAO().selectVideo(entityManager, nomVideo).size()==0)
			{
				System.out.println("----- video doesn't exist-----");
			}
			else
			{
				videos=videoFormatControl.getVideoDAO().selectVideo(entityManager, nomVideo);
				Video video=videos.get(0);
				//if(video.getFormat()!=null)
				//{
					//Format format=video.getFormat();
					//videoFormatControl.formatDeleteVideo(entityManager, nomVideo, format.getNomFormat());
				//}
				videoFormatControl.getVideoDAO().deleteVideo(entityManager, nomVideo);
			}
		}
		else if(commande==2) //2- player
		{
			System.out.println("player name (string)");
			Scanner in = new Scanner(System.in);
			String nomPlayer=in.nextLine();
			List<Player> players=new ArrayList<Player>();
			if(playerCodecControl.getPlayerDAO().selectPlayer(entityManager, nomPlayer)==null || playerCodecControl.getPlayerDAO().selectPlayer(entityManager, nomPlayer).size()==0)
			{
				System.out.println("----- player doesn't exist-----");
			}
			else 
			{
				Player player=playerCodecControl.getPlayerDAO().selectPlayer(entityManager, nomPlayer).get(0);
				playerCodecControl.getPlayerDAO().deletePlayer(entityManager, player.getNomPlayer());
			}
		}
		else if(commande==3)//3- format si format supprime le video supprime
		{
			System.out.println("format name (string)");
			Scanner in = new Scanner(System.in);
			String nomFormat=in.nextLine();
			List<Format> players=new ArrayList<Format>();
			if(formatPlayerControl.getFormatDAO().selectFormat(entityManager, nomFormat)==null || formatPlayerControl.getFormatDAO().selectFormat(entityManager, nomFormat).size()==0)
			{
				System.out.println("----- format doesn't exist-----");
			}
			else 
			{
				Format format=formatPlayerControl.getFormatDAO().selectFormat(entityManager, nomFormat).get(0);
				formatPlayerControl.getFormatDAO().deleteFormat(entityManager, format.getNomFormat());
			}
		}
		else if(commande==4) //4- codec
		{
			System.out.println("codec name (string)");
			Scanner in = new Scanner(System.in);
			String nomCodec=in.nextLine();
			List<Codec> codecs=new ArrayList<Codec>();
			if(codecTypeControl.getCodecDAO().select(entityManager, nomCodec)==null || codecTypeControl.getCodecDAO().select(entityManager, nomCodec).size()==0)
			{
				System.out.println("----- codec doesn't exist-----");
			}
			else 
			{
				Codec codec=codecTypeControl.getCodecDAO().select(entityManager, nomCodec).get(0);
				codecTypeControl.getCodecDAO().delete(entityManager, codec.getNomCodec());
			}
		}
		else if(commande==5) //5- codecType
		{
			System.out.println("codecType name (string)");
			Scanner in = new Scanner(System.in);
			String type=in.nextLine();
			List<CodecType> codecTypes=new ArrayList<CodecType>();
			if(codecTypeControl.getCodecTypeDAO().select(entityManager, type)==null || codecTypeControl.getCodecTypeDAO().select(entityManager, type).size()==0)
			{
				System.out.println("----- player doesn't exist-----");
			}
			else 
			{
				CodecType codecType=codecTypeControl.getCodecTypeDAO().select(entityManager, type).get(0);
				codecTypeControl.getCodecTypeDAO().delete(entityManager, type);
			}
		}
		else if(commande==6) //6- entreprise
		{
			System.out.println("Entreprise name (string)");
			Scanner in = new Scanner(System.in);
			String nomEntreprise=in.nextLine();
			List<Entreprise> videos=new ArrayList<Entreprise>();
			if(codecEntrepriseControl.getEntrepriseDAO().select(entityManager, nomEntreprise)==null || codecEntrepriseControl.getEntrepriseDAO().select(entityManager, nomEntreprise).size()==0)
			{
				System.out.println("----- entreprise doesn't exist-----");
			}
			else
			{
				Entreprise entreprise=codecEntrepriseControl.getEntrepriseDAO().select(entityManager, nomEntreprise).get(0);
				codecEntrepriseControl.getEntrepriseDAO().delete(entityManager, entreprise.getNomEntreprise());
			}
		}
	}
	
	//select function
	public void select(int commande,EntityManager entityManager)
	{
		if(commande==1)
		{
			System.out.println("select video by the name of the video :");
			System.out.println("video name (string)");
			Scanner in = new Scanner(System.in);
			String nomVideo=in.nextLine();
			List<Video> videos=new ArrayList<Video>();
			if(videoFormatControl.getVideoDAO().selectVideo(entityManager, nomVideo)==null || videoFormatControl.getVideoDAO().selectVideo(entityManager, nomVideo).size()==0)
			{
				System.out.println("----- video doesn't exist-----");
			}
			else
			{
				videos=videoFormatControl.getVideoDAO().selectVideo(entityManager, nomVideo);
				Video video=videos.get(0);
				if(video.getFormat()==null || video.getFormat().getNomFormat()==null)
				{
					System.out.println("video: "+video.getNomVideo()+" "+"format: "+"null");
				}
				else 
				{
					System.out.println("video: "+video.getNomVideo()+" "+"format: "+video.getFormat().getNomFormat());
				}
				
			}
		}
		else if(commande==2)
		{
			System.out.println("select player by the name of the format :");
			System.out.println("format name (string)");
			Scanner in = new Scanner(System.in);
			String nomFormat=in.nextLine();
			List<Player> players=new ArrayList<Player>();
			if(videoFormatControl.getFormatDAO().selectFormat(entityManager, nomFormat)==null || videoFormatControl.getFormatDAO().selectFormat(entityManager, nomFormat).size()==0)
			{
				System.out.println("----- format doesn't exist-----");
			}
			else
			{
				Format format=videoFormatControl.getFormatDAO().selectFormat(entityManager, nomFormat).get(0);
				if(formatPlayerControl.getPlayerFormatDAO().selectPlayer(entityManager, nomFormat)==null || formatPlayerControl.getPlayerFormatDAO().selectPlayer(entityManager, nomFormat).size()==0)
				{
					System.out.println("-----corresponding player doesn't exist-----");
				}
				else
				{
					players=formatPlayerControl.getPlayerFormatDAO().selectPlayer(entityManager, nomFormat);
					for(int i=0;i<players.size();i++)
					{
						System.out.println("player: " + players.get(i).getNomPlayer()+"   "+"koctetsPlayer: "+players.get(i).getKoctetsPlayer());
					}
				}
			}
		}
		else if(commande==3)
		{
			System.out.println("select format by the name of the video :");
			System.out.println("video name (string): ");
			Scanner in = new Scanner(System.in);
			String nomVideo=in.nextLine();
			if(videoFormatControl.getVideoDAO().selectVideo(entityManager, nomVideo)==null || videoFormatControl.getVideoDAO().selectVideo(entityManager, nomVideo).size()==0)
			{
				System.out.println("-----corresponding video doesn't exist-----");
			}
			else
			{
				Video video=videoFormatControl.getVideoDAO().selectVideo(entityManager, nomVideo).get(0);
				if(video.getFormat()==null)
				{
					System.out.println("the format of this video is: "+"null");
				}
				else
				{
					System.out.println("the format of this video is: "+video.getFormat().getNomFormat());
				}			
			}
		}
		else if(commande==4)
		{
			System.out.println("select codec by the name of the player :");
			System.out.println("Player name (string): ");
			Scanner in = new Scanner(System.in);
			String nomPlayer=in.nextLine();
			if(playerCodecControl.getPlayerDAO().selectPlayer(entityManager, nomPlayer)==null || playerCodecControl.getPlayerDAO().selectPlayer(entityManager, nomPlayer).size()==0)
			{
				System.out.println("-----corresponding player doesn't exist-----");
			}
			else
			{
				Player player=playerCodecControl.getPlayerDAO().selectPlayer(entityManager, nomPlayer).get(0);
				List<Codec> codecs=new ArrayList<Codec>();
				if(playerCodecControl.getPlayerCodecDAO().selectCodec(entityManager, nomPlayer)==null || playerCodecControl.getPlayerCodecDAO().selectCodec(entityManager, nomPlayer).size()==0)
				{
					System.out.println("-----corresponding codec doesn't exist-----");
				}
				else
				{
					codecs=playerCodecControl.getPlayerCodecDAO().selectCodec(entityManager, nomPlayer);
					for(int j=0;j<codecs.size();j++)
					{
						System.out.println("player: " + nomPlayer+"   "+"codec: "+codecs.get(j).getNomCodec());
					}
				}
			}
		}
		else if(commande==5)
		{
			System.out.println("select codectype by the name of the codec :");
			System.out.println("codec name (string)");
			Scanner in = new Scanner(System.in);
			String nomCodec=in.nextLine();
			if(codecEntrepriseControl.getCodecDAO().select(entityManager, nomCodec)==null || codecEntrepriseControl.getCodecDAO().select(entityManager, nomCodec).size()==0)
			{
				System.out.println("-----corresponding codec doesn't exist-----");
			}
			else
			{
				Codec codec=codecEntrepriseControl.getCodecDAO().select(entityManager, nomCodec).get(0);
				System.out.println("type of this codec: "+ codec.getCodecType().getType());
			}
		}
		else if(commande==6)
		{
			System.out.println("select entreprise by the name of the codec :");
			System.out.println("codec name (string)");
			Scanner in = new Scanner(System.in);
			String nomCodec=in.nextLine();
			if(codecEntrepriseControl.getCodecDAO().select(entityManager, nomCodec)==null || codecEntrepriseControl.getCodecDAO().select(entityManager, nomCodec).size()==0)
			{
				System.out.println("-----corresponding codec doesn't exist-----");
			}
			else
			{
				Codec codec=codecEntrepriseControl.getCodecDAO().select(entityManager, nomCodec).get(0);
				if(codec.getCodecType().getType().equals("commercial"))
				{
					System.out.println("codec source entreprise: "+codec.getEntreprise().getNomEntreprise() + " "+"entreprise web: "+codec.getEntreprise().getAdresseWeb());
				}
				else
				{
					System.out.println("-----codec open source-----");
				}
			}
		}
		else
		{
			System.out.println("select player and codec by the name of the video :");
			System.out.println("video name (string)");
			Scanner in = new Scanner(System.in);
			String nomVideo=in.nextLine();
			if(videoFormatControl.getVideoDAO().selectVideo(entityManager, nomVideo)==null || videoFormatControl.getVideoDAO().selectVideo(entityManager, nomVideo).size()==0)
			{
				System.out.println("-----corresponding video doesn't exist-----");
			}
			else
			{
				Format format=videoFormatControl.getVideoDAO().selectVideo(entityManager, nomVideo).get(0).getFormat();
				if(videoFormatControl.getVideoDAO().selectVideo(entityManager, nomVideo).get(0).getFormat()==null)
				{
					System.out.println("-----on ne sait pas le format de cette video, il faut ajouter le format de cette video-----");
				}
				else
				{
					String nomFormat=format.getNomFormat();
					List<Player> players=new ArrayList<Player>();
					if(formatPlayerControl.getPlayerFormatDAO().selectPlayer(entityManager, nomFormat)==null || formatPlayerControl.getPlayerFormatDAO().selectPlayer(entityManager, nomFormat).size()==0)
					{
						System.out.println("-----corresponding player doesn't exist-----");
					}
					else 
					{
						players=formatPlayerControl.getPlayerFormatDAO().selectPlayer(entityManager, nomFormat);
						//System.out.println(players.size());
						List<Codec> codecs=new ArrayList<Codec>();
						for(int i=0;i<players.size();i++)
						{
							List<Codec> temp=playerCodecControl.getPlayerCodecDAO().selectCodec(entityManager, players.get(i).getNomPlayer());
							if(playerCodecControl.getPlayerCodecDAO().selectCodec(entityManager, players.get(i).getNomPlayer())==null)
							{
								System.out.println("-----corresponding codec doesn't exist-----");
							}
							else
							{
								codecs=playerCodecControl.getPlayerCodecDAO().selectCodec(entityManager, players.get(i).getNomPlayer());
								for(int j=0;j<codecs.size();j++)
								{
									System.out.println("player: " + players.get(i).getNomPlayer()+"   "+"codec: "+codecs.get(j).getNomCodec());
								}
							}
						}
					}
				}			
			}
		}
	}
	
	//update function
	public void update(int commande,EntityManager entityManager)
	{	
		if(commande==1) //1- video add format
		{
			System.out.println("1- video add format: ");
			System.out.println("video name (string): ");
			Scanner in = new Scanner(System.in);
			String nomVideo=in.nextLine();
			System.out.println("format name (string)");
			String nomFormat=in.nextLine();
			videoFormatControl.VideoAddFormat(entityManager, nomVideo, nomFormat);
		}
		else if(commande==2) //2- player add format
		{
			System.out.println("2- player add format: ");
			System.out.println("player name (string)");
			Scanner in = new Scanner(System.in);
			String nomPlayer=in.nextLine();
			System.out.println("format name (string)");
			String nomFormat=in.nextLine();
			formatPlayerControl.FormatAddPlayer(entityManager, nomFormat, nomPlayer);
		}
		else if(commande==3)//3- player add codec
		{
			System.out.println("3- player add codec: ");
			System.out.println("player name (string)");
			Scanner in = new Scanner(System.in);
			String nomPlayer=in.nextLine();
			System.out.println("codec name (string)");
			String nomCodec=in.nextLine();
			playerCodecControl.playerAddCodec(entityManager, nomPlayer, nomCodec);
		}
		else if(commande==4) //4- codec add codecType
		{
			System.out.println("4- codec add codecType: ");
			System.out.println("codec name (string)");
			Scanner in = new Scanner(System.in);
			String nomCodec=in.nextLine();
			System.out.println("codecType name (string)");
			String type=in.nextLine();
			if(type.equals("nonCommercial"))
			{
				codecTypeControl.codecAddType(entityManager, nomCodec, type);
			}
			else if(type.equals("commercial"))
			{
				System.out.println("entreprise name (string)");
				String nomEntreprise=in.nextLine();
				codecEntrepriseControl.codecAddEntreprise(entityManager, nomCodec, nomEntreprise);
			}
			else {
				System.out.println("-----error: wrong command -----");
			}
		}
		else if(commande==5) //5- codec add/change entreprise
		{
			System.out.println("5- codec add/change entreprise: ");
			System.out.println("codec name (string)");
			Scanner in = new Scanner(System.in);
			String nomCodec=in.nextLine();
			System.out.println("entreprise name (string)");
			String nomEntreprise=in.nextLine();
			codecEntrepriseControl.codecAddEntreprise(entityManager, nomCodec, nomEntreprise);
		}
		else if(commande==6) //6- video delete format 
		{
			System.out.println("6- video delete format: ");
			System.out.println("video name (string)");
			Scanner in = new Scanner(System.in);
			String nomVideo=in.nextLine();
			System.out.println("format name (string)");
			String nomFormat=in.nextLine();
			videoFormatControl.formatDeleteVideo(entityManager, nomVideo, nomFormat);
		}
		else if(commande==7) //7-change le codec to open
		{
			System.out.println("7-change the codec to open: ");
			System.out.println("codec (string)");
			Scanner in = new Scanner(System.in);
			String nomCodec=in.nextLine();
			codecEntrepriseControl.codecChangeToOPen(entityManager, nomCodec);
			
		}
		else //8- player delete codec
		{
			System.out.println("8- player delete codec: ");
			System.out.println("player name (string)");
			Scanner in = new Scanner(System.in);
			String nomPlayer=in.nextLine();
			System.out.println("codec name (string)");
			String nomCodec=in.nextLine();
			playerCodecControl.playerDeleteCodec(entityManager, nomPlayer, nomCodec);
		}
	}
	
	//afficher function
	public void afficher(int commande,EntityManager entityManager)
	{
		if(commande==1) //1-video
		{
			System.out.println("show the infomation by the name of the video: ");
			System.out.println("video name (string)");
			Scanner in = new Scanner(System.in);
			String nomVideo=in.nextLine();
			if(videoFormatControl.getVideoDAO().selectVideo(entityManager, nomVideo)==null || videoFormatControl.getVideoDAO().selectVideo(entityManager, nomVideo).size()==0) 
			{
				System.out.println("----- corresponding video doesn't exist-----");
			}
			else
			{
				Video video=videoFormatControl.getVideoDAO().selectVideo(entityManager, nomVideo).get(0);
				if(video.getFormat() == null)
				{
					System.out.println("video name: "+video.getNomVideo() + " "+" format: "+"null");
				}
				else
				{
					Format format=video.getFormat();
					System.out.println("video name: "+video.getNomVideo() + " "+" format: "+format.getNomFormat());
				}
				
			}
			
		}
		else if(commande==2) //2- player
		{
			System.out.println("show the infomation by the name of the player: ");
			System.out.println("player name (string)");
			Scanner in = new Scanner(System.in);
			String nomPlayer=in.nextLine();
			if(formatPlayerControl.getPlayerDAO().selectPlayer(entityManager, nomPlayer)==null || formatPlayerControl.getPlayerDAO().selectPlayer(entityManager, nomPlayer).size()==0)
			{
				System.out.println("----- corresponding player doesn't exist-----");
			}
			else
			{
				Player player= formatPlayerControl.getPlayerDAO().selectPlayer(entityManager, nomPlayer).get(0);
				System.out.println("player: "+player.getNomPlayer() + " "+" kocket: "+player.getKoctetsPlayer());
			}
		}
		else if(commande==3)//3- format
		{
			System.out.println("show the infomation by the name of the format: ");
			System.out.println("format name (string)");
			Scanner in = new Scanner(System.in);
			String nomFormat=in.nextLine();
			if(formatPlayerControl.getFormatDAO().selectFormat(entityManager, nomFormat)==null || formatPlayerControl.getFormatDAO().selectFormat(entityManager, nomFormat).size()==0)
			{
				System.out.println("----- corresponding format doesn't exist-----");
			}
			else
			{
				Format format=formatPlayerControl.getFormatDAO().selectFormat(entityManager, nomFormat).get(0);
				System.out.println("format: "+format.getNomFormat() + " "+" entension: "+format.getEntensionFormat());
			}
		}
		else if(commande==4) //4- codec
		{
			System.out.println("show the infomation by the name of the codec : ");
			System.out.println("codec name (string)");
			Scanner in = new Scanner(System.in);
			String nomCodec=in.nextLine();
			if(codecTypeControl.getCodecDAO().select(entityManager, nomCodec)==null || codecTypeControl.getCodecDAO().select(entityManager, nomCodec).size()==0)
			{
				System.out.println("----- corresponding codec doesn't exist-----");
			}
			else
			{
				Codec codec=codecTypeControl.getCodecDAO().select(entityManager, nomCodec).get(0);
				if(codec.getCodecType().getType().equals("commercial"))
				{
					System.out.println("codec: "+codec.getNomCodec() + " "+" fichier: "+codec.getFichierCodec()+" "+"entreprise: "+codec.getEntreprise().getNomEntreprise());
				}
				else 
				{
					System.out.println("codec: "+codec.getNomCodec() + " "+" fichier: "+codec.getFichierCodec()+" "+"type: "+"codec open sources");
				}
			}
		}
		else if(commande==5) //5- codecType
		{
			System.out.println("show all the type of the codec: ");
			if(codecTypeControl.getCodecTypeDAO().selectTousLesType(entityManager)==null || codecTypeControl.getCodecTypeDAO().selectTousLesType(entityManager).size()==0)
			{
				System.out.println("----- corresponding codec doesn't exist-----");
			}
			else
			{
				List<CodecType> codecTypes=codecTypeControl.getCodecTypeDAO().selectTousLesType(entityManager);
				for(int i=0;i<codecTypes.size();i++)
				{
					System.out.println("codec type: "+codecTypes.get(i).getType());
				}
			}
		}
		else if(commande==6) //6- entreprise
		{
			System.out.println("show the infomation by the name of the entreprise: ");
			System.out.println("entreprise name (string): ");
			Scanner in = new Scanner(System.in);
			String nomEntreprise=in.nextLine();
			if(codecEntrepriseControl.getEntrepriseDAO().select(entityManager, nomEntreprise)==null || codecEntrepriseControl.getEntrepriseDAO().select(entityManager, nomEntreprise).size()==0)
			{
				System.out.println("----- corresponding entreprise doesn't exist-----");
			}
			else 
			{
				Entreprise entreprise=codecEntrepriseControl.getEntrepriseDAO().select(entityManager, nomEntreprise).get(0);
				System.out.println("entreprise nom: "+entreprise.getNomEntreprise()+" "+" adresse web: "+entreprise.getAdresseWeb());
			}
		}
	}
	public CodecEntrepriseControl getCodecEntrepriseControl() {
		return codecEntrepriseControl;
	}
	public void setCodecEntrepriseControl(CodecEntrepriseControl codecEntrepriseControl) {
		this.codecEntrepriseControl = codecEntrepriseControl;
	}
	public CodecTypeControl getCodecTypeControl() {
		return codecTypeControl;
	}
	public void setCodecTypeControl(CodecTypeControl codecTypeControl) {
		this.codecTypeControl = codecTypeControl;
	}
	public FormatPlayerControl getFormatPlayerControl() {
		return formatPlayerControl;
	}
	public void setFormatPlayerControl(FormatPlayerControl formatPlayerControl) {
		this.formatPlayerControl = formatPlayerControl;
	}
	public PlayerCodecControl getPlayerCodecControl() {
		return playerCodecControl;
	}
	public void setPlayerCodecControl(PlayerCodecControl playerCodecControl) {
		this.playerCodecControl = playerCodecControl;
	}
	public VideoFormatControl getVideoFormatControl() {
		return videoFormatControl;
	}
	public void setVideoFormatControl(VideoFormatControl videoFormatControl) {
		this.videoFormatControl = videoFormatControl;
	}
}
