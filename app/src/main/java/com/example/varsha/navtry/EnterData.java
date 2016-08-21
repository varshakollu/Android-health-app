package com.example.varsha.navtry;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Varsha on 5/6/2016.
 */
public class EnterData extends ActionBarActivity {

 private EditText editTextName;
    private EditText editTextCalorie;
    private EditText editTextDate;
    private EditText editTextNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_enterdata);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextCalorie = (EditText) findViewById(R.id.editTextCalorie);
        editTextDate = (EditText) findViewById(R.id.editTextDate);
        editTextNote = (EditText) findViewById(R.id.editTextNote);

    }

    public void onclickhome1(View v) {
        Intent intent = new Intent(EnterData.this, MainActivity.class);
        startActivity(intent);
    }

    public void insert(View view){
        String name = editTextName.getText().toString();
        String calorie = editTextCalorie.getText().toString();
        String date = editTextName.getText().toString();
        //String note = editTextName.getText().toString();

        insertToDatabase(name,calorie,date);
    }

    public void see(View view){

        Intent myIntent = new Intent(EnterData.this, RetrieveData.class);
        startActivity(myIntent);
    }

    private void insertToDatabase(final String name, final String calorie, final String date){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String paramUsername = params[0];
                String paramAddress = params[1];

                String name = editTextName.getText().toString();
                String calorie = editTextCalorie.getText().toString();
                String date = editTextDate.getText().toString();

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("name", name));
                nameValuePairs.add(new BasicNameValuePair("calorie", calorie));
                nameValuePairs.add(new BasicNameValuePair("date", date));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://pragatisahu.esy.es/food.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "success";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
                textViewResult.setText("Saved");
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(name, calorie, date);
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
