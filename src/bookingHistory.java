import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class bookingHistory extends JPanel{
    JPanel mother;
    bookingHistory(bookingBeans obj) {
        mother = new JPanel();
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","safwan");
            String query = "select * from bookingHistory where username = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,obj.getCustomerName());
            ResultSet rst = pstmt.executeQuery();
            List<ResultSet> resultSetList = new ArrayList<>();
            List<JButton> tickets = new ArrayList<>();
            while(rst.next()){
                JLabel username = new JLabel("Username: "+rst.getString("username"));
                JLabel movie = new JLabel("Movie: "+rst.getString("movie"));
                JLabel screen = new JLabel("Screen: "+rst.getString("screen"));
                JLabel seats = new JLabel("Seats: "+rst.getString("seats"));
                JLabel date = new JLabel("Date: "+rst.getString("dateinfo"));
                JLabel time = new JLabel("Time: "+rst.getString("time"));
                JLabel amount = new JLabel("Amount: "+rst.getString("amount"));

                JPanel dummy = new JPanel();
                dummy.setVisible(true);
                dummy.setLayout(new GridLayout(3,3));

                dummy.add(username);
                dummy.add(movie);
                dummy.add(screen);
                dummy.add(seats);
                dummy.add(date);
                dummy.add(time);
                dummy.add(amount);
                JButton ticketButton = new JButton();
                ticketButton.add(dummy);
                tickets.add(ticketButton);
            }

            /*for(int i = 0; i < resultSetList.size(); i++){

                JLabel username = new JLabel("Username: "+resultSetList.get(i).getString("username"));
                JLabel movie = new JLabel("Movie: "+resultSetList.get(i).getString("movie"));
                JLabel screen = new JLabel("Screen: "+resultSetList.get(i).getString("screen"));
                JLabel seats = new JLabel("Seats: "+resultSetList.get(i).getString("seats"));
                JLabel date = new JLabel("Date: "+resultSetList.get(i).getString("dateinfo"));
                JLabel time = new JLabel("Time: "+resultSetList.get(i).getString("time"));
                JLabel amount = new JLabel("Amount: "+resultSetList.get(i).getString("amount"));

                JPanel dummy = new JPanel();
                dummy.setVisible(true);
                dummy.setLayout(new GridLayout(3,3));

                dummy.add(username);
                dummy.add(movie);
                dummy.add(screen);
                dummy.add(seats);
                dummy.add(date);
                dummy.add(time);
                dummy.add(amount);

                tickets.add(dummy);
            }*/

            mother.setLayout(new GridLayout(tickets.size(),1,10,10));
            for(int i = 0; i < tickets.size(); i++){
                mother.add(tickets.get(i));
            }

            mother.setVisible(true);
            add(mother);
            setSize(750,750);
            //setVisible(true);
        }
        catch(Exception e){
            System.out.println("Error at bookingHistory: "+e);
        }
    }
}
