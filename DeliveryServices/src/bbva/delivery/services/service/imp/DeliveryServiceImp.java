package bbva.delivery.services.service.imp;

import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bbva.delivery.services.bean.Courier;
import bbva.delivery.services.bean.Delivery;
import bbva.delivery.services.bean.EmbeddedImages;
import bbva.delivery.services.bean.EstadoRegistro;
import bbva.delivery.services.bean.InformarEntregaCourier;
import bbva.delivery.services.bean.RequestChangeEstadoRegistro;
import bbva.delivery.services.bean.RequestGetVisitasUsuario;
import bbva.delivery.services.bean.RequestInformarActivacionBBVA;
import bbva.delivery.services.bean.RequestInformarEntregaCourier;
import bbva.delivery.services.bean.RequestTransferirArchivo;
import bbva.delivery.services.bean.RequestValidarCourier;
import bbva.delivery.services.bean.ResponseChangeEstadoRegistro;
import bbva.delivery.services.bean.ResponseGetVisitasUsuario;
import bbva.delivery.services.bean.ResponseInformarActivacionBBVA;
import bbva.delivery.services.bean.ResponseInformarEntregaCourier;
import bbva.delivery.services.bean.ResponseObtenerListaCourier;
import bbva.delivery.services.bean.ResponseTransferirArchivo;
import bbva.delivery.services.bean.ResponseValidarCourier;
import bbva.delivery.services.bean.Tx;
import bbva.delivery.services.bean.Usuario;
import bbva.delivery.services.bean.ValidarCourier;
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
		
		ResponseValidarCourier responseValidarCourier = new ResponseValidarCourier();
