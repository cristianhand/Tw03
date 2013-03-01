/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

/**
 *
 * @author meme
 */
public class TupleClass {
    private String s;
    private int i;

    public TupleClass() {
    }

    public TupleClass(String s, int i) {
        this.s = s;
        this.i = i;
    }
    
    public String getStr() {
        return s;
    }

    public void setStr(String str) {
        this.s = str;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
    
    public String tupleToString(){
        String ret = "";
        ret = "el password es: " + s + "El Id es: " + i;;
        return ret;
    }
    
}
