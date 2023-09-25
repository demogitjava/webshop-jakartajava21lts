package de.jgsoftware.webshop.controller;

import de.jgsoftware.webshop.controller.interfaces.i_indexcontroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author hoscho
 */

@Controller
public class IndexController implements i_indexcontroller {
    
    
    ModelAndView mv;
    
    @Override
    public ModelAndView index() 
    {
        mv = new ModelAndView("index");
        return null;
    }
    
}
