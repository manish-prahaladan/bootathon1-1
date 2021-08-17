import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class movieBookingPage extends JFrame {
    JPanel mother,mov1,mov2,mov3,mov4;

    movieBookingPage(String imgPath,String movieName) throws IOException {

        bookingBeans ob = new bookingBeans();
        ob.setMovieInfo(movieName);
        Font bookingFont = new Font(null).deriveFont(20.0f);

        mother = new JPanel();
        mother.setLayout(new GridLayout(4,1,5,10));

        mov1 = new JPanel();
        mov1.setLayout(new GridLayout(1,2,5,5));

        mov2 = new JPanel();
        mov2.setLayout(new GridLayout(1,5,5,5));

        mov4 = new JPanel();
        mov4.setLayout(new GridLayout(1,5,5,5));

        mov3 = new JPanel();
        mov3.setLayout(new GridLayout(3,1,5,5));

        BufferedImage movieOne = ImageIO.read(new File(imgPath));
        JLabel movieOneImg = new JLabel(new ImageIcon(movieOne));
        JLabel movieOneTitle = new JLabel(movieName,SwingConstants.CENTER);
        movieOneTitle.setFont(new Font(null).deriveFont(20.0f));

        JLabel dateLabel = new JLabel("Select Date",SwingConstants.CENTER);
        dateLabel.setFont(bookingFont);
        JButton day1 = new JButton("16/08/2021");
        JButton day2 = new JButton("17/08/2021");
        JButton day3 = new JButton("18/08/2021");
        JButton changeDay = new JButton("Change Date");
        changeDay.setEnabled(false);
        changeDay.setFont(bookingFont);

        List<JButton> dayButtons = new ArrayList<>();
        dayButtons.add(day1);
        dayButtons.add(day2);
        dayButtons.add(day3);

        for (JButton dayButton : dayButtons) {
            dayButton.setFont(bookingFont);
        }

        day1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ob.setDayInfo("Day1");
                for (JButton dayButton : dayButtons) {
                    dayButton.setEnabled(false);
                }
                changeDay.setEnabled(true);
            }
        });

        day2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ob.setDayInfo("Day2");
                for (JButton dayButton : dayButtons) {
                    dayButton.setEnabled(false);
                }
                changeDay.setEnabled(true);
            }
        });

        day3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ob.setDayInfo("Day3");
                for (JButton dayButton : dayButtons) {
                    dayButton.setEnabled(false);
                }
                changeDay.setEnabled(true);
            }
        });

        changeDay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (JButton dayButton : dayButtons) {
                    dayButton.setEnabled(true);
                }
                changeDay.setEnabled(false);
            }
        });

        JLabel screenLabel = new JLabel("Select screen",SwingConstants.CENTER);
        screenLabel.setFont(bookingFont);
        JButton screen1 = new JButton("Screen1");
        JButton screen2 = new JButton("Screen2");
        JButton screen3 = new JButton("Screen3");
        JButton changeScreen = new JButton("Change Screen");
        changeScreen.setEnabled(false);
        changeScreen.setFont(bookingFont);

        List<JButton> screenList = new ArrayList<>();
        screenList.add(screen1);
        screenList.add(screen2);
        screenList.add(screen3);

        for(JButton screen:screenList){
            screen.setFont(bookingFont);
        }

        screen1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ob.setScreenInfo("scre1");
                for(JButton screen:screenList){
                    screen.setEnabled(false);
                }
                changeScreen.setEnabled(true);
            }
        });

        screen2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ob.setScreenInfo("scre2");
                for(JButton screen:screenList){
                    screen.setEnabled(false);
                }
                changeScreen.setEnabled(true);
            }
        });

        screen3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ob.setScreenInfo("scre3");
                for(JButton screen:screenList){
                    screen.setEnabled(false);
                }
                changeScreen.setEnabled(true);
            }
        });

        changeScreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(JButton screen:screenList){
                    screen.setEnabled(true);
                }
                changeScreen.setEnabled(false);
            }
        });

        JComboBox<String> timingSelection = new JComboBox<>();
        timingSelection.addItem("Morning: 9AM-12:00PM");
        timingSelection.addItem("After noon: 2PM-5PM");
        timingSelection.addItem("Night: 8PM-11PM");
        JButton changeTime = new JButton("Change Timing");
        changeTime.setEnabled(false);
        JButton bookTickets = new JButton("Book Ticlets");

        timingSelection.setFont(bookingFont);
        changeTime.setFont(bookingFont);;
        bookTickets.setFont(bookingFont);

        timingSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String timing = (String) timingSelection.getSelectedItem();
                if(timing.equals("Morning: 9AM-12:00PM")){
                    ob.setTimeInfo("stat1");
                }
               else if(timing.equals("After noon: 2PM-5PM")){
                   ob.setTimeInfo("stat2");
                }
               else if(timing.equals("Night: 8PM-11PM")){
                   ob.setTimeInfo("stat3");
                }
               timingSelection.setEnabled(false);
               changeTime.setEnabled(true);
            }
        });
        changeTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timingSelection.setEnabled(true);
                changeTime.setEnabled(false);
            }
        });

        bookTickets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((ob.getTimeInfo()==null)||(ob.getTimeInfo()==null)||(ob.getTimeInfo()==null)){
                    JOptionPane.showMessageDialog(mother,"Please select all the fields");
                }
                else{

                    seatSelection obj = new seatSelection(ob);
                    mother.setVisible(false);
                    obj.mother.setVisible(true);
                    System.out.println("Ready to roll");
                }
            }
        });

        mov1.add(movieOneImg);
        mov1.add(movieOneTitle);

        mov2.add(dateLabel);
        mov2.add(day1);
        mov2.add(day2);
        mov2.add(day3);
        mov2.add(changeDay);

        mov4.add(screenLabel);
        mov4.add(screen1);
        mov4.add(screen2);
        mov4.add(screen3);
        mov4.add(changeScreen);

        mov3.add(timingSelection);
        mov3.add(changeTime);
        mov3.add(bookTickets);

        mother.add(mov1);
        mother.add(mov2);
        mother.add(mov4);
        mother.add(mov3);

        mov1.setVisible(true);
        mov2.setVisible(true);
        mov3.setVisible(true);

        mother.setVisible(true);
        setSize(750,750);
        add(mother);
    }
}
