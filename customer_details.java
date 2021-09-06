import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.io.*;

public class customer_details extends JFrame implements ActionListener
{
	Connection conn;
	Statement stmt;
	ResultSet rs=null;
	JTextArea txtResult;
	JPanel p,o;
	JButton b;
		
	void createConn()
	{
		try
		{
			Class.forName("org.postgresql.Driver");
			conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","1234");
			
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this,"Exception:"+ex.getMessage());
		}
	}


	void custdetail()
	{
		p=new JPanel();
		txtResult=new JTextArea(30,30);
        	p.add(txtResult);
		o=new JPanel();
		b=new JButton("Show");
		b.addActionListener(this);
		o.add(b);
		add(p,BorderLayout.CENTER);
		add(o,BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent ae)
	{
		String msg=ae.getActionCommand();
		if(msg.equals("Show"))
		{
			try
			{
				stmt=conn.createStatement();
				stmt.execute("select * from cust");
				rs=stmt.getResultSet();
                		while(rs.next()) 
				{
                  			  txtResult.append(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6)+"\t"+rs.getString(7)+"\n");
                		}
                		stmt.close(); 			
           		}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this,"Some issue:"+ex.getMessage());
			}
            
		}
	}


	customer_details()
	{
		createConn();
		custdetail();      
        	setSize(500,500);
        	setVisible(true);
        	
	}

	public static void main(String[] args)
	{
		new customer_details();
	}

}

//java  -cp ".;c:\tools\postgresql-42.2.18.jar" Project
