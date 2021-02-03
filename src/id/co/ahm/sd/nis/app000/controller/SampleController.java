package id.co.ahm.sd.nis.app000.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import id.co.ahm.sd.nis.app000.service.Com001Service;

@Controller
@RequestMapping("/sample-module")
public class SampleController {

	@Autowired
	Com001Service com001Service;

	@RequestMapping(value = "/getDataSample", method = RequestMethod.GET)
	public String getAllEmployees(Model model) {
		model.addAttribute("data", com001Service.getSampleData());
		return "dataSampleDisplay";
	}
}
