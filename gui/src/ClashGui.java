import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class ClashGui {
    private JPanel topLayer;
    private JPanel admin;
    private JPanel display;
    private JButton adButton;
    private JButton usButton;
    private JComboBox adProgramme;
    private JComboBox adYear;
    private JComboBox adTerm;
    private JTextField adModuleText;
    private JLabel adCompulsoryTitle;
    private JLabel adAcitivityTitle;
    private JComboBox adActivity;
    private JLabel adTimeTitle;
    private JComboBox adTime;
    private JLabel timeStart;
    private JLabel endTime;
    private JRadioButton yesRadioButton;
    private JRadioButton noRadioButton;
    private JLabel adDayTitle;
    private JComboBox comboBox1;
    private JLabel adCrashTitle;
    private JComboBox adCrash;
    private JButton adAddButton;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JLabel adModule;

    private CardLayout cl = (CardLayout)(topLayer.getLayout());


    public ClashGui(){

        //home panel actionListeners
        /*
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
        */

        //Admin panel actionListener
        adProgramme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //calls funciton form controller detectClash()
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

