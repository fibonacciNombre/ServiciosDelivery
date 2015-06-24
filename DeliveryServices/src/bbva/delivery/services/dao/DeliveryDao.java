package bbva.delivery.services.dao;

import java.util.List;

import bbva.delivery.services.bean.Courier;
import bbva.delivery.services.bean.Delivery;
import bbva.delivery.services.bean.Usuario;

public interface DeliveryDao{
	void test();
	
	public void validarUsuarioToken(Usuario usuario);
	
	public List<Delivery> lstDelivery(Delivery param);
	
	public boolean validarUsuario(Usuario usuario) throws Exception;
	
	public List<Courier> obtenerListaCourier();
	
	public Usuario addUsuario(Usuario usuario);
	
	public Usuario getUsuario(Usuario usuario);
	
	public Usuario obtUsuario(Integer id);
}
