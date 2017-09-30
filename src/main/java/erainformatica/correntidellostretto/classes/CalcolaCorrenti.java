/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package erainformatica.correntidellostretto.classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polygon;

/**
 *
 * @author robertodedomenico
 */
public class CalcolaCorrenti {
        Double a1 = 0.505868098737548;
        Double a2 = Math.PI / 6.0d;
        Double a3 = 0.496368809964971;
        Double a4 = 0.5250338681713;
        Double a5 = 0.262515837271703;
        Double a6 = 0.243352271640966;
        Double a7 = 0.261082498771273;
        Double a8 = 1.0117361974751;

        Double b1= 2.6d;
        Double b2= 0.7d;
        Double b3= 0.4d;
        Double b4= 0.2d;
        Double b5= 0.7d;
        Double b6= 0.3d;
        Double b7= 0.2d;
        Double b8= 0.2d;

        Double c1= 0.744556d;
        Double c2= 0.174842d;
        Double c3= 0.0000438664d;
        Double c4= 0.00000000622148d;
        Double c5= 0.427921d;
        Double c6= 1.87228d;
        Double c7= 0.290699d;
        Double c8= 2.71778d;

        Double p1,p2,p3,p4,p5,p6,p7,p8,ptot,pp,z1,z2,z3,z4,z5,z6,z7,z8,z10;
        
        Map<Integer,Coords> correnti;
        
        ConfigClass conf;
        
        public CalcolaCorrenti()
        {
            conf = new ConfigClass();
        }

    public Map<Integer, Coords> getCorrenti() {
        return correnti;
    }

    public void setCorrenti(Map<Integer, Coords> correnti) {
        this.correnti = correnti;
    }
        
    public List<Coords> correntiToList()
    {
        return new ArrayList(correnti.values());
    }

        private double convertiData(Calendar now)
        {
            long days = now.get(Calendar.DAY_OF_YEAR);
            for (int i = 2007; i < now.get(Calendar.YEAR); i++)
            {
                Calendar date = new GregorianCalendar();
                date.set(Calendar.YEAR, i);
                date.set(Calendar.MONTH, Calendar.DECEMBER);
                date.set(Calendar.DAY_OF_MONTH, 31);
                days += date.getActualMaximum(Calendar.DAY_OF_YEAR);
            }
            days -= 1;//tolgo 1 giorno non so perche'
            long seconds = days * 86400l;
            seconds += now.get(Calendar.HOUR_OF_DAY) * 3600;
            seconds += now.get(Calendar.MINUTE) * 60;
            seconds += now.get(Calendar.SECOND);
            return (double)seconds / (double)3600;
         }

        public Map<Integer,Coords> eseguiCalcolo(Calendar now)
        {
            Calendar nowTem = (Calendar) now.clone();
            correnti = new HashMap();
            //ORA LEGALE
            if (TimeZone.getDefault().inDaylightTime(nowTem.getTime()))
                nowTem.add(Calendar.HOUR, -1);
            else
                nowTem.add(Calendar.HOUR, -2);
    
            Double ora=convertiData(nowTem);
            p1 = b1 * Math.sin(((ora * a1) + c1));
            p2 = b2 * Math.sin(((ora * a2) + c2));
            p3 = b3 * Math.sin(((ora * a3) + c3));
            p4 = b4 * Math.sin(((ora * a4) + c4));
            p5 = b5 * Math.sin(((ora * a5) + c5));
            p6 = b6 * Math.sin(((ora * a6) + c6));
            p7 = b7 * Math.sin(((ora * a7) + c7));
            p8 = b8 * Math.sin(((ora * a8) + c8));
            ptot = p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8;
            pp= (ptot -0.89d)/0.70d;
            z1= (pp* 0.15d) - 0.30d;
            z2= (pp*0.30d) - 0.59d;
            z3= (pp*0.90d) + 0.63d;
            z4= (pp*0.65d) + 0.46d;
            z5= (pp*0.50d) + 0.75d;
            z6= (pp*0.30d) - 0.59d;
            z7= (pp*0.50d) - 0.25d;
            z8= (pp*0.15d) + 0.10d;
            z10 = (pp * 0.50d) - 0.65d;
            
            for (int i = 0; i<11; i++)
            {
                Coords temp = new ConfigClass().cordeAPP.get(i);
                switch (i)
                {
                    case ConfigClass.CORRENTE_PUNTA_PEZZO:
                        temp.setIntensity(pp);
                        break;
                    case ConfigClass.CORRENTE_ZONA_CENTRALE_SCILLA_CAPO_PELORO:
                        temp.setIntensity(z1);
                        break;
                    case ConfigClass.CORRENTE_ZONA_CENTRALE_TORRE_CAVALLO_CAPO_PELORO:
                        temp.setIntensity(z2);
                        break;
                    case ConfigClass.CORRENTE_CENTRO_TRASVERSALE_GANZIRRI_PUNTA_PEZZO:
                        temp.setIntensity(z3);
                        break;
                    case ConfigClass.CORRENTE_ADIACENZE_SAGATA:
                        temp.setIntensity(z4);
                        break;
                    case ConfigClass.CORRENTE_ADIACENZE_PACE:
                        temp.setIntensity(z5);
                        break;
                    case ConfigClass.CORRENTE_ADIACENZE_SW_TORRE_CAVALLO:
                        temp.setIntensity(z6);
                        break;
                    case ConfigClass.CORRENTE_ADIACENZE_NW_TORRE_CAVALLO:
                        temp.setIntensity(z7);
                        break;
                    case ConfigClass.CORRENTE_BACINO_NW_SCILLA:
                        temp.setIntensity(z8);
                        break;
                    case ConfigClass.CORRENTE_SPIAGGIA_TRA_GANZIRRI_E_TORRE_FARO:
                        temp.setIntensity(ptot);
                        break;
                    case ConfigClass.CORRENTE_DINTORNI_NE_CAPO_PELORO:
                        temp.setIntensity(z10);
                        break;
                }
                correnti.put(i, temp);
            }
            
            return correnti;
        }
        
        

