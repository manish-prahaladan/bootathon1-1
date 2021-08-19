import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class profile extends JFrame{

    Container co;
    bookingBeans ob= new bookingBeans();
    profile(bookingBeans ob) throws Exception
    {

        co = getContentPane();
        co.setLayout(null);

        try {
            String uname,fname,em,pwd;
            String userpass="adamjohan";
            Font proFont = new Font(null).deriveFont(20.0f);
            Font headerFont = new Font(null).deriveFont(31.0f);
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "SYSTEM", "manu2000!");
            String query = "Select * from users where username = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userpass);
            ResultSet rst = pstmt.executeQuery();
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


                    Username.setBounds(50, 150, 200, 50);
                    Username.setFont(proFont);
                    Fullname.setBounds(50, 200, 400, 50);
                    Fullname.setFont(proFont);
                    Email.setBounds(50, 250, 500, 50);
                    Email.setFont(proFont);
                    Password.setBounds(50, 300, 300, 50);
                    Password.setFont(proFont);


                    add(Username);
                    add(Fullname);
                    add(Email);
                    add(Password);
                }
                else
                {
                    System.out.println("Error");
                }
            }


            JLabel header=new JLabel("MY PROFILE");
            header.setFont(headerFont);
            header.setBounds(160,10,400,100);
            add(header);


            JButton Back=new JButton("Go Back");
            Back.setBounds(50,450,200,50);
            Back.setFont(proFont);
            add(Back);

            Back.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

            JButton Edit=new JButton("Edit Profile");
            Edit.setBounds(250,450,200,50);
            Edit.setFont(proFont);
            add(Edit);

            Edit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });


            setVisible(true);
            setSize(550, 650);

        }
        catch(Exception e)
        {
            System.out.println("Error: "+e);
        }
    }
}
