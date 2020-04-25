/*
 * Copyright © 2019-2020 Djalel Chefrour
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

import android.app.Application;

public class ZaidApplication extends Application {
    WarathaInput mInput = new WarathaInput();
    Massala massala;

    // THIS IS A SINGLETON

    public WarathaInput getWarathaInput() { return mInput; }

    public Massala getMassala() { return massala; }

    public String hissabMawarith() {
        massala = new Massala();
        massala.hissabMawarith(mInput);
        return massala.getSharh();
    }

}
