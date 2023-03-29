package com.example.basicunitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //declare each element type and assign variable names
    EditText inputText;
    TextView subHeading1;

    TextView subHeading3;
    TextView outputText;

    Spinner spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //use findViewByid to assign the variables to their respective ids in the XML
        inputText = findViewById(R.id.inputTextField);

        subHeading1 = findViewById(R.id.subHeading1);
        subHeading3 = findViewById(R.id.subHeading3);

        outputText = findViewById(R.id.outputText);

        spinner = findViewById(R.id.spinner);

        //array of values to be options for the spinner
        String[] spinnerValues = {"Inch to CM", "Pound to KG", "Celsius to Fahrenheit"};
        //creates an array adapter to take the strings from spinner values
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinnerValues);
        //uses the created adapter to set the spinner values
        spinner.setAdapter(spinnerAdapter);



        //set on item selected listener (like on click listener for button from lecture but for spinner selection)
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            //using adapter view (imported), documentation at https://developer.android.com/reference/android/widget/AdapterView.OnItemSelectedListener?authuser=1
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                // Get the selected item from the spinner
                String selectedItem = parent.getItemAtPosition(position).toString();

                // Perform different actions based on the selected item (in this case updates input fields to match their corresponding measurements, and deletes any previous result)
                if (selectedItem.equals("Inch to CM"))
                {
                    subHeading1.setText("Inch (Input)");
                    subHeading3.setText("CM (Result)");
                    outputText.setText("");
                }
                if (selectedItem.equals("Pound to KG"))
                {
                    subHeading1.setText("Pound (Input)");
                    subHeading3.setText("KG (Result)");
                    outputText.setText("");
                }
                if (selectedItem.equals("Celsius to Fahrenheit"))
                {
                    subHeading1.setText("Celsius (Input)");
                    subHeading3.setText("Fahrenheit (Result)");
                    outputText.setText("");
                }
            }
            //Must have function for nothing selected. In this case this does nothing
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


        }


    //function for calculate button, declared as onclick in xml
    public void calculate(View view)
        {
        double conversionResult;

        String conversionType;
        //Get the type of conversion by checking the heading value changed by the OnItemSelectedListener
        conversionType = subHeading1.getText().toString();
        //In try/catch block to stop program crashing if input is null
        try
        {
            double input = Double.parseDouble(inputText.getText().toString());

            if (conversionType == "Inch (Input)")
            {
                conversionResult = input * 2.54;
                //Cast double result as a string to be displayed
                String stringResult = String.valueOf(conversionResult);
                outputText.setText(stringResult);

            }
            else if (conversionType == "Pound (Input)")
            {
                conversionResult = input * 0.453592;
                String stringResult = String.valueOf(conversionResult);
                outputText.setText(stringResult);
            }
            else if (conversionType == "Celsius (Input)")
            {
                conversionResult = (input * 1.8) + 32;
                String stringResult = String.valueOf(conversionResult);
                outputText.setText(stringResult);
            }
            //Do not think possible for user to calculate without selecting a measurement, but implemented in case
            else
            {
                outputText.setText("UNKNOWN ERROR");
            }
        }
        //if the parse double fails, nothing happens
        catch (NumberFormatException x)
        {}


    }
}