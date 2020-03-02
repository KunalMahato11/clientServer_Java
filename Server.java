
import java.net.*;
import java.io.*;
import java.util.*;


public class Server {
    public static void main(String args[]){
         int i = 0;
        
        try(ServerSocket ss = new ServerSocket(8080)){
     
            while(true){
                i++;
                new ClientHandler(ss.accept()).start();
                System.out.println("Client "+ i +"Connected");

            }
        }
        catch(IOException e) {
            System.out.println("Server Exception : " + e.getMessage());
        }

    }
}

class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        int i = 0;
        Scanner s = new Scanner(System.in);
        try{
           
            DataInputStream din = new DataInputStream(socket.getInputStream());
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            
            while(true){
                i++;
                String str = (String)din.readUTF();
                System.out.println("Received From Client "+ i +" : "+str);
                System.out.print("Enter the Message to reply : ");
                String str1 = s.nextLine();
                dout.writeUTF(str1);
                if(str.equals("exit")){
                    break;
                }
            }
           

        }
        catch(IOException e){
            System.out.println("Oops : " + e.getMessage());
        }
        finally {
            
            try{
                socket.close();
                s.close();
            }
            catch(IOException e){

            }
        }
    }
}


