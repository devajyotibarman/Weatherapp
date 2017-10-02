package edureka.devajyoti.com.weatherapp.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import edureka.devajyoti.com.weatherapp.R;
import edureka.devajyoti.com.weatherapp.api.ApiClient;
import edureka.devajyoti.com.weatherapp.api.ApiInterface;
import edureka.devajyoti.com.weatherapp.model.ForecastResponse;
import edureka.devajyoti.com.weatherapp.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "ebfcac32bda131ed5a160f2757938396";
    private final static int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 201;
    ApiInterface apiService;
    LocationManager locationManager;
    Criteria locationCriteria;
    String bestLocationProvider = null;
    android.support.v7.app.ActionBar ab;
    WeatherResponse weatherResponse;
    private SlidingUpPanelLayout mLayout;

    TextView city_value_tv;
    TextView country_value_tv;
    TextView weather_value_tv;
    TextView weather_detail_value;
    TextView temp_value_tv;
    TextView temp_max_value_tv;
    TextView temp_min_value_tv;
    TextView pressure_value_tv;
    TextView humidity_value_tv;
    TextView visibility_val;
    TextView wind_speed_value_tv;
    TextView wind_dir_value_tv;
    TextView clouds_value_tv;
    ImageView weather_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar) findViewById(R.id.main_toolbar));

        ab = getSupportActionBar();
        assert ab != null;
        ab.setSubtitle(R.string.toolbar_subtitle);

        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        ab.setLogo(R.drawable.g53);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Invalid/Missing API", Toast.LENGTH_LONG).show();
            return;
        }

        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);

        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i(TAG, "onPanelSlide, offset " + slideOffset);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                Log.i(TAG, "onPanelStateChanged " + newState);
            }
        });
        mLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });

        city_value_tv = (TextView) findViewById(R.id.city_value);
        country_value_tv = (TextView) findViewById(R.id.country_value);
        weather_value_tv = (TextView) findViewById(R.id.weather_value);
        weather_detail_value = (TextView) findViewById(R.id.weather_detail_value);
        temp_value_tv = (TextView) findViewById(R.id.temperature_value);
        temp_max_value_tv = (TextView) findViewById(R.id.temp_max_value);
        temp_min_value_tv = (TextView) findViewById(R.id.temp_min_value);
        pressure_value_tv = (TextView) findViewById(R.id.pressure_value);
        humidity_value_tv = (TextView) findViewById(R.id.humidity_value);
        visibility_val = (TextView) findViewById(R.id.visibility_value);
        wind_speed_value_tv = (TextView) findViewById(R.id.wind_speed_value);
        wind_dir_value_tv = (TextView) findViewById(R.id.wind_dir_value);
        clouds_value_tv = (TextView) findViewById(R.id.clouds_value);
        weather_iv = (ImageView) findViewById(R.id.weather_image);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                alertBuilder.setCancelable(true);
                alertBuilder.setTitle("Location Access");
                alertBuilder.setMessage("Weatherapp needs location access permission to show the weather near you");
                alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                        Manifest.permission.ACCESS_COARSE_LOCATION},
                                MY_PERMISSIONS_REQUEST_FINE_LOCATION);
                    }
                });

                AlertDialog alert = alertBuilder.create();
                alert.show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_FINE_LOCATION);
            }
        } else {
            refreshWeather();
        }
    }

    public void refreshWeather() {
        locationCriteria = new Criteria();
        bestLocationProvider = locationManager.getBestProvider(locationCriteria, false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "Location Access Denied!", Toast.LENGTH_LONG).show();
        }
        Location currentLocation = locationManager.getLastKnownLocation(bestLocationProvider);

        if (currentLocation != null) {
            apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<WeatherResponse> callWeatherApi = apiService.getWeather(currentLocation.getLatitude(), currentLocation.getLongitude(), API_KEY, "metric");
            callWeatherApi.enqueue(new Callback<WeatherResponse>() {
                @Override
                public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                    int statusCode = response.code();
                    Log.e(TAG, "Status Code: " + statusCode);
                    if (statusCode != 0) {
                        weatherResponse = response.body();
                        assert weatherResponse != null;
                        Log.e(TAG, "City: " + weatherResponse.getName() + ", " + weatherResponse.getSys().getCountry());
                        fillValues(weatherResponse);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                    Toast.makeText(getApplicationContext(), "Failed to get a response!", Toast.LENGTH_LONG).show();
                }
            });
            refreshForecast(currentLocation);
        } else {
            Toast.makeText(getApplicationContext(), "Current Location Returned Null", Toast.LENGTH_SHORT).show();
        }
    }

    public void refreshForecast(Location location) {
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ForecastResponse> callForecastApi = apiService.getForecast(location.getLatitude(), location.getLongitude(), 10, API_KEY, "metric");
        callForecastApi.enqueue(new Callback<ForecastResponse>() {
            @Override
            public void onResponse(@NonNull Call<ForecastResponse> call, @NonNull Response<ForecastResponse> response) {
                int statusCode = response.code();
                Log.e(TAG, "Status Code: " + statusCode);
                if (statusCode != 0) {
                    ForecastResponse forecastResponse = response.body();
                    assert forecastResponse != null;
                    for (int i = 0; i < forecastResponse.getList().size(); i++) {
                        Log.e(TAG, "Weather: " + forecastResponse.getList().get(i).getWeather().get(0).getMain());
                    }
                    fillForecast(forecastResponse);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ForecastResponse> call, @NonNull Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                Toast.makeText(getApplicationContext(), "Failed to get a response!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void fillForecast(ForecastResponse forecastResponse) {
        Resources r = getResources();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault());
        String name = getPackageName();
        TextView tv;
        ImageView iv;

        for (int i = 0; i < forecastResponse.getList().size(); i++) {
            tv = (TextView) findViewById(r.getIdentifier("day" + i + "_weather", "id", name));
            tv.setText(forecastResponse.getList().get(i).getWeather().get(0).getMain());

            tv = (TextView) findViewById(r.getIdentifier("day" + i + "_date", "id", name));
            tv.setText(dateFormat.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_YEAR, 1);

            tv = (TextView) findViewById(r.getIdentifier("day" + i + "_description", "id", name));
            tv.setText(forecastResponse.getList().get(i).getWeather().get(0).getDescription());

            tv = (TextView) findViewById(r.getIdentifier("day" + i + "_temp", "id", name));
            tv.setText(R.string.max_temp);
            tv.append(String.format(Locale.getDefault(), "%.2f", forecastResponse.getList().get(i).getTemp().getMax()));
            tv.append(" \u2103");

            iv = (ImageView) findViewById(r.getIdentifier("day" + i + "_imageView", "id", name));
            String iconpath = "http://openweathermap.org/img/w/" + forecastResponse.getList().get(i).getWeather().get(0).getIcon() + ".png";
            Log.e(TAG, "IconPath: : " + iconpath);
            Picasso.with(this.getApplicationContext()).load(iconpath).into(iv);
        }
    }

    private void fillValues(WeatherResponse weatherResponse) {
        city_value_tv.setText(weatherResponse.getName());
        country_value_tv.setText(weatherResponse.getSys().getCountry());
        weather_value_tv.setText(weatherResponse.getWeather().get(0).getMain());
        String iconpath = "http://openweathermap.org/img/w/" + weatherResponse.getWeather().get(0).getIcon() + ".png";
        Log.e(TAG, "IconPath: : " + iconpath);
        Picasso.with(this.getApplicationContext()).load(iconpath).into(weather_iv);
        weather_detail_value.setText(weatherResponse.getWeather().get(0).getDescription());
        temp_value_tv.setText(String.format(Locale.getDefault(), "%.2f", weatherResponse.getMain().getTemp()));
        temp_value_tv.append( " ℃");
        temp_max_value_tv.setText(String.format(Locale.getDefault(), "%.2f", weatherResponse.getMain().getTempMax()));
        temp_max_value_tv.append( " ℃");
        temp_min_value_tv.setText(String.format(Locale.getDefault(), "%.2f", weatherResponse.getMain().getTempMin()));
        temp_min_value_tv.append( " ℃");
        pressure_value_tv.setText(String.format(Locale.getDefault(), "%.2f", weatherResponse.getMain().getPressure()));
        pressure_value_tv.append( " hPa");
        humidity_value_tv.setText(String.format(Locale.getDefault(), "%.2f", weatherResponse.getMain().getHumidity()));
        humidity_value_tv.append("%");
        visibility_val.setText(String.format(Locale.getDefault(), "%.2f", weatherResponse.getVisibility()));
        visibility_val.append(" m");
        wind_speed_value_tv.setText(String.format(Locale.getDefault(), "%.2f", weatherResponse.getWind().getSpeed()));
        wind_speed_value_tv.append(" m/s");
        wind_dir_value_tv.setText(String.format(Locale.getDefault(), "%.2f", weatherResponse.getWind().getDeg()));
        wind_dir_value_tv.append(" \u00b0");
        clouds_value_tv.setText(String.format(Locale.getDefault(), "%.2f", weatherResponse.getClouds().getAll()));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_FINE_LOCATION:
                boolean granted = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (granted) {
                    refreshWeather();
                } else {
                    Toast.makeText(getApplicationContext(), "Location Access Denied!", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /* Request updates at startup */
    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(bestLocationProvider, 400, 1, this);
    }

    @Override
    public void onLocationChanged(Location location) {
        refreshWeather();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        refreshWeather();
    }

    @Override
    public void onProviderEnabled(String provider) {
        refreshWeather();
    }

    @Override
    public void onProviderDisabled(String provider) {
        refreshWeather();
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }


}
