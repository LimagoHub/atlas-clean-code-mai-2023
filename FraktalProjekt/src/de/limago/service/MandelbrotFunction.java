package de.limago.service;

import de.limago.math.Complex;

public class MandelbrotFunction implements ComplexToIntFunction {

    private static final int MAXITER = 255;
    @Override
    public int apply(final Complex complex) {
        int retval = 0;
        Complex z = new Complex();
        while(z.abs() < 2 ) {
            z = z.times(z);
            z = z.plus(complex);
            retval ++;
            if (retval > MAXITER) return 0;
        }
        return retval;
    }
}
