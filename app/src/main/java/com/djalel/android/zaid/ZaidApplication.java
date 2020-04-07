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

import java.util.EnumMap;

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

    EnumMap<Warith, Mirath> warathah;
    // maintained in the natural order of their keys (the order in which the enum constants are declared)
    private int adad_3assabat;
    private int asl_mas2ala;

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

    private void resetMawarith()
    {
        warathah = new EnumMap<Warith, Mirath>(Warith.class);
        special_case = null;
        adad_3assabat = 0;
        asl_mas2ala = 1;

    }

    private void hajb(Warith warith, String ism, String dhamir, String hajib)
    {
        if (warathah != null) {
            Mirath m = warathah.get(warith);
            if (m == null) {
                warathah.put(warith, new Mirath(ism + " حجب" + dhamir + " " + hajib));
            } else {
                m.addHajib(hajib);
            }
        }
    }

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
        boolean ikhwa_ma3a_aljad = (alikhwa_alashika + alikhwa_li_ab) > 0;
        int awlad_alom = alikhwa_li_om + alakhawat_li_om;

        String tafsir, ism, hajib, dhamir;  // keep un-intialized so Studio warns us if they're not set in the code
        int bast, maqam, ro2os;
        boolean baqi;

        resetMawarith();

        // mirath azzawj
        if (zawj) {
            if (far3_warith) {
                warathah.put(Warith.AZAWJ, new Mirath("الزوج يرث الربع 1\\4 فرضا", 1, 4));
            } else {
                warathah.put(Warith.AZAWJ, new Mirath("الزوج يرث النصف 1\\2 فرضا", 1, 2));
            }
        }

        // mirath azzawjat
        if (azawjat >= 1) {
            switch (azawjat) {
                case 1:
                    tafsir = "الزوجة ترث ";
                    break;
                case 2:
                    tafsir = "الزوجتان تشتركان بالتساوي في ";
                    break;
                default:
                    tafsir = "الزوجات تشتركن بالتساوي في ";
                    break;
            }
            tafsir += (far3_warith ? "الثمن 1\\8" : "الربع 1\\4") + " فرضا";
            warathah.put(Warith.AZAWJAT, new Mirath(tafsir, 1, (far3_warith ? 8 : 4), false, azawjat));
        }

        // mirath alab
        if (alab) {
            tafsir = "الأب يرث ";
            if (far3_warith_dhakar) {
                tafsir += "السدس 1\\6 فرضا فقط";
                bast = 1;
                maqam = 6;
                baqi = false;
            } else if (far3_warith_ontha) {
                tafsir += "السدس 1\\6 فرضا + الباقي تعصيبا بالنفس";
                bast = 1;
                maqam = 6;
                baqi = true;
            } else {
                tafsir += "الباقي تعصيبا بالنفس";
                bast = 0;
                maqam = 1;
                baqi = true;
            }
            warathah.put(Warith.ALAB, new Mirath(tafsir, bast, maqam, baqi));

            // alhajb bi alab
            if (aljad) {
                hajb(Warith.ALJAD, "الجد", "ه", "الأب");
            }
            if (madhab != Madhab.HAMBALI) {
                // تسقط الجدة من جهة الأب بالأب، ولا تسقط الجدة من جهة الأم بالأب
                if (aljadah_li_ab) {
                    hajb(Warith.ALJADAH_LI_AB, "الجدة لأب", "ها", "الأب المدلية به");
                }
            }
            if (alikhwa_alashika >= 1) {
                hajb(Warith.ALIKHWA_ALASHIKA, "الإخوة الأشقاء", "هم", "الأب");
            }
            if (alakhawat_ashakikat >= 1) {
                hajb(Warith.ALAKHAWAT_ASHAKIKAT, "الأخوات الشقيقات", "هن", "الأب");
            }
            if (alikhwa_li_om >= 1) {
                hajb(Warith.ALIKHWA_LI_OM, "الإخوة لأم", "هم", "الأب");
            }
            if (alakhawat_li_om >= 1) {
                hajb(Warith.ALAKHAWAT_LI_OM, "الأخوات لأم", "هن", "الأب");
            }
            if (alikhwa_li_ab >= 1) {
                hajb(Warith.ALIKHWA_LI_AB, "الإخوة لأب", "هم", "الأب");
            }
            if (alakhawat_li_ab >= 1) {
                hajb(Warith.ALAKHAWAT_LI_AB, "الأخوات لأب", "هن", "الأب");
            }
            if (abna_alikhwa_alashika >= 1) {
                hajb(Warith.ABNA_ALIKHWA_ALASHIKA, "أبناء الإخوة الأشقاء", "هم", "الأب");
            }
            if (abna_alikhwa_li_ab >= 1) {
                hajb(Warith.ABNA_ALIKHWA_LI_AB, "أبناء الإخوة لأب", "هم", "الأب");
            }
            if (ala3mam_alashika >= 1) {
                hajb(Warith.ALA3MAM_ALASHIKA, "الأعمام الأشقاء", "هم", "الأب");
            }
            if (ala3mam_li_ab >= 1) {
                hajb(Warith.ALA3MAM_LI_AB, "الأعمام لأب", "هم", "الأب");
            }
            if (abna_ala3mam_alashika >= 1) {
                hajb(Warith.ABNA_ALA3MAM_ALASHIKA, "أبناء الأعمام الأشقاء", "هم", "الأب");
            }
            if (abna_ala3mam_li_ab >= 1) {
                hajb(Warith.ABNA_ALA3MAM_LI_AB, "أبناء الأعمام لأب", "هم", "الأب");
            }
        }

        // mirath aljad
        if (aljad) {
            if (!alab) // no hajb by ab
            {
                tafsir = "الجد يرث ";
                ro2os = 1;
                if (far3_warith_dhakar) {
                    tafsir += "السدس 1\\6 فرضا فقط";
                    bast = 1;
                    maqam = 6;
                    baqi = false;
                } else {
                    if (far3_warith_ontha) {
                        tafsir += "السدس 1\\6 فرضا + الباقي تعصيبا بالنفس";
                        bast = 1;
                        maqam = 6;
                    } else {
                        tafsir += "الباقي تعصيبا بالنفس";
                        bast = 0;
                        maqam = 1;
                    }
                    baqi = true;
                    if (alikhwa_alashika_wa_li_ab) {
                        String muqasim;
                        if (alikhwa_alashika > 0) {
                            muqasim = (alikhwa_alashika == 1) ? "الأخ الشقيق" : "الإخوة الأشقاء";
                            ro2os +=  (alakhawat_ashakikat > 0) ?
                                    (2 * alikhwa_alashika + alakhawat_ashakikat) : alikhwa_alashika;
                        } else {  // alikhwa_li_ab > 0
                            muqasim = (alikhwa_li_ab == 1) ? "الأخ لأب" : "الإخوة لأب";
                            ro2os +=  (alakhawat_li_ab > 0) ?
                                    (2 * alikhwa_li_ab + alakhawat_li_ab) : alikhwa_li_ab;                        }
                        tafsir += " ومقاسمة مع " + muqasim;
                    }
                }
                warathah.put(Warith.ALJAD, new Mirath(tafsir, bast, maqam, baqi, 1, ro2os));
            }

            // alhajb by aljad, we put it even if there is hajb by ab for detailed info
            if (alikhwa_li_om >= 1) {
                hajb(Warith.ALIKHWA_LI_OM, "الإخوة لأم", "هم", "الجد");
            }
            if (alakhawat_li_om >= 1) {
                hajb(Warith.ALAKHAWAT_LI_OM, "الأخوات لأم", "هن", "الجد");
            }
            if (abna_alikhwa_alashika >= 1) {
                hajb(Warith.ABNA_ALIKHWA_ALASHIKA, "أبناء الإخوة الأشقاء", "هم", "الجد");
            }
            if (abna_alikhwa_li_ab >= 1) {
                hajb(Warith.ABNA_ALIKHWA_LI_AB, "أبناء الإخوة لأب", "هم", "الجد");
            }
            if (ala3mam_alashika >= 1) {
                hajb(Warith.ALA3MAM_ALASHIKA, "الأعمام الأشقاء", "هم", "الجد");
            }
            if (ala3mam_li_ab >= 1) {
                hajb(Warith.ALA3MAM_LI_AB, "الأعمام لأب", "هم", "الجد");
            }
            if (abna_ala3mam_alashika >= 1) {
                hajb(Warith.ABNA_ALA3MAM_ALASHIKA, "أبناء الأعمام الأشقاء", "هم", "الجد");
            }
            if (abna_ala3mam_li_ab >= 1) {
                hajb(Warith.ABNA_ALA3MAM_LI_AB, "أبناء الأعمام لأب", "هم", "الجد");
            }
        }

        // mirath alom
        if (alom) {
            tafsir = "الأم ترث ";
            if (jam3_alikhwa || far3_warith) {
                tafsir += "السدس 1\\6 فرضا";
                maqam = 6;
                baqi = false;
            } else if ((alab) && ((azawjat > 0) || (zawj))) {
                tafsir = "الأم ترث ثلث 1\\3 الباقي";
                maqam = 3;
                baqi = true;
                special_case = "مسألة الغرّاوين";
            } else {
                tafsir += "الثلث 1\\3 فرضا";
                maqam = 3;
                baqi = false;
            }
            warathah.put(Warith.ALOM, new Mirath(tafsir, 1, maqam, baqi));

            // alhajb bi alom
            if (aljadah_li_ab) {
                hajb(Warith.ALJADAH_LI_AB, "الجدة لأب", "تها", "الأم");
            }
            if (aljadah_li_om) {
                hajb(Warith.ALJADAH_LI_OM, "الجدة لأم", "تها", "الأم");
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
                warathah.put(Warith.ALJADAH_LI_AB, new Mirath(tafsir, 1, 6, false, 1, ro2os));
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
                warathah.put(Warith.ALJADAH_LI_OM, new Mirath(tafsir, 1, 6, false, 1,  ro2os));
            }
        }

        // mirath alikhwa li om
        if (awlad_alom > 0) {
            if (far3_warith) {
                if (alikhwa_li_om > 0) {
                    hajb(Warith.ALIKHWA_LI_OM, "الإخوة لأم", "هم", "الفرع الوارث");
                }
                if (alakhawat_li_om > 0) {
                    hajb(Warith.ALAKHAWAT_LI_OM, "الأخوات لأم", "هن", "الفرع الوارث");
                }
            } else if (!asl_warith_dhaker) { // hajb by asl warith dhaker done above
                if (awlad_alom > 1) {
                    if (alikhwa_li_om > 0) {
                        ism = (alikhwa_li_om == 1) ? "الأخ لأم" : "الإخوة لأم";
                        dhamir = (alikhwa_li_om == 1) ?  "" : "ون";
                        tafsir = ism + " يشترك" + dhamir + " بالتساوي مع أولاد الأم في الثلث 1\\3 فرضا";
                        warathah.put(Warith.ALIKHWA_LI_OM, new Mirath(tafsir, 1, 3, false, alikhwa_li_om, awlad_alom));
                    }
                    if (alakhawat_li_om > 0) {
                        ism = (alakhawat_li_om == 1) ? "الأخت لأم": "الأخوات لأم";
                        dhamir = (alakhawat_li_om == 1) ? "" : "ن";
                        tafsir = ism + " تشترك" + dhamir + " بالتساوي مع أولاد الأم في الثلث 1\\3 فرضا";
                        warathah.put(Warith.ALAKHAWAT_LI_OM, new Mirath(tafsir, 1, 3, false, alakhawat_li_om, awlad_alom));
                    }
                } else if (alikhwa_li_om == 1) {
                    warathah.put(Warith.ALIKHWA_LI_OM, new Mirath("الأخ لأم يرث السدس 1\\6 فرضا", 1, 6));
                } else { // alakhawat_li_om == 1
                    warathah.put(Warith.ALAKHAWAT_LI_OM, new Mirath("الأخت لأم ترث السدس 1\\6 فرضا", 1, 6));
                }
            }
        }

        // mirath albanat ma3a 3adam wojud alabna
        if (alabna == 0) {
            if (albanat == 1) {
                warathah.put(Warith.ALBANAT, new Mirath("البنت ترث النصف 1\\2 فرضا", 1, 2));
                if ((abna_alabna == 0) && (banat_alabna > 0)) {
                    tafsir = (banat_alabna == 1) ? "بنت الابن ترث" : "بنات الأبناء تشتركن بالتساوي في";
                    tafsir += " السدس 1\\6 تتمة الثلثين فرضا";
                    warathah.put(Warith.BANAT_ALABNA, new Mirath(tafsir, 1, 6, false, banat_alabna));
                }
            } else if (albanat >= 2) {
                warathah.put(Warith.ALBANAT, new Mirath("البنات تشتركن بالتساوي في الثلثين 2\\3 فرضا", 2, 3, false, albanat));
                if ((abna_alabna == 0) && (banat_alabna > 0)) {
                    ism = (banat_alabna == 1) ? "بنت الابن" : "بنات الأبناء";
                    dhamir = (banat_alabna == 1) ? "ها" : "هن";
                    hajb(Warith.BANAT_ALABNA, ism, dhamir, "الجمع من البنات");
                }
            } else { // albanat == 0
                // same logic holds for 2nd generation if present
                if ((abna_alabna == 0) && (banat_alabna > 0)) {
                    if (banat_alabna == 1) {
                        bast = 1; maqam = 2;
                        tafsir = "بنت الابن ترث النصف 1\\2 فرضا";
                    } else {
                        bast = 2; maqam = 3;
                        tafsir = "بنات الأبناء تشتركن بالتساوي في الثلثين 2\\3 فرضا";
                    }
                    warathah.put(Warith.BANAT_ALABNA, new Mirath(tafsir, bast, maqam, false, banat_alabna));
                }
            }
        }

        // mirath alakhawat ashakikat 3inda 3adam wojud alikhwa alashika
        if ((alakhawat_ashakikat > 0) && !alab && !far3_warith_dhakar && (alikhwa_alashika == 0)) {
            if (!far3_warith_ontha) {
                baqi = false;
                if (alakhawat_ashakikat == 1) {
                    bast = 1; maqam = 2;
                    tafsir = "الأخت الشقيقة ترث النصف 1\\2 فرضا";
                } else { // (alakhawat_ashakikat >= 2)
                    bast = 2; maqam = 3;
                    tafsir ="الأخوات الشقيقات تشتركن بالتساوي في الثلثين 2\\3 فرضا";
                }
            } else { // presence of far3_warith_ontha
                baqi = true;
                bast = 0; maqam = 1;
                tafsir = (alakhawat_ashakikat == 1) ? "الأخت الشقيقة ترث" : "الأخوات الشقيقات تشتركن بالتساوي في";
                tafsir += " الباقي تعصيبا مع الغير";
            }
            warathah.put(Warith.ALAKHAWAT_ASHAKIKAT, new Mirath(tafsir, bast, maqam, baqi, alakhawat_ashakikat, alakhawat_ashakikat));
        }

        // mirath alakhawat li ab 3inda 3adam wojud alikhwa
        if ((alakhawat_li_ab > 0) && !alab && !far3_warith_dhakar && !alikhwa_alashika_wa_li_ab) {
            if (!far3_warith_ontha) {
                if (alakhawat_ashakikat == 0) {
                    if (alakhawat_li_ab == 1) {
                        warathah.put(Warith.ALAKHAWAT_LI_AB, new Mirath("الأخت لأب ترث النصف 1\\2 فرضا", 1, 2));
                    } else { // (alakhawat_li_ab >= 2)
                        warathah.put(Warith.ALAKHAWAT_LI_AB, new Mirath("الأخوات لأب تشتركن بالتساوي في الثلثين 2\\3 فرضا", 2, 3, false, alakhawat_li_ab));
                    }
                } else if (alakhawat_ashakikat == 1) {
                    if (alakhawat_li_ab == 1) {
                        warathah.put(Warith.ALAKHAWAT_LI_AB, new Mirath("الأخت لأب ترث السدس 1\\6 تتمة الثلثين فرضا", 1, 6));
                    } else {// (alakhawat_li_ab >= 2)
                        warathah.put(Warith.ALAKHAWAT_LI_AB, new Mirath("الأخوات لأب تشتركن بالتساوي في السدس 1\\6 تتمة الثلثين فرضا", 1, 6, false, alakhawat_li_ab));
                    }
                } else { //(alakhawat_alshakikat > 1)
                    if (alakhawat_li_ab == 1) {
                        ism = "الأخت لأب";
                        dhamir = "ها";
                    } else {
                        ism = "الأخوات لأب";
                        dhamir = "هن";
                    }
                    hajb(Warith.ALAKHAWAT_LI_AB, ism, dhamir, "الأخوات الشقيقات");
                }
            } else { // far3_warith_ontha
                if (alakhawat_ashakikat == 0) {
                    tafsir = (alakhawat_li_ab == 1) ? "الأخت لأب ترث" : "الأخوات لأب تشتركن بالتساوي في";
                    tafsir += " الباقي تعصيبا مع الغير";
                    warathah.put(Warith.ALAKHAWAT_LI_AB, new Mirath(tafsir, 0, 1, true, alakhawat_li_ab, alakhawat_li_ab));
                } else {
                    hajib = (alakhawat_ashakikat == 1) ? "الأخت الشقيقة" : "الأخوات الشقيقات";
                    if (alakhawat_li_ab == 1) {
                        ism = "الأخت لأب";
                        dhamir = "تها";
                    } else {
                        ism = "الأخوات لأب";
                        dhamir = "هن";
                    }
                    hajb(Warith.ALAKHAWAT_LI_AB, ism, dhamir, hajib);
                }
            }
        }

        //mirath alabna + albanat 3inda woujoud alabna
        if (alabna > 0) {
            if (albanat == 0) {
                tafsir = (alabna == 1) ? "الابن يرث الباقي تعصيبا بالنفس" : "الأبناء يشتركون في الباقي تعصيبا بالنفس";
                warathah.put(Warith.ALABNA, new Mirath(tafsir, 0, 1, true, alabna, alabna));
            } else {
                String tafsir2;
                if (albanat == 1) {
                    tafsir = (alabna == 1) ? "الابن يشترك مع البنت في الباقي تعصيبا بالنفس" : "الأبناء يشتركون مع البنت في الباقي تعصيبا بالنفس";
                    tafsir2 = (alabna == 1) ? "البنت تشترك مع الابن في الباقي تعصيبا" : "البنت تشترك مع الأبناء في الباقي تعصيبا";
                } else { // albanat > 1
                    tafsir = (alabna == 1) ? "الابن يشترك مع البنات في الباقي تعصيبا بالنفس" : "الأبناء يشتركون مع البنات في الباقي تعصيبا بالنفس";
                    tafsir2 = (alabna == 1) ? "البنات تشتركن مع الابن في الباقي تعصيبا بالغير" : "البنات تشتركن مع الأبناء في الباقي تعصيبا بالغير";
                }
                ro2os = 2 * alabna + albanat;

                tafsir += " (للذكر مثل حظ الأنثيين)";
                warathah.put(Warith.ALABNA, new Mirath(tafsir, 0, 1, true, alabna, ro2os));

                tafsir2 += " (للذكر مثل حظ الأنثيين)";
                warathah.put(Warith.ALBANAT, new Mirath(tafsir2, 0, 1, true, albanat, ro2os));
            }

            // hajb by alabna
            hajib = (alabna == 1) ? "الابن" : "الأبناء";
            if (abna_alabna > 0) {
                ism = (abna_alabna == 1) ? "ابن الابن" : "أبناء الأبناء";
                dhamir = (abna_alabna == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALABNA, ism, dhamir, hajib);
            }
            if (banat_alabna > 0) {
                ism = (banat_alabna == 1) ? "بنت الابن" : "بنات الأبناء";
                dhamir = (banat_alabna == 1) ? "ها" : "هن";
                hajb(Warith.BANAT_ALABNA, ism, dhamir, hajib);
            }
            if (alikhwa_alashika > 0) {
                ism = (alikhwa_alashika == 1) ? "الأخ الشقيق" : "الإخوة الأشقاء";
                dhamir = (alikhwa_alashika == 1) ? "ه" : "هم";
                hajb(Warith.ALIKHWA_ALASHIKA, ism, dhamir, hajib);
            }
            if (alakhawat_ashakikat > 0) {
                ism = (alakhawat_ashakikat == 1) ? "الأخت الشقيقة" : "الأخوات الشقيقات";
                dhamir = (alakhawat_ashakikat == 1) ? "ها" : "هن";
                hajb(Warith.ALAKHAWAT_ASHAKIKAT, ism, dhamir, hajib);
            }
            if (alikhwa_li_ab > 0) {
                ism = (alikhwa_li_ab == 1) ? "الأخ لأب" : "الإخوة لأب";
                dhamir = (alikhwa_li_ab == 1) ? "ه" : "هم";
                hajb(Warith.ALIKHWA_LI_AB, ism, dhamir, hajib);
            }
            if (alakhawat_li_ab > 0) {
                ism = (alakhawat_li_ab == 1) ? "الأخت لأب" : "الأخوات لأب";
                dhamir = (alakhawat_li_ab == 1) ? "ها" : "هن";
                hajb(Warith.ALAKHAWAT_LI_AB, ism, dhamir, hajib);
            }
            //الحجب تم بالفرع الوارث سابقا في ميراث أولاد الأم
           /* if (alikhwa_li_om > 0) {
                ism = (alikhwa_li_om == 1) ? "الأخ لأم" : "الإخوة لأم";
                dhamir = (alikhwa_li_om == 1) ? "ه" : "هم";
                hajb(Warith.ALIKHWA_LI_OM, ism, dhamir, hajib);
            }
            if (alakhawat_li_om > 0) {
                ism = (alakhawat_li_om == 1) ? "الأخت لأم" : "الأخوات لأم";
                dhamir = (alakhawat_li_om == 1) ? "ها" : "هن";
                hajb(Warith.ALAKHAWAT_LI_OM, ism, dhamir, hajib);
            } */
            if (abna_alikhwa_alashika > 0) {
                ism = (abna_alikhwa_alashika == 1) ? "ابن الأخ الشقيق" : "أبناء الإخوة الأشقاء";
                dhamir = (abna_alikhwa_alashika == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALIKHWA_ALASHIKA, ism, dhamir, hajib);
            }
            if (abna_alikhwa_li_ab > 0) {
                ism = (abna_alikhwa_li_ab == 1) ? "ابن الأخ لأب" : "أبناء الإخوة لأب";
                dhamir = (abna_alikhwa_li_ab == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALIKHWA_LI_AB, ism, dhamir, hajib);
            }
            if (ala3mam_alashika > 0) {
                ism = (ala3mam_alashika == 1) ? "العم الشقيق" : "الأعمام الأشقاء";
                dhamir = (ala3mam_alashika == 1) ? "ه" : "هم";
                hajb(Warith.ALA3MAM_ALASHIKA, ism, dhamir, hajib);
            }
            if (ala3mam_li_ab > 0) {
                ism = (ala3mam_li_ab == 1) ? "العم لأب" : "الأعمام لأب";
                dhamir = (ala3mam_li_ab == 1) ? "ه" : "هم";
                hajb(Warith.ALA3MAM_LI_AB, ism, dhamir, hajib);
            }
            if (abna_ala3mam_alashika > 0) {
                ism = (abna_ala3mam_alashika == 1) ? "ابن العم الشقيق" : "أبناء الأعمام الأشقاء";
                dhamir = (abna_ala3mam_alashika == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALA3MAM_ALASHIKA, ism, dhamir, hajib);
            }
            if (abna_ala3mam_li_ab > 0) {
                ism = (abna_ala3mam_li_ab == 1) ? "ابن العم لأب" : "أبناء الأعمام لأب";
                dhamir = (abna_ala3mam_li_ab == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALA3MAM_LI_AB, ism, dhamir, hajib);
            }
        }

        // mirath abna_alabna + banat_alabna
        if ((abna_alabna > 0) && (alabna == 0)) {
            if (banat_alabna == 0) {
                tafsir = (abna_alabna == 1) ? "ابن الابن يرث الباقي تعصيبا بالنفس" :  "أبناء الأبناء يشتركون في الباقي تعصيبا بالنفس";
                warathah.put(Warith.ABNA_ALABNA, new Mirath(tafsir, 0, 1, true, abna_alabna, abna_alabna));
            } else {
                String str2;
                if (banat_alabna == 1) {
                    tafsir = (abna_alabna == 1) ? "ابن الابن يشترك مع بنت الابن في الباقي تعصيبا بالنفس" : "أبناء الأبناء يشتركون مع بنت الابن في الباقي تعصيبا بالنفس";
                    str2 = (abna_alabna == 1) ? "بنت الابن تشترك مع ابن الابن في الباقي تعصيبا بالغير" : "بنت الابن تشترك مع أبناء الأبناء في الباقي تعصيبا بالغير";
                } else { // banat_alabna > 1
                    tafsir = (abna_alabna == 1) ? "ابن الابن يشترك مع بنات الأبناء في الباقي تعصيبا بالنفس" : "أبناء الأبناء يشتركون مع بنات الأبناء في الباقي تعصيبا بالنفس";
                    str2 = (abna_alabna == 1) ? "بنات الأبناء تشتركن مع ابن الابن في الباقي تعصيبا بالغير" : "بنات الأبناء تشتركن مع أبناء الأبناء في الباقي تعصيبا بالغير";
                }
                ro2os = 2 * abna_alabna + banat_alabna;

                tafsir += " (للذكر مثل حظ الأنثيين)";
                warathah.put(Warith.ABNA_ALABNA, new Mirath(tafsir, 0, 1, true, abna_alabna, ro2os));

                str2 += " (للذكر مثل حظ الأنثيين)";
                warathah.put(Warith.BANAT_ALABNA, new Mirath(str2, 0, 1, true, banat_alabna, ro2os));
            }

            // hajb by abna alabna
            hajib = (abna_alabna == 1) ? "ابن الابن" : "أبناء الأبناء";
            if (alikhwa_alashika > 0) {
                ism = (alikhwa_alashika == 1) ? "الأخ الشقيق" : "الإخوة الأشقاء";
                dhamir = (alikhwa_alashika == 1) ? "ه" : "هم";
                hajb(Warith.ALIKHWA_ALASHIKA, ism, dhamir, hajib);
            }
            if (alakhawat_ashakikat > 0) {
                ism = (alakhawat_ashakikat == 1) ? "الأخت الشقيقة" : "الأخوات الشقيقات";
                dhamir = (alakhawat_ashakikat == 1) ? "ها" : "هن";
                hajb(Warith.ALAKHAWAT_ASHAKIKAT, ism, dhamir, hajib);
            }
            if (alikhwa_li_ab > 0) {
                ism = (alikhwa_li_ab == 1) ? "الأخ لأب" : "الإخوة لأب";
                dhamir = (alikhwa_li_ab == 1) ? "ه" : "هم";
                hajb(Warith.ALIKHWA_LI_AB, ism, dhamir, hajib);
            }
            if (alakhawat_li_ab > 0) {
                ism = (alakhawat_li_ab == 1) ? "الأخت لأب" : "الأخوات لأب";
                dhamir = (alakhawat_li_ab == 1) ? "ها" : "هن";
                hajb(Warith.ALAKHAWAT_LI_AB, ism, dhamir, hajib);
            }
            //الحجب تم بالفرع الوارث سابقا في ميراث أولاد الأم
         /*   if (alikhwa_li_om > 0) {
                ism = (alikhwa_li_om == 1) ? "الأخ لأم" : "الإخوة لأم";
                dhamir = (alikhwa_li_om == 1) ? "ه" : "هم";
                hajb(Warith.ALIKHWA_LI_OM, ism, dhamir, hajib);
            }
            if (alakhawat_li_om > 0) {
                ism = (alakhawat_li_om == 1) ? "الأخت لأم" : "الأخوات لأم";
                dhamir = (alakhawat_li_om == 1) ? "ها" : "هن";
                hajb(Warith.ALAKHAWAT_LI_OM, ism, dhamir, hajib);
            }*/
            if (abna_alikhwa_alashika > 0) {
                ism = (abna_alikhwa_alashika == 1) ? "ابن الأخ الشقيق" : "أبناء الإخوة الأشقاء";
                dhamir = (abna_alikhwa_alashika == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALIKHWA_ALASHIKA, ism, dhamir, hajib);
            }
            if (abna_alikhwa_li_ab > 0) {
                ism = (abna_alikhwa_li_ab == 1) ? "ابن الأخ لأب" : "أبناء الإخوة لأب";
                dhamir = (abna_alikhwa_li_ab == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALIKHWA_LI_AB, ism, dhamir, hajib);
            }
            if (ala3mam_alashika > 0) {
                ism = (ala3mam_alashika == 1) ? "العم الشقيق" : "الأعمام الأشقاء";
                dhamir = (ala3mam_alashika == 1) ? "ه" : "هم";
                hajb(Warith.ALA3MAM_ALASHIKA, ism, dhamir, hajib);
            }
            if (ala3mam_li_ab > 0) {
                ism = (ala3mam_li_ab == 1) ? "العم لأب" : "الأعمام لأب";
                dhamir = (ala3mam_li_ab == 1) ? "ه" : "هم";
                hajb(Warith.ALA3MAM_LI_AB, ism, dhamir, hajib);
            }
            if (abna_ala3mam_alashika > 0) {
                ism = (abna_ala3mam_alashika == 1) ? "ابن العم الشقيق" : "أبناء الأعمام الأشقاء";
                dhamir = (abna_ala3mam_alashika == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALA3MAM_ALASHIKA, ism, dhamir, hajib);
            }
            if (abna_ala3mam_li_ab > 0) {
                ism = (abna_ala3mam_li_ab == 1) ? "ابن العم لأب" : "أبناء الأعمام لأب";
                dhamir = (abna_ala3mam_li_ab == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALA3MAM_LI_AB, ism, dhamir, hajib);
            }
        }

        // mirath alab and hajb by alab were previously calculated
        // mirath aljad and hajb by aljad were previously calculated

        // mirath alikhwa alashika + alakhawat ashakikat
        if (alikhwa_alashika > 0 && !far3_warith_dhakar && !alab) {
            if (alakhawat_ashakikat == 0) {
                tafsir = (alikhwa_alashika == 1) ? "الأخ الشقيق يرث الباقي تعصيبا بالنفس" : "الإخوة الأشقاء يشتركون بالتساوي في الباقي تعصيبا بالنفس";
                tafsir += (aljad) ? " مقاسمة مع الجد" : "";
                warathah.put(Warith.ALIKHWA_ALASHIKA, new Mirath(tafsir, 0, 1, true, alikhwa_alashika, alikhwa_alashika));
            } else {
                String tafsir2;

                if (alakhawat_ashakikat == 1) {
                    tafsir = (alikhwa_alashika == 1) ? "الأخ الشقيق يشترك مع الأخت الشقيقة في الباقي تعصيبا بالنفس" : "الإخوة الأشقاء يشتركون مع الأخت الشقيقة في الباقي تعصيبا بالنفس";
                    tafsir2 = (alikhwa_alashika == 1) ? "الأخت الشقيقة تشترك مع الأخ الشقيق في الباقي تعصيبا بالغير" : "الأخت الشقيقة تشترك مع الإخوة الأشقاء في الباقي تعصيبا بالغير";
                } else { // alakhawat_ashakikat > 1
                    tafsir = (alikhwa_alashika == 1) ? "الأخ الشقيق يشترك مع الأخوات الشقيقات في الباقي تعصيبا بالنفس" : "الإخوة الأشقاء يشتركون مع الأخوات الشقيقات في الباقي تعصيبا بالنفس";
                    tafsir2 = (alikhwa_alashika == 1) ? "الأخوات الشقيقات تشتركن مع الأخ الشقيق في الباقي تعصيبا بالغير" : "الأخوات الشقيقات تشتركن مع الإخوة الأشقاء في الباقي تعصيبا بالغير";
                }
                ro2os = 2 * alikhwa_alashika + alakhawat_ashakikat;

                tafsir += " (للذكر مثل حظ الأنثيين)";
                tafsir += (aljad) ? " مقاسمة مع الجد" : "";
                warathah.put(Warith.ALIKHWA_ALASHIKA, new Mirath(tafsir, 0, 1, true, alikhwa_alashika, ro2os));

                tafsir2 += " (للذكر مثل حظ الأنثيين)";
                tafsir2 += (aljad) ? " مقاسمة مع الجد" : "";
                warathah.put(Warith.ALAKHAWAT_ASHAKIKAT, new Mirath(tafsir2, 0, 1, true, alakhawat_ashakikat, ro2os));
            }

            // الحجب بالإخوة الأشقاء
            hajib = (alikhwa_alashika == 1) ? "الأخ الشقيق" : "الإخوة الأشقاء";
            if (alikhwa_li_ab > 0) {
                ism = (alikhwa_li_ab == 1) ? "الأخ لأب" : "الإخوة لأب";
                dhamir = (alikhwa_li_ab == 1) ? "ه" : "هم";
                hajb(Warith.ALIKHWA_LI_AB, ism, dhamir, hajib);
            }
            if (alakhawat_li_ab > 0) {
                ism = (alakhawat_li_ab == 1) ? "الأخت لأب" : "الأخوات لأب";
                dhamir = (alakhawat_li_ab == 1) ? "ها" : "هن";
                hajb(Warith.ALAKHAWAT_LI_AB, ism, dhamir, hajib);
            }
            if (abna_alikhwa_alashika > 0) {
                ism = (abna_alikhwa_alashika == 1) ? "ابن الأخ الشقيق" : "أبناء الإخوة الأشقاء";
                dhamir = (abna_alikhwa_alashika == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALIKHWA_ALASHIKA, ism, dhamir, hajib);
            }
            if (abna_alikhwa_li_ab > 0) {
                ism = (abna_alikhwa_li_ab == 1) ? "ابن الأخ لأب" : "أبناء الإخوة لأب";
                dhamir = (abna_alikhwa_li_ab == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALIKHWA_LI_AB, ism, dhamir, hajib);
            }
            if (ala3mam_alashika > 0) {
                ism = (ala3mam_alashika == 1) ? "العم الشقيق" : "الأعمام الأشقاء";
                dhamir = (ala3mam_alashika == 1) ? "ه" : "هم";
                hajb(Warith.ALA3MAM_ALASHIKA, ism, dhamir, hajib);
            }
            if (ala3mam_li_ab > 0) {
                ism = (ala3mam_li_ab == 1) ? "العم لأب" : "الأعمام لأب";
                dhamir = (ala3mam_li_ab == 1) ? "ه" : "هم";
                hajb(Warith.ALA3MAM_LI_AB, ism, dhamir, hajib);
            }
            if (abna_ala3mam_alashika > 0) {
                ism = (abna_ala3mam_alashika == 1) ? "ابن العم الشقيق" : "أبناء الأعمام الأشقاء";
                dhamir = (abna_ala3mam_alashika == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALA3MAM_ALASHIKA, ism, dhamir, hajib);
            }
            if (abna_ala3mam_li_ab > 0) {
                ism = (abna_ala3mam_li_ab == 1) ? "ابن العم لأب" : "أبناء الأعمام لأب";
                dhamir = (abna_ala3mam_li_ab == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALA3MAM_LI_AB, ism, dhamir, hajib);
            }
        }

        // mirath alikhwa li ab + alakhawat li ab
        if ((alikhwa_li_ab > 0) && !far3_warith_dhakar && !alab && (alikhwa_alashika == 0)) {
            if (alakhawat_li_ab == 0) {
                tafsir = (alikhwa_li_ab == 1) ? "الأخ لأب يرث الباقي تعصيبا بالنفس" : "الإخوة لأب يشتركون بالتساوي في الباقي تعصيبا بالنفس";
                tafsir += (aljad) ? " مقاسمة مع الجد" : "";
                warathah.put(Warith.ALIKHWA_LI_AB, new Mirath(tafsir, 0, 1, true, alikhwa_li_ab, alikhwa_li_ab));
            } else {
                String tafsir2;

                if (alakhawat_li_ab == 1) {
                    tafsir = (alikhwa_li_ab == 1) ? "الأخ لأب يشترك مع الأخت لأب في الباقي تعصيبا بالنفس" : "الإخوة لأب يشتركون مع الأخت لأب في الباقي تعصيبا بالنفس";
                    tafsir2 = (alikhwa_li_ab == 1) ? "الأخت لأب تشترك مع الأخ لأب في الباقي تعصيبا بالغير" : "الأخت لأب تشترك مع الإخوة لأب في الباقي تعصيبا بالغير";
                } else { // alakhawat_li_ab > 1
                    tafsir = (alikhwa_li_ab == 1) ? "الأخ لأب يشترك مع الأخوات لأب في الباقي تعصيبا بالنفس" : "الإخوة لأب يشتركون مع الأخوات لأب في الباقي تعصيبا بالنفس";
                    tafsir2 = (alikhwa_li_ab == 1) ? "الأخوات لأب تشتركن مع الأخ لأب في الباقي تعصيبا بالغير" : "الأخوات لأب تشتركن مع الإخوة لأب في الباقي تعصيبا بالغير";
                }
                ro2os = 2 * alikhwa_li_ab + alakhawat_li_ab;

                tafsir += " (للذكر مثل حظ الأنثيين)";
                tafsir += (aljad) ? " مقاسمة مع الجد" : "";
                warathah.put(Warith.ALIKHWA_LI_AB, new Mirath(tafsir, 0, 1, true, alikhwa_li_ab, ro2os));

                tafsir2 += " (للذكر مثل حظ الأنثيين)";
                tafsir2 += (aljad) ? " مقاسمة مع الجد" : "";
                warathah.put(Warith.ALAKHAWAT_LI_AB, new Mirath(tafsir2, 0, 1, true, alakhawat_li_ab, ro2os));
            }

            // alhajb by alikhwa li ab
            hajib = (alikhwa_li_ab == 1) ? "الأخ لأب" : "الإخوة لأب";
            if (abna_alikhwa_alashika > 0) {
                ism = (abna_alikhwa_alashika == 1) ? "ابن الأخ الشقيق" : "أبناء الإخوة الأشقاء";
                dhamir = (abna_alikhwa_alashika == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALIKHWA_ALASHIKA, ism, dhamir, hajib);
            }
            if (abna_alikhwa_li_ab > 0) {
                ism = (abna_alikhwa_li_ab == 1) ? "ابن الأخ لأب" : "أبناء الإخوة لأب";
                dhamir = (abna_alikhwa_li_ab == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALIKHWA_LI_AB, ism, dhamir, hajib);
            }
            if (ala3mam_alashika > 0) {
                ism = (ala3mam_alashika == 1) ? "العم الشقيق" : "الأعمام الأشقاء";
                dhamir = (ala3mam_alashika == 1) ? "ه" : "هم";
                hajb(Warith.ALA3MAM_ALASHIKA, ism, dhamir, hajib);
            }
            if (ala3mam_li_ab > 0) {
                ism = (ala3mam_li_ab == 1) ? "العم لأب" : "الأعمام لأب";
                dhamir = (ala3mam_li_ab == 1) ? "ه" : "هم";
                hajb(Warith.ALA3MAM_LI_AB, ism, dhamir, hajib);
            }
            if (abna_ala3mam_alashika > 0) {
                ism = (abna_ala3mam_alashika == 1) ? "ابن العم الشقيق" : "أبناء الأعمام الأشقاء";
                dhamir = (abna_ala3mam_alashika == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALA3MAM_ALASHIKA, ism, dhamir, hajib);
            }
            if (abna_ala3mam_li_ab > 0) {
                ism = (abna_ala3mam_li_ab == 1) ? "ابن العم لأب" : "أبناء الأعمام لأب";
                dhamir = (abna_ala3mam_li_ab == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALA3MAM_LI_AB, ism, dhamir, hajib);
            }
        }

        // mirath abna alikhwa alashika
        if ((abna_alikhwa_alashika > 0) && !far3_wa_asl_warith_dhaker && !alikhwa_alashika_wa_li_ab) {
            tafsir = (abna_alikhwa_alashika == 1) ? "ابن الأخ الشقيق يرث الباقي تعصيبا بالنفس" : "أبناء الإخوة الأشقاء يشتركون بالتساوي في الباقي تعصيبا بالنفس";
            warathah.put(Warith.ABNA_ALIKHWA_ALASHIKA, new Mirath(tafsir, 0, 1, true, abna_alikhwa_alashika, abna_alikhwa_alashika));

            // alhajb by abna alikhwa alashika
            hajib = (abna_alikhwa_alashika == 1) ? "ابن الأخ الشقيق" : "أبناء الإخوة الأشقاء";
            if (abna_alikhwa_li_ab > 0) {
                ism = (abna_alikhwa_li_ab == 1) ? "ابن الأخ لأب" : "أبناء الإخوة لأب";
                dhamir = (abna_alikhwa_li_ab == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALIKHWA_LI_AB, ism, dhamir, hajib);
            }
            if (ala3mam_alashika > 0) {
                ism = (ala3mam_alashika == 1) ? "العم الشقيق" : "الأعمام الأشقاء";
                dhamir = (ala3mam_alashika == 1) ? "ه" : "هم";
                hajb(Warith.ALA3MAM_ALASHIKA, ism, dhamir, hajib);
            }
            if (ala3mam_li_ab > 0) {
                ism = (ala3mam_li_ab == 1) ? "العم لأب" : "الأعمام لأب";
                dhamir = (ala3mam_li_ab == 1) ? "ه" : "هم";
                hajb(Warith.ALA3MAM_LI_AB, ism, dhamir, hajib);
            }
            if (abna_ala3mam_alashika > 0) {
                ism = (abna_ala3mam_alashika == 1) ? "ابن العم الشقيق" : "أبناء الأعمام الأشقاء";
                dhamir = (abna_ala3mam_alashika == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALA3MAM_ALASHIKA, ism, dhamir, hajib);
            }
            if (abna_ala3mam_li_ab > 0) {
                ism = (abna_ala3mam_li_ab == 1) ? "ابن العم لأب" : "أبناء الأعمام لأب";
                dhamir = (abna_ala3mam_li_ab == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALA3MAM_LI_AB, ism, dhamir, hajib);
            }
        }

        // mirath abna alikhwa li ab
        if ((abna_alikhwa_li_ab > 0) && !far3_wa_asl_warith_dhaker && !alikhwa_alashika_wa_li_ab && (abna_alikhwa_alashika == 0)) {
            tafsir = (abna_alikhwa_li_ab == 1) ? "ابن الأخ لأب يرث الباقي تعصيبا بالنفس" : "أبناء الإخوة لأب يشتركون بالتساوي في الباقي تعصيبا بالنفس";
            warathah.put(Warith.ABNA_ALIKHWA_LI_AB, new Mirath(tafsir, 0, 1, true, abna_alikhwa_li_ab, abna_alikhwa_li_ab));

            // alhajb by abna alikhwa li ab
            hajib = (abna_alikhwa_li_ab == 1) ? "ابن الأخ لأب" : "أبناء الإخوة لأب";
            if (ala3mam_alashika > 0) {
                ism = (ala3mam_alashika == 1) ? "العم الشقيق" : "الأعمام الأشقاء";
                dhamir = (ala3mam_alashika == 1) ? "ه" : "هم";
                hajb(Warith.ALA3MAM_ALASHIKA, ism, dhamir, hajib);
            }
            if (ala3mam_li_ab > 0) {
                ism = (ala3mam_li_ab == 1) ? "العم لأب" : "الأعمام لأب";
                dhamir = (ala3mam_li_ab == 1) ? "ه" : "هم";
                hajb(Warith.ALA3MAM_LI_AB, ism, dhamir, hajib);
            }
            if (abna_ala3mam_alashika > 0) {
                ism = (abna_ala3mam_alashika == 1) ? "ابن العم الشقيق" : "أبناء الأعمام الأشقاء";
                dhamir = (abna_ala3mam_alashika == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALA3MAM_ALASHIKA, ism, dhamir, hajib);
            }
            if (abna_ala3mam_li_ab > 0) {
                ism = (abna_ala3mam_li_ab == 1) ? "ابن العم لأب" : "أبناء الأعمام لأب";
                dhamir = (abna_ala3mam_li_ab == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALA3MAM_LI_AB, ism, dhamir, hajib);
            }
        }

        // mirath ala3mam alashika
        if ((ala3mam_alashika > 0) && !far3_wa_asl_warith_dhaker && !alikhwa_wa_abna_alikhwa) {
            tafsir = (ala3mam_alashika == 1) ? "العم الشقيق يرث الباقي تعصيبا بالنفس" : "الأعمام الأشقاء يشتركون بالتساوي في الباقي تعصيبا بالنفس";
            warathah.put(Warith.ALA3MAM_ALASHIKA, new Mirath(tafsir, 0, 1, true, ala3mam_alashika, ala3mam_alashika));

            // alhajb by mirath ala3mam alashika
            hajib = (ala3mam_alashika == 1) ? "العم الشقيق" : "الأعمام الأشقاء";
            if (ala3mam_li_ab > 0) {
                ism = (ala3mam_li_ab == 1) ? "العم لأب" : "الأعمام لأب";
                dhamir = (ala3mam_li_ab == 1) ? "ه" : "هم";
                hajb(Warith.ALA3MAM_LI_AB, ism, dhamir, hajib);
            }
            if (abna_ala3mam_alashika > 0) {
                ism = (abna_ala3mam_alashika == 1) ? "ابن العم الشقيق" : "أبناء الأعمام الأشقاء";
                dhamir = (abna_ala3mam_alashika == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALA3MAM_ALASHIKA, ism, dhamir, hajib);
            }
            if (abna_ala3mam_li_ab > 0) {
                ism = (abna_ala3mam_li_ab == 1) ? "ابن العم لأب" : "أبناء الأعمام لأب";
                dhamir = (abna_ala3mam_li_ab == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALA3MAM_LI_AB, ism, dhamir, hajib);
            }
        }

        // mirath ala3mam li ab
        if ((ala3mam_li_ab > 0) && !far3_wa_asl_warith_dhaker && !alikhwa_wa_abna_alikhwa && (ala3mam_alashika == 0)) {
            tafsir = (ala3mam_li_ab == 1) ? "العم لأب يرث الباقي تعصيبا بالنفس" : "الأعمام لأب يشتركون بالتساوي في الباقي تعصيبا بالنفس";
            warathah.put(Warith.ALA3MAM_LI_AB, new Mirath(tafsir, 0, 1, true, ala3mam_li_ab, ala3mam_li_ab));

            // alhajb by mirath ala3mam li ab
            hajib = (ala3mam_li_ab == 1) ? "العم لأب" : "الأعمام لأب";
            if (abna_ala3mam_alashika > 0) {
                ism = (abna_ala3mam_alashika == 1) ? "ابن العم الشقيق" : "أبناء الأعمام الأشقاء";
                dhamir = (abna_ala3mam_alashika == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALA3MAM_ALASHIKA, ism, dhamir, hajib);
            }
            if (abna_ala3mam_li_ab > 0) {
                ism = (abna_ala3mam_li_ab == 1) ? "ابن العم لأب" : "أبناء الأعمام لأب";
                dhamir = (abna_ala3mam_li_ab == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALA3MAM_LI_AB, ism, dhamir, hajib);
            }
        }

        // mirath abna ala3mam alashika
        if ((abna_ala3mam_alashika > 0) && !far3_wa_asl_warith_dhaker && !alikhwa_wa_abna_alikhwa && !ala3mam) {
            tafsir = (abna_ala3mam_alashika == 1) ? "ابن العم الشقيق يرث الباقي تعصيبا بالنفس" : "أبناء الأعمام الأشقاء يشتركون بالتساوي في الباقي تعصيبا بالنفس";
            warathah.put(Warith.ABNA_ALA3MAM_ALASHIKA, new Mirath(tafsir, 0, 1, true, abna_ala3mam_alashika, abna_ala3mam_alashika));

            // alhajb by abna ala3mam alashika
            hajib = (abna_ala3mam_alashika == 1) ? "ابن العم الشقيق" : "أبناء الأعمام الأشقاء";
            if (abna_ala3mam_li_ab > 0) {
                ism = (abna_ala3mam_li_ab == 1) ? "ابن العم لأب" : "أبناء الأعمام لأب";
                dhamir = (abna_ala3mam_li_ab == 1) ? "ه" : "هم";
                hajb(Warith.ABNA_ALA3MAM_LI_AB, ism, dhamir, hajib);
            }
        }

        // mirath abnaa ala3mam li ab
        if ((abna_ala3mam_li_ab > 0) && !far3_wa_asl_warith_dhaker && !alikhwa_wa_abna_alikhwa && !ala3mam && (abna_ala3mam_alashika == 0)) {
            tafsir = (abna_ala3mam_li_ab == 1) ? "ابن العم لأب يرث الباقي تعصيبا بالنفس" : "أبناء الأعمام لأب يشتركون بالتساوي في الباقي تعصيبا بالنفس";
            warathah.put(Warith.ABNA_ALA3MAM_LI_AB, new Mirath(tafsir, 0, 1, true, abna_ala3mam_li_ab, abna_ala3mam_li_ab));
        }

        // al-hissab
        StringBuilder result = new StringBuilder();
        for (Mirath m : warathah.values()) {
            result.append("- ").append(m.getTafsir()).append(".\n");

            if (m.getMaqam() != 1) {
                asl_mas2ala = lcm(asl_mas2ala, m.getMaqam());
            }

            if (m.baqi()) {
                adad_3assabat = m.getRo2os();
                break;
            }
        }
        result.append("\n" + "أصل المسألة: " + asl_mas2ala + "\n");
        if (adad_3assabat > 0)  {
            result.append("الباقي يُقسم على: " + adad_3assabat + "\n");
        }

//        result.append(String.format("\t | \t | \t\n", ));

        return result.toString();
    }

    private int lcm(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        int aa = Math.abs(a);
        int ab = Math.abs(b);
        int h = Math.max(aa, ab);
        int l = Math.min(aa, ab);
        int lcm = h;
        while (lcm % l != 0) {
            lcm += h;
        }
        return lcm;
    }
}
