import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class homepage extends JFrame
{
    JPanel mother,curPanel,upcPanel,parent,home,nav;
    bookingBeans ob = new bookingBeans();
    JLabel current, upcoming,txt1;
    movieBookingPage moviebkPage;

    homepage() throws Exception {


        Font bookingFont = new Font(null).deriveFont(20.0f);


        ob.setCustomerName("John");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        current = new JLabel("Now Playing",JLabel.CENTER);
        current.setForeground(Color.YELLOW);
        upcoming = new JLabel("Upcoming Movies",JLabel.CENTER);
        upcoming.setForeground(Color.YELLOW);

        CardLayout cardLayout = new CardLayout();

        home = new JPanel();
        home.setLayout(new BorderLayout());

        nav = new JPanel();
        nav.setLayout(new BorderLayout());

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(parent,"Home"));

        txt1=new JLabel("SK Cinemas",JLabel.CENTER);
        txt1.setFont(bookingFont);
        txt1.setForeground(Color.YELLOW);


        JButton profileButton = new JButton("Profile");
        profileButton.addActionListener(e -> cardLayout.show(parent,"Profile"));

        nav.add(backButton,BorderLayout.WEST);
        nav.add(txt1,BorderLayout.CENTER);
        nav.add(profileButton,BorderLayout.EAST);
        nav.setPreferredSize(new Dimension(50,50));

        nav.setBackground(Color.BLACK);

        home.add(nav,BorderLayout.NORTH);

        parent = new JPanel();
        parent.setLayout(cardLayout);

        mother = new JPanel();
        mother.setLayout(new GridLayout(2,1,5,10));

        curPanel = new JPanel();
        curPanel.setLayout(new GridLayout(1,4,5,5));

        upcPanel = new JPanel();
        upcPanel.setLayout(new GridLayout(1,4,5,5));

        current.setFont(bookingFont);
        upcoming.setFont(bookingFont);

        //List and Hashmap for Now playing movies
        HashMap<String,String> nowPlayingMap = new HashMap<>();
        List<String> nowPlayingMovies = new ArrayList<>();
        //List and Hashmap for Upcoming movies
        HashMap<String,String> upComingMap = new HashMap<>();
        List<String> upComingMovies = new ArrayList<>();
        ///////////////////////////////////////////////////////////////////////////////////////////
        //DB Connection
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","safwan");

            String query = "select * from movie where nowplaying = 'Y'";
            String query2 = "select * from movie where nowplaying = 'N'";

            Statement stmt = conn.createStatement();

            ResultSet rst1 = stmt.executeQuery(query);
            while(rst1.next()){
                nowPlayingMovies.add(rst1.getString("moviename"));
                nowPlayingMap.put(rst1.getString("moviename"),rst1.getString("imgPath"));
            }

            ResultSet rst2 = stmt.executeQuery(query2);
            while(rst2.next()){
                upComingMovies.add(rst2.getString("moviename"));
                upComingMap.put(rst2.getString("moviename"),rst2.getString("imgPath"));
            }
            System.out.println(nowPlayingMap);
            System.out.println(upComingMap);
            conn.close();
        }
        catch(Exception e){
            System.out.println("Error at homepage: "+e);
        }

        ///////////////////////////////////////////////////////////////////////////////////////////
        //Now Playing Movies
        //////////////////////////////////////////////////////////////////////////////////////////

        curPanel.add(current);
        for (String nowPlayingMovie : nowPlayingMovies) {
            JButton nowButton = new JButton();
            nowButton.setLayout(new BorderLayout());

            JLabel nowLabel = new JLabel(nowPlayingMovie, JLabel.CENTER);
            nowLabel.setFont(bookingFont);


            nowButton.add(nowLabel, BorderLayout.NORTH);

            JPanel imgPanel = new JPanel();
            imgPanel.setBackground(Color.BLACK);
            imgPanel.setLayout(new GridLayout(1, 1));
            JLabel imgLbl = new JLabel(new ImageIcon(nowPlayingMap.get(nowPlayingMovie)));
            imgPanel.add(imgLbl);

            nowButton.add(imgPanel, BorderLayout.CENTER);

            nowButton.addActionListener(e -> {
                System.out.println(nowLabel.getText());
                try {
                    moviebkPage = new movieBookingPage(nowPlayingMap.get(nowLabel.getText()), nowLabel.getText());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                parent.add(moviebkPage, nowLabel.getText());
                cardLayout.show(parent, nowLabel.getText());
            });

            curPanel.add(nowButton);
        }
        ///////////////////////////////////////////////////////////////////////////////////////////
        //Upcoming Movies
        //////////////////////////////////////////////////////////////////////////////////////////
        upcPanel.add(upcoming);
        for (String upComingMovie : upComingMovies) {
            JButton upcButton = new JButton();
            upcButton.setLayout(new BorderLayout());

            JLabel upcLabel = new JLabel(upComingMovie, JLabel.CENTER);
            upcLabel.setFont(bookingFont);


            upcButton.add(upcLabel, BorderLayout.NORTH);

            JPanel imgPanel = new JPanel();
            imgPanel.setLayout(new GridLayout(1, 1));
            imgPanel.setBackground(Color.BLACK);
            JLabel imgLbl = new JLabel(new ImageIcon(upComingMap.get(upComingMovie)));
            imgPanel.add(imgLbl);

            upcButton.add(imgPanel, BorderLayout.CENTER);

            upcButton.addActionListener(e -> {
                try {
                    moviebkPage = new movieBookingPage(upComingMap.get(upcLabel.getText()), upcLabel.getText());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                parent.add(moviebkPage, upcLabel.getText());
                cardLayout.show(parent, upcLabel.getText());
            });

            upcPanel.add(upcButton);
        }

        profile pro = new profile(ob);

        parent.add(mother,"Home");
        parent.add(pro,"Profile");

        mother.add(curPanel);
        mother.add(upcPanel);

        curPanel.setVisible(true);
        upcPanel.setVisible(true);
        setSize(750, 730);
        setVisible(true);
        mother.setVisible(true);
        parent.setVisible(true);
        home.setVisible(true);
        home.add(parent,BorderLayout.CENTER);
        add(home);
        curPanel.setBackground(Color.BLACK);
        upcPanel.setBackground(Color.BLACK);
    }

    public static void main(String[] args) throws Exception {
        new homepage();
    }
}

