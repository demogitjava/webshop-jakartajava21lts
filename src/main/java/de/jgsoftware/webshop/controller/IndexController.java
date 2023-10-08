package de.jgsoftware.webshop.controller;

import de.jgsoftware.webshop.controller.interfaces.i_indexcontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import de.jgsoftware.webshop.service.interfaces.i_indexservice;


/**
 *
 * @author hoscho
 */

@Controller
public class IndexController implements i_indexcontroller {
    
    
	@Autowired
	i_indexservice interfaceindexservice;
	
	
	
	
    ModelAndView mv;
    
    
    
    @Override
    public ModelAndView index() 
    {
    	
    	
        mv = new ModelAndView("index");
       // Long usercountdemo = interfaceindexservice.getuseragentcount();
        //mv.addObject("Usercount", interfaceindexservice.getuseragentcount());
       
        
        return mv;
    }
    
}
