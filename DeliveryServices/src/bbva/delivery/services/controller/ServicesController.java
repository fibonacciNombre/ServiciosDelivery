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
import bbva.delivery.services.bean.RequestChangeEstadoRegistro;
import bbva.delivery.services.bean.RequestGetVisitasUsuario;
import bbva.delivery.services.bean.RequestValidarCourier;
import bbva.delivery.services.bean.ResponseChangeEstadoRegistro;
import bbva.delivery.services.bean.ResponseGetVisitasUsuario;
import bbva.delivery.services.bean.ResponseValidarCourier;
import bbva.delivery.services.bean.Usuario;
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
	public ResponseValidarCourier validarDNICourier(@RequestBody RequestValidarCourier requestValidarCourier) throws Exception {
		
		return deliveryService.validarDNICourier(requestValidarCourier);
	 }
	//RF - 05
	@RequestMapping(value = "/getVisitasUsuario", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseGetVisitasUsuario getVisitasUsuario(@RequestBody RequestGetVisitasUsuario requestGetVisitasUsuario) throws Exception {

		return deliveryService.getVisitasUsuario(requestGetVisitasUsuario);

	}
	//RF - 20
	@RequestMapping(value = "/changeEstadoRegistro", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseChangeEstadoRegistro changeEstadoRegistro(@RequestBody RequestChangeEstadoRegistro requestChangeEstadoRegistro) throws Exception {

		return deliveryService.changeEstadoRegistro(requestChangeEstadoRegistro);

	}
}
