package DAO;

import Conexao.ConexaoSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProcessDAO {
    
    private String process;
    
    public String getProcess() {
        return process;
    }
    
    public void Create(String n, String p){
        
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO aws_process (Name, Process) VALUES (?,?)");
            stmt.setString(1, n);
            stmt.setString(2, p);
            if (stmt.execute()) {
                JOptionPane.showMessageDialog(null, "Falha ao Inserir!");
            } else {
                JOptionPane.showMessageDialog(null, "Inserido com Sucesso!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar informações: " +e);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt);
        }
    }
    
    public boolean Search(String n, String p){
        
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        boolean cont = false;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM aws_process WHERE Name = ? OR Process = ?");
            stmt.setString(1, n);
            stmt.setString(2, p);
            rs = stmt.executeQuery();
            if(rs.next()){
                cont = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao obter registros do BD: " +e);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt, rs);
        }
        return cont;
        
    }
    
    public boolean Search(String name){
        
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        boolean cont = false;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM aws_process WHERE Name = ?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            if(rs.next()){
                cont = true;
                process = rs.getString("Process");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao obter registros do BD: " +e);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt, rs);
        }
        return cont;
        
    }
    
    public List<String> Box() {
        
        Connection con = ConexaoSQL.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> nomes = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM aws_process");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                nomes.add(rs.getString("Name"));
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao obter registros do BD: " +e);
        } finally {
            ConexaoSQL.CloseConnection(con, stmt, rs);
        }
        
        return nomes;    
    }
    
}
