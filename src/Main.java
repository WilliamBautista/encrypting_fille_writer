import java.util.Scanner;
import java.util.ArrayList;
import org.jasypt.util.text.*;
import java.io.File;


public class Main {
    public static void main(String[] args){

        String pass;
        String f_name;
        Scanner input = new Scanner(System.in);

        ArrayList<String> writ = new ArrayList<String>();

        System.out.println("******************************************************\n");
        
        System.out.println("Hello welocome to the the file encrypter writer.\nPress enter to begin!");
        System.out.println("If you would like to gain access to a file, please enter: View\n");
        System.out.println("If you would like to quit the program, please type: No\n");

        System.out.println("******************************************************\n");
        

        String choice = input.nextLine().toUpperCase();

        if(choice.toUpperCase().equals("NO")){
            System.out.println("Ok goodbye!");
            System.exit(0);
        } else if(choice.toUpperCase().equals("VIEW")){

            System.out.println("Please enter the name of the file. Do not include file ending.\n");

            f_name = input.nextLine()+".txt";

            while(!new File("filepath"+f_name).isFile()){
                System.out.print("Please try again with a different name.\n\n");
                f_name = input.nextLine()+".txt";

            }

            System.out.print("File name is accepted.\n");

            System.out.print("Pllease enter the password of the file you want to accesss.\n");
            

            pass = input.nextLine();

        }

        System.out.println("Please enter the name of the file.\n");

        f_name = input.nextLine()+".txt";

        while (!Writer.create_file(f_name)){
            System.out.print("Please try again with a different name.\n\n");
            f_name = input.nextLine()+".txt";
        }


        System.out.println("The next step is to now to crete a password that will lock your file.");

        System.out.print("\nA password must have:\n Must contain at least eight characters\n" +
            " Must contain letters, digits, and a special character\n Must contain at lease a digit\n\n");

        pass = input.nextLine();
        
        while (!Pass_create.password_checker(pass)) {

            System.out.print("\nNot a valid password!\n");
            System.out.print("Please try again.\n\n");
            pass = input.nextLine();
            
        }
        System.out.print("\nPasswword has been accepted\n\n");


        System.out.println("Next, please write into file:\nIf you would like to stop type: NO\n");

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

        textEncryptor.setPassword(pass);

        while(true){

          String line = input.nextLine();

          if (line.toUpperCase().equals("DONE")){
            break;
          }
          writ.add(line);
        }
        
        for(String str: writ) {

            String enText = textEncryptor.encrypt(str);
            Writer.write_file(enText+"\n", f_name);

        }

        System.out.println("Thank you very much, your file has been created and encrypted");
        
        input.close();
    }
    
}
