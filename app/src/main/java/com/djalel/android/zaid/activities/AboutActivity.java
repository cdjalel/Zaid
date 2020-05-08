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

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.djalel.android.zaid.R;

public class AboutActivity extends Activity {
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        WebView view = findViewById(R.id.about_webview);
        view.loadData(getString(R.string.about), "text/html; charset=utf-8", "utf-8");
    }
}
