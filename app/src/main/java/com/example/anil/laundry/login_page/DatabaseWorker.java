package com.example.anil.laundry.login_page;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.UriMatcher;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static com.facebook.FacebookSdk.getApplicationContext;


public class DatabaseWorker extends AsyncTask<String, Void, String> {

    private Context context;
    private AlertDialog alertDialog;
    /*public DatabaseWorker(Context ctx) {
        context = ctx;
    }*/
    private String json_string;

    public DatabaseWorker() {
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://mdconstructionpune.com/laundrydata/login.php";
        String register_url = "http://mdconstructionpune.com/laundrydata/register.php";
        String contactUs_url = "http://mdconstructionpune.com/laundrydata/tbl_contact_us.php";
        String terms_cond_url = "http://mdconstructionpune.com/laundrydata/get_json_data_tblTermsCond.php";

        if (type.equals("login")) {
            try {
                String mobileNo = params[1];
                String password = params[2];
                URL url = new URL(login_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data =
                        URLEncoder.encode("mobileNo", "UTF-8") + "=" + URLEncoder.encode(mobileNo, "UTF-8") + "&"
                                + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                StringBuilder result = new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("register")) {
            try {
                String firstName = params[1];
                System.out.println(firstName);
                String lastName = params[2];
                String Email = params[3];
                String Mobile = params[4];
                String Password = params[5];
                URL url = new URL(register_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data =
                        URLEncoder.encode("first_name", "UTF-8") + "=" + URLEncoder.encode(firstName, "UTF-8") + "&"
                                + URLEncoder.encode("last_name", "UTF-8") + "=" + URLEncoder.encode(lastName, "UTF-8") + "&"
                                + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(Email, "UTF-8") + "&"
                                + URLEncoder.encode("mobile", "UTF-8") + "=" + URLEncoder.encode(Mobile, "UTF-8") + "&"
                                + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(Password, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                StringBuilder result = new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("contact_us")) {
            try {
                String Name = params[1];
                String Email = params[2];
                String Mobile = params[3];
                String Message = params[4];
                URL url = new URL(contactUs_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data =
                        URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(Name, "UTF-8") + "&"
                                + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(Email, "UTF-8") + "&"
                                + URLEncoder.encode("mobile", "UTF-8") + "=" + URLEncoder.encode(Mobile, "UTF-8") + "&"
                                + URLEncoder.encode("message", "UTF-8") + "=" + URLEncoder.encode(Message, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                StringBuilder result = new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (type.equals("Terms_Cond")) {
            try {
                String JSON_STRING;
                String terms = params[1];
                String cond = params[2];
                URL url = new URL(terms_cond_url);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRING + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(getApplicationContext(), "Trying to Login", Toast.LENGTH_LONG).show();
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
        alertDialog.dismiss();
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(getApplicationContext(), "Trying to Login", Toast.LENGTH_LONG).show();
        json_string = result;
        alertDialog.setMessage(result);
        alertDialog.show();
        alertDialog.dismiss();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}