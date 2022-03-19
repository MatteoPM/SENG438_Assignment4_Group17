package org.jfree.data;

import static org.junit.Assert.*; import org.jfree.data.Range;
import org.jmock.Mockery;
import org.junit.*;

public class RangeTest2 {
    static private Range exampleRange;
    @BeforeClass public static void setUpBeforeClass() throws Exception { 
        exampleRange = new Range(-5, 5);
    }


//    @Before
//    public void setUp() throws Exception {}
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor() {
        Range temp = new Range(10,-10);
    }
  
    @Test
    public void testContains_lowerBound() {
        assertEquals("The lowerBound of -5 and 5 is -5 and contains(-5) should return true",
        true, exampleRange.contains(-5));
    }
    
    @Test
    public void testContains_upperBound() {
        assertEquals("The upperBound of -5 and 5 is 5 and contains(5) should return true",
        true, exampleRange.contains(5));
    }
    
    @Test
    public void testContains_inBetween() {
        assertEquals("In the Range of -5 and 5 is contains(2) should return true",
        true, exampleRange.contains(2));
    }
    
    @Test
    public void testContains_belowLowerBound() {
        assertEquals("In the Range of -5 and 5 is contains(-7) should return false",
        false, exampleRange.contains(-7));
    }
        
    @Test
    public void testContains_belowUpperBound() {
        assertEquals("In the Range of -5 and 5 is contains(7) should return false",
        false, exampleRange.contains(7));
    }
   
    
    @Test
    public void testGetLowerBound_lowerbound() {
        Range instance = new Range(4,10);
        assertEquals("The lowerBound of 4 and 10 is 4 and getLowerBound() should return 4",
                4, instance.getLowerBound(), .000000001d);
    }
   
    
    @Test
    public void testGetLowerBound_lowerboundNeg() {
        Range instance = new Range(-4,-2);
        assertEquals("The lowerBound of -4 and -2 is -4 and getLowerBound() should return -4",
                -4, instance.getLowerBound(), .000000001d);
    }
   
    
    @Test
    public void testGetUpperBound_upperbound() {
        Range instance = new Range(4,10);
        assertEquals("The lowerBound of 4 and 10 is 10 and getUpperBound() should return 10",
                10, instance.getUpperBound(), .000000001d);
    }
   
    
    @Test
    public void testGetUpperBound_upperboundNeg() {
        Range instance = new Range(-4,-2);
        assertEquals("The lowerBound of -4 and -2 is -2 and getUpperBound() should return -2",
                -2, instance.getUpperBound(), .000000001d);
    }
   
    
    @Test
    public void testGetLength_SameUpperLower() {
        Range instance = new Range(5,5);
        assertEquals("The Length should be 0",
                0, instance.getLength(), .000000001d);
    }
   
    
    @Test
    public void testGetLength_NegUpperLower() {
        Range instance = new Range(-5,-2);
        assertEquals("The Length should be 3",
                3, instance.getLength(), .000000001d);
    }
   
    
    @Test
    public void testGetLength_posUpperLower() {
        Range instance = new Range(2,10);
        assertEquals("The Length should be 8",
                8, instance.getLength(), .000000001d);
    }
   
    
    @Test
    public void testToString() {
        Range instance = new Range(3,10);
        assertEquals("Range[3.0,10.0]", instance.toString());
    }    
    
    
    @Test
    public void testgetCentralValue() {
        Range instance = new Range(-10,10);
        assertEquals("The Centre should be 0",
                0, instance.getCentralValue(), .000000001d);
    }    
    
    
    
    
    //public boolean intersects(double b0, double b1)
    // making b1 > lower bound(-10) puts us in the else
    @Test
    public void testIntersects_lower() {
        Range instance = new Range(-10,10);
        assertTrue("The intersects should be True",
                instance.intersects(-15,2));
    }  
    
