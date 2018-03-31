package controle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainTpNote 
{
	public static void main(String[] args) 
	{
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("test");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		System.out.println("create tablespace successfully");
		
		/**-----pour executer-----*/
		DisplayControl  displayControl=new DisplayControl();
		displayControl.setEntityManager(entityManager);
		displayControl.runExe();
		System.out.println("-----run finished -----");
		
		/**-----pour tester-----*/
		//TestControl testControl=new TestControl();
		//testControl.setEntityManager(entityManager);
		//testControl.testExe();
		//System.out.println("-----test finished -----");
		
		entityManager.close();
		entityManagerFactory.close();
	}
}
