package bbva.delivery.services.service;

import bbva.delivery.services.bean.RequestChangeEstadoRegistro;
import bbva.delivery.services.bean.RequestGetVisitasUsuario;
import bbva.delivery.services.bean.RequestValidarCourier;
import bbva.delivery.services.bean.ResponseChangeEstadoRegistro;
import bbva.delivery.services.bean.ResponseGetVisitasUsuario;
import bbva.delivery.services.bean.ResponseObtenerListaCourier;
import bbva.delivery.services.bean.ResponseValidarCourier;
import bbva.delivery.services.bean.Usuario;

public interface DeliveryService {

	public void test();
	
	public void validarUsuarioToken(Usuario usuario) throws Exception;
	
	public ResponseValidarCourier validarDNICourier(RequestValidarCourier requestValidarCourier);
	
	public ResponseGetVisitasUsuario getVisitasUsuario(RequestGetVisitasUsuario requestGetVisitasUsuario);
	
	public ResponseChangeEstadoRegistro changeEstadoRegistro(RequestChangeEstadoRegistro requestChangeEstadoRegistro);
	
	public boolean validarUsuario(Usuario usuario) throws Exception;
	
	public ResponseObtenerListaCourier obtenerListaCourier();
	
	public Usuario addUsuario(Usuario usuario) throws Exception;

}