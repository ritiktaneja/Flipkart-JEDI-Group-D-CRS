package com.flipkart.dao;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constants.Department;
import com.flipkart.constants.Designation;
import com.flipkart.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDao implements DaoInterface<Professor> {

    private static final String DELETE = "DELETE FROM professor WHERE professorId=?";
    private static final String GET_ALL = "SELECT * FROM professor ORDER BY professsorId";
    private static final String GET_BY_ID = "SELECT * FROM professor WHERE professorId=?";
    private static final String INSERT = "INSERT INTO professor(professorId,professorName) VALUES(?, ?)";
    private static final String UPDATE = "UPDATE professor SET name=?, password=?, department=?, designation=? WHERE professorId=?";

    @Override

    public Professor get(String id) {
        if (id == null) {
            return null;
        }
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(GET_BY_ID);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Professor.ProfessorBuilder builder = new Professor.ProfessorBuilder();
                builder.setFacultyId(rs.getString("professorId"));
                builder.setName(rs.getString("professorName"));
                return builder.build();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeConnection(connection);
        }
        return null;
    }

    @Override
    public List<Professor> getAll() {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        List<Professor> professorList = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(GET_ALL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Professor.ProfessorBuilder builder = new Professor.ProfessorBuilder();
                builder.setFacultyId(rs.getString("facultyId"));
                builder.setName(rs.getString("name"));
                builder.setPassword(rs.getString("password"));
                builder.setDesignation(Designation.values()[rs.getInt("designation")]);
                builder.setDepartment(Department.values()[rs.getInt("department")]);
                professorList.add(builder.build());
            }
            return professorList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    public int insert(Professor professor) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            Professor dbProfessor = get(professor.getFacultyId());
            if (dbProfessor == null) {
                stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, professor.getFacultyId());
                stmt.setString(2, professor.getName());
                int result = stmt.executeUpdate();
                System.out.println("Professor Added Successfully");
                return result;
            } else {
                System.out.println("Professor with same ID present");
                return 0;
            }
        } catch (Exception e) {
            System.out.println("Professor with same ID present");
        } finally {
            DBConnection.closeConnection(connection);
        }
        return 0;
    }

    public int update(String id, Professor professor) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(UPDATE);
            stmt.setString(1, professor.getName());
            stmt.setString(2, professor.getPassword());
            stmt.setInt(3, professor.getDepartment().getValue());
            stmt.setInt(4, professor.getDesignation().getValue());
            stmt.setString(5, id);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    public int delete(Professor professor) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(DELETE);
            stmt.setString(1, professor.getFacultyId());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeConnection(connection);
        }
    }


}


/**
 * CRUD -> Create, Read,
 */




