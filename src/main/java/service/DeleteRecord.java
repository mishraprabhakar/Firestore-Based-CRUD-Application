package service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class DeleteRecord {

    private static FirestoreInitialization initialization = new FirestoreInitialization();

    public static void deleteRecord(String name) throws IOException, ExecutionException, InterruptedException {
        Firestore db = initialization.initializeFirestore();
        DocumentReference reference = db.collection("Person").document(name);

        ApiFuture<DocumentSnapshot> future = reference.get();

        DocumentSnapshot document = future.get();
        if (document.exists()){
            reference.delete();
            System.out.println("Document Deleted Successfully!!!");
        }else{
            System.out.println("Invalid username");
        }

        try {
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
