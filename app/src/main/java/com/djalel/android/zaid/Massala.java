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

import java.util.ArrayList;
import java.util.List;

public class Massala {
    WarathaInput mInput;

    List<Mirath> mWarathah;
    List<Mirath> mMahjoobin;
    private boolean mHal;
    private boolean mFardh;
    private boolean mTa3seeb;
    private boolean mTassawi;           // تعصيب بالتساوي أو للذكر مثل حظ الأنثيين
    private boolean mIstighraq;
    private boolean mShirkaTa3seeb;     // اشتراك عدة ورثة مختلفين في الباقي (جد وإخوة أو أبناء وبتات)

    private boolean m_alakhawat_ashakikat_3assabat_ma3a_lghayr;
    private boolean m_alakhawat_3assabat_ma3a_lghayr;

    private int mAsl;
    private int mBaqi;
    private int mAdadRo2osAlbaqi;
    private int mAwl;
    private int mMissah;
    private String mSpecialCase;

    private int mJadIndex;              // initial position of Aljad in mWarathah

    private enum Naw3 {  // TODO public or package private
        NAW3_NONE,
        NAW3_ADILA,
        NAW3_RAD,
        NAW3_AWL,
    };
    private Naw3 mNaw3;

    public Massala() {
        mInput = null;
        mWarathah = new ArrayList<Mirath>();
        mMahjoobin = new ArrayList<Mirath>();

        mSpecialCase = null;
        mAsl = 1;
        mAdadRo2osAlbaqi = 1;
        mAwl = 0;

        mHal = false;
        mFardh = false;
        mTa3seeb = false;
        mTassawi = true;
        mIstighraq = false;
        mShirkaTa3seeb = false;

        m_alakhawat_ashakikat_3assabat_ma3a_lghayr = false;
        m_alakhawat_3assabat_ma3a_lghayr = false;

        mNaw3 = Naw3.NAW3_NONE;

        mJadIndex = -1;
    }

    private void addMirath(Mirath m) {
        mWarathah.add(m);
    }

    private void addHajb(Warith warith, int nw, String hajib, Warith hajib2, int nh)
    {
        for(Mirath m : mMahjoobin) {
            if (warith == m.getWarith()) {
                m.addHajib((hajib != null) ? hajib : hajib2.getName(nh));
                return;
            }
        }
        String hajb = warith.getName(nw) + " حجب";
        if (hajib != null) {
            // ALAB below is for male tense only
            hajb += warith.getDhamirHajaba(nw, Warith.ALAB) + " " + hajib;
        } else {
            hajb += warith.getDhamirHajaba(nw, hajib2) + " " + hajib2.getName(nh);
        }
        mMahjoobin.add(new Mirath(warith, hajb));
    }

    private void addHajb(Warith warith, int nw, Warith hajib2, int nh)
    {
        addHajb(warith, nw, null, hajib2, nh);
    }

    private void addHajb(Warith warith, int nw, String hajib)
    {
        addHajb(warith, nw, hajib, null, 1);
    }

    private void addHajb(Warith warith, int nw, Warith hajib)
    {
        addHajb(warith, nw, hajib, 1);
    }

    private void addHajb(Warith warith, Warith hajib)
    {
        addHajb(warith, 1, hajib, 1);
    }

    public void hissabMawarith(WarathaInput in) {
        mInput = in;

        boolean far3_warith_dhakar = (mInput.get_alabna() + mInput.get_abna_alabna()) > 0;
        boolean far3_warith_ontha = (mInput.get_albanat() + mInput.get_banat_alabna()) > 0;
        boolean far3_warith = far3_warith_dhakar || far3_warith_ontha;
        boolean jam3_alikhwa = (mInput.get_alikhwa_alashika() + mInput.get_alikhwa_li_ab() + mInput.get_alikhwa_li_om() +
                mInput.get_alakhawat_ashakikat() + mInput.get_alakhawat_li_ab() + mInput.get_alakhawat_li_om()) > 1;
        boolean asl_warith_dhaker = mInput.alab() || mInput.aljad();
        boolean far3_wa_asl_warith_dhaker = far3_warith_dhakar || asl_warith_dhaker;
        boolean alikhwa_alashika_wa_li_ab = (mInput.get_alikhwa_alashika() + mInput.get_alikhwa_li_ab()) > 0;
        boolean alikhwa_wa_abna_alikhwa = (mInput.get_alikhwa_alashika() + mInput.get_alikhwa_li_ab() + mInput.get_abna_alikhwa_alashika() + mInput.get_abna_alikhwa_li_ab()) > 0;
        boolean ala3mam = (mInput.get_ala3mam_alashika() + mInput.get_ala3mam_li_ab()) > 0;

        // Order matters. Here be dragons
        mirathAzawj(far3_warith);
        mirathAzawjat(far3_warith);
        mirathAlab(far3_warith_dhakar, far3_warith_ontha, jam3_alikhwa);
        mirathAljad(far3_warith_dhakar, far3_warith_ontha, alikhwa_alashika_wa_li_ab);
        mirathAlom(far3_warith, jam3_alikhwa);
        mirathAljadat();
        mirathAwladAlom(far3_warith, asl_warith_dhaker);
        mirathAlbanat();
        mirathAlkhawatAshakikat(far3_warith_dhakar, far3_warith_ontha);
        mirathAlakhawatLiAb(far3_warith_dhakar, far3_warith_ontha, alikhwa_alashika_wa_li_ab);
        mirathAlabnaWaAlbanat();
        mirathAbnaWaBanatAlabna();
        mirathAlikhwaAlashika(far3_warith_dhakar);
        mirathAlikhwaLiAb(far3_warith_dhakar);
        mirathAbnaAlikhwaAlashika(far3_wa_asl_warith_dhaker, alikhwa_alashika_wa_li_ab);
        mirathAbnaAlikhwaLiAb(far3_wa_asl_warith_dhaker, alikhwa_alashika_wa_li_ab);
        mirathAla3mamAlashika(far3_wa_asl_warith_dhaker, alikhwa_wa_abna_alikhwa);
        mirathAla3mamLiAb(far3_wa_asl_warith_dhaker, alikhwa_wa_abna_alikhwa);
        mirathAbnaAla3mamAlashika(far3_wa_asl_warith_dhaker, alikhwa_wa_abna_alikhwa, ala3mam);
        mirathAbnaAla3mamLiAb(far3_wa_asl_warith_dhaker, alikhwa_wa_abna_alikhwa, ala3mam);

        // TODO assert none of the Warith enums is both in mWarathah & mMahjoobin
        if (noInput()) {
            System.out.println("لم يتم إدخال أية ورثة");
            return;
        }

        // Order matters. Here be dragons
        hissabAslAndRo2os();
        hissabAshomAndBaqi();
        hissabAnsiba();
    }

    private void mirathAzawj(boolean far3_warith) {
        if (!mInput.zawj()) return;

        int maqam;

        String sharh = Warith.AZAWJ.getSharhPrefix();
        if (far3_warith) {
            maqam = 4;
            sharh += "الربع 1\\4 فرضا";
        } else {
            maqam = 2;
            sharh += "النصف 1\\2 فرضا";
        }
        addMirath(new Mirath(Warith.AZAWJ, sharh, 1, maqam));
    }

    private void mirathAzawjat(boolean far3_warith) {
        int nbr = mInput.get_azawjat();
        if (nbr == 0) return;

        int maqam;

        String sharh = Warith.AZAWJAT.getSharhPrefix(nbr);
        if (far3_warith) {
            maqam = 8;
            sharh += "الثمن 1\\8 فرضا";
        } else {
            maqam = 4;
            sharh += "الربع 1\\4 فرضا";
        }
        addMirath(new Mirath(Warith.AZAWJAT, nbr, sharh, 1, maqam));
    }

