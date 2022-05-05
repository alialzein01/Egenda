package com.lau.egenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class logInJava extends AppCompatActivity {
    EditText usern, pass;
    String post_url = "http://10.0.0.2/login.php";
    loginApi logapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        usern = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
    }

    public void signPage(View view) {
        logapi = new loginApi();
        logapi.execute();
    }

    class loginApi extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            HttpClient http_client = new DefaultHttpClient();
            HttpPost http_post = new HttpPost(post_url);


            BasicNameValuePair usernameParam = new BasicNameValuePair("Username", usern.getText().toString());
            BasicNameValuePair passwordParam = new BasicNameValuePair("Password", pass.getText().toString());

            ArrayList<NameValuePair> name_value_pair_list = new ArrayList<>();
            name_value_pair_list.add(usernameParam);
            name_value_pair_list.add(passwordParam);

            try {
                // This is used to send the list with the api in an encoded form entity
                UrlEncodedFormEntity url_encoded_form_entity = new UrlEncodedFormEntity(name_value_pair_list);

                // This sets the entity (which holds the list of values) in the http_post object
                http_post.setEntity(url_encoded_form_entity);

                // This gets the response from the post api and returns a string of the response.
                HttpResponse http_response = http_client.execute(http_post);
                InputStream input_stream = http_response.getEntity().getContent();
                InputStreamReader input_stream_reader = new InputStreamReader(input_stream);
                BufferedReader buffered_reader = new BufferedReader(input_stream_reader);
                StringBuilder string_builder = new StringBuilder();
                String buffered_str_chunk = null;
                while ((buffered_str_chunk = buffered_reader.readLine()) != null) {
                    string_builder.append(buffered_str_chunk);
                }
                Log.i("result", string_builder.toString());
                return string_builder.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                if (s.equalsIgnoreCase("login successfully")) {
                    Intent i = new Intent(logInJava.this, profileJava.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong username or password!", Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}