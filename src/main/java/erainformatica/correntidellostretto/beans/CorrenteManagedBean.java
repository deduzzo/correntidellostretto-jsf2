/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package erainformatica.correntidellostretto.beans;

import erainformatica.correntidellostretto.classes.CalcolaCorrenti;
import erainformatica.correntidellostretto.classes.CalcolaFasiLunari;
import erainformatica.correntidellostretto.classes.ConfigClass;
import erainformatica.correntidellostretto.classes.Coords;
import erainformatica.correntidellostretto.classes.ElementoTavolaCorrenti;
import erainformatica.correntidellostretto.classes.Utility;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 *
 * @author robertodedomenico
 */
@Named(value = "correnteManagedBean")
@ViewScoped
public class CorrenteManagedBean implements Serializable {

    CalcolaCorrenti correnti;
    
    MapModel frecce;
    
    Date orario;
    Date stanca;
    Date picco;
    Boolean andamento;
    Coords correnteSelezionata;
    
    LineChartModel andamentoCorrente;
    
    Integer oreMobile, minutiMobile,giornoMobile,meseMobile,annoMobile;
    
    List<Integer> giorniMobile, mesiMobile, anniMobile;
    private List<Object> datiLunari;
    
    String faseLunare;
    Integer valLuna;
    Integer luceLuna;
    private List<Object> storicoLuna;
    
    Boolean mostraMesiAnni;
    
    Integer puntoSelezionato;
    List<Coords> nomiCorrenti;
    
    /**
     * Creates a new instance of CorrenteManagedBean
     */
    public CorrenteManagedBean() {
        correnti = new CalcolaCorrenti();
        mesiMobile = new ArrayList();
        for (int i =1; i<13;i++)
            mesiMobile.add(i);
        Calendar temp = Calendar.getInstance();
        anniMobile = new ArrayList();
        for (int i=2008; i<temp.get(Calendar.YEAR)+200; i++)
            anniMobile.add(i);
        mostraMesiAnni = false;
    }

    public Integer getPuntoSelezionato() {
        if (puntoSelezionato == null)
        {
            puntoSelezionato = ConfigClass.CORRENTE_CENTRO_TRASVERSALE_GANZIRRI_PUNTA_PEZZO;
        }
        return puntoSelezionato;
    }

    public void setPuntoSelezionato(Integer puntoSelezionato) {
        this.puntoSelezionato = puntoSelezionato;
    }


    public Boolean getMostraMesiAnni() {
        return mostraMesiAnni;
    }

    public void setMostraMesiAnni(Boolean mostraMesiAnni) {
        this.mostraMesiAnni = mostraMesiAnni;
    }
    
    

    public List<Object> getStoricoLuna() {
        if (datiLunari == null)
            storicoLuna = calcolaDatiLunari();
        return storicoLuna;
    }

    public void setStoricoLuna(List<Object> storicoLuna) {
        this.storicoLuna = storicoLuna;
    }

    public String getFaseLunare() {
        if (faseLunare == null)
        {
            if (datiLunari == null)
                storicoLuna = calcolaDatiLunari();
            faseLunare = (String) datiLunari.get(1);
        }
        return faseLunare;
    }

    public void setFaseLunare(String faseLunare) {
        this.faseLunare = faseLunare;
    }

    public Integer getValLuna() {
        if (valLuna == null)
        {
            if (datiLunari == null)
                storicoLuna = calcolaDatiLunari();
            valLuna =  (Integer) datiLunari.get(0);
        }
        return valLuna;
    }

    public void setValLuna(Integer valLuna) {
        this.valLuna = valLuna;
    }

    public Integer getLuceLuna() {
        if (luceLuna == null)
        {
            if (datiLunari == null)
                storicoLuna = calcolaDatiLunari();
            luceLuna =  (Integer) datiLunari.get(2);
        }
        return luceLuna;
    }

    public void setLuceLuna(Integer luceLuna) {
        this.luceLuna = luceLuna;
    }

