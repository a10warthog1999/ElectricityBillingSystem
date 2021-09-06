import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class generate_bill extends JFrame implements ActionListener
{
	JPanel p1,p2,p3;
	JLabel l1;
	JTextField t1;
	JTextArea jt1;
	JButton b1;
	Connection conn;
	Statement stmt;
	ResultSet rs=null;
	
	
	void createConn1()
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

	void gb()
	{
		p1=new JPanel();
		l1=new JLabel("Meter Number");
		p1.add(l1);
		t1=new JTextField(20);
		p1.add(t1);
		add(p1,BorderLayout.NORTH);
		
		
		p2=new JPanel();
		jt1=new JTextArea(30,30);
		JScrollPane j=new JScrollPane(jt1);
		p2.add(j);  
		add(p2,BorderLayout.CENTER);

		
		p3=new JPanel();
		b1=new JButton("Generate bill");
		p3.add(b1);
		b1.addActionListener(this);
		add(p3,BorderLayout.SOUTH);
			
		
			
	}

	public void actionPerformed(ActionEvent ae)
	{
		String msg=ae.getActionCommand();
		if(msg.equals("Generate bill"))
		{
			try
			{
				stmt=conn.createStatement();
				
				stmt.execute("select * from cust where meter_number='"+t1.getText()+"'");
				rs=stmt.getResultSet();
                		while(rs.next()) 
				{
            				jt1.append("\n    Customer Name:"+rs.getString("name"));
                			jt1.append("\n    Meter Number:  "+rs.getString("meter_number"));
                			jt1.append("\n    Address:            "+rs.getString("address"));
                			jt1.append("\n    State:                 "+rs.getString("state"));
                			jt1.append("\n    City:                   "+rs.getString("city"));
                			jt1.append("\n    Email:                "+rs.getString("email"));
                			jt1.append("\n    Phone Number  "+rs.getString("phone"));
                			jt1.append("\n-------------------------------------------------------------");
                			jt1.append("\n");
                		}
                		stmt.close(); 	
				
				stmt=conn.createStatement();
				stmt.execute("select * from bill where meter_number='"+t1.getText()+"'");
				rs=stmt.getResultSet();
                		while(rs.next()) 
				{
                			jt1.append("\n    Meter Number: "+rs.getString("meter_number"));
                			jt1.append("\n    Uniits:                "+rs.getInt("units"));
                			jt1.append("\n    For the Month:  "+rs.getString("month"));
					jt1.append("\n    Amount:(Rs)     "+rs.getInt("amount"));
					
				}
				
                		stmt.close(); 	
				
						
           		}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this,"Some issue:"+ex.getMessage());
			}
            
		}
		
	}



	generate_bill()
	{
		createConn1();
		gb();
		setSize(500,500);
        	setVisible(true);
		

	}


	public static void main(String[] args)
	{
		new generate_bill();
	}

	
}


//java  -cp ".;c:\tools\postgresql-42.2.18.jar" Project