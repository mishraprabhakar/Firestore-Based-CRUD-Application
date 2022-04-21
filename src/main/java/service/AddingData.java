package service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class AddingData {
    private static FirestoreInitialization initialization = new FirestoreInitialization();
    private static Scanner sc = new Scanner(System.in);

    public static void addData() throws ExecutionException, InterruptedException, IOException {
        Firestore db = initialization.initializeFirestore();

        System.out.println("Enter username");
        String uname = sc.nextLine();
        //Adding first record to the document
        DocumentReference docRef = db.collection("Person").document(uname);
        System.out.println("Enter Name");
        String name = sc.nextLine();

        System.out.println("Enter age");
        Integer age = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter email");
        String email = sc.nextLine();

        System.out.println("Enter Address");
        String address = sc.nextLine();

        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("address", address);
        data.put("age", age);
        data.put("email", email);

        ApiFuture<WriteResult> result = docRef.set(data);
        System.out.println();
        System.out.println("New Data Added!!!");
        try {
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
