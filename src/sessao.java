public class sessao {
    private static Professor professorLogado = null;

    public static void logar(Professor p) {
        professorLogado = p;
    }

    public static Professor getProfessorLogado() {
        return professorLogado;
    }

    public static boolean estaLogado() {
        return professorLogado != null;
    }

    public static void logout() {
        professorLogado = null;
    }
}
