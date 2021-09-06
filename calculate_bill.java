import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.io.*;

public class calculate_bill extends JFrame implements ActionListener
{
   JLabel l1,l2,l3,l4;
    JTextField t1,t2;
    Choice c2;
    JButton b1,b2;
    JPanel p;
    Connection conn;
    PreparedStatement pstmt;
    String insSql="Insert into bill values(?,?,?,?)";


void createConn()
	{
		try
		{
		Class.forName("org.postgresql.Driver");
		conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","1234");
		pstmt=conn.prepareStatement(insSql);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this,"Exception:"+ex.getMessage());
		}
	}


	void calculate()
	{
		p = new JPanel();
        	p.setLayout(new GridLayout(4,2));

		l1 = new JLabel("Calculate Electricity Bill");
		add(l1,BorderLayout.NORTH);

       		
		l2 = new JLabel("Meter No");
		t1=new JTextField(10);
		p.add(l2);
		p.add(t1);

        	l3 = new JLabel("Units Cosumed");
		t2=new JTextField(10);
		p.add(l3);
		p.add(t2);
		
        	l4 = new JLabel("Month");
		 c2 = new Choice();
        	c2.add("January");
       		 c2.add("February");
        	c2.add("March");
        	c2.add("April");
        	c2.add("May");
        	c2.add("June");
        	c2.add("July");
        	c2.add("August");
        	c2.add("September");
       		 c2.add("October");
        	c2.add("November");
        	c2.add("December");
		p.add(l4);
		p.add(c2);


		b1 = new JButton("Submit");
		p.add(b1);
        	b2 = new JButton("Cancel");
        	p.add(b2);
		b1.addActionListener(this);
        	b2.addActionListener(this);
		
		add(p,BorderLayout.CENTER);
	
	}	


	calculate_bill()
	{
		createConn();
		calculate();
		setSize(200,200);
		setVisible(true);
		
	}

	public void actionPerformed(ActionEvent ae)
	{
		String msg=ae.getActionCommand();
	
	 	if(msg.equals("Submit"))
		{
		try
		{
		pstmt.setString(1,t1.getText());
		int units=Integer.parseInt(t2.getText());
		pstmt.setInt(2,units);
		pstmt.setString(3,c2.getSelectedItem());  
		int amount=units*5;
		pstmt.setInt(4,amount);
		

		pstmt.executeUpdate();
		JOptionPane.showMessageDialog(this,"User Charged Rs"+amount);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this,"Pls enter proper values:"+ex.getMessage());
		}
		}
		
		if(msg.equals("Cancel"))
		{
			JOptionPane.showMessageDialog(this,"Action canceled");
		}
	
	}

	public static void main(String[] args)
	{
		new calculate_bill();
	}		


}