    public List<Coords> getNomiCorrenti() {
        if (nomiCorrenti == null)
        {
            nomiCorrenti = new ArrayList(new ConfigClass().cordeAPP.values());
        }
        return nomiCorrenti;
    }

    public void setNomiCorrenti(List<Coords> nomiCorrenti) {
        this.nomiCorrenti = nomiCorrenti;
    }
    
    

    public Integer getGiornoMobile() {
        Calendar temp = Calendar.getInstance();
        temp.setTime((Date) getOrario().clone());
        giornoMobile = temp.get(Calendar.DAY_OF_MONTH);
        return giornoMobile;
    }

    public void setGiornoMobile(Integer giornoMobile) {
        this.giornoMobile = giornoMobile;
    }

    public Integer getMeseMobile() {
        Calendar temp = Calendar.getInstance();
        temp.setTime((Date) getOrario().clone());
        meseMobile = temp.get(Calendar.MONTH) +1;
        return meseMobile;
    }

    public void setMeseMobile(Integer meseMobile) {
        this.meseMobile = meseMobile;
    }

    public Integer getAnnoMobile() {
        Calendar temp = Calendar.getInstance();
        temp.setTime((Date) getOrario().clone());
        annoMobile = temp.get(Calendar.YEAR);
        return annoMobile;
    }

    public void setAnnoMobile(Integer annoMobile) {
        this.annoMobile = annoMobile;
    }
    
    
    public List<Integer> getGiorniMobile() {
        {
            Calendar temp = Calendar.getInstance();
            temp.setTime((Date) getOrario().clone());
            giorniMobile = new ArrayList();
            for (int i=1; i<temp.getActualMaximum(Calendar.DATE) +1; i++)
                giorniMobile.add(i);
        }
        return giorniMobile;
    }

    public void setGiorniMobile(List<Integer> giorniMobile) {
        this.giorniMobile = giorniMobile;
    }

    public List<Integer> getMesiMobile() {
        return mesiMobile;
    }

    public void setMesiMobile(List<Integer> mesiMobile) {
        this.mesiMobile = mesiMobile;
    }

    public List<Integer> getAnniMobile() {
        return anniMobile;
    }

    public void setAnniMobile(List<Integer> anniMobile) {
        this.anniMobile = anniMobile;
    }
    
    public Boolean getAndamento() {
        if (andamento==null)
            getStanca();
        return andamento;
    }

    public void setAndamento(Boolean andamento) {
        this.andamento = andamento;
    }

    public Date getStanca() {
        if (stanca == null)
        {
            List<Object> te = calcolaStanca(getOrario(),puntoSelezionato);
            stanca = (Date) ((Date) te.get(0)).clone();
            picco = (Date) ((Date) te.get(1)).clone();
            andamento = (Boolean) te.get(2);
            
        }
        return stanca;
    }

    public void setStanca(Date stanca) {
        this.stanca = stanca;
    }

    public Date getPicco() {
        if (picco == null)
        {
            List<Object> te = calcolaStanca(getOrario(),puntoSelezionato);
            stanca = (Date) ((Date) te.get(0)).clone();
            picco = (Date) ((Date) te.get(1)).clone();
            andamento = (Boolean) te.get(2);
        }
        return picco;
    }

    public void setPicco(Date picco) {
        this.picco = picco;
    }
    
    

    public Integer getOreMobile() {
        Calendar c = Calendar.getInstance();
        c.setTime(getOrario());
        oreMobile = c.get(Calendar.HOUR_OF_DAY);
        return oreMobile;
    }

    public void setOreMobile(Integer oreMobile) {
        this.oreMobile = oreMobile;
    }

    public Integer getMinutiMobile() {
        Calendar c = Calendar.getInstance();
        c.setTime(getOrario());
        minutiMobile = c.get(Calendar.MINUTE);
        return minutiMobile;
    }

    public void setMinutiMobile(Integer minutiMobile) {
        this.minutiMobile = minutiMobile;
    }

