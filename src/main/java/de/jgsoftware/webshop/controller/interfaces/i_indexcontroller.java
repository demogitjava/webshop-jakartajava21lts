package de.jgsoftware.webshop.controller.interfaces;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author hoscho
 */

@RequestMapping("/")
public interface i_indexcontroller 
{
    @GetMapping({"index", "/"})
    ModelAndView index();
}
