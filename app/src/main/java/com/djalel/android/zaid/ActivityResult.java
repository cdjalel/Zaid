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
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ActivityResult extends AppCompatActivity {

    private LinearLayout mResultTableLayout;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mResultTextView = findViewById(R.id.resultTextView);
        mResultTableLayout = findViewById(R.id.resultTableLayout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //reinitialisation
        mResultTableLayout.removeAllViews();

        ZaidApplication app = (ZaidApplication) this.getApplication();
        mResultTextView.setText(app.hissabMawarith());
        if (app.getMassala().getMawarith().size() != 0) {
            mResultTableLayout.addView(createTable(app));
        }
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
    public TableLayout createTable(ZaidApplication app){
        TableLayout mawarithTable = new TableLayout(this);
        mawarithTable.setStretchAllColumns(true);
        mawarithTable.setShrinkAllColumns(true);

        TableLayout.LayoutParams lp = new TableLayout.LayoutParams();
        lp.setMargins(10,10,10, 10);
        mawarithTable.setLayoutParams(lp);

        TableRow r;
        Massala massala = app.getMassala();
        if (massala.getAwl() != 0) {
            //row of awl
            r = new TableRow(this);
            r.addView(createHeadTextView(String.valueOf(massala.getAwl()), false),0);
            r.addView(createHeadTextView("", false),0);
            mawarithTable.addView(r);

            //row of asl
            r = new TableRow(this);
            r.addView(createHeadTextView(massala.getAsl() + " عول", false),0);
            r.addView(createHeadTextView(massala.getMissah() + "" /*+ "، للفرد ↓"*/, true),0);
            mawarithTable.addView(r);
        } else {
            r = new TableRow(this);
            r.addView(createHeadTextView(String.valueOf(massala.getAsl()), false),0);
            r.addView(createHeadTextView(massala.getMissah() + ""  /* + "، للفرد ↓"*/, true),0);
            mawarithTable.addView(r);
        }

        boolean jadahFirst = true;
        boolean shirkatTa3seebFirst = true;
        boolean waladAlomFirst = true;
        boolean last_row = false;
        int i = 0;
        for (Mirath m : massala.getMawarith()) {
            if (++i == massala.getMawarith().size()) {
                last_row = true;
            } /* last iteration */
            r = new TableRow(this);
            r.addView(createCellTextView(m.getNassibFardi(), true, last_row));
            if (m.isJadah() && m.isShirka()) {
                if (jadahFirst) {
                    r.addView(createCellTextView(m.getNassibMojmal() + " ↓", false, last_row));
                    jadahFirst = false;
                } else {
                    r.addView(createCellTextView("", false, last_row, true));
                }
            } else if (m.isWaladAlom() && m.isShirka()) {
                if (waladAlomFirst) {
                    r.addView(createCellTextView(m.getNassibMojmal()+" ↓", false, last_row));
                    waladAlomFirst = false;
                } else {
                    r.addView(createCellTextView("", false, last_row, true));
                }
            } else if (m.isTa3seeb() && massala.isShirkaTa3seeb()) {
                if (shirkatTa3seebFirst) {
                    r.addView(createCellTextView(m.getNassibMojmal() + " ↓", false, last_row));
                    shirkatTa3seebFirst = false;
                } else {
                    r.addView(createCellTextView("", false, last_row, true));
                }
            } else {
                r.addView(createCellTextView(m.getNassibMojmal(), false, last_row));
            }
            r.addView(createCellTextView(m.getIsm(), false, last_row));
            r.addView(createCellTextView(m.getHokom(), false, last_row));
            mawarithTable.addView(r);
        }

        return mawarithTable;
    }

    private TextView createHeadTextView(CharSequence txt, boolean last_column) {
        TextView head = prepareTextView();

//        head.setBackgroundColor(Color.rgb(177, 177, 177));
        head.setBackgroundResource((last_column)? R.drawable.last_head_borders : R.drawable.head_borders);
        head.setText(txt);

        return head;
    }

    private TextView createCellTextView(CharSequence txt, boolean last_column, boolean last_row) {
        TextView cell = prepareTextView();

        if (last_row) {
            cell.setBackgroundResource((last_column)? R.drawable.last_row_and_column_borders : R.drawable.last_row_borders);
        } else {
            cell.setBackgroundResource((last_column)? R.drawable.last_column_borders : R.drawable.cell_borders);
        }
        cell.setText(txt);

        return cell;
    }

    private TextView createCellTextView(CharSequence txt, boolean last_column, boolean last_row, boolean is_shirka) {
        TextView cell = prepareTextView();

        if (last_row) {
            cell.setBackgroundResource((is_shirka)? R.drawable.last_row_shirka_borders : R.drawable.last_row_borders);
        } else {
            cell.setBackgroundResource((is_shirka)? R.drawable.shirka_cell_borders : R.drawable.cell_borders);
        }
        cell.setText(txt);

        return cell;
    }

    private TextView prepareTextView() {
        TextView tv = new TextView(this);

        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.font_size));
        tv.setTextColor(Color.BLACK);
        tv.setGravity(Gravity.RIGHT);
        tv.setTextDirection(View.TEXT_DIRECTION_RTL);
        return tv;
    }
}
