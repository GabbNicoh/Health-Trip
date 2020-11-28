package com.pakage.googlemapsnearbyplacesapi;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.GoogleMap;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.pakage.googlemapsnearbyplacesapi.POJO.POJO.ExampleInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class InfoView extends AppCompatActivity {
    private static final int REQUEST_CALL =1;
    GoogleMap mMap;
    TextView name1;
    TextView loc1;
    TextView num1;
    TextView hours1;
    TextView web1;
    TextView rat1;
    TextView rev1;
    ImageView phot1;
    ImageView call1;
    private String placesId;
    String formattedPhoneNumber1 = null;

    private Context context = InfoView.this;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_view);

        name1 = (TextView) findViewById(R.id.Placetitle);
        loc1 = (TextView) findViewById(R.id.Placeadress);
        num1 = (TextView) findViewById(R.id.Placecontact);
        hours1 = (TextView) findViewById(R.id.Placehours);
        rat1 = (TextView) findViewById(R.id.Placerating);
        web1 = (TextView) findViewById(R.id.Placewebsite);
        phot1 = (ImageView) findViewById(R.id.placephoto);
        call1 = (ImageView) findViewById(R.id.call);



        placesId = getIntent().getStringExtra("getId");
        build_retrofit_and_get_response(placesId);







    }



    public void build_retrofit_and_get_response(final String placesId) {

        final String url = "https://maps.googleapis.com/maps/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        RetrofitMaps service = retrofit.create(RetrofitMaps.class);

        final Call<ExampleInfo> call = (Call<ExampleInfo>)service.getDetails(placesId);

        call.enqueue(new Callback<ExampleInfo>() {




            @Override
            public void onResponse(Call<ExampleInfo> call, Response<ExampleInfo> response) {
                Log.d("LinkResponse",String.valueOf(response));


                String name2 = null;
                String location1 = null;
                String hours = null;
                String web = null;
                String rating = null;
                String photo = null;



                try {
                        name2 = response.body().getResult().getName();
                        Log.d("Value of String",String.valueOf(name2));
                        location1 = response.body().getResult().getFormattedAddress();
                        Log.d("Value of String",String.valueOf(location1));
                        formattedPhoneNumber1 = response.body().getResult().getFormattedPhoneNumber();
                        Log.d("Value of String",String.valueOf(formattedPhoneNumber1));
                        hours = String.valueOf(response.body().getResult().getOpeningHours().getWeekdayText());
                        Log.d("Value of String",String.valueOf(hours));
                        web = response.body().getResult().getWebsite();
                        Log.d("Value of String",String.valueOf(web));
                        rating = String.valueOf(response.body().getResult().getRating());
                        Log.d("Value of String",String.valueOf(rating));

                        photo = String.valueOf(response.body().getResult().getPhotos(0));
                         Log.d("Value of String",String.valueOf(photo));



                }catch (Exception e) {
                    Log.d("onResponse", "There is an error");

                }


                name1.setText(name2);
                loc1.setText(location1);
                num1.setText(formattedPhoneNumber1);
                hours1.setText(hours);
                rat1.setText("Rating: " + rating);
                web1.setText(web);

                String url = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=CnRtAAAATLZNl354RwP_9UKbQ_5Psy40texXePv4oAlgP4qNEkdIrkyse7rPXYGd9D_Uj1rVsQdWT4oRz4QrYAJNpFX7rzqqMlZw2h2E2y5IKMUZ7ouD_SlcHxYq1yL4KbKUv3qtWgTK0A6QbGh87GB3sscrHRIQiG2RrmU_jF4tENr9wGS_YxoUSSDrYjWmrNfeEHSGSc3FyhNLlBU&sensor=true&key=AIzaSyADP1Z54-Y3M8S3nGlMvqVWmKNroHzhhAk";
                Glide.with(InfoView.this)
                        .load(url + photo)
                        .into(phot1);





            }

            @Override
            public void onFailure(Call<ExampleInfo> call, Throwable t) {
                Log.d("onFailure", t.toString());

            }

        });
        

         call1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();

            }
        });



    }
    private void makePhoneCall(){
        if (ContextCompat.checkSelfPermission(InfoView.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(InfoView.this,
                    new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        }
        else{
            if (formattedPhoneNumber1 != null){
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + formattedPhoneNumber1));
                Log.d("Value of String",String.valueOf(formattedPhoneNumber1));
                startActivity(intent);

            }else if (formattedPhoneNumber1 == null){
                Toast.makeText(getApplicationContext(),"Place contact is not available", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_CALL){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }
            else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }









}
