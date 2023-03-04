package com.example.android_development_assessment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SymbolsModel {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("symbols")
    @Expose
    private Symbols symbols;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Symbols getSymbols() {
        return symbols;
    }

    public void setSymbols(Symbols symbols) {
        this.symbols = symbols;
    }

    public class Symbols {

        @SerializedName("AED")
        @Expose
        private String aed;
        @SerializedName("AFN")
        @Expose
        private String afn;
        @SerializedName("ALL")
        @Expose
        private String all;
        @SerializedName("AMD")
        @Expose
        private String amd;
        @SerializedName("ANG")
        @Expose
        private String ang;
        @SerializedName("AOA")
        @Expose
        private String aoa;
        @SerializedName("ARS")
        @Expose
        private String ars;
        @SerializedName("AUD")
        @Expose
        private String aud;
        @SerializedName("AWG")
        @Expose
        private String awg;
        @SerializedName("AZN")
        @Expose
        private String azn;
        @SerializedName("BAM")
        @Expose
        private String bam;
        @SerializedName("BBD")
        @Expose
        private String bbd;
        @SerializedName("BDT")
        @Expose
        private String bdt;
        @SerializedName("BGN")
        @Expose
        private String bgn;
        @SerializedName("BHD")
        @Expose
        private String bhd;
        @SerializedName("BIF")
        @Expose
        private String bif;
        @SerializedName("BMD")
        @Expose
        private String bmd;
        @SerializedName("BND")
        @Expose
        private String bnd;
        @SerializedName("BOB")
        @Expose
        private String bob;
        @SerializedName("BRL")
        @Expose
        private String brl;
        @SerializedName("BSD")
        @Expose
        private String bsd;
        @SerializedName("BTC")
        @Expose
        private String btc;
        @SerializedName("BTN")
        @Expose
        private String btn;
        @SerializedName("BWP")
        @Expose
        private String bwp;
        @SerializedName("BYN")
        @Expose
        private String byn;
        @SerializedName("BYR")
        @Expose
        private String byr;
        @SerializedName("BZD")
        @Expose
        private String bzd;
        @SerializedName("CAD")
        @Expose
        private String cad;
        @SerializedName("CDF")
        @Expose
        private String cdf;
        @SerializedName("CHF")
        @Expose
        private String chf;
        @SerializedName("CLF")
        @Expose
        private String clf;
        @SerializedName("CLP")
        @Expose
        private String clp;
        @SerializedName("CNY")
        @Expose
        private String cny;
        @SerializedName("COP")
        @Expose
        private String cop;
        @SerializedName("CRC")
        @Expose
        private String crc;
        @SerializedName("CUC")
        @Expose
        private String cuc;
        @SerializedName("CUP")
        @Expose
        private String cup;
        @SerializedName("CVE")
        @Expose
        private String cve;
        @SerializedName("CZK")
        @Expose
        private String czk;
        @SerializedName("DJF")
        @Expose
        private String djf;
        @SerializedName("DKK")
        @Expose
        private String dkk;
        @SerializedName("DOP")
        @Expose
        private String dop;
        @SerializedName("DZD")
        @Expose
        private String dzd;
        @SerializedName("EGP")
        @Expose
        private String egp;
        @SerializedName("ERN")
        @Expose
        private String ern;
        @SerializedName("ETB")
        @Expose
        private String etb;
        @SerializedName("EUR")
        @Expose
        private String eur;
        @SerializedName("FJD")
        @Expose
        private String fjd;
        @SerializedName("FKP")
        @Expose
        private String fkp;
        @SerializedName("GBP")
        @Expose
        private String gbp;
        @SerializedName("GEL")
        @Expose
        private String gel;
        @SerializedName("GGP")
        @Expose
        private String ggp;
        @SerializedName("GHS")
        @Expose
        private String ghs;
        @SerializedName("GIP")
        @Expose
        private String gip;
        @SerializedName("GMD")
        @Expose
        private String gmd;
        @SerializedName("GNF")
        @Expose
        private String gnf;
        @SerializedName("GTQ")
        @Expose
        private String gtq;
        @SerializedName("GYD")
        @Expose
        private String gyd;
        @SerializedName("HKD")
        @Expose
        private String hkd;
        @SerializedName("HNL")
        @Expose
        private String hnl;
        @SerializedName("HRK")
        @Expose
        private String hrk;
        @SerializedName("HTG")
        @Expose
        private String htg;
        @SerializedName("HUF")
        @Expose
        private String huf;
        @SerializedName("IDR")
        @Expose
        private String idr;
        @SerializedName("ILS")
        @Expose
        private String ils;
        @SerializedName("IMP")
        @Expose
        private String imp;
        @SerializedName("INR")
        @Expose
        private String inr;
        @SerializedName("IQD")
        @Expose
        private String iqd;
        @SerializedName("IRR")
        @Expose
        private String irr;
        @SerializedName("ISK")
        @Expose
        private String isk;
        @SerializedName("JEP")
        @Expose
        private String jep;
        @SerializedName("JMD")
        @Expose
        private String jmd;
        @SerializedName("JOD")
        @Expose
        private String jod;
        @SerializedName("JPY")
        @Expose
        private String jpy;
        @SerializedName("KES")
        @Expose
        private String kes;
        @SerializedName("KGS")
        @Expose
        private String kgs;
        @SerializedName("KHR")
        @Expose
        private String khr;
        @SerializedName("KMF")
        @Expose
        private String kmf;
        @SerializedName("KPW")
        @Expose
        private String kpw;
        @SerializedName("KRW")
        @Expose
        private String krw;
        @SerializedName("KWD")
        @Expose
        private String kwd;
        @SerializedName("KYD")
        @Expose
        private String kyd;
        @SerializedName("KZT")
        @Expose
        private String kzt;
        @SerializedName("LAK")
        @Expose
        private String lak;
        @SerializedName("LBP")
        @Expose
        private String lbp;
        @SerializedName("LKR")
        @Expose
        private String lkr;
        @SerializedName("LRD")
        @Expose
        private String lrd;
        @SerializedName("LSL")
        @Expose
        private String lsl;
        @SerializedName("LTL")
        @Expose
        private String ltl;
        @SerializedName("LVL")
        @Expose
        private String lvl;
        @SerializedName("LYD")
        @Expose
        private String lyd;
        @SerializedName("MAD")
        @Expose
        private String mad;
        @SerializedName("MDL")
        @Expose
        private String mdl;
        @SerializedName("MGA")
        @Expose
        private String mga;
        @SerializedName("MKD")
        @Expose
        private String mkd;
        @SerializedName("MMK")
        @Expose
        private String mmk;
        @SerializedName("MNT")
        @Expose
        private String mnt;
        @SerializedName("MOP")
        @Expose
        private String mop;
        @SerializedName("MRO")
        @Expose
        private String mro;
        @SerializedName("MUR")
        @Expose
        private String mur;
        @SerializedName("MVR")
        @Expose
        private String mvr;
        @SerializedName("MWK")
        @Expose
        private String mwk;
        @SerializedName("MXN")
        @Expose
        private String mxn;
        @SerializedName("MYR")
        @Expose
        private String myr;
        @SerializedName("MZN")
        @Expose
        private String mzn;
        @SerializedName("NAD")
        @Expose
        private String nad;
        @SerializedName("NGN")
        @Expose
        private String ngn;
        @SerializedName("NIO")
        @Expose
        private String nio;
        @SerializedName("NOK")
        @Expose
        private String nok;
        @SerializedName("NPR")
        @Expose
        private String npr;
        @SerializedName("NZD")
        @Expose
        private String nzd;
        @SerializedName("OMR")
        @Expose
        private String omr;
        @SerializedName("PAB")
        @Expose
        private String pab;
        @SerializedName("PEN")
        @Expose
        private String pen;
        @SerializedName("PGK")
        @Expose
        private String pgk;
        @SerializedName("PHP")
        @Expose
        private String php;
        @SerializedName("PKR")
        @Expose
        private String pkr;
        @SerializedName("PLN")
        @Expose
        private String pln;
        @SerializedName("PYG")
        @Expose
        private String pyg;
        @SerializedName("QAR")
        @Expose
        private String qar;
        @SerializedName("RON")
        @Expose
        private String ron;
        @SerializedName("RSD")
        @Expose
        private String rsd;
        @SerializedName("RUB")
        @Expose
        private String rub;
        @SerializedName("RWF")
        @Expose
        private String rwf;
        @SerializedName("SAR")
        @Expose
        private String sar;
        @SerializedName("SBD")
        @Expose
        private String sbd;
        @SerializedName("SCR")
        @Expose
        private String scr;
        @SerializedName("SDG")
        @Expose
        private String sdg;
        @SerializedName("SEK")
        @Expose
        private String sek;
        @SerializedName("SGD")
        @Expose
        private String sgd;
        @SerializedName("SHP")
        @Expose
        private String shp;
        @SerializedName("SLE")
        @Expose
        private String sle;
        @SerializedName("SLL")
        @Expose
        private String sll;
        @SerializedName("SOS")
        @Expose
        private String sos;
        @SerializedName("SRD")
        @Expose
        private String srd;
        @SerializedName("STD")
        @Expose
        private String std;
        @SerializedName("SVC")
        @Expose
        private String svc;
        @SerializedName("SYP")
        @Expose
        private String syp;
        @SerializedName("SZL")
        @Expose
        private String szl;
        @SerializedName("THB")
        @Expose
        private String thb;
        @SerializedName("TJS")
        @Expose
        private String tjs;
        @SerializedName("TMT")
        @Expose
        private String tmt;
        @SerializedName("TND")
        @Expose
        private String tnd;
        @SerializedName("TOP")
        @Expose
        private String top;
        @SerializedName("TRY")
        @Expose
        private String _try;
        @SerializedName("TTD")
        @Expose
        private String ttd;
        @SerializedName("TWD")
        @Expose
        private String twd;
        @SerializedName("TZS")
        @Expose
        private String tzs;
        @SerializedName("UAH")
        @Expose
        private String uah;
        @SerializedName("UGX")
        @Expose
        private String ugx;
        @SerializedName("USD")
        @Expose
        private String usd;
        @SerializedName("UYU")
        @Expose
        private String uyu;
        @SerializedName("UZS")
        @Expose
        private String uzs;
        @SerializedName("VEF")
        @Expose
        private String vef;
        @SerializedName("VES")
        @Expose
        private String ves;
        @SerializedName("VND")
        @Expose
        private String vnd;
        @SerializedName("VUV")
        @Expose
        private String vuv;
        @SerializedName("WST")
        @Expose
        private String wst;
        @SerializedName("XAF")
        @Expose
        private String xaf;
        @SerializedName("XAG")
        @Expose
        private String xag;
        @SerializedName("XAU")
        @Expose
        private String xau;
        @SerializedName("XCD")
        @Expose
        private String xcd;
        @SerializedName("XDR")
        @Expose
        private String xdr;
        @SerializedName("XOF")
        @Expose
        private String xof;
        @SerializedName("XPF")
        @Expose
        private String xpf;
        @SerializedName("YER")
        @Expose
        private String yer;
        @SerializedName("ZAR")
        @Expose
        private String zar;
        @SerializedName("ZMK")
        @Expose
        private String zmk;
        @SerializedName("ZMW")
        @Expose
        private String zmw;
        @SerializedName("ZWL")
        @Expose
        private String zwl;

        public String getAed() {
            return aed;
        }

        public void setAed(String aed) {
            this.aed = aed;
        }

        public String getAfn() {
            return afn;
        }

        public void setAfn(String afn) {
            this.afn = afn;
        }

        public String getAll() {
            return all;
        }

        public void setAll(String all) {
            this.all = all;
        }

        public String getAmd() {
            return amd;
        }

        public void setAmd(String amd) {
            this.amd = amd;
        }

        public String getAng() {
            return ang;
        }

        public void setAng(String ang) {
            this.ang = ang;
        }

        public String getAoa() {
            return aoa;
        }

        public void setAoa(String aoa) {
            this.aoa = aoa;
        }

        public String getArs() {
            return ars;
        }

        public void setArs(String ars) {
            this.ars = ars;
        }

        public String getAud() {
            return aud;
        }

        public void setAud(String aud) {
            this.aud = aud;
        }

        public String getAwg() {
            return awg;
        }

        public void setAwg(String awg) {
            this.awg = awg;
        }

        public String getAzn() {
            return azn;
        }

        public void setAzn(String azn) {
            this.azn = azn;
        }

        public String getBam() {
            return bam;
        }

        public void setBam(String bam) {
            this.bam = bam;
        }

        public String getBbd() {
            return bbd;
        }

        public void setBbd(String bbd) {
            this.bbd = bbd;
        }

        public String getBdt() {
            return bdt;
        }

        public void setBdt(String bdt) {
            this.bdt = bdt;
        }

        public String getBgn() {
            return bgn;
        }

        public void setBgn(String bgn) {
            this.bgn = bgn;
        }

        public String getBhd() {
            return bhd;
        }

        public void setBhd(String bhd) {
            this.bhd = bhd;
        }

        public String getBif() {
            return bif;
        }

        public void setBif(String bif) {
            this.bif = bif;
        }

        public String getBmd() {
            return bmd;
        }

        public void setBmd(String bmd) {
            this.bmd = bmd;
        }

        public String getBnd() {
            return bnd;
        }

        public void setBnd(String bnd) {
            this.bnd = bnd;
        }

        public String getBob() {
            return bob;
        }

        public void setBob(String bob) {
            this.bob = bob;
        }

        public String getBrl() {
            return brl;
        }

        public void setBrl(String brl) {
            this.brl = brl;
        }

        public String getBsd() {
            return bsd;
        }

        public void setBsd(String bsd) {
            this.bsd = bsd;
        }

        public String getBtc() {
            return btc;
        }

        public void setBtc(String btc) {
            this.btc = btc;
        }

        public String getBtn() {
            return btn;
        }

        public void setBtn(String btn) {
            this.btn = btn;
        }

        public String getBwp() {
            return bwp;
        }

        public void setBwp(String bwp) {
            this.bwp = bwp;
        }

        public String getByn() {
            return byn;
        }

        public void setByn(String byn) {
            this.byn = byn;
        }

        public String getByr() {
            return byr;
        }

        public void setByr(String byr) {
            this.byr = byr;
        }

        public String getBzd() {
            return bzd;
        }

        public void setBzd(String bzd) {
            this.bzd = bzd;
        }

        public String getCad() {
            return cad;
        }

        public void setCad(String cad) {
            this.cad = cad;
        }

        public String getCdf() {
            return cdf;
        }

        public void setCdf(String cdf) {
            this.cdf = cdf;
        }

        public String getChf() {
            return chf;
        }

        public void setChf(String chf) {
            this.chf = chf;
        }

        public String getClf() {
            return clf;
        }

        public void setClf(String clf) {
            this.clf = clf;
        }

        public String getClp() {
            return clp;
        }

        public void setClp(String clp) {
            this.clp = clp;
        }

        public String getCny() {
            return cny;
        }

        public void setCny(String cny) {
            this.cny = cny;
        }

        public String getCop() {
            return cop;
        }

        public void setCop(String cop) {
            this.cop = cop;
        }

        public String getCrc() {
            return crc;
        }

        public void setCrc(String crc) {
            this.crc = crc;
        }

        public String getCuc() {
            return cuc;
        }

        public void setCuc(String cuc) {
            this.cuc = cuc;
        }

        public String getCup() {
            return cup;
        }

        public void setCup(String cup) {
            this.cup = cup;
        }

        public String getCve() {
            return cve;
        }

        public void setCve(String cve) {
            this.cve = cve;
        }

        public String getCzk() {
            return czk;
        }

        public void setCzk(String czk) {
            this.czk = czk;
        }

        public String getDjf() {
            return djf;
        }

        public void setDjf(String djf) {
            this.djf = djf;
        }

        public String getDkk() {
            return dkk;
        }

        public void setDkk(String dkk) {
            this.dkk = dkk;
        }

        public String getDop() {
            return dop;
        }

        public void setDop(String dop) {
            this.dop = dop;
        }

        public String getDzd() {
            return dzd;
        }

        public void setDzd(String dzd) {
            this.dzd = dzd;
        }

        public String getEgp() {
            return egp;
        }

        public void setEgp(String egp) {
            this.egp = egp;
        }

        public String getErn() {
            return ern;
        }

        public void setErn(String ern) {
            this.ern = ern;
        }

        public String getEtb() {
            return etb;
        }

        public void setEtb(String etb) {
            this.etb = etb;
        }

        public String getEur() {
            return eur;
        }

        public void setEur(String eur) {
            this.eur = eur;
        }

        public String getFjd() {
            return fjd;
        }

        public void setFjd(String fjd) {
            this.fjd = fjd;
        }

        public String getFkp() {
            return fkp;
        }

        public void setFkp(String fkp) {
            this.fkp = fkp;
        }

        public String getGbp() {
            return gbp;
        }

        public void setGbp(String gbp) {
            this.gbp = gbp;
        }

        public String getGel() {
            return gel;
        }

        public void setGel(String gel) {
            this.gel = gel;
        }

        public String getGgp() {
            return ggp;
        }

        public void setGgp(String ggp) {
            this.ggp = ggp;
        }

        public String getGhs() {
            return ghs;
        }

        public void setGhs(String ghs) {
            this.ghs = ghs;
        }

        public String getGip() {
            return gip;
        }

        public void setGip(String gip) {
            this.gip = gip;
        }

        public String getGmd() {
            return gmd;
        }

        public void setGmd(String gmd) {
            this.gmd = gmd;
        }

        public String getGnf() {
            return gnf;
        }

        public void setGnf(String gnf) {
            this.gnf = gnf;
        }

        public String getGtq() {
            return gtq;
        }

        public void setGtq(String gtq) {
            this.gtq = gtq;
        }

        public String getGyd() {
            return gyd;
        }

        public void setGyd(String gyd) {
            this.gyd = gyd;
        }

        public String getHkd() {
            return hkd;
        }

        public void setHkd(String hkd) {
            this.hkd = hkd;
        }

        public String getHnl() {
            return hnl;
        }

        public void setHnl(String hnl) {
            this.hnl = hnl;
        }

        public String getHrk() {
            return hrk;
        }

        public void setHrk(String hrk) {
            this.hrk = hrk;
        }

        public String getHtg() {
            return htg;
        }

        public void setHtg(String htg) {
            this.htg = htg;
        }

        public String getHuf() {
            return huf;
        }

        public void setHuf(String huf) {
            this.huf = huf;
        }

        public String getIdr() {
            return idr;
        }

        public void setIdr(String idr) {
            this.idr = idr;
        }

        public String getIls() {
            return ils;
        }

        public void setIls(String ils) {
            this.ils = ils;
        }

        public String getImp() {
            return imp;
        }

        public void setImp(String imp) {
            this.imp = imp;
        }

        public String getInr() {
            return inr;
        }

        public void setInr(String inr) {
            this.inr = inr;
        }

        public String getIqd() {
            return iqd;
        }

        public void setIqd(String iqd) {
            this.iqd = iqd;
        }

        public String getIrr() {
            return irr;
        }

        public void setIrr(String irr) {
            this.irr = irr;
        }

        public String getIsk() {
            return isk;
        }

        public void setIsk(String isk) {
            this.isk = isk;
        }

        public String getJep() {
            return jep;
        }

        public void setJep(String jep) {
            this.jep = jep;
        }

        public String getJmd() {
            return jmd;
        }

        public void setJmd(String jmd) {
            this.jmd = jmd;
        }

        public String getJod() {
            return jod;
        }

        public void setJod(String jod) {
            this.jod = jod;
        }

        public String getJpy() {
            return jpy;
        }

        public void setJpy(String jpy) {
            this.jpy = jpy;
        }

        public String getKes() {
            return kes;
        }

        public void setKes(String kes) {
            this.kes = kes;
        }

        public String getKgs() {
            return kgs;
        }

        public void setKgs(String kgs) {
            this.kgs = kgs;
        }

        public String getKhr() {
            return khr;
        }

        public void setKhr(String khr) {
            this.khr = khr;
        }

        public String getKmf() {
            return kmf;
        }

        public void setKmf(String kmf) {
            this.kmf = kmf;
        }

        public String getKpw() {
            return kpw;
        }

        public void setKpw(String kpw) {
            this.kpw = kpw;
        }

        public String getKrw() {
            return krw;
        }

        public void setKrw(String krw) {
            this.krw = krw;
        }

        public String getKwd() {
            return kwd;
        }

        public void setKwd(String kwd) {
            this.kwd = kwd;
        }

        public String getKyd() {
            return kyd;
        }

        public void setKyd(String kyd) {
            this.kyd = kyd;
        }

        public String getKzt() {
            return kzt;
        }

        public void setKzt(String kzt) {
            this.kzt = kzt;
        }

        public String getLak() {
            return lak;
        }

        public void setLak(String lak) {
            this.lak = lak;
        }

        public String getLbp() {
            return lbp;
        }

        public void setLbp(String lbp) {
            this.lbp = lbp;
        }

        public String getLkr() {
            return lkr;
        }

        public void setLkr(String lkr) {
            this.lkr = lkr;
        }

        public String getLrd() {
            return lrd;
        }

        public void setLrd(String lrd) {
            this.lrd = lrd;
        }

        public String getLsl() {
            return lsl;
        }

        public void setLsl(String lsl) {
            this.lsl = lsl;
        }

        public String getLtl() {
            return ltl;
        }

        public void setLtl(String ltl) {
            this.ltl = ltl;
        }

        public String getLvl() {
            return lvl;
        }

        public void setLvl(String lvl) {
            this.lvl = lvl;
        }

        public String getLyd() {
            return lyd;
        }

        public void setLyd(String lyd) {
            this.lyd = lyd;
        }

        public String getMad() {
            return mad;
        }

        public void setMad(String mad) {
            this.mad = mad;
        }

        public String getMdl() {
            return mdl;
        }

        public void setMdl(String mdl) {
            this.mdl = mdl;
        }

        public String getMga() {
            return mga;
        }

        public void setMga(String mga) {
            this.mga = mga;
        }

        public String getMkd() {
            return mkd;
        }

        public void setMkd(String mkd) {
            this.mkd = mkd;
        }

        public String getMmk() {
            return mmk;
        }

        public void setMmk(String mmk) {
            this.mmk = mmk;
        }

        public String getMnt() {
            return mnt;
        }

        public void setMnt(String mnt) {
            this.mnt = mnt;
        }

        public String getMop() {
            return mop;
        }

        public void setMop(String mop) {
            this.mop = mop;
        }

        public String getMro() {
            return mro;
        }

        public void setMro(String mro) {
            this.mro = mro;
        }

        public String getMur() {
            return mur;
        }

        public void setMur(String mur) {
            this.mur = mur;
        }

        public String getMvr() {
            return mvr;
        }

        public void setMvr(String mvr) {
            this.mvr = mvr;
        }

        public String getMwk() {
            return mwk;
        }

        public void setMwk(String mwk) {
            this.mwk = mwk;
        }

        public String getMxn() {
            return mxn;
        }

        public void setMxn(String mxn) {
            this.mxn = mxn;
        }

        public String getMyr() {
            return myr;
        }

        public void setMyr(String myr) {
            this.myr = myr;
        }

        public String getMzn() {
            return mzn;
        }

        public void setMzn(String mzn) {
            this.mzn = mzn;
        }

        public String getNad() {
            return nad;
        }

        public void setNad(String nad) {
            this.nad = nad;
        }

        public String getNgn() {
            return ngn;
        }

        public void setNgn(String ngn) {
            this.ngn = ngn;
        }

        public String getNio() {
            return nio;
        }

        public void setNio(String nio) {
            this.nio = nio;
        }

        public String getNok() {
            return nok;
        }

        public void setNok(String nok) {
            this.nok = nok;
        }

        public String getNpr() {
            return npr;
        }

        public void setNpr(String npr) {
            this.npr = npr;
        }

        public String getNzd() {
            return nzd;
        }

        public void setNzd(String nzd) {
            this.nzd = nzd;
        }

        public String getOmr() {
            return omr;
        }

        public void setOmr(String omr) {
            this.omr = omr;
        }

        public String getPab() {
            return pab;
        }

        public void setPab(String pab) {
            this.pab = pab;
        }

        public String getPen() {
            return pen;
        }

        public void setPen(String pen) {
            this.pen = pen;
        }

        public String getPgk() {
            return pgk;
        }

        public void setPgk(String pgk) {
            this.pgk = pgk;
        }

        public String getPhp() {
            return php;
        }

        public void setPhp(String php) {
            this.php = php;
        }

        public String getPkr() {
            return pkr;
        }

        public void setPkr(String pkr) {
            this.pkr = pkr;
        }

        public String getPln() {
            return pln;
        }

        public void setPln(String pln) {
            this.pln = pln;
        }

        public String getPyg() {
            return pyg;
        }

        public void setPyg(String pyg) {
            this.pyg = pyg;
        }

        public String getQar() {
            return qar;
        }

        public void setQar(String qar) {
            this.qar = qar;
        }

        public String getRon() {
            return ron;
        }

        public void setRon(String ron) {
            this.ron = ron;
        }

        public String getRsd() {
            return rsd;
        }

        public void setRsd(String rsd) {
            this.rsd = rsd;
        }

        public String getRub() {
            return rub;
        }

        public void setRub(String rub) {
            this.rub = rub;
        }

        public String getRwf() {
            return rwf;
        }

        public void setRwf(String rwf) {
            this.rwf = rwf;
        }

        public String getSar() {
            return sar;
        }

        public void setSar(String sar) {
            this.sar = sar;
        }

        public String getSbd() {
            return sbd;
        }

        public void setSbd(String sbd) {
            this.sbd = sbd;
        }

        public String getScr() {
            return scr;
        }

        public void setScr(String scr) {
            this.scr = scr;
        }

        public String getSdg() {
            return sdg;
        }

        public void setSdg(String sdg) {
            this.sdg = sdg;
        }

        public String getSek() {
            return sek;
        }

        public void setSek(String sek) {
            this.sek = sek;
        }

        public String getSgd() {
            return sgd;
        }

        public void setSgd(String sgd) {
            this.sgd = sgd;
        }

        public String getShp() {
            return shp;
        }

        public void setShp(String shp) {
            this.shp = shp;
        }

        public String getSle() {
            return sle;
        }

        public void setSle(String sle) {
            this.sle = sle;
        }

        public String getSll() {
            return sll;
        }

        public void setSll(String sll) {
            this.sll = sll;
        }

        public String getSos() {
            return sos;
        }

        public void setSos(String sos) {
            this.sos = sos;
        }

        public String getSrd() {
            return srd;
        }

        public void setSrd(String srd) {
            this.srd = srd;
        }

        public String getStd() {
            return std;
        }

        public void setStd(String std) {
            this.std = std;
        }

        public String getSvc() {
            return svc;
        }

        public void setSvc(String svc) {
            this.svc = svc;
        }

        public String getSyp() {
            return syp;
        }

        public void setSyp(String syp) {
            this.syp = syp;
        }

        public String getSzl() {
            return szl;
        }

        public void setSzl(String szl) {
            this.szl = szl;
        }

        public String getThb() {
            return thb;
        }

        public void setThb(String thb) {
            this.thb = thb;
        }

        public String getTjs() {
            return tjs;
        }

        public void setTjs(String tjs) {
            this.tjs = tjs;
        }

        public String getTmt() {
            return tmt;
        }

        public void setTmt(String tmt) {
            this.tmt = tmt;
        }

        public String getTnd() {
            return tnd;
        }

        public void setTnd(String tnd) {
            this.tnd = tnd;
        }

        public String getTop() {
            return top;
        }

        public void setTop(String top) {
            this.top = top;
        }

        public String getTry() {
            return _try;
        }

        public void setTry(String _try) {
            this._try = _try;
        }

        public String getTtd() {
            return ttd;
        }

        public void setTtd(String ttd) {
            this.ttd = ttd;
        }

        public String getTwd() {
            return twd;
        }

        public void setTwd(String twd) {
            this.twd = twd;
        }

        public String getTzs() {
            return tzs;
        }

        public void setTzs(String tzs) {
            this.tzs = tzs;
        }

        public String getUah() {
            return uah;
        }

        public void setUah(String uah) {
            this.uah = uah;
        }

        public String getUgx() {
            return ugx;
        }

        public void setUgx(String ugx) {
            this.ugx = ugx;
        }

        public String getUsd() {
            return usd;
        }

        public void setUsd(String usd) {
            this.usd = usd;
        }

        public String getUyu() {
            return uyu;
        }

        public void setUyu(String uyu) {
            this.uyu = uyu;
        }

        public String getUzs() {
            return uzs;
        }

        public void setUzs(String uzs) {
            this.uzs = uzs;
        }

        public String getVef() {
            return vef;
        }

        public void setVef(String vef) {
            this.vef = vef;
        }

        public String getVes() {
            return ves;
        }

        public void setVes(String ves) {
            this.ves = ves;
        }

        public String getVnd() {
            return vnd;
        }

        public void setVnd(String vnd) {
            this.vnd = vnd;
        }

        public String getVuv() {
            return vuv;
        }

        public void setVuv(String vuv) {
            this.vuv = vuv;
        }

        public String getWst() {
            return wst;
        }

        public void setWst(String wst) {
            this.wst = wst;
        }

        public String getXaf() {
            return xaf;
        }

        public void setXaf(String xaf) {
            this.xaf = xaf;
        }

        public String getXag() {
            return xag;
        }

        public void setXag(String xag) {
            this.xag = xag;
        }

        public String getXau() {
            return xau;
        }

        public void setXau(String xau) {
            this.xau = xau;
        }

        public String getXcd() {
            return xcd;
        }

        public void setXcd(String xcd) {
            this.xcd = xcd;
        }

        public String getXdr() {
            return xdr;
        }

        public void setXdr(String xdr) {
            this.xdr = xdr;
        }

        public String getXof() {
            return xof;
        }

        public void setXof(String xof) {
            this.xof = xof;
        }

        public String getXpf() {
            return xpf;
        }

        public void setXpf(String xpf) {
            this.xpf = xpf;
        }

        public String getYer() {
            return yer;
        }

        public void setYer(String yer) {
            this.yer = yer;
        }

        public String getZar() {
            return zar;
        }

        public void setZar(String zar) {
            this.zar = zar;
        }

        public String getZmk() {
            return zmk;
        }

        public void setZmk(String zmk) {
            this.zmk = zmk;
        }

        public String getZmw() {
            return zmw;
        }

        public void setZmw(String zmw) {
            this.zmw = zmw;
        }

        public String getZwl() {
            return zwl;
        }

        public void setZwl(String zwl) {
            this.zwl = zwl;
        }

    }

}
