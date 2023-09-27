package de.jgsoftware.webshop.service.interfaces;

import de.jgsoftware.webshop.dao.interfaces.i_daoindex;

/**
 *
 * @author hoscho
 */

public interface i_indexservice 
{
	i_daoindex getIdaoindex();
	void setIdaoindex(i_daoindex idaoindex);
	
	
	// demodb
	/*
	 *  get count of table useragent 
	 *  demodb
	 */
	Long getuseragentcount();
}
