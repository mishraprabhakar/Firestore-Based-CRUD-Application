import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;
import service.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        Scanner sc = new Scanner(System.in);
        Integer choice;
        do {
            
            System.out.println("1. View All Records");
            System.out.println("2. View Record by Name");
            System.out.println("3. Store New Person");
            System.out.println("4. Update existing Record");
            System.out.println("5. Delete Record");
            System.out.println("6. Exit\n\n");

            System.out.println("Choose Option");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:
                    ReadData.getData();
                    System.out.println();
                    break;

                case 2:
                    System.out.println("Enter Username ");
                    String name = sc.nextLine();
                    ReadById.readById(name);
                    System.out.println();
                    break;

                case 3:
                    AddingData.addData();
                    System.out.println();
                    break;

                case 4:
                    System.out.println("Enter existing username to update record");
                    String user = sc.nextLine();
                    UpdateRecord.updateField(user);
                    System.out.println();
                    break;

                case 5:
                    System.out.println("Enter username to Delete");
                    String uname = sc.nextLine();
                    DeleteRecord.deleteRecord(uname);
                    System.out.println();
                    break;

                case 6:
                    break;

                default:
                    System.out.println("Invalid Input");
                    System.out.println();
                    break;

            }

        }while(choice !=6);

    }
}
