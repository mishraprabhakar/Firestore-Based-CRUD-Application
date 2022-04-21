package service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ReadData {

    private static FirestoreInitialization initialization = new FirestoreInitialization();

    public static void getData() throws ExecutionException, InterruptedException, IOException {

        //Firestore Connection
        Firestore db = initialization.initializeFirestore();

        // asynchronously retrieve all users
        ApiFuture<QuerySnapshot> query = db.collection("Person").get();

        // query.get() blocks on response
        QuerySnapshot querySnapshot = query.get();

        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            System.out.println("User: " + document.getId());
            System.out.println("Name: " + document.getString("name"));
            System.out.println("Age: " + document.getLong("age"));
            System.out.println("Email: " + document.getString("email"));
            System.out.println("Address: " + document.getString("address"));
            System.out.println();
        }
        try {
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
