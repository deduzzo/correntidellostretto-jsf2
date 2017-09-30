/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package erainformatica.correntidellostretto.classes;

/**
 *
 * @author robertodedomenico
 */
public class Coords {
    private Integer id;
    private String luogo;
    private String luogoShort;
    private String ora;
    private Double intensity;
    private Boolean drawable;
    private Double latitude;
    private Double longitude;
    private Double inclinazione;
    private String colore;
    
    public Coords() {
    }

    public Coords(Integer id,String luogo, String luogoShort,String ora, Double intensity, Boolean drawable, Double latitude, Double longitude, Double inclinazione, String colore) {
        this.id = id;
        this.luogo = luogo;
        this.luogoShort = luogoShort;
        this.ora = ora;
        this.intensity = intensity;
        this.drawable = drawable;
        this.latitude = latitude;
        this.longitude = longitude;
        this.inclinazione = inclinazione;
        this.colore = colore;
    }

    public String getLuogoShort() {
        return luogoShort;
    }

    public void setLuogoShort(String luogoShort) {
        this.luogoShort = luogoShort;
    }
    
    public String getLuogo() {
        return luogo;
    }

    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public double getIntensity() {
        return intensity;
    }

    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }

    public Boolean isDrawable() {
        return drawable;
    }

    public void setDrawable(Boolean drawable) {
        this.drawable = drawable;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getInclinazione() {
        return inclinazione;
    }

    public void setInclinazione(double inclinazione) {
        this.inclinazione = inclinazione;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
    
    
}
