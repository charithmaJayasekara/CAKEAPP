package com.example.thecakewalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thecakewalk.Models.ProductDetails;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddProduct extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    Button button;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        ref = FirebaseDatabase.getInstance().getReference("ProductDetails");

        editText1 = (EditText)findViewById(R.id.pcode);
        editText2 = (EditText)findViewById(R.id.pname);
        editText3 = (EditText)findViewById(R.id.pprice);
        editText4 = (EditText)findViewById(R.id.pdescription);
        button = (Button)findViewById(R.id.addbtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String code = editText1.getText().toString();
                final String name = editText2.getText().toString();
                final String price = editText3.getText().toString();
                final String description = editText4.getText().toString();

                if (code.isEmpty()) {
                    editText1.setError("Product Code is required");
                }else if (name.isEmpty()) {
                    editText2.setError("Product Name is required");
                }else if (price.isEmpty()) {
                    editText3.setError("Product Price Number is required");
                }else if (description.isEmpty()) {
                    editText4.setError("Product Description is required");
                }else {

                    ProductDetails productDetails = new ProductDetails(code,name,price,description);
                    ref.child(code).setValue(productDetails);

                    Toast.makeText(AddProduct.this, "Successfully added", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddProduct.this, ManageProduct.class);
                    startActivity(intent);
                }
            }
        });
    }
}