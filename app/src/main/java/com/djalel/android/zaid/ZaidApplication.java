/*
 * Copyright © 2019 Djalel Chefrour
 * Copyright © 2020 Haihtem Guefassa
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

import java.util.ArrayList;

public class ZaidApplication extends Application {

    private Madhab madhab;
    private boolean male;
    private boolean alab;
    private boolean alom;
    private boolean aljad;
    private boolean aljadah_li_ab;
    private boolean aljadah_li_om;
    private boolean zawj;

    private int azawjat;
    private int alabna;
    private int albanat;
    private int abna_alabna;
    private int banat_alabna;
    private int alikhwa_li_om;
    private int alakhawat_li_om;
    private int alikhwa_alashika;
    private int alakhawat_ashakikat;
    private int alikhwa_li_ab;
    private int alakhawat_li_ab;
    private int abna_alikhwa_alashika;
    private int abna_alikhwa_li_ab;
    private int ala3mam_alashika;
    private int ala3mam_li_ab;
    private int abna_ala3mam_alashika;
    private int abna_ala3mam_li_ab;

    Massala massala;

    // maintained in the natural order of their keys (the order in which the enum constants are declared)
    private int asl_mas2ala;
    private int ashom;              //  مجموع أسهم أصحاب الفروض بما فيها الأم في الغراوين
    private int adad_ro2os;         // عدد رؤوس الورثة بالباقي (للذكر مثل حظ الأنثيين)
    private int awl;


    private String special_case;

    public void set_madhab(Madhab m) {
        madhab = m;
    }

    public Madhab get_madhab() {
        return madhab;
    }

    public boolean is_male() {
        return male;
    }

    public void set_male(boolean b) {
        male = b;
    }

    public boolean has_alab() {
        return alab;
    }

    public void set_alab(boolean b) {
        alab = b;
    }

    public boolean has_alom() {
        return alom;
    }

    public void set_alom(boolean b) {
        alom = b;
    }

    public boolean has_aljad() {
        return aljad;
    }

    public void set_aljad(boolean b) {
        aljad = b;
    }

    public boolean has_aljadah_li_ab() {
        return aljadah_li_ab;
    }

    public void set_aljadah_li_ab(boolean b) {
        aljadah_li_ab = b;
    }

    public boolean has_aljadah_li_om() {
        return aljadah_li_om;
    }

    public void set_aljadah_li_om(boolean b) {
        aljadah_li_om = b;
    }

    public boolean has_zawj() {
        return zawj;
    }

    public void set_zawj(boolean b) {
        zawj = b;
    }

    public void set_azawjat(int n) {
        azawjat = n;
    }

    public int get_azawjat() {
        return azawjat;
    }

    public void set_alabna(int n) {
        alabna = n;
    }

    public int get_alabna() {
        return alabna;
    }

    public void set_albanat(int n) {
        albanat = n;
    }

    public int get_albanat() {
        return albanat;
    }

    public void set_abna_alabna(int n) {
        abna_alabna = n;
    }

    public int get_abna_alabna() {
        return abna_alabna;
    }

    public void set_banat_alabna(int n) {
        banat_alabna = n;
    }

    public int get_banat_alabna() {
        return banat_alabna;
    }

    public void set_alikhwa_li_om(int n) {
        alikhwa_li_om = n;
    }

    public int get_alikhwa_li_om() {
        return alikhwa_li_om;
    }

    public void set_alakhawat_li_om(int n) {
        alakhawat_li_om = n;
    }

    public int get_alakhawat_li_om() {
        return alakhawat_li_om;
    }

    public void set_alikhwa_alashika(int n) {
        alikhwa_alashika = n;
    }

    public int get_alikhwa_alashika() {
        return alikhwa_alashika;
    }

    public void set_alakhawat_ashakikat(int n) {
        alakhawat_ashakikat = n;
    }

    public int get_alakhawat_ashakikat() {
        return alakhawat_ashakikat;
    }

    public void set_alikhwa_li_ab(int n) {
        alikhwa_li_ab = n;
    }

    public int get_alikhwa_li_ab() {
        return alikhwa_li_ab;
    }

    public void set_alakhawat_li_ab(int n) {
        alakhawat_li_ab = n;
    }

    public int get_alakhawat_li_ab() {
        return alakhawat_li_ab;
    }

    public void set_abna_alikhwa_alashika(int n) {
        abna_alikhwa_alashika = n;
    }

    public int get_abna_alikhwa_alashika() {
        return abna_alikhwa_alashika;
    }

    public void set_abna_alikhwa_li_ab(int n) {
        abna_alikhwa_li_ab = n;
    }

    public int get_abna_alikhwa_li_ab() {
        return abna_alikhwa_li_ab;
    }

    public void set_ala3mam_alashika(int n) {
        ala3mam_alashika = n;
    }

    public int get_ala3mam_alashika() {
        return ala3mam_alashika;
    }

    public void set_ala3mam_li_ab(int n) {
        ala3mam_li_ab = n;
    }

    public int get_ala3mam_li_ab() {
        return ala3mam_li_ab;
    }

    public void set_abna_ala3mam_alashika(int n) {
        abna_ala3mam_alashika = n;
    }

    public int get_abna_ala3mam_alashika() {
        return abna_ala3mam_alashika;
    }

    public void set_abna_ala3mam_li_ab(int n) {
        abna_ala3mam_li_ab = n;
    }

    public int get_abna_ala3mam_li_ab() {
        return abna_ala3mam_li_ab;
    }

    // TODO default constructor?

    public String calculate_furudh() {
        boolean far3_warith_dhakar = (alabna + abna_alabna) > 0;
        boolean far3_warith_ontha = (albanat + banat_alabna) > 0;
        boolean far3_warith = far3_warith_dhakar || far3_warith_ontha;
        boolean jam3_alikhwa = (alikhwa_alashika + alikhwa_li_ab + alikhwa_li_om +
                alakhawat_ashakikat + alakhawat_li_ab + alakhawat_li_om) > 1;
        boolean asl_warith_dhaker = alab || aljad;
        boolean far3_wa_asl_warith_dhaker = far3_warith_dhakar || asl_warith_dhaker;
        boolean alikhwa_alashika_wa_li_ab = (alikhwa_alashika + alikhwa_li_ab) > 0;
        boolean alikhwa_wa_abna_alikhwa = (alikhwa_alashika + alikhwa_li_ab + abna_alikhwa_alashika + abna_alikhwa_li_ab) > 0;
        boolean ala3mam = (ala3mam_alashika + ala3mam_li_ab) > 0;
        int awlad_alom = alikhwa_li_om + alakhawat_li_om;

        String tafsir;          // keep un-intialized so Studio warns us if they're not set in the code
        int bast, maqam, ro2os;
        boolean ta3seeb;

        massala = new Massala();
        massala.calcHal();


        // mirath azzawj
        if (zawj) {
            bast = 1;
            tafsir = Warith.AZAWJ.getTafsirPrefix();
            if (far3_warith) {
                maqam = 4;
                tafsir += "الربع 1\\4 فرضا";
            } else {
                maqam = 2;
                tafsir += "النصف 1\\2 فرضا";
            }
            massala.addMirath(new Mirath(Warith.AZAWJ, tafsir, bast, maqam));
        }

        // mirath azzawjat
        if (azawjat >= 1) {
            tafsir = Warith.AZAWJAT.getTafsirPrefix(azawjat);
            if (far3_warith) {
                maqam = 8;
                tafsir += "الثمن 1\\8 فرضا";
            } else {
                maqam = 4;
                tafsir += "الربع 1\\4 فرضا";
            }
            massala.addMirath(new Mirath(Warith.AZAWJAT, tafsir, 1, maqam, false, azawjat));
        }

        // mirath alab
        if (alab) {
            tafsir = Warith.ALAB.getTafsirPrefix();
            if (far3_warith_dhakar) {
                tafsir += "السدس 1\\6 فرضا فقط";
                bast = 1;
                maqam = 6;
                ta3seeb = false;
            } else if (far3_warith_ontha) {
                tafsir += "السدس 1\\6 فرضا + الباقي تعصيبا بالنفس";
                bast = 1;
                maqam = 6;
                ta3seeb = true;
            } else {
                tafsir += "الباقي تعصيبا بالنفس";
                bast = 0;
                maqam = 1;
                ta3seeb = true;
            }
            massala.addMirath(new Mirath(Warith.ALAB, tafsir, bast, maqam, ta3seeb));

            // alhajb bi alab
            if (aljad) {
                massala.addHajb(Warith.ALJAD, Warith.ALAB);
            }
            if (madhab != Madhab.HAMBALI) {
                // تسقط الجدة من جهة الأب بالأب، ولا تسقط الجدة من جهة الأم بالأب
                if (aljadah_li_ab) {
                    massala.addHajb(Warith.ALJADAH_LI_AB, Warith.ALAB);
                }
            }
            if (alikhwa_alashika >= 1) {
                massala.addHajb(Warith.ALIKHWA_ALASHIKA, alikhwa_alashika, Warith.ALAB);
            }
            if (alakhawat_ashakikat >= 1) {
                massala.addHajb(Warith.ALAKHAWAT_ASHAKIKAT, alakhawat_ashakikat, Warith.ALAB);
            }
            if (alikhwa_li_om >= 1) {
                massala.addHajb(Warith.ALIKHWA_LI_OM, alikhwa_li_om, Warith.ALAB);
            }
            if (alakhawat_li_om >= 1) {
                massala.addHajb(Warith.ALAKHAWAT_LI_OM, alakhawat_li_om, Warith.ALAB);
            }
            if (alikhwa_li_ab >= 1) {
                massala.addHajb(Warith.ALIKHWA_LI_AB, alikhwa_li_ab, Warith.ALAB);
            }
            if (alakhawat_li_ab >= 1) {
                massala.addHajb(Warith.ALAKHAWAT_LI_AB, alakhawat_li_ab, Warith.ALAB);
            }
            if (abna_alikhwa_alashika >= 1) {
                massala.addHajb(Warith.ABNA_ALIKHWA_ALASHIKA, abna_alikhwa_alashika, Warith.ALAB);
            }
            if (abna_alikhwa_li_ab >= 1) {
                massala.addHajb(Warith.ABNA_ALIKHWA_LI_AB, abna_alikhwa_li_ab, Warith.ALAB);
            }
            if (ala3mam_alashika >= 1) {
                massala.addHajb(Warith.ALA3MAM_ALASHIKA, ala3mam_alashika, Warith.ALAB);
            }
            if (ala3mam_li_ab >= 1) {
                massala.addHajb(Warith.ALA3MAM_LI_AB, ala3mam_li_ab, Warith.ALAB);
            }
            if (abna_ala3mam_alashika >= 1) {
                massala.addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, abna_ala3mam_alashika, Warith.ALAB);
            }
            if (abna_ala3mam_li_ab >= 1) {
                massala.addHajb(Warith.ABNA_ALA3MAM_LI_AB, abna_ala3mam_li_ab, Warith.ALAB);
            }
        }

        // mirath aljad
        if (aljad) {
            if (!alab) {        // no hajb by ab
                ro2os = 1;
                if (far3_warith_dhakar) {
                    tafsir = Warith.ALJAD.getTafsirPrefix();
                    tafsir += "السدس 1\\6 فرضا فقط";
                    bast = 1;
                    maqam = 6;
                    ta3seeb = false;
                } else {            // Here be dragons الجد يرث فرضا وتعصيبا أو تعصيبا فقط
                    ta3seeb = true;
                    if (far3_warith_ontha) {
                        tafsir = Warith.ALJAD.getTafsirPrefix();
                        tafsir += "السدس 1\\6 فرضا + ";
                        bast = 1;
                        maqam = 6;
                    } else {             // الجد يرث الباقي فقط
                        tafsir = "";
                        bast = 0;
                        maqam = 1;
                    }
                    if (alikhwa_alashika_wa_li_ab) {        // الجد يُقاسم الإخوة
                        boolean tassawi;
                        if (alikhwa_alashika > 0) {
                            ro2os += alikhwa_alashika;
                            if (alakhawat_ashakikat > 0) {
                                ro2os *= 2;
                                ro2os += alakhawat_ashakikat;
                                tassawi = false;
                            } else {
                                tassawi = true;
                            }
                            tafsir += Warith.ALJAD.getTafsirPrefix(1, Warith.ALIKHWA_ALASHIKA, alikhwa_alashika, tassawi);
                        } else {  // alikhwa_li_ab > 0
                            ro2os += alikhwa_li_ab;
                            if (alakhawat_li_ab > 0) {
                                ro2os *= 2;
                                ro2os += alikhwa_li_ab;
                                tassawi = false;
                            } else {
                                tassawi = true;
                            }
                            tafsir += Warith.ALJAD.getTafsirPrefix(1, Warith.ALIKHWA_LI_AB, alikhwa_li_ab, tassawi);
                        }
                    }
                    else {       // الجد يرث الباقي لوحده
                        tafsir = Warith.ALJAD.getTafsirPrefix();
                    }
                    tafsir += "الباقي تعصيبا بالنفس";
                }
                massala.addMirath(new Mirath(Warith.ALJAD, tafsir, bast, maqam, ta3seeb, 1, ro2os));
            }

            // alhajb by aljad, we add it even if there is hajb by ab for detailed info
            if (alikhwa_li_om >= 1) {
                massala.addHajb(Warith.ALIKHWA_LI_OM, alikhwa_li_om, Warith.ALJAD);
            }
            if (alakhawat_li_om >= 1) {
                massala.addHajb(Warith.ALAKHAWAT_LI_OM, alakhawat_li_om, Warith.ALJAD);
            }
            if (abna_alikhwa_alashika >= 1) {
                massala.addHajb(Warith.ABNA_ALIKHWA_ALASHIKA, abna_alikhwa_alashika, Warith.ALJAD);
            }
            if (abna_alikhwa_li_ab >= 1) {
                massala.addHajb(Warith.ABNA_ALIKHWA_LI_AB, abna_alikhwa_li_ab, Warith.ALJAD);
            }
            if (ala3mam_alashika >= 1) {
                massala.addHajb(Warith.ALA3MAM_ALASHIKA, ala3mam_alashika, Warith.ALJAD);
            }
            if (ala3mam_li_ab >= 1) {
                massala.addHajb(Warith.ALA3MAM_LI_AB, ala3mam_li_ab, Warith.ALJAD);
            }
            if (abna_ala3mam_alashika >= 1) {
                massala.addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, abna_ala3mam_alashika, Warith.ALJAD);
            }
            if (abna_ala3mam_li_ab >= 1) {
                massala.addHajb(Warith.ABNA_ALA3MAM_LI_AB, abna_ala3mam_li_ab, Warith.ALJAD);
            }
        }

        // mirath alom
        if (alom) {
            tafsir = Warith.ALOM.getTafsirPrefix();
            if (jam3_alikhwa || far3_warith) {
                tafsir += "السدس 1\\6 فرضا";
                maqam = 6;
                ta3seeb = false;
            } else if ((alab) && ((azawjat > 0) || (zawj))) {
                tafsir = "الأم ترث ثلث 1\\3 الباقي";
                maqam = 3;
                ta3seeb = true;
                special_case = "هذه مسألة الغرّاوين"; // TODO make it an enum type
            } else {
                tafsir += "الثلث 1\\3 فرضا";
                maqam = 3;
                ta3seeb = false;
            }
            massala.addMirath(new Mirath(Warith.ALOM, tafsir, 1, maqam, ta3seeb, 1));

            // alhajb bi alom
            if (aljadah_li_ab) {
                massala.addHajb(Warith.ALJADAH_LI_AB, Warith.ALOM);
            }
            if (aljadah_li_om) {
                massala.addHajb(Warith.ALJADAH_LI_OM, Warith.ALOM);
            }
        } else { // mirath aljadat
            if (aljadah_li_ab && !alab) {
                tafsir = "الجدة لأب ";
                if (aljadah_li_om) {
                    tafsir += "تشترك بالتساوي مع الجدة لأم في السدس 1\\6 فرضا";
                    ro2os = 2;
                } else {
                    tafsir += "ترث السدس 1\\6 فرضا";
                    ro2os = 1;
                }
                massala.addMirath(new Mirath(Warith.ALJADAH_LI_AB, tafsir, 1, 6, false, 1, ro2os));
            }

            if (aljadah_li_om) {
                tafsir = "الجدة لأم ";
                if (aljadah_li_ab && !alab) {
                    tafsir += "تشترك بالتساوي مع الجدة لأب في السدس 1\\6 فرضا";
                    ro2os = 2;
                } else {
                    tafsir += "ترث السدس 1\\6 فرضا";
                    ro2os = 1;
                }
                massala.addMirath(new Mirath(Warith.ALJADAH_LI_OM, tafsir, 1, 6, false, 1,  ro2os));
            }
        }

        // mirath alikhwa li om
        if (awlad_alom > 0) {
            if (far3_warith) {
                if (alikhwa_li_om > 0) {
                    massala.addHajb(Warith.ALIKHWA_LI_OM, alikhwa_li_om, "الفرع الوارث");
                }
                if (alakhawat_li_om > 0) {
                    massala.addHajb(Warith.ALAKHAWAT_LI_OM, alakhawat_li_om, "الفرع الوارث");
                }
            } else if (!asl_warith_dhaker) { // hajb by asl warith dhaker done above
                if (awlad_alom > 1) {
                    if (alikhwa_li_om > 0) {
                        if (alakhawat_li_om > 0) {
                            tafsir = Warith.ALAKHAWAT_LI_OM.getTafsirPrefix(alakhawat_li_om, Warith.ALIKHWA_LI_OM, alikhwa_li_om, true);
                            tafsir += "الثلث 1\\3 فرضا";
                            ro2os = awlad_alom;
                            massala.addMirath(new Mirath(Warith.ALAKHAWAT_LI_OM, tafsir, 1, 3, false, alakhawat_li_om, ro2os));
                            tafsir = Warith.ALIKHWA_LI_OM.getTafsirPrefix(alikhwa_li_om, Warith.ALAKHAWAT_LI_OM, alakhawat_li_om, true);
                        }
                        else {
                            tafsir = Warith.ALIKHWA_LI_OM.getTafsirPrefix(alikhwa_li_om);
                            ro2os = alikhwa_li_om;
                        }
                        tafsir += "الثلث 1\\3 فرضا";
                        massala.addMirath(new Mirath(Warith.ALIKHWA_LI_OM, tafsir, 1, 3, false, alikhwa_li_om, ro2os));
                    }
                    else if (alakhawat_li_om > 0) {
                        tafsir = Warith.ALAKHAWAT_LI_OM.getTafsirPrefix(alakhawat_li_om);
                        tafsir += "الثلث 1\\3 فرضا";
                        massala.addMirath(new Mirath(Warith.ALAKHAWAT_LI_OM, tafsir, 1, 3, false, alakhawat_li_om, alakhawat_li_om));
                    }
                } else if (alikhwa_li_om == 1) {
                    tafsir = Warith.ALIKHWA_LI_OM.getTafsirPrefix();
                    tafsir += "السدس 1\\6 فرضا";
                    massala.addMirath(new Mirath(Warith.ALIKHWA_LI_OM, tafsir, 1, 6));
                } else { // alakhawat_li_om == 1
                    tafsir = Warith.ALAKHAWAT_LI_OM.getTafsirPrefix();
                    tafsir += "السدس 1\\6 فرضا";
                    massala.addMirath(new Mirath(Warith.ALAKHAWAT_LI_OM, tafsir, 1, 6));
                }
            }
        }

        // mirath albanat ma3a 3adam wojud alabna
        if (alabna == 0) {
            if (albanat == 1) {
                tafsir = Warith.ALBANAT.getTafsirPrefix();
                tafsir += "النصف 1\\2 فرضا";
                massala.addMirath(new Mirath(Warith.ALBANAT, tafsir, 1, 2));
                if ((abna_alabna == 0) && (banat_alabna > 0)) {
                    tafsir = Warith.BANAT_ALABNA.getTafsirPrefix(banat_alabna);
                    tafsir += "السدس 1\\6 تتمة الثلثين فرضا";
                    massala.addMirath(new Mirath(Warith.BANAT_ALABNA, tafsir, 1, 6, false, banat_alabna));
                }
            } else if (albanat >= 2) {
                tafsir = Warith.ALBANAT.getTafsirPrefix(albanat);
                tafsir += "الثلثين 2\\3 فرضا";
                massala.addMirath(new Mirath(Warith.ALBANAT, tafsir, 2, 3, false, albanat));
                if ((abna_alabna == 0) && (banat_alabna > 0)) {
                    massala.addHajb(Warith.BANAT_ALABNA, banat_alabna, "الجمع من البنات");
                }
            } else { // albanat == 0
                // same logic holds for 2nd generation if present
                if ((abna_alabna == 0) && (banat_alabna > 0)) {
                    tafsir = Warith.BANAT_ALABNA.getTafsirPrefix(banat_alabna);
                    if (banat_alabna == 1) {
                        bast = 1; maqam = 2;
                        tafsir += "النصف 1\\2 فرضا";
                    } else {
                        bast = 2; maqam = 3;
                        tafsir += "الثلثين 2\\3 فرضا";
                    }
                    massala.addMirath(new Mirath(Warith.BANAT_ALABNA, tafsir, bast, maqam, false, banat_alabna));
                }
            }
        }

        // mirath alakhawat ashakikat 3inda 3adam wojud alikhwa alashika
        if ((alakhawat_ashakikat > 0) && !alab && !far3_warith_dhakar && (alikhwa_alashika == 0)) {
            tafsir = Warith.ALAKHAWAT_ASHAKIKAT.getTafsirPrefix(alakhawat_ashakikat);
            if (!far3_warith_ontha) {
                ta3seeb = false;
                if (alakhawat_ashakikat == 1) {
                    bast = 1; maqam = 2;
                    tafsir += "النصف 1\\2 فرضا";
                } else { // (alakhawat_ashakikat >= 2)
                    bast = 2; maqam = 3;
                    tafsir +="الثلثين 2\\3 فرضا";
                }
            } else { // presence of far3_warith_ontha
                ta3seeb = true;
                bast = 0; maqam = 1;
                tafsir += "الباقي تعصيبا مع الغير";
            }
            massala.addMirath(new Mirath(Warith.ALAKHAWAT_ASHAKIKAT, tafsir, bast, maqam, ta3seeb, alakhawat_ashakikat));
        }

        // mirath alakhawat li ab 3inda 3adam wojud alikhwa
        if ((alakhawat_li_ab > 0) && !alab && !far3_warith_dhakar && !alikhwa_alashika_wa_li_ab) {
            if (!far3_warith_ontha) {
                if (alakhawat_ashakikat > 1) {
                    massala.addHajb(Warith.ALAKHAWAT_LI_AB, alakhawat_li_ab, Warith.ALAKHAWAT_ASHAKIKAT.getName(alakhawat_ashakikat));
                } else {
                    tafsir = Warith.ALAKHAWAT_LI_AB.getTafsirPrefix(alakhawat_li_ab);
                    if (alakhawat_ashakikat == 0) {
                        if (alakhawat_li_ab == 1) {
                            bast = 1; maqam = 2;
                            tafsir += "النصف 1\\2 فرضا";
                        } else { // (alakhawat_li_ab >= 2)
                            bast = 2; maqam = 3;
                            tafsir += "الثلثين 2\\3 فرضا";
                        }
                    } else { // (alakhawat_ashakikat == 1)
                        bast = 1; maqam = 6;
                        tafsir += "السدس 1\\6 تتمة الثلثين فرضا";
                    }
                    massala.addMirath(new Mirath(Warith.ALAKHAWAT_LI_AB, tafsir, bast, maqam, false, alakhawat_li_ab));
                }
            } else { // far3_warith_ontha
                if (alakhawat_ashakikat == 0) {
                    tafsir = Warith.ALAKHAWAT_LI_AB.getTafsirPrefix(alakhawat_li_ab);
                    tafsir += " الباقي تعصيبا مع الغير";
                    massala.addMirath(new Mirath(Warith.ALAKHAWAT_LI_AB, tafsir, 0, 1, true, alakhawat_li_ab));
                } else {
                    massala.addHajb(Warith.ALAKHAWAT_LI_AB, alakhawat_li_ab, Warith.ALAKHAWAT_ASHAKIKAT, alakhawat_ashakikat);
                }
            }
        }

        //mirath alabna + albanat 3inda woujoud alabna
        if (alabna > 0) {
            if (albanat == 0) {
                tafsir = Warith.ALABNA.getTafsirPrefix(alabna);
                tafsir += " الباقي تعصيبا بالنفس";
                massala.addMirath(new Mirath(Warith.ALABNA, tafsir, 0, 1, true, alabna));
            } else {
                String tafsir2;
                tafsir = Warith.ALABNA.getTafsirPrefix(alabna, Warith.ALBANAT, albanat, false);
                tafsir += "الباقي تعصيبا بالنفس";
                tafsir2 = Warith.ALBANAT.getTafsirPrefix(albanat, Warith.ALABNA, alabna, false);
                tafsir2 += "الباقي تعصيبا بالغير";
                ro2os = 2 * alabna + albanat;
                massala.addMirath(new Mirath(Warith.ALABNA, tafsir, 0, 1, true, alabna, ro2os));
                massala.addMirath(new Mirath(Warith.ALBANAT, tafsir2, 0, 1, true, albanat, ro2os));
            }

            // hajb by alabna
            if (abna_alabna > 0) {
                massala.addHajb(Warith.ABNA_ALABNA, abna_alabna, Warith.ALABNA, alabna);
            }
            if (banat_alabna > 0) {
                massala.addHajb(Warith.BANAT_ALABNA, banat_alabna, Warith.ALABNA, alabna);
            }
            if (alikhwa_alashika > 0) {
                massala.addHajb(Warith.ALIKHWA_ALASHIKA, alikhwa_alashika, Warith.ALABNA, alabna);
            }
            if (alakhawat_ashakikat > 0) {
                massala.addHajb(Warith.ALAKHAWAT_ASHAKIKAT, alakhawat_ashakikat, Warith.ALABNA, alabna);
            }
            if (alikhwa_li_ab > 0) {
                massala.addHajb(Warith.ALIKHWA_LI_AB, alikhwa_li_ab, Warith.ALABNA, alabna);
            }
            if (alakhawat_li_ab > 0) {
                massala.addHajb(Warith.ALAKHAWAT_LI_AB, alakhawat_li_ab, Warith.ALABNA, alabna);
            }
            //الحجب تم بالفرع الوارث سابقا في ميراث أولاد الأم
           /* if (alikhwa_li_om > 0) {
                massala.addHajb(Warith.ALIKHWA_LI_OM, alikhwa_li_om, Warith.ALABNA, alabna);
            }
            if (alakhawat_li_om > 0) {
                massala.addHajb(Warith.ALAKHAWAT_LI_OM, alakhawat_li_om, Warith.ALABNA, alabna);
            } */
            if (abna_alikhwa_alashika > 0) {
                massala.addHajb(Warith.ABNA_ALIKHWA_ALASHIKA, abna_alikhwa_alashika, Warith.ALABNA, alabna);
            }
            if (abna_alikhwa_li_ab > 0) {
                massala.addHajb(Warith.ABNA_ALIKHWA_LI_AB, abna_alikhwa_li_ab, Warith.ALABNA, alabna);
            }
            if (ala3mam_alashika > 0) {
                massala.addHajb(Warith.ALA3MAM_ALASHIKA, ala3mam_alashika, Warith.ALABNA, alabna);
            }
            if (ala3mam_li_ab > 0) {
                massala.addHajb(Warith.ALA3MAM_LI_AB, ala3mam_li_ab, Warith.ALABNA, alabna);
            }
            if (abna_ala3mam_alashika > 0) {
                massala.addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, abna_ala3mam_alashika, Warith.ALABNA, alabna);
            }
            if (abna_ala3mam_li_ab > 0) {
                massala.addHajb(Warith.ABNA_ALA3MAM_LI_AB, abna_ala3mam_li_ab, Warith.ALABNA, alabna);
            }
        }

        // mirath abna_alabna + banat_alabna
        if ((abna_alabna > 0) && (alabna == 0)) {
            if (banat_alabna == 0) {
                tafsir = Warith.ABNA_ALABNA.getTafsirPrefix(abna_alabna);
                tafsir += "الباقي تعصيبا بالنفس";
                massala.addMirath(new Mirath(Warith.ABNA_ALABNA, tafsir, 0, 1, true, abna_alabna));
            } else {
                String tafsir2;
                tafsir = Warith.ABNA_ALABNA.getTafsirPrefix(abna_alabna, Warith.BANAT_ALABNA, banat_alabna, false);
                tafsir += "الباقي تعصيبا بالنفس";
                tafsir2 = Warith.BANAT_ALABNA.getTafsirPrefix(banat_alabna, Warith.ABNA_ALABNA, abna_alabna, false);
                tafsir2 += "الباقي تعصيبا بالغير";
                ro2os = 2 * abna_alabna + banat_alabna;
                massala.addMirath(new Mirath(Warith.ABNA_ALABNA, tafsir, 0, 1, true, abna_alabna, ro2os));
                massala.addMirath(new Mirath(Warith.BANAT_ALABNA, tafsir2, 0, 1, true, banat_alabna, ro2os));
            }

            // hajb by abna alabna
            if (alikhwa_alashika > 0) {
                massala.addHajb(Warith.ALIKHWA_ALASHIKA, alikhwa_alashika, Warith.ABNA_ALABNA, abna_alabna);
            }
            if (alakhawat_ashakikat > 0) {
                massala.addHajb(Warith.ALAKHAWAT_ASHAKIKAT, alakhawat_ashakikat, Warith.ABNA_ALABNA, abna_alabna);
            }
            if (alikhwa_li_ab > 0) {
                massala.addHajb(Warith.ALIKHWA_LI_AB, alikhwa_li_ab, Warith.ABNA_ALABNA, abna_alabna);
            }
            if (alakhawat_li_ab > 0) {
                massala.addHajb(Warith.ALAKHAWAT_LI_AB, alakhawat_li_ab, Warith.ABNA_ALABNA, abna_alabna);
            }
            //الحجب تم بالفرع الوارث سابقا في ميراث أولاد الأم
         /*   if (alikhwa_li_om > 0) {
                massala.addHajb(Warith.ALIKHWA_LI_OM, alikhwa_li_om, Warith.ABNA_ALABNA, abna_alabna);
            }
            if (alakhawat_li_om > 0) {
                massala.addHajb(Warith.ALAKHAWAT_LI_OM, alakhawat_li_om, Warith.ABNA_ALABNA, abna_alabna);
            }*/
            if (abna_alikhwa_alashika > 0) {
                massala.addHajb(Warith.ABNA_ALIKHWA_ALASHIKA, abna_alikhwa_alashika, Warith.ABNA_ALABNA, abna_alabna);
            }
            if (abna_alikhwa_li_ab > 0) {
                massala.addHajb(Warith.ABNA_ALIKHWA_LI_AB, abna_alikhwa_li_ab, Warith.ABNA_ALABNA, abna_alabna);
            }
            if (ala3mam_alashika > 0) {
                massala.addHajb(Warith.ALA3MAM_ALASHIKA, ala3mam_alashika, Warith.ABNA_ALABNA, abna_alabna);
            }
            if (ala3mam_li_ab > 0) {
                massala.addHajb(Warith.ALA3MAM_LI_AB, ala3mam_li_ab, Warith.ABNA_ALABNA, abna_alabna);
            }
            if (abna_ala3mam_alashika > 0) {
                massala.addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, abna_ala3mam_alashika, Warith.ABNA_ALABNA, abna_alabna);
            }
            if (abna_ala3mam_li_ab > 0) {
                massala.addHajb(Warith.ABNA_ALA3MAM_LI_AB, abna_ala3mam_li_ab, Warith.ABNA_ALABNA, abna_alabna);
            }
        }

        // mirath alab and hajb by alab were previously calculated
        // mirath aljad and hajb by aljad were previously calculated

        // mirath alikhwa alashika + alakhawat ashakikat
        if (alikhwa_alashika > 0 && !far3_warith_dhakar && !alab) {
            if (alakhawat_ashakikat == 0) {
                ro2os = alikhwa_alashika;
                if (aljad) {
                    ro2os++;
                    tafsir = Warith.ALIKHWA_ALASHIKA.getTafsirPrefix(alikhwa_alashika, Warith.ALJAD, 1, true);
                }
                else {
                    tafsir = Warith.ALIKHWA_ALASHIKA.getTafsirPrefix(alikhwa_alashika);
                }
                tafsir += "الباقي تعصيبا بالنفس";
                massala.addMirath(new Mirath(Warith.ALIKHWA_ALASHIKA, tafsir, 0, 1, true, alikhwa_alashika, ro2os));
            } else {
                String tafsir2;
                tafsir = Warith.ALIKHWA_ALASHIKA.getTafsirPrefix(alikhwa_alashika, Warith.ALAKHAWAT_ASHAKIKAT, alakhawat_ashakikat, false);
                tafsir += "الباقي تعصيبا بالنفس";
                tafsir2 = Warith.ALAKHAWAT_ASHAKIKAT.getTafsirPrefix(alakhawat_ashakikat, Warith.ALIKHWA_ALASHIKA, alikhwa_alashika, false);
                tafsir2 += "الباقي تعصيبا بالغير";
                ro2os = 2 * alikhwa_alashika + alakhawat_ashakikat;
                if (aljad) {
                    tafsir += " ومقاسمة مع الجد";
                    tafsir2 += " ومقاسمة مع الجد";
                    ro2os += 2;
                }
                massala.addMirath(new Mirath(Warith.ALIKHWA_ALASHIKA, tafsir, 0, 1, true, alikhwa_alashika, ro2os));
                massala.addMirath(new Mirath(Warith.ALAKHAWAT_ASHAKIKAT, tafsir2, 0, 1, true, alakhawat_ashakikat, ro2os));
            }

            // الحجب بالإخوة الأشقاء
            if (alikhwa_li_ab > 0) {
                massala.addHajb(Warith.ALIKHWA_LI_AB, alikhwa_li_ab, Warith.ALIKHWA_ALASHIKA, alikhwa_alashika);
            }
            if (alakhawat_li_ab > 0) {
                massala.addHajb(Warith.ALAKHAWAT_LI_AB, alakhawat_li_ab, Warith.ALIKHWA_ALASHIKA, alikhwa_alashika);
            }
            if (abna_alikhwa_alashika > 0) {
                massala.addHajb(Warith.ABNA_ALIKHWA_ALASHIKA, abna_alikhwa_alashika, Warith.ALIKHWA_ALASHIKA, alikhwa_alashika);
            }
            if (abna_alikhwa_li_ab > 0) {
                massala.addHajb(Warith.ABNA_ALIKHWA_LI_AB, abna_alikhwa_alashika, Warith.ALIKHWA_ALASHIKA, alikhwa_alashika);
            }
            if (ala3mam_alashika > 0) {
                massala.addHajb(Warith.ALA3MAM_ALASHIKA, ala3mam_alashika, Warith.ALIKHWA_ALASHIKA, alikhwa_alashika);
            }
            if (ala3mam_li_ab > 0) {
                massala.addHajb(Warith.ALA3MAM_LI_AB, ala3mam_li_ab, Warith.ALIKHWA_ALASHIKA, alikhwa_alashika);
            }
            if (abna_ala3mam_alashika > 0) {
                massala.addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, abna_ala3mam_alashika, Warith.ALIKHWA_ALASHIKA, alikhwa_alashika);
            }
            if (abna_ala3mam_li_ab > 0) {
                massala.addHajb(Warith.ABNA_ALA3MAM_LI_AB, abna_ala3mam_li_ab, Warith.ALIKHWA_ALASHIKA, alikhwa_alashika);
            }
        }

        // mirath alikhwa li ab + alakhawat li ab
        if ((alikhwa_li_ab > 0) && !far3_warith_dhakar && !alab && (alikhwa_alashika == 0)) {
            if (alakhawat_li_ab == 0) {
                ro2os = alikhwa_li_ab;
                if (aljad) {
                    ro2os++;
                    tafsir = Warith.ALIKHWA_LI_AB.getTafsirPrefix(alikhwa_li_ab, Warith.ALJAD, 1, true);
                }
                else {
                    tafsir = Warith.ALIKHWA_LI_AB.getTafsirPrefix(alikhwa_li_ab);
                }
                tafsir += "الباقي تعصيبا بالنفس";
                massala.addMirath(new Mirath(Warith.ALIKHWA_LI_AB, tafsir, 0, 1, true, alikhwa_li_ab, ro2os));
            } else {
                String tafsir2;
                tafsir = Warith.ALIKHWA_LI_AB.getTafsirPrefix(alikhwa_li_ab, Warith.ALAKHAWAT_LI_AB, alakhawat_li_ab, false);
                tafsir += "الباقي تعصيبا بالنفس";
                tafsir2 = Warith.ALAKHAWAT_LI_AB.getTafsirPrefix(alakhawat_li_ab, Warith.ALIKHWA_LI_AB, alikhwa_li_ab, false);
                tafsir2 += "الباقي تعصيبا بالغير";
                ro2os = 2 * alikhwa_li_ab + alakhawat_li_ab;
                if (aljad) {
                    tafsir += " ومقاسمة مع الجد";
                    tafsir2 += " ومقاسمة مع الجد";
                    ro2os += 2;
                }
                massala.addMirath(new Mirath(Warith.ALIKHWA_LI_AB, tafsir, 0, 1, true, alikhwa_li_ab, ro2os));
                massala.addMirath(new Mirath(Warith.ALAKHAWAT_LI_AB, tafsir2, 0, 1, true, alakhawat_li_ab, ro2os));
            }

            // alhajb by alikhwa li ab
            if (abna_alikhwa_alashika > 0) {
                massala.addHajb(Warith.ABNA_ALIKHWA_ALASHIKA, abna_alikhwa_alashika, Warith.ALIKHWA_LI_AB, alikhwa_li_ab);
            }
            if (abna_alikhwa_li_ab > 0) {
                massala.addHajb(Warith.ABNA_ALIKHWA_LI_AB, abna_alikhwa_li_ab, Warith.ALIKHWA_LI_AB, alikhwa_li_ab);
            }
            if (ala3mam_alashika > 0) {
                massala.addHajb(Warith.ALA3MAM_ALASHIKA, ala3mam_alashika, Warith.ALIKHWA_LI_AB, alikhwa_li_ab);
            }
            if (ala3mam_li_ab > 0) {
                massala.addHajb(Warith.ALA3MAM_LI_AB, ala3mam_li_ab, Warith.ALIKHWA_LI_AB, alikhwa_li_ab);
            }
            if (abna_ala3mam_alashika > 0) {
                massala.addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, abna_ala3mam_alashika, Warith.ALIKHWA_LI_AB, alikhwa_li_ab);
            }
            if (abna_ala3mam_li_ab > 0) {
                massala.addHajb(Warith.ABNA_ALA3MAM_LI_AB, abna_ala3mam_li_ab, Warith.ALIKHWA_LI_AB, alikhwa_li_ab);
            }
        }

        // mirath abna alikhwa alashika
        if ((abna_alikhwa_alashika > 0) && !far3_wa_asl_warith_dhaker && !alikhwa_alashika_wa_li_ab) {
            tafsir = Warith.ABNA_ALIKHWA_ALASHIKA.getName(abna_alikhwa_alashika);
            tafsir += "الباقي تعصيبا بالنفس";
            massala.addMirath(new Mirath(Warith.ABNA_ALIKHWA_ALASHIKA, tafsir, 0, 1, true, abna_alikhwa_alashika));

            // alhajb by abna alikhwa alashika
            
            if (abna_alikhwa_li_ab > 0) {
                massala.addHajb(Warith.ABNA_ALIKHWA_LI_AB, abna_alikhwa_li_ab, Warith.ABNA_ALIKHWA_ALASHIKA, abna_alikhwa_alashika);
            }
            if (ala3mam_alashika > 0) {
                massala.addHajb(Warith.ALA3MAM_ALASHIKA, ala3mam_alashika, Warith.ABNA_ALIKHWA_ALASHIKA, abna_alikhwa_alashika);
            }
            if (ala3mam_li_ab > 0) {
                massala.addHajb(Warith.ALA3MAM_LI_AB, ala3mam_li_ab, Warith.ABNA_ALIKHWA_ALASHIKA, abna_alikhwa_alashika);
            }
            if (abna_ala3mam_alashika > 0) {
                massala.addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, abna_ala3mam_alashika, Warith.ABNA_ALIKHWA_ALASHIKA, abna_alikhwa_alashika);
            }
            if (abna_ala3mam_li_ab > 0) {
                massala.addHajb(Warith.ABNA_ALA3MAM_LI_AB, abna_ala3mam_li_ab, Warith.ABNA_ALIKHWA_ALASHIKA, abna_alikhwa_alashika);
            }
        }

        // mirath abna alikhwa li ab
        if ((abna_alikhwa_li_ab > 0) && !far3_wa_asl_warith_dhaker && !alikhwa_alashika_wa_li_ab && (abna_alikhwa_alashika == 0)) {
            tafsir = Warith.ABNA_ALIKHWA_LI_AB.getName(abna_alikhwa_li_ab);
            tafsir += "الباقي تعصيبا بالنفس";
            massala.addMirath(new Mirath(Warith.ABNA_ALIKHWA_LI_AB, tafsir, 0, 1, true, abna_alikhwa_li_ab));

            // alhajb by abna alikhwa li_ab
            if (ala3mam_alashika > 0) {
                massala.addHajb(Warith.ALA3MAM_ALASHIKA, ala3mam_alashika, Warith.ABNA_ALIKHWA_LI_AB, abna_alikhwa_li_ab);
            }
            if (ala3mam_li_ab > 0) {
                massala.addHajb(Warith.ALA3MAM_LI_AB, ala3mam_li_ab, Warith.ABNA_ALIKHWA_LI_AB, abna_alikhwa_li_ab);
            }
            if (abna_ala3mam_alashika > 0) {
                massala.addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, abna_ala3mam_alashika, Warith.ABNA_ALIKHWA_LI_AB, abna_alikhwa_li_ab);
            }
            if (abna_ala3mam_li_ab > 0) {
                massala.addHajb(Warith.ABNA_ALA3MAM_LI_AB, abna_ala3mam_li_ab, Warith.ABNA_ALIKHWA_LI_AB, abna_alikhwa_li_ab);
            }
        }

        // mirath ala3mam alashika
        if ((ala3mam_alashika > 0) && !far3_wa_asl_warith_dhaker && !alikhwa_wa_abna_alikhwa) {
            tafsir = Warith.ALA3MAM_ALASHIKA.getName(ala3mam_alashika);
            tafsir += "الباقي تعصيبا بالنفس";
            massala.addMirath(new Mirath(Warith.ALA3MAM_ALASHIKA, tafsir, 0, 1, true, ala3mam_alashika));

            // alhajb by abna alikhwa alashika
            if (ala3mam_li_ab > 0) {
                massala.addHajb(Warith.ALA3MAM_LI_AB, ala3mam_li_ab, Warith.ALA3MAM_ALASHIKA, ala3mam_alashika);
            }
            if (abna_ala3mam_alashika > 0) {
                massala.addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, abna_ala3mam_alashika, Warith.ALA3MAM_ALASHIKA, ala3mam_alashika);
            }
            if (abna_ala3mam_li_ab > 0) {
                massala.addHajb(Warith.ABNA_ALA3MAM_LI_AB, abna_ala3mam_li_ab, Warith.ALA3MAM_ALASHIKA, ala3mam_alashika);
            }
        }

        // mirath ala3mam li ab
        if ((ala3mam_li_ab > 0) && !far3_wa_asl_warith_dhaker && !alikhwa_wa_abna_alikhwa && (ala3mam_alashika == 0)) {
            tafsir = Warith.ALA3MAM_LI_AB.getName(ala3mam_li_ab);
            tafsir += "الباقي تعصيبا بالنفس";
            massala.addMirath(new Mirath(Warith.ALA3MAM_LI_AB, tafsir, 0, 1, true, ala3mam_li_ab));

            // alhajb by abna alikhwa li_ab
            if (abna_ala3mam_alashika > 0) {
                massala.addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, abna_ala3mam_alashika, Warith.ALA3MAM_LI_AB, ala3mam_li_ab);
            }
            if (abna_ala3mam_li_ab > 0) {
                massala.addHajb(Warith.ABNA_ALA3MAM_LI_AB, abna_ala3mam_li_ab, Warith.ALA3MAM_LI_AB, ala3mam_li_ab);
            }
        }

        // mirath abna ala3mam alashika
        if ((abna_ala3mam_alashika > 0) && !far3_wa_asl_warith_dhaker && !alikhwa_wa_abna_alikhwa && !ala3mam) {
            tafsir = Warith.ABNA_ALA3MAM_ALASHIKA.getName(abna_ala3mam_alashika);
            tafsir += "الباقي تعصيبا بالنفس";
            massala.addMirath(new Mirath(Warith.ABNA_ALA3MAM_ALASHIKA, tafsir, 0, 1, true, abna_ala3mam_alashika));

            // alhajb by abna alikhwa li_ab
            if (abna_ala3mam_li_ab > 0) {
                massala.addHajb(Warith.ABNA_ALA3MAM_LI_AB, abna_ala3mam_li_ab, Warith.ABNA_ALA3MAM_ALASHIKA, abna_ala3mam_alashika);
            }
        }

        // mirath abnaa ala3mam li ab
        if ((abna_ala3mam_li_ab > 0) && !far3_wa_asl_warith_dhaker && !alikhwa_wa_abna_alikhwa && !ala3mam && (abna_ala3mam_alashika == 0)) {
            tafsir = Warith.ABNA_ALA3MAM_LI_AB.getName(abna_ala3mam_li_ab);
            tafsir += "الباقي تعصيبا بالنفس";
            massala.addMirath(new Mirath(Warith.ABNA_ALA3MAM_LI_AB, tafsir, 0, 1, true, abna_ala3mam_li_ab));
        }

        if (massala.noInput()) {
            return "لم يتم إدخال أية ورثة";
        }

        // al-hissab
        massala.calcAslAndRo2os();
        massala.calcAshomAndBaqi();
        massala.calcQissma();

        System.out.println(massala.getTable());

        return massala.getTafsir();
    }

}
