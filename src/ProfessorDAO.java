import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    public void inserir(Professor professor) {
        String sql = "INSERT INTO professores (nome, email, estado, cidade, estado_civil, materia_responsavel, usuario, senha) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, professor.getEstado());
            stmt.setString(4, professor.getCidade());
            stmt.setString(5, professor.getEstado_civil());
            stmt.setString(6, professor.getMateria_responsavel());
            stmt.setString(7, professor.getUsuario());
            stmt.setString(8, professor.getSenha());

            stmt.executeUpdate();

            System.out.println("Professor inserido com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao inserir professor: " + e.getMessage());
        }
    }

    public List<Professor> listar() {
        List<Professor> lista = new ArrayList<>();
        String sql = "SELECT * FROM professores";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Professor p = new Professor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("estado"),
                        rs.getString("cidade"),
                        rs.getString("estado_civil"),
                        rs.getString("materia_responsavel"),
                        rs.getString("usuario"),
                        rs.getString("senha")
                );

                lista.add(p);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar professores: " + e.getMessage());
        }

        return lista;
    }

    // ============================
    // ATUALIZAR PROFESSOR
    // ============================
    public void atualizar(Professor professor) {
        String sql = "UPDATE professores SET nome = ?, email = ?, estado = ?, cidade = ?, estado_civil = ?, materia_responsavel = ?, usuario = ?, senha = ? " +
                "WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, professor.getEstado());
            stmt.setString(4, professor.getCidade());
            stmt.setString(5, professor.getEstado_civil());
            stmt.setString(6, professor.getMateria_responsavel());
            stmt.setString(7, professor.getUsuario());
            stmt.setString(8, professor.getSenha());
            stmt.setInt(9, professor.getId());

            stmt.executeUpdate();

            System.out.println("Professor atualizado!");

        } catch (Exception e) {
            System.out.println("Erro ao atualizar professor: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM professores WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Professor deletado!");

        } catch (Exception e) {
            System.out.println("Erro ao deletar professor: " + e.getMessage());
        }
    }
    public Professor login(String usuario, String senha) {
        String sql = "SELECT * FROM professores WHERE usuario = ? AND senha = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Professor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("estado"),
                        rs.getString("cidade"),
                        rs.getString("estado_civil"),
                        rs.getString("materia_responsavel"),
                        rs.getString("usuario"),
                        rs.getString("senha")
                );
            }

        } catch (Exception e) {
            System.out.println("Erro ao autenticar professor: " + e.getMessage());
        }

        return null;
    }
}
