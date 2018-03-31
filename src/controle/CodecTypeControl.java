package controle;

import java.util.List;

import javax.persistence.EntityManager;

import dao.CodecDAO;
import dao.CodecTypeDAO;
import entite.Codec;
import entite.CodecType;

public class CodecTypeControl 
{
	private CodecDAO codecDAO=new CodecDAO();
	private CodecTypeDAO codecTypeDAO=new CodecTypeDAO();
	
	public void codecAddType(EntityManager entityManager,String nomCodec,String type)
	{
		List<Codec> codecs=codecDAO.select(entityManager, nomCodec);
		List<CodecType> codecTypes=codecTypeDAO.select(entityManager, type);
		if(codecs.isEmpty() || codecTypes.isEmpty())
		{
			System.out.println("----- codecs ou type est null -----");
		}
		else
		{
			Codec codec=codecs.get(0);
			CodecType codecType=codecTypes.get(0);
			codecDAO.updateCodecType(entityManager, nomCodec, codecType);
			//codecTypeDAO.addCodec(entityManager, type, codec);
			System.out.println("----- ajoute codec et type -----");
		}
	}

	public CodecDAO getCodecDAO() {
		return codecDAO;
	}

	public void setCodecDAO(CodecDAO codecDAO) {
		this.codecDAO = codecDAO;
	}

	public CodecTypeDAO getCodecTypeDAO() {
		return codecTypeDAO;
	}

	public void setCodecTypeDAO(CodecTypeDAO codecTypeDAO) {
		this.codecTypeDAO = codecTypeDAO;
	}
	
	
}
