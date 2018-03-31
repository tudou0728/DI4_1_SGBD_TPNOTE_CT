package controle;

import java.util.Scanner;

import javax.persistence.EntityManager;

public class DisplayControl
{
	private ExecuteControl executeControl=new ExecuteControl();
	private EntityManager entityManager;
	
	public void runExe() 
	{
		if(executeControl.getCodecTypeControl().getCodecTypeDAO().select(entityManager, "commercial")==null || executeControl.getCodecTypeControl().getCodecTypeDAO().select(entityManager, "commercial").size()==0)
		{
			executeControl.getCodecTypeControl().getCodecTypeDAO().add(entityManager, "commercial", null);
		}
		if(executeControl.getCodecTypeControl().getCodecTypeDAO().select(entityManager, "nonCommercial")==null || executeControl.getCodecTypeControl().getCodecTypeDAO().select(entityManager, "nonCommercial").size()==0)
		{
			executeControl.getCodecTypeControl().getCodecTypeDAO().add(entityManager, "nonCommercial", null);
		}	
		System.out.println("Welcome user: ");
		Scanner in = new Scanner(System.in);
		String user=in.nextLine();
		while(!user.equals("quit"))//!user.equals("quit")
		{
			System.out.println("1- insert");
			System.out.println("2- select");
			System.out.println("3- delete");
			System.out.println("4- update");
			System.out.println("5- show some infos");
			System.out.println("your command:");
			Scanner in2 = new Scanner(System.in);
			try 
			{
				int commande=in2.nextInt();
				if(commande==1)
				{
					int choisir=chosirCommande();
					if(0<choisir && choisir<7)
					{
						executeControl.insert(choisir,this.entityManager);
					}
					else
					{
						System.out.println("----- wrong command -----");
					}
				}
				else if(commande==2)
				{
					int choisir=chosirCommande();
					if(0<choisir && choisir<8)
					{
						executeControl.select(choisir,this.entityManager);
					}
					else
					{
						System.out.println("----- wrong command -----");
					}
				}
				else if(commande==3)
				{
					int choisir=chosirCommande();
					if(0<choisir && choisir<7)
					{
						executeControl.delete(choisir,this.entityManager);
					}
					else
					{
						System.out.println("----- wrong command -----");
					}
				}
				else if(commande==4)
				{
					int choisir=updateDisplay();
					if(0<choisir && choisir<9)
					{
						executeControl.update(choisir,this.entityManager);
					}				
					else
					{
						System.out.println("----- wrong command -----");
					}
				}
				else if(commande==5)
				{
					int choisir=chosirCommande();
					if(0<choisir && choisir<7)
					{
						executeControl.afficher(choisir,this.entityManager);
					}
					else
					{
						System.out.println("----- wrong command -----");
					}
				}
				else 
				{
					System.out.println("----- wrong command -----");
				}
				System.out.println("");
				System.out.println("quit or not?");
				Scanner in3 = new Scanner(System.in);
				user=in3.nextLine();
			}		
			catch(Exception e)
			{
				System.out.println("----- wrong command -----");
			}
		}
		System.exit(0);
	}
	
	public int chosirCommande()
	{
		System.out.println("1- video");
		System.out.println("2- player");
		System.out.println("3- format");
		System.out.println("4- codec");
		System.out.println("5- codecType");
		System.out.println("6- entreprise");
		System.out.println("7- PlayerEtCodec");
		System.out.println("your command:");
		Scanner in = new Scanner(System.in);
		int commande=in.nextInt();
		return commande;
	}

	public ExecuteControl getExecuteControl() {
		return executeControl;
	}

	public void setExecuteControl(ExecuteControl executeControl) {
		this.executeControl = executeControl;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public int updateDisplay()
	{
		System.out.println("1- video add/change format");
		System.out.println("2- player add format");
		System.out.println("3- player add codec");
		System.out.println("4- codec add codecType");
		System.out.println("5- codec add entreprise");
		System.out.println("6- video delete format");
		System.out.println("7- change the codec to open");
		System.out.println("8- player delete codec");
		System.out.println("your command:");
		Scanner in = new Scanner(System.in);
		int commande=in.nextInt();
		return commande;
	}
}
