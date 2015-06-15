package bbva.delivery.services.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bbva.delivery.services.bean.Observer;
import bbva.delivery.services.bean.Person;

@Controller
@RequestMapping(value = "/observers")
public class ServicesController {
	
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
	 public Observer addObserver(@RequestBody Observer o) {

	       o.setId(34L);
	       o.setAccreditation("Lobaton");
	       System.out.println("create observer" + "-->" + o.getId());
	       return o;
	 }

}