    public LineChartModel getAndamentoCorrente() {
        if (andamentoCorrente == null)
        {
            andamentoCorrente = new LineChartModel();
            CalcolaCorrenti correntiTemp = new CalcolaCorrenti();
            Calendar now = Calendar.getInstance();
            now.setTime(getOrario());
            now.add(Calendar.DAY_OF_YEAR, -2);
            now.set(Calendar.MINUTE, 0);
            correntiTemp.eseguiCalcolo(now);
            andamentoCorrente.setTitle("Andamento Corrente");
            andamentoCorrente.setLegendPosition("e");
            andamentoCorrente.setShowPointLabels(false);
            
            andamentoCorrente.getAxes().put(AxisType.X, new CategoryAxis("Tempo"));
            Axis yAxis = andamentoCorrente.getAxis(AxisType.Y);
            yAxis.setLabel("Intensità");
            
            LineChartSeries series1 = new LineChartSeries();
            List<Coords> correntiT = new ArrayList(correntiTemp.correntiToList());
            series1.setLabel(correntiT.get(0).getLuogo());
            for (int i = 0; i<4000; i++)
            {
                now.add(Calendar.MINUTE, 30);
                correntiTemp.eseguiCalcolo(now);
                correntiT = correntiTemp.correntiToList();
                series1.set(String.valueOf(String.valueOf(now.get(Calendar.DAY_OF_MONTH))) + String.valueOf(now.get(Calendar.HOUR)) + "." + String.valueOf(now.get(Calendar.MINUTE)) , correntiT.get(0).getIntensity());
            }
            andamentoCorrente.addSeries(series1);
        }
        return andamentoCorrente;
    }

    public void setAndamentoCorrente(LineChartModel andamentoCorrente) {
        this.andamentoCorrente = andamentoCorrente;
    }

