package service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class ReadById {
    private static FirestoreInitialization initialization = new FirestoreInitialization();

    public static void readById(String name) throws IOException, ExecutionException, InterruptedException {
        Firestore db = initialization.initializeFirestore();
        DocumentReference documentReference = db.collection("Person").document(name);

        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();
        if (document.exists()){
            System.out.println("Name: " + document.getString("name"));
            System.out.println("Age: " + document.getLong("age"));
            System.out.println("Email: " + document.getString("email"));
            System.out.println("Address: " + document.getString("address"));
        }else{
            System.out.println("No such record found!!!");
        }
        try {
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
