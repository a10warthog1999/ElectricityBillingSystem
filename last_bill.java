import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.io.*;

public class last_bill extends JFrame implements ActionListener
{
	Connection conn;
	Statement stmt;
	ResultSet rs=null;
	JTextArea txtResult;
	JPanel p,o;
	JButton b,b1;

	
	PreparedStatement pstmt;
	
	 String sqlDeletei = "DELETE from bill";

		
	void createConn()
	{
		try
		{
			Class.forName("org.postgresql.Driver");
			conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","1234");
			pstmt=conn.prepareStatement(sqlDeletei);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this,"Exception:"+ex.getMessage());
		}
	}


	void last()
	{
		p=new JPanel();
		txtResult=new JTextArea(30,30);
        	p.add(txtResult);
		o=new JPanel();
		b=new JButton("Show");
		b.addActionListener(this);
		b1=new JButton("Clear for the month");
		b1.addActionListener(this);
		o.add(b);
		o.add(b1);
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
				stmt.execute("select * from bill");
				rs=stmt.getResultSet();
				
                		while(rs.next()) 
				{
                  			  txtResult.append(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\n");
                		}
                		stmt.close(); 			
           		}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this,"Some issue:"+ex.getMessage());
			}
            
		}

		if(msg.equals("Clear for the month"))
		{
			try
			{
				
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(this,"Cleared Sucessfully");
			
           		}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this,"Some issue:"+ex.getMessage());
			}
			
		}

		
	}


	last_bill()
	{
		createConn();
		last();    
        	setSize(500,500);
        	setVisible(true);
        	
	}

	public static void main(String[] args)
	{
		new last_bill(); 
	}

}

//java  -cp ".;c:\tools\postgresql-42.2.18.jar" Project
