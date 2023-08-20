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

class WarathahOutput {  // TODO public vs private fields
    ArrayList<Mirath> mWarathah;
    ArrayList<Mirath> mMahjoobin;

    boolean mTassawi;           // تعصيب بالتساوي أو للذكر مثل حظ الأنثيين
    boolean mIstighraq;
    boolean mShirkaTa3seeb;     // اشتراك عدة ورثة مختلفين في الباقي (جد وإخوة أو أبناء وبنات)
    boolean mMu3addah;
    boolean mAkdaria;

    int mNbrFurudh;
    int mNbr3assabat;
    int mAsl;
    int mAshom;
    int mBaqi;
    double mBaqiAlbaqi;
    int mRo2osAlbaqi;
    int mRo2osBaqiAlbaqi;
    int mAwl;
    int mMissah;

    int mAslRad;
    int mAslZawjia;
    int mAslJami3a;
    int mBaqiZawjia;
    int mIndexZawjia;

    Naw3 mNaw3;

    public WarathahOutput() {
        mWarathah = new ArrayList<>();
        mMahjoobin = new ArrayList<>();

        mTassawi = true;
        mIstighraq = false;
        mShirkaTa3seeb = false;
        mMu3addah = false;
        mAkdaria = false;

        mNbrFurudh = 0;
        mNbr3assabat = 0;
        mAsl = 1;
        mBaqi = 0;
        mBaqiAlbaqi = 0;
        mRo2osAlbaqi = 1;
        mRo2osBaqiAlbaqi = 1;
        mAwl = 0;
        mMissah = 0;

        mAslRad = 0;
        mAslJami3a = 0;
        mAslZawjia = 0;
        mBaqiZawjia = 0;
        mIndexZawjia = -1;

        mNaw3 = Naw3.NAW3_3ADIA;
    }

    public void copyWarathahExceptAljadWaAlikhwa(WarathahOutput from) {
        // This is kind of late copy constructor used for Aljad ma3a Alikhwa
        // 'from' is Hal al-Mas2ala till Aljad who is the last Sahib Fard in the list with 1/6
        // 'mWarathah' contains only Aljad + Ikhwa for the other two cases where aljad part != 1/6
        ArrayList<Mirath> tmp = mWarathah;
        mWarathah = new ArrayList<>();
        int i;
        // Deep copy of Warathah (except Aljad and Ikhwa) as their Mirath Hissab might change between the 3 cases
        for (i = 0; i < (from.mNbrFurudh - 1); i++) { mWarathah.add(new Mirath(from.mWarathah.get(i))); }
        // Preserve already calculated aljad and alikhwa in this Hal
        for (i = 0; i < tmp.size(); i++) { mWarathah.add(tmp.get(i)); }

        mMahjoobin = from.mMahjoobin;    // reference copy as hajb is the same for all cases

        mNbrFurudh += from.mNbrFurudh - 1;
        mNbr3assabat = from.mNbr3assabat + 1;
        mTassawi = from.mTassawi;

        // remaining fields are unique to each case DO NOT COPY
//            mShirkaTa3seeb = from.mShirkaTa3seeb;
//            mMu3addah = from.mMu3addah;
//            mIstighraq = from.mIstighraq;
//            mAkdaria = form.mAkdaria;

//            mAsl = 1;
//            mBaqi = 0;
//            mBaqiAlbaqi = 0;
//            mRo2osAlbaqi = 1;
//            mRo2osBaqiAlbaqi = 1;
//            mAwl = 0;
//            mMissah = 0;
    }

    public WarathahOutput copyWarathahExceptAhadZawjayn() {
        // deep copy of initial part of Hal
        WarathahOutput tmp = new WarathahOutput();
        for (Mirath m : this.mWarathah) {
            if (m.isAhadZawjain()) { continue; }
            tmp.mWarathah.add(new Mirath(m));
        }
        tmp.mNbrFurudh = tmp.mWarathah.size();
        return tmp;
    }

    public int getFardh(Warith w) {
        for (Mirath m : mWarathah) {
            if (w == m.getWarith()) { return m.getFardh(); }
        }
        return 0;
    }

    public boolean isInkissar() {
        switch (mNaw3) {
            case NAW3_AWL:
                return mAwl != mMissah;
            case NAW3_RAD_3ALA_WAHED:
            case NAW3_RAD_3ALA_MUTAJANISEEN:
            case NAW3_RAD_3ALA_MUKHTALIFEEN:
            case NAW3_RAD_3ALA_WAHED_ZAWJIA:
            case NAW3_RAD_3ALA_MUTAJANISEEN_ZAWJIA:
            case NAW3_RAD_3ALA_MUKHTALIFEEN_ZAWJIA:
                return mAslJami3a != mMissah;
            default:
                return mAsl != mMissah;
        }
    }

    public void saveZawjiaIndex() {
       mIndexZawjia = mWarathah.size();
    }

    public boolean isZawjia() { return  mIndexZawjia != -1; }
}
