package com.example.varsha.navtry;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import com.example.varsha.navtry.R;
import com.example.varsha.navtry.FatSecretUtils;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;


public class SearchDB extends AppCompatActivity {


    private static final String ACCESS_TOKEN_MISSING = "gone";

    private static final String TAG = SearchDB.class.getName();


    public void onclickhome(View v) {
        Intent intent = new Intent(SearchDB.this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_search_db);

        SharedPreferences pref = getSharedPreferences(FatSecretUtils.PREFERENCES_FILE, MODE_PRIVATE);
        String accessToken = pref.getString(FatSecretUtils.OAUTH_ACCESS_TOKEN_KEY, ACCESS_TOKEN_MISSING);

        if(accessToken.equals(ACCESS_TOKEN_MISSING)) {
            Intent login = new Intent(this, SearchOptions.class);
            startActivity(login);
            finish();
            return;
        }

        FatSecretUtils.setContext(this);

        TextView loggedInText = (TextView) findViewById(R.id.loggedInText);
        loggedInText.setText("auth token = " + pref.getString("oauth_access_token", ACCESS_TOKEN_MISSING));

        final TextView responseText = (TextView) findViewById(R.id.responseText);
        responseText.setText("Searching foods for bananas...");

        final Gson gson = new GsonBuilder().setPrettyPrinting().create();

        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader reader = null;
                try {
                    String signedFoodSearchUrl = FatSecretUtils.sign("http://platform.fatsecret.com/rest/server.api?format=json&method=food.search&oauth_consumer_key=8bb11b48638b4bd79a9b43551b2a5f30&oauth_nonce=127685168&oauth_signature=Sx14uPLA0NUHjblTVTruR651xEI%253D&oauth_signature_method=HMAC-SHA1&oauth_timestamp=TIMESTAMP&oauth_version=1.0&search_expression=orange");

                    Log.d(TAG, "Signed foods.search URL = " + signedFoodSearchUrl);

                    HttpURLConnection foodSearchConnection = (HttpURLConnection) new URL(signedFoodSearchUrl).openConnection();
                    reader = new BufferedReader(new InputStreamReader(foodSearchConnection.getInputStream()));
                    final String json = reader.readLine();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            JsonObject response = gson.fromJson(json, JsonObject.class);
                            responseText.setText(gson.toJson(response));
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (OAuthExpectationFailedException e) {
                    e.printStackTrace();
                } catch (OAuthCommunicationException e) {
                    e.printStackTrace();
                } catch (OAuthMessageSignerException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
