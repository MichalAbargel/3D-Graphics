package primitives;


import javax.print.DocFlavor;

/**
 * @author Salay Shalom Shuker 311600605 salayshuker@gmail.com
 * @author Shimon Mizrahi 203375563 shimonshimi4@gmail.com
 */


public class Material {

    /**
     * @param _kD         Coefficient of diffuse
     * @param _kS         Coefficient of specular
     * @param _nShininess The exponent - > shininess  of the material     *
     */


    public double _kD;
    public double _kS;
    public int _nShininess ;
    public double kT;
    public double kR;

    public double getkT() {
        return kT;
    }

    /**
     * C-tor - default values 0
     */
    public Material() {
        _kD = 0;
        _kS = 0;
        _nShininess = 0;
        kT=0;
        kR=0;
    }

    public Material setkT(double kT) {
        this.kT = kT;
        return this;
    }

    public Material setkR(double kR) {
        this.kR = kR;
        return this;
    }

    /**
     * Ctor Material. Note: kS+kD<=1. The variables kR & kT get a default value.
     *TODO - delete this notes
     * /




     /**
     * set of kD
     * @param kD
     * @return this Material
     */
    public Material setKd(double kD) {
        _kD = kD;
        return this;
    }

    /**
     * set of kS
     * @param kS
     * @return this Material
     */
    public Material setKs(double kS) {
        _kS = kS;
        return this;
    }

    /**
     * set of nShininess
     * @param nShininess
     * @return this Material
     */
    public Material setnShininess(int nShininess) {
        _nShininess = nShininess;
        return this;
    }
}
