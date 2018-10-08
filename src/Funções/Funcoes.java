package Funções;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

public class Funcoes {

    private boolean test = true;
    
    public void setTest(boolean test) {
        this.test = test;
    }
//    
//    public boolean CheckProcess(String process){
//        
//        try {
//            String line;
//            Process p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
//            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
//                while ((line = input.readLine()) != null) {
//                    if(line.contains(process)){
//                        setTest(true);
//                    }
//                }
//            input.close();
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, "An error has occurred!" +e);
//        }
//        return test;
//    }
    
    public void ExecSave(String process, long tms){
    
        Timer verifica = new Timer();
        TimerTask executa = new TimerTask(){
        public void run(){
        try {
            String line;
            Process p = Runtime.getRuntime().exec(System.getenv("windir") +"\\system32\\"+"tasklist.exe");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
                while ((line = input.readLine()) != null) {
                    if(line.contains(process) && test){
                        Robot();
                    }
                }
            input.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error has occurred!" +e);
        }
        }};
        verifica.scheduleAtFixedRate(executa, tms, tms);
    }
    
    public void Robot(){
    
        try{
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_B);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_B);
            System.out.println("Auto Save was Executed!");
        }catch (AWTException e) {
            JOptionPane.showMessageDialog(null, "An error has occurred!" +e);
        }
        
    }
    
}
