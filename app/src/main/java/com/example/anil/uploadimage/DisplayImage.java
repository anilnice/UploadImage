package com.example.anil.uploadimage;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.anil.uploadimage.model.ReadData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DisplayImage extends AppCompatActivity {
    ImageView iv;
    EditText et;
    RecyclerView rv;
    ProgressDialog progressDialog;
    private String urlWebService="https://anil12345678.000webhostapp.com/fetchimage.php";
    List<ReadData> imagesdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);
        iv=(ImageView)findViewById(R.id.image);
        imagesdata=new ArrayList<ReadData>();
        rv=(RecyclerView)findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);

    }

    public void getImage(View view) {
//        String name=et.getText().toString();
        class GetImageFromServer extends AsyncTask<String,Void,String>{
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(DisplayImage.this,"Image is Uploading","Please Wait",false,false);
            }

            @Override
            protected String doInBackground(String... strings) {
                try {
                    //creating a URL
                    URL url = new URL(urlWebService);

                    //Opening the URL using HttpURLConnection
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    //StringBuilder object to read the string from the service
                    StringBuilder sb = new StringBuilder();

                    //We will use a buffered reader to read the string from service
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    //A simple string to read values from each line
                    String json;

                    //reading until we don't find null
                    while ((json = bufferedReader.readLine()) != null) {

                        //appending it to string builder
                        sb.append(json + "\n");
                    }

                    //finally returning the read string
                    return sb.toString().trim();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
                @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();
                    try {
                        JSONArray object=new JSONArray(s);
                        for (int i=0;i<=object.length();i++){
                            JSONObject a=object.getJSONObject(i);
                            ReadData currentdata=new ReadData();
                            int id=a.getInt("id");
                            String name=a.getString("image_name");
                            String path=a.getString("image_path");
                            currentdata.setId(id);
                            currentdata.setName(name);
                            currentdata.setPath(path);
                            imagesdata.add(currentdata);
                            rv.setAdapter(new Myadapter(DisplayImage.this,imagesdata));
                            Log.d("hi",  ""+id+"   "+name+"   "+path);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(DisplayImage.this, ""+s, Toast.LENGTH_SHORT).show();
            }
        }
        GetImageFromServer server=new GetImageFromServer();
        server.execute();
    }


}
