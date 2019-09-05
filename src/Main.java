import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
import java.sql.PreparedStatement;
public class Main {

	

			static Scanner sc = new Scanner(System.in);

			public static void addEmp() {
				/*try {
					Connection con = DatabaseConnection.getCon();
					//eno++;
					System.out.print("Enter employee's number :");
					int eno = sc.nextInt();
					System.out.print("Enter employee's name :");
					String ename = sc.next();
					System.out.print("Enter employee's Salary :");
					double salary = sc.nextDouble();

					String sql = "insert into empdata1 values (" + eno + ",'" + ename + "'," + salary + ")";
					System.out.println(sql);
					PreparedStatement ps1 =  con.prepareStatement(sql);
					ps1.executeUpdate();
					System.out.println("data feeded successfully!");
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
*/				try{
				Connection con = DatabaseConnection.getCon();
				CallableStatement stmt = con.prepareCall("{call insert_employee(?,?,?)}");
				System.out.println("Enter Id:");
				int id = sc.nextInt();
				System.out.println("Enter ename:");
				String name = sc.next();
				System.out.println("Enter Salary:");
				int sal = sc.nextInt();
				stmt.setInt(1, id);
				stmt.setString(2, name);
				stmt.setInt(3, sal);
				stmt.execute();
				con.close();
				System.out.println("Data Added into Table");
				}catch (Exception e) {
				e.printStackTrace();
				}
			}

			public static void ViewAllEmp() {
				try {

					Connection con = DatabaseConnection.getCon();
					String sql = "select eno,ename,salary from emp";
					PreparedStatement ps1 =con.prepareStatement(sql);
					ResultSet rs = ps1.executeQuery(sql);
					while (rs.next()) {
						System.out.print(rs.getInt(1));
						System.out.print(",");
						System.out.print(rs.getString(2));
						System.out.print(",");
						System.out.print(rs.getDouble(3));
						System.out.println();
					}
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			public static void removeEmp() {
				try {
					Connection con = DatabaseConnection.getCon();
					System.out.print("enter employee number you want to delete record of :");
					int eno = sc.nextInt();

					String sql = "delete from emp where eno=" + eno;
					PreparedStatement st1 = (PreparedStatement) con.prepareStatement(sql);
					st1.executeUpdate();
					con.close();
					System.out.println("Record deleted sucessfully!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			public static void clearData() {
				try {
					Connection con = DatabaseConnection.getCon();
					PreparedStatement st1 = con.prepareStatement("delete from emp");
					st1.executeUpdate();
					con.close();
					System.out.println("table deleted sucessfully!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			public static void updateSalary() {
				try {
					Connection con = DatabaseConnection.getCon();
					System.out.print("Entre employee number you want to update salary of :");
					int eno = sc.nextInt();
					System.out.print("Entre employee's updated salary :");
					double salary = sc.nextDouble();
					String sql = "update emp set salary="+salary+" where eno="+eno+"";

					PreparedStatement ps1 =  con.prepareStatement(sql);
					//ps1.setDouble(1, salary);
					//ps1.setInt(2, eno);
					ps1.executeUpdate();
					con.close();
					System.out.println("table updated  successfully!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			public static void searchEmployee() {
				try {
					Connection con = DatabaseConnection.getCon();
					System.out.print("enter employee number you want to look details for :");
					int eno = sc.nextInt();

					String sql = "select eno,ename,salary from emp where eno =" + eno;
					PreparedStatement ps1 =  con.prepareStatement(sql);
					ResultSet rs = ps1.executeQuery(sql);
					while (rs.next()) {
						System.out.println(rs.getInt(1)+",");
						System.out.println(rs.getString(2)+",");
						System.out.println(rs.getDouble(3));
						
					}
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			public static void viewSal(){
				try{
				Connection con = DatabaseConnection.getCon();
				System.out.println("Enter Id:");
				int id = sc.nextInt();

				CallableStatement stmt = con.prepareCall("{?=call return_sal(?)}");
				stmt.registerOutParameter(1,Types.INTEGER);
				stmt.setInt(2,id);
				stmt.execute();
				con.close();
				System.out.println("Salary displayed="+stmt.getInt(1));
				}catch (Exception e) {
					e.printStackTrace();
				}
			}

			public static void main(String[] args) throws ClassNotFoundException, SQLException {

				Scanner sc = new Scanner(System.in);

				System.out.println("press 1 to add new Employee");
				System.out.println("press 2 view all Employee");
				System.out.println("press 3 to remove an Employee");
				System.out.println("press 4 to clear Date of all Employes");
				System.out.println("press 5 to change salary Employee");
				System.out.println("press 6 to search for an  Employee");
				System.out.println("press 7 to Exit");
				
				// m.addEmp();

				while (true) {
					int choice = sc.nextInt();
					switch (choice) {
					case 1:
						addEmp();
						break;
					case 2:
						ViewAllEmp();
						break;
					case 3:
						removeEmp();
						break;
					case 4:
						clearData();
						break;
					case 5:
						updateSalary();
						break;
					case 6:
						searchEmployee();
						break;
					case 7:
						viewSal();
						break;
					default:
						System.out.println("Please Choose correct Option");
						break;
					}
				}

			}

		
	}