    @Test
    public void testIntersects_lowerout() {
        Range instance = new Range(-10,10);
        assertFalse("The intersects should be false",
                instance.intersects(-15,-12));
    }
    @Test
    public void testIntersects_nonRealrange() {
        Range instance = new Range(-10,10);
        assertFalse("The intersects should be false",
                instance.intersects(15,2));
    } 
    @Test
    public void testIntersects_aboveout() {
        Range instance = new Range(-10,10);
        assertFalse("The intersects should be false",
                instance.intersects(15,20));
    } 
    @Test
    public void testIntersects_nonRealrange2() {
        Range instance = new Range(-10,10);
        assertFalse("The intersects should be false",
                instance.intersects(5,2));
    }  
    @Test
    public void testIntersects_upperinRange() {
        Range instance = new Range(-10,10);
        assertTrue("The intersects should be True",
                instance.intersects(5,20));
    }   
    
    //public boolean intersects(Range range)
    @Test
    public void testIntersects_withRange() {
        Range instance = new Range(-10,10);
        Range instance2 = new Range(-5,5);
        assertTrue("The intersects should be true",
                instance.intersects(instance2));
    }     
    
    //public double constrain(double value)
    @Test
    public void testConstrain_inRange() {
        Range instance = new Range(-10,10);
        assertEquals(5, instance.constrain(5), .000000001d);
    }       
    @Test
    public void testConstrain_above() {
        Range instance = new Range(-10,10);
        assertEquals(10, instance.constrain(50), .000000001d);
    }       
    @Test
    public void testConstrain_below() {
        Range instance = new Range(-10,10);
        assertEquals(-10, instance.constrain(-50), .000000001d);
    }     
    //public static Range combine(Range range1, Range range2)
    @Test
    public void testCombine_bothValid() {
        Range instance = new Range(-10,5);
        Range instance2 = new Range(-5,10);
       Range instance3 = Range.combine(instance,instance2);
       Range temp = new Range(-10,10);
        assertEquals(temp,instance3);
    }  
    @Test
    public void testCombine_oneNull() {
        Range instance = null;
        Range instance2 = new Range(-5,10);
       Range instance3 = Range.combine(instance,instance2);
        assertEquals(instance2, instance3);
    }  
    @Test
    public void testCombine_secondNull() {
        Range instance = new Range(-10,5);
        Range instance2 = null;
       Range instance3 = Range.combine(instance,instance2);
        assertEquals(instance, instance3);
    }       
    //    public static Range combineIgnoringNaN(Range range1, Range range2)
    @Test
    public void testCombineIgnoringNaN_bothValid() {
        Range instance = new Range(-10,5);
        Range instance2 = new Range(-5,10);
       Range instance3 = Range.combineIgnoringNaN(instance,instance2);
       Range temp = new Range(-10,10);
        assertEquals(temp,instance3);
    }  
    @Test
    public void testCombineIgnoringNaN_oneNull() {
        Range instance = null;
        Range instance2 = new Range(-5,10);
       Range instance3 = Range.combineIgnoringNaN(instance,instance2);
        assertEquals(instance2, instance3);
    }  
    @Test
    public void testCombineIgnoringNaN_secondNull() {
        Range instance = new Range(-10,5);
        Range instance2 = null;
       Range instance3 = Range.combineIgnoringNaN(instance,instance2);
        assertEquals(instance, instance3);
    }  
    
    @Test
    public void testCombineIgnoringNaN_oneNANsecondNull() {
        Range instance = new Range(Math.sqrt(-1),Math.sqrt(-1));
        Range instance2 = null;
       Range instance3 = Range.combineIgnoringNaN(instance,instance2);
        assertEquals(instance2, instance3);
    }  
    
    @Test
    public void testCombineIgnoringNaN_oneNullsecondNAN() {
        Range instance = null;
        Range instance2 = new Range(Math.sqrt(-1),Math.sqrt(-1));
       Range instance3 = Range.combineIgnoringNaN(instance,instance2);
        assertEquals(instance, instance3);
    }  
    
    @Test
    public void testCombineIgnoringNaN_bothNull() {
        Range instance = null;
        Range instance2 = null;
       Range instance3 = Range.combineIgnoringNaN(instance,instance2);
        assertEquals(instance, instance3);
    }  
    
