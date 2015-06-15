package bbva.delivery.services.dao.imp;

import org.springframework.stereotype.Repository;

import bbva.delivery.services.comun.dao.imp.JdbcDaoBase;
import bbva.delivery.services.dao.DeliveryDao;

@Repository("portalWebDao")
public class DeliveryDaoImp extends JdbcDaoBase implements DeliveryDao {
	
	
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("dao ok");

	}	
}

