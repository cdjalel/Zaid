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

class WarathahOutput {
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

    public WarathahOutput() {
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

    void copyHal(WarathahOutput from) {
        // from is the complete Massala where aljad is the last Sahib Fard in the list with 1/6
        // mWarathah contains only aljad + ikhwa for the other two cases where aljad part != 1/6
        ArrayList<Mirath> tmp = mWarathah;
        mWarathah = new ArrayList<>();
        int i;
        // Shallow copy of warathah as they are the same in all 3 cases (except Aljad and Ikhwa)
        for (i = 0; i < (from.mNbrFurudh - 1); i++) { mWarathah.add(from.mWarathah.get(i)); }
        // Preserve aljad and alikhwa from current massalal
        for (i = 0; i < tmp.size(); i++) { mWarathah.add(tmp.get(i)); }

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