    @Test
    public void testCombineIgnoringNaN_bothNAN() {
        Range instance = new Range(Math.sqrt(-1),Math.sqrt(-1));
        Range instance2 = new Range(Math.sqrt(-1),Math.sqrt(-1));
       Range instance3 = Range.combineIgnoringNaN(instance,instance2);
        assertEquals(null, instance3);
    } 
    @Test
    public void testCombineIgnoringNaN_oneValidSecondNan() {
        Range instance = new Range(-10,5);
        Range instance2 =  new Range(Math.sqrt(-1),Math.sqrt(-1));;
       Range instance3 = Range.combineIgnoringNaN(instance,instance2);
        assertEquals(instance, instance3);
    }     
    //public static Range expandToInclude(Range range, double value) 
    @Test
    public void testExpandToInclude_upper() {
        Range instance = new Range(-10,5);
       Range instance3 = Range.expandToInclude(instance,10);
       Range temp = new Range(-10,10);
        assertEquals(temp,instance3);
    }    
    @Test
    public void testExpandToInclude_lower() {
        Range instance = new Range(-5,10);
       Range instance3 = Range.expandToInclude(instance,-10);
       Range temp = new Range(-10,10);
        assertEquals(temp,instance3);
    } 
    @Test
    public void testExpandToInclude_Null() {
        Range instance = null;
       Range instance3 = Range.expandToInclude(instance,10);
       Range temp = new Range(10,10);
        assertEquals(temp, instance3);
    }  
    @Test
    public void testExpandToInclude_Middle() {
        Range instance = new Range(-10,10);
       Range instance3 = Range.expandToInclude(instance,0);
       Range temp = new Range(-10,10);
        assertEquals(temp, instance3);
    }       
    //public static Range expand(Range range,double lowerMargin, double upperMargin) 
    @Test
   public void testExpand_validRange() {
       Range instance = new Range(-10,10);
      Range instance3 = Range.expand(instance,-5,1);
      Range temp = new Range(60,60);
       assertEquals(temp,instance3);
   }          
    //public static Range shift(Range base, double delta,boolean allowZeroCrossing) 
        @Test
   public void testShift_leftWithZeroCrossing() {
       Range instance = new Range(-10,10);
      Range instance3 = Range.shift(instance,-5,true);
      Range temp = new Range(-15,5);
       assertEquals(temp,instance3);
   }         
        @Test
   public void testShift_leftWithoutZeroCrossing() {
       Range instance = new Range(-10,10);
      Range instance3 = Range.shift(instance,-5,false);
      Range temp = new Range(-15,5);
       assertEquals(temp,instance3);
   }         
        @Test
   public void testShift_leftWithZeroRange() {
       Range instance = new Range(0,0);
      Range instance3 = Range.shift(instance,-5,false);
      Range temp = new Range(-5,-5);
       assertEquals(temp,instance3);
   }          

   //    public static Range scale(Range base, double factor) {
        @Test
   public void testScale_PositiveScale() {
       Range instance = new Range(-10,10);
      Range instance3 = Range.scale(instance,5);
      Range temp = new Range(-50,50);
       assertEquals(temp,instance3);
   }  
        
        
        
        
        
        // ADDED FOR ASSIGNMENT 4
        // THE TESTS BELOW CHECK UPPER AND LOWER BOUNDS AFTER A FUNCTOIN IS CALLED.    
  
    
    
    
    
    @Test
    public void testCheckUpper_Contains_lowerBound() {
        exampleRange.contains(-5);        
        assertEquals(5, exampleRange.getUpperBound(), .000000001d);
    }
    
    @Test
    public void testCheckUpper_Contains_upperBound() {
       exampleRange.contains(5);        
       assertEquals(5, exampleRange.getUpperBound(), .000000001d);
    }
    
    @Test
    public void testCheckUpper_Contains_inBetween() {
    	exampleRange.contains(2);        
        assertEquals(5, exampleRange.getUpperBound(), .000000001d);
    }
    
    @Test
    public void testCheckUpper_Contains_belowLowerBound() {
       exampleRange.contains(-7);        
       assertEquals(5, exampleRange.getUpperBound(), .000000001d);
    }
        
