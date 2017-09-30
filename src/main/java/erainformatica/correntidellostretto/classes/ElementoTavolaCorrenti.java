/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erainformatica.correntidellostretto.classes;

import java.util.Date;

/**
 *
 * @author robertodedomenico
 */
public class ElementoTavolaCorrenti {
    
    Date orario;
    boolean isPicco;
    Double intensita;

    public Date getOrario() {
        return orario;
    }

    public void setOrario(Date orario) {
        this.orario = orario;
    }

    public boolean isIsPicco() {
        return isPicco;
    }

    public void setIsPicco(boolean isPicco) {
        this.isPicco = isPicco;
    }

    public Double getIntensita() {
        return intensita;
    }

    public void setIntensita(Double intensita) {
        this.intensita = intensita;
    }
    
    public ElementoTavolaCorrenti()
    {
        
    }

    public ElementoTavolaCorrenti(Date orario, boolean isPicco, Double intensita) {
        this.orario = orario;
        this.isPicco = isPicco;
        this.intensita = intensita;
    }
    
    
}
