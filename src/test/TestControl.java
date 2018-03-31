package test;

import javax.persistence.EntityManager;

import controle.ExecuteControl;

public class TestControl implements TestInterface
{
	private ExecuteControl executeControl=new ExecuteControl();
	private EntityManager entityManager;	
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void testExe()
	{
		int commande=1; // il peut changer
		//executeControl.select(commande, entityManager);
		//executeControl.insert(commande, entityManager);
		//executeControl.delete(commande, entityManager);
		//executeControl.update(commande, entityManager);
		//executeControl.afficher(commande, entityManager);
	}

	public ExecuteControl getExecuteControl() {
		return executeControl;
	}

	public void setExecuteControl(ExecuteControl executeControl) {
		this.executeControl = executeControl;
	}
	
}
