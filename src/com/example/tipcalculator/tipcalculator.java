package com.example.tipcalculator;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.NumberKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.text.*;

public class tipcalculator extends Activity implements OnClickListener {
	private EditText checkAmount;
	private EditText numberOfPeople;
	private EditText tip;
	private TextView totalBill;
	private TextView totalPerPerson;
	private TextView totalTip;
	private TextView tipPerPerson;
	private Button button;
	private Button button2;
	private Button button3;
	private static final String tag = "Tip Calculator";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle foo) {
		super.onCreate(foo);
		setContentView(R.layout.main);

		checkAmount = (EditText) findViewById(R.id.checkAmount);
		numberOfPeople = (EditText) findViewById(R.id.numberOfPeople);
		tip = (EditText) findViewById(R.id.tip);

		// Remove on screen keyboard, requires hard keyboard for input
		numberOfPeople.setInputType(0);
		checkAmount.setInputType(0);
		tip.setInputType(0);

		checkAmount.setKeyListener(new NumberKeyListener() {
			public int getInputType() {
				return InputType.TYPE_NULL;
			}

			@Override
			protected char[] getAcceptedChars() {
				char[] numberChars = { '1', '2', '3', '4', '5', '6', '7', '8',
						'9', '0', '.' };
				return numberChars;
			}
		});
		numberOfPeople.setKeyListener(new NumberKeyListener() {

			public int getInputType() {
				return InputType.TYPE_NULL;
			}

			@Override
			protected char[] getAcceptedChars() {
				char[] numberChars = { '1', '2', '3', '4', '5', '6', '7', '8',
						'9', '0', '.' };
				return numberChars;
			}
		});

		tip.setKeyListener(new NumberKeyListener() {
			public int getInputType() {
				return InputType.TYPE_NULL;
			}

			@Override
			protected char[] getAcceptedChars() {
				char[] numberChars = { '1', '2', '3', '4', '5', '6', '7', '8',
						'9', '0', '.' };
				return numberChars;
			}
		});

		totalBill = (EditText) findViewById(R.id.totalBill);
		totalBill.setFocusable(false);
		totalPerPerson = (EditText) findViewById(R.id.totalPerPerson);
		totalPerPerson.setFocusable(false);
		totalTip = (EditText) findViewById(R.id.totalTip);
		totalTip.setFocusable(false);
		tipPerPerson = (EditText) findViewById(R.id.tipPerPerson);
		tipPerPerson.setFocusable(false);

		button = (Button) findViewById(R.id.calculate);
		button.setOnClickListener(this);
		button2 = (Button) findViewById(R.id.web);
		button2.setOnClickListener(this);
		button3 = (Button) findViewById(R.id.dialer);
		button3.setOnClickListener(this);

	}

	// Perform action on click
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.calculate:
			// grab the check amount
			try {
				String check = checkAmount.getText().toString();
				Log.i(tag, "checkAmount is:$ " + check);

				// grab the number of people
				String numPeople = numberOfPeople.getText().toString();
				Log.i(tag, "The Number of People: " + numPeople);

				// grab the tip percent
				String tipAmount = tip.getText().toString();
				Log.i(tag, "Tip: " + tipAmount + '%');

				// Remove $ from checkAmount String if present
				if (check.indexOf("$") != -1) {
					check = check.substring(1);
				}

				float foo = Float.parseFloat(check);
				float bar = Float.parseFloat(tipAmount) / 100;
				float barfoo = Float.parseFloat(numPeople);

				// calculate total tip
				bar = bar * foo;
				Log.i(tag, "Total Tip is $" + bar);
				String tBone = String.format("$%.2f", bar);
				totalTip.setText(tBone);

				// Calculate bill total including tip
				foo = foo + bar;
				Log.i(tag, "Total Bill is $" + foo);
				tBone = String.format("$%.2f", foo);
				totalBill.setText(tBone);

				// calculate individual bill
				foo = foo / barfoo;
				Log.i(tag, "Total Bill Per Person is $" + foo);
				tBone = String.format("$%.2f", foo);
				totalPerPerson.setText(tBone);

				// calculate total tip per person
				bar = bar / barfoo;
				Log.i(tag, "Total Tip per person is $" + bar);
				tBone = String.format("$%.2f", bar);
				tipPerPerson.setText(tBone);

				Log.i(tag, "onClick complete.");

			} catch (Exception e) {
				Log.e(tag, "Failed to Calculate Tip:" + e.getMessage());
				e.printStackTrace();
				totalBill.setText("Invalid");
				totalTip.setText("Invalid");
				totalPerPerson.setText("Invalid");
				tipPerPerson.setText("Invalid");
				Toast toast = Toast
						.makeText(
								getApplicationContext(),
								"Please enter valid numerical values for Check Amount, Number of People, and for the Tip Percentage.",
								Toast.LENGTH_LONG);
				toast.show();
			}
			break;
		case R.id.dialer:
			Uri dialme = Uri.parse("tel:2076297932");
			Intent ca = new Intent(Intent.ACTION_CALL, dialme);
			startActivity(ca);
			break;
		case R.id.web:
			Intent br = new Intent(this, browser.class);
			startActivity(br);
			break;

		}
	}
}
