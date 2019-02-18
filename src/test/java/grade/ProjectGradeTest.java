package grade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProjectGradeTest {

    private static final double DELTA = 0.001;

    @Test
    public void getGoodGradeIfCorrect() {

        double grade = ProjectGrade.calculateGrade(true, true, 7.5);

        Assertions.assertEquals(7.5, grade, DELTA);
    }

    @Test
    public void getBadGradeIfNotCompiles() {

        double grade = ProjectGrade.calculateGrade(true, false, 7.5);

        Assertions.assertEquals(1, grade, DELTA);
    }

    @Test
    public void getBadGradeIfNotUsedGit() {

        double grade = ProjectGrade.calculateGrade(false, true, 7.5);

        Assertions.assertEquals(1, grade, DELTA);
    }

    @Test
    public void testTaIsNotHappy() {
        Assertions.assertFalse(ProjectGrade.taIsHappy(false));
    }

    @Test
    public void testTaIsHappy() {
        Assertions.assertTrue(ProjectGrade.taIsHappy(true));
    }

    @Test
    public void testIsUsingLambda() {
        Assertions.assertTrue(ProjectGrade.usesLambda());
    }
}