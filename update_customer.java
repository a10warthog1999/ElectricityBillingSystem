import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class update_customer extends JFrame implements ActionListener
{
	JLabel l1,l2,l3,l4,l5,l6,l7,l8;
	JTextField t1,t2,t3,t4,t5,t6,t7;
	JButton b1,b2;
	
	Connection conn;
	PreparedStatement pstmt;
	
	 String sqlUpdate = "UPDATE cust "
                + "SET name = ? , address=? , state=? , city=? , email=? , phone=?"
                + "WHERE meter_number = ?";
	

void createConn()
	{
		try
		{
		Class.forName("org.postgresql.Driver");
		conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","1234");
		pstmt=conn.prepareStatement(sqlUpdate);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this,"Exception:"+ex.getMessage());
		}
	}

	update_customer() 
	{
	createConn();
        setLocation(350,200);
        setSize(650,600);
        
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(9,2,10,10));
        
        p.setBackground(Color.WHITE);
        
        l1 = new JLabel("Name");
        t1 = new JTextField(20);
        p.add(l1);
        p.add(t1);
        l2 = new JLabel("Meter No");
        t2 = new JTextField(10);
        p.add(l2);
        p.add(t2);
        l3 = new JLabel("Address");
        t3 = new JTextField(40);
        p.add(l3);
        p.add(t3);
        l4 = new JLabel("State");
        t4 = new JTextField(20);
        p.add(l4);
        p.add(t4);
        l5 = new JLabel("City");
        t5 = new JTextField(15);
        p.add(l5);
        p.add(t5);
        l6 = new JLabel("Email");
        t6 = new JTextField(30);
        p.add(l6);
        p.add(t6);
        l7 = new JLabel("Phone Number");
        t7 = new JTextField(15);
        p.add(l7);
        p.add(t7);
        
        b1 = new JButton("Submit");
        b2 = new JButton("Cancel");
        
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        
        p.add(b1);
        p.add(b2);
        setLayout(new BorderLayout());
        
        add(p,"Center");

	
	b1.addActionListener(this);
        b2.addActionListener(this);
	

	}

	public void actionPerformed(ActionEvent ae)
{
	String msg=ae.getActionCommand();
	
	 if(msg.equals("Submit"))
	{
		try
		{
		pstmt.setString(1,t1.getText());
		pstmt.setString(2,t3.getText());
		pstmt.setString(3,t4.getText());
		pstmt.setString(4,t5.getText());
		pstmt.setString(5,t6.getText());
		pstmt.setString(6,t7.getText());
		pstmt.setString(7,t2.getText());
		


		pstmt.executeUpdate();
		JOptionPane.showMessageDialog(this,"Updated Sucessfully");
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this,"Pls enter proper values:"+ex.getMessage());
		}
            
	}
	else
	{
            JOptionPane.showMessageDialog(this,"User will not be updated");
	}


}

public static void main(String[] args)
{
	new update_customer().setVisible(true);
}

}



//java  -cp ".;c:\tools\postgresql-42.2.18.jar" Project