package com.example.thecakewalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thecakewalk.Models.EmployeeDetails;
import com.example.thecakewalk.Models.ProductDetails;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddEmployee extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    EditText editText6;
    Button button;
    DatabaseReference ref; //firebase access class reference

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        ref = FirebaseDatabase.getInstance().getReference("EmployeeDetails"); //create path to employee details table

        //assign IDs to variables
        editText1 = (EditText)findViewById(R.id.Eid);
        editText2 = (EditText)findViewById(R.id.Ename);
        editText3 = (EditText)findViewById(R.id.Econtact);
        editText4 = (EditText)findViewById(R.id.Enic);
        editText5 = (EditText)findViewById(R.id.Eaddress);
        editText6 = (EditText)findViewById(R.id.Eposition);
        button = (Button)findViewById(R.id.addbtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //assign values to string
                final String id = editText1.getText().toString();
                final String name = editText2.getText().toString();
                final String contact = editText3.getText().toString();
                final String nic = editText4.getText().toString();
                final String address = editText5.getText().toString();
                final String position = editText6.getText().toString();

                //check if data is empty
                if (id.isEmpty()) {
                    editText1.setError("Employee ID is required");
                }else if (name.isEmpty()) {
                    editText2.setError("Employee Name is required");
                }else if (contact.isEmpty()) {
                    editText3.setError("Employee Contact Number is required");
                }else if (nic.isEmpty()) {
                    editText4.setError("Employee nic is required");
                } else if (address.isEmpty()) {
                    editText5.setError("Employee address is required");
                } else if (position.isEmpty()) {
                    editText6.setError("Employee position is required");
                }else {
                    //set strings values to model class constructor
                    EmployeeDetails employeeDetails = new EmployeeDetails(id,name,contact,nic,address,position); //creating and object from the model class
                    ref.child(id).setValue(employeeDetails); //save details to referenced table using id values

                    Toast.makeText(AddEmployee.this, "Successfully added", Toast.LENGTH_SHORT).show(); //display add success toast message

                    Intent intent = new Intent(AddEmployee.this, ManageEmployeee.class); //return to ManageEmployee
                    startActivity(intent);
                }
            }
        });
    }
}