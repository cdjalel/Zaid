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

package com.djalel.android.zaid;

import android.app.Application;

import com.djalel.libjfarayid.WarathahInput;
import com.djalel.libjfarayid.Massala;

public class ZaidApplication extends Application {
    private WarathahInput mInput = new WarathahInput();
    private Massala mMassala;

    // THIS IS A SINGLETON
    static private ZaidApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    static public ZaidApplication getApplication() { return mContext; }

    public WarathahInput getWarathaInput() { return mInput; }

    public Massala getMassala() { return mMassala; }

    public String hissabMawarith() {
        mMassala = new Massala();
        mMassala.hissabMawarith(mInput);
        return mMassala.getSharh();
    }

}
