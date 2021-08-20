import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class profile extends JPanel{

    Container co;
    bookingBeans ob= new bookingBeans();

    profile(bookingBeans obj) throws Exception
    {
        bookingHistory history = new bookingHistory(obj);

        JPanel container = new JPanel();
        container.setLayout(new CardLayout());
        
        JPanel slot = new JPanel();
        slot.setLayout(new BorderLayout());

        JPanel profileHeader = new JPanel();
        profileHeader.setLayout(new BorderLayout());

        JButton profileBackButton = new JButton("Back to Profile");
        profileBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                slot.removeAll();
                slot.repaint();
                profile p2 = null;
                try {
                    p2 = new profile(obj);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                slot.add(p2);
                slot.revalidate();
            }
        });
        JLabel profileTitle = new JLabel("Profile");

        JPanel profileInfoPanel = new JPanel();
        profileInfoPanel.setLayout(new BorderLayout());

        profileHeader.add(profileBackButton,BorderLayout.WEST);
        profileHeader.add(profileTitle,BorderLayout.CENTER);

        CardLayout cardLayout = new CardLayout();

        

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(2,1));
        headerPanel.setVisible(true);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(3,1));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,2));
        buttonPanel.setVisible(true);

        Font proFont = new Font(null).deriveFont(20.0f);
        Font headerFont = new Font(null).deriveFont(31.0f);

        JLabel header=new JLabel("MY PROFILE", JLabel.CENTER);
        header.setFont(headerFont);
        //header.setBounds(160,10,400,100);
        headerPanel.add(header);
        headerPanel.add(buttonPanel);
        String uname,fname,em,pwd;
        String userpass="Krasinski";
        add(headerPanel);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "safwan");
            String query = "select * from users where username = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userpass);
            ResultSet rst = pstmt.executeQuery();
            System.out.println("Connection successful");
            while (rst.next())
            {
                if(rst.getString("username").equals(userpass))
                {
                    uname=rst.getString("username");
                    fname=rst.getString("fullname");
                    em=rst.getString("email");
                    pwd=rst.getString("password");


                    JLabel Username=new JLabel("Username:"+uname);
                    JLabel Fullname=new JLabel("Full Name:"+fname);
                    JLabel Email=new JLabel("Email:"+em);
                    JLabel Password=new JLabel("Password:"+pwd);


                    //Username.setBounds(50, 150, 200, 50);
                    Username.setFont(proFont);
                   //Fullname.setBounds(50, 200, 400, 50);
                    Fullname.setFont(proFont);
                    //Email.setBounds(50, 250, 500, 50);
                    Email.setFont(proFont);
                    //Password.setBounds(50, 300, 300, 50);
                    Password.setFont(proFont);
                    System.out.println(Username.getText());

                    contentPanel.add(Username);
                    contentPanel.add(Fullname);
                    contentPanel.add(Email);

                }
                else
                {
                    System.out.println("Error");
                }
            }


            profileInfoPanel.add(headerPanel,BorderLayout.NORTH);
            profileInfoPanel.add(contentPanel,BorderLayout.CENTER);

            JButton bookHist=new JButton("Booking History");
            //bookHist.setBounds(50,450,200,50);
            bookHist.setFont(proFont);
            buttonPanel.add(bookHist);

            bookHist.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) {
                    container.removeAll();
                    container.repaint();
                    container.add(history);
                    container.revalidate();
                    //cardLayout.show(container,"Booking History");
                }
            });

            JButton Edit=new JButton("Edit Profile");
            //Edit.setBounds(250,450,200,50);
            Edit.setFont(proFont);
            buttonPanel.add(Edit);

            Edit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

            setLayout(new GridLayout(1,1));
            setVisible(true);

            container.add(profileInfoPanel,"Profile");
            container.add(history,"Booking History");

            slot.add(profileHeader,BorderLayout.NORTH);
            slot.add(container,BorderLayout.CENTER);
            add(slot);

            setSize(550, 650);

        }
        catch(Exception e)
        {
            System.out.println("Error: "+e);
        }
    }
}
