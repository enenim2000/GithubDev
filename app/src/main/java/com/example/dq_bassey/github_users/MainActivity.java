package com.example.dq_bassey.github_users;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<User> users;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetUsers(MainActivity.this).execute();
    }

    private void initViews(ArrayList<User> userList){
        recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        users = userList;

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                User user = users.get(position);

                Intent intent = new Intent(MainActivity.this, UserDetails.class);
                Bundle bundle = new Bundle();

                bundle.putSerializable(Common.USER_KEY, user);
                intent.putExtras(bundle);

                startActivity(intent);;
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));

    }


    private class GetUsers extends AsyncTask<Void, Void, Void> {
        ArrayList<User> userList = new ArrayList<User>();
        private ProgressDialog dialog;

        public GetUsers(MainActivity activity) {
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog.setMessage("Retrieving data.");
            dialog.show();
            //Toast.makeText(MainActivity.this,"Retrieving data",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String url = Common.USER_API;
            String jsonStr = sh.makeServiceCall(url);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray users = jsonObj.getJSONArray("items");

                    // looping through All Users
                    for (int i = 0; i < users.length(); i++) {
                        JSONObject c = users.getJSONObject(i);
                        String userName = c.getString("login");
                        String photoUrl = c.getString("avatar_url");
                        String profileUrl = c.getString("html_url");

                        User user = new User();
                        user.setUserName(userName);
                        user.setPhotoUrl(photoUrl);
                        user.setProfileUrl(profileUrl);

                        userList.add(user);
                    }
                } catch (final JSONException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (dialog.isShowing()) {
                dialog.dismiss();
            }

            initViews(userList);
            DataAdapter adapter = new DataAdapter(getApplicationContext(), userList);
            recyclerView.setAdapter(adapter);
        }
    }

}
