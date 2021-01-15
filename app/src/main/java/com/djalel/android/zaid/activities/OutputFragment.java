/*
 * Copyright © 2019-2020 Djalel Chefrour and the Zaid project contributors.
 * This file is part of Zaid project.
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

package com.djalel.android.zaid.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.djalel.android.zaid.R;
import com.djalel.android.zaid.ZaidApplication;
import com.djalel.libjfarayid.Massala;
import com.djalel.libjfarayid.Mirath;

import java.util.ArrayList;
import java.util.Locale;

public class OutputFragment extends Fragment {

    private TableLayout mResultTableLayout;
    private TextView mResultTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_output, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mResultTextView = view.findViewById(R.id.resultTextView);
        mResultTableLayout = view.findViewById(R.id.resultTableLayout);

        view.findViewById(R.id.button_restart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ZaidApplication app = ZaidApplication.getApplication();
                app.getWarathaInput().resetWarathahInput();

                NavHostFragment.findNavController(OutputFragment.this)
                        .navigate(R.id.action_OutputFragment_to_InputFragment);
            }
        });

        view.findViewById(R.id.button_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(OutputFragment.this)
                        .navigate(R.id.action_OutputFragment_to_InputFragment);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        //reinitialisation
        mResultTableLayout.removeAllViews();

        ZaidApplication app = ZaidApplication.getApplication();
        mResultTextView.setText(app.hissabMawarith());
        createTable(app);
    }

    private void createTable(ZaidApplication app) {
        Massala massala = app.getMassala();
        ArrayList<Mirath> mawarith = massala.getMawarith();

        if (mawarith.isEmpty()) { return; }

        // table head
        double tarika = app.getWarathaInput().getTarika();
        TableRow row = new TableRow(getActivity());
        int col = 2;
        TextView tvAsl;
        if (massala.getAwl() != 0) {
            //row of awl
            row.addView(createHeadCell(String.valueOf(massala.getAwl()), false), new TableRow.LayoutParams(col));
            mResultTableLayout.addView(row);

            //row of asl
            row = new TableRow(getActivity());
            tvAsl = createHeadAwlCell(massala.getAsl());
        } else {
            tvAsl = createHeadCell(String.valueOf(massala.getAsl()), false);
        }
        row.addView(tvAsl, new TableRow.LayoutParams(col++));

        boolean hissabFardiColumn = massala.isHissabFardi();
        if (hissabFardiColumn) {
            row.addView(createHeadCell(String.valueOf(massala.getMissah()), false), new TableRow.LayoutParams(col++));
        }
        row.addView(createHeadCell(String.format(Locale.ROOT, "%.2f", tarika), true), new TableRow.LayoutParams(col));
        mResultTableLayout.addView(row);

        // table body
        boolean jadahFirst = true;
        boolean shirkatTa3seebFirst = true;
        boolean waladAlomFirst = true;
        boolean last_row = false;
        int i = 0;
        for (Mirath m : mawarith) {
            row = new TableRow(getActivity());
            if (++i == mawarith.size()) { last_row = true; }

            // columns 1 & 2
            row.addView(createCell(m.getHokom(), false, last_row));
            row.addView(createCell(m.getIsm(), false, last_row));

            // column 3: nassib mojmal
            if (m.isJadah() && m.isShirka()) {
                if (jadahFirst) {
                    row.addView(createCell(m.getNassibMojmal() + " ↓", false, last_row));
                    jadahFirst = false;
                } else {
                    row.addView(createEmptyCell(last_row));
                }
            } else if (m.isWaladAlom() && massala.isJinsayAwladAlom()) {
                if (waladAlomFirst) {
                    row.addView(createCell(m.getNassibMojmal() + " ↓", false, last_row));
                    waladAlomFirst = false;
                } else {
                    row.addView(createEmptyCell(last_row));
                }
            } else if (m.isTa3seeb() && massala.isShirkaTa3seeb()) {
                if (shirkatTa3seebFirst) {
                    row.addView(createCell(m.getNassibMojmal() + " ↓", false, last_row));
                    shirkatTa3seebFirst = false;
                } else {
                    row.addView(createEmptyCell(last_row));
                }
            } else {
                row.addView(createCell(m.getNassibMojmal(), false, last_row));
            }

            // column 4: nassib fardi
            if (hissabFardiColumn) {
                row.addView(createCell(m.getNassibFardi(), false, last_row));
            }

            // column 5: nassib fardi mina tarika
            StringBuilder nassibTarikaStr = new StringBuilder();
            nassibTarikaStr.append(String.format(Locale.ROOT, "%.2f", m.getNassib() * tarika / massala.getMissah()));
            if (m.getNbr() > 1) { nassibTarikaStr.append("(*").append(m.getNbr()).append(")");}
            row.addView(createCell(nassibTarikaStr, true, last_row));

            mResultTableLayout.addView(row);
        }
    }

    private TextView createHeadCell(CharSequence txt, boolean last_column) {
        TextView head = prepareTextView();

        head.setBackgroundResource((last_column)? R.drawable.last_head_borders : R.drawable.head_borders);
        head.setText(txt);
        return head;
    }

    private TextView createHeadAwlCell(int awl) {
        TextView head = prepareTextView();

        head.setBackgroundResource((awl < 10) ? R.drawable.head_awl1 : R.drawable.head_awl2);
        head.setText(String.valueOf(awl));
        return head;
    }

    private TextView createCell(CharSequence txt, boolean last_column, boolean last_row) {
        TextView cell = prepareTextView();

        if (last_row) {
            cell.setBackgroundResource((last_column)? R.drawable.last_row_and_column_borders : R.drawable.last_row_borders);
        } else {
            cell.setBackgroundResource((last_column)? R.drawable.last_column_borders : R.drawable.cell_borders);
        }
        cell.setText(txt);
        return cell;
    }

    private TextView createEmptyCell(boolean last_row) {
        TextView cell = prepareTextView();

        cell.setBackgroundResource(last_row ? R.drawable.last_row_shirka_borders : R.drawable.shirka_cell_borders);
        cell.setText("");
        return cell;
    }

    private TextView prepareTextView() {
        TextView tv = new TextView(getActivity());

        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.font_size));
        tv.setTextColor(Color.BLACK);
        tv.setTextDirection(View.TEXT_DIRECTION_RTL);
        tv.setGravity(Gravity.START);
        return tv;
    }
}
