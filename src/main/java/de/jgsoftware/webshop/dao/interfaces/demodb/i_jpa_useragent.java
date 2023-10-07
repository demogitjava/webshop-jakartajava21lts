package de.jgsoftware.webshop.dao.interfaces.demodb;

import de.jgsoftware.webshop.model.demodb.Useragent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hoscho
 */
@Repository
public interface i_jpa_useragent extends JpaRepository<Useragent, Integer>
{
    
}
