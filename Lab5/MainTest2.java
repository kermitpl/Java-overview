import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class MainTest2 {
    //Test to solution of digits sum
    @Test
    public void solutionSumOfDigitsWithZerog() throws Exception{
        int [] a = new int [1];
        a[0] = 0;
        Assert.assertEquals(0, Main.solutionSumOfDigits(a));
    }

    @Test
    public void solutionSumOfDigitsWithZeroAtBegging() throws Exception{
        int [] a = new int [5];
        a[0] = 3;
        a[1] = 5;
        a[2] = 1;
        a[3] = 6;
        a[4] = 0;
        Assert.assertEquals(12, Main.solutionSumOfDigits(a));
    }

    @Test
    public void solutionSumOfDigitsWithNegativeDigits() throws Exception{
        int [] a = new int [5];
        a[0] = -3;
        a[1] = 5;
        a[2] = 1;
        a[3] = -6;
        a[4] = 7;
        Assert.assertEquals(23, Main.solutionSumOfDigits(a));
    }


    @Test(expected = Exception.class)
    public void solutionSumOfDigitsWithUninitializedArray() throws Exception{
        int [] a = null;
        Main.solutionSumOfDigits(a);
    }

    @Test
    public void solutionSumOfDigitsWithSomeDigitsProvided() throws Exception{
        int [] a = new int [5];
        a[1] = 3;
        a[3] = 2;
        //Actual number would be 02030, so sum of digits 17*2030 should be 13
        Assert.assertEquals(13, Main.solutionSumOfDigits(a));
    }

    @Test(expected = Exception.class)
    public void solutionSumOfDigitsWithElementGreaterThan9() throws Exception{
        int [] a = new int [3];
        a[0] = 23;
        a[1] = 1;
        a[2] = 2;
        Main.solutionSumOfDigits(a);
    }

    @Test
    public void solutionSumOfDigitsWithDigitProvidedAsDivision() throws Exception{
        int [] a = new int [1];
        a[0] = 9/4;
        Assert.assertEquals(7, Main.solutionSumOfDigits(a));
    }

    @Test(expected = Exception.class)
    public void solutionSumOfDigitsWithTableExceedingMaxLength() throws Exception{
        int [] a = new int [9];
        a[8] = 3;
        //Actual number calculated from this table would overflow int
        Main.solutionSumOfDigits(a);
    }

}
