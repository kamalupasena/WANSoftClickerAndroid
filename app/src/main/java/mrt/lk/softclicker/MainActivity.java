package mrt.lk.softclicker;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.IOException;

import mrt.lk.softclicker.config.AppConfig;
import mrt.lk.softclicker.config.MainApplication;
import mrt.lk.softclicker.model.Quections;
import mrt.lk.softclicker.model._embedded;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Button btnMain, btnSubmit;
    private ProgressBar progressBar;
    private MainApplication mainApplication;
    private String ipAddress;
    private RadioGroup radioGroup;
    private RadioButton answerA, answerB, answerC, answerD;
    private TextView quectionTitle;
    private Quections quection;
    private RelativeLayout quectionView;
    private EditText editText;
    private String registerNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Question View");

        mainApplication = ((MainApplication) this.getApplication());

        btnMain = findViewById(R.id.btnGetQuection);
        btnSubmit = findViewById(R.id.btnSubmit);
        progressBar = findViewById(R.id.progressLoading);
        radioGroup = findViewById(R.id.radio);
        quectionView = findViewById(R.id.quectionView);
        quectionTitle = findViewById(R.id.txtQuectionTitle);
        editText = findViewById(R.id.editText);

        answerA = findViewById(R.id.radAnswerA);
        answerB = findViewById(R.id.radAnswerB);
        answerC = findViewById(R.id.radAnswerC);
        answerD = findViewById(R.id.radAnswerD);




        ipAddress = mainApplication.getServerIPAddress();


        Toast.makeText(this, mainApplication.getServerIPAddress(), Toast.LENGTH_SHORT).show();


        progressBar.setVisibility(View.GONE);
        quectionView.setVisibility(View.GONE);
        btnSubmit.setVisibility(View.GONE);


        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerNumber = editText.getText().toString();

                if(registerNumber.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please Enetr your register id",Toast.LENGTH_LONG).show();
                    return;
                }




                progressBar.setVisibility(View.VISIBLE);
                editText.setVisibility(View.GONE);
                new OkHttpHandler().execute("http://" + ipAddress + AppConfig.GET_QUECTIONS);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                View radioButton = radioGroup.findViewById(radioButtonID);

                int idx = radioGroup.indexOfChild(radioButton);

                Toast.makeText(getApplicationContext(), "" + idx, Toast.LENGTH_SHORT).show();

                JsonObject jsonObject = new JsonObject();

                String answer = "";

                switch (idx){
                    case 0:
                        answer = "answer1";
                        break;
                    case 1:
                        answer = "answer2";
                        break;
                    case 2:
                        answer = "answer3";
                        break;
                    case 3:
                        answer = "answer4";
                        break;
                    default:
                        break;
                }

                jsonObject.addProperty("quection",quection.get_links().getSelf().getHref());
                jsonObject.addProperty("answer",answer);
                jsonObject.addProperty("studentId",registerNumber);

                new SubmitAnswers().execute(jsonObject.toString());






            }
        });

    }

    private class SubmitAnswers extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {

            OkHttpClient client = new OkHttpClient();
            MediaType type = MediaType.parse("application/json; charset=utf-8");
            RequestBody requestBody = RequestBody.create(type,strings[0]);
            Request request = new Request.Builder().url("http://"+ipAddress+AppConfig.SUBMIT_ANSWERS).post(requestBody).build();

            try {
                Response response =  client.newCall(request).execute();

                Log.d("Response",response.body().string());

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            btnSubmit.setVisibility(View.GONE);
            btnMain.setVisibility(View.VISIBLE);
            quectionView.setVisibility(View.GONE);
            editText.setVisibility(View.VISIBLE);
        }
    }

    private class OkHttpHandler extends AsyncTask<String, Void, Quections> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected Quections doInBackground(String... params) {

            Request.Builder builder = new Request.Builder();
            builder.url(params[0]);
            Request request = builder.build();

            try {
                Response response = client.newCall(request).execute();

                JSONObject jsonObject = new JSONObject(response.body().string());

                Gson gson = new Gson();

                _embedded embedded = gson.fromJson(jsonObject.getJSONObject("_embedded").toString(), _embedded.class);

                if (embedded == null) {
                    return null;
                } else {
                    if (embedded.getQuections() != null) {
                        if (embedded.getQuections().length > 0) {
                            return embedded.getQuections()[0];
                        }
                    }
                }

                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Quections quections) {

            if (quections != null) {

                quection = quections;
                btnMain.setVisibility(View.GONE);
                btnSubmit.setVisibility(View.VISIBLE);

                progressBar.setVisibility(View.GONE);
                quectionView.setVisibility(View.VISIBLE);

                quectionTitle.setText(quections.getQuestion());

                answerA.setText(quections.getAnswer1());
                answerB.setText(quections.getAnswer2());
                answerC.setText(quections.getAnswer3());
                answerD.setText(quections.getAnswer4());
            }


        }
    }
}


