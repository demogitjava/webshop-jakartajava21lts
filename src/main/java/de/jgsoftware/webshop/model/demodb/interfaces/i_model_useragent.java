package de.jgsoftware.webshop.model.demodb.interfaces;

import java.security.Timestamp;
import java.util.Date;

/**
 *
 * @author hoscho
 */
public interface i_model_useragent 
{
   
   Integer getId();
   void setId(Integer id);
   
   String getIpAddress();
   void setIpAddress(String ipAddress);
   String getStbrowser();
   void setStbrowser(String stbrowser);
   String getStbrowserversion();
   void setStbrowserversion(String stbrowserversion);
   String getStsystem();
   void setStsystem(String stsystem);
   Date getDatum();
   void setDatum(Date datum);
   Timestamp getTimestamp();
   void setTimestamp(Timestamp timestamp);
}
