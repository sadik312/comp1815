import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
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
    private JComboBox adDay;
    private JLabel adCrashTitle;
    private JComboBox adCrash;
    public JButton adAddButton;
    private JComboBox adEndTime1;
    private JComboBox adEndTime2;
    private JLabel adModule;

    private CardLayout cl = (CardLayout)(topLayer.getLayout());


    public String getAdCrash(){
        return (String) adCrash.getSelectedItem();
    }

    public String getAdProgramme(){
        return (String) adProgramme.getSelectedItem();
    }

    public String getAdYear(){
        return (String) adYear.getSelectedItem();
    }

    public String getAdTerm(){
        return (String) adTerm.getSelectedItem();
    }

    public String getAdModuleText(){
        return (String) adModuleText.getText();
    }

    public String getAdActivity(){
        return (String) adActivity.getSelectedItem();
    }

    public String getAdDay(){
        return (String) adDay.getSelectedItem();
    }

    public String getAdTime(){
        return (String) adTime.getSelectedItem();
    }

    public String getAdEndTime(){
        return (String) adEndTime1.getSelectedItem() + ":" + (String) adEndTime2.getSelectedItem();
    }

    public String getComRadioButton(){
        //ButtonGroup bg = new ButtonGroup();

        //bg.add(yesRadioButton);
        //bg.add(noRadioButton);

        String radioText = "";

        if (yesRadioButton.isSelected()){
            radioText = yesRadioButton.getText();
        }
        else if (noRadioButton.isSelected()){
            radioText = noRadioButton.getText();
        } else{
            radioText = noRadioButton.getText();
        }

        return(radioText);

    }

    public void adAddButtonListener(ActionListener listenForButton){
        adAddButton.addActionListener(listenForButton);
    }




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

        adAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//                Controller controllerObj = new Controller();
//                controllerObj.detectClash();
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



    //display page





}

