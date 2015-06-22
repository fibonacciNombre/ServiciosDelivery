package bbva.delivery.services.service.imp;

import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64;

import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bbva.delivery.services.bean.Usuario;
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
		
		usuarioPassword = usuario.getUsuario()+":"+usuario.getPassword();
		
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
	    
		DeliveryDaoImp daoImp = new DeliveryDaoImp();
		
		return daoImp.validarUsuario(usuario);
			
	}

}