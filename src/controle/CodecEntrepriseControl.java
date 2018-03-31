package controle;

import java.util.List;

import javax.persistence.EntityManager;

import dao.CodecDAO;
import dao.CodecTypeDAO;
import dao.EntrepriseDAO;
import entite.Codec;
import entite.CodecType;
import entite.Entreprise;

public class CodecEntrepriseControl 
{
	private CodecDAO codecDAO=new CodecDAO();
	private EntrepriseDAO entrepriseDAO=new EntrepriseDAO();
	private CodecTypeDAO codecTypeDAO=new CodecTypeDAO();
	
	public void codecAddEntreprise(EntityManager entityManager,String nomCodec,String nomEntreprise)
	{
		if(codecDAO.select(entityManager, nomCodec)==null ||codecDAO.select(entityManager, nomCodec).size()==0)
		{
			System.out.println("----- error: pas de codec-----");
		}
		else if(entrepriseDAO.select(entityManager, nomEntreprise)==null || entrepriseDAO.select(entityManager, nomEntreprise).size()==0 )
		{
			System.out.println("-----error: pas de entreprise-----");
		}
		else 
		{
			Codec codec=codecDAO.select(entityManager, nomCodec).get(0);
			Entreprise entreprise=entrepriseDAO.select(entityManager, nomEntreprise).get(0);
			codecDAO.updateEntreprise(entityManager, nomCodec, entreprise);
			CodecType codecType=codecTypeDAO.select(entityManager, "commercial").get(0);
			codecDAO.updateCodecType(entityManager, nomCodec, codecType);
			//entrepriseDAO.addCodec(entityManager, nomEntreprise, codec);
		}		
	}
	
	public void deleteEntrepriseVerCodec(EntityManager entityManager,String nomEntreprise)
	{
		if(entrepriseDAO.select(entityManager, nomEntreprise)==null)
		{
			System.out.println("-----error: pas de entreprise-----");
		}
		else 
		{
			Entreprise entreprise=entrepriseDAO.select(entityManager, nomEntreprise).get(0);
			List<Codec> codecs=entreprise.getCodecs();
			for(int i=0;i<codecs.size();i++)
			{
				codecDAO.updateEntreprise(entityManager, codecs.get(i).getNomCodec(), null);
			}
		}		
		//entrepriseDAO.delete(entityManager, entreprise.getNomEntreprise());
	}
	
	public void codecChangeToOPen(EntityManager entityManager,String nomCodec)
	{
		if(codecDAO.select(entityManager, nomCodec)==null || codecDAO.select(entityManager, nomCodec).size()==0)
		{
			System.out.println("----- error: pas de codec-----");
		}
		else if(codecTypeDAO.select(entityManager, "nonCommercial")==null || codecTypeDAO.select(entityManager, "nonCommercial").size()==0)
		{
			System.out.println("-----error: pas de codecType-----");
		}
		else 
		{
			Codec codec=codecDAO.select(entityManager, nomCodec).get(0);
			CodecType codecType=codecTypeDAO.select(entityManager, "nonCommercial").get(0);
			codecDAO.updateEntreprise(entityManager, nomCodec, null);
			codecDAO.updateCodecType(entityManager, nomCodec, codecType);			
		}		
	}

	public void addCodec(EntityManager entityManager,String nomCodec,String fichier,String nomEntreprise)
	{
		if(codecDAO.select(entityManager, nomCodec)!=null && codecDAO.select(entityManager, nomCodec).size()!=0)
		{
			System.out.println("----- error: codec deja existe-----");
		}
		else
		{
			if (nomEntreprise==null)
			{
				CodecType codecType=codecTypeDAO.select(entityManager, "nonCommercial").get(0);
				codecDAO.addCodec(entityManager, nomCodec, fichier, null, null, codecType);
			}
			else
			{
				if(entrepriseDAO.select(entityManager, nomEntreprise)==null || entrepriseDAO.select(entityManager, nomEntreprise).size()==0 )
				{
					System.out.println("----- error: pas de ce entreprise-----");
				}
				else
				{
					Entreprise entreprise=entrepriseDAO.select(entityManager, nomEntreprise).get(0);
					CodecType codecType=codecTypeDAO.select(entityManager, "commercial").get(0);
					codecDAO.addCodec(entityManager, nomCodec, fichier, entreprise, null, codecType);
				}
			}
		}
	}
	public CodecDAO getCodecDAO() {
		return codecDAO;
	}

	public void setCodecDAO(CodecDAO codecDAO) {
		this.codecDAO = codecDAO;
	}

	public EntrepriseDAO getEntrepriseDAO() {
		return entrepriseDAO;
	}

	public void setEntrepriseDAO(EntrepriseDAO entrepriseDAO) {
		this.entrepriseDAO = entrepriseDAO;
	}
	
	
}
