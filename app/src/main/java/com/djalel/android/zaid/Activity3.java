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

public class Activity3 extends AppCompatActivity {

    WarathaInput mInput;
    NumberPicker sonsOfSonsNP;
    NumberPicker daughtersOfSonsNP;
    NumberPicker maternalBrothersNP;
    NumberPicker maternalSistersNP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        ZaidApplication app = (ZaidApplication) this.getApplication();
        mInput = app.getWarathaInput();

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

        maternalBrothersNP = findViewById(R.id.maternalBrothersNumberPicker);
        maternalBrothersNP.setMinValue(0);
        maternalBrothersNP.setMaxValue(50);
        maternalBrothersNP.setValue(mInput.get_alikhwa_li_om());
        maternalBrothersNP.setWrapSelectorWheel(false);
        maternalBrothersNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

		    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mInput.set_alikhwa_li_om(newVal);
		    }
	    });

        maternalSistersNP = findViewById(R.id.maternalSistersNumberPicker);
        maternalSistersNP.setMinValue(0);
        maternalSistersNP.setMaxValue(50);
        maternalSistersNP.setValue(mInput.get_alakhawat_li_om());
        maternalSistersNP.setWrapSelectorWheel(false);
        maternalSistersNP.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mInput.set_alakhawat_li_om(newVal);
            }
        });
    }


        public void onNextClicked(View view) {
        // start next activity
        Intent intent = new Intent(this, Activity4.class);
        startActivity(intent);
    }
}
