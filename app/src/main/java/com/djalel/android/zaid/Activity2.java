/*
 * Copyright © 2019 Djalel Chefrour
 * This file is part of Zaid.
 *
 * Zaid is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Zaid is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 *  along with Zaid.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.djalel.android.zaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    WarathaInput mInput;
    NumberPicker zawjatNP;
    NumberPicker sonsNP;
    NumberPicker daughtersNP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        ZaidApplication app = (ZaidApplication) this.getApplication();
        mInput = app.getWarathaInput();

        // read default values from layout
        RadioGroup radioGroup;
        radioGroup = (RadioGroup) findViewById(R.id.radioGroupZawj);
        zawjatNP = (NumberPicker) findViewById(R.id.zawjatNumberPicker);

        if (mInput.is_male()) {
            radioGroup.setEnabled(false);
            for(int i = 0; i < radioGroup.getChildCount(); i++) {
                ((RadioButton)radioGroup.getChildAt(i)).setEnabled(false);
            }
            ((TextView) findViewById(R.id.zawjTextView)).setEnabled(false);

            ((TextView) findViewById(R.id.zawjatTextView)).setEnabled(true);
            zawjatNP.setEnabled(true);
            zawjatNP.setValue(0);
            zawjatNP.setMinValue(0);
            zawjatNP.setMaxValue(4);
            zawjatNP.setWrapSelectorWheel(false);
            zawjatNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    mInput.set_azawjat(newVal);
                }
            });
        }
        else {
            zawjatNP.setEnabled(false);
            ((TextView) findViewById(R.id.zawjatTextView)).setEnabled(false);

            ((TextView) findViewById(R.id.zawjTextView)).setEnabled(true);
            radioGroup.setEnabled(true);
            for(int i = 0; i < radioGroup.getChildCount(); i++) {
                ((RadioButton)radioGroup.getChildAt(i)).setEnabled(true);
            }
            onZawjButtonClicked(radioGroup.findViewById(radioGroup.getCheckedRadioButtonId()));
        }

        sonsNP = (NumberPicker) findViewById(R.id.sonsNumberPicker);
        sonsNP.setValue(0);
        sonsNP.setMinValue(0);
        sonsNP.setMaxValue(50);
        sonsNP.setWrapSelectorWheel(false);
        sonsNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mInput.set_alabna(newVal);
                }
        });

        daughtersNP = (NumberPicker) findViewById(R.id.daughtersNumberPicker);
        daughtersNP.setValue(0);
        daughtersNP.setMinValue(0);
        daughtersNP.setMaxValue(50);
        daughtersNP.setWrapSelectorWheel(false);
        daughtersNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

		    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mInput.set_albanat(newVal);
		    }
	    });
    }

    public void onZawjButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioButtonZawjYes:
                if (checked)
                    mInput.set_zawj(true);
                break;

            case R.id.radioButtonZawjNo:
                if (checked)
                    mInput.set_zawj(false);
                break;
        }
    }

    public void onNextClicked(View view) {
        // start next activity
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);
    }
}
