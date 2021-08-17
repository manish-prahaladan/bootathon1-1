package moviebook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;


public class homepage extends JFrame
{
    JPanel mother,curPanel,upcPanel,header;
    bookingBeans ob = new bookingBeans();

    homepage() {
        JButton editprofile,cmovie1,cmovie2,cmovie3,upmovie1,upmovie2,upmovie3;
        JLabel current, upcoming,txt1;
        Font bookingFont = new Font(null).deriveFont(20.0f);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        current = new JLabel("Current Movies");
        upcoming = new JLabel("Upcoming Movies");

        mother = new JPanel();
        mother.setLayout(new GridLayout(3,1,5,10));

        header=new JPanel();
        header.setLayout(new GridLayout(2,3,5,5));

        curPanel = new JPanel();
        curPanel.setLayout(new GridLayout(1,4,5,5));

        upcPanel = new JPanel();
        upcPanel.setLayout(new GridLayout(1,4,5,5));

        cmovie1=new JButton();
        cmovie1.setIcon(new ImageIcon("bootathon1-main/src/assets/btmn.jpg"));
        cmovie1.setSize(300,600);
        cmovie2=new JButton();
        cmovie2.setIcon(new ImageIcon("bootathon1-main/src/assets/btmn2.jpg"));
        cmovie2.setSize(300,300);
        cmovie3=new JButton();
        cmovie3.setIcon(new ImageIcon("bootathon1-main/src/assets/btmn3.jpg"));
        cmovie3.setSize(300,300);

        upmovie1=new JButton();
        upmovie1.setIcon(new ImageIcon("bootathon1-main/src/assets/tenet.jpg"));
        upmovie1.setSize(300,300);
        upmovie2=new JButton();
        upmovie2.setIcon(new ImageIcon("bootathon1-main/src/assets/dunkirk.jpg"));
        upmovie2.setSize(300,300);
        upmovie3=new JButton();
        upmovie3.setIcon(new ImageIcon("bootathon1-main/src/assets/interstellar.jpg"));
        upmovie3.setSize(300,300);

        current.setFont(bookingFont);
        upcoming.setFont(bookingFont);

        txt1=new JLabel("Welcome to SK Cinemas! ");
        txt1.setFont(bookingFont);

        JLabel txt2= new JLabel("");
        txt2.setFont(bookingFont);

        JLabel txt3= new JLabel("Application made by SKCET students");

        editprofile = new JButton("Edit Profile");
        editprofile.setFont(bookingFont);

        JButton feedback=new JButton("Feedback");
        feedback.setFont(bookingFont);

        JButton bookhist=new JButton("Booking History");
        bookhist.setFont(bookingFont);


//        editprofile.setBounds(50, 50, 200, 50);

        cmovie1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ob.setMovieInfo("Batman Begins");
                movieBookingPage obj = null;
                try
                {
                    obj = new movieBookingPage(ob);
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
//                mother.setVisible(false);
                obj.mother.setVisible(true);
            }
        });

        cmovie2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ob.setMovieInfo("The Dark Knight");
                movieBookingPage obj = null;
                try
                {
                    obj = new movieBookingPage(ob);
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
//                mother.setVisible(false);
                obj.mother.setVisible(true);
            }
        });

        cmovie3.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ob.setMovieInfo("The Dark Knight Rises");
                movieBookingPage obj = null;
                try
                {
                    obj = new movieBookingPage(ob);
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
//                mother.setVisible(false);
                obj.mother.setVisible(true);
            }
        });

        //        editprofile.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
        header.add(txt1);
        header.add(txt2);
        header.add(txt3);
       header.add(editprofile);
        header.add(bookhist);
        header.add(feedback);

        curPanel.add(current);
        curPanel.add(cmovie1);
        curPanel.add(cmovie2);
        curPanel.add(cmovie3);

        upcPanel.add(upcoming);
        upcPanel.add(upmovie1);
        upcPanel.add(upmovie2);
        upcPanel.add(upmovie3);

        mother.add(header);
        mother.add(curPanel);
        mother.add(upcPanel);

        curPanel.setVisible(true);
        upcPanel.setVisible(true);
        header.setVisible(true);
        setSize(750, 730);
        setVisible(true);
        mother.setVisible(true);
        add(mother);
    }

    public static void main(String[] args)
    {
        new homepage();
    }
}

