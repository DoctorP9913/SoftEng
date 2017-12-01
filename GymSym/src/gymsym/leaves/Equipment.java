/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymsym.leaves;

/**
 *
 * @author DoctorP
 */
public class Equipment {
    
    private String equip_name;
    
    Equipment(String equip_name){
        this.equip_name = equip_name;
    }

    public String getEquip_name() {
        return equip_name;
    }

    public void setEquip_name(String equip_name) {
        this.equip_name = equip_name;
    }
    
}
