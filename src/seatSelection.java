import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Arrays;
import javax.swing.*;
import java.util.*;
import java.util.List;

public class seatSelection extends JFrame{

    JPanel mother,seats;
    JPanel title;
    int seatCount;


    String[] bkd;
    int selectedCount = 0;
    String selectedSeats = "";
    String renderedSeats = "";
    seatSelection(){


        mother = new JPanel();
        mother.setLayout(new BorderLayout());

        seats = new JPanel();
        seats.setLayout(new GridLayout(5,10,5,5));

        title  = new JPanel();
        title.setLayout(new GridLayout(1,3));

        JLabel tit = new JLabel("Enter the number of viewers", SwingConstants.CENTER);
        tit.setFont(new Font(null).deriveFont(20.0f));

        JButton changeSeatSelection = new JButton("Change Seat Selection");

        JComboBox<Integer> tf = new JComboBox<>();
        tf.addItem(1);
        tf.addItem(2);
        tf.addItem(3);
        tf.addItem(4);
        tf.addItem(5);
        tf.addItem(6);
        tf.addItem(7);
        tf.addItem(8);
        tf.addItem(9);
        tf.addItem(10);

        tf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seatCount = (int)tf.getSelectedItem();

                seats.setVisible(true);
                changeSeatSelection.setEnabled(true);
                tf.setEnabled(false);
            }
        });


        changeSeatSelection.setEnabled(false);
        changeSeatSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf.setEnabled(true);
                seats.setVisible(false);
                changeSeatSelection.setEnabled(false);
                seats.removeAll();
                selectedCount = 0;
                selectedSeats = "";
                renderSeats(getSeats());
            }
        });

        JButton bookTheSeats = new JButton("Book the Seats");
        bookTheSeats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","safwan");
                    String joinedSeats = getSeats()+selectedSeats;
                    String query = "update "+"scre1"+" set "+"stat1"+" = ? where day = ?";
                    PreparedStatement pstmt = con.prepareStatement(query);
                    pstmt.setString(1,joinedSeats);
                    pstmt.setString(2,"Day1");

                    pstmt.executeUpdate();
                    System.out.println(joinedSeats);
                    con.setAutoCommit(true);
                    con.close();
                }
                catch(Exception er){
                    System.out.println("ERROR AT UPDATE CONNECTION"+er);
                }

            }
        });

        title.add(tit);
        title.add(tf);
        title.add(changeSeatSelection);
        title.add(bookTheSeats);
        title.setPreferredSize(new Dimension(500,200));

        mother.add(title,BorderLayout.NORTH);
        title.setVisible(true);
        seats.setVisible(false);
        mother.add(seats,BorderLayout.CENTER);
        mother.setVisible(true);

        add(mother);
        setSize(500,500);
        setVisible(true);
        System.out.println("HI");
        renderSeats(getSeats());
    }
    public String getSeats(){
        String booked = "";
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","safwan");

            String query = "select "+"stat1"+" from "+"scre1"+" where day = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            //pstmt.setString(1,"stat1");
            //pstmt.setString(2,"scre1");
            pstmt.setString(1,"Day1");
            //Statement stmt = conn.createStatement();
            ResultSet rst = pstmt.executeQuery();
            while(rst.next()){
                booked = rst.getString("STAT1");
            }
            System.out.println("Dates Acquired");
            conn.setAutoCommit(true);
            conn.close();
        }
        catch (Exception e){
            System.out.println("ERROR AT CONNECTION "+e);
        }
        return booked;
    }
    public void renderSeats(String booked){
        int index = 0;
        bkd = booked.split("/");
        List<String> strList = new ArrayList<>(Arrays.asList(bkd));
        System.out.println(Arrays.toString(bkd));
        for(int i = 1; i <= 50; i++){
            if(strList.contains(String.valueOf(i))){
                Button but = new Button(String.valueOf(i));
                but.setEnabled(false);
                but.setBackground(Color.RED);
                seats.add(but);
                index += 1;
            }
            else{
                Button but2 = new Button(String.valueOf(i));
                but2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(selectedCount < seatCount) {
                            selectedSeats += "/"+but2.getLabel();
                            but2.setBackground(Color.GREEN);
                            selectedCount += 1;
                        }
                    }
                });
                seats.add(but2);
            }
        }
    }
    public static void main(String[] args) {
        new seatSelection();
    }
}
