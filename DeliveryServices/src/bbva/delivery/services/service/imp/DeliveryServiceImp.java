package bbva.delivery.services.service.imp;

import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bbva.delivery.services.bean.Courier;
import bbva.delivery.services.bean.Delivery;
import bbva.delivery.services.bean.EstadoRegistro;
import bbva.delivery.services.bean.RequestChangeEstadoRegistro;
import bbva.delivery.services.bean.RequestGetVisitasUsuario;
import bbva.delivery.services.bean.RequestValidarCourier;
import bbva.delivery.services.bean.ResponseChangeEstadoRegistro;
import bbva.delivery.services.bean.ResponseGetVisitasUsuario;
import bbva.delivery.services.bean.ResponseObtenerListaCourier;
import bbva.delivery.services.bean.ResponseValidarCourier;
import bbva.delivery.services.bean.Tx;
import bbva.delivery.services.bean.Usuario;
import bbva.delivery.services.bean.ValidarCourier;
import bbva.delivery.services.bean.VisitasUsuario;
import bbva.delivery.services.dao.DeliveryDao;
import bbva.delivery.services.dao.imp.DeliveryDaoImp;
import bbva.delivery.services.service.DeliveryService;


@Service("deliveryService")
@Transactional(propagation=Propagation.SUPPORTS)
public class DeliveryServiceImp implements DeliveryService {
	
	// Definición del tipo de algoritmo a utilizar (AES, DES, RSA)
	private final static String ALG = "AES";
	// Definición del modo de cifrado a utilizar
    private final static String CI = "AES/CBC/PKCS5Padding";
    
    private final static String KEY = "92AE31A79FEEB2A3"; //llave
    
    private final static String IV = "0123456789ABCDEF"; // vector de inicialización
    
	@Autowired
	private DeliveryDao deliveryDao;

	
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("service ok");
		
