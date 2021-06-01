package sg.edu.np.prac3githhub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    private final static String TAG = "List Activity";
    static ArrayList<User> userDBList = new ArrayList<>();
    MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        int profile_counts = dbHandler.getProfilesCount();
        dbHandler.close();

        if(profile_counts > 20){
            userDBList = dbHandler.getUsers();
        } else if (profile_counts == 0){

            for (int i = 0; i < 20; i++) {
                Random r = new Random();
                int random = r.nextInt();
                int random1 = r.nextInt();
                int random2 = r.nextInt();
                String randomName = "Name" + random;
                String randomDesc = "Description " + random1;
                boolean randomBool;
                if (random2 % 2 == 0) {
                    randomBool = false;
                } else {
                    randomBool = true;
                }
                User dbUser = new User(randomName, i + 1, randomDesc, randomBool);
                dbHandler.addUser(dbUser);
            }
        }
        userDBList = dbHandler.getUsers();
        RecyclerView recyclerViewProfiles = findViewById(R.id.rv);
        ProfilesAdapter profilesAdapter = new ProfilesAdapter(userDBList);
        LinearLayoutManager pLayoutManager = new LinearLayoutManager(this);
        recyclerViewProfiles.setLayoutManager(pLayoutManager);
        recyclerViewProfiles.setItemAnimator(new DefaultItemAnimator());
        recyclerViewProfiles.setAdapter(profilesAdapter);

    }

}