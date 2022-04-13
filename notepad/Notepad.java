package notepad;
import java.awt.event.ActionEvent;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;
public class Notepad extends JFrame implements ActionListener{
    JTextArea textArea;
    JScrollPane scrollPane;
    String text;
    Notepad(){
        this.setTitle("Notepad");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setBounds(100,100,800,600);
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem newDoc = new JMenuItem("New");
        newDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        newDoc.addActionListener(this);

        JMenuItem open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        open.addActionListener(this);

        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        save.addActionListener(this);

        JMenuItem print = new JMenuItem("Print");
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
        print.addActionListener(this);

        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
        exit.addActionListener(this);

        file.add(newDoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);

        JMenu edit = new JMenu("Edit");
        JMenuItem cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
        cut.addActionListener(this);

        JMenuItem copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
        copy.addActionListener(this);

        JMenuItem paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
        paste.addActionListener(this);

        JMenuItem selectAll = new JMenuItem("Select All");
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
        selectAll.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);

        JMenu help = new JMenu("Help");
        JMenuItem aboutNotepad = new JMenuItem("About Notepad");
        aboutNotepad.addActionListener(this);

        help.add(aboutNotepad);
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(help);
        setJMenuBar(menuBar);
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.add(scrollPane,BorderLayout.CENTER);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("New")){
            this.textArea.setText("");
        }
        else if(e.getActionCommand().equals("Open")){
            JFileChooser openFile = new JFileChooser();
            openFile.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrictedFiles = new FileNameExtensionFilter("Only .txt files","txt");
            openFile.addChoosableFileFilter(restrictedFiles);
            if(openFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) return;
            File file = openFile.getSelectedFile();
            try{
                BufferedReader reader = new BufferedReader(new FileReader(file));
                this.textArea.read(reader,null);
            }
            catch(Exception ex){
                System.out.println(ex);
            }
        }
        else if(e.getActionCommand().equals("Save")){
            JFileChooser saveFile = new JFileChooser();
            saveFile.setApproveButtonText("Save");
            if(saveFile.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) return;
            File fileName = new File(saveFile.getSelectedFile()+".txt");
            BufferedWriter outFile = null;
            try{
                outFile = new BufferedWriter(new FileWriter(fileName));
                textArea.write(outFile);
            }
            catch(Exception ex){
                System.out.println(ex);
            }
        }
        else if(e.getActionCommand().equals("Print")){
            try{
                textArea.print();
            }
            catch(Exception ex){
                System.out.println(ex);
            }
        }
        else if(e.getActionCommand().equals("Exit")){
            System.exit(0);
        }
        else if(e.getActionCommand().equals("Cut")){
            text = textArea.getSelectedText();
            textArea.replaceRange("",textArea.getSelectionStart(),textArea.getSelectionEnd());
        }
        else if(e.getActionCommand().equals("Copy")){
            text = textArea.getSelectedText();
        }
        else if(e.getActionCommand().equals("Paste")){
            textArea.insert(text,textArea.getCaretPosition());
        }
        else if(e.getActionCommand().equals("Select All")){
            textArea.selectAll();
        }
        else if(e.getActionCommand().equals("About Notepad")){
            new About().setVisible(true);
        }
    }
    public static void main(String args[]){
        new Notepad().setVisible(true);
    }
}