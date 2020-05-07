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

package com.djalel.libjfarayid;

import java.util.ArrayList;

class WarathaOuput {
    ArrayList<Mirath> mWarathah;
    ArrayList<Mirath> mMahjoobin;

    boolean mTassawi;           // تعصيب بالتساوي أو للذكر مثل حظ الأنثيين
    boolean mIstighraq;
    boolean mShirkaTa3seeb;     // اشتراك عدة ورثة مختلفين في الباقي (جد وإخوة أو أبناء وبنات)
    boolean mMu3addah;

    int mNbrFurudh;
    int mNbr3assabat;
    int mAsl;
    int mBaqi;
    int mRo2osAlbaqi;
    int mRo2osBaqiAlbaqi;
    int mAwl;
    int mMissah;

    Naw3 mNaw3;

    public WarathaOuput() {
        mWarathah = new ArrayList<>();
        mMahjoobin = new ArrayList<>();

        mTassawi = true;
        mIstighraq = false;
        mShirkaTa3seeb = false;
        mMu3addah = false;

        mNbrFurudh = 0;
        mNbr3assabat = 0;
        mAsl = 1;
        mBaqi = 0;
        mRo2osAlbaqi = 1;
        mRo2osBaqiAlbaqi = 1;
        mAwl = 0;
        mMissah = 0;

        mNaw3 = Naw3.NAW3_NONE;
    }

    void copyHal(WarathaOuput from) {
        // except Aljad and ikhwa, shallow copy of waratah as they are the same in all cases
        ArrayList<Mirath> tmp = (ArrayList<Mirath>)mWarathah.clone();
        mWarathah = (ArrayList<Mirath>)from.mWarathah.clone();
        // replace aljad & ikhwa
        for (int i = 0; i < tmp.size(); i++) {
            mWarathah.remove(from.mNbrFurudh + i - 1);
            mWarathah.add(from.mNbrFurudh + i - 1, tmp.get(i));
        }
        mMahjoobin = from.mMahjoobin;    // reference copy as hajb is the same for all cases

        mNbrFurudh = from.mNbrFurudh - 1;
        mNbr3assabat = from.mNbr3assabat + 1;
        mTassawi = from.mTassawi;

        // rest is unique to each case DO NOT COPY
//            mShirkaTa3seeb = from.mShirkaTa3seeb;
//            mMu3addah = from.mMu3addah;
//            mIstighraq = from.mIstighraq;

//            mAsl = 1;
//            mBaqi = 0;
//            mRo2osAlbaqi = 1;
//            mRo2osBaqiAlbaqi = 1;
//            mAwl = 0;
//            mMissah = 0;
    }

    public boolean isInkissar() { return mAwl != 0 ? mAwl != mMissah : mAsl != mMissah; }
}
