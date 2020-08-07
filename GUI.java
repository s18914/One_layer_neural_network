import javax.swing.*;
import java.awt.event.*;
public class GUI implements ActionListener{
    JLabel l1,l2;
    JTextArea area;
    JButton b;
    GUI() {
        JFrame f= new JFrame();
        l1=new JLabel();
        l1.setBounds(50,25,100,30);
        l2=new JLabel();
        l2.setBounds(160,25,100,30);
        area=new JTextArea();
        area.setBounds(20,75,250,200);
        b=new JButton("Check language");
        b.setBounds(100,300,120,30);
        b.addActionListener(this);
        f.add(l1);f.add(l2);f.add(area);f.add(b);
        f.setSize(450,450);
        f.setLayout(null);
        f.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        FileService fs = new FileService();
        String text=fs.findLang(area.getText());
        l1.setText("Jezyk: "+text);
    }

}  