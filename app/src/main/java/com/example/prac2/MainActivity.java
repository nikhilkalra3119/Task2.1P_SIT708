package com.example.prac2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button submitButton;
    TextView resultView;
    EditText unitValueItem;
    Spinner unitSpinner;
    Spinner sourceUnitSpinner;
    Spinner destinationUnitSpinner;
    String destinationUnit = null;
    String sourceUnit = null;
    Double unitValue = null;
    String conversionType="Temperature";

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void printValues(){
        Log.v("destinationUnit", destinationUnit);
        Log.v("sourceUnit", sourceUnit);
        Log.v("unitValue", unitValue+"");
        Log.v("conversionType", conversionType+"");

    }
    public void setUnits(String conversion){
        sourceUnitSpinner=findViewById(R.id.spinner2);
        destinationUnitSpinner=findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence>adapter;
        switch(conversion) {
            case "Temperature":
                adapter=ArrayAdapter.createFromResource(this, R.array.temp, android.R.layout.simple_spinner_item);
                conversionType = "Temperature";
                break;
            case "Weight":
                adapter=ArrayAdapter.createFromResource(this, R.array.weight, android.R.layout.simple_spinner_item);
                conversionType = "Weight";
                break;
            case "Length":
                adapter=ArrayAdapter.createFromResource(this, R.array.length, android.R.layout.simple_spinner_item);
                conversionType = "Length";
                break;
            default:
                adapter=ArrayAdapter.createFromResource(this, R.array.temp, android.R.layout.simple_spinner_item);
                Toast.makeText(MainActivity.this, "Error in conversion type", Toast.LENGTH_LONG).show();
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sourceUnitSpinner.setAdapter(adapter);
        destinationUnitSpinner.setAdapter(adapter);

    }

    public Double convertTemp(Double value, String sourceUnit, String destinationUnit){
        if(sourceUnit == destinationUnit){
            return  value;
        }
        Double valueInCelsius = -0.1;
        switch(sourceUnit) {
            case "Celsius":
                valueInCelsius = value;
                break;
            case "Fahrenheit":
                valueInCelsius = (value - 32)/1.8;
                break;
            case "Kelvin":
                valueInCelsius = value - 273.15;
                break;
        }
        Double result = -1.0;
        switch(destinationUnit) {
            case "Celsius":
                result = valueInCelsius;
                break;
            case "Fahrenheit":
                result = (valueInCelsius * 1.8)+32;
                break;
            case "Kelvin":
                result = valueInCelsius + 273.15;
                break;
        }
        return result;
    }
    public Double convertLength(Double value, String sourceUnit, String destinationUnit){
        if(sourceUnit == destinationUnit){
            return  value;
        }
        Double valueInCm = -0.1;
        switch(sourceUnit) {
            case "Inch":
                valueInCm = value * 2.54;
                break;
            case "Foot":
                valueInCm = value * 30.48;
                break;
            case "Yard":
                valueInCm = value * 91.44;
                break;
            case "Mile":
                valueInCm = value * 160934;
                break;
            case "cm":
                valueInCm = value;
                break;
            case "km":
                valueInCm = value * 100000;
                break;
        }
        Double result = -1.0;
        switch(destinationUnit) {
            case "Inch":
                result = valueInCm / 2.54;
                break;
            case "Foot":
                result = valueInCm / 30.48;
                break;
            case "Yard":
                result = valueInCm / 91.44;
                break;
            case "Mile":
                result = valueInCm / 160934;
                break;
            case "cm":
                result = valueInCm;
                break;
            case "km":
                result = valueInCm / 100000;
                break;
        }
        return result;
    }
    public Double convertWeight(Double value, String sourceUnit, String destinationUnit){
        if(sourceUnit == destinationUnit){
            return  value;
        }
        Double valueInG = -0.1;
        switch(sourceUnit) {
            case "Pound":
                valueInG = value * 0.453592 * 1000;
                break;
            case "Ounce":
                valueInG = value * 28.3495;
                break;
            case "Ton":
                valueInG = value * 907.185 * 1000;
                break;
            case "g":
                valueInG = value;
                break;
            case "Kg":
                valueInG = value * 1000;
                break;
        }
        Double result = -1.0;
        switch(destinationUnit) {
            case "Pound":
                result = valueInG / 0.453592 / 1000;
                break;
            case "Ounce":
                result = valueInG / 28.3495;
                break;
            case "Ton":
                result = valueInG / 907.185 / 1000;
                break;
            case "g":
                result = valueInG;
                break;
            case "Kg":
                result = valueInG / 1000;
                break;
        }
        System.out.println(valueInG);
        System.out.println(result);
        return result;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unitSpinner=findViewById(R.id.unitSpinner);
        sourceUnitSpinner=findViewById(R.id.spinner2);
        destinationUnitSpinner=findViewById(R.id.spinner3);
        submitButton = (Button) findViewById(R.id.submitButton);
        resultView = findViewById(R.id.resultView);
        unitValueItem = findViewById(R.id.unitValue);


        //Unit Spinner
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        unitSpinner.setAdapter(adapter);

        unitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    setUnits(item.toString());
                }
                else {
                    Toast.makeText(MainActivity.this, "Invalid Input",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                setUnits("Temperature");
            }
        });
        sourceUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    sourceUnit = item.toString();
                }
                else {
                    Toast.makeText(MainActivity.this, "Invalid Source Unit",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "Invalid Source Unit",
                        Toast.LENGTH_SHORT).show();
            }
        });
        destinationUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                if (item != null) {
                    destinationUnit = item.toString();
                }
                else {
                    Toast.makeText(MainActivity.this, "Invalid Destination Unit",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(MainActivity.this, "Invalid Destination Unit",
                        Toast.LENGTH_SHORT).show();
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String value = unitValueItem.getText().toString();
                if(!isNumeric(value)){
                    Toast.makeText(MainActivity.this, "Source value is not numeric!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                unitValue = Double.parseDouble(value);
                printValues();
                Double result;
                switch(conversionType) {
                    case "Temperature":
                        result = convertTemp(unitValue, sourceUnit, destinationUnit);
                        break;
                    case "Weight":
                        result = convertWeight(unitValue, sourceUnit, destinationUnit);
                        break;
                    default:
                        result = convertLength(unitValue, sourceUnit, destinationUnit);
                        break;
                }
                    resultView.setText("Answer: " + result + " " + destinationUnit);
            }
        });

    }
}