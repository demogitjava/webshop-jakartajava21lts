package de.jgsoftware.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.jgsoftware.webshop.service.interfaces.i_indexservice;
import de.jgsoftware.webshop.dao.interfaces.i_daoindex;
/**
 *
 * @author hoscho
 */

@Service
public class indexservice implements i_indexservice
{
    @Autowired
    i_daoindex idaoindex;

	public i_daoindex getIdaoindex() {
		return idaoindex;
	}

	public void setIdaoindex(i_daoindex idaoindex) {
		this.idaoindex = idaoindex;
	}
    
    
    
}
