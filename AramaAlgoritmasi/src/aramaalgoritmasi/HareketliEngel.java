/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aramaalgoritmasi;


public class HareketliEngel extends Engel {
    
    private String hareketYonu;
    
    public String getHareketYonu() {
        return hareketYonu;
    }
    
    public void setHareketYonu(String hareketYonu) {
        this.hareketYonu = hareketYonu;
    }
                
    public class Ari {
        
        public Ari() {
            setNesneAdi("ari");
            setHareketYonu("yatay");
            setBoyutX(1);
            setBoyutY(1);
            
        }
        
    }
    
    public class Kus {
        
        public Kus() {            
            setNesneAdi("kus");
            setHareketYonu("dikey");
            setBoyutX(2);
            setBoyutY(2);
        }
    }
    
}
