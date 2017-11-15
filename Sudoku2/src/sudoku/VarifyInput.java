
package sudoku;

import javax.swing.*;


/**
 *
 * @author v
 */
public class VarifyInput extends InputVerifier {

    @Override
    public boolean verify(JComponent input) {
        String text=((JTextField)input).getText();
        
        int textInt;
        try{
        textInt=Integer.parseInt(text);
                }catch (NumberFormatException mfEx){
                    
                    textInt=0;
                }
        if(textInt>=1||textInt<=9||text=="") return true;
        else return false;
        
    }
    
}
