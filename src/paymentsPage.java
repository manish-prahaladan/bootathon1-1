import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/*Ticket
Name:
Movie:
Screen
Seat nos:
Date:
Time:
Amount:
 */

public class paymentsPage extends JPanel {
    JPanel ticket = new JPanel();
    Font paymentFont = new Font(null).deriveFont(20.0f);
    paymentsPage(bookingBeans obj){
        ticket.setLayout(new GridLayout(8,1));

        JLabel name = new JLabel("Name: "+obj.getCustomerName());
        name.setFont(paymentFont);

        JLabel movie = new JLabel("Movie: "+obj.getMovieInfo());
        movie.setFont(paymentFont);

        String screen = obj.getScreenInfo();

        if(screen.equals("scre1")){
            screen = "Screen 1";
        }

        else if(screen.equals("scre2")){
            screen = "Screen 2";
        }

        else if(screen.equals("scre3")){
            screen = "Screen 3";
        }

        JLabel screenInfo = new JLabel(screen);
        screenInfo.setFont(paymentFont);

        String[] seats = obj.getSeatNumbers().split("/");
        String s = "";
        for(int i = 1; i < seats.length; i++){
            if(i == seats.length-1){
                s += seats[i];
            }

            else{
                s += seats[i]+", ";
            }

        }

        JLabel seatsBooked = new JLabel("Seats booked: "+s);
        seatsBooked.setFont(paymentFont);

        String date = obj.getDayInfo();
        if(date.equals("Day1")){
            date = "16/08/2021";
        }
        else if(date.equals("Day2")){
            date = "17/08/2021";
        }
        else{
            date = "18/08/2021";
        }

        JLabel  dateInfo = new JLabel("Date: "+date);
        dateInfo.setFont(paymentFont);

        String time = obj.getTimeInfo();
        if(time.equals("stat1")){
            time = "Morning: 9AM-12:00PM";
        }
        else if(time.equals("stat2")){
            time = "After noon: 2PM-5PM";
        }
        else if(time.equals("stat3")){
            time = "Night: 8PM-11PM";
        }

        JLabel timeInfo = new JLabel("Timing: "+time);
        timeInfo.setFont(paymentFont);

        String amt = String.valueOf((seats.length-1)*100);
        JLabel amount = new JLabel("Amount: Rs. "+amt);
        amount.setFont(paymentFont);

        JButton payButton = new JButton("Pay");
        String finalS = s;
        String finalDate = date;
        String finalTime = time;
        String finalScreen = screen;
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","safwan");
                    String query = "insert into bookingHistory values(?,?,?,?,?,?,?)";
                    PreparedStatement pstmt = conn.prepareStatement(query);
                    pstmt.setString(1,obj.getCustomerName());
                    pstmt.setString(2,obj.getMovieInfo());
                    pstmt.setString(3, finalScreen);
                    pstmt.setString(4, finalS);
                    pstmt.setString(5, finalDate);
                    pstmt.setString(6, finalTime);
                    pstmt.setString(7,amt);
                    pstmt.executeUpdate();
                    System.out.println("Booking history inserted");
                    String query2 = "update "+obj.getScreenInfo()+" set "+obj.getTimeInfo()+" = ? where day = ?";
                    PreparedStatement pstmt2 = conn.prepareStatement(query2);
                    pstmt2.setString(1,obj.getJoinedSeats());
                    pstmt2.setString(2, obj.getDayInfo());
                    pstmt2.executeUpdate();
                    System.out.println("Updating seats"+obj.getJoinedSeats());
                    JOptionPane.showMessageDialog(ticket,"Tickets have been booked, view profile for tickets");
                    payButton.setEnabled(false);
                    conn.setAutoCommit(true);
                    conn.close();
                }
                catch(Exception er){
                    System.out.println("Booking history table error: "+er);
                }
            }
        });

        ticket.add(name);
        ticket.add(movie);
        ticket.add(screenInfo);
        ticket.add(seatsBooked);
        ticket.add(dateInfo);
        ticket.add(timeInfo);
        ticket.add(amount);
        ticket.add(payButton);

        ticket.setVisible(true);
        add(ticket);
        setSize(750,750);
    }
}