//		if(requestValidarCourier == null || requestValidarCourier.getCourier() == null || requestValidarCourier.getDni() == null){
//			Tx tx = new Tx();
//			tx.setCodigo("1");
//			tx.setMensaje("error");
//			responseValidarCourier.setTx(tx);
//			return responseValidarCourier;
//		}else{
//			
//			if("Elvis".equals(requestValidarCourier.getCourier()) && "12345678".equals(requestValidarCourier.getDni())){
//				
//				Tx tx = new Tx();
//				tx.setCodigo("0");
//				tx.setMensaje("correcto");
//				ValidarCourier validarCourier = new ValidarCourier();
//				validarCourier.setCodigo("000");
//				validarCourier.setMensaje("Usuario Activo");
//				responseValidarCourier.setTx(tx);
//				responseValidarCourier.setValidarCourier(validarCourier);
//			
//				return responseValidarCourier;
//			}else{
//				Tx tx = new Tx();
//				tx.setCodigo("0");
//				tx.setMensaje("correcto");
//				ValidarCourier validarCourier = new ValidarCourier();
//				validarCourier.setCodigo("001");
//				validarCourier.setMensaje("Usuario no existe");
//				responseValidarCourier.setTx(tx);
//				responseValidarCourier.setValidarCourier(validarCourier);
//				return responseValidarCourier;
//			}
//		}
		
		List<ValidarCourier> validarCouriers = deliveryDao.validarDNICourier(requestValidarCourier);
		if(validarCouriers.isEmpty()){
			Tx tx = new Tx();
			tx.setCodigo("0");
			tx.setMensaje("correcto");
			responseValidarCourier.setTx(tx);
			ValidarCourier courier = new ValidarCourier();
			courier.setCodigo("002");
			courier.setMensaje("Usuario no existe");
			responseValidarCourier.setValidarCourier(courier);
		}else{
			Tx tx = new Tx();
			tx.setCodigo("0");
			tx.setMensaje("correcto");
			responseValidarCourier.setTx(tx);
			ValidarCourier courier = validarCouriers.get(0);
			courier.setCodigo("000");
			courier.setMensaje("Usuario activo");
			responseValidarCourier.setValidarCourier(validarCouriers.get(0));
		}

		return responseValidarCourier;
	}
	
	public ResponseGetVisitasUsuario getVisitasUsuario(RequestGetVisitasUsuario requestGetVisitasUsuario){
		
		ResponseGetVisitasUsuario responseGetVisitasUsuario =  new ResponseGetVisitasUsuario();
		String fecha = requestGetVisitasUsuario.getDia()+"/"+requestGetVisitasUsuario.getMes()+"/"+requestGetVisitasUsuario.getAnio();
		
		//deliveryDao.getVisitasUsuario(requestGetVisitasUsuario, fecha);
		Tx tx = new Tx();
		tx.setCodigo("0");
		tx.setMensaje("correcto");
//		VisitasUsuario visitasUsuario = new VisitasUsuario();
//		visitasUsuario.setDni(requestGetVisitasUsuario.getDni());
//		visitasUsuario.setCoordenadas("1234");
//		visitasUsuario.setCoordenadas("7845");
//		visitasUsuario.setDireccion("Av. Dean Valdivia");
//		visitasUsuario.setDistrito("San Isiadro");
//		visitasUsuario.setFechaEntrega("28/06/2015");
//		visitasUsuario.setHorarioEntrega("15-16 horas");
//		visitasUsuario.setLineaCredito("5000");
//		visitasUsuario.setNombres(requestGetVisitasUsuario.getCourier());
//		visitasUsuario.setPrimDigitosTar("4250");
//		visitasUsuario.setTipoTarjeta("Signature");
//		visitasUsuario.setUltiDigitosTar("1420");
//		visitasUsuario.setCodigoEntrega("666");
		
		responseGetVisitasUsuario.setVisitasUsuarios(deliveryDao.getVisitasUsuario(requestGetVisitasUsuario, fecha));
		responseGetVisitasUsuario.setTx(tx);
		return responseGetVisitasUsuario;
	}
	
	public ResponseChangeEstadoRegistro changeEstadoRegistro(RequestChangeEstadoRegistro requestChangeEstadoRegistro){
		
		ResponseChangeEstadoRegistro responseChangeEstadoRegistro =  new ResponseChangeEstadoRegistro();
		Tx tx = new Tx();
		tx.setCodigo("0");
		tx.setMensaje("correcto");
		RequestChangeEstadoRegistro rcr = deliveryDao.changeEstadoRegistro(requestChangeEstadoRegistro);
		
		EstadoRegistro estadoRegistro = new EstadoRegistro();
		estadoRegistro.setEstado(rcr.getEstado());
		estadoRegistro.setMotivo(rcr.getMotivo());
		estadoRegistro.setCodigoEntrega(rcr.getCodigoEntrega().toString());
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
	
	public ResponseInformarEntregaCourier informarEntregaCourier(RequestInformarEntregaCourier requestInformarEntregaCourier){
		ResponseInformarEntregaCourier responseInformarEntregaCourier = new ResponseInformarEntregaCourier();
		List<Delivery> deliveries = deliveryDao.informarEntregaCourier(requestInformarEntregaCourier);
		Delivery delivery = null;
		InformarEntregaCourier  j = null;
		String body = "";
		if(!deliveries.isEmpty()){
			delivery = deliveries.get(0);
			j = new InformarEntregaCourier();
			
			body = "Se envía el informe de Entrega Courier, lo envia: "+delivery.getNombretercero()+"<br>"
					+ "  Codigo de Entrega: "+delivery.getIddelivery().toString()+"<br>"
					+ "  Estado: "+ requestInformarEntregaCourier.getEstado()+"<br>"
					+ "  Observaciones : "+ requestInformarEntregaCourier.getObservaciones()+"<br>"
					+ "  Fecha y Hora : "+ requestInformarEntregaCourier.getFechaHora();			
			this.envioCorreo("InformarEntregaCourier", body, null, delivery.getCorreocourier(), "david.inga.81@gmail.com", "david.inga.81@gmail.com");
			j.setCodigo("0");
			j.setMensaje("Correo Enviado con Exito");
			responseInformarEntregaCourier.setInformarEntregaCourier(j);
		}
		Tx tx = new Tx();
		tx.setCodigo("0");
		tx.setMensaje("correcto");
		responseInformarEntregaCourier.setTx(tx);
		return responseInformarEntregaCourier;
	}
	
	public ResponseInformarActivacionBBVA informarActivacionBBVA(RequestInformarActivacionBBVA requestInformarActivacionBBVA){
		ResponseInformarActivacionBBVA responseInformarActivacionBBVA = new ResponseInformarActivacionBBVA();
		Tx tx = new Tx();
		tx.setCodigo("0");
		tx.setMensaje("correcto");
		responseInformarActivacionBBVA.setTx(tx);
		return responseInformarActivacionBBVA;
	}
	
	public ResponseTransferirArchivo transferirArchivo(RequestTransferirArchivo requestTransferirArchivo){
		ResponseTransferirArchivo responseTransferirArchivo = new ResponseTransferirArchivo();
		Tx tx = new Tx();
		tx.setCodigo("0");
		tx.setMensaje("correcto");
		responseTransferirArchivo.setTx(tx);
		return responseTransferirArchivo;
	}
	
	public Usuario addUsuario(Usuario usuario) throws Exception{
		
		usuario.setContrasena(this.encriptar(KEY, IV, usuario.getContrasena()));
		usuario.setIdpestado(1);
		usuario.setUsuario("DELIVERY_BBVA");
		
		return deliveryDao.obtUsuario(deliveryDao.addUsuario(usuario).getIdeusuario());
		//return deliveryDao.addUsuario(usuario);
	}
	
	public void envioCorreo(String subject, String body, String adjunto, String para, String copia, String copiaoculta){

			String userAuth = null;
			String passAuth = null;
			String host = null;
			List<String> to = new ArrayList<String>();
			List<String> cc = new ArrayList<String>();
			List<String> cco = new ArrayList<String>();
			List<String> listAdjunto =  null;
			String subjectMail = null;
			String bodyMail = null;
			String from = null;

			from = "rimacsoporte@gmail.com";

			to.add(para);
			cc.add(copia);
			cco.add(copiaoculta);
			
			subjectMail = subject;
			bodyMail = body;
			if(!(adjunto == null)){
				listAdjunto = new ArrayList<String>();
				listAdjunto.add(adjunto);
			}
			
			userAuth = "rimacsoporte@gmail.com";
			passAuth = "Rimac0003";
			host = "smtp.gmail.com";

			this.sendGeneral(from, to, cc, cco, subjectMail,
					bodyMail, listAdjunto, null, null, null, userAuth,
					passAuth, host);	
		}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void sendGeneral(String from,List<String> to,List<String> cc,List<String> bcc,String subjectMail,String bodyMail,//elementos principales
								   List<String> attachment,List<EmbeddedImages> embeddedImages ,//elementos adicionales sino mandar null
								   String acuserecibo,String urlAcuse,//Acuse de recibo 1=SI,0 ó null=NO
								   String userAuth,String passAuth,String host) //parametros de autentificacion
	{
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.user", userAuth);
		properties.setProperty("mail.smtp.auth", "true");
		
		//si el host es gmail
		if("smtp.gmail.com".equals(host))
		{
			properties.setProperty("mail.smtp.starttls.enable", "true");
			properties.setProperty("mail.smtp.port", "587");
		}
        
        
		try{
			//JCASIANOC-creamos sesion
			Session session = Session.getDefaultInstance(properties);
			MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setSubject(subjectMail);
            
            //JCASIANOC-convertir List a InternetAddress (TO)
            ArrayList addresses=new ArrayList();
            Iterator iter = to.iterator();
            while (iter.hasNext()){
            	addresses.add(new InternetAddress(iter.next().toString()));
            }
            InternetAddress[] adreessesArray=(InternetAddress[])addresses.toArray(new InternetAddress[addresses.size()]);
            message.addRecipients(Message.RecipientType.TO,adreessesArray);

            //JCASIANOC-convertir List a InternetAddress (CC)
            if(cc!=null && cc.size()>0){
                addresses=new ArrayList();
                iter = cc.iterator();
                while (iter.hasNext()){
                	addresses.add(new InternetAddress(iter.next().toString()));
                }
                adreessesArray=(InternetAddress[])addresses.toArray(new InternetAddress[addresses.size()]);
                message.addRecipients(Message.RecipientType.CC,adreessesArray);
            }
            //JCASIANOC-convertir List a InternetAddress (BCC)
            if(bcc!=null && bcc.size()>0){
                addresses=new ArrayList();
                iter = bcc.iterator();
                while (iter.hasNext()){
                	addresses.add(new InternetAddress(iter.next().toString()));
                }
                adreessesArray=(InternetAddress[])addresses.toArray(new InternetAddress[addresses.size()]);
                message.addRecipients(Message.RecipientType.BCC,adreessesArray);
            	
            }
            //JCASIANOC - Opciones con body y adjuntos
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            
            //Editando para acuse de recibe 
            if(acuserecibo!=null)
            {
            	if("1".equals(acuserecibo)){
            		if(bodyMail.indexOf("</body>")>0)
            			bodyMail=bodyMail.replace("</body>", "<img src=\""+urlAcuse+"\" width=\"1px\" HEIGHT=\"1px\"></body>");
            		else
            			bodyMail=bodyMail+"<img src=\""+urlAcuse+"\" width=\"1px\" HEIGHT=\"1px\">";
            	}
            		
            }
            
            
            messageBodyPart.setContent(bodyMail,"text/html");
            System.out.println("Enviando...=====>"+bodyMail);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            
            //JCASIANOC - Se adjuntan los archivos al correo
            if( attachment!=null && attachment.size()>0 ){
               for( String rutaAdjunto : attachment ){
                   messageBodyPart = new MimeBodyPart();
                   File f = new File(rutaAdjunto);
                   if( f.exists() ){
                      DataSource source = new FileDataSource( rutaAdjunto );
                      messageBodyPart.setDataHandler( new DataHandler(source) );
                      messageBodyPart.setFileName( f.getName() );
                      multipart.addBodyPart(messageBodyPart);
                   }
               }
            }
            message.setContent(multipart);
            //JCASIANOC - Se adjuntan imagenes embebidas en el bodyMail
            if( embeddedImages!=null && embeddedImages.size()>0 ){
            	for( EmbeddedImages datos : embeddedImages ){
            		messageBodyPart = new MimeBodyPart();
            		File f = new File(datos.getPath());
            		if( f.exists() ){
            			messageBodyPart.attachFile(datos.getPath());
            			messageBodyPart.setHeader("Content-ID", datos.getId());
            			multipart.addBodyPart(messageBodyPart);
            			
            		}
            	}
            }
            message.setContent(multipart);
            
            
            Transport t = session.getTransport("smtp");
            t.connect(userAuth, passAuth);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
			
		}
		catch (AddressException e) {
			e.printStackTrace();
	    	throw new RuntimeException("" + e, e);
		}
		catch (IOException e) {
			e.printStackTrace();
	    	throw new RuntimeException("" + e, e);
		}
		catch (MessagingException mex) {
		      	mex.printStackTrace();
		    	throw new RuntimeException("" + mex, mex);
	      }
	}
	
	
}