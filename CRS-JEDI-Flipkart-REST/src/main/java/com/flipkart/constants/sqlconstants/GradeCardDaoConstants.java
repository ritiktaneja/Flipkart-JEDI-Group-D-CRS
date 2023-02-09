package com.flipkart.constants.sqlconstants;

public class GradeCardDaoConstants {
    public static final String INSERT_IN_GRADE = "INSERT INTO gradeCard (RegisteredCourseId,grade) VALUES(?,?)";

    public static final String UPDATE_GRADE = "UPDATE gradeCard SET grade = ? WHERE registeredCourseId = ?";

    public static final String GET_GRADE = "SELECT * from gradeCard WHERE registeredCourseId = ?";
}
