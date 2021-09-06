import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class Project extends JFrame implements ActionListener {

JMenuBar mb; 
JMenu master,user,report;
JMenuItem m1,m2,m3,m4,u1,u2,u3,r1;

  
void pro()
{

	//main menu mb
	//1st master->m1 m2
	//2nd user->u1,u2,u3
	//cuz this is  project variables will be declared on fly 
	
	mb  = new JMenuBar();  
        
	//1st
	 master = new JMenu("Master");
	master.setMnemonic('M');
	master.setForeground(Color.BLUE);

        m1 = new JMenuItem("New Customer");
	m1.addActionListener(this);
	m3 = new JMenuItem("Update Customer");
	m3.addActionListener(this);
	m4 = new JMenuItem("Delete Customer");
	m4.addActionListener(this);
        m2 = new JMenuItem("Customer Details");
	m2.addActionListener(this);
	master.add(m1);
	master.add(m3);
	 master.add(m4);
        master.add(m2);
	
        
	mb.add(master);
	
	//2nd
	
	user = new JMenu("User");
	user.setMnemonic('U');
	user.setForeground(Color.RED);
        
	 u1 = new JMenuItem("Pay Bill");
	 u1.addActionListener(this);
         u2 = new JMenuItem("Calculate Bill");
	 u2.addActionListener(this);
         u3 = new JMenuItem("Last Bill");
	 u3.addActionListener(this);
	user.add(u1);
	user.add(u2);
	user.add(u3);

	mb.add(user);

	//3rd
	 report = new JMenu("Report");
	report.setMnemonic('R');
	report.setForeground(Color.BLUE);
        
	r1 = new JMenuItem("Generate Bill");
       	r1.addActionListener(this);
	report.add(r1);

	mb.add(report);

	setJMenuBar(mb);

	
//--------------------------------------------

}

public void actionPerformed(ActionEvent ae)
{
	String msg=ae.getActionCommand();
	
	 if(msg.equals("Customer Details"))
	{
            new customer_details().setVisible(true);
	}
	if(msg.equals("New Customer"))
	{
            new new_customer().setVisible(true);
        }
	if(msg.equals("Update Customer"))
	{
            new update_customer().setVisible(true);
        }
	if(msg.equals("Delete Customer"))
	{
            new delete_customer().setVisible(true);
        }
	if(msg.equals("Calculate Bill"))
	{
            new calculate_bill().setVisible(true);
        }
	if(msg.equals("Pay Bill"))
	{
           new pay_bill().setVisible(true); //doesnt work
	}
	if(msg.equals("Last Bill"))
	{
           new last_bill().setVisible(true); //doesnt work
	}
	if(msg.equals("Generate Bill"))
	{
            new generate_bill().setVisible(true);
        }
}    
	



public Project()
    {
       	super("Electricity Billing System");
        pro();      
        setSize(500,500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


	


public static void main(String[] args)
{
        new Project();
}

}