package com.flipkart.dao;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constants.Department;
import com.flipkart.constants.Designation;
import com.flipkart.utils.DBConnection;
import com.flipkart.constants.sqlconstants.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDao implements DaoInterface<Professor> {


    private static ProfessorDao instance = null;

    private ProfessorDao() {

    }

    public static ProfessorDao getInstance() {
        if (instance == null) {
            instance = new ProfessorDao();
        }
        return instance;
    }


    /**
     * Get professor object from the given id
     * @param id
     * @return
     */
    @Override
    public Professor get(String id) {
        if (id == null) {
            return null;
        }
        Connection connection = DBConnection.getConnection();
        PreparedStatement professorStatement = null;

        try {
            professorStatement = connection.prepareStatement(ProfessorDaoConstants.GET_BY_ID);
            professorStatement.setString(1, id);

            ResultSet rs = professorStatement.executeQuery();

            if (rs.next()) {
                Professor.ProfessorBuilder builder = new Professor.ProfessorBuilder();
                builder.setFacultyId(rs.getString("professorId"));
                builder.setName(rs.getString("professorName"));
                return builder.build();
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("No professor with this ID");
            e.printStackTrace();
        } finally {
            DBConnection.closeStatement(professorStatement);
            DBConnection.closeConnection(connection);
        }
        return null;
    }

    /**
     * Get the list of all professor
     * @return list of all professor
     */
    @Override
    public List<Professor> getAll() {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        List<Professor> professorList = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(ProfessorDaoConstants.GET_ALL);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Professor.ProfessorBuilder builder = new Professor.ProfessorBuilder();

                String professorName = rs.getString("professorName");
                String professorId = rs.getString("professorId");

                builder.setName(professorName);
                builder.setFacultyId(professorId);

                professorList.add(builder.build());
            }
            return professorList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
    }

    /**
     * Insert current professor in the given database
     * @param professor
     * @return
     */
    public int insert(Professor professor) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(ProfessorDaoConstants.INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, professor.getFacultyId());
            stmt.setString(2, professor.getName());
            int result = stmt.executeUpdate();
            System.out.println("Professor Added Successfully");
            return result;
        } catch (Exception e) {
            System.out.println("Professor with same ID present");
        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
        return 0;
    }

    /**
     * Update professor details in the database
     * @param id
     * @param professor
     * @return status
     */
    public int update(String id, Professor professor) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(ProfessorDaoConstants.UPDATE);
            stmt.setString(1, professor.getName());
            stmt.setString(2, professor.getPassword());
            stmt.setInt(3, professor.getDepartment().getValue());
            stmt.setInt(4, professor.getDesignation().getValue());
            stmt.setString(5, id);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
    }

    /**
     * Delete professor from the given database
     * @param professor
     * @return status
     */
    public int delete(Professor professor) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(ProfessorDaoConstants.DELETE);
            stmt.setString(1, professor.getFacultyId());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(connection);
        }
    }


}


/**
 * CRUD -> Create, Read,
 */




