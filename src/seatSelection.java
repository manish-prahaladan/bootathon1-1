import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Arrays;
import javax.swing.*;
import java.util.*;
import java.util.List;

public class seatSelection extends JPanel{

    JPanel mother,seats,header;
    JPanel title,side;
    int seatCount;


    String[] bkd;
    int selectedCount = 0;
    String selectedSeats = "";
    String renderedSeats = "";
    bookingBeans ob;
    JButton changeSeatSelection;
    seatSelection(bookingBeans obj){

        ob = obj;
        mother = new JPanel();
        mother.setLayout(new BorderLayout());

        header = new JPanel();
        header.setLayout(new BorderLayout());

        side = new JPanel();
        side.setLayout(new GridLayout(1,1));

        seats = new JPanel();
        seats.setLayout(new GridLayout(5,10,5,5));

        title  = new JPanel();
        title.setLayout(new GridLayout(1,3));

        JLabel tit = new JLabel("Enter the number of viewers", SwingConstants.CENTER);
        tit.setFont(new Font(null).deriveFont(20.0f));

        JButton changeSeatCount = new JButton("Change Seat Count");

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
                changeSeatCount.setEnabled(true);
                tf.setEnabled(false);
            }
        });


        changeSeatCount.setEnabled(false);
        changeSeatCount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf.setEnabled(true);
                seats.setVisible(false);
                changeSeatCount.setEnabled(false);
                seats.removeAll();
                selectedCount = 0;
                selectedSeats = "";
                renderSeats(getSeats());
            }
        });

        JLabel sideLabel = new JLabel("Screen this side",SwingConstants.CENTER);
        sideLabel.setFont(new Font(null).deriveFont(20.0f));
        side.add(sideLabel);

        JButton bookTheSeats = new JButton("Book the Seats");
        bookTheSeats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String joinedSeats = getSeats()+selectedSeats;
                    obj.setJoinedSeats(joinedSeats);
                    obj.setSeatNumbers(selectedSeats);
                    obj.setCustomerName("John");
                    removeAll();
                    repaint();
                    paymentsPage payP = new paymentsPage(obj);
                    payP.setVisible(true);
                    add(payP);
                    revalidate();
            }
        });

        changeSeatSelection = new JButton("Change Seat Selection");
        changeSeatSelection.setFont(new Font(null).deriveFont(20.0f));
        changeSeatSelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seats.removeAll();
                renderSeats(getSeats());
                seats.setVisible(true);
                changeSeatSelection.setEnabled(false);
                selectedCount = 0;
                selectedSeats ="";
            }
        });
        changeSeatSelection.setEnabled(false);
        title.add(tit);
        title.add(tf);
        title.add(changeSeatCount);
        title.add(bookTheSeats);
        title.setPreferredSize(new Dimension(500,200));
        header.add(title, BorderLayout.NORTH);
        header.add(side,BorderLayout.SOUTH);
        mother.add(header,BorderLayout.NORTH);
        title.setVisible(true);
        seats.setVisible(false);
        mother.add(seats,BorderLayout.CENTER);
        mother.add(changeSeatSelection,BorderLayout.SOUTH);
        mother.setVisible(true);

        add(mother);
        //setSize(750,750);
        setLayout(new GridLayout(1,1));
        setVisible(true);
        System.out.println("HI");
        renderSeats(getSeats());
    }


    public String getSeats(){
        String booked = "";
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","safwan");

            String query = "select "+ob.getTimeInfo()+" from "+ob.getScreenInfo()+" where day = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,ob.getDayInfo());
            ResultSet rst = pstmt.executeQuery();
            while(rst.next()){
                booked = rst.getString(ob.getTimeInfo());
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
                            if(selectedCount < 1){
                                changeSeatSelection.setEnabled(true);
                            }
                            selectedSeats += "/"+but2.getLabel();
                            but2.setBackground(Color.GREEN);
                            but2.setEnabled(false);
                            selectedCount += 1;
                        }
                    }
                });
                seats.add(but2);
            }
        }
    }
}
