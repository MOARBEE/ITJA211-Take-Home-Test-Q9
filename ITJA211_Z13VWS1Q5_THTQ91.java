package itja211_z13vws1q5_thtq9.pkg1;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class ITJA211_Z13VWS1Q5_THTQ91 extends JFrame {
    String[] messageStrings = {"Level 1", "Level 2", "Level 3"};
    String msg;
    private JComboBox levelBox;
    private JFrame frame;
    private JLabel num;
    private JLabel name;
    private JLabel surname;
    private JLabel level;
    private JTextField numTextField;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JButton insert;
  

    public ITJA211_Z13VWS1Q5_THTQ91() {
        frame = new JFrame("Library");
        
        num = new JLabel("Number");
        
        name = new JLabel("Name");
        
        surname = new JLabel("surname");
        
        level = new JLabel("Level");
        
        numTextField = new JTextField(null,15);
        
        nameTextField = new JTextField(null,15);
        
        surnameTextField = new JTextField(null,15);
        
        levelBox = new JComboBox(messageStrings);
        levelBox.setSelectedIndex(1);
        thehandler handler = new thehandler();
        levelBox.addActionListener(handler);
        
        insert = new JButton("Insert");
        insert.addActionListener(handler);
    
        frame.setLayout(new GridLayout(5,2));
        frame.add(num);
        frame.add(numTextField);
        frame.add(name);
        frame.add(nameTextField);
        frame.add(surname);
        frame.add(surnameTextField);
        frame.add(level);
        frame.add(levelBox);
        frame.add(insert);
    }
    
    
    private class thehandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            String message = "";
            String inputNumber = numTextField.getText();
            String inputName = nameTextField.getText();
            String inputSurname = surnameTextField.getText();
            String newMessage;
            
            if(event.getSource() == levelBox){
                JComboBox cb = (JComboBox)event.getSource();
                msg = (String)cb.getSelectedItem();
                
            }
           
            
            
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Student","root","");
                System.out.println("Connection Successful");
                
                if(event.getSource().equals(insert)){
                    String sqlInsert = "insert into StudentDetails(stNumber,stName,stSurname,stLevel)values(?,?,?,?)";
                    PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
                    pstmt.setString(1, inputNumber);
                    pstmt.setString(2,inputName );
                    pstmt.setString(3, inputSurname);
                    pstmt.setString(4, msg);
                    pstmt.executeUpdate();
                    message = "Record was successfully stored!";
                    JOptionPane.showMessageDialog(null, message);
                }
            } 
            
            catch(SQLException ex){
                ex.printStackTrace();
            }
            
            
            catch (Exception e) {
                System.out.println("Connection Failed" + e);
            }
        }
    }
    
    
    
    
    public static void main(String[] args) {
        ITJA211_Z13VWS1Q5_THTQ91 app = new ITJA211_Z13VWS1Q5_THTQ91();
        app.frame.setSize(400,200);
        app.frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        app.frame.setVisible(true);
    }
    
}
