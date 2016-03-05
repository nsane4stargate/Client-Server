import java.net.*;
import java.io.*;
public class ServerSide {

	public static void main(String[] args) throws IOException{
		
		double a,b,c, discriminant, root1, root2;
		boolean roots;
		char answer;
		ServerSocket server = new ServerSocket(12345,100);
		Socket connect = server.accept();
		System.out.println("A client connected.");
		
		ObjectOutputStream output=
			new ObjectOutputStream(connect.getOutputStream());
		output.flush();
		ObjectInputStream input=
			new ObjectInputStream(connect.getInputStream());
		System.out.println("I/O streams established.");
		try{
			answer = (char)input.readObject();
			if(answer == 'n'){
				input.close();
				output.close();
				connect.close();
			}else{
				a = Double.parseDouble((String)input.readObject());
				b = Double.parseDouble((String)input.readObject());
				c = Double.parseDouble((String)input.readObject());
				String mes1= "a = " + String.valueOf(a);
				String mes2= "b = " + String.valueOf(b);
				String mes3= "c = " + String.valueOf(c);
				output.writeObject(mes1);
				output.writeObject(mes2);
				output.writeObject(mes3);
				
				discriminant = Math.pow(b, 2) - (4 * a * c);
		
				if(a == 0){
					roots = false;
			    	String mess1= "a = 0, therefore this is now a linear equation";
			    	String mess2= String.valueOf(roots)+"/0 roots";
				    output.writeObject(mess1);
				    output.writeObject(mess2);		
				    output.writeObject(" ");
				    output.flush();
				    }
				if(discriminant > 0)
					//the discriminant is positive then there are two distinct roots
			     {
			        root1 = (-(b) + Math.sqrt(discriminant))/(2.0 * a);
			        root2 = (-(b) - Math.sqrt(discriminant))/(2.0 * a);
			        String mess1= "root1 = "+String.valueOf(root1);
			        String mess2 = "root2 = "+String.valueOf(root2);
			        output.writeObject("Equation has 2 roots");
			        output.writeObject(mess1);
			        output.writeObject(mess2);
			        output.flush();
			        
			      }

			      if(discriminant == 0)
			    	  //the discriminant equals 0 an has only one root
			      {
			    	root1 = (-(b))/2 * a;
			    	String mess1= "Equation has 1 roots";
			    	String mess2= "root1 = "+String.valueOf(root1);
			        output.writeObject(mess1);
			        output.writeObject(mess2);
			        output.writeObject(" ");
			        output.flush();
			      }

			       if(discriminant < 0){
			    	   //this discriminant only produces imaginary roots
			    	  roots = false;
			    	  String mess1= "These variables produces imaginary roots";
			    	  String mess2= String.valueOf(roots)+"/0 roots";
				      output.writeObject(mess1);
				      output.writeObject(mess2);
				      output.writeObject(" ");
			    	  output.flush();
			    	 
			       }
			   }
		}catch(ClassNotFoundException e){
			System.out.println("Unknown objet type received.");
			System.exit(1);
		}
		
		output.flush();
		input.close();
		output.close();
		connect.close();
		System.out.println("Server shut down");
		
	}

}
