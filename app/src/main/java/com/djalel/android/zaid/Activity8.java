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

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Activity8 extends AppCompatActivity {

    LinearLayout mPrincipalLayout;
    LinearLayout mButtonsLayout;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_8);

        mResultTextView = (TextView) findViewById(R.id.resultTextView);
        mResultTextView.setMovementMethod(new ScrollingMovementMethod());
        mPrincipalLayout = (LinearLayout) findViewById(R.id.principalLayout);
        mButtonsLayout = (LinearLayout) findViewById(R.id.buttonsLayout);
//        resultTextView.setTypeface(Typeface.MONOSPACE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //reinitialisation
        mPrincipalLayout.removeAllViews();
        mPrincipalLayout.addView(mResultTextView);

        ZaidApplication app = (ZaidApplication) this.getApplication();
        mResultTextView.setText(app.hissabMawarith());
        mPrincipalLayout.addView(printTable(app));

        mPrincipalLayout.addView(mButtonsLayout);
    }

    public void onRestartClicked(View view) {
        ZaidApplication app = (ZaidApplication) this.getApplication();
        app.getWarathaInput().resetWarathahInput();

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }

    public void onChangeMassalaClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public TableLayout printTable(ZaidApplication app){
        TableLayout mawarithTable = new TableLayout(this);
        mawarithTable.setStretchAllColumns(true);
        mawarithTable.setShrinkAllColumns(true);

        TableLayout.LayoutParams lp = new TableLayout.LayoutParams();
        lp.setMargins(30,0,30, 60);
        mawarithTable.setLayoutParams(lp);

        TableRow r;
        Massala massala = app.getMassala();
        if (massala.getAwl() != 0) {
            //row of awl
            r = new TableRow(this);
            r.addView(createHeadTextView(String.valueOf(massala.getAwl())),0);
            r.addView(createHeadTextView(""),0);
            mawarithTable.addView(r);

            //row of asl
            r = new TableRow(this);
            r.addView(createHeadTextView(massala.getAsl() + " عول"),0);
            r.addView(createHeadTextView("كل فرد"),0);
            mawarithTable.addView(r);
        } else {
            r = new TableRow(this);
            r.addView(createHeadTextView(String.valueOf(massala.getAsl())),0);
            r.addView(createHeadTextView("نصيب كل فرد"),0);
            mawarithTable.addView(r);
        }

        for (Mirath m : massala.getMawarith()) {
            r = new TableRow(this);
            r.addView(createCellTextView(m.getNassibFardi()));
            r.addView(createCellTextView(m.getNassibMojmal()));
            r.addView(createCellTextView(m.getIsm()));
            r.addView(createCellTextView(m.getHokom()));
            mawarithTable.addView(r);
        }

        return mawarithTable;
    }

    private TextView createHeadTextView(CharSequence txt) {
        TextView head = new TextView(this);

        head.setTextSize(20);
        head.setTextColor(Color.BLACK);
        head.setGravity(Gravity.RIGHT);
        head.setBackgroundColor(Color.rgb(177, 177, 177));
        head.setTextDirection(View.TEXT_DIRECTION_RTL);
        head.setText(txt);

        return head;
    }

    private TextView createCellTextView(CharSequence txt) {
        TextView cell = new TextView(this);

        cell.setTextSize(20);
        cell.setTextColor(Color.BLACK);
        cell.setGravity(Gravity.RIGHT);
        cell.setBackgroundColor(Color.rgb(225, 225, 225));
        cell.setTextDirection(View.TEXT_DIRECTION_RTL);
        cell.setText(txt);

        return cell;
    }
    private TextView createCellTextView(int n) {
            return createCellTextView("" + n);
    }
}