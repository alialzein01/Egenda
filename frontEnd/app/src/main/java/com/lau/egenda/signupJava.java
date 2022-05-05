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

public class signupJava extends AppCompatActivity {
    EditText Name, User, Em, Pass, Conf;
    String post_url = "https://10.0.0.2/userregister.php";
    signUpApi sapi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        Name = (EditText) findViewById(R.id.name);
        User = (EditText) findViewById(R.id.username);
        Em = (EditText) findViewById(R.id.email);
        Pass = (EditText) findViewById(R.id.password);
        Conf = (EditText) findViewById(R.id.confirmation);
    }

    public void goBack(View view) {
        sapi = new signUpApi();
        sapi.execute();
    }

    class signUpApi extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            HttpClient http_client = new DefaultHttpClient();
            HttpPost http_post = new HttpPost(post_url);

            String Uname = Name.getText().toString();
            String Username = User.getText().toString();
            String Umail = User.getText().toString();
            String Upass = User.getText().toString();

            BasicNameValuePair namep = new BasicNameValuePair("FullName", Uname);
            BasicNameValuePair usernamep = new BasicNameValuePair("Username", Username);
            BasicNameValuePair emailp = new BasicNameValuePair("Email", Umail);
            BasicNameValuePair passp = new BasicNameValuePair("Password", Upass);

            ArrayList<NameValuePair> name_value_pair_list = new ArrayList<>();
            name_value_pair_list.add(namep);
            name_value_pair_list.add(usernamep);
            name_value_pair_list.add(emailp);
            name_value_pair_list.add(passp);

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
                if (s.equalsIgnoreCase("User Registered Successfully")) {
                    Intent i = new Intent(signupJava.this, logInJava.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"Something Went Wrong",Toast.LENGTH_LONG).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}