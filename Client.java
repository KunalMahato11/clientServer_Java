import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
       
        try(Socket socket = new Socket("localhost",8080)){
            DataInputStream din = new DataInputStream(socket.getInputStream());
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            String str,str1;
         
           do{ 
                System.out.print("Enter the Message : ");
                str = s.nextLine();
                dout.writeUTF(str);
                String fromServer = (String)din.readUTF();
                System.out.println("Received From Server : "+ fromServer);
                if(str.equals("exit")){
                    str1 = (String)din.readUTF();
                    System.out.println(str1);
                }
            
            }while(true);
            

           

        } catch(IOException e){
            System.out.println("Client Error : "+ e.getMessage());
        }finally {
            s.close();
        }
    }
}