    @Test
    public void testCheckUpper_Contains_belowUpperBound() {
        exampleRange.contains(7);        
        assertEquals(5, exampleRange.getUpperBound(), .000000001d);
    }
   
    
    @Test
    public void testCheckUpper_GetLowerBound_lowerbound() {
        Range instance = new Range(4,10);
        instance.getLowerBound();        
        assertEquals(10, instance.getUpperBound(), .000000001d);
    }
   
    
    @Test
    public void testCheckUpper_GetLowerBound_lowerboundNeg() {
        Range instance = new Range(-4,-2);
        instance.getLowerBound();        
        assertEquals(-2, instance.getUpperBound(), .000000001d);
    }
   
    
    @Test
    public void testCheckUpper_GetUpperBound_upperbound() {
        Range instance = new Range(4,10);
        instance.getUpperBound();        
        assertEquals(10, instance.getUpperBound(), .000000001d);
    }
   
    
    @Test
    public void testCheckUpper_GetUpperBound_upperboundNeg() {
        Range instance = new Range(-4,-2);
        instance.getUpperBound();        
        assertEquals(-2, instance.getUpperBound(), .000000001d);
    }
   
    
    @Test
    public void testCheckUpper_GetLength_SameUpperLower() {
        Range instance = new Range(5,5);
        instance.getLength();        
        assertEquals(5, instance.getUpperBound(), .000000001d);
    }
   
    
    @Test
    public void testCheckUpper_GetLength_NegUpperLower() {
        Range instance = new Range(-5,-2);
       instance.getLength();        
       assertEquals(-2, instance.getUpperBound(), .000000001d);
    }
   
    
    @Test
    public void testCheckUpper_GetLength_posUpperLower() {
        Range instance = new Range(2,10);
        instance.getLength();        
        assertEquals(10, instance.getUpperBound(), .000000001d);
    }
   
    
    @Test
    public void testCheckUpper_ToString() {
        Range instance = new Range(3,10);
         instance.toString();        
         assertEquals(10, instance.getUpperBound(), .000000001d);
    }    
    
    
    @Test
    public void testCheckUpper_getCentralValue() {
        Range instance = new Range(-10,10);
        instance.getCentralValue();        
        assertEquals(10, instance.getUpperBound(), .000000001d);
    }    
    
    
    
    
    //public boolean intersects(double b0, double b1)
    // making b1 > lower bound(-10) puts us in the else
    @Test
    public void testCheckUpper_Intersects_lower() {
        Range instance = new Range(-10,10);
        instance.intersects(-15,2);        
        assertEquals(10, instance.getUpperBound(), .000000001d);
    }  
    
    @Test
    public void testCheckUpper_Intersects_lowerout() {
        Range instance = new Range(-10,10);
        instance.intersects(-15,-12);        
        assertEquals(10, instance.getUpperBound(), .000000001d);
    }
    @Test
    public void testCheckUpper_Intersects_nonRealrange() {
        Range instance = new Range(-10,10);
        instance.intersects(15,2);        
        assertEquals(10, instance.getUpperBound(), .000000001d);
    } 
    @Test
    public void testCheckUpper_Intersects_aboveout() {
        Range instance = new Range(-10,10);
        instance.intersects(15,20);        
        assertEquals(10, instance.getUpperBound(), .000000001d);
    } 
    @Test
    public void testCheckUpper_Intersects_nonRealrange2() {
        Range instance = new Range(-10,10);
        instance.intersects(5,2);        
        assertEquals(10, instance.getUpperBound(), .000000001d);
    }  
    @Test
    public void testCheckUpper_Intersects_upperinRange() {
        Range instance = new Range(-10,10);
        instance.intersects(5,20);        
        assertEquals(10, instance.getUpperBound(), .000000001d);
    }   
    
    //public boolean intersects(Range range)
    @Test
    public void testCheckUpperinstance1_Intersects_withRange() {
        Range instance = new Range(-10,10);
        Range instance2 = new Range(-5,5);
        instance.intersects(instance2);        
        assertEquals(10, instance.getUpperBound(), .000000001d);
    }
    @Test
    public void testCheckUpperinstance2_Intersects_withRange() {
        Range instance = new Range(-10,10);
        Range instance2 = new Range(-5,5);
        instance.intersects(instance2);        
        assertEquals(5, instance2.getUpperBound(), .000000001d);
    }     
    
