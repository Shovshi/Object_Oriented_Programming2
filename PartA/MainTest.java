package PartA;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * in this class we use different functions to test various parts of our code
 * BEFORE RUNNING A NEW TEST YOU MUST DELETE THE TEXT FILES CREATED
 * FROM THE PREVIOUS ONE
 */
class MainTest
{
    @Test
    public void testLines()
    {
        String names[]= Ex2_1.createTextFiles(100,10,50000);
        int linesRegular = Ex2_1.getNumOfLines(names);
        int linesFromThreads = Ex2_1.getNumOfLinesThreads(names);
        int linesFromThreadPool = Ex2_1.getNumOfLinesThreadPool(names);

        Assert.assertEquals(linesRegular,linesFromThreadPool,linesFromThreads);




    }
}