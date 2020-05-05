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

    private enum AljadMa3aAlikhwa {
        LA,
        NA3AM,
        MA3A_FARDH,
        THULUTH_ALMAL,
        MUQASSAMA,
        SUDUSS,
        THULUTH_ALBAQI,
    };
    private AljadMa3aAlikhwa mAljadMa3aAlikhwa;

    private enum Naw3 {  // TODO public or package private
        NAW3_NONE,
        NAW3_ADILA,
        NAW3_RAD,
        NAW3_AWL,
    };

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

    private List<WarathaOuput> mHal;

    private boolean m_alakhawat_ashakikat_3assabat_ma3a_lghayr;
    private boolean m_alakhawat_3assabat_ma3a_lghayr;

    private String mSpecialCase;
    private String mSharh;

    public Massala() {
        mInput = null;

        mHal = new ArrayList<>();
        mHal.add(new WarathaOuput());

        mSpecialCase = null;
        mSharh = "لم يتم إدخال أية ورثة.\n";

        m_alakhawat_ashakikat_3assabat_ma3a_lghayr = false;
        m_alakhawat_3assabat_ma3a_lghayr = false;

        mAljadMa3aAlikhwa = AljadMa3aAlikhwa.LA;
    }

    private void addMirath(Mirath m) { mHal.get(0).mWarathah.add(m); }

    private void addMirath(int halIdx, Mirath m) {
        if (halIdx < mHal.size()) {
            mHal.get(halIdx).mWarathah.add(m);
        }
    }

    private void addHajb(Warith warith, int nw, String hajib, Warith hajib2, int nh)
    {
        for(Mirath m : mHal.get(0).mMahjoobin) {
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
        mHal.get(0).mMahjoobin.add(new Mirath(warith, hajb, nw));
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
        // أصحاب الفروض
        mirathAzawj(far3_warith);
        mirathAzawjat(far3_warith);
        mirathAlab(far3_warith_dhakar, far3_warith_ontha, jam3_alikhwa);
        mirathAljad(far3_warith_dhakar, far3_warith_ontha, alikhwa_alashika_wa_li_ab);
        mirathAlom(far3_warith, jam3_alikhwa);
        mirathAljadat();
        mirathAwladAlom(far3_warith, asl_warith_dhaker);
        mirathAlbanatBiAlfardh();
        mirathAlakhawatAshakikatBiAlfardh(far3_warith_dhakar, far3_warith_ontha);
        mirathAlakhawatLiAbBiAlfardh(far3_warith_dhakar, far3_warith_ontha, alikhwa_alashika_wa_li_ab);
        mHal.get(0).mNbrFurudh = mHal.get(0).mWarathah.size();

        // أصحاب التصعيب
        mirathAlabnaWaAlbanatBita3seeb();
        mirathAbnaWaBanatAlabnaBita3seeb();
        if (mAljadMa3aAlikhwa == AljadMa3aAlikhwa.LA) {
            mirathAlashikaWaAshakikatBita3seeb(0, far3_warith_dhakar, false, false);
            mirathAlikhwaWaAlakhawatLiAbBita3seeb(0, far3_warith_dhakar, false, false);
        }
        else {
            mirathAljadMa3aAlikhwa();
        }
        mirathAbnaAlikhwaAlashika(far3_wa_asl_warith_dhaker, alikhwa_alashika_wa_li_ab);
        mirathAbnaAlikhwaLiAb(far3_wa_asl_warith_dhaker, alikhwa_alashika_wa_li_ab);
        mirathAla3mamAlashika(far3_wa_asl_warith_dhaker, alikhwa_wa_abna_alikhwa);
        mirathAla3mamLiAb(far3_wa_asl_warith_dhaker, alikhwa_wa_abna_alikhwa);
        mirathAbnaAla3mamAlashika(far3_wa_asl_warith_dhaker, alikhwa_wa_abna_alikhwa, ala3mam);
        mirathAbnaAla3mamLiAb(far3_wa_asl_warith_dhaker, alikhwa_wa_abna_alikhwa, ala3mam);
        mHal.get(0).mNbr3assabat = mHal.get(0).mWarathah.size() - mHal.get(0).mNbrFurudh;

        if (mHal.get(0).mWarathah.size() == 0) {
            System.out.println(mSharh);
            return;
        }

        if (mAljadMa3aAlikhwa == AljadMa3aAlikhwa.MA3A_FARDH) {
            mHal.get(1).copyHal(mHal.get(0));
            mHal.get(2).copyHal(mHal.get(0));
        }

        for (WarathaOuput hal : mHal) {
            hissabAlaslWaRo2os(hal);
            hissabAlashomWaAlbaqi(hal);
        }

        if (mAljadMa3aAlikhwa == AljadMa3aAlikhwa.MA3A_FARDH) {
            hissabAlahdhahLiljad();
        }

        hissabAsharhPrefix();
        hissabAnsiba();
        hissabAsharhSuffix();
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
                mHal.get(0).mTassawi = false;
                mHal.get(0).mShirkaTa3seeb = true;
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
        boolean alakhawat_ashakikat_wa_li_ab = (mInput.get_alakhawat_ashakikat() + mInput.get_alakhawat_li_ab()) > 0;

        if (!mInput.alab()) {        // no hajb by ab
            if (far3_warith_dhakar) {
                sharh = Warith.ALJAD.getSharhPrefix();
                sharh += "السدس 1\\6 فرضا فقط";
                bast = 1;
                maqam = 6;
                addMirath(new Mirath(Warith.ALJAD, sharh, bast, maqam));
            }
            else if (!alikhwa_alashika_wa_li_ab && !alakhawat_ashakikat_wa_li_ab) {
                // الجد يرث فرضا وتعصيبا أو تعصيبا فقط
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
                sharh += Warith.ALJAD.getSharhPrefix();
                sharh += "الباقي تعصيبا بالنفس";
                addMirath(new Mirath(Warith.ALJAD, sharh, bast, maqam, true));
            }
            else if (mInput.get_madhab() == Madhab.HANAFI) {
                if (mInput.get_alikhwa_alashika() >= 1) {
                    addHajb(Warith.ALIKHWA_ALASHIKA, mInput.get_alikhwa_alashika(), Warith.ALJAD);
                }
                if (mInput.get_alakhawat_ashakikat() >= 1) {
                    addHajb(Warith.ALAKHAWAT_ASHAKIKAT, mInput.get_alakhawat_ashakikat(), Warith.ALJAD);
                }
                if (mInput.get_alikhwa_li_ab() >= 1) {
                    addHajb(Warith.ALIKHWA_LI_AB, mInput.get_alikhwa_li_ab(), Warith.ALJAD);
                }
                if (mInput.get_alakhawat_li_ab() >= 1) {
                    addHajb(Warith.ALAKHAWAT_LI_AB, mInput.get_alakhawat_li_ab(), Warith.ALJAD);
                }
            }
            else {
                // عند الجمهور الجد يرث مع الإخوة بالأحظ له
                mAljadMa3aAlikhwa = AljadMa3aAlikhwa.NA3AM;
                // يؤجل إلى ما بعد أصحاب الفروض وقبل الفصل في ميراث الإخوة بالتعصيب
            }
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
            mHal.get(0).mTassawi = false;
            mHal.get(0).mShirkaTa3seeb = true;
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

        if (mInput.aljadah_li_ab() && (!mInput.alab() || mInput.get_madhab() == Madhab.HAMBALI)) {
            sharh = "الجدة لأب ";
            if (mInput.aljadah_li_om()) {
                sharh += "تشترك (بالتساوي) مع الجدة لأم في السدس 1\\6 فرضا";
                ro2os = 2;
            } else {
                sharh += "ترث السدس 1\\6 فرضا";
                ro2os = 1;
            }
            addMirath(new Mirath(Warith.ALJADAH_LI_AB, sharh, 1, 6, ro2os));
        }

        if (mInput.aljadah_li_om()) {
            sharh = "الجدة لأم ";
            if (mInput.aljadah_li_ab() && (!mInput.alab() || mInput.get_madhab() == Madhab.HAMBALI)) {
                sharh += "تشترك (بالتساوي) مع الجدة لأب في السدس 1\\6 فرضا";
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
                        sharh = Warith.ALIKHWA_LI_OM.getSharhPrefix(nbr_a, Warith.ALAKHAWAT_LI_OM, nbr_b, true);
                        sharh += "الثلث 1\\3 فرضا";
                        addMirath(new Mirath(Warith.ALIKHWA_LI_OM, nbr_a, sharh, 1, 3, awlad_alom));

                        sharh = Warith.ALAKHAWAT_LI_OM.getSharhPrefix(nbr_b, Warith.ALIKHWA_LI_OM, nbr_a, true);
                        sharh += "الثلث 1\\3 فرضا";
                        addMirath(new Mirath(Warith.ALAKHAWAT_LI_OM, nbr_b, sharh, 1, 3, awlad_alom));
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

    private void mirathAlbanatBiAlfardh() {
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

    private void mirathAlakhawatAshakikatBiAlfardh(boolean far3_warith_dhakar, boolean far3_warith_ontha) {
        // mirath alakhawat ashakikat 3inda 3adam wojud alikhwa alashika
        int nbr = mInput.get_alakhawat_ashakikat();
        if ((nbr == 0) || mInput.alab() || far3_warith_dhakar || (mInput.get_alikhwa_alashika() > 0) || (mAljadMa3aAlikhwa != AljadMa3aAlikhwa.LA)) return;

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
            sharh = Warith.ALAKHAWAT_ASHAKIKAT.getSharhPrefix(nbr);
            sharh += "الباقي تعصيبا مع الغير";
            addMirath(new Mirath(Warith.ALAKHAWAT_ASHAKIKAT, nbr, sharh));
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

    private void mirathAlakhawatLiAbBiAlfardh(boolean far3_warith_dhakar, boolean far3_warith_ontha, boolean alikhwa_alashika_wa_li_ab) {
        // mirath alakhawat li ab 3inda 3adam wojud alikhwa
        int nbr = mInput.get_alakhawat_li_ab();
        if ((nbr == 0) || mInput.alab() || far3_warith_dhakar || alikhwa_alashika_wa_li_ab || (mAljadMa3aAlikhwa != AljadMa3aAlikhwa.LA) || m_alakhawat_ashakikat_3assabat_ma3a_lghayr) return;

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
            sharh = Warith.ALAKHAWAT_LI_AB.getSharhPrefix(nbr);
            sharh += " الباقي تعصيبا مع الغير";
            addMirath(new Mirath(Warith.ALAKHAWAT_LI_AB, nbr, sharh));
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

    private void mirathAlabnaWaAlbanatBita3seeb() {
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
            mHal.get(0).mTassawi = false;
            mHal.get(0).mShirkaTa3seeb = true;
            sharh = Warith.ALABNA.getSharhPrefix(nbr_a, Warith.ALBANAT, nbr_b);
            sharh += "الباقي تعصيبا بالنفس";
            addMirath(new Mirath(Warith.ALABNA, nbr_a, sharh, ro2os));

            sharh = Warith.ALBANAT.getSharhPrefix(nbr_b, Warith.ALABNA, nbr_a);
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

    private void mirathAbnaWaBanatAlabnaBita3seeb() {
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
            mHal.get(0).mTassawi = false;
            mHal.get(0).mShirkaTa3seeb = true;
            sharh = Warith.ABNA_ALABNA.getSharhPrefix(nbr_a, Warith.BANAT_ALABNA, nbr_b);
            sharh += "الباقي تعصيبا بالنفس";
            addMirath(new Mirath(Warith.ABNA_ALABNA, nbr_a, sharh, ro2os));

            sharh = Warith.BANAT_ALABNA.getSharhPrefix(nbr_b, Warith.ABNA_ALABNA, nbr_a);
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

    private void mirathAlashikaWaAshakikatBita3seeb(int halIdx, boolean far3_warith_dhakar, boolean muqassamaMa3aAljad, boolean ta3seebBiljad) {
        // Here be dragons, يعُامل الجد كأن لم يكن
        if (far3_warith_dhakar || mInput.alab()) return;
        // assert(halIdx < 0 || halIdx >= mHal.size());

        String sharh;
        int nbr_a = mInput.get_alikhwa_alashika();
        int nbr_b = mInput.get_alakhawat_ashakikat();

        if (nbr_a > 0) {
            if (nbr_b == 0) {
                sharh = Warith.ALIKHWA_ALASHIKA.getSharhPrefix(nbr_a);
                sharh += "الباقي تعصيبا بالنفس";
                if (muqassamaMa3aAljad) { sharh += " ومقاسمة مع الجد"; }
                addMirath(halIdx, new Mirath(Warith.ALIKHWA_ALASHIKA, nbr_a, sharh));
            } else {
                String sharh2;
                mHal.get(halIdx).mTassawi = false;
                mHal.get(halIdx).mShirkaTa3seeb = true;
                sharh = Warith.ALIKHWA_ALASHIKA.getSharhPrefix(nbr_a, Warith.ALAKHAWAT_ASHAKIKAT, nbr_b);
                sharh2 = Warith.ALAKHAWAT_ASHAKIKAT.getSharhPrefix(nbr_b, Warith.ALIKHWA_ALASHIKA, nbr_a);
                sharh += "الباقي تعصيبا بالنفس";
                sharh2 += "الباقي تعصيبا بالغير";
                if (muqassamaMa3aAljad) {
                    sharh += " ومقاسمة مع الجد";
                    sharh2 += " ومقاسمة مع الجد";
                }
                addMirath(halIdx, new Mirath(Warith.ALIKHWA_ALASHIKA, nbr_a, sharh, 2 * nbr_a + nbr_b));
                addMirath(halIdx, new Mirath(Warith.ALAKHAWAT_ASHAKIKAT, nbr_b, sharh2, 2 * nbr_a + nbr_b));
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
        else if ((nbr_b > 0) && (ta3seebBiljad || ta3seebBiljad)) {
            // الأخت دون الأخ مع الجد تصبح عصبة به فتقاسمه وتحجب من يحجبه الأخ
            mHal.get(halIdx).mTassawi = false;
            sharh = Warith.ALAKHAWAT_ASHAKIKAT.getSharhPrefix(nbr_b, false);
            sharh += "الباقي تعصيبا بالغير";
            if (muqassamaMa3aAljad) { sharh += " ومقاسمة مع الجد"; }
            addMirath(halIdx, new Mirath(Warith.ALAKHAWAT_ASHAKIKAT, nbr_b, sharh));

            // الحجب بالأخوات الشقيقات في وجود الجد العصاب لهن
            if (mInput.get_alikhwa_li_ab() > 0) {
                addHajb(Warith.ALIKHWA_LI_AB, mInput.get_alikhwa_li_ab(), Warith.ALAKHAWAT_ASHAKIKAT, nbr_b);
            }
            if (mInput.get_alakhawat_li_ab() > 0) {
                addHajb(Warith.ALAKHAWAT_LI_AB, mInput.get_alakhawat_li_ab(), Warith.ALAKHAWAT_ASHAKIKAT, nbr_b);
            }
            if (mInput.get_abna_alikhwa_alashika() > 0) {
                addHajb(Warith.ABNA_ALIKHWA_ALASHIKA, mInput.get_abna_alikhwa_alashika(), Warith.ALAKHAWAT_ASHAKIKAT, nbr_b);
            }
            if (mInput.get_abna_alikhwa_li_ab() > 0) {
                addHajb(Warith.ABNA_ALIKHWA_LI_AB, mInput.get_abna_alikhwa_alashika(), Warith.ALAKHAWAT_ASHAKIKAT, nbr_b);
            }
            if (mInput.get_ala3mam_alashika() > 0) {
                addHajb(Warith.ALA3MAM_ALASHIKA, mInput.get_ala3mam_alashika(), Warith.ALAKHAWAT_ASHAKIKAT, nbr_b);
            }
            if (mInput.get_ala3mam_li_ab() > 0) {
                addHajb(Warith.ALA3MAM_LI_AB, mInput.get_ala3mam_li_ab(), Warith.ALAKHAWAT_ASHAKIKAT, nbr_b);
            }
            if (mInput.get_abna_ala3mam_alashika() > 0) {
                addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, mInput.get_abna_ala3mam_alashika(), Warith.ALAKHAWAT_ASHAKIKAT, nbr_b);
            }
            if (mInput.get_abna_ala3mam_li_ab() > 0) {
                addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ALAKHAWAT_ASHAKIKAT, nbr_b);
            }
        }
    }

    private void mirathAlikhwaWaAlakhawatLiAbBita3seeb(int halIdx, boolean far3_warith_dhakar, boolean muqassamaMa3aAljad, boolean ta3seebBiljad) {
        if (far3_warith_dhakar || mInput.alab() || (mInput.get_alikhwa_alashika() > 0) || m_alakhawat_ashakikat_3assabat_ma3a_lghayr || (mInput.get_alakhawat_ashakikat() > 0 && muqassamaMa3aAljad)) return;
        // assert(halIdx < 0 || halIdx >= mHal.size());

        String sharh;
        int nbr_a = mInput.get_alikhwa_li_ab();
        int nbr_b = mInput.get_alakhawat_li_ab();

        if (nbr_a > 0) {
            if (nbr_b == 0) {
                sharh = Warith.ALIKHWA_LI_AB.getSharhPrefix(nbr_a);
                sharh += "الباقي تعصيبا بالنفس";
                if (muqassamaMa3aAljad) { sharh += " ومقاسمة مع الجد"; }
                addMirath(halIdx, new Mirath(Warith.ALIKHWA_LI_AB, nbr_a, sharh, nbr_a));
            } else {
                String sharh2;
                mHal.get(halIdx).mTassawi = false;
                mHal.get(halIdx).mShirkaTa3seeb = true;
                sharh = Warith.ALIKHWA_LI_AB.getSharhPrefix(nbr_a, Warith.ALAKHAWAT_LI_AB, nbr_b);
                sharh2 = Warith.ALAKHAWAT_LI_AB.getSharhPrefix(nbr_b, Warith.ALIKHWA_LI_AB, nbr_a);
                sharh += "الباقي تعصيبا بالنفس";
                sharh2 += "الباقي تعصيبا بالغير";
                if (muqassamaMa3aAljad) {
                    sharh += " ومقاسمة مع الجد";
                    sharh2 += " ومقاسمة مع الجد";
                }
                addMirath(halIdx, new Mirath(Warith.ALIKHWA_LI_AB, nbr_a, sharh, 2 * nbr_a + nbr_b));
                addMirath(halIdx, new Mirath(Warith.ALAKHAWAT_LI_AB, nbr_b, sharh2, 2 * nbr_a + nbr_b));
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
        } else if ((nbr_b > 0) && (ta3seebBiljad || muqassamaMa3aAljad)) {
            // الأخت دون الأخ مع الجد تصبح عصبة به فتقاسمه وتحجب من يحجبه الأخ
            mHal.get(halIdx).mTassawi = false;
            sharh = Warith.ALAKHAWAT_ASHAKIKAT.getSharhPrefix(nbr_b, false);
            sharh += "الباقي تعصيبا بالغير";
            if (muqassamaMa3aAljad) { sharh += " ومقاسمة مع الجد"; }
            addMirath(halIdx, new Mirath(Warith.ALAKHAWAT_LI_AB, nbr_b, sharh));

            // alhajb by alikhwa li ab
            if (mInput.get_abna_alikhwa_alashika() > 0) {
                addHajb(Warith.ABNA_ALIKHWA_ALASHIKA, mInput.get_abna_alikhwa_alashika(), Warith.ALAKHAWAT_LI_AB, nbr_b);
            }
            if (mInput.get_abna_alikhwa_li_ab() > 0) {
                addHajb(Warith.ABNA_ALIKHWA_LI_AB, mInput.get_abna_alikhwa_li_ab(), Warith.ALAKHAWAT_LI_AB, nbr_b);
            }
            if (mInput.get_ala3mam_alashika() > 0) {
                addHajb(Warith.ALA3MAM_ALASHIKA, mInput.get_ala3mam_alashika(), Warith.ALAKHAWAT_LI_AB, nbr_b);
            }
            if (mInput.get_ala3mam_li_ab() > 0) {
                addHajb(Warith.ALA3MAM_LI_AB, mInput.get_ala3mam_li_ab(), Warith.ALAKHAWAT_LI_AB, nbr_b);
            }
            if (mInput.get_abna_ala3mam_alashika() > 0) {
                addHajb(Warith.ABNA_ALA3MAM_ALASHIKA, mInput.get_abna_ala3mam_alashika(), Warith.ALAKHAWAT_LI_AB, nbr_b);
            }
            if (mInput.get_abna_ala3mam_li_ab() > 0) {
                addHajb(Warith.ABNA_ALA3MAM_LI_AB, mInput.get_abna_ala3mam_li_ab(), Warith.ALAKHAWAT_LI_AB, nbr_b);
            }
        }
    }

    private void mirathAljadMa3aAlikhwa() {
        String sharh;

        boolean tassawi = mInput.get_alakhawat_ashakikat() == 0 && mInput.get_alakhawat_li_ab() == 0;

        int ro2os_aljad = tassawi ? 1 : 2;
        int ro2os_ashika = tassawi ? mInput.get_alikhwa_alashika() : mInput.get_alikhwa_alashika() * 2 + mInput.get_alakhawat_ashakikat();
        int ro2os_li_ab = tassawi ? mInput.get_alikhwa_li_ab() : mInput.get_alikhwa_li_ab() * 2 + mInput.get_alakhawat_li_ab();
        int ro2os_alikhwa = ro2os_ashika + ro2os_li_ab;
        int ro2os = ro2os_aljad + ro2os_alikhwa;

        sharh = Warith.ALJAD.getSharhMa3aAlikhwaPrefix(mInput);
        // هل هناك صاحب فرض آخر غير الجد
        if (mHal.get(0).mNbrFurudh == 0) {
            if (ro2os_alikhwa >= 2 * ro2os_aljad) {
                mAljadMa3aAlikhwa = AljadMa3aAlikhwa.THULUTH_ALMAL;
                mHal.get(0).mNbrFurudh++;
                sharh += "الثلث 1\\3";
                addMirath(new Mirath(Warith.ALJAD, sharh, 1, 3));

                mirathAlashikaWaAshakikatBita3seeb(0, false, false, true);
                mirathAlikhwaWaAlakhawatLiAbBita3seeb(0, false, false, true);
            } else {
                mAljadMa3aAlikhwa = AljadMa3aAlikhwa.MUQASSAMA;
                mHal.get(0).mShirkaTa3seeb = true;
                sharh += "المقاسمة";
                if ((ro2os_ashika > 0) && (ro2os_li_ab > 0)) {
                    mHal.get(0).mMu3addah = true;
                    sharh += " بالمُعادّة";
                }
                addMirath(new Mirath(Warith.ALJAD, 1, sharh, ro2os)); // يُحسب عليه جميع الإخوة

                mirathAlashikaWaAshakikatBita3seeb(0, false, true, true);
                mirathAlikhwaWaAlakhawatLiAbBita3seeb(0, false, true, true);
            }
        } else {
            mAljadMa3aAlikhwa = AljadMa3aAlikhwa.MA3A_FARDH;

            //  موقتا نحسب ثلاث مسائل حيث يأخذ الجد في الأولى السدس فرضا وفي الثانية ثلث الباقي
            // وفي الثالثة يقاسم الإخوة، ثم عند الحساب بعد إكمال كل الورثة نختار له الأحظ
            mHal.add(new WarathaOuput());
            mHal.add(new WarathaOuput());

            // 1
            mHal.get(0).mNbrFurudh++;
            addMirath(0, new Mirath(Warith.ALJAD, sharh + "السدس 1\\6 فرضا", 1, 6));
            mirathAlashikaWaAshakikatBita3seeb(0, false, false, true);
            mirathAlikhwaWaAlakhawatLiAbBita3seeb(0, false, false, true);

            // 2
            mHal.get(1).mShirkaTa3seeb = true;
            addMirath(1, new Mirath(Warith.ALJAD, sharh + "ثلث 1\\3 الباقي", 1, 3, true, 3));
            mirathAlashikaWaAshakikatBita3seeb(1, false, false, true);
            mirathAlikhwaWaAlakhawatLiAbBita3seeb(1, false, false, true);

            // 3
            mHal.get(2).mShirkaTa3seeb = true;
            sharh += "المقاسمة";
            if ((ro2os_ashika > 0) && (ro2os_li_ab > 0)) {
                mHal.get(2).mMu3addah = true;
                sharh += " بالمُعادّة";
            }
            addMirath(2, new Mirath(Warith.ALJAD, 1, sharh, ro2os)); // يُحسب عليه جميع الإخوة
            mirathAlashikaWaAshakikatBita3seeb(2, false, true, true);
            mirathAlikhwaWaAlakhawatLiAbBita3seeb(2, false, true, true);
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

    private void hissabAlaslWaRo2os(WarathaOuput hal) {
        // استخراج أصل المسألة (ص 40 من كتاب "الفرائض المُيسر) وعدد رؤوس ورثة الباقي"
        hal.mAsl = 1;
        for (Mirath m : hal.mWarathah) {
            if (m.isFardh()) {
                hal.mAsl = lcm(hal.mAsl, m.getMaqam());
            }

            if (m.isTa3seeb() && (m.getRo2os() != 1)) {
                if (hal.mRo2osAlbaqi == 1) {
                    hal.mRo2osAlbaqi = m.getRo2os();            // بالأخص من يأخذ ثلث الباقي أو الجد مقاسمة مع الإخوة بصنفيهم (المعادة)
                } else if (hal.mRo2osBaqiAlbaqi == 1) {
                    hal.mRo2osBaqiAlbaqi = m.getRo2os();        // من يأخذ ثلثي الباقي بعد إعطاء ثلثه أو الإخوة بصنفيهم بعد الجد
                }
            }
        }
        if ((hal.mNbrFurudh == 0) && (hal.mNbr3assabat > 0)) {
            hal.mAsl = hal.mRo2osAlbaqi;
        }
    }

    private void hissabAlashomWaAlbaqi(WarathaOuput hal) {
        // حساب مجموع أسهم أصحاب الفروض وباقي المسألة
        int ashom = 0;
        boolean jadahCounted = false;
        boolean waladAlomCounted = false;
        for (Mirath m : hal.mWarathah) {
            if (m.isFardh()) {
                int fardh = m.getBast() * hal.mAsl / m.getMaqam();
                m.setFardh(fardh);
                if (m.isJadah()) {
                    if (jadahCounted) { continue; }
                    jadahCounted = true;
                }
                else if (m.isWaladAlom()) {
                    if (waladAlomCounted) { continue; }
                    waladAlomCounted = true;
                }
                ashom += fardh;
            }
        }

        hal.mBaqi = hal.mAsl - ashom;
        if (hal.mBaqi == 0) {
            if (hal.mNbrFurudh > 0) {
                hal.mNaw3 = Naw3.NAW3_ADILA;
            }
            if (hal.mNbr3assabat > 0) {
                hal.mIstighraq = true;
            }
        } else if (hal.mBaqi > 0) { // مسألة فيها باقي
            if ((hal.mNbr3assabat == 0) && (ashom != 0)) {            // مسألة ناقصة فيها رد
                hal.mNaw3 = Naw3.NAW3_RAD;
                // TODO rad albaqi
            }
        } else {                                // مسألة عائلة
            hal.mNaw3 = Naw3.NAW3_AWL;
            hal.mAwl = ashom;
            if (hal.mNbr3assabat > 0) {
                hal.mIstighraq = true;
            }
            // TODO  XXX mBaqi < 0
            hal.mBaqi = 0;
        }
    }

    private void hissabAlahdhahLiljad() {
        // اختيار الأخظ للجد في وجود صاحب فرض آخر غيره
        if (mHal.get(0).mBaqi == 0) {
            mAljadMa3aAlikhwa = AljadMa3aAlikhwa.SUDUSS;
            mHal.remove(2);
            mHal.remove(1);
        } else { // if (mHal.get(0).mBaqi > 0) {
            // compare Jad parts in the three cases with ikhwa
            double asl0 = mHal.get(0).mAsl;
            double baqi0 = mHal.get(0).mBaqi;
            double nassib0 = 1.0 / 6;

            if (nassib0 >= (baqi0 / asl0)) {
                mAljadMa3aAlikhwa = AljadMa3aAlikhwa.SUDUSS;
                mHal.remove(2);
                mHal.remove(1);
            } else {
                double asl1 = mHal.get(1).mAsl;
                double baqi1 = mHal.get(1).mBaqi;
                double nassib1 = (baqi1 / asl1) / 3;

                int jadIdx = mHal.get(2).mNbrFurudh - 1;
                int ro2os = mHal.get(2).mWarathah.get(jadIdx).getRo2os();
                double nassib2 = (baqi1 / asl1) / ro2os;
                if (!mHal.get(2).mTassawi) {
                    nassib2 *= 2;
                }

                if ((nassib0 >= nassib1) && (nassib0 >= nassib2)) {
                    mAljadMa3aAlikhwa = AljadMa3aAlikhwa.SUDUSS;
                    mHal.remove(2);
                    mHal.remove(1);
                } else if ((nassib1 >= nassib0) && (nassib1 >= nassib2)) {
                    mAljadMa3aAlikhwa = AljadMa3aAlikhwa.THULUTH_ALBAQI;
                    mHal.remove(2);
                    mHal.remove(0);
                } else {
                    mAljadMa3aAlikhwa = AljadMa3aAlikhwa.MUQASSAMA;
                    mHal.remove(1);
                    mHal.remove(0);
                }
            }
        }
    }

    private void hissabAnsiba() {
        // قسمة الأسهم في الجدول
        int nassib = 0;
        int missahFactor = 1;
        ArrayList<Mirath> all = new ArrayList<>();

        all.addAll(mHal.get(0).mWarathah);
        all.addAll(mHal.get(0).mMahjoobin);
        for (Mirath m : all) {          // Here be dragons
            StringBuilder nassibMojmal = new StringBuilder();
            StringBuilder nassibFardi = new StringBuilder();

            if (m.isFardh()) {
                nassib = m.getFardh();
                nassibMojmal.append(nassib);
                nassibFardi.append(nassib);

                if (m.isTa3seeb()) { // حالات الأب والجد يرثان 1\6 + باقي
                    // assert m.getBast() == 1 && m.getMaqam() == 6
                    nassibMojmal.append(" + ").append(mHal.get(0).mBaqi);
                    nassibFardi.append(" + ").append(mHal.get(0).mBaqi);

                    if (m.isShirka()) {                 // assert Aljada
//                        nassibMojmal.append("ش");

                        int factor = mHal.get(0).mTassawi || m.getWarith().isOntha() ? 1 : 2;
                        nassibFardi.append(" * ");
                        nassibFardi.append(factor);
                        nassibFardi.append("\\").append(m.getRo2os());

                        int bast = mHal.get(0).mBaqi * factor;
                        if (bast % m.getRo2os() == 0) {
                            nassib += bast / m.getRo2os();
                            nassibFardi.append(" = ").append(nassib);
                        }
                        else { // إنكسار
                            missahFactor = lcm(missahFactor, m.getRo2os() / gcd(bast, m.getRo2os()));
                            nassib = -bast;                 // bast only for now
                        }
                    } else {
                        nassib += mHal.get(0).mBaqi;
                    }
                } else {              // وارث بالفرض فقط
                    if (m.isShirka()) {
//                        nassibMojmal.append("ش");

                        nassibFardi.append(" * 1\\");
                        nassibFardi.append(m.getRo2os());
                        if (m.getFardh() % m.getRo2os() == 0) {
                            nassib = m.getFardh() / m.getRo2os();
                            nassibFardi.append(" = ").append(nassib);
                        }
                        else {  // إنكسار
                            missahFactor = lcm(missahFactor, m.getRo2os() / gcd(m.getFardh(), m.getRo2os()));
                            nassib = -m.getFardh();         // bast only for now
                        }
                    }
                }
            }
            else if (m.isTa3seeb()) {       // وارث بالتعصيب فقط
                int factor1;
                int ro2os1;
                boolean qissmatAlikhwa;

                switch (mAljadMa3aAlikhwa) {
                    case THULUTH_ALBAQI:
                        if (m.getWarith() == Warith.ALJAD) {
                            ro2os1 = 3;  // assert m.getRo2os() == mHal.get(0).mAdadRo2osAlbaqi == 3;
                            factor1 = 1;
                            qissmatAlikhwa = false;
                        } else {  // ikhwa
                            factor1 = 2;
                            ro2os1 = 3;
                            qissmatAlikhwa = true;
                        }
                        break;

                    case MUQASSAMA:
                        if (m.getWarith() == Warith.ALJAD) {
                            factor1 = mHal.get(0).mTassawi ? 1 : 2;
                            ro2os1 = m.getRo2os();
                            qissmatAlikhwa = false;
                        } else {  // ikhwa
                            int ro2os_aljad = mHal.get(0).mTassawi ? 1 : 2;
                            ro2os1 = mHal.get(0).mRo2osAlbaqi;
                            factor1 = ro2os1 - ro2os_aljad;
                            qissmatAlikhwa = true;
                        }
                        break;

                    default:
                        factor1 = ro2os1 = 0;
                        qissmatAlikhwa = true;
                        break;
                }

                int bast = mHal.get(0).mBaqi;
                int ro2os = 1;
                boolean shirka = false;

                nassibMojmal.append(bast);
                nassibFardi.append(bast);

                if (ro2os1 != 0) {
//                    nassibMojmal.append("ش");
                    shirka = true;

                    nassibFardi.append(" * ");
                    nassibFardi.append(factor1).append("\\").append(ro2os1);
                    bast *= factor1;
                    ro2os *= ro2os1;
                }

                if (qissmatAlikhwa && m.isShirka()) {
//                    if (!shirka) { nassibMojmal.append("ش"); }

                    int factor2 = mHal.get(0).mTassawi || m.getWarith().isOntha() ? 1 : 2;
                    bast *= factor2;

                    int ro2os2 = m.getRo2os();
                    nassibFardi.append(" * ").append(factor2).append("\\").append(ro2os2);
                    ro2os *= ro2os2;
                }

                if ((bast % ro2os) == 0) {
                    nassib = bast / ro2os;
                    nassibFardi.append(" = ").append(nassib);
                }
                else { //  إنكسار
                    missahFactor = lcm(missahFactor, ro2os / gcd(bast, ro2os));
                    nassib = -bast;                         // bast only for now
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

        mHal.get(0).mMissah =  mHal.get(0).mAwl == 0?  mHal.get(0).mAsl * missahFactor : mHal.get(0).mAwl * missahFactor;
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
                    else if (m.isTa3seeb() && m.getWarith() != Warith.ALJAD &&
                            (mAljadMa3aAlikhwa == AljadMa3aAlikhwa.THULUTH_ALBAQI
                            || mAljadMa3aAlikhwa == AljadMa3aAlikhwa.MUQASSAMA)) {
                        nassib = (-oldNassib * missahFactor) / (mHal.get(0).mRo2osAlbaqi * mHal.get(0).mRo2osBaqiAlbaqi);
                    }
                    else {
                        nassib = -oldNassib * missahFactor / m.getRo2os();
                    }
                }
                else {
                    nassib = oldNassib * missahFactor;
                }
                m.setNassib(nassib);
                m.setNassibFardi(m.getNassibFardi(true) + " -> "+ nassib);
            }
        }
    }
    public String getSharh() {  return mSharh; }

    private void hissabAsharhPrefix() {
        StringBuilder sharh = new StringBuilder();

        if (mSpecialCase != null /* TODO غراوين */) {
            sharh.append(mSpecialCase + "\n");
        }

        for (WarathaOuput hal : mHal) {
            sharh.append("\n");

            if (hal.mWarathah != null) {
                for (Mirath m : hal.mWarathah) {
                    sharh.append("- " + m.getSharh() + ".\n");
                }
            }

            if (hal.mMahjoobin != null) {
                for (Mirath m : hal.mMahjoobin) {
                    sharh.append("- " + m.getSharh() + ".\n");
                }
            }

            sharh.append("\n" + "- المسألة أصلها " + hal.mAsl);
            switch (hal.mNaw3) {
                case NAW3_ADILA:
                    sharh.append("، وهي عادلة (تساوى أصلها مع أسهمها).\n");
                    break;
                case NAW3_RAD:
                    sharh.append(" وهي ناقصة (أسهمها أقل من أصلها).\n");
                    sharh.append(String.format("- الباقي من الأصل %d يُرد على أصحاب الفروض ما عدا الزوجين (إن وجدوا).", hal.mBaqi));
                    sharh.append(" لقسمة الباقي يُمكن عمل مسألة جديدة فيها أصحاب الفروض  دون أحد الزوجين (إن وجدوا).\n");
                    // TODO. Ethier implement Rad and remove the previous line
                    // TODO. or add Rad buttong which is like 'change' button and eliminates zawj
                    // TODO. or keep it like that
                    break;
                case NAW3_AWL:
                    sharh.append(String.format(" وهي عائلة (أسهمها أكثر من أصلها)، تعول إلى %d.\n", hal.mAwl));
                    break;
                default:
                    sharh.append(".\n");
                    break;
            }

            if (hal.mNbr3assabat > 0) {
                if (hal.mIstighraq) {
                    sharh.append("- استغرق أصحاب الفروض الأسهم ولم يبق للورثة بالتعصيب شيء.\n");
                } else {
                    sharh.append(String.format("- الباقي من الأصل %d", hal.mBaqi));
                    switch (mAljadMa3aAlikhwa) {
                        case MUQASSAMA:
                            if (hal.mMu3addah) {
                                sharh.append(String.format("، يتقاسمه الجد و%s بالمُعادّة.", Warith.ALJAD.getAlikhwa(mInput)));
                                sharh.append(String.format(" أي يُحسب على الجد كل الإخوة الأشقاء ولأب فيكون سهمه من %d،", hal.mRo2osAlbaqi));
                                sharh.append(String.format(" ثم يحجب الأشقاءُ الإخوة لأب ويشتركون في ما بقي وعدد رؤوسه %d.\n", hal.mRo2osBaqiAlbaqi));
                            } else {
                                sharh.append(String.format("، يتقاسمه الجد و%s وعدد رؤوسه %d.\n", Warith.ALJAD.getAlikhwa(mInput), hal.mRo2osAlbaqi));
                            }
                            break;
                        case THULUTH_ALBAQI:
                            sharh.append(String.format("، يأخذ منه الجد الثلث ويتقاسم %s ما بقي وعدد رؤوسه %d.\n", hal.mBaqi, Warith.ALJAD.getAlikhwa(mInput), hal.mRo2osBaqiAlbaqi));
                            break;
                        case THULUTH_ALMAL:
                        case SUDUSS:
                        case LA:
                        default:
                            sharh.append(String.format(" وعدد رؤوسه %d.\n", hal.mBaqi, hal.mRo2osAlbaqi));
                            break;
                    }
                }
            }
        }

        mSharh = sharh.toString();
    }

    private void hissabAsharhSuffix() {
        if (mHal.get(0).isInkissar()) {
            mSharh += "- المسألة فيها انكسار وتصح من " + mHal.get(0).mMissah + ".\n";
        }
    }

    public boolean isTassawi() { return mHal.get(0).mTassawi; }

    public boolean isShirkaTa3seeb() { return mHal.get(0).mShirkaTa3seeb; }

    public boolean isInkissar() { return mHal.get(0).isInkissar();}

    public boolean isJinsayAwladAlom() { return (mInput.get_alikhwa_li_om() > 0) && (mInput.get_alakhawat_li_om() > 0);}

    public int getAsl() { return mHal.get(0).mAsl; }

    public int getBaqi() { return mHal.get(0).mBaqi; }

    public int getAwl() { return mHal.get(0).mAwl; }

    public int getMissah() { return mHal.get(0).mMissah; }

    public String getSpecialCase() { return mSpecialCase; }

    public List<Mirath> getMawarith() {
        ArrayList<Mirath> all = new ArrayList<>();

        all.addAll(mHal.get(0).mWarathah);
        all.addAll(mHal.get(0).mMahjoobin);

        return all;
    }

    // TODO move to Utilities class
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
