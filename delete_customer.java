import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.io.*;

public class delete_customer extends JFrame implements ActionListener
{
	JPanel p;
	JTextField f;
	JLabel l;
	JButton b1;
	
	Connection conn;
	PreparedStatement pstmt;
	
	 String sqlDelete = "DELETE FROM cust WHERE meter_number=?";


	void createConn()
	{
		try
		{
		Class.forName("org.postgresql.Driver");
		conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","1234");
		pstmt=conn.prepareStatement(sqlDelete);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this,"Exception:"+ex.getMessage());
		}
	}

	
	void del()
	{
		p=new JPanel();
		l=new JLabel("User to delete");
		f=new JTextField(10);
		p.add(l);
		p.add(f);
		b1=new JButton("Delete");
		b1.addActionListener(this);
		p.add(b1);
		add(p,BorderLayout.CENTER);
		
	}


	public void actionPerformed(ActionEvent ae)
	{
		String msg=ae.getActionCommand();
		if(msg.equals("Delete"))
		{
			try
			{
				
				pstmt.setString(1,f.getText());
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(this,"Deleted Sucessfully");
			
           		}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this,"Some issue:"+ex.getMessage());
			}
            
		}
	}

	
	
   	delete_customer()
	{
		createConn();
		del();
		setSize(500,500);
        	setVisible(true);
		
	}


	public static void main(String[] args)
	{
		new delete_customer();
	}


	


}