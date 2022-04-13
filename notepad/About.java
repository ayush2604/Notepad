package notepad;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import javax.swing.*;
public class About extends JFrame implements ActionListener{
    About(){
        this.setTitle("About Notepad");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(400,400,300,200);
        this.setResizable(false);
        this.setLayout(null);   
        JButton okButton = new JButton("OK");
        okButton.setBounds(200,100,70,40);
        okButton.addActionListener(this);
        this.add(okButton);
        JLabel description = new JLabel("<html>This is a notepad made for <br> opening and editing .txt files.</html>");
        description.setBounds(40,10,150,80);
        this.add(description);
    }
    public void actionPerformed(ActionEvent e){
        this.setVisible(false);
        this.dispose();
    }
    public static void main(String[] args) {
        new About().setVisible(true);
    }
}
