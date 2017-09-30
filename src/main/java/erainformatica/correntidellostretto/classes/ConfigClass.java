/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package erainformatica.correntidellostretto.classes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author robertodedomenico
 */
public class ConfigClass {
    //Punta Pezzo$ora selezionata$false$38,231354$15,6354868$0,56
    //Zona Centrale tra Scilla e Capo Peloro$+30 minuti$false$38,2601541$15,681438$0,26
    //Zona Centrale tra Torre Cavallo e Capo Peloro$+15 minuti$false$38,260558$15,671138$0,46
    //Centro della Trasversale Ganzirri-Punta Pezzo$ora selezionata$true$38,244707$15,6240874$0,86
    //Adiacenze di S.Agata$-15 minuti$false$38,251796$15,602817$0,16
    //Adiacenze di Pace$-90 minuti$false$38,23582$15,580759$0,86
    //Adiacenze SW di Torre Cavallo$+15 minuti$false$38,253313$15,688819$0,2
    //Adiacenze NW di Torre Cavallo$+15 minuti$false$38,259244$15,704612$0,2
    //Bacino a NW di Scilla$+45 minuti$false$38,264062$15,707316$0,3
    //Spiaggia tra Ganzirri e Torre Faro$+10 minuti$false$38,256851$15,63097$0,3
    //Dintorni NE di Capo Peloro$+30 minuti$false$38,270801$15,664787$0,16
    
    public Map<Integer,Coords> cordeAPP;
    
    
    public static final int CORRENTE_PUNTA_PEZZO = 0;
    public static final int CORRENTE_ZONA_CENTRALE_SCILLA_CAPO_PELORO = 1;
    public static final int CORRENTE_ZONA_CENTRALE_TORRE_CAVALLO_CAPO_PELORO = 2;
    public static final int CORRENTE_CENTRO_TRASVERSALE_GANZIRRI_PUNTA_PEZZO = 3;
    public static final int CORRENTE_ADIACENZE_SAGATA = 4;
    public static final int CORRENTE_ADIACENZE_PACE = 5;
    public static final int CORRENTE_ADIACENZE_SW_TORRE_CAVALLO = 6;
    public static final int CORRENTE_ADIACENZE_NW_TORRE_CAVALLO = 7;
    public static final int CORRENTE_BACINO_NW_SCILLA = 8;
    public static final int CORRENTE_SPIAGGIA_TRA_GANZIRRI_E_TORRE_FARO = 9;
    public static final int CORRENTE_DINTORNI_NE_CAPO_PELORO = 10;

    public static final String coloreMontante = "#1E90FF";
    public static final String coloreAscendente = "#009933";
    public static final String correnteForte = "#FF0000";
    
    public ConfigClass() {
        cordeAPP = new HashMap<>();
        cordeAPP.put(this.CORRENTE_PUNTA_PEZZO,new Coords(this.CORRENTE_PUNTA_PEZZO,"Punta Pezzo","Punta Pezzo","ora selezionata",null,false,38.231354d,15.6354868d,0.56d,null));
        cordeAPP.put(this.CORRENTE_ZONA_CENTRALE_SCILLA_CAPO_PELORO,new Coords(this.CORRENTE_ZONA_CENTRALE_SCILLA_CAPO_PELORO,"Zona Centrale tra Scilla e Capo Peloro","Tra Scilla e Capo Peloro", "+30 minuti",null,false,38.2601541d,15.681438,0.26d, null));
        cordeAPP.put(this.CORRENTE_ZONA_CENTRALE_TORRE_CAVALLO_CAPO_PELORO, new Coords(CORRENTE_ZONA_CENTRALE_TORRE_CAVALLO_CAPO_PELORO,"Zona Centrale tra Torre Cavallo e Capo Peloro","Tra Torre Cavallo e Capo Peloro","+15 minuti",null,false,38.260558d,15.671138d,0.46d, null));
        cordeAPP.put(this.CORRENTE_CENTRO_TRASVERSALE_GANZIRRI_PUNTA_PEZZO,new Coords(CORRENTE_CENTRO_TRASVERSALE_GANZIRRI_PUNTA_PEZZO,"Centro della Trasversale Ganzirri-Punta Pezzo","Centro Ganzirri / Punta Pezzo","ora selezionata",null,false,38.244707d,15.6240874d,0.86d, null));
        cordeAPP.put(this.CORRENTE_ADIACENZE_SAGATA, new Coords(this.CORRENTE_ADIACENZE_SAGATA,"Adiacenze di S.Agata","S. Agata","-15 minuti",null,false,38.251796d,15.602817d,0.16d, null));
        cordeAPP.put(this.CORRENTE_ADIACENZE_PACE, new Coords(this.CORRENTE_ADIACENZE_PACE,"Adiacenze di Pace","Pace","-90 minuti",null,false,38.23582d,15.580759d,0.86, null));
        cordeAPP.put(this.CORRENTE_ADIACENZE_SW_TORRE_CAVALLO, new Coords(this.CORRENTE_ADIACENZE_SW_TORRE_CAVALLO,"Adiacenze SW di Torre Cavallo","SW Torre Cavallo","+15 minuti",null,false,38.253313d,15.688819d,0.2, null));
        cordeAPP.put(this.CORRENTE_ADIACENZE_NW_TORRE_CAVALLO, new Coords(this.CORRENTE_ADIACENZE_NW_TORRE_CAVALLO,"Adiacenze NW di Torre Cavallo","NW Torre Cavallo","+15 minuti",null,false,38.259244d,15.704612d,0.2, null));
        cordeAPP.put(this.CORRENTE_BACINO_NW_SCILLA, new Coords(this.CORRENTE_BACINO_NW_SCILLA,"Bacino a NW di Scilla","NW Scilla","+45 minuti",null,false,38.264062d,15.707316d,0.3d, null));
        cordeAPP.put(this.CORRENTE_SPIAGGIA_TRA_GANZIRRI_E_TORRE_FARO,new Coords(this.CORRENTE_SPIAGGIA_TRA_GANZIRRI_E_TORRE_FARO,"Spiaggia tra Ganzirri e Torre Faro","Tra Ganzirri e Torre Faro","+10 minuti",null,false,38.256851d,15.63097d,0.3d, null));
        cordeAPP.put(this.CORRENTE_DINTORNI_NE_CAPO_PELORO, new Coords(this.CORRENTE_DINTORNI_NE_CAPO_PELORO,"Dintorni NE di Capo Peloro","NE Capo Peloro","+30 minuti",null,false,38.270801d,15.664787d,0.16d, null));
    }

    public Map<Integer, Coords> getCordeAPP() {
        return cordeAPP;
    }

    public void setCordeAPP(Map<Integer, Coords> cordeAPP) {
        this.cordeAPP = cordeAPP;
    }
    
    
    
    
}
