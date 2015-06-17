package bbva.delivery.services.service;

import bbva.delivery.services.bean.Usuario;

public interface DeliveryService {

	public void test();
	
	public void validarUsuarioToken(Usuario usuario) throws Exception;

}