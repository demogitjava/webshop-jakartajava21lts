package de.jgsoftware.webshop.dao.interfaces;

import de.jgsoftware.webshop.model.demodb.Useragent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author hoscho
 */

public interface i_jpa_useragent extends JpaRepository<Useragent, Integer>{
    
}
