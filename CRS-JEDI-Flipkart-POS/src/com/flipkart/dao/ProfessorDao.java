package com.flipkart.dao;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constants.Department;
import com.flipkart.constants.Designation;
import com.flipkart.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDao implements DaoInterface<Professor>  {

    private static final String DELETE = "DELETE FROM professor WHERE id=?";
    private static final String GET_ALL = "SELECT * FROM professor ORDER BY id";
    private static final String GET_BY_ID = "SELECT * FROM professor WHERE id=?";
    private static final String INSERT = "INSERT INTO professor(facultyId, name, password, department, designation) VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE professor SET name=?, password=?, department=?, designation=? WHERE professorId=?";
    @Override
    public Professor get(String id) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(GET_BY_ID);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                Professor.ProfessorBuilder builder = new Professor.ProfessorBuilder();
                builder.setFacultyId(rs.getString("facultyId"));
                builder.setName(rs.getString("name"));
                builder.setPassword(rs.getString("password"));
                builder.setDepartment(Department.values()[rs.getInt("department")]);
                builder.setDesignation(Designation.values()[rs.getInt("designation")]);
                return builder.build();
            } else {
                throw new SQLException("Student Not Found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeConnection(connection);
        }
    }

    @Override
    public List<Professor> getAll() {
        Connection connection = DBConnection.getConnection();
        PreparedStatement stmt = null;
        List<Professor> professorList = new ArrayList<>();
        try {
            stmt = connection.prepareStatement(GET_ALL);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
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
            stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, professor.getFacultyId());
            stmt.setString(2, professor.getName());
            stmt.setString(3, professor.getPassword());
            stmt.setInt(4, professor.getDepartment().getValue());
            stmt.setInt(5, professor.getDesignation().getValue());
            int result = stmt.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        } finally {
            DBConnection.closeConnection(connection);
        }
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
        } catch(SQLException e) {
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




