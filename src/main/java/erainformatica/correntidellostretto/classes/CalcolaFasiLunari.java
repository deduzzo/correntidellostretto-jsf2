/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package erainformatica.correntidellostretto.classes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author robertodedomenico
 */
public class CalcolaFasiLunari {
    
    
    
    public List<Object> moon_phases(int day, int month, int year)
    {
        int ages[] = {18, 0, 11, 22, 3, 14, 25, 6, 17, 28, 9, 20, 1, 12, 23, 4, 15, 26, 7};
        //int offsets[] = {-1,  1, 0,   1, 2, 3, 4, 5, 7, 7, 9, 9};
          int offsets[] = {-3, -1, -2, -1, 0, 1, 2, 3, 5, 5, 7, 7};
        String description[] = {"Luna nuova",
          "Luna crescente",
          "Primo quarto (crescente)",
          "Gibbosa crescente",
          "Luna piena",
          "Gibbosa calante",
          "Ultimo quarto (calante)",
          "Luna calante"};
        int index,light;
        String status;
        double days_into_phase;
        if (day == 31)
            day = 1;
        days_into_phase = ((ages[(year + 1) % 19] + ((day + offsets[month]) % 30) + (year <1900 ? 1:0)) % 30);
        index = (int)((days_into_phase + 2) * 16/59.0);
        if (index > 7)
            index = 7;
        status = description[index];

        // light should be 100% 15 days into phase
        light = (int)(2 * days_into_phase * 100/29);
        if (light > 100)
            light = Math.abs(light - 200);
        List<Object> temp = new ArrayList();
        temp.add(index);
        temp.add(status);
        temp.add(light);
        return temp;
    }
    
    
    
    
}
