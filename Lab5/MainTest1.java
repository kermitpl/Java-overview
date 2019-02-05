import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class MainTest1 {

    //Tests to solution of first positive that does not occur
    @Test
    public void solutionWith1Extreme() throws Exception{
        List<Integer> integerList = new LinkedList<>();
        integerList.add(1000000);

        Assert.assertEquals(1, Main.solution(integerList));
    }

    @Test
    public void solutionWithRandom() throws Exception{
        List<Integer> integerList = new LinkedList<>();

        int randomValue = 6;
        //int randomValue = -18;
        integerList.add(randomValue);

        Assert.assertEquals(1, Main.solution(integerList));
    }

    @Test
    public void solutionWithPositives() throws Exception{
        List<Integer> integerList = new LinkedList<>();
        integerList.add(3);
        integerList.add(132);
        integerList.add(1123);
        integerList.add(1);
        integerList.add(32);
        integerList.add(2);
        integerList.add(4);
        integerList.add(56);
        integerList.add(8);
        integerList.add(45);
        integerList.add(24);
        integerList.add(24332);
        integerList.add(234);

        Assert.assertEquals(5, Main.solution(integerList));
    }

    @Test
    public void solutionWithNegatives() throws Exception{
        List<Integer> integerList = new LinkedList<>();
        integerList.add(-3);
        integerList.add(-132);
        integerList.add(-1123);
        integerList.add(-1);
        integerList.add(-32);
        integerList.add(-2);
        integerList.add(-4);
        integerList.add(-56);
        integerList.add(-8);
        integerList.add(-45);
        integerList.add(-24);
        integerList.add(-24332);
        integerList.add(-234);

        Assert.assertEquals(1, Main.solution(integerList));
    }

    @Test
    public void solutionWithPositivesNegativesDuplicates() throws Exception{
        List<Integer> integerList = new LinkedList<>();
        integerList.add(3);
        integerList.add(-132);
        integerList.add(-1123);
        integerList.add(-132);
        integerList.add(-1123);
        integerList.add(1);
        integerList.add(1);
        integerList.add(1);
        integerList.add(-32);
        integerList.add(-2);
        integerList.add(4);
        integerList.add(-56);
        integerList.add(8);
        integerList.add(-45);
        integerList.add(-24);
        integerList.add(8);
        integerList.add(8);
        integerList.add(24332);
        integerList.add(-234);

        Assert.assertEquals(2, Main.solution(integerList));
    }

    @Test
    public void solutionWithSomethingWith10Zeroes() throws Exception{
        List<Integer> integerList = new LinkedList<>();
        integerList.add(0);
        integerList.add(0);
        integerList.add(0);
        integerList.add(3);
        integerList.add(-132);
        integerList.add(0);
        integerList.add(0);
        integerList.add(0);
        integerList.add(0);
        integerList.add(0);
        integerList.add(-1123);
        integerList.add(2);
        integerList.add(-132);
        integerList.add(0);
        integerList.add(-1123);
        integerList.add(1);
        integerList.add(0);

        Assert.assertEquals(4, Main.solution(integerList));
    }

    @Test
    public void testSolution1to100000() throws Exception{
        List<Integer> integerList = new LinkedList<>();
        for (int i=1; i<=100000; i++) integerList.add(i);

        Assert.assertEquals(100001, Main.solution(integerList));
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionWhenEmptyList() throws Exception{
        List<Integer> integerList = new LinkedList<>();
        Main.solution(integerList);
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionWhenUninitialized() throws Exception{
        List<Integer> integerList = null;
        Main.solution(integerList);
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionWhenListExceedsMax() throws Exception{
        List<Integer> integerList = new LinkedList<>();
        for (int i=1; i<=100004; i++) integerList.add(i);
        Main.solution(integerList);
    }

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
