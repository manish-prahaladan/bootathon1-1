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

public class movieBookingPage extends JPanel {
    JPanel mother,movieInfo,dateInfo,timeInfo,screenInfo,home;

    movieBookingPage(String imgPath,String movieName) throws IOException {

        bookingBeans ob = new bookingBeans();
        ob.setMovieInfo(movieName);
        Font bookingFont = new Font(null).deriveFont(20.0f);

        home = new JPanel();
        home.setLayout(new BorderLayout());

        mother = new JPanel();
        mother.setLayout(new GridLayout(4,1,5,10));

        movieInfo = new JPanel();
        movieInfo.setLayout(new BorderLayout());

        dateInfo = new JPanel();
        dateInfo.setLayout(new GridLayout(1,5,5,5));

        screenInfo = new JPanel();
        screenInfo.setLayout(new GridLayout(1,5,5,5));

        timeInfo = new JPanel();
        timeInfo.setLayout(new GridLayout(3,1,5,5));

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
        JButton bookTickets = new JButton("Book Tickets");

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
                    obj.mother.setVisible(true);
                    home.removeAll();
                    home.repaint();
                    home.add(obj);
                    home.revalidate();
                    System.out.println("Ready to roll");
                }
            }
        });

        movieInfo.add(movieOneImg,BorderLayout.WEST);
        movieInfo.add(movieOneTitle,BorderLayout.CENTER);
        movieInfo.setPreferredSize(new Dimension(200,200));
        System.out.println(movieOneTitle.getText());

        dateInfo.add(dateLabel);
        dateInfo.add(day1);
        dateInfo.add(day2);
        dateInfo.add(day3);
        dateInfo.add(changeDay);

        screenInfo.add(screenLabel);
        screenInfo.add(screen1);
        screenInfo.add(screen2);
        screenInfo.add(screen3);
        screenInfo.add(changeScreen);

        timeInfo.add(timingSelection);
        timeInfo.add(changeTime);
        timeInfo.add(bookTickets);

        home.add(movieInfo,BorderLayout.NORTH);

        mother.add(dateInfo);
        mother.add(screenInfo);
        mother.add(timeInfo);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        home.add(mother,BorderLayout.CENTER);

        movieInfo.setVisible(true);
        dateInfo.setVisible(true);
        timeInfo.setVisible(true);
        mother.setVisible(true);
        //setSize(750,750);
        setLayout(new GridLayout(1,1));
        add(home);
    }
}
