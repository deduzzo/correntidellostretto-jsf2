/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erainformatica.correntidellostretto.beans;

import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;
import erainformatica.correntidellostretto.classes.Utility;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author robertodedomenico
 */
@Named(value = "applicationManagedBean")
@ApplicationScoped
public class ApplicationManagedBean {
    
    String ultimaDataAggiornamentoDati;
    String credits = "          <br /><b>Correnti dello Stretto</b><br />\n" +
"                                Il calcolo perpetuo delle correnti utilizza le costanti ufficiali, tuttavia l'intensità e i risultati mostrati<br />\n" +
"                                potrebbero talvolta differire da quelli reali. La corrente nello stretto di Messina infatti è soggetta a variabili esterne<br />\n" +
"                                non facilmente calcolabili  come vento, condizioni meteo, corrente di ritorno (il cosiddetto \"refolo\").<br />\n" +
"                                <br /><b>CREDITS</b><br />\n" +
"                                Algoritmo e idea basata da una tesi<br /> di Amante Emanuele <br />\n" +
"                                Realizzazione, adattatamento mobile, creazione WebApp e MobileApp a cura di <br />\n" +
"                                <a href='http://www.erainformatica.it'>Era Informatica</a>\n<br />De Domenico Roberto<bt />\n" +
"                                <br /><br />\n" +
"                                <b>I creatori\n" +
"                                non si assumono alcuna responsabilità sull'utilizzo improprio dello stesso e dei valori ottenuti.</b><br />\n" +
"                                <br />\n" +
"                                \"Correnti dello Stretto\" è disponibile <b>gratuitamente</b> <br />\n" +
"                                per iPhone, iPad, Android e Windows Phone all'indirizzo <a href='http://www.correntidellostretto.it'>www.correntidellostretto.it</a>\n<br />" +
                                 "<br /><br />Per info e suggerimenti <a hfef='mailto:dev@erainformatica.it'>dev@erainformatica.it</a>" +
                                "</form>";

    public String getUltimaDataAggiornamentoDati() {
        return ultimaDataAggiornamentoDati;
    }

    public void setUltimaDataAggiornamentoDati(String ultimaDataAggiornamentoDati) {
        this.ultimaDataAggiornamentoDati = ultimaDataAggiornamentoDati;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }
    
    

    /**
     * Creates a new instance of ApplicationManagedBean
     */
    public ApplicationManagedBean() {
        ultimaDataAggiornamentoDati = "";
    }
    
    
    public void creaSiteMap(String url) throws MalformedURLException, IOException
    {
        //url +="/prodotto?id=";
        File f = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/"
                + "resources"));
        WebSitemapGenerator wsg = new WebSitemapGenerator(url,f );
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(new Date());
        
        WebSitemapUrl urlx = new WebSitemapUrl.Options(url)
                .lastMod(new Date()).priority(1.0).changeFreq(ChangeFreq.ALWAYS).build();
                wsg.addUrl(urlx);
        
        for (int i=2008; i<calendario.get(Calendar.YEAR)+100; i++)
        {
            urlx = new WebSitemapUrl.Options(url + "/tabella/" + String.valueOf(i))
                    .lastMod(new Date()).priority(1.0).changeFreq(ChangeFreq.MONTHLY).build();
            wsg.addUrl(urlx);
            urlx = new WebSitemapUrl.Options(url + Utility.getAppPath() + "/tavola/" + String.valueOf(i))
                    .lastMod(new Date()).priority(1.0).changeFreq(ChangeFreq.MONTHLY).build();
            wsg.addUrl(urlx);
            urlx = new WebSitemapUrl.Options(url + Utility.getAppPath() + "/messina/" + String.valueOf(i))
                    .lastMod(new Date()).priority(1.0).changeFreq(ChangeFreq.MONTHLY).build();
            wsg.addUrl(urlx);
            for (int k=0; k<12; k++)
            {
                DateFormatSymbols dfs = new DateFormatSymbols(Locale.ITALIAN);
                urlx = new WebSitemapUrl.Options(url + Utility.getAppPath() + "/tavola/messina/" + dfs.getMonths()[k] + "/" + String.valueOf(i))
                    .lastMod(new Date()).priority(1.0).changeFreq(ChangeFreq.MONTHLY).build();
                wsg.addUrl(urlx);
                urlx = new WebSitemapUrl.Options(url + Utility.getAppPath() + "/tabella/messina/" + dfs.getMonths()[k] + "/" + String.valueOf(i))
                    .lastMod(new Date()).priority(1.0).changeFreq(ChangeFreq.MONTHLY).build();
                wsg.addUrl(urlx);
            }
        }
        
        wsg.write();
    }
    
    public String getAppPath() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }
    
    
}
