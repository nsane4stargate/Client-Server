import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientSide {
	
	public static void main(String[] args) throws IOException {
		
			Scanner read = new Scanner(System.in);
			Socket client = new Socket("localhost",12345);
			System.out.println("Connection established");
		
			ObjectOutputStream output=
				new ObjectOutputStream(client.getOutputStream());
			output.flush();
			ObjectInputStream input=
				new ObjectInputStream(client.getInputStream());
			System.out.println("Would you need help solving a quadratic equation? Y/N");
			char answer = read.next().trim().charAt(0);
			output.writeObject(answer);
			output.flush();
			if(answer == 'n'){
				input.close();
				output.close();
				client.close();
				
			}else{
				System.out.println("Please input values for variabes a, b,& c");
				double a =read.nextDouble();
				double b = read.nextDouble();
				double c = read.nextDouble();
				output.writeObject(String.valueOf(a));
				output.writeObject(String.valueOf(b));
				output.writeObject(String.valueOf(c));
				output.flush();
			}
			try{
				String message=(String)input.readObject();
				System.out.println(message);
				String message2=(String)input.readObject();
				System.out.println(message2);
				String message3=(String)input.readObject();
				System.out.println(message3);
				String message4=(String)input.readObject();
				System.out.println(message4);
				String message5=(String)input.readObject();
				System.out.println(message5);
				String message6=(String)input.readObject();
				System.out.println(message6);
				
			}catch(ClassNotFoundException e){
				System.out.println("Unknown object type received.");
				System.exit(1);
			}
			
			input.close();
			output.close();
			client.close();
			System.out.println("Client shut down");
			
	}//end of main method
				
}