    //public double constrain(double value)
    @Test
    public void testCheckUpper_Constrain_inRange() {
        Range instance = new Range(-10,10);
        instance.constrain(5);        
        assertEquals(10, instance.getUpperBound(), .000000001d);
    }       
    @Test
    public void testCheckUpper_Constrain_above() {
        Range instance = new Range(-10,10);
        instance.constrain(50);        
        assertEquals(10, instance.getUpperBound(), .000000001d);
    }       
    @Test
    public void testCheckUpper_Constrain_below() {
        Range instance = new Range(-10,10);
        instance.constrain(-50);        
        assertEquals(10, instance.getUpperBound(), .000000001d);
    }     
    //public static Range combine(Range range1, Range range2)
    @Test
    public void testCheckUpperinstance1_Combine_bothValid() {
        Range instance = new Range(-10,5);
        Range instance2 = new Range(-5,10);
        Range.combine(instance,instance2);      
        assertEquals(5, instance.getUpperBound(), .000000001d);
    } 
    @Test
    public void testCheckUpperinstance2_Combine_bothValid() {
        Range instance = new Range(-10,5);
        Range instance2 = new Range(-5,10);
       Range.combine(instance,instance2);     
        assertEquals(10, instance2.getUpperBound(), .000000001d);
    }  
    @Test
    public void testCheckUpper_Combine_oneNull() {
        Range instance = null;
        Range instance2 = new Range(-5,10);
       Range.combine(instance,instance2);        
       assertEquals(10, instance2.getUpperBound(), .000000001d);
    }  
    @Test
    public void testCheckUpper_Combine_secondNull() {
        Range instance = new Range(-10,5);
        Range instance2 = null;
       Range.combine(instance,instance2);        
       assertEquals(5, instance.getUpperBound(), .000000001d);
    }       
    //    public static Range combineIgnoringNaN(Range range1, Range range2)
    @Test
    public void testCheckUpperinstance1_CombineIgnoringNaN_bothValid() {
        Range instance = new Range(-10,5);
        Range instance2 = new Range(-5,10);
       Range.combineIgnoringNaN(instance,instance2);        
       assertEquals(5, instance.getUpperBound(), .000000001d);
    }
    @Test
    public void testCheckUpperinstance2_CombineIgnoringNaN_bothValid() {
        Range instance = new Range(-10,5);
        Range instance2 = new Range(-5,10);
       Range.combineIgnoringNaN(instance,instance2);        
       assertEquals(10, instance2.getUpperBound(), .000000001d);
    }  
    @Test
    public void testCheckUpper_CombineIgnoringNaN_oneNull() {
        Range instance = null;
        Range instance2 = new Range(-5,10);
       Range.combineIgnoringNaN(instance,instance2);        
       assertEquals(10, instance2.getUpperBound(), .000000001d);
    }  
    @Test
    public void testCheckUpper_CombineIgnoringNaN_secondNull() {
        Range instance = new Range(-10,5);
        Range instance2 = null;
       Range.combineIgnoringNaN(instance,instance2);        
       assertEquals(5, instance.getUpperBound(), .000000001d);
    }   
      
    @Test
    public void testCheckUpper_CombineIgnoringNaN_oneValidSecondNan() {
        Range instance = new Range(-10,5);
        Range instance2 =  new Range(Math.sqrt(-1),Math.sqrt(-1));;
       Range.combineIgnoringNaN(instance,instance2);        
       assertEquals(5, instance.getUpperBound(), .000000001d);
    }     
    //public static Range expandToInclude(Range range, double value) 
    @Test
    public void testCheckUpper_ExpandToInclude_upper() {
        Range instance = new Range(-10,5);
       Range.expandToInclude(instance,10);        
       assertEquals(5, instance.getUpperBound(), .000000001d);
    }    
    @Test
    public void testCheckUpper_ExpandToInclude_lower() {
        Range instance = new Range(-5,10);
       Range.expandToInclude(instance,-10);        
       assertEquals(10, instance.getUpperBound(), .000000001d);
    }  
    @Test
    public void testCheckUpper_ExpandToInclude_Middle() {
        Range instance = new Range(-10,10);
       Range.expandToInclude(instance,0);        
       assertEquals(10, instance.getUpperBound(), .000000001d);
    }       
    //public static Range expand(Range range,double lowerMargin, double upperMargin) 
    @Test
   public void testCheckUpper_Expand_validRange() {
       Range instance = new Range(-10,10);
      Range.expand(instance,-5,1);        
      assertEquals(10, instance.getUpperBound(), .000000001d);
   }          
    //public static Range shift(Range base, double delta,boolean allowZeroCrossing) 
        @Test
   public void testCheckUpper_Shift_leftWithZeroCrossing() {
       Range instance = new Range(-10,10);
      Range.shift(instance,-5,true);        
      assertEquals(10, instance.getUpperBound(), .000000001d);
   }         
        @Test
   public void testCheckUpper_Shift_leftWithoutZeroCrossing() {
       Range instance = new Range(-10,10);
      Range.shift(instance,-5,false);        
      assertEquals(10, instance.getUpperBound(), .000000001d);
   }         
        @Test
   public void testCheckUpper_Shift_leftWithZeroRange() {
       Range instance = new Range(0,0);
      Range.shift(instance,-5,false);        
      assertEquals(0, instance.getUpperBound(), .000000001d);
   }          

