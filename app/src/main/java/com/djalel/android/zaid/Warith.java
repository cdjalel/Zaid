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

public enum Warith {
    // order matters
    ALAB ("الأب", "الأب", "الأب", "أب"),
    ALOM ("الأم", "الأم", "الأم", "أم"),
    ALJAD ("الجد", "الجد", "الجد", "جد"),
    ALJADAH_LI_AB ("الجدة لأب", "الجدة لأب", "الجدة لأب", "جدة ب"),
    ALJADAH_LI_OM ("الجدة لأم", "الجدة لأم", "الجدة لأم", "جدة م"),
    AZAWJ ("الزوج", "الزوج", "الزوج", "زوج"),
    AZAWJAT ("الزوجة", "الزوجتان", "الزوجات", "زوجة"),
    ALABNA ("الابن", "الإبنان", "الأبناء", "ابن"),
    ALBANAT ("البنت", "البنتان", "البنات", "بنت"),
    ABNA_ALABNA ("ابن الابن", "ابني الابن", "أبناء الأبناء", "ابن ابن"),
    BANAT_ALABNA ("بنت الابن", "بنتي الابن", "بنات الأبناء", "بنت ابن"),
    ALIKHWA_LI_OM ("أخ لأم", "الأخوان لأم", "الإخوة لأم", "خ م"),
    ALAKHAWAT_LI_OM ("أخت لأم", "الأختان لأم", "الأخوات لأم", "حت م"),
    ALIKHWA_ALASHIKA ("الأخ شقيق", "الأخوان الشقيقان", "الإخوة الأشقاء", "خ ش"),
    ALAKHAWAT_ASHAKIKAT ("الأحت الشقيقة", "الأختان الشقيقتان", "الأخوات الشقيقات", "خت ش"),
    ALIKHWA_LI_AB ("الأخ لأب", "الأخوان لأب", "الإخوة لأب", "خ ب"),
    ALAKHAWAT_LI_AB ("الأخت لأب", "الأختان لأب", "الأخوات لأب", "خت ب"),
    ABNA_ALIKHWA_ALASHIKA ("ابن أخ شقيق", "ابني الأخ الشقيق", "أبناء الإخوة الأشقاء", "ابن خ ش"),
    ABNA_ALIKHWA_LI_AB ("ابن أخ لأب", "ابني الأخ لأب", "أبناء الإخوة لأب", "ابن خ ب"),
    ALA3MAM_ALASHIKA ("العم الشقيق", "العمان الشقيقان", "الأعمام الأشقاء", "عم ش"),
    ALA3MAM_LI_AB ("العم لأب", "العمان لأب", "الأعمام لأب", "عم ب"),
    ABNA_ALA3MAM_ALASHIKA ("ابن العم الشقيق", "ابني العم الشقيق", "أبناء الأعمام الأشقاء", "ابن عم ش"),
    ABNA_ALA3MAM_LI_AB ("ابن العم لأب", "ابني العم لأب", "أبناء الأعمام لأب", "ابن عم ب");

    private final String mofrad;
    private final String mothana;
    private final String djam3;
    private final String short_name;


    Warith(String mofrad, String mothana, String djam3, String short_name) {
        this.mofrad = mofrad;
        this.mothana = mothana;
        this.djam3 = djam3;
        this.short_name = short_name;
    }

    public String getName() { return mofrad; }
    public String getName(int nbr) { return nbr == 1 ? mofrad : (nbr == 2 ? mothana : djam3); }
    public String getShortName() { return short_name; }
}
