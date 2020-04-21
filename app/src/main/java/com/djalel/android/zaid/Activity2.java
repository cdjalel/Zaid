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
    NumberPicker sonsNP;
    NumberPicker daughtersNP;
    NumberPicker sonsOfSonsNP;
    NumberPicker daughtersOfSonsNP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        ZaidApplication app = (ZaidApplication) this.getApplication();
        mInput = app.getWarathaInput();

        // update layout

        sonsNP = findViewById(R.id.sonsNumberPicker);
        sonsNP.setMinValue(0);
        sonsNP.setMaxValue(50);
        sonsNP.setValue(mInput.get_alabna());
        sonsNP.setWrapSelectorWheel(false);
        sonsNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mInput.set_alabna(newVal);
                }
        });

        daughtersNP = findViewById(R.id.daughtersNumberPicker);
        daughtersNP.setMinValue(0);
        daughtersNP.setMaxValue(50);
        daughtersNP.setValue(mInput.get_albanat());
        daughtersNP.setWrapSelectorWheel(false);
        daughtersNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

		    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mInput.set_albanat(newVal);
		    }
	    });

        sonsOfSonsNP = findViewById(R.id.sonsOfSonsNumberPicker);
        sonsOfSonsNP.setMinValue(0);
        sonsOfSonsNP.setMaxValue(50);
        sonsOfSonsNP.setValue(mInput.get_abna_alabna());
        sonsOfSonsNP.setWrapSelectorWheel(false);
        sonsOfSonsNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mInput.set_abna_alabna(newVal);
            }
        });

        daughtersOfSonsNP = findViewById(R.id.daughtersOfSonsNumberPicker);
        daughtersOfSonsNP.setMinValue(0);
        daughtersOfSonsNP.setMaxValue(50);
        daughtersOfSonsNP.setValue(mInput.get_banat_alabna());
        daughtersOfSonsNP.setWrapSelectorWheel(false);
        daughtersOfSonsNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mInput.set_banat_alabna(newVal);
            }
        });
    }

    public void onNextClicked(View view) {
        // start next activity
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);
    }
}
