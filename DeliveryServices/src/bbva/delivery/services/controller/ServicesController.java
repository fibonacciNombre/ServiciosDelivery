package bbva.delivery.services.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.resteasy.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bbva.delivery.services.bean.Observer;
import bbva.delivery.services.bean.Person;
import bbva.delivery.services.bean.RequestChangeEstadoRegistro;
import bbva.delivery.services.bean.RequestGetVisitasUsuario;
import bbva.delivery.services.bean.RequestValidarCourier;
import bbva.delivery.services.bean.ResponseChangeEstadoRegistro;
import bbva.delivery.services.bean.ResponseGetVisitasUsuario;
import bbva.delivery.services.bean.ResponseObtenerListaCourier;
import bbva.delivery.services.bean.ResponseValidarCourier;
import bbva.delivery.services.bean.Tx;
import bbva.delivery.services.bean.Usuario;
import bbva.delivery.services.service.DeliveryService;

@Controller
@RequestMapping(value = "/services")
public class ServicesController {
	
	private static final String AUTHORIZATION_PROPERTY = "Authorization";
	private static final String AUTHENTICATION_SCHEME  = "Basic";
	
	@Autowired
	DeliveryService deliveryService;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Observer> getObservers() {

        System.out.println("list observers");
        
        List<Observer> list = new ArrayList<Observer>();
        
        Observer ob = new Observer();
        Person  per = new Person();
        
        per.setName("David Prueba");
        
        ob.setPerson(per);
        ob.setApprovedUser("PRUEBA");
        
        list.add(ob);
        
        return list;

    }
	

	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Usuario addUsuario(@RequestBody Usuario usuario,HttpServletResponse k) throws Exception {

			 
			 deliveryService.addUsuario(usuario);
			 return usuario;

	 }
	
	//INICIO DE LOS SERVICIOS
	
	//RF - 04
	@RequestMapping(value = "/validarDNICourier", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseValidarCourier validarDNICourier(@RequestBody RequestValidarCourier requestValidarCourier, HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		if(this.customAutorization(request)){
			System.out.println("header aaaaaaaaaa   --> "+ request.getHeader("Authorization"));
			return deliveryService.validarDNICourier(requestValidarCourier);
        }else{
            System.out.println("invalid authorization");
            ResponseValidarCourier responseValidarCourier = new ResponseValidarCourier();
            Tx tx = new Tx();
            tx.setCodigo("1");
            tx.setMensaje("Unauthorized: Authentication authorization was invalid.");
            responseValidarCourier.setTx(tx);
            return responseValidarCourier;
        }
		
		
	 }
	//RF - 05
	@RequestMapping(value = "/getVisitasUsuario", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseGetVisitasUsuario getVisitasUsuario(@RequestBody RequestGetVisitasUsuario requestGetVisitasUsuario ,HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		if(this.customAutorization(request)){
			System.out.println("header aaaaaaaaaa   --> "+ request.getHeader("Authorization"));
			return deliveryService.getVisitasUsuario(requestGetVisitasUsuario);
        }else{
            System.out.println("invalid authorization");
            ResponseGetVisitasUsuario responseGetVisitasUsuario = new ResponseGetVisitasUsuario();
            Tx tx = new Tx();
            tx.setCodigo("1");
            tx.setMensaje("Unauthorized: Authentication authorization was invalid.");
            responseGetVisitasUsuario.setTx(tx);
            return responseGetVisitasUsuario;
        }

	}
	//RF - 20
	@RequestMapping(value = "/changeEstadoRegistro", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseChangeEstadoRegistro changeEstadoRegistro(@RequestBody RequestChangeEstadoRegistro requestChangeEstadoRegistro, HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		if(this.customAutorization(request)){
			System.out.println("header aaaaaaaaaa   --> "+ request.getHeader("Authorization"));
			return deliveryService.changeEstadoRegistro(requestChangeEstadoRegistro);
        }else{
            System.out.println("invalid authorization");
            ResponseChangeEstadoRegistro responseChangeEstadoRegistro = new ResponseChangeEstadoRegistro();
            Tx tx = new Tx();
            tx.setCodigo("1");
            tx.setMensaje("Unauthorized: Authentication authorization was invalid.");
            responseChangeEstadoRegistro.setTx(tx);
            return responseChangeEstadoRegistro;
        }
	}
	
	//RF - 03
	@RequestMapping(value = "/obtenerListaCourier", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseObtenerListaCourier obtenerListaCourier(HttpServletResponse response, HttpServletRequest request) throws Exception {

		if(this.customAutorization(request)){
			System.out.println("header aaaaaaaaaa   --> "+ request.getHeader("Authorization"));
			return deliveryService.obtenerListaCourier();
        }else{
            System.out.println("invalid authorization");
            ResponseObtenerListaCourier responseObtenerListaCourier = new ResponseObtenerListaCourier();
            Tx tx = new Tx();
            tx.setCodigo("1");
            tx.setMensaje("Unauthorized: Authentication authorization was invalid.");
            responseObtenerListaCourier.setTx(tx);
            return responseObtenerListaCourier;
        }

    }
	
	public boolean customAutorization( HttpServletRequest h) throws IOException{
		
		String authorization = null;
		String encodedUserPassword = null;
		String usernameAndPassword = null;
		try {
			authorization = h.getHeader(AUTHORIZATION_PROPERTY);
			System.out.println("AUTHORIZATION_PROPERTY: " + authorization);
	   	 
	    	encodedUserPassword = authorization.replaceFirst(AUTHENTICATION_SCHEME + " ", ""); 
	    	 
	 		usernameAndPassword = new String(Base64.decode(encodedUserPassword));
	 		System.out.println("usernameAndPassword --> "+usernameAndPassword);
	 		
	 		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		    final String username = tokenizer.nextToken();
		    final String password = tokenizer.nextToken();
		    
		    System.out.println("username --> "+username);
		    System.out.println("password --> "+password);
		    
		    Usuario u = new Usuario();
		    u.setCodusuario(username);
		    u.setContrasena(password);
		    
		    return deliveryService.validarUsuario(u);
		} catch (Exception e) {
			return false;
		}
	}
	
	@RequestMapping(value = "/addUsuario", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Usuario addUsuario(@RequestBody Usuario usuario, HttpServletResponse response, HttpServletRequest request) throws Exception {
		
		
			System.out.println("header aaaaaaaaaa   --> "+ request.getHeader("Authorization"));
			return deliveryService.addUsuario(usuario);

	}
	
}
