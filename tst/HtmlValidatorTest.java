// This is JUnit test program stub
// DO NOT CHANGE THE NAME OF THE METHODS GIVEN
// 0) test0 is by the instructor as example to test your validate() method
// 1) You are to reproduce testing validate() method with test1.html-test8.html and
//    match the expected output
// 2) You are to add your own JUnit test for testing your removeAll method (At least 4)
// 3) Feel free to add more test cases to test any of your public methods in HtmlValidator (No extra credit, but for your own benefit)

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

public class HtmlValidatorTest {
    /**
     * Below code returns the String format
     * of the content of the given file
     * @param expectedFileName The name of the file that has expected output
     *                         Make sure put relative path in front of
     *                         the file name
     *                         (For example, if your files under tst folder,
     *                         expectedFileName should be "tst/YOUR_FILE_NAME"
     * @return The String format of what the expectedFileName contains
     */
    private static String expectedOutputToString (String expectedFileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner fileScanner = new Scanner(new File(expectedFileName));
            while (fileScanner.hasNextLine()) {
                sb.append(fileScanner.nextLine()+ System.lineSeparator());
            }
        } catch (FileNotFoundException ex) {
            Assert.fail(expectedFileName + "not found. Make sure this file exists. Use relative path to root in front of the file name");
        }
        return sb.toString();
    }

    /** Below code returns the String format
     * of what your validator's validate prints to the console
     * Feel free to use it so that you can compare it with the expected string
     * @param validator HtmlValidator to test
     * @return String format of what HtmlValidator's validate outputs
     */
    private static String validatorOutputToString(HtmlValidator validator) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);
        validator.validate();
        System.out.flush();
        System.setOut(oldOut);
        return baos.toString();
    }

    /**
     * This test is an instructor given test case to show you some example
     * of testing your validate() method
     * <b>Hi</b><br/> is the hypothetical html file to test
     */
    @Test
    public void test0(){
        //<b>Hi</b><br/>
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("b", true));      // <b>
        tags.add(new HtmlTag("b", false));     // </b>
        tags.add(new HtmlTag("br"));           // <br/>
        HtmlValidator validator = new HtmlValidator(tags);

        //Note test0_expected_output.txt is placed under tst. Use relative path!
        Assert.assertEquals(expectedOutputToString("tst/test0_expected_output.txt"),
                            validatorOutputToString(validator));
    }

    /**
     * This test1 method should test your validate() method
     * reproducing the test of
     * input_html/test1.html and expected_output/validate_result_for_test1.txt
     */
	@Test
	public void test1(){
        // tests constructor's Illegal Argument Exception
        new HtmlValidator(null);

        Assert.assertTrue("If false, null activates IllegalArgumentException", true);

	}

    /**
     * This test2 method should test your validate() method
     * reproducing the test of
     * input_html/test2.html and expected_output/validate_result_for_test2.txt
     */
	@Test
	public void test2(){
        // test's that addTag method throws IllegalArgumentException

        HtmlValidator validator = new HtmlValidator();
        validator.addTag(null);


	}


    /**
     * This test3 method should test your validate() method
     * reproducing the test of
     * input_html/test3.html and expected_output/validate_result_for_test3.txt
     */
	@Test
	public void test3(){
        // Test's addTag method with actual tags
        HtmlValidator validator = new HtmlValidator();
        validator.addTag(new HtmlTag("h1", true));
        validator.addTag(new HtmlTag("h1", false));

        Queue<HtmlTag> tags = validator.getTags();
        Assert.assertNotEquals(tags, validator.hTag);
        Assert.assertEquals(tags, validator.hTag);

    }


    /**
     * This test4 method should test your validate() method
     * reproducing the test of
     * input_html/test4.html and expected_output/validate_result_for_test4.txt
     */
	@Test
	public void test4(){
        // Tests remove all method
        HtmlValidator validator = new HtmlValidator();

        validator.addTag(new HtmlTag("h1"));
        validator.addTag(new HtmlTag("h2"));

        validator.removeAll("h1");
        Assert.assertEquals(1, validator.hTag.size());
        Assert.assertEquals("h2", validator.hTag.peek().getElement());

	}

    /**
     * This test5 method should test your validate() method
     * reproducing the test of
     * input_html/test5.html and expected_output/validate_result_for_test5.txt
     */
	@Test
	public void test5(){
        // tests removeAll will throw IllegalArgumentException

        HtmlValidator validator = new HtmlValidator();
        validator.removeAll(null);

	}

    /**
     * This test1 method should test your validate() method
     * reproducing the test of
     * input_html/test6.html and expected_output/validate_result_for_test6.txt
     */
	@Test
	public void test6(){
        // tests validate method can identify self closing tags
        HtmlValidator validator = new HtmlValidator();
        validator.addTag(new HtmlTag("h1", true));
        //validator.addTag(new HtmlTag("p",true ));
        validator.addTag(new HtmlTag("h1", false));
        validator.validate();

        Assert.assertTrue("Validator correctly identified true tag",true);
        //uncomment p tag to use assert statement below
        // Assert.assertTrue("Validator identified p tag",false);

	}

    /**
     * This test7 method should test your validate() method
     * reproducing the test of
     * input_html/test7.html and expected_output/validate_result_for_test7.txt
     */
	@Test
	public void test7(){
        // Tests validator error
        HtmlValidator validator = new HtmlValidator();
        HtmlTag tag1 = new HtmlTag("html", true);
        HtmlTag tag2 = new HtmlTag("body", true);
        HtmlTag tag3 = new HtmlTag("p", true);

        validator.addTag(tag1);
        validator.addTag(tag2);
        validator.addTag(tag3);

        validator.validate();
        Assert.assertTrue("No closing tags are found", false);

	}

    /**
     * This test8 method should test your validate() method
     * reproducing the test of
     * input_html/test8.html and expected_output/validate_result_for_test8.txt
     */
	@Test
	public void test8(){
        //tests validate method correctly processes closing tag
        HtmlValidator validator = new HtmlValidator();
        validator.addTag(new HtmlTag("b"));

	}

	/**
	 * Add your own test to test your removeAll method
	 * Add your own comment here:
	 */
	@Test
	public void myRemoveAllTest1(){
        //test adds tags and then removes them
        //After, test checks to make sure they are removed
        HtmlValidator validator = new HtmlValidator();
        HtmlTag tag1 = new HtmlTag("html", true);
        HtmlTag tag2 = new HtmlTag("body", true);
        HtmlTag tag3 = new HtmlTag("p", true);
        HtmlTag tag4 = new HtmlTag("p", false);
        validator.addTag(tag1);
        validator.addTag(tag2);
        validator.addTag(tag3);
        validator.addTag(tag4);

        validator.removeAll("p");

        Assert.assertEquals(2, validator.getTags().size());
        Assert.assertEquals("html", validator.getTags().remove().getElement());
        Assert.assertEquals("body", validator.getTags().remove().getElement());
        Assert.assertTrue(validator.getTags().isEmpty());
	}

	/**
	 * Add your own test to test your removeAll method
	 * Add your own comment here:
	 */
	@Test
	public void myRemoveAllTest2(){

	}

	/**
	 * Add your own test to test your removeAll method
	 * Add your own comment here:
	 */
	@Test
	public void myRemoveAllTest3(){

	}

    /**
     * Add your own test to test your removeAll method
     * Add your own comment here:
     */
    @Test
    public void myRemoveAllTest4(){

    }

    //FEEL FREE TO ADD MORE TESTS HERE
}
