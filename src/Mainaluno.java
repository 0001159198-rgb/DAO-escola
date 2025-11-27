// =================================================================
// 4. MAIN.java (Interface de Usuário/Teste)
// =================================================================
import java.util.Scanner;

public class Mainaluno {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AlunoDAO dao = new AlunoDAO();

        System.out.println("=== CADASTRO DE ALUNO ===");

        // --- 1. Operação CREATE (Criação) ---
        System.out.print("Digite o nome do aluno: ");
        String nome = sc.nextLine();

        System.out.print("Digite o email do aluno: ");
        String email = sc.nextLine();

        // Cria o objeto POO (sem ID, que será gerado pelo BD).
        Aluno novo = new Aluno(nome, email);
        dao.inserir(novo);

        // --- 2. Operação READ (Leitura/Listagem) ---
        System.out.println("\n--- LISTA DE ALUNOS ---");
        for (Aluno a : dao.listar()) {
            System.out.println(a.getId() + " - " + a.getNome() + " - " + a.getEmail());
        }

        // --- 3. Operação UPDATE (Atualização) ---
        System.out.println("\n=== ATUALIZAR UM ALUNO ===");
        System.out.print("Digite o ID do aluno que deseja atualizar: ");
        int idAtualizar = sc.nextInt();
        sc.nextLine(); // Limpa o buffer do Scanner após a leitura de um inteiro.

        System.out.print("Novo nome: ");
        String novoNome = sc.nextLine();

        System.out.print("Novo email: ");
        String novoEmail = sc.nextLine();

        // Cria o objeto Aluno completo (com ID) para enviar a atualização.
        Aluno atualizado = new Aluno(idAtualizar, novoNome, novoEmail);
        dao.atualizar(atualizado);

        // --- 4. Operação DELETE (Exclusão) ---
        System.out.println("\n=== DELETAR UM ALUNO ===");
        System.out.print("Digite o ID do aluno que deseja deletar: ");
        int idDelete = sc.nextInt();

        dao.deletar(idDelete);

        sc.close();
    }
}