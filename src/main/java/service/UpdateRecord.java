package service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class UpdateRecord {
    private static FirestoreInitialization initialization = new FirestoreInitialization();
    private static Scanner sc = new Scanner(System.in);

    public static void updateField(String name) throws IOException, ExecutionException, InterruptedException {
        Firestore db = initialization.initializeFirestore();

        DocumentReference reference = db.collection("Person").document(name);
        System.out.println("Enter Name :");
        String personName = sc.nextLine();

        System.out.println("Enter age :");
        Long age = sc.nextLong();
        sc.nextLine();

        System.out.println("Enter email :");
        String email = sc.nextLine();


        System.out.println("Enter address :");
        String address = sc.nextLine();

        ApiFuture<DocumentSnapshot> future = reference.get();

        DocumentSnapshot document = future.get();

        if (document.exists()){
            reference.update("name",personName);
            reference.update("age",age);
            reference.update("email", email);
            reference.update("address",address);
        }else{
            System.out.println("No such record found!!!");
        }
        System.out.println("Record Updated Successfully");

        try {
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
