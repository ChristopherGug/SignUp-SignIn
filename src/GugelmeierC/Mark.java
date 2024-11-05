package GugelmeierC;

import java.text.DecimalFormat;

/**
 * This is a Mark class
 * @author Christopher Gugelmeier
 * @Since 1.0
 * @Version 1.0
 */
public class Mark {

    /**
     * The minimum GPA allowed
     */
    public static final float MINIMUM_GPA = 0.0f;
    /**
     * The highest achievable GPA
     */
    public static final float MAXIMUM_GPA = 5.0f;
    /**
     * Formatting for GPA
     */
    public static final DecimalFormat GPA = new DecimalFormat("0.0");
    /**
     * The course code for the mark
     */
    private String courseCode;
    /**
     * The course name for the mark
     */
    private String courseName;
    /**
     * The student's final mark in the course
     */
    private int result;
    /**
     * The course's gpa weighting
     */
    private float gpaWeighting;

    /**
     * This is the parameterized constructor for the Mark class
     * @param courseCode The course code for the given mark
     * @param courseName The course name for the given Mark
     * @param result The final Mark
     * @param gpaWeighting The gpa weighting of the course
     */
    public Mark(String courseCode, String courseName, int result, float gpaWeighting)
    {
        setCourseCode(courseCode);
        setCourseName(courseName);
        setResult(result);
        setGpaWeighting(gpaWeighting);
    }

    /**
     * Overloaded toString method, returning a formatted string of the Mark
     * @return
     */
    public String toString()
    {
        return String.format("%s\t%-35s     %d\t\t%s", courseCode, courseName, result, GPA.format(gpaWeighting));
    }

    /**
     * @return The course code
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * @param courseCode The course code to set
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * @return The course name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName The course name to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * @return The result
     */
    public int getResult() {
        return result;
    }

    /**
     * @param result The result to set
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * @return The gpa weighting
     */
    public float getGpaWeighting() {
        return gpaWeighting;
    }

    /**
     * @param gpaWeighting The gpa weighting to set
     */
    public void setGpaWeighting(float gpaWeighting) {
        this.gpaWeighting = gpaWeighting;
    }
}
