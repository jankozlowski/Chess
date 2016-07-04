public final class Complex implements Cloneable{
    private final double re;
    private final double im;
    
    public Complex(){this(0,0);}
    public Complex(double re){this(re,0);}
    public Complex(double re,double im){this.re=re; this.im=im;}
    
    public double real() {return re;}
    public double imag() {return im;}
    
    public Complex add(Complex z) {return new Complex(re+z.re, im+z.im);}
    public Complex sub(Complex z) {return new Complex(re-z.re, im-z.im);}
    public Complex mul(Complex z) {return new Complex(re*z.re-im*z.im, im*z.re+re*z.im);}
  /*  public Complex div(Complex z) {return new Complex(re/z.re, im/z.im);}
    public Complex sqrt(){}
    public double norm(){return double(real()*real(),imag()*imag());}
    public double abs(){return double(sqrt(re*re,im*im));}*/
    public Complex conj(){return new Complex(re,-im);}
    public Complex neg(){return new Complex(-re,-im);}
    
    
    public boolean equals(Object o){
        if( !(o instanceof Complex)) return false;
        Complex c=(Complex)o;
        return (re==c.re && im==c.im);
    }
    
    public String toString(){
        StringBuilder napis=new StringBuilder( Double.toString(re));
        napis.append("+i*(").append(im).append(')');
        return napis.toString();
    }
    
    public int hashcode(){
        long hasz=Double.doubleToLongBits(re);
        hasz += Double.doubleToLongBits(im);
        return (int)(hasz^(hasz>>>32));
    }
    
    public Complex clone(){return this;}
    
    
    
    public static void main(String[] args) {
        
        Complex one = new Complex(3,-2);
        Complex two = new Complex(4,3);
        Complex tree = one.add(two);
        System.out.println(tree);
    }
    
    
}