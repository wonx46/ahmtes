package id.co.ahm.sd.nis.app000.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import id.co.ahm.sd.nis.app000.model.AhmsdnisMstbrnd;
import id.co.ahm.sd.nis.app000.service.Com001Service;

@RestController
@RequestMapping("jx/com001")
public class Com001Controller {

	private static final Logger logger = Logger
			.getLogger(Com001Controller.class);

	public Com001Controller() {
		System.out.println("Com001Controller()");
	}

	@Autowired
	@Qualifier(value = "com001Service")
	private Com001Service com001Service;
	
	 @RequestMapping(value = "/getSampleData", method = RequestMethod.GET)
		public List<AhmsdnisMstbrnd> getSampleData(){
				List<AhmsdnisMstbrnd> list = new ArrayList<>();
				AhmsdnisMstbrnd o = new AhmsdnisMstbrnd();
				o.setVbrndcd("A9373");
				o.setVbrndnm("Joini");
				o.setVbrndtyp("Playbos");
				list.add(o);
				return list;
		}

	 
	 
	
	 @RequestMapping(value = "/getAllBrands", method = RequestMethod.GET)
	public List<AhmsdnisMstbrnd> getAllBrandnya(){
			List<AhmsdnisMstbrnd> list = com001Service.getAllBrand();
			return list;
	}
	 
	
	@RequestMapping(value = "/")
	public ModelAndView list(ModelAndView model) throws IOException {
		List<AhmsdnisMstbrnd> list = com001Service.getAllBrand();
		model.addObject("list", list);
		model.setViewName("home");
		return model;
	}

	@RequestMapping(value = "/newBrand", method = RequestMethod.GET)
	public ModelAndView createNew(ModelAndView model) {
		AhmsdnisMstbrnd o = new AhmsdnisMstbrnd();
		model.addObject("obj", o);
		model.setViewName("idcoahmsdnismstbrand/IdcoahmsdnismstbrandFormNew");
		return model;
	}

	@RequestMapping(value = "/updateBrand", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute AhmsdnisMstbrnd obj) {
		
			obj.setVmodi("adminupdate");
			obj.setDmodi(getNow());
			com001Service.updateBrand(obj);
		
		return new ModelAndView("redirect:/");
	}

	
	
	@RequestMapping(value = "/saveBrand", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute AhmsdnisMstbrnd obj) {
			obj.setVcrea("admincreate");
			obj.setDcrea(getNow());
			com001Service.addMstBrand(obj);
		return new ModelAndView("redirect:/");
	}

	private Date getNow() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	@RequestMapping(value = "/deleteBrand", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request) {
		String id = request.getParameter("id");
		com001Service.deleteBrand(id);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/editBrand", method = RequestMethod.GET)
	public ModelAndView edit(HttpServletRequest request) {
		String id = request.getParameter("id");
		AhmsdnisMstbrnd obj = com001Service.getBrand(id);
		ModelAndView model = new ModelAndView("idcoahmsdnismstbrand/IdcoahmsdnismstbrandFormUpdate");
		model.addObject("obj", obj);

		return model;
	}

}