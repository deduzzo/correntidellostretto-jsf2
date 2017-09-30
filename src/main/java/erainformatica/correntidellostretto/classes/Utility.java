/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erainformatica.correntidellostretto.classes;

import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 *
 * @author deduzzo
 */
public class Utility
{
    public static final String CONST_UTIL_FORMAT_DATA = "dd/MM/yyyy";
    public static final String CONST_UTIL_FORMAT_ORA = "HH:mm";
    public static final String CONST_UTIL_FORMAT_DATA_ORA = "dd/MM/yyyy HH:mm";
    public static final String CONST_UTIL_FORMAT_DATA_ORA_PARLANTE = "dd/MM/yyyy HH:mm";
    
    public static void mostraGrowl(String titolo, String testo, Severity severity)
    {
        FacesMessage message = new FacesMessage(severity, titolo,testo);  
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public static String dateToString(String format, Date date) {
        if (date != null)
            return new SimpleDateFormat(format).format(date);
        else
            return "";
    }
    
    public static double round(double value, int places) 
    {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    public static String getAppPath() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }
    
    

}
