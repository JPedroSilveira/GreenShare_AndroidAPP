package com.si.greenshare.activity.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.si.greenshare.AuthHelper;
import com.si.greenshare.R;
import com.si.greenshare.activity.LoginActivity;
import com.si.greenshare.communication.WSAddress;
import com.si.greenshare.communication.WSUser;
import com.si.greenshare.helpers.IsHelperActivity;
import com.si.greenshare.pojo.Address;
import com.si.greenshare.pojo.City;
import com.si.greenshare.pojo.Country;
import com.si.greenshare.pojo.State;
import com.si.greenshare.pojo.User;
import com.si.greenshare.pojo.enumeration.AddressType;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RegisterStepFourActivity extends IsHelperActivity {

    private AuthHelper authHelper;
    private Spinner spCountry, spState, spCity, spAddressType;
    private Button btNext, btSkip;
    private EditText etNumber, etNeighborhood, etAddressName;
    private User user;
    private City city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step_four);
        startComponents();
        getUserOfBundleContent();
        getCountries();
    }

    private void startButtons() {
        this.btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Address address = setAddress();
                if(address != null){
                    user.setAddress(address);
                    toRegisterConfirmActivity();
                }
            }
        });
        this.btSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setAddress(null);
                toRegisterConfirmActivity();
            }
        });
        this.spCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cleanStates();
                cleanCities();
                Country country = (Country)adapterView.getItemAtPosition(i);
                getStates(country);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                showToast(getApplicationContext(), getString(R.string.country_not_selected), Toast.LENGTH_SHORT);
                spCountry.setBackground(getDrawableInvalidBackgroundField());
            }
        });
        this.spState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cleanCities();
                State state = (State)adapterView.getItemAtPosition(i);
                getCities(state);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                showToast(getApplicationContext(), getString(R.string.state_not_selected), Toast.LENGTH_SHORT);
                spState.setBackground(getDrawableInvalidBackgroundField());
            }
        });
        this.spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                City city = (City)adapterView.getItemAtPosition(i);
                setCity(city);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                showToast(getApplicationContext(), getString(R.string.city_not_selected), Toast.LENGTH_SHORT);
                spState.setBackground(getDrawableInvalidBackgroundField());
            }
        });
    }

    private void getCountries() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WSUser.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        WSAddress apiService = retrofit.create(WSAddress.class);
        String authHeader = this.authHelper.getAuthentication().getAuthHeader();
        Call<List<Country>> call = apiService.findAllCountry(authHeader);
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                Context context = getApplicationContext();
                if (response.isSuccessful()){
                    showCountries(response.body());
                }
                showToast(context, getString(R.string.unexpectedErrorReturnActivity));
                toLoginActivity();
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Context context = getApplicationContext();
                showToast(context, getString(R.string.connection_error));
                cleanAllAddress();
                getCountries();
            }
        });
    }

    private void getStates(Country country) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WSUser.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        WSAddress apiService = retrofit.create(WSAddress.class);
        String authHeader = this.authHelper.getAuthentication().getAuthHeader();
        Call<List<State>> call = apiService.findAllStateByCountry(authHeader, country.getId());
        call.enqueue(new Callback<List<State>>() {
            @Override
            public void onResponse(Call<List<State>> call, Response<List<State>> response) {
                Context context = getApplicationContext();
                if (response.isSuccessful()){
                    showStates(response.body());
                }
                showToast(context, getString(R.string.unexpectedErrorReturnActivity));
                toLoginActivity();
            }

            @Override
            public void onFailure(Call<List<State>> call, Throwable t) {
                Context context = getApplicationContext();
                showToast(context, getString(R.string.connection_error));
            }
        });
    }

    private void getCities(State state) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WSUser.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        WSAddress apiService = retrofit.create(WSAddress.class);
        String authHeader = this.authHelper.getAuthentication().getAuthHeader();
        Call<List<City>> call = apiService.findAllCityByState(authHeader, state.getId());
        call.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                Context context = getApplicationContext();
                if (response.isSuccessful()){
                    showCities(response.body());
                }
                showToast(context, getString(R.string.unexpectedErrorReturnActivity));
                toLoginActivity();
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Context context = getApplicationContext();
                showToast(context, getString(R.string.connection_error));
                getCountries();
            }
        });
    }

    private void toLoginActivity() {
        Intent itLoginActivity = new Intent(this, LoginActivity.class);
        startActivity(itLoginActivity);
    }

    private void showCountries(List<Country> countries) {
        ArrayAdapter<Country> spinnerArrayAdapter = new ArrayAdapter<Country> (this, android.R.layout.simple_spinner_item, countries);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spCountry.setAdapter(spinnerArrayAdapter);
    }

    private void cleanAllAddress(){
        cleanCountries();
        cleanStates();
        cleanCities();
    }

    private void cleanCountries(){
        this.spCountry.setAdapter(null);
    }

    private void cleanStates(){
        this.spState.setAdapter(null);
    }

    private void cleanCities(){
        this.spCity.setAdapter(null);
    }

    private void showStates(List<State> states) {
        ArrayAdapter<State> spinnerArrayAdapter = new ArrayAdapter<State> (this, android.R.layout.simple_spinner_item, states);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spState.setAdapter(spinnerArrayAdapter);
    }

    private void showCities(List<City> cities) {
        ArrayAdapter<City> spinnerArrayAdapter = new ArrayAdapter<City> (this, android.R.layout.simple_spinner_item, cities);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spCity.setAdapter(spinnerArrayAdapter);
    }

    private void startComponents(){
        this.spCity = findViewById(R.id.sp_city);
        this.spState = findViewById(R.id.sp_state);
        this.spCountry = findViewById(R.id.sp_country);
        this.btNext = findViewById(R.id.bt_next);
        this.btSkip = findViewById(R.id.bt_skip);
        this.etNumber = findViewById(R.id.et_number);
        this.etNeighborhood = findViewById(R.id.et_neighborhood);
        this.etAddressName = findViewById(R.id.et_address_name);
        this.spAddressType = findViewById(R.id.sp_address_type);
        ArrayAdapter<AddressType> spinnerArrayAdapter = new ArrayAdapter<> (this, android.R.layout.simple_spinner_item, AddressType.values());
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spAddressType.setAdapter(spinnerArrayAdapter);
    }

    private void toRegisterConfirmActivity() {
        Intent itRegisterStepFourActivity = new Intent(this, RegisterConfirm.class);
        itRegisterStepFourActivity.putExtra("User", this.user);
        startActivity(itRegisterStepFourActivity);
    }

    private void setCity(City city){
        this.city = city;
    }

    private void getUserOfBundleContent() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.user = this.user = (User)getIntent().getSerializableExtra("User");
            if(isNotNull(this.user)){
                if (!user.isValidEmail() || !user.isValidName() || !user.isValidPhoneNumber()) {
                    showToast(getApplicationContext(), getString(R.string.unexpectedErrorReturnActivity), Toast.LENGTH_SHORT);
                    toStepTwoActivity();
                }
                if(!user.isValidPassword()){
                    showToast(getApplicationContext(), getString(R.string.unexpectedErrorReturnActivity), Toast.LENGTH_SHORT);
                    toStepThreeActivity();
                }
            }
            showToast(getApplicationContext(), getString(R.string.unexpectedErrorReturnActivity), Toast.LENGTH_SHORT);
            toStepThreeActivity();
        } else {
            showToast(getApplicationContext(), getString(R.string.unexpectedErrorReturnActivity), Toast.LENGTH_SHORT);
            toLoginActivity();
        }
    }

    private void toStepTwoActivity(){
        Intent itStepTwoActivity = new Intent(this, RegisterStepTwoActivity.class);
        startActivity(itStepTwoActivity);
    }

    private void toStepThreeActivity(){
        Intent itStepThreeActivity = new Intent(this, RegisterStepThreeActivity.class);
        startActivity(itStepThreeActivity);
    }

    private Address setAddress(){
        try{
            int number = Integer.parseInt(etNumber.getText().toString());
            Address address = new Address(this.city, number, etNeighborhood.getText().toString(),
                    etAddressName.getText().toString(), null, null, ((AddressType)spAddressType.getSelectedItem()).getValue());
            if(address.isValid()){
                return address;
            }
            if(!address.getCity().isValid()){
                cleanAllAddress();
                spCity.setBackground(getDrawableInvalidBackgroundField());
                spState.setBackground(getDrawableInvalidBackgroundField());
                spCountry.setBackground(getDrawableInvalidBackgroundField());
                showToast(getApplicationContext(), getString(R.string.invalid_address), Toast.LENGTH_SHORT);
            }
            if(!address.isValidNeighborhood()){
                etNeighborhood.setBackground(getDrawableInvalidBackgroundField());
                showToast(getApplicationContext(), concatStringList(address.getValidationErrors()), Toast.LENGTH_LONG);
            }
            if(!address.isValidAddressName()){
                etAddressName.setBackground(getDrawableInvalidBackgroundField());
                showToast(getApplicationContext(), concatStringList(address.getValidationErrors()), Toast.LENGTH_LONG);
            }
            if(!address.isValidAddressType()){
                spAddressType.setBackground(getDrawableInvalidBackgroundField());
                showToast(getApplicationContext(), concatStringList(address.getValidationErrors()), Toast.LENGTH_LONG);
            }
            if(!address.isValidNumber()){
                etNumber.setBackground(getDrawableInvalidBackgroundField());
                showToast(getApplicationContext(), concatStringList(address.getValidationErrors()), Toast.LENGTH_LONG);
            }
        }catch (Exception ex){
            showToast(getApplicationContext(), getString(R.string.invalid_number_address), Toast.LENGTH_LONG);
            etNumber.setBackground(getDrawableInvalidBackgroundField());
        }
        return null;
    }
}
