import org.junit.runner.JUnitCore;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {

    public static int solution(List<Integer> a) throws Exception
    {
        if (a == null) throw new Exception("List was null/uninitialized.");
        if (a.size()==0) throw new Exception("List was empty.");
        if (a.size()>100000) throw new Exception("List size exceeds maximum.");

        SortedSet<Integer> positives = new TreeSet<>();

        for (Integer element : a) {
            if (element > 0) positives.add(element);
        }

        if (positives.size() == 0 || positives.first() > 1) return 1;
        if (positives.size() == positives.last()) return positives.last()+1;

        int iterator=1;

        for (; iterator <= 1000000; iterator++)
        {
            if (!positives.contains(iterator)) break;
        }

        return iterator;
    }

    public static int solutionSumOfDigits(int [] a) throws  Exception{
        if (a == null) throw new Exception("Table was null/uninitialized.");
        if (a.length==0) throw new Exception("Table was empty.");
        if (a.length > 8) throw new Exception("Table length exceeds max.");

        int actualNumber = 0, var=1;
        for (int element : a) {
            if (Math.abs(element) > 9) throw new Exception("One of table's element wasn't a digit.");
            actualNumber += Math.abs(element) * var;
            var *= 10;
        }
        actualNumber *= 17;

        int sum = 0;
        while (actualNumber > 0) {
            sum += actualNumber % 10;
            actualNumber /= 10;
        }

        return sum;
    }

    public static void main(String[] args) {
        JUnitCore.runClasses(TestSuite.class);

    }
}
