package bbva.delivery.services.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bbva.delivery.services.bean.Observer;
import bbva.delivery.services.bean.Person;
import bbva.delivery.services.bean.RequestValidarCourier;
import bbva.delivery.services.bean.ResponseValidarCourier;
import bbva.delivery.services.bean.Tx;
import bbva.delivery.services.bean.Usuario;
import bbva.delivery.services.bean.ValidarCourier;
import bbva.delivery.services.service.DeliveryService;

@Controller
@RequestMapping(value = "/services")
public class ServicesController {
	
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
	 public Observer addObserver(@RequestBody Observer o,HttpServletResponse k) throws Exception {

			 Usuario usuario = new Usuario();
			 o.getApprovedUser().equals("hshshs");
		       deliveryService.validarUsuarioToken(usuario);
		       System.out.println(ToStringBuilder.reflectionToString(usuario,ToStringStyle.MULTI_LINE_STYLE));
			       o.setId(34L);
			       o.setAccreditation("Lobaton");
			       System.out.println("create observer" + "-->" + o.getId());
			       return o;

	 }
	
	//INICIO DE LOS SERVICIOS
	
	//RF - 04
	@RequestMapping(value = "/validarDNICourier", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseValidarCourier validarDNICourier(@RequestBody RequestValidarCourier o) throws Exception {
		
		ResponseValidarCourier responseValidarCourier = new ResponseValidarCourier();
		if(o == null || o.getCourier() == null || o.getDni() == null){
			Tx tx = new Tx();
			tx.setCodigo("1");
			tx.setMensaje("error");
			responseValidarCourier.setTx(tx);
			return responseValidarCourier;
		}else{
			
			if("Elvis".equals(o.getCourier()) && "12345678".equals(o.getDni())){
				
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
	//RF - 05
	@RequestMapping(value = "/getVisitasUsuario", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Observer getVisitasUsuario(@RequestBody Observer o) throws Exception {

		Usuario usuario = new Usuario();
		o.getApprovedUser().equals("hshshs");
		deliveryService.validarUsuarioToken(usuario);
		System.out.println(ToStringBuilder.reflectionToString(usuario,ToStringStyle.MULTI_LINE_STYLE));
		o.setId(36L);
		o.setAccreditation("getVisitasUsuario");
		System.out.println("create observer" + "-->" + o.getId());
		return o;

	 }
	//RF - 20
	@RequestMapping(value = "/changeEstadoRegistro", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Observer changeEstadoRegistro(@RequestBody Observer o) throws Exception {

		Usuario usuario = new Usuario();
		o.getApprovedUser().equals("hshshs");
		deliveryService.validarUsuarioToken(usuario);
		System.out.println(ToStringBuilder.reflectionToString(usuario,ToStringStyle.MULTI_LINE_STYLE));
		o.setId(37L);
		o.setAccreditation("changeEstadoRegistro");
		System.out.println("create observer" + "-->" + o.getId());
		return o;

	 }
}
