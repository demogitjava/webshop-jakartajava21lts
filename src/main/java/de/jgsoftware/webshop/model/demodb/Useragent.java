package de.jgsoftware.webshop.model.demodb;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.security.Timestamp;
import java.util.Date;
import de.jgsoftware.webshop.model.demodb.interfaces.i_model_useragent;

/**
 *
 * @author hoscho 
 */

@Entity
public class Useragent implements i_model_useragent
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String ipAddress;
    
    private String stbrowser;
    private String stbrowserversion;
    private String stsystem;
    
    private Date datum;
    
    private Timestamp timestamp;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getIpAddress() {
        return ipAddress;
    }

    @Override
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @Override
    public String getStbrowser() {
        return stbrowser;
    }

    @Override
    public void setStbrowser(String stbrowser) {
        this.stbrowser = stbrowser;
    }

    @Override
    public String getStbrowserversion() {
        return stbrowserversion;
    }

    @Override
    public void setStbrowserversion(String stbrowserversion) {
        this.stbrowserversion = stbrowserversion;
    }

    @Override
    public String getStsystem() {
        return stsystem;
    }

    @Override
    public void setStsystem(String stsystem) {
        this.stsystem = stsystem;
    }

    @Override
    public Date getDatum() {
        return datum;
    }

    @Override
    public void setDatum(Date datum) {
        this.datum = datum;
    }

    @Override
    public Timestamp getTimestamp() {
        return timestamp;
    }

    @Override
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    
    
    
}