   //    public static Range scale(Range base, double factor) {
        @Test
   public void testCheckUpper_Scale_PositiveScale() {
       Range instance = new Range(-10,10);
      Range.scale(instance,5);        
      assertEquals(10, instance.getUpperBound(), .000000001d);
   }
        
        
        
        
        
        
        
        
        
   
    
    @Test
    public void testCheckLower_Contains_lowerBound() {
        exampleRange.contains(-5);        
        assertEquals(-5, exampleRange.getLowerBound(), .000000001d);
    }
    
    @Test
    public void testCheckLower_Contains_upperBound() {
       exampleRange.contains(5);        
       assertEquals(-5, exampleRange.getLowerBound(), .000000001d);
    }
    
    @Test
    public void testCheckLower_Contains_inBetween() {
    	exampleRange.contains(2);        
        assertEquals(-5, exampleRange.getLowerBound(), .000000001d);
    }
    
    @Test
    public void testCheckLower_Contains_belowLowerBound() {
       exampleRange.contains(-7);        
       assertEquals(-5, exampleRange.getLowerBound(), .000000001d);
    }
        
    @Test
    public void testCheckLower_Contains_belowUpperBound() {
        exampleRange.contains(7);        
        assertEquals(-5, exampleRange.getLowerBound(), .000000001d);
    }
   
    
    @Test
    public void testCheckLower_GetLowerBound_lowerbound() {
        Range instance = new Range(4,10);
        instance.getLowerBound();        
        assertEquals(4, instance.getLowerBound(), .000000001d);
    }
   
    
    @Test
    public void testCheckLower_GetLowerBound_lowerboundNeg() {
        Range instance = new Range(-4,-2);
        instance.getLowerBound();        
        assertEquals(-4, instance.getLowerBound(), .000000001d);
    }
   
    
    @Test
    public void testCheckLower_GetUpperBound_upperbound() {
        Range instance = new Range(4,10);
        instance.getUpperBound();        
        assertEquals(4, instance.getLowerBound(), .000000001d);
    }
   
    
    @Test
    public void testCheckLower_GetUpperBound_upperboundNeg() {
        Range instance = new Range(-4,-2);
        instance.getUpperBound();        
        assertEquals(-4, instance.getLowerBound(), .000000001d);
    }
   
    
    @Test
    public void testCheckLower_GetLength_SameUpperLower() {
        Range instance = new Range(5,5);
        instance.getLength();        
        assertEquals(5, instance.getLowerBound(), .000000001d);
    }
   
    
    @Test
    public void testCheckLower_GetLength_NegUpperLower() {
        Range instance = new Range(-5,-2);
       instance.getLength();        
       assertEquals(-5, instance.getLowerBound(), .000000001d);
    }
   
    
    @Test
    public void testCheckLower_GetLength_posUpperLower() {
        Range instance = new Range(2,10);
        instance.getLength();        
        assertEquals(2, instance.getLowerBound(), .000000001d);
    }
   
    
    @Test
    public void testCheckLower_ToString() {
        Range instance = new Range(3,10);
         instance.toString();        
         assertEquals(3, instance.getLowerBound(), .000000001d);
    }    
    
    
    @Test
    public void testCheckLower_getCentralValue() {
        Range instance = new Range(-10,10);
        instance.getCentralValue();        
        assertEquals(-10, instance.getLowerBound(), .000000001d);
    }    
    
    
    
    
    //public boolean intersects(double b0, double b1)
    // making b1 > lower bound(-10) puts us in the else
    @Test
    public void testCheckLower_Intersects_lower() {
        Range instance = new Range(-10,10);
        instance.intersects(-15,2);        
        assertEquals(-10, instance.getLowerBound(), .000000001d);
    }  
    
