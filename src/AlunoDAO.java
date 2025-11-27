// =================================================================
// 3. ALUNODAO.java (Data Access Object)
// =================================================================
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AlunoDAO {

    public void inserir(Aluno aluno) {
        String sql = "INSERT INTO alunos (nome, email) VALUES (?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());

            stmt.executeUpdate();

            System.out.println("Aluno inserido com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao inserir: " + e.getMessage());
        }
    }

    public List<Aluno> listar() {
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT * FROM alunos";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Aluno a = new Aluno(
                        rs.getInt("id"), // Pega o valor da coluna 'id'
                        rs.getString("nome"), // Pega o valor da coluna 'nome'
                        rs.getString("email") // Pega o valor da coluna 'email'
                );
                lista.add(a);
            }

        } catch (Exception e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }

        return lista;
    }

    public void atualizar(Aluno aluno) {
        String sql = "UPDATE alunos SET nome = ?, email = ? WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());

            stmt.setInt(3, aluno.getId());

            stmt.executeUpdate();

            System.out.println("Aluno atualizado!");

        } catch (Exception e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM alunos WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.println("Aluno deletado!");

        } catch (Exception e) {
            System.out.println("Erro ao deletar: " + e.getMessage());
        }
    }
}