       public MapModel creaPoligoniGoogle(List<Coords> correnti) 
       {
           MapModel polylineModel = new DefaultMapModel();
           for (Coords corrente: correnti)
           {
                double latitudine = corrente.getLatitude();
                double longitudine = corrente.getLongitude();
                double intensità = corrente.getIntensity();
                double angolo = corrente.getInclinazione();
                   //double forza = intensità;
                   intensità = intensità * 0.0209389d; //conversione in nodi come coordinate      
                   if (intensità < 0) {
                       intensità = -intensità;
                       angolo += Math.PI;
                   }
                   double baseFrecciaMezzi = intensità / 20d; //un ventesimo della lunghezza e' la meta' della base della freccia
                   double latoFreccia = intensità / 10d * 9d;
                   double FI = (2d * Math.PI) - angolo;
                   double MCquadro = Math.pow(baseFrecciaMezzi, 2d) + Math.pow(latoFreccia, 2d);
                   double cosAngoloBMC = (MCquadro + Math.pow(baseFrecciaMezzi, 2d) - Math.pow(latoFreccia, 2d)) / (2d * baseFrecciaMezzi * Math.sqrt(MCquadro));
                   double angoloXMC = Math.acos(cosAngoloBMC) - (Math.PI / 2d - FI);
                   double angoloXMG = FI + ((Math.PI / 2d) - Math.acos(cosAngoloBMC));
                   double latoApertura = intensità / 7d;
                   double MDquadro = Math.pow(latoApertura, 2d) + Math.pow(latoFreccia, 2d);
                   double cosAngoloDMK = (MDquadro + Math.pow(latoFreccia, 2d) - Math.pow(latoApertura, 2d)) / (2d * latoFreccia * Math.sqrt(MDquadro));
                   double angoloXMD = FI - Math.acos(cosAngoloDMK);
                   double angoloXMF = FI + Math.acos(cosAngoloDMK);


                 //Punto A (base della freccia punto a destra se la freccia e' rivolta verso l'alto) 
                 LatLng GP1 = new LatLng(latitudine - (baseFrecciaMezzi * Math.sin(Math.PI / 2d - FI)),longitudine - (baseFrecciaMezzi * Math.cos(Math.PI / 2d - FI)));
                 //Punto B (base della freccia punto a sinistra se la freccia e' rivolta verso l'alto) 
                 LatLng GP2 = new LatLng(latitudine + (baseFrecciaMezzi * Math.sin(Math.PI / 2d - FI)),longitudine + (baseFrecciaMezzi * Math.cos(Math.PI / 2d - FI)));
                 //Punto C (prima dell'allargamento della freccia a sinistra se la freccia e' rivolta verso l'alto)
                 LatLng GP3 = new LatLng(latitudine - (Math.sqrt(MCquadro) * Math.sin(angoloXMC)),longitudine + (Math.sqrt(MCquadro) * Math.cos(angoloXMC)));
                 //Punto D (lato estrema sinistra se la freccia e' rivolta verso l'alto)
                 LatLng GP4 = new LatLng(latitudine - (Math.sqrt(MDquadro) * Math.sin(angoloXMD)),longitudine + (Math.sqrt(MDquadro) * Math.cos(angoloXMD)));
                 //Punto E (centro della freccia)
                 LatLng GP5 = new LatLng(latitudine + (intensità * Math.sin(angolo)),longitudine + (intensità * Math.cos(angolo)));
                 //Punto F (lato estrema destra se la freccia e' rivolta verso l'alto)
                 LatLng GP6 = new LatLng(latitudine - (Math.sqrt(MDquadro) * Math.sin(angoloXMF)),longitudine + (Math.sqrt(MDquadro) * Math.cos(angoloXMF)));
                 //Punto G (prima dell'allargamento della freccia a destra se la freccia e' rivolta verso l'alto)
                 LatLng GP7 = new LatLng(latitudine - (Math.sqrt(MCquadro) * Math.sin(angoloXMG)),longitudine + (Math.sqrt(MCquadro) * Math.cos(angoloXMG)));
                 //Create Polygon using above points

                 Polygon polygon = new Polygon();
                 polygon.getPaths().add(GP1);
                 polygon.getPaths().add(GP2);
                 polygon.getPaths().add(GP3);
                 polygon.getPaths().add(GP4);
                 polygon.getPaths().add(GP5);
                 polygon.getPaths().add(GP6);
                 polygon.getPaths().add(GP7);
                 polygon.getPaths().add(GP1);

                 polygon.setStrokeColor("#060606");
                 polygon.setFillColor(Math.abs(corrente.getIntensity())>3 ? ConfigClass.correnteForte: corrente.getIntensity() >0 ? ConfigClass.coloreMontante : ConfigClass.coloreAscendente);
                 polygon.setFillOpacity(corrente.getIntensity()>3 ? 0.6d : 0.9);
                 polygon.setStrokeWeight(2);

                 polylineModel.addOverlay(polygon);
                 Marker m = new Marker(GP1,String.valueOf(corrente.getId()));
                 m.setFlat(true);
                 m.setClickable(true);
                 polylineModel.addOverlay(m);
           }
            return polylineModel;
          }

}
