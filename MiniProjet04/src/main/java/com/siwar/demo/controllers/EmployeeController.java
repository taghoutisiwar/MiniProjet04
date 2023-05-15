package com.siwar.demo.controllers;

import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.siwar.demo.entities.Employee;
import com.siwar.demo.entities.Team;
import com.siwar.demo.service.EmployeeService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	@RequestMapping("/showCreate")
	public String showCreate(ModelMap modelMap)
	{
		List<Team> teams = employeeService.getAllTeam();
		Employee e = new Employee();
		Team t = new Team ();

		if (!teams.isEmpty()) {
		    t = teams.get(0);
		}

		e.setTeam(t);
		modelMap.addAttribute("employee", new Employee());
		modelMap.addAttribute("mode", "new");
		modelMap.addAttribute("teams", teams);
		
		for (Team t1 : teams) {
			System.out.println(t1);
		}
		
		modelMap.addAttribute("page",0);
		return "formEmployee";
	}
	
	@RequestMapping("/saveEmployee")
	public String saveEmployee(@Valid Employee employee ,  BindingResult bindingResult, @ModelAttribute("page") int pageFromPrevious,
		    @RequestParam (name="size", defaultValue = "3") int size,
		    RedirectAttributes redirectAttributes,ModelMap modelMap)
	{ int page;
		 if (bindingResult.hasErrors()) {
			 List<Team> teams = employeeService.getAllTeam();
			 modelMap.addAttribute("team", teams);
			 modelMap.addAttribute("mode", "edit");
		        return "formEmployee";
		    }if (employee.getId()==null) //adding
		        page = employeeService.getAllEmployees().size()/size; // calculer le nbr de pages
		    else //updating
		        page = pageFromPrevious;
		    employeeService.saveEmployee(employee);
		    redirectAttributes.addAttribute("page", page);
		    return "redirect:/listeEmployees";
	}
	@RequestMapping("/listeEmployees")
	public String listeEmployees(ModelMap modelMap ,@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "3") int size)

	{
		Page<Employee> employee = employeeService.getAllEmployeeParPage(page, size);
		modelMap.addAttribute("employees", employee);
		 modelMap.addAttribute("pages", new int[employee.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		return "listeEmployees";
	}
	@RequestMapping("/supprimerEmployee")
	public String supprimerEmployee(@RequestParam("id") Long id,
	 ModelMap modelMap ,@RequestParam (name="page",defaultValue = "0") int page,
	 @RequestParam (name="size", defaultValue = "3") int size)
	{ 
		employeeService.deleteEmployeeById(id);
		Page<Employee> employee = employeeService.getAllEmployeeParPage(page, 
				size);
				modelMap.addAttribute("employees", employee);
				modelMap.addAttribute("pages", new int[employee.getTotalPages()]);
				modelMap.addAttribute("currentPage", page);
				modelMap.addAttribute("size", size);

	return "listeEmployees";
	}
	@RequestMapping("/modifierEmployee")
	public String editerEmployee(@RequestParam("id") Long id, @RequestParam("page") int page,ModelMap modelMap)
	{
		Employee e = employeeService.getEmployee(id);
		List<Team> teams = employeeService.getAllTeam();
		modelMap.addAttribute("employee", e);
		modelMap.addAttribute("mode", "edit");
		modelMap.addAttribute("page",page);
		modelMap.addAttribute("team", teams);
		return "formEmployee";
	}
	@RequestMapping("/updateEmployee")
	public String updateEmployee(@ModelAttribute("chat") Employee employee,
	@RequestParam("date") String date,ModelMap modelMap) throws ParseException 
	{
		//conversion de la date 
		 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		 Date dateRec = dateformat.parse(String.valueOf(date));
		 employee.setDateRec(dateRec);
		 
		 employeeService.updateEmployee(employee);
		 List<Employee> employee1 = employeeService.getAllEmployees();
		 modelMap.addAttribute("employees", employee1);
		return "listeEmployees";
		}
}
