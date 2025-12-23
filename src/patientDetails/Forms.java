package patientDetails;
import java.sql.*;
import javax.swing.*;
public class Forms {
	static int n=0;	
	public static int pid(Connection con) {
	    try {
	        Statement state = con.createStatement();
	        ResultSet rset = state.executeQuery("SELECT seq.nextval FROM dual");
	        rset.next();
	        return rset.getInt(1);
	    } catch(Exception ex) {
	        System.out.println("ERROR " + ex);
	    }
	    return 0;
	}
	static int nextPid;  
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		try {			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "pooja");
			con.setAutoCommit(true);
			nextPid = pid(con);

			JFrame f = new JFrame("Forms  :D");
			f.setSize(470, 300);
			f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			f.setLocationRelativeTo(null);
			f.setLayout(null);
			
			JLabel idLabel = new JLabel("Your patient id : ");
			idLabel.setBounds(20, 20, 200, 20);
			f.add(idLabel);
			
			JTextField idField = new JTextField("" + nextPid);
			idField.setBounds(240, 20, 200, 20);
			f.add(idField);
			
			JLabel nameLabel = new JLabel("Enter your Name : ");
			nameLabel.setBounds(20, 60, 200, 20);
			f.add(nameLabel);
			
			JTextField nameField = new JTextField();
			nameField.setBounds(240, 60, 200, 20);
			f.add(nameField);
			
			JLabel ageLabel = new JLabel("Enter your Age : ");
			ageLabel.setBounds(20, 100, 200, 20);
			f.add(ageLabel);
		
			JTextField ageField = new JTextField();
			ageField.setBounds(240, 100, 200, 20);
			f.add(ageField);
			
			JLabel emailLabel = new JLabel("Enter your email id : ");
			emailLabel.setBounds(20, 140, 200, 20);
			f.add(emailLabel);
			
			JTextField emailField = new JTextField();
			emailField.setBounds(240, 140, 200, 20);
			f.add(emailField);
			
			JLabel phLabel = new JLabel("Enter your Phone number : ");
			phLabel.setBounds(20, 180, 200, 20);
			f.add(phLabel);
			
			JTextField phField = new JTextField();
			phField.setBounds(240, 180, 200, 20);
			f.add(phField);
			
			JButton insert = new JButton("Insert");
			insert.setBounds(300, 220, 120, 30);
			f.add(insert);
			
			JButton view = new JButton("View");
			view.setBounds(20, 220, 120, 30);
			f.add(view);
			
			JButton delete = new JButton("Delete");
			delete.setBounds(160, 220, 120, 30);
			f.add(delete);
			
			JButton exit = new JButton("Exit");
			exit.setBounds(200, 260, 70, 30);
			
			insert.addActionListener(e->{				
				String name = nameField.getText();
				int age = Integer.parseInt(ageField.getText());
				String email = emailField.getText();
				long ph = Long.parseLong(phField.getText());
				try {				

					PreparedStatement ps = con.prepareStatement("insert into patients values "
							+ "(?,?,?,?,?) ");					
					ps.setInt(1, nextPid);
					ps.setString(2, name);
					ps.setInt(3, age);
					ps.setString(4, email);
					ps.setLong(5, ph);
					n += ps.executeUpdate();
					
					nameField.setText("");
					ageField.setText("");
					emailField.setText("");
					phField.setText("");
					nextPid = pid(con);
					idField.setText("" + nextPid);
					String result = n + "Rows inserted in total.";
					JOptionPane.showMessageDialog(f, result);
					
				}catch(Exception ex) {					
					System.out.println("ERROR  " + ex);
					}		
			});

			view.addActionListener(e ->{
				try {
					String all = "";					
					String q = "select * from patients";
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(q);
					while(rs.next()) {						
						all += rs.getInt("pid") + " | " +
							       rs.getString("name") + " | " +
							       rs.getInt("age") + " | " +
							       rs.getString("email") + " | " +
							       rs.getLong("phone") + "\n";				
					}
					JOptionPane.showMessageDialog(f, all);
					
				}catch(Exception ex) {
					System.out.println("Error  "+ex);
				}			
			});
			
			delete.addActionListener(e ->{
				try {					
					int pid = Integer.parseInt(idField.getText());
					String qd = "delete from patients where pid = " + pid;
					Statement st = con.createStatement();
					int num = st.executeUpdate(qd);					
					String result = num + "Rows deleted in total.";
					JOptionPane.showMessageDialog(f, result);
					idField.setText("" + nextPid);
				}catch(Exception ex) {					
					System.out.println("ERROR  " + ex);					
				}
			});
      
			exit.addActionListener(e ->{
				try {
					con.close();	
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			f.setVisible(true);
			
		}catch(Exception ex) {
			System.out.println("ERROR "+ex);	
		}
	}
}