		deliveryDao.test();
	}
	
	public void validarUsuarioToken(Usuario usuario) throws Exception{
		
		DeliveryDaoImp daoImp = new DeliveryDaoImp();
		
		String usuarioPassword = null;
		String cadenaEncriptada = null;
		String cadenaDesencriptada = null;
		
		String token = UUID.randomUUID().toString();
		System.out.println("token random --> "+ token);
		daoImp.validarUsuarioToken(usuario);
		
		usuarioPassword = usuario.getUsuario()+":"+usuario.getContrasena();
		
		cadenaEncriptada = this.encriptar(KEY, IV, usuarioPassword);
		
		cadenaDesencriptada = this.desencriptar(KEY, IV, cadenaEncriptada);
		
		System.out.println("Encrip --> "+ cadenaEncriptada);
		System.out.println("Desencrip --> "+ cadenaDesencriptada);
		
	}
	
	public String encriptar(String key, String iv, String cleartext) throws Exception{
		
		 Cipher cipher = Cipher.getInstance(CI);
         SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), ALG);
         IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
         cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
         byte[] encrypted = cipher.doFinal(cleartext.getBytes());
         return new String(encodeBase64(encrypted));
		
	}
	
	public String desencriptar(String key, String iv, String encrypted) throws Exception{
		 Cipher cipher = Cipher.getInstance(CI);
         SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), ALG);
         IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
         byte[] enc = decodeBase64(encrypted);
         cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
         byte[] decrypted = cipher.doFinal(enc);
         return new String(decrypted);
	}
	
	public boolean validarUsuario(Usuario usuario) throws Exception{
//		Delivery param = new Delivery();
//		deliveryDao.lstDelivery(param);
		Usuario usr = deliveryDao.getUsuario(usuario);
		
		if(usr != null){
			if(usuario.getCodusuario().equals(usr.getCodusuario()) && usuario.getContrasena().equals(usr.getContrasena())){
				return true;
			}
		}
		return false;
		
		//return deliveryDao.validarUsuario(usuario);
	}
	
	public ResponseValidarCourier validarDNICourier(RequestValidarCourier requestValidarCourier){
		Delivery j = new Delivery();
		deliveryDao.lstDelivery(j);
		ResponseValidarCourier responseValidarCourier = new ResponseValidarCourier();
		if(requestValidarCourier == null || requestValidarCourier.getCourier() == null || requestValidarCourier.getDni() == null){
			Tx tx = new Tx();
			tx.setCodigo("1");
			tx.setMensaje("error");
			responseValidarCourier.setTx(tx);
			return responseValidarCourier;
		}else{
			
			if("Elvis".equals(requestValidarCourier.getCourier()) && "12345678".equals(requestValidarCourier.getDni())){
				
				Tx tx = new Tx();
				tx.setCodigo("0");
				tx.setMensaje("correcto");
				ValidarCourier validarCourier = new ValidarCourier();
				validarCourier.setCodigo("000");
				validarCourier.setMensaje("Usuario Activo");
				responseValidarCourier.setTx(tx);
				responseValidarCourier.setValidarCourier(validarCourier);
			
				return responseValidarCourier;
			}else{
				Tx tx = new Tx();
				tx.setCodigo("0");
				tx.setMensaje("correcto");
				ValidarCourier validarCourier = new ValidarCourier();
				validarCourier.setCodigo("001");
				validarCourier.setMensaje("Usuario no existe");
				responseValidarCourier.setTx(tx);
				responseValidarCourier.setValidarCourier(validarCourier);
				return responseValidarCourier;
			}
		}
	}
	
	public ResponseGetVisitasUsuario getVisitasUsuario(RequestGetVisitasUsuario requestGetVisitasUsuario){
		
		ResponseGetVisitasUsuario responseGetVisitasUsuario =  new ResponseGetVisitasUsuario();
		
		Tx tx = new Tx();
		tx.setCodigo("0");
		tx.setMensaje("correcto");
		VisitasUsuario visitasUsuario = new VisitasUsuario();
		visitasUsuario.setDni(requestGetVisitasUsuario.getDni());
		visitasUsuario.setCoordenadas("1234");
		visitasUsuario.setCoordenadas("7845");
		visitasUsuario.setDireccion("Av. Dean Valdivia");
		visitasUsuario.setDistrito("San Isiadro");
		visitasUsuario.setFechaEntrega("28/06/2015");
		visitasUsuario.setHorarioEntrega("15-16 horas");
		visitasUsuario.setLineaCredito("5000");
		visitasUsuario.setNombres(requestGetVisitasUsuario.getCourier());
		visitasUsuario.setPrimDigitosTar("4250");
		visitasUsuario.setTipoTarjeta("Signature");
		visitasUsuario.setUltiDigitosTar("1420");
		visitasUsuario.setCodigoEntrega("666");
		
		responseGetVisitasUsuario.setVisitasUsuario(visitasUsuario);
		responseGetVisitasUsuario.setTx(tx);
		return responseGetVisitasUsuario;
	}
	
	public ResponseChangeEstadoRegistro changeEstadoRegistro(RequestChangeEstadoRegistro requestChangeEstadoRegistro){
		
		ResponseChangeEstadoRegistro responseChangeEstadoRegistro =  new ResponseChangeEstadoRegistro();
		Tx tx = new Tx();
		tx.setCodigo("0");
		tx.setMensaje("correcto");
		EstadoRegistro estadoRegistro = new EstadoRegistro();
		estadoRegistro.setCodigo("0");
		estadoRegistro.setMensaje("Entregado sin inconvenientes");
		responseChangeEstadoRegistro.setTx(tx);
		responseChangeEstadoRegistro.setEstadoRegistro(estadoRegistro);
		return responseChangeEstadoRegistro;
	}

	public ResponseObtenerListaCourier obtenerListaCourier(){
		ResponseObtenerListaCourier responseObtenerListaCourier = new ResponseObtenerListaCourier();
		Tx tx = new Tx();
		tx.setCodigo("0");
		tx.setMensaje("correcto");
		List<Courier> couriers = new ArrayList<Courier>();
//		Courier courier = new Courier();
//		courier.setIdCourier(363);
//		courier.setCodBbva("738393");
//		courier.setNombre("Casaca de Matraca");
//		couriers.add(courier);
//		Courier courier2 = new Courier();
//		courier2.setIdCourier(364);
//		courier2.setCodBbva("738394");
//		courier2.setNombre("Jon Snow");
//		couriers.add(courier2);
		
		couriers = deliveryDao.obtenerListaCourier();
		responseObtenerListaCourier.setListaCourier(couriers);
		responseObtenerListaCourier.setTx(tx);
		return responseObtenerListaCourier;
		
	}
	
	public Usuario addUsuario(Usuario usuario) throws Exception{
		
		usuario.setContrasena(this.encriptar(KEY, IV, usuario.getContrasena()));
		usuario.setIdpestado(1);
		usuario.setUsuario("DELIVERY_BBVA");
		
		return deliveryDao.obtUsuario(deliveryDao.addUsuario(usuario).getIdeusuario());
		//return deliveryDao.addUsuario(usuario);
	}
	
	
}