/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package erainformatica.correntidellostretto.classes;

import erainformatica.correntidellostretto.beans.ApplicationManagedBean;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author robertodedomenico
 */
public class SessionManage implements HttpSessionListener  {

    @Inject
    ApplicationManagedBean application;
    
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        
        try {
            if (!application.getUltimaDataAggiornamentoDati().equals(Utility.dateToString("MM/yyyy", new Date())))
            {
                application.creaSiteMap("http://www.correntidellostretto.it");
                application.setUltimaDataAggiornamentoDati(Utility.dateToString(Utility.CONST_UTIL_FORMAT_DATA, new Date()));
            }
        } catch (Exception e) 
        {
            Logger LOG = Logger.getLogger(SessionManage.class.getName());
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    }
    
}