    @Test
    public void testCheckLower_Intersects_lowerout() {
        Range instance = new Range(-10,10);
        instance.intersects(-15,-12);        
        assertEquals(-10, instance.getLowerBound(), .000000001d);
    }
    @Test
    public void testCheckLower_Intersects_nonRealrange() {
        Range instance = new Range(-10,10);
        instance.intersects(15,2);        
        assertEquals(-10, instance.getLowerBound(), .000000001d);
    } 
    @Test
    public void testCheckLower_Intersects_aboveout() {
        Range instance = new Range(-10,10);
        instance.intersects(15,20);        
        assertEquals(-10, instance.getLowerBound(), .000000001d);
    } 
    @Test
    public void testCheckLower_Intersects_nonRealrange2() {
        Range instance = new Range(-10,10);
        instance.intersects(5,2);        
        assertEquals(-10, instance.getLowerBound(), .000000001d);
    }  
    @Test
    public void testCheckLower_Intersects_upperinRange() {
        Range instance = new Range(-10,10);
        instance.intersects(5,20);        
        assertEquals(-10, instance.getLowerBound(), .000000001d);
    }   
    
    //public boolean intersects(Range range)
    @Test
    public void testCheckLowerinstance1_Intersects_withRange() {
        Range instance = new Range(-10,10);
        Range instance2 = new Range(-5,5);
        instance.intersects(instance2);        
        assertEquals(-10, instance.getLowerBound(), .000000001d);
    }
    @Test
    public void testCheckLowerinstance2_Intersects_withRange() {
        Range instance = new Range(-10,10);
        Range instance2 = new Range(-5,5);
        instance.intersects(instance2);        
        assertEquals(-5, instance2.getLowerBound(), .000000001d);
    }     
    
    //public double constrain(double value)
    @Test
    public void testCheckLower_Constrain_inRange() {
        Range instance = new Range(-10,10);
        instance.constrain(5);        
        assertEquals(-10, instance.getLowerBound(), .000000001d);
    }       
    @Test
    public void testCheckLower_Constrain_above() {
        Range instance = new Range(-10,10);
        instance.constrain(50);        
        assertEquals(-10, instance.getLowerBound(), .000000001d);
    }       
    @Test
    public void testCheckLower_Constrain_below() {
        Range instance = new Range(-10,10);
        instance.constrain(-50);        
        assertEquals(-10, instance.getLowerBound(), .000000001d);
    }     
    //public static Range combine(Range range1, Range range2)
    @Test
    public void testCheckLowerinstance1_Combine_bothValid() {
        Range instance = new Range(-10,5);
        Range instance2 = new Range(-5,10);
        Range.combine(instance,instance2);      
        assertEquals(-10, instance.getLowerBound(), .000000001d);
    } 
    @Test
    public void testCheckLowerinstance2_Combine_bothValid() {
        Range instance = new Range(-10,5);
        Range instance2 = new Range(-5,10);
       Range.combine(instance,instance2);      
       assertEquals(-5, instance2.getLowerBound(), .000000001d);
    }  
    @Test
    public void testCheckLower_Combine_oneNull() {
        Range instance = null;
        Range instance2 = new Range(-5,10);
       Range.combine(instance,instance2);        
       assertEquals(-5, instance2.getLowerBound(), .000000001d);
    }  
    @Test
    public void testCheckLower_Combine_secondNull() {
        Range instance = new Range(-10,5);
        Range instance2 = null;
       Range.combine(instance,instance2);        
       assertEquals(-10, instance.getLowerBound(), .000000001d);
    }       
    //    public static Range combineIgnoringNaN(Range range1, Range range2)
    @Test
    public void testCheckLowerinstance1_CombineIgnoringNaN_bothValid() {
        Range instance = new Range(-10,5);
        Range instance2 = new Range(-5,10);
       Range.combineIgnoringNaN(instance,instance2);        
       assertEquals(-10, instance.getLowerBound(), .000000001d);
    }
    @Test
    public void testCheckLowerinstance2_CombineIgnoringNaN_bothValid() {
        Range instance = new Range(-10,5);
        Range instance2 = new Range(-5,10);
       Range.combineIgnoringNaN(instance,instance2);        
       assertEquals(-5, instance2.getLowerBound(), .000000001d);
    }  
    @Test
    public void testCheckLower_CombineIgnoringNaN_oneNull() {
        Range instance = null;
        Range instance2 = new Range(-5,10);
       Range.combineIgnoringNaN(instance,instance2);        
       assertEquals(-5, instance2.getLowerBound(), .000000001d);
    }  
    @Test
    public void testCheckLower_CombineIgnoringNaN_secondNull() {
        Range instance = new Range(-10,5);
        Range instance2 = null;
       Range.combineIgnoringNaN(instance,instance2);        
       assertEquals(-10, instance.getLowerBound(), .000000001d);
    }   
      