    public Date getOrario() {
        if (orario == null)
        {
            String anno =(String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("anno");
            if (anno == null)
                orario = new Date();
            else
            {
                boolean error = false;
                Calendar temp = Calendar.getInstance();
                try
                {
                    if (Integer.valueOf(anno) >2007 && Integer.valueOf(anno) <Calendar.getInstance().get(Calendar.YEAR) + 100)
                        temp.set(Calendar.YEAR,Integer.valueOf(anno));
                }
                catch (Exception e)
                {
                    orario = new Date();
                    error = true;
                }
                if (!error)
                {
                    try
                    {
                        String mese = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mese");
                        mese = mese.toLowerCase();
                        if (mese != null)
                        {
                            DateFormatSymbols dfs = new DateFormatSymbols(Locale.ITALIAN);
                            List<String> mesi = Arrays.asList(dfs.getMonths());
                            if (mesi.contains(mese))
                                temp.set(Calendar.MONTH, mesi.indexOf(mese));
                            else
                                mostraMesiAnni = true;
                        }
                        else
                            mostraMesiAnni = true;
                    }
                    catch (Exception e) 
                    {
                        temp.set(Calendar.MONTH, 1);
                        mostraMesiAnni = true;
                    }
                    temp.set(Calendar.DAY_OF_MONTH, 1);
                    orario = temp.getTime();
                }
            }
        }
        else 
            if (minutiMobile != null)
            {
                Calendar temp = Calendar.getInstance();
                temp.setTime(orario);
                temp.set(Calendar.HOUR_OF_DAY, oreMobile);
                temp.set(Calendar.MINUTE, minutiMobile);
                temp.set(Calendar.YEAR, annoMobile);
                temp.set(Calendar.MONTH, meseMobile -1);
                temp.set(Calendar.DAY_OF_MONTH, giornoMobile);
                orario = (Date) temp.getTime().clone();
            }
        return orario;
    }

    public void setOrario(Date orario) {
        this.orario = orario;
    }
    
    public MapModel getFrecce() {
        if (frecce == null)
        {
            Calendar now = Calendar.getInstance();
            now.setTime(getOrario());
            Map<Integer,Coords> c = new HashMap<>(correnti.eseguiCalcolo(now));
            frecce = correnti.creaPoligoniGoogle(new ArrayList<>(c.values()));
        }
        return frecce;
    }
    
    private List<Object> calcolaStanca(Date data, int punto)
    {
        Calendar temp = Calendar.getInstance();
        temp.setTime((Date) data.clone());
        CalcolaCorrenti correntiTemp = new CalcolaCorrenti();
        double correntePezzo = 0d;
        double correntePezzoPrec =0d;
        Double quantoPicco= 0d;
        int intervallo = 1;
        Date piccoT = new Date();
        Date stancaT = new Date();
        boolean positiva;
        Boolean positivaOld = null;
        boolean piccoTrovato = false;
        boolean stancaTrovata = false;
        Boolean staSalendo = null;
        Boolean staSalendoOld = null;
        correntiTemp.eseguiCalcolo(temp);
        correntePezzoPrec = correntiTemp.getCorrenti().get(punto).getIntensity();
        do {
                do
                {
                    temp.add(Calendar.MINUTE, intervallo);
                    correntiTemp.eseguiCalcolo(temp);
                    correntePezzo = correntiTemp.getCorrenti().get(punto).getIntensity();
                }
                while (correntePezzo == correntePezzoPrec);
                positiva = correntePezzo >0;
                if (positivaOld == null)
                    positivaOld = positiva;
                staSalendo = correntePezzoPrec < correntePezzo;
                if (staSalendoOld == null)
                {
                    staSalendoOld = staSalendo;
                }
                if (positivaOld != positiva)
                {
                    if (!stancaTrovata)
                    {
                            stancaTrovata = true;
                            stancaT = (Date) temp.getTime().clone();
                    }
                }
                if (staSalendoOld != staSalendo.booleanValue())
                {
                    if (!piccoTrovato)
                    {
                            piccoTrovato = true;
                            piccoT = (Date) temp.getTime().clone();
                            quantoPicco = correntePezzo;
                    }
                    
                }
                correntePezzoPrec = correntePezzo;
                staSalendoOld = staSalendo;
                positivaOld = positiva;
            }
        while (!stancaTrovata || !piccoTrovato);
        List<Object> l = new ArrayList();
        l.add(stancaT);
        l.add(piccoT);
        l.add(stancaT.after(piccoT));
        l.add(quantoPicco);
        return l;
    }
    
    public List<ElementoTavolaCorrenti> calcolaTabellaCorrentiDelMese(Date d, Integer punto)
    {
        Calendar dataOriginal = Calendar.getInstance();
        dataOriginal.setTime(d);
        Calendar data = Calendar.getInstance();
        data.setTime(d);
        data.set(Calendar.DAY_OF_MONTH, 1);
        data.set(Calendar.HOUR_OF_DAY, 0);
        data.set(Calendar.MINUTE,0);
        
        List<ElementoTavolaCorrenti> tabella = new ArrayList();
        
        while (data.get(Calendar.MONTH) == dataOriginal.get(Calendar.MONTH))
        {
            Date stancaTemp,piccoTemp;
            List<Object> te = calcolaStanca(data.getTime(),punto);
            stancaTemp = (Date) ((Date) te.get(0)).clone();
            piccoTemp = (Date) ((Date) te.get(1)).clone();
            Calendar temp = Calendar.getInstance();
            if (!stancaTemp.after(piccoTemp))
            {
                temp.setTime(stancaTemp);
                if (data.get(Calendar.MONTH) == temp.get(Calendar.MONTH))
                    tabella.add(new ElementoTavolaCorrenti(stancaTemp,false,null));
                temp.setTime(piccoTemp);
                if (data.get(Calendar.MONTH) == temp.get(Calendar.MONTH))
                    tabella.add(new ElementoTavolaCorrenti(piccoTemp,true,(Double) te.get(3)));
                data.setTime(piccoTemp);
                data.add(Calendar.MINUTE, 20);
            }
            else
            {
                temp.setTime(piccoTemp);
                if (data.get(Calendar.MONTH) == temp.get(Calendar.MONTH))
                    tabella.add(new ElementoTavolaCorrenti(piccoTemp,true,(Double) te.get(3)));
                temp.setTime(stancaTemp);
                if (data.get(Calendar.MONTH) == temp.get(Calendar.MONTH))
                    tabella.add(new ElementoTavolaCorrenti(stancaTemp,false,null));
                data.setTime(stancaTemp);
                data.add(Calendar.MINUTE, 20);
            }
        }
        
        return tabella;
        
    }
    
    public HashMap<Date,List<ElementoTavolaCorrenti>> tavolaCorrentiPerGiorno(List<ElementoTavolaCorrenti> tav)
    {
        HashMap main = new HashMap();
        for (ElementoTavolaCorrenti el:tav)
        {
            
        }
        
        return main;
        // INCOMPLETO;
    }
    
    public List<List<ElementoTavolaCorrenti>> ottieniTavolaCorrenti()
    {
        List<ElementoTavolaCorrenti> tav = calcolaTabellaCorrentiDelMese(getOrario(),puntoSelezionato);
        List<List<ElementoTavolaCorrenti>> definitivo = new ArrayList();
        Calendar dataCorrente = Calendar.getInstance();
        dataCorrente.setTime(getOrario());
        
        List<ElementoTavolaCorrenti> correntiDelGiorno = new ArrayList();
        int giornoOld = 1;
        if (tav.get(0).isIsPicco())
            tav.remove(tav.get(0));
        for (ElementoTavolaCorrenti el:tav)
        {
            Calendar dataTemp = Calendar.getInstance();
            dataTemp.setTime(el.getOrario());
            if (dataTemp.get(Calendar.DAY_OF_MONTH)>giornoOld)
            {
                // giorno successivo
                if (correntiDelGiorno.size() < 8)
                {
                    if (el.isIsPicco())
                        correntiDelGiorno.add(el);
                    while (correntiDelGiorno.size() < 8)
                    {
                        correntiDelGiorno.add(null);
                    }
                }
                definitivo.add(correntiDelGiorno);
                correntiDelGiorno = new ArrayList();
                if (!el.isIsPicco())
                    correntiDelGiorno.add(el);
                giornoOld = dataTemp.get(Calendar.DAY_OF_MONTH);
            }
            else
            {
                correntiDelGiorno.add(el);
            }
        }
        if (correntiDelGiorno.size() < 8)
        {
            while (correntiDelGiorno.size() < 8)
            {
                correntiDelGiorno.add(null);
            }
        }
        definitivo.add(correntiDelGiorno);

        return definitivo;
    }
    
    public List<List<ElementoTavolaCorrenti>> ottieniTavolaCorrentiOld()
    {
        List<ElementoTavolaCorrenti> tav = calcolaTabellaCorrentiDelMese(getOrario(),puntoSelezionato);
        List<List<ElementoTavolaCorrenti>> definitivo = new ArrayList();
        List<ElementoTavolaCorrenti> temp = new ArrayList();
        Calendar dataCorrente = Calendar.getInstance();
        dataCorrente.setTime(getOrario());
        boolean nuovo = true;
        boolean nuovonuovo = true;
        int count = 0;
        for (ElementoTavolaCorrenti el:tav)
        {
            Calendar cal = Calendar.getInstance();
            cal.setTime(el.getOrario());
            if (dataCorrente.get(Calendar.DAY_OF_MONTH) != cal.get(Calendar.DAY_OF_MONTH))
            {
                if (!nuovonuovo)
                {
                    if (el.isIsPicco())
                    {
                        temp.add(el);
                        count++;
                    }
                    while (count < 8)
                    {
                        temp.add(null);
                        count++;
                    }
                    definitivo.add(temp);
                    temp = new ArrayList();
                }
                else 
                {
                    nuovonuovo = false;
                }
                count = 0;
                nuovo = true;
                dataCorrente.setTime(cal.getTime());
            }
            else
                nuovo = false;
            if (nuovo && el.isIsPicco())
            {
            }
            else
            {
                temp.add(el);
                count++;
            }
        } 
        while (count < 8)
        {
            temp.add(null);
            count++;
        }
        definitivo.add(temp);
        return definitivo;
    }

    public void setFrecce(MapModel frecce) {
        this.frecce = frecce;
    }

    public Coords getCorrenteSelezionata() {
        return correnteSelezionata;
    }

    public void setCorrenteSelezionata(Coords correnteSelezionata) {
        this.correnteSelezionata = correnteSelezionata;
    }
    
    public void ricalcola()
    {
        frecce = null;
        stanca = null;
        picco = null;
        andamento = null;
        datiLunari = null;
        faseLunare = null;
        luceLuna = null;
        valLuna = null;
        RequestContext.getCurrentInstance().execute("forceResize();");
        RequestContext.getCurrentInstance().update("unica:gmap");
        RequestContext.getCurrentInstance().update("unica:fooMain:fooTXT");
        RequestContext.getCurrentInstance().update("unica:fasiLunari");
        RequestContext.getCurrentInstance().update("tavola:tavoleForm:tavole");
        RequestContext.getCurrentInstance().update("tavoleForm:nomePuntoCalcolo");
    }
    
    public void onMarkerSelect(OverlaySelectEvent event) {
        correnteSelezionata = correnti.getCorrenti().get(Integer.valueOf(((Marker) event.getOverlay()).getTitle()));
    }
    
    public String roundDouble(double d)
    {
        DecimalFormat df = new DecimalFormat("#.#####");
        return df.format(Math.abs(d));
    }
    
    public List<Coords> datiCorrenti()
    {
        return correnti.correntiToList();
    }
    
    public String coloreCorrenteMontante()
    {
        return ConfigClass.coloreMontante;
    }
    
    public String coloreCorrenteAscendente()
    {
        return ConfigClass.coloreAscendente;
    }
    
    public String coloreCorrenteForte()
    {
        return ConfigClass.correnteForte;
    }
    
    public String oraLegale()
    {
        if (TimeZone.getDefault().inDaylightTime(orario))
            return "Ora Legale";
        else
            return "Ora Solare";
    }
    
    public String giornoOraString()
    {
        return Utility.dateToString(Utility.CONST_UTIL_FORMAT_DATA_ORA_PARLANTE, getOrario());
    }
    
    public String giornoOraString(Date data)
    {
        return Utility.dateToString(Utility.CONST_UTIL_FORMAT_DATA_ORA_PARLANTE, data);
    }
    
    public String tipoCorrente()
    {
        return correnti.getCorrenti().get(ConfigClass.CORRENTE_PUNTA_PEZZO).getIntensity()>0 ? "MONTANTE" : "SCENDENTE";
    }
    
    public void aggiornaGiorniDelMese()
    {
        RequestContext.getCurrentInstance().update("unica:main:selezionaData");
    }

    private List<Object> calcolaDatiLunari() {
        List<Object> storicoLunare = new ArrayList();
        CalcolaFasiLunari f = new CalcolaFasiLunari();
        Calendar temp = Calendar.getInstance();
        temp.setTime(getOrario());
        datiLunari = f.moon_phases(temp.get(Calendar.DAY_OF_MONTH), temp.get(Calendar.MONTH), temp.get(Calendar.YEAR));
        int indexPrecedente = (int) datiLunari.get(0);
        int indexOriginario = indexPrecedente;
        List<Object> tempLuna = new ArrayList(datiLunari);
        int count = 0;
        while (count <4)
        {
            while ((int)tempLuna.get(0) == indexPrecedente)
            {
                temp.add(Calendar.DAY_OF_YEAR, 1);
                tempLuna = f.moon_phases(temp.get(Calendar.DAY_OF_MONTH), temp.get(Calendar.MONTH), temp.get(Calendar.YEAR));
            }
            tempLuna.add(temp.getTime());
            storicoLunare.add(tempLuna);
            indexPrecedente = (int)tempLuna.get(0);
            if (indexOriginario == indexPrecedente)
                count++;
        }
        return storicoLunare;
    }
    
    public String vaiAlleTavole()
    {
        return "pm:tavola";
    }
    
    public String dateToString(String formato, Date data)
    {
        return Utility.dateToString(formato, data);
    }
    
    public boolean ePari(int numero)
    {
        return numero%2 == 0;
    }
    
    public int getOre (Date d)
    {
        Calendar temp = Calendar.getInstance();
        temp.setTime(d);
        return temp.get(Calendar.HOUR_OF_DAY);
    }
    
    public String ottieniNomeMeseCorrente(Boolean aggiornaPagina)
    {
        Calendar temp = Calendar.getInstance();
        temp.setTime(this.getOrario());
        DateFormatSymbols dfs = new DateFormatSymbols(Locale.ITALIAN);
        if (aggiornaPagina)
        {
            if ((String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("anno") == null)
                RequestContext.getCurrentInstance().execute("resizeMap(true);");
            else
                RequestContext.getCurrentInstance().execute("resizeMap(false);");
        }
        return dfs.getMonths()[temp.get(Calendar.MONTH)];
    }
    
    public String ottieniNomeMeseCorrenteFromIndex(int index)
    {
        DateFormatSymbols dfs = new DateFormatSymbols(Locale.ITALIAN);
        return dfs.getMonths()[index];
    }
    
    public String intensitaRoundABS(Double val)
    {
        return String.valueOf(Utility.round(Math.abs(val), 1));
    }
    
    public String titolo()
    {
        String temp = "";
        String anno =(String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("anno");
        String mese =(String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mese");
        boolean annoOk = false,meseOk = false;
        if (anno !=null)
        {
            if (Integer.valueOf(anno) >2007 && Integer.valueOf(anno) <Calendar.getInstance().get(Calendar.YEAR) + 100)
            {
                annoOk=true;
                if (mese!= null)
                {
                    mese = mese.toLowerCase();
                    DateFormatSymbols dfs = new DateFormatSymbols(Locale.ITALIAN);
                    List<String> mesi = Arrays.asList(dfs.getMonths());
                    if (mesi.contains(mese))
                    {
                        meseOk=true;
                    }
                }
            }
        }
        if (annoOk && meseOk)
            temp += "Tabella delle Correnti Stretto di Messina " + mese.toLowerCase() + " " + anno + " - Tavola correnti Stretto di Messina";
        else if (annoOk && meseOk)
            temp += "Tabella delle Correnti Stretto di Messina anno " + anno + " - Tavola correnti dello Stretto di Messina";
        else
            temp += "Correnti dello Stretto - Valori e calcolo Correnti Stretto di Messina";
        return temp;
    }
    
    public boolean mostraMesiAnni()
    {
        boolean mostra = false;
        String anno =(String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("anno");
        String mese =(String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("mese");
        boolean annoOk = false,meseOk = false;
        if (anno !=null)
        {
            if (Integer.valueOf(anno) >2007 && Integer.valueOf(anno) <Calendar.getInstance().get(Calendar.YEAR) + 100)
            {
                annoOk=true;
                if (mese!= null)
                {
                    mese = mese.toLowerCase();
                    DateFormatSymbols dfs = new DateFormatSymbols(Locale.ITALIAN);
                    List<String> mesi = Arrays.asList(dfs.getMonths());
                    if (mesi.contains(mese))
                    {
                        meseOk=true;
                    }
                }
            }
        }
        if (annoOk && !meseOk)
        {
            mostra = true;
        }
        return mostra;
    }
    
    public String ottieniLinkVerbose(int mese)
    {
        Calendar anno = Calendar.getInstance();
        anno.setTime(orario);
        DateFormatSymbols dfs = new DateFormatSymbols(Locale.ITALIAN);
        return "/tavola/messina/" + dfs.getMonths()[mese] + "/" + String.valueOf(anno.get(Calendar.YEAR));
    }
    
    public String ottieniNomePuntoSelezionata()
    {
        return new ConfigClass().cordeAPP.get(puntoSelezionato).getLuogoShort();
    }
    
}