    private void mirathAlab(boolean far3_warith_dhakar, boolean far3_warith_ontha, boolean jam3_alikhwa) {
        if (!mInput.alab()) return;

        int bast;
        int maqam;
        int ro2os;
        boolean ta3seeb;

        String sharh = Warith.ALAB.getSharhPrefix();
        if (far3_warith_dhakar) {
            sharh += "السدس 1\\6 فرضا فقط";
            bast = 1;
            maqam = 6;
            ro2os = 1;
            ta3seeb = false;
        } else if (far3_warith_ontha) {
            sharh += "السدس 1\\6 فرضا والباقي تعصيبا بالنفس";
            bast = 1;
            maqam = 6;
            ro2os = 1;
            ta3seeb = true;
        } else {
            bast = 0;
            maqam = 1;
            ta3seeb = true;
            if (!jam3_alikhwa && mInput.alom() && ((mInput.get_azawjat() > 0) || (mInput.zawj()))) {
                mTassawi = false;
                mShirkaTa3seeb = true;
                ro2os = 3;
                sharh += "ثلثي 2\\3 الباقي تعصيبا بالنفس";
                mSpecialCase = "هذه مسألة الغرّاوين"; // TODO make it an enum type
            } else {
                ro2os = 1;
                sharh += "الباقي تعصيبا بالنفس";
            }
        }
        addMirath(new Mirath(Warith.ALAB, sharh, bast, maqam, ta3seeb, ro2os));

        // alhajb bi alab
        if (mInput.aljad()) {
            addHajb(Warith.ALJAD, Warith.ALAB);
        }
        if (mInput.get_madhab() != Madhab.HAMBALI) {
            // تسقط الجدة من جهة الأب بالأب، ولا تسقط الجدة من جهة الأم بالأب
            if (mInput.aljadah_li_ab()) {
                addHajb(Warith.ALJADAH_LI_AB, Warith.ALAB);
            }
        }
        if (mInput.get_alikhwa_alashika() >= 1) {
            addHajb(Warith.ALIKHWA_ALASHIKA, mInput.get_alikhwa_alashika(), Warith.ALAB);
        }
        if (mInput.get_alakhawat_ashakikat() >= 1) {
            addHajb(Warith.ALAKHAWAT_ASHAKIKAT, mInput.get_alakhawat_ashakikat(), Warith.ALAB);
        }
        if (mInput.get_alikhwa_li_om() >= 1) {
            addHajb(Warith.ALIKHWA_LI_OM, mInput.get_alikhwa_li_om(), Warith.ALAB);
        }
        if (mInput.get_alakhawat_li_om() >= 1) {
            addHajb(Warith.ALAKHAWAT_LI_OM, mInput.get_alakhawat_li_om(), Warith.ALAB);
        }
        if (mInput.get_alikhwa_li_ab() >= 1) {
            addHajb(Warith.ALIKHWA_LI_AB, mInput.get_alikhwa_li_ab(), Warith.ALAB);
        }
        if (mInput.get_alakhawat_li_ab() >= 1) {
            addHajb(Warith.ALAKHAWAT_LI_AB, mInput.get_alakhawat_li_ab(), Warith.ALAB);
        }
        if (mInput.get_abna_alikhwa_alashika() >= 1) {
            addHajb(Warith.ABNA_ALIKHWA_ALASHIKA, mInput.get_abna_alikhwa_alashika(), Warith.ALAB);
        }
        if (mInput.get_abna_alikhwa_li_ab() >= 1) {
            addHajb(Warith.ABNA_ALIKHWA_LI_AB, mInput.get_abna_alikhwa_li_ab(), Warith.ALAB);
        }
        if (mInput.get_ala3mam_alashika() >= 1) {
            addHajb(Warith.ALA3MAM_ALASHIKA, mInput.get_ala3mam_alashika(), Warith.ALAB);
        }
        if (mInput.get_ala3mam_li_ab() >= 1) {
            addHajb(Warith.ALA3MAM_LI_AB, mInput.get_ala3mam_li_ab(), Warith.ALAB);
        }
        if (mInput.get_abna_ala3mam_alashika() >= 1) {
            addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, mInput.get_abna_ala3mam_alashika(), Warith.ALAB);
        }
        if (mInput.get_abna_ala3mam_li_ab() >= 1) {
            addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ALAB);
        }
    }

    private void mirathAljad(boolean far3_warith_dhakar, boolean far3_warith_ontha, boolean alikhwa_alashika_wa_li_ab) {
        if (!mInput.aljad()) return;

        String sharh;
        int bast;
        int maqam;
        int ro2os;
        boolean ta3seeb;

        if (!mInput.alab()) {        // no hajb by ab
            ro2os = 1;
            boolean tassawi = true;
            if (far3_warith_dhakar) {
                sharh = Warith.ALJAD.getSharhPrefix();
                sharh += "السدس 1\\6 فرضا فقط";
                bast = 1;
                maqam = 6;
                ta3seeb = false;
            } else {            // Here be dragons الجد يرث فرضا وتعصيبا أو تعصيبا فقط
                ta3seeb = true;
                if (far3_warith_ontha) {
                    sharh = Warith.ALJAD.getSharhPrefix();
                    sharh += "السدس 1\\6 فرضا و";
                    bast = 1;
                    maqam = 6;
                } else {             // الجد يرث بالتعصيب فقط
                    sharh = "";
                    bast = 0;
                    maqam = 1;
                }
                if (alikhwa_alashika_wa_li_ab) {        // الجد يُقاسم الإخوة
                    if (mInput.get_alikhwa_alashika() > 0) {
                        mShirkaTa3seeb = true;
                        ro2os += mInput.get_alikhwa_alashika();
                        if (mInput.get_alakhawat_ashakikat() > 0) {
                            ro2os *= 2;
                            ro2os += mInput.get_alakhawat_ashakikat();
                            mTassawi = tassawi = false;
                            sharh += Warith.ALJAD.getSharhPrefix(1, Warith.ALIKHWA_ALASHIKA, mInput.get_alikhwa_alashika(),
                                    Warith.ALAKHAWAT_ASHAKIKAT, mInput.get_alakhawat_ashakikat());
                        } else {
                            tassawi = true;
                            sharh += Warith.ALJAD.getSharhPrefix(1, Warith.ALIKHWA_ALASHIKA, mInput.get_alikhwa_alashika(), tassawi);
                        }
                    }
                    else {  // mInput.get_alikhwa_li_ab() > 0
                        mShirkaTa3seeb = true;
                        ro2os += mInput.get_alikhwa_li_ab();
                        if (mInput.get_alakhawat_li_ab() > 0) {
                            ro2os *= 2;
                            ro2os += mInput.get_alikhwa_li_ab();
                            mTassawi = tassawi = false;
                            sharh += Warith.ALJAD.getSharhPrefix(1, Warith.ALIKHWA_LI_AB, mInput.get_alikhwa_li_ab(),
                                    Warith.ALAKHAWAT_LI_AB, mInput.get_alakhawat_li_ab());
                        } else {
                            tassawi = true;
                            sharh += Warith.ALJAD.getSharhPrefix(1, Warith.ALIKHWA_LI_AB, mInput.get_alikhwa_li_ab(), tassawi);
                        }
                    }
                    mJadIndex = mWarathah.size();
                }
                else if (mInput.get_alakhawat_ashakikat() > 0 && far3_warith_ontha) { // الأخت العاصبة مع الغير تصبح مثل الأخ فتقاسم الجد
                    mShirkaTa3seeb = true;
                    mTassawi = tassawi = false;
                    ro2os *= 2;
                    ro2os += mInput.get_alakhawat_ashakikat();
                    sharh += Warith.ALJAD.getSharhPrefix(1, Warith.ALAKHAWAT_ASHAKIKAT, mInput.get_alakhawat_ashakikat(), tassawi);
                    mJadIndex = mWarathah.size();
                }
                else if (mInput.get_alakhawat_li_ab() > 0 && far3_warith_ontha) {
                    mShirkaTa3seeb = true;
                    mTassawi = tassawi = false;
                    ro2os *= 2;
                    ro2os += mInput.get_alakhawat_li_ab();
                    sharh += Warith.ALJAD.getSharhPrefix(1, Warith.ALAKHAWAT_LI_AB, mInput.get_alakhawat_li_ab(), tassawi);
                    mJadIndex = mWarathah.size();
                }
                else {       // الجد يرث الباقي لوحده
                    sharh += Warith.ALJAD.getSharhPrefix();
                }
                sharh += "الباقي تعصيبا بالنفس";
            }
            addMirath(new Mirath(Warith.ALJAD, sharh, bast, maqam, ta3seeb, ro2os));
        }

        // alhajb by aljad, we add it even if there is hajb by ab for detailed info
        if (mInput.get_alikhwa_li_om() >= 1) {
            addHajb(Warith.ALIKHWA_LI_OM, mInput.get_alikhwa_li_om(), Warith.ALJAD);
        }
        if (mInput.get_alakhawat_li_om() >= 1) {
            addHajb(Warith.ALAKHAWAT_LI_OM, mInput.get_alakhawat_li_om(), Warith.ALJAD);
        }
        if (mInput.get_abna_alikhwa_alashika() >= 1) {
            addHajb(Warith.ABNA_ALIKHWA_ALASHIKA, mInput.get_abna_alikhwa_alashika(), Warith.ALJAD);
        }
        if (mInput.get_abna_alikhwa_li_ab() >= 1) {
            addHajb(Warith.ABNA_ALIKHWA_LI_AB, mInput.get_abna_alikhwa_li_ab(), Warith.ALJAD);
        }
        if (mInput.get_ala3mam_alashika() >= 1) {
            addHajb(Warith.ALA3MAM_ALASHIKA, mInput.get_ala3mam_alashika(), Warith.ALJAD);
        }
        if (mInput.get_ala3mam_li_ab() >= 1) {
            addHajb(Warith.ALA3MAM_LI_AB, mInput.get_ala3mam_li_ab(), Warith.ALJAD);
        }
        if (mInput.get_abna_ala3mam_alashika() >= 1) {
            addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, mInput.get_abna_ala3mam_alashika(), Warith.ALJAD);
        }
        if (mInput.get_abna_ala3mam_li_ab() >= 1) {
            addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ALJAD);
        }
    }

    private void mirathAlom(boolean far3_warith, boolean jam3_alikhwa) {
        if (!mInput.alom()) return;

        int maqam;
        int ro2os;
        boolean ta3seeb;

        String sharh = Warith.ALOM.getSharhPrefix();
        if (jam3_alikhwa || far3_warith) {
            sharh += "السدس 1\\6 فرضا";
            maqam = 6;
            ro2os = 1;
            ta3seeb = false;
        } else if ((mInput.alab()) && ((mInput.get_azawjat() > 0) || (mInput.zawj()))) {
            sharh = "الأم ترث ثلث 1\\3 الباقي";
            maqam = 3;
            ro2os = 3;
            ta3seeb = true;
            mTassawi = false;
            mShirkaTa3seeb = true;
            mSpecialCase = "هذه مسألة الغرّاوين"; // TODO make it an enum type
        } else {
            sharh += "الثلث 1\\3 فرضا";
            maqam = 3;
            ro2os = 1;
            ta3seeb = false;
        }
        addMirath(new Mirath(Warith.ALOM, sharh, 1, maqam, ta3seeb, ro2os));

        // alhajb bi mInput.alom()
        if (mInput.aljadah_li_ab()) {
            addHajb(Warith.ALJADAH_LI_AB, Warith.ALOM);
        }
        if (mInput.aljadah_li_om()) {
            addHajb(Warith.ALJADAH_LI_OM, Warith.ALOM);
        }
    }

    private void mirathAljadat() {
        if (mInput.alom()) return;

        String sharh;
        int ro2os;

        if (mInput.aljadah_li_ab() && !mInput.alab()) {
            sharh = "الجدة لأب ";
            if (mInput.aljadah_li_om()) {
                sharh += "تشترك بالتساوي مع الجدة لأم في السدس 1\\6 فرضا";
                ro2os = 2;
            } else {
                sharh += "ترث السدس 1\\6 فرضا";
                ro2os = 1;
            }
            addMirath(new Mirath(Warith.ALJADAH_LI_AB, sharh, 1, 6, ro2os));
        }

        if (mInput.aljadah_li_om()) {
            sharh = "الجدة لأم ";
            if (mInput.aljadah_li_ab() && !mInput.alab()) {
                sharh += "تشترك بالتساوي مع الجدة لأب في السدس 1\\6 فرضا";
                ro2os = 2;
            } else {
                sharh += "ترث السدس 1\\6 فرضا";
                ro2os = 1;
            }
            addMirath(new Mirath(Warith.ALJADAH_LI_OM, sharh, 1, 6, ro2os));
        }
    }

    private void mirathAwladAlom(boolean far3_warith, boolean asl_warith_dhaker) {
        int nbr_a = mInput.get_alikhwa_li_om();
        int nbr_b = mInput.get_alakhawat_li_om();
        int awlad_alom = nbr_a + nbr_b;
        if (awlad_alom == 0) return;

        String sharh;

        if (far3_warith) {
            if (nbr_a > 0) {
                addHajb(Warith.ALIKHWA_LI_OM, nbr_a, "الفرع الوارث");
            }
            if (nbr_b > 0) {
                addHajb(Warith.ALAKHAWAT_LI_OM, nbr_b, "الفرع الوارث");
            }
        } else if (!asl_warith_dhaker) { // hajb by asl warith dhaker done above
            if (awlad_alom > 1) {
                if (nbr_a > 0) {
                    if (nbr_b > 0) {
                        mShirkaTa3seeb = true;
                        sharh = Warith.ALAKHAWAT_LI_OM.getSharhPrefix(nbr_b, Warith.ALIKHWA_LI_OM, nbr_a, true);
                        sharh += "الثلث 1\\3 فرضا";
                        addMirath(new Mirath(Warith.ALAKHAWAT_LI_OM, nbr_b, sharh, 1, 3, awlad_alom));

                        sharh = Warith.ALIKHWA_LI_OM.getSharhPrefix(nbr_a, Warith.ALAKHAWAT_LI_OM, nbr_b, true);
                        sharh += "الثلث 1\\3 فرضا";
                        addMirath(new Mirath(Warith.ALIKHWA_LI_OM, nbr_a, sharh, 1, 3, awlad_alom));
                    }
                    else {
                        sharh = Warith.ALIKHWA_LI_OM.getSharhPrefix(nbr_a);
                        sharh += "الثلث 1\\3 فرضا";
                        addMirath(new Mirath(Warith.ALIKHWA_LI_OM, nbr_a, sharh, 1, 3));
                    }
                }
                else if (nbr_b > 0) {
                    sharh = Warith.ALAKHAWAT_LI_OM.getSharhPrefix(nbr_b);
                    sharh += "الثلث 1\\3 فرضا";
                    addMirath(new Mirath(Warith.ALAKHAWAT_LI_OM, nbr_b, sharh, 1, 3));
                }
            } else if (nbr_a == 1) {
                sharh = Warith.ALIKHWA_LI_OM.getSharhPrefix();
                sharh += "السدس 1\\6 فرضا";
                addMirath(new Mirath(Warith.ALIKHWA_LI_OM, sharh, 1, 6));
            } else { // nbr_b == 1
                sharh = Warith.ALAKHAWAT_LI_OM.getSharhPrefix();
                sharh += "السدس 1\\6 فرضا";
                addMirath(new Mirath(Warith.ALAKHAWAT_LI_OM, sharh, 1, 6));
            }
        }

    }

    private void mirathAlbanat() {
        if (mInput.get_alabna() > 0) return;

        String sharh;
        int bast;
        int maqam;
        int nbr = mInput.get_albanat();

        // mirath albanat ma3a 3adam wojud alabna
        if (nbr == 1) {
            sharh = Warith.ALBANAT.getSharhPrefix();
            sharh += "النصف 1\\2 فرضا";
            addMirath(new Mirath(Warith.ALBANAT, sharh, 1, 2));
            if ((mInput.get_abna_alabna() == 0) && (mInput.get_banat_alabna() > 0)) {
                sharh = Warith.BANAT_ALABNA.getSharhPrefix(mInput.get_banat_alabna());
                sharh += "السدس 1\\6 تتمة الثلثين فرضا";
                addMirath(new Mirath(Warith.BANAT_ALABNA, mInput.get_banat_alabna(), sharh, 1, 6));
            }
        } else if (nbr >= 2) {
            sharh = Warith.ALBANAT.getSharhPrefix(nbr);
            sharh += "الثلثين 2\\3 فرضا";
            addMirath(new Mirath(Warith.ALBANAT, nbr, sharh, 2, 3));
            if ((mInput.get_abna_alabna() == 0) && (mInput.get_banat_alabna() > 0)) {
                addHajb(Warith.BANAT_ALABNA, mInput.get_banat_alabna(), "الجمع من البنات");
            }
        } else { // nbr == 0
            // same logic holds for 2nd generation if present
            if ((mInput.get_abna_alabna() == 0) && (mInput.get_banat_alabna() > 0)) {
                sharh = Warith.BANAT_ALABNA.getSharhPrefix(mInput.get_banat_alabna());
                if (mInput.get_banat_alabna() == 1) {
                    bast = 1; maqam = 2;
                    sharh += "النصف 1\\2 فرضا";
                } else {
                    bast = 2; maqam = 3;
                    sharh += "الثلثين 2\\3 فرضا";
                }
                addMirath(new Mirath(Warith.BANAT_ALABNA, mInput.get_banat_alabna(), sharh, bast, maqam));
            }
        }
    }

    private void mirathAlkhawatAshakikat(boolean far3_warith_dhakar, boolean far3_warith_ontha) {
        // mirath alakhawat ashakikat 3inda 3adam wojud alikhwa alashika
        int nbr = mInput.get_alakhawat_ashakikat();
        if ((nbr == 0) || mInput.alab() || far3_warith_dhakar || (mInput.get_alikhwa_alashika() > 0)) return;

        int bast;
        int maqam;

        String sharh;
        if (!far3_warith_ontha) {
            sharh = Warith.ALAKHAWAT_ASHAKIKAT.getSharhPrefix(nbr);
            if (nbr == 1) {
                bast = 1; maqam = 2;
                sharh += "النصف 1\\2 فرضا";
            } else { // (nbr >= 2)
                bast = 2; maqam = 3;
                sharh +="الثلثين 2\\3 فرضا";
                if ((mInput.get_alakhawat_li_ab() >= 1) && (mInput.get_alikhwa_li_ab() == 0)) {
                    addHajb(Warith.ALAKHAWAT_LI_AB, nbr, Warith.ALAKHAWAT_ASHAKIKAT.getName(mInput.get_alakhawat_ashakikat()));
                }
            }
            addMirath(new Mirath(Warith.ALAKHAWAT_ASHAKIKAT, nbr, sharh, bast, maqam));
        }
        else { // presence of far3_warith_ontha
            int ro2os = nbr;
            if (mInput.aljad()) {
                moveAljadBeforeAlikhaw();
                mShirkaTa3seeb = true;
                mTassawi = false;
                ro2os += 2;
                sharh = Warith.ALAKHAWAT_ASHAKIKAT.getSharhPrefix(nbr, Warith.ALJAD, 1, false);
            } else {
                sharh = Warith.ALAKHAWAT_ASHAKIKAT.getSharhPrefix(nbr);
            }
            sharh += "الباقي تعصيبا مع الغير";
            addMirath(new Mirath(Warith.ALAKHAWAT_ASHAKIKAT, nbr, sharh, ro2os));
            m_alakhawat_ashakikat_3assabat_ma3a_lghayr = true;
            m_alakhawat_3assabat_ma3a_lghayr = true;

            //alhajb by ALAKHAWAT_ASHAKIKAT
            if (mInput.get_alikhwa_li_ab() >= 1) {
                addHajb(Warith.ALIKHWA_LI_AB, mInput.get_alikhwa_li_ab(), Warith.ALAKHAWAT_ASHAKIKAT, nbr);
            }
            if (mInput.get_alakhawat_li_ab() >= 1){
                addHajb(Warith.ALAKHAWAT_LI_AB, mInput.get_alakhawat_li_ab(), Warith.ALAKHAWAT_ASHAKIKAT, nbr);
            }
            if (mInput.get_abna_alikhwa_alashika() >= 1) {
                addHajb(Warith.ABNA_ALIKHWA_ALASHIKA, mInput.get_abna_alikhwa_alashika(), Warith.ALAKHAWAT_ASHAKIKAT, nbr);
            }
            if (mInput.get_abna_alikhwa_li_ab() >= 1) {
                addHajb(Warith.ABNA_ALIKHWA_LI_AB, mInput.get_abna_alikhwa_li_ab(), Warith.ALAKHAWAT_ASHAKIKAT, nbr);
            }
            if (mInput.get_ala3mam_alashika() >= 1) {
                addHajb(Warith.ALA3MAM_ALASHIKA, mInput.get_ala3mam_alashika(), Warith.ALAKHAWAT_ASHAKIKAT, nbr);
            }
            if (mInput.get_ala3mam_li_ab() >= 1) {
                addHajb(Warith.ALA3MAM_LI_AB, mInput.get_ala3mam_li_ab(), Warith.ALAKHAWAT_ASHAKIKAT, nbr);
            }
            if (mInput.get_abna_ala3mam_alashika() >= 1) {
                addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, mInput.get_abna_ala3mam_alashika(), Warith.ALAKHAWAT_ASHAKIKAT, nbr);
            }
            if (mInput.get_abna_ala3mam_li_ab() >= 1) {
                addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ALAKHAWAT_ASHAKIKAT, nbr);
            }
        }
    }

    private void mirathAlakhawatLiAb(boolean far3_warith_dhakar, boolean far3_warith_ontha, boolean alikhwa_alashika_wa_li_ab) {
        // mirath alakhawat li ab 3inda 3adam wojud alikhwa
        int nbr = mInput.get_alakhawat_li_ab();
        if ((nbr == 0) || mInput.alab() || far3_warith_dhakar || alikhwa_alashika_wa_li_ab || m_alakhawat_ashakikat_3assabat_ma3a_lghayr) return;

        String sharh;
        int bast;
        int maqam;

        if (!far3_warith_ontha) {
            sharh = Warith.ALAKHAWAT_LI_AB.getSharhPrefix(nbr);
            if (mInput.get_alakhawat_ashakikat() == 0) {
                if (nbr == 1) {
                    bast = 1; maqam = 2;
                    sharh += "النصف 1\\2 فرضا";
                } else { // (nbr >= 2)
                    bast = 2; maqam = 3;
                    sharh += "الثلثين 2\\3 فرضا";
                }
                addMirath(new Mirath(Warith.ALAKHAWAT_LI_AB, nbr, sharh, bast, maqam));
            }
            else if (mInput.get_alakhawat_ashakikat() == 1) {
                bast = 1; maqam = 6;
                sharh += "السدس 1\\6 تتمة الثلثين فرضا";
                addMirath(new Mirath(Warith.ALAKHAWAT_LI_AB, nbr, sharh, bast, maqam));
            }
            // else hajb by alakhawat_ashakikat() >=2 handled above
        } else { // far3_warith_ontha
            int ro2os = nbr;
            if (mInput.aljad()) {
                moveAljadBeforeAlikhaw();
                mShirkaTa3seeb = true;
                mTassawi = false;
                ro2os += 2;
                sharh = Warith.ALAKHAWAT_LI_AB.getSharhPrefix(nbr, Warith.ALJAD, 1, false);
            } else {
                sharh = Warith.ALAKHAWAT_LI_AB.getSharhPrefix(nbr);
            }
            sharh += " الباقي تعصيبا مع الغير";
            addMirath(new Mirath(Warith.ALAKHAWAT_LI_AB, nbr, sharh, ro2os));
            m_alakhawat_3assabat_ma3a_lghayr = true;

            //alhajb by ALAKHAWAT_LI_AB
            if (mInput.get_abna_alikhwa_alashika() >= 1) {
                addHajb(Warith.ABNA_ALIKHWA_ALASHIKA, mInput.get_abna_alikhwa_alashika(), Warith.ALAKHAWAT_LI_AB, nbr);
            }
            if (mInput.get_abna_alikhwa_li_ab() >= 1) {
                addHajb(Warith.ABNA_ALIKHWA_LI_AB, mInput.get_abna_alikhwa_li_ab(), Warith.ALAKHAWAT_LI_AB, nbr);
            }
            if (mInput.get_ala3mam_alashika() >= 1) {
                addHajb(Warith.ALA3MAM_ALASHIKA, mInput.get_ala3mam_alashika(), Warith.ALAKHAWAT_LI_AB, nbr);
            }
            if (mInput.get_ala3mam_li_ab() >= 1) {
                addHajb(Warith.ALA3MAM_LI_AB, mInput.get_ala3mam_li_ab(), Warith.ALAKHAWAT_LI_AB, nbr);
            }
            if (mInput.get_abna_ala3mam_alashika() >= 1) {
                addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, mInput.get_abna_ala3mam_alashika(), Warith.ALAKHAWAT_LI_AB, nbr);
            }
            if (mInput.get_abna_ala3mam_li_ab() >= 1) {
                addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ALAKHAWAT_LI_AB, nbr);
            }
        }
    }

    private void mirathAlabnaWaAlbanat() {
        //mirath alabna + albanat 3inda woujoud alabna
        int nbr_a = mInput.get_alabna();
        if (nbr_a == 0) return;

        String sharh;
        int nbr_b = mInput.get_albanat();
        if (nbr_b == 0) {
            sharh = Warith.ALABNA.getSharhPrefix(nbr_a);
            sharh += " الباقي تعصيبا بالنفس";
            addMirath(new Mirath(Warith.ALABNA, nbr_a, sharh));
        } else {
            int ro2os = 2 * nbr_a + nbr_b;
            mTassawi = false;
            mShirkaTa3seeb = true;
            sharh = Warith.ALABNA.getSharhPrefix(nbr_a, Warith.ALBANAT, nbr_b, false);
            sharh += "الباقي تعصيبا بالنفس";
            addMirath(new Mirath(Warith.ALABNA, nbr_a, sharh, ro2os));

            sharh = Warith.ALBANAT.getSharhPrefix(nbr_b, Warith.ALABNA, nbr_a, false);
            sharh += "الباقي تعصيبا بالغير";
            addMirath(new Mirath(Warith.ALBANAT, nbr_b, sharh, ro2os));
        }

        // hajb by alabna
        if (mInput.get_abna_alabna() > 0) {
            addHajb(Warith.ABNA_ALABNA, mInput.get_abna_alabna(), Warith.ALABNA, nbr_a);
        }
        if (mInput.get_banat_alabna() > 0) {
            addHajb(Warith.BANAT_ALABNA, mInput.get_banat_alabna(), Warith.ALABNA, nbr_a);
        }
        if (mInput.get_alikhwa_alashika() > 0) {
            addHajb(Warith.ALIKHWA_ALASHIKA, mInput.get_alikhwa_alashika(), Warith.ALABNA, nbr_a);
        }
        if (mInput.get_alakhawat_ashakikat() > 0) {
            addHajb(Warith.ALAKHAWAT_ASHAKIKAT, mInput.get_alakhawat_ashakikat(), Warith.ALABNA, nbr_a);
        }
        if (mInput.get_alikhwa_li_ab() > 0) {
            addHajb(Warith.ALIKHWA_LI_AB, mInput.get_alikhwa_li_ab(), Warith.ALABNA, nbr_a);
        }
        if (mInput.get_alakhawat_li_ab() > 0) {
            addHajb(Warith.ALAKHAWAT_LI_AB, mInput.get_alakhawat_li_ab(), Warith.ALABNA, nbr_a);
        }
        //الحجب تم بالفرع الوارث سابقا في ميراث أولاد الأم
           /* if (mInput.get_alikhwa_li_om() > 0) {
                addHajb(Warith.ALIKHWA_LI_OM, mInput.get_alikhwa_li_om(), Warith.ALABNA, nbr_a);
            }
            if (mInput.get_alakhawat_li_om() > 0) {
                addHajb(Warith.ALAKHAWAT_LI_OM, mInput.get_alakhawat_li_om(), Warith.ALABNA, nbr_a);
            } */
        if (mInput.get_abna_alikhwa_alashika() > 0) {
            addHajb(Warith.ABNA_ALIKHWA_ALASHIKA, mInput.get_abna_alikhwa_alashika(), Warith.ALABNA, nbr_a);
        }
        if (mInput.get_abna_alikhwa_li_ab() > 0) {
            addHajb(Warith.ABNA_ALIKHWA_LI_AB, mInput.get_abna_alikhwa_li_ab(), Warith.ALABNA, nbr_a);
        }
        if (mInput.get_ala3mam_alashika() > 0) {
            addHajb(Warith.ALA3MAM_ALASHIKA, mInput.get_ala3mam_alashika(), Warith.ALABNA, nbr_a);
        }
        if (mInput.get_ala3mam_li_ab() > 0) {
            addHajb(Warith.ALA3MAM_LI_AB, mInput.get_ala3mam_li_ab(), Warith.ALABNA, nbr_a);
        }
        if (mInput.get_abna_ala3mam_alashika() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, mInput.get_abna_ala3mam_alashika(), Warith.ALABNA, nbr_a);
        }
        if (mInput.get_abna_ala3mam_li_ab() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ALABNA, nbr_a);
        }
    }

    private void mirathAbnaWaBanatAlabna() {
        int nbr_a = mInput.get_abna_alabna();
        if ((nbr_a == 0) || (mInput.get_alabna() > 0)) return;

        String sharh;
        int nbr_b = mInput.get_banat_alabna();

        if (nbr_b == 0) {
            sharh = Warith.ABNA_ALABNA.getSharhPrefix(nbr_a);
            sharh += "الباقي تعصيبا بالنفس";
            addMirath(new Mirath(Warith.ABNA_ALABNA, nbr_a, sharh));
        } else {
            int ro2os = 2 * nbr_a + nbr_b;
            mTassawi = false;
            mShirkaTa3seeb = true;
            sharh = Warith.ABNA_ALABNA.getSharhPrefix(nbr_a, Warith.BANAT_ALABNA, nbr_b, false);
            sharh += "الباقي تعصيبا بالنفس";
            addMirath(new Mirath(Warith.ABNA_ALABNA, nbr_a, sharh, ro2os));

            sharh = Warith.BANAT_ALABNA.getSharhPrefix(nbr_b, Warith.ABNA_ALABNA, nbr_a, false);
            sharh += "الباقي تعصيبا بالغير";
            addMirath(new Mirath(Warith.BANAT_ALABNA, nbr_b, sharh, ro2os));
        }

        // hajb by abna alabna
        if (mInput.get_alikhwa_alashika() > 0) {
            addHajb(Warith.ALIKHWA_ALASHIKA, mInput.get_alikhwa_alashika(), Warith.ABNA_ALABNA, nbr_a);
        }
        if (mInput.get_alakhawat_ashakikat() > 0) {
            addHajb(Warith.ALAKHAWAT_ASHAKIKAT, mInput.get_alakhawat_ashakikat(), Warith.ABNA_ALABNA, nbr_a);
        }
        if (mInput.get_alikhwa_li_ab() > 0) {
            addHajb(Warith.ALIKHWA_LI_AB, mInput.get_alikhwa_li_ab(), Warith.ABNA_ALABNA, nbr_a);
        }
        if (mInput.get_alakhawat_li_ab() > 0) {
            addHajb(Warith.ALAKHAWAT_LI_AB, mInput.get_alakhawat_li_ab(), Warith.ABNA_ALABNA, nbr_a);
        }
        //الحجب تم بالفرع الوارث سابقا في ميراث أولاد الأم
         /*   if (mInput.get_alikhwa_li_om() > 0) {
                addHajb(Warith.ALIKHWA_LI_OM, mInput.get_alikhwa_li_om(), Warith.ABNA_ALABNA, nbr_a);
            }
            if (mInput.get_alakhawat_li_om() > 0) {
                addHajb(Warith.ALAKHAWAT_LI_OM, mInput.get_alakhawat_li_om(), Warith.ABNA_ALABNA, nbr_a);
            }*/
        if (mInput.get_abna_alikhwa_alashika() > 0) {
            addHajb(Warith.ABNA_ALIKHWA_ALASHIKA, mInput.get_abna_alikhwa_alashika(), Warith.ABNA_ALABNA, nbr_a);
        }
        if (mInput.get_abna_alikhwa_li_ab() > 0) {
            addHajb(Warith.ABNA_ALIKHWA_LI_AB, mInput.get_abna_alikhwa_li_ab(), Warith.ABNA_ALABNA, nbr_a);
        }
        if (mInput.get_ala3mam_alashika() > 0) {
            addHajb(Warith.ALA3MAM_ALASHIKA, mInput.get_ala3mam_alashika(), Warith.ABNA_ALABNA, nbr_a);
        }
        if (mInput.get_ala3mam_li_ab() > 0) {
            addHajb(Warith.ALA3MAM_LI_AB, mInput.get_ala3mam_li_ab(), Warith.ABNA_ALABNA, nbr_a);
        }
        if (mInput.get_abna_ala3mam_alashika() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, mInput.get_abna_ala3mam_alashika(), Warith.ABNA_ALABNA, nbr_a);
        }
        if (mInput.get_abna_ala3mam_li_ab() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ABNA_ALABNA, nbr_a);
        }
    }

    private void moveAljadBeforeAlikhaw() {
        if (mJadIndex != -1) {
            // assert mJadIndex < mWarathah.size()
            Mirath aljad = mWarathah.remove(mJadIndex);
            mJadIndex = mWarathah.size();
            mWarathah.add(aljad);
        }
    }

    private void mirathAlikhwaAlashika(boolean far3_warith_dhakar) {
        int nbr_a = mInput.get_alikhwa_alashika();
        if (nbr_a == 0 || far3_warith_dhakar || mInput.alab()) return;

        String sharh;
        int nbr_b = mInput.get_alakhawat_ashakikat();
        int ro2os;

        if (nbr_b == 0) {
            ro2os = nbr_a;
            if (mInput.aljad()) {
                moveAljadBeforeAlikhaw();
                mShirkaTa3seeb = true;
                ro2os++;
                sharh = Warith.ALIKHWA_ALASHIKA.getSharhPrefix(nbr_a, Warith.ALJAD, 1, true);
            }
            else {
                sharh = Warith.ALIKHWA_ALASHIKA.getSharhPrefix(nbr_a);
            }
            sharh += "الباقي تعصيبا بالنفس";
            addMirath(new Mirath(Warith.ALIKHWA_ALASHIKA, nbr_a, sharh, ro2os));
        } else {
            String sharh2;
            mTassawi = false;
            mShirkaTa3seeb = true;
            ro2os = 2 * nbr_a + nbr_b;
            if (mInput.aljad()) {
                moveAljadBeforeAlikhaw();
                sharh = Warith.ALIKHWA_ALASHIKA.getSharhPrefix(nbr_a, Warith.ALAKHAWAT_ASHAKIKAT, nbr_b, Warith.ALJAD, 1);
                sharh2 = Warith.ALAKHAWAT_ASHAKIKAT.getSharhPrefix(nbr_b, Warith.ALIKHWA_ALASHIKA, nbr_a, Warith.ALJAD, 1);
                ro2os += 2;
            } else {
                sharh = Warith.ALIKHWA_ALASHIKA.getSharhPrefix(nbr_a, Warith.ALAKHAWAT_ASHAKIKAT, nbr_b, false);
                sharh2 = Warith.ALAKHAWAT_ASHAKIKAT.getSharhPrefix(nbr_b, Warith.ALIKHWA_ALASHIKA, nbr_a, false);
            }
            sharh += "الباقي تعصيبا بالنفس";
            sharh2 += "الباقي تعصيبا بالغير";
            addMirath(new Mirath(Warith.ALIKHWA_ALASHIKA, nbr_a, sharh, ro2os));
            addMirath(new Mirath(Warith.ALAKHAWAT_ASHAKIKAT, nbr_b, sharh2, ro2os));
        }

        // الحجب بالإخوة الأشقاء
        if (mInput.get_alikhwa_li_ab() > 0) {
            addHajb(Warith.ALIKHWA_LI_AB, mInput.get_alikhwa_li_ab(), Warith.ALIKHWA_ALASHIKA, nbr_a);
        }
        if (mInput.get_alakhawat_li_ab() > 0) {
            addHajb(Warith.ALAKHAWAT_LI_AB, mInput.get_alakhawat_li_ab(), Warith.ALIKHWA_ALASHIKA, nbr_a);
        }
        if (mInput.get_abna_alikhwa_alashika() > 0) {
            addHajb(Warith.ABNA_ALIKHWA_ALASHIKA, mInput.get_abna_alikhwa_alashika(), Warith.ALIKHWA_ALASHIKA, nbr_a);
        }
        if (mInput.get_abna_alikhwa_li_ab() > 0) {
            addHajb(Warith.ABNA_ALIKHWA_LI_AB, mInput.get_abna_alikhwa_alashika(), Warith.ALIKHWA_ALASHIKA, nbr_a);
        }
        if (mInput.get_ala3mam_alashika() > 0) {
            addHajb(Warith.ALA3MAM_ALASHIKA, mInput.get_ala3mam_alashika(), Warith.ALIKHWA_ALASHIKA, nbr_a);
        }
        if (mInput.get_ala3mam_li_ab() > 0) {
            addHajb(Warith.ALA3MAM_LI_AB, mInput.get_ala3mam_li_ab(), Warith.ALIKHWA_ALASHIKA, nbr_a);
        }
        if (mInput.get_abna_ala3mam_alashika() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, mInput.get_abna_ala3mam_alashika(), Warith.ALIKHWA_ALASHIKA, nbr_a);
        }
        if (mInput.get_abna_ala3mam_li_ab() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ALIKHWA_ALASHIKA, nbr_a);
        }
    }

    private void mirathAlikhwaLiAb(boolean far3_warith_dhakar) {
        int nbr_a = mInput.get_alikhwa_li_ab();
        if ((nbr_a == 0) || far3_warith_dhakar || mInput.alab() || (mInput.get_alikhwa_alashika() > 0) || m_alakhawat_ashakikat_3assabat_ma3a_lghayr) return;

        String sharh;
        int nbr_b = mInput.get_alakhawat_li_ab();
        int ro2os;

        if (nbr_b == 0) {
            ro2os = nbr_a;
            if (mInput.aljad()) {
                moveAljadBeforeAlikhaw();
                mShirkaTa3seeb = true;
                ro2os++;
                sharh = Warith.ALIKHWA_LI_AB.getSharhPrefix(nbr_a, Warith.ALJAD, 1, true);
            }
            else {
                sharh = Warith.ALIKHWA_LI_AB.getSharhPrefix(nbr_a);
            }
            sharh += "الباقي تعصيبا بالنفس";
            addMirath(new Mirath(Warith.ALIKHWA_LI_AB, nbr_a, sharh, ro2os));
        } else {
            String sharh2;
            mTassawi = false;
            mShirkaTa3seeb = true;
            ro2os = 2 * nbr_a + nbr_b;
            if (mInput.aljad()) {
                moveAljadBeforeAlikhaw();
                sharh = Warith.ALIKHWA_LI_AB.getSharhPrefix(nbr_a, Warith.ALAKHAWAT_LI_AB, nbr_b, Warith.ALJAD, 1);
                sharh2 = Warith.ALAKHAWAT_LI_AB.getSharhPrefix(nbr_b, Warith.ALIKHWA_LI_AB, nbr_a, Warith.ALJAD, 1);
                ro2os += 2;
            } else {
                sharh = Warith.ALIKHWA_LI_AB.getSharhPrefix(nbr_a, Warith.ALAKHAWAT_LI_AB, nbr_b, false);
                sharh2 = Warith.ALAKHAWAT_LI_AB.getSharhPrefix(nbr_b, Warith.ALIKHWA_LI_AB, nbr_a, false);
            }
            sharh += "الباقي تعصيبا بالنفس";
            sharh2 += "الباقي تعصيبا بالغير";
            addMirath(new Mirath(Warith.ALIKHWA_LI_AB, nbr_a, sharh, ro2os));
            addMirath(new Mirath(Warith.ALAKHAWAT_LI_AB, nbr_b, sharh2, ro2os));
        }

        // alhajb by alikhwa li ab
        if (mInput.get_abna_alikhwa_alashika() > 0) {
            addHajb(Warith.ABNA_ALIKHWA_ALASHIKA, mInput.get_abna_alikhwa_alashika(), Warith.ALIKHWA_LI_AB, nbr_a);
        }
        if (mInput.get_abna_alikhwa_li_ab() > 0) {
            addHajb(Warith.ABNA_ALIKHWA_LI_AB, mInput.get_abna_alikhwa_li_ab(), Warith.ALIKHWA_LI_AB, nbr_a);
        }
        if (mInput.get_ala3mam_alashika() > 0) {
            addHajb(Warith.ALA3MAM_ALASHIKA, mInput.get_ala3mam_alashika(), Warith.ALIKHWA_LI_AB, nbr_a);
        }
        if (mInput.get_ala3mam_li_ab() > 0) {
            addHajb(Warith.ALA3MAM_LI_AB, mInput.get_ala3mam_li_ab(), Warith.ALIKHWA_LI_AB, nbr_a);
        }
        if (mInput.get_abna_ala3mam_alashika() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, mInput.get_abna_ala3mam_alashika(), Warith.ALIKHWA_LI_AB, nbr_a);
        }
        if (mInput.get_abna_ala3mam_li_ab() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ALIKHWA_LI_AB, nbr_a);
        }
    }

    private void mirathAbnaAlikhwaAlashika(boolean far3_wa_asl_warith_dhaker, boolean alikhwa_alashika_wa_li_ab) {
        int nbr = mInput.get_abna_alikhwa_alashika();
        if ((nbr == 0) || far3_wa_asl_warith_dhaker || alikhwa_alashika_wa_li_ab || m_alakhawat_3assabat_ma3a_lghayr) return;

        String sharh = Warith.ABNA_ALIKHWA_ALASHIKA.getSharhPrefix(nbr);
        sharh += "الباقي تعصيبا بالنفس";
        addMirath(new Mirath(Warith.ABNA_ALIKHWA_ALASHIKA, nbr, sharh));

        // alhajb by abna alikhwa alashika
        if (mInput.get_abna_alikhwa_li_ab() > 0) {
            addHajb(Warith.ABNA_ALIKHWA_LI_AB, mInput.get_abna_alikhwa_li_ab(), Warith.ABNA_ALIKHWA_ALASHIKA, nbr);
        }
        if (mInput.get_ala3mam_alashika() > 0) {
            addHajb(Warith.ALA3MAM_ALASHIKA, mInput.get_ala3mam_alashika(), Warith.ABNA_ALIKHWA_ALASHIKA, nbr);
        }
        if (mInput.get_ala3mam_li_ab() > 0) {
            addHajb(Warith.ALA3MAM_LI_AB, mInput.get_ala3mam_li_ab(), Warith.ABNA_ALIKHWA_ALASHIKA, nbr);
        }
        if (mInput.get_abna_ala3mam_alashika() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, mInput.get_abna_ala3mam_alashika(), Warith.ABNA_ALIKHWA_ALASHIKA, nbr);
        }
        if (mInput.get_abna_ala3mam_li_ab() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ABNA_ALIKHWA_ALASHIKA, nbr);
        }
    }

    private void mirathAbnaAlikhwaLiAb(boolean far3_wa_asl_warith_dhaker, boolean alikhwa_alashika_wa_li_ab) {
        int nbr = mInput.get_abna_alikhwa_li_ab();
        if ((nbr == 0) || far3_wa_asl_warith_dhaker || alikhwa_alashika_wa_li_ab || m_alakhawat_3assabat_ma3a_lghayr || (mInput.get_abna_alikhwa_alashika() > 0)) return;

        String sharh = Warith.ABNA_ALIKHWA_LI_AB.getSharhPrefix(nbr);
        sharh += "الباقي تعصيبا بالنفس";
        addMirath(new Mirath(Warith.ABNA_ALIKHWA_LI_AB, nbr, sharh));

        // alhajb by abna alikhwa li_ab
        if (mInput.get_ala3mam_alashika() > 0) {
            addHajb(Warith.ALA3MAM_ALASHIKA, mInput.get_ala3mam_alashika(), Warith.ABNA_ALIKHWA_LI_AB, nbr);
        }
        if (mInput.get_ala3mam_li_ab() > 0) {
            addHajb(Warith.ALA3MAM_LI_AB, mInput.get_ala3mam_li_ab(), Warith.ABNA_ALIKHWA_LI_AB, nbr);
        }
        if (mInput.get_abna_ala3mam_alashika() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, mInput.get_abna_ala3mam_alashika(), Warith.ABNA_ALIKHWA_LI_AB, nbr);
        }
        if (mInput.get_abna_ala3mam_li_ab() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ABNA_ALIKHWA_LI_AB, nbr);
        }
    }

    private void mirathAla3mamAlashika(boolean far3_wa_asl_warith_dhaker, boolean alikhwa_wa_abna_alikhwa) {
        int nbr = mInput.get_ala3mam_alashika();
        if ((nbr == 0) || far3_wa_asl_warith_dhaker || alikhwa_wa_abna_alikhwa || m_alakhawat_3assabat_ma3a_lghayr) return;

        String sharh = Warith.ALA3MAM_ALASHIKA.getSharhPrefix(nbr);
        sharh += "الباقي تعصيبا بالنفس";
        addMirath(new Mirath(Warith.ALA3MAM_ALASHIKA, nbr, sharh));

        // alhajb by abna alikhwa alashika
        if (mInput.get_ala3mam_li_ab() > 0) {
            addHajb(Warith.ALA3MAM_LI_AB, mInput.get_ala3mam_li_ab(), Warith.ALA3MAM_ALASHIKA, nbr);
        }
        if (mInput.get_abna_ala3mam_alashika() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, mInput.get_abna_ala3mam_alashika(), Warith.ALA3MAM_ALASHIKA, nbr);
        }
        if (mInput.get_abna_ala3mam_li_ab() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ALA3MAM_ALASHIKA, nbr);
        }
    }

    private void mirathAla3mamLiAb(boolean far3_wa_asl_warith_dhaker, boolean alikhwa_wa_abna_alikhwa) {
        int nbr = mInput.get_ala3mam_li_ab();
        if ((nbr == 0) || far3_wa_asl_warith_dhaker || alikhwa_wa_abna_alikhwa || m_alakhawat_3assabat_ma3a_lghayr || (mInput.get_ala3mam_alashika() > 0)) return;

        String sharh = Warith.ALA3MAM_LI_AB.getSharhPrefix(nbr);
        sharh += "الباقي تعصيبا بالنفس";
        addMirath(new Mirath(Warith.ALA3MAM_LI_AB, nbr, sharh));

        // alhajb by abna alikhwa li_ab
        if (mInput.get_abna_ala3mam_alashika() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, mInput.get_abna_ala3mam_alashika(), Warith.ALA3MAM_LI_AB, nbr);
        }
        if (mInput.get_abna_ala3mam_li_ab() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ALA3MAM_LI_AB, nbr);
        }
    }

    private void mirathAbnaAla3mamAlashika(boolean far3_wa_asl_warith_dhaker, boolean alikhwa_wa_abna_alikhwa, boolean ala3mam) {
        int nbr = mInput.get_abna_ala3mam_alashika();
        if ((mInput.get_abna_ala3mam_alashika() == 0) || far3_wa_asl_warith_dhaker || alikhwa_wa_abna_alikhwa || m_alakhawat_3assabat_ma3a_lghayr || ala3mam) return;

        String sharh = Warith.ABNA_ALA3MAM_ALASHIKA.getSharhPrefix(mInput.get_abna_ala3mam_alashika());
        sharh += "الباقي تعصيبا بالنفس";
        addMirath(new Mirath(Warith.ABNA_ALA3MAM_ALASHIKA, nbr, sharh));

        // alhajb by abna alikhwa li_ab
        if (mInput.get_abna_ala3mam_li_ab() > 0) {
            addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ABNA_ALA3MAM_ALASHIKA, nbr);
        }
    }

    private void mirathAbnaAla3mamLiAb(boolean far3_wa_asl_warith_dhaker, boolean alikhwa_wa_abna_alikhwa, boolean ala3mam) {
        int nbr = mInput.get_abna_ala3mam_li_ab();
        if ((nbr == 0) || far3_wa_asl_warith_dhaker || alikhwa_wa_abna_alikhwa || m_alakhawat_3assabat_ma3a_lghayr || ala3mam || (mInput.get_abna_ala3mam_alashika() > 0))  return;

        String sharh = Warith.ABNA_ALA3MAM_LI_AB.getSharhPrefix(mInput.get_abna_ala3mam_li_ab());
        sharh += "الباقي تعصيبا بالنفس";
        addMirath(new Mirath(Warith.ABNA_ALA3MAM_LI_AB, nbr, sharh));
    }

    private boolean noInput() {
        return mWarathah.size() == 0;
    }

    private void hissabAslAndRo2os() {
        // استخراج أصل المسألة (ص 40 من كتاب "الفرائض المُيسر) وعدد رؤوس ورثة الباقي"
        mAsl = 1;
        for (Mirath m : mWarathah) {
            if (m.isFardh()) {
                mAsl = lcm(mAsl, m.getMaqam());
                if (!mFardh) {
                    mFardh = true;
                }
            }
            if (m.isTa3seeb()) {
                if ((m.getRo2os() != 1) && (mAdadRo2osAlbaqi == 1)) {
                    mAdadRo2osAlbaqi = m.getRo2os();
                }
                if (!mTa3seeb) {
                    mTa3seeb = true;
                }
            }
        }
        if (!mFardh && mTa3seeb) {
            mAsl = mAdadRo2osAlbaqi;
        }
    }

    private void hissabAshomAndBaqi() {
        // حساب مجموع أسهم أصحاب الفروض وباقي المسألة
        int ashom = 0;
        boolean counted = false;
        for (Mirath m : mWarathah) {
            if (m.isFardh()) {
                int fardh = m.getBast() * mAsl / m.getMaqam();
                m.setFardh(fardh);
                if (m.isJadah()) {
                    if (counted) { continue; }
                    counted = true;
                }
                ashom += fardh;
            }
        }
        mBaqi = mAsl - ashom;
        if (mBaqi == 0) {
            if (mFardh) {
                mNaw3 = Naw3.NAW3_ADILA;
            }
            if (mTa3seeb) {
                mIstighraq = true;
            }
        }
        else if (mBaqi > 0) { // مسألة فيها باقي
            if (mTa3seeb) {
                // TODO ADILA here?
                // TODO mBaqi % mAdadRo2os != 0
            }
            else if (ashom != 0) {                           // مسألة ناقصة فيها رد
                mNaw3 = Naw3.NAW3_RAD;
                // TODO rad albaqi
            }
        } else {                                // مسألة عائلة
            mNaw3 = Naw3.NAW3_AWL;
            mAwl = ashom;
            if (mTa3seeb) {
                mIstighraq = true;
            }
            // TODO  XXX mBaqi < 0
            mBaqi = 0;
        }
    }

    private void hissabAnsiba() {
        // قسمة الأسهم في الجدول
        int nassib;
        int missahFactor = 1;
        ArrayList<Mirath> all = new ArrayList<>();

        all.addAll(mWarathah);
        all.addAll(mMahjoobin);
        for (Mirath m : all) {          // Here be dragons
            StringBuilder nassibMojmal = new StringBuilder();
            StringBuilder nassibFardi = new StringBuilder();

            if (m.isFardh()) {
                nassib = m.getFardh();
                nassibMojmal.append(nassib);
                nassibFardi.append(nassib);

                if (m.isTa3seeb()) { // حالات الأب والجد يرثان 1\6 + باقي
                    // assert m.getBast() == 1 && m.getMaqam() == 6
                    nassibMojmal.append(" + ").append(mBaqi);
                    nassibFardi.append(" + ").append(mBaqi);

                    if (m.isShirka()) {                 // assert Aljada
                        nassibMojmal.append("ش");

                        int factor = mTassawi || m.getWarith().isOntha() ? 1 : 2;
                        nassibFardi.append(" * ");
                        nassibFardi.append(factor);
                        nassibFardi.append("\\").append(m.getRo2os());

                        int bast = mBaqi * factor;
                        if (bast % m.getRo2os() == 0) {
                            nassib += bast / m.getRo2os();
                            nassibFardi.append(" = ").append(nassib);
                        }
                        else { // إنكسار
                            int gcd = gcd(bast, m.getRo2os());
                            missahFactor = lcm(missahFactor, gcd == 1 ? m.getRo2os() : gcd);
                            nassib = -bast;                 // bast only for now
                        }
                    } else {
                        nassib += mBaqi;
                    }
                } else {              // وارث بالفرض فقط
                    if (m.isShirka()) {
                        nassibMojmal.append("ش");

                        nassibFardi.append(" * 1\\");
                        nassibFardi.append(m.getRo2os());
                        if (m.getFardh() % m.getRo2os() == 0) {
                            nassib = m.getFardh() / m.getRo2os();
                            nassibFardi.append(" = ").append(nassib);
                        }
                        else {  // إنكسار
                            int gcd = gcd(m.getFardh(), m.getRo2os());
                            missahFactor = lcm(missahFactor, gcd == 1 ? m.getRo2os() : gcd);
                            nassib = -m.getFardh();         // bast only for now
                        }
                    }
                }
            }
            else if (m.isTa3seeb()) {       // وارث بالتعصيب فقط
                nassib = mBaqi;
                nassibMojmal.append(mBaqi);
                nassibFardi.append(mBaqi);

                if (m.isShirka()) {
                    nassibMojmal.append("ش");

                    int factor = mTassawi || m.getWarith().isOntha() ? 1 : 2;
                    nassibFardi.append(" * ");
                    nassibFardi.append(factor);
                    nassibFardi.append("\\").append(m.getRo2os());

                    int bast = mBaqi * factor;
                    if (bast % m.getRo2os() == 0) {
                        nassib = bast / m.getRo2os();
                        nassibFardi.append(" = ").append(nassib);
                    }
                    else { //  إنكسار
                        int gcd = gcd(bast, m.getRo2os());
                        missahFactor = lcm(missahFactor, gcd == 1 ? m.getRo2os() : gcd);
                        nassib = -bast;                         // bast only for now
                    }
                }
            }
            else if (m.isMahjoob()) {
                nassibMojmal.append("--");
                nassibFardi.append("--");
                nassib = 0;
            }
            else {
                System.out.println(String.format("BUG! %s خطأ وارث ليس صاحب فرض أو تعصيب أو حجب", m.getWarith().getName()));
                continue;
            }

            m.setNassibMojmal(nassibMojmal.toString());
            m.setNassibFardi(nassibFardi.toString());
            m.setNassib(nassib);
        }

        mMissah =  mAwl == 0?  mAsl * missahFactor : mAwl * missahFactor;
        if (missahFactor != 1) {
            // Tasshih, go again :-)
            for (Mirath m:all) {
                if (m.isMahjoob()) continue;
                int oldNassib = m.getNassib();
                if (oldNassib < 0) {
                    if (m.isFardh() && m.isTa3seeb()) {
                        nassib = m.getFardh() * missahFactor;
                        nassib += -oldNassib * missahFactor / m.getRo2os();
                    }
                    else {
                        nassib = -oldNassib * missahFactor / m.getRo2os();
                    }
                }
                else {
                    nassib = oldNassib * missahFactor;
                }
                m.setNassib(nassib);
                m.setNassibFardi(m.getNassibFardi() + " -> "+ nassib);
            }
        }
    }

    public String getSharh() {
        StringBuilder sharh = new StringBuilder();

        if (mWarathah != null) {
            for (Mirath m : mWarathah) {
                sharh.append("- " + m.getSharh() + ".\n");
            }
        }

        if (mMahjoobin != null) {
            for (Mirath m : mMahjoobin) {
                sharh.append("- " + m.getSharh() + ".\n");
            }
        }

        if (mSpecialCase != null /* TODO غراوين */) {
            sharh.append(mSpecialCase + "\n");
        }

        sharh.append("\n" + "- أصل المسألة " + mAsl + ".\n");
        switch (mNaw3) {
            case NAW3_ADILA:
                sharh.append("- المسألة عادلة (تساوى أصلها مع أسهمها).\n");
                break;
            case NAW3_RAD:
                sharh.append(String.format("- المسألة ناقصة (أسهمها أقل من أصلها) فيرد الباقي %d على أصحاب الفروض عدى الزوجين.\n", mBaqi));
                break;
            case NAW3_AWL:
                sharh.append(String.format("- المسألة عائلة (أسهمها أكثر من أصلها)، تعول إلى %d.\n", mAwl));
            default:
                break;
        }

        if (mTa3seeb) {
            if (mIstighraq) {
                sharh.append("- استغرق أصحاب الفروض الأسهم ولم يبق للورثة بالتعصيب شيء.\n");
            } else {
                sharh.append(String.format("- الباقي %d وعدد رؤوسه %d.\n", mBaqi, mAdadRo2osAlbaqi));
            }
        }

        if (isInkissar() ) {
            sharh.append("- المسألة فيها انكسار وتصح من " + mMissah + ".\n");
        }

        return sharh.toString();
    }

    // Here is an example on how to retrieve the results to display them.
    public void printTable() {
        // رسم الجدول
        StringBuilder table = new StringBuilder();

        table.append("┌──────────┐\n");       // https://en.wikipedia.org/wiki/Box-drawing_character
        if (getAwl() != 0) {
            table.append(String.format("│ %d\t │\n", getAwl()));
            table.append("├──────┤\n");
            table.append(String.format("│ ع%d\t │\n", getAsl()));
        } else {
            table.append(String.format("│ %d\t │\n", getAsl()));
        }

        boolean jadaFirst = true;
        boolean shirkatTa3seebFirst = true;
        List<Mirath> all = getMawarith();

        if (isInkissar()) {
            table.append(String.format("├──────────┼──────────┬────────────┬──────────┐\n"));
            for (Mirath m : all) {
                if (m.isShirka() && m.isFardh() && m.isJadah() ) {
                    if (jadaFirst) {
                        table.append(String.format("│ %s\t │ %s ↓\t│ %s\t │ %s\t │\n", m.getNassibFardi(), m.getNassibMojmal(), m.getIsm(), m.getHokom()));
                        jadaFirst = false;
                    } else {
                        table.append(String.format("│ %s\t │ \t│ %s\t │ %s\t │\n", m.getNassibFardi(), m.getIsm(), m.getHokom()));
                    }
                } else if (m.isShirka() && m.isTa3seeb() && isShirkaTa3seeb()) {
                    if (shirkatTa3seebFirst) {
                        table.append(String.format("│ %s\t │ %s ↓\t│ %s\t │ %s\t │\n", m.getNassibFardi(), m.getNassibMojmal(), m.getIsm(), m.getHokom()));
                        shirkatTa3seebFirst = false;
                    } else {
                        table.append(String.format("│ %s\t │ \t│ %s\t │ %s\t │\n", m.getNassibFardi(), m.getIsm(), m.getHokom()));
                    }
                } else {
                    table.append(String.format("│ %s\t│ %s\t │ %s\t │\n", m.getNassibMojmal(), m.getIsm(), m.getHokom()));
                }

                if (all.indexOf(m) == (all.size() - 1)) {
                    table.append("└──────────┴──────────┴──────────┴──────────┘\n");
                } else {
                    table.append("├──────────┼──────────┼──────────┼──────────┤\n");
                }
            }
        }
        else {
            table.append(String.format("├──────────┼──────────┬──────────┐\n"));
            for (Mirath m : all) {
                if (m.isShirka() && m.isFardh() && m.isJadah()) {
                    if (jadaFirst) {
                        table.append(String.format("│ %s ↓\t│ %s\t │ %s\t │\n", m.getNassibMojmal(), m.getIsm(), m.getHokom()));
                        jadaFirst = false;
                    } else {
                        table.append(String.format("│ \t│ %s\t │ %s\t │\n", m.getIsm(), m.getHokom()));
                    }
                } else if (m.isShirka() && m.isTa3seeb() && isShirkaTa3seeb()) {
                    if (shirkatTa3seebFirst) {
                        table.append(String.format("│ %s ↓\t│ %s\t │ %s\t │\n", m.getNassibMojmal(), m.getIsm(), m.getHokom()));
                        shirkatTa3seebFirst = false;
                    } else {
                        table.append(String.format("│ \t│ %s\t │ %s\t │\n", m.getIsm(), m.getHokom()));
                    }
                } else {
                    table.append(String.format("│ %s\t│ %s\t │ %s\t │\n", m.getNassibMojmal(), m.getIsm(), m.getHokom()));
                }

                if (all.indexOf(m) == (all.size() - 1)) {
                    table.append("└──────────┴──────────┴──────────┘\n");
                } else {
                    table.append("├──────────┼──────────┼──────────┤\n");
                }
            }
        }
        System.out.println(table.toString());
    }

    public boolean isHal() { return mHal; }

    public boolean isFardh() { return mFardh; }

    public boolean isTa3seeb() { return mTa3seeb; }

    public boolean isTassawi() { return mTassawi; }

    public boolean isShirkaTa3seeb() { return mShirkaTa3seeb; }

    public boolean isInkissar() { return mAwl != 0 ? mAwl != mMissah : mAsl != mMissah;}

    public int getAsl() { return mAsl; }

    public int getBaqi() { return mBaqi; }

    public int getAwl() { return mAwl; }

    public int getMissah() { return mMissah; }

    public String getSpecialCase() { return mSpecialCase; }

    public List<Mirath> getMawarith() {
        ArrayList<Mirath> all = new ArrayList<>();

        all.addAll(mWarathah);
        all.addAll(mMahjoobin);

        return all;
    }

    public int getAdadRo2os() { return mAdadRo2osAlbaqi; }

    // TODO move to Utilities clas
    private int lcm(int a, int b) {
        if (a == 0 || b == 0) { return 0; }
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

    private int gcd(int a, int b) {  // Euclid's Algorithm
        if (b == 0) { return a; }
        return gcd(b, a % b);
    }
}
