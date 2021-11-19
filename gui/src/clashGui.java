import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class clashGui {
    private JPanel topLayer;
    private JPanel home;
    private JPanel admin;
    private JPanel user;
    private JPanel display;
    private JButton adButton;
    private JButton usButton;
    private JComboBox adProgramme;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;

    private CardLayout cl = (CardLayout)(topLayer.getLayout());


    public clashGui(){

        //home panel actionListeners
        adButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(topLayer, "admin");
            }
        });


        usButton.addActionListener(new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(topLayer, "user");
            }
        });


        //Admin panel actionListener
        adProgramme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(getAdProg());
            }
        });
    }

    public void start(){

       JFrame frame = new JFrame("Clash detection");


       frame.setSize(720, 576);
       frame.setContentPane(this.topLayer);
       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       frame.setVisible(true);
   }


   // Admin page
    public String getAdProg(){
        return adProgramme.getSelectedItem().toString();
    }



    //user page


    //display page





}