    @Test
    public void testCheckLower_CombineIgnoringNaN_oneValidSecondNan() {
        Range instance = new Range(-10,5);
        Range instance2 =  new Range(Math.sqrt(-1),Math.sqrt(-1));;
       Range.combineIgnoringNaN(instance,instance2);        
       assertEquals(-10, instance.getLowerBound(), .000000001d);
    }     
    //public static Range expandToInclude(Range range, double value) 
    @Test
    public void testCheckLower_ExpandToInclude_upper() {
        Range instance = new Range(-10,5);
       Range.expandToInclude(instance,10);        
       assertEquals(-10, instance.getLowerBound(), .000000001d);
    }    
    @Test
    public void testCheckLower_ExpandToInclude_lower() {
        Range instance = new Range(-5,10);
       Range.expandToInclude(instance,-10);        
       assertEquals(-5, instance.getLowerBound(), .000000001d);
    }  
    @Test
    public void testCheckLower_ExpandToInclude_Middle() {
        Range instance = new Range(-10,10);
       Range.expandToInclude(instance,0);        
       assertEquals(-10, instance.getLowerBound(), .000000001d);
    }       
    //public static Range expand(Range range,double lowerMargin, double upperMargin) 
    @Test
   public void testCheckLower_Expand_validRange() {
       Range instance = new Range(-10,10);
      Range.expand(instance,-5,1);        
      assertEquals(-10, instance.getLowerBound(), .000000001d);
   }          
    //public static Range shift(Range base, double delta,boolean allowZeroCrossing) 
        @Test
   public void testCheckLower_Shift_leftWithZeroCrossing() {
       Range instance = new Range(-10,10);
      Range.shift(instance,-5,true);        
      assertEquals(-10, instance.getLowerBound(), .000000001d);
   }         
        @Test
   public void testCheckLower_Shift_leftWithoutZeroCrossing() {
       Range instance = new Range(-10,10);
      Range.shift(instance,-5,false);        
      assertEquals(-10, instance.getLowerBound(), .000000001d);
   }         
        @Test
   public void testCheckLower_Shift_leftWithZeroRange() {
       Range instance = new Range(0,0);
      Range.shift(instance,-5,false);        
      assertEquals(0, instance.getLowerBound(), .000000001d);
   }          

   //    public static Range scale(Range base, double factor) {
        @Test
   public void testCheckLower_Scale_PositiveScale() {
       Range instance = new Range(-10,10);
      Range.scale(instance,5);        
      assertEquals(-10, instance.getLowerBound(), .000000001d);
   }
  
    
    @Test
    public void testShift_WithoutZeroCrossing() {
        Range instance = new Range(-0.5,0.5);
       Range instance3 = Range.shift(instance,-1,false);
       Range temp = new Range(-1.5,0.0);
        assertEquals(temp,instance3);
    } 
    
    
    @Test
   public void testExpand() {
       Range instance = new Range(-10,10);
      Range instance3 = Range.expand(instance,-5,4);
      Range temp = new Range(90,90);
       assertEquals(temp,instance3);
   }  
    
    
    @Test
   public void testExpand2() {
       Range instance = new Range(-10,10);
      Range instance3 = Range.expand(instance,5,-4);
      Range temp = new Range(-110,-70);
       assertEquals(temp,instance3);
   }   
   
     

       
    

//    @After
//    public void tearDown() throws Exception {}

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        exampleRange = null;
    }
}