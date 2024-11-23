package classes;

import java.util.List;

//precisa implementar, é um rascunho
public class AMaquinaVirtual {
    private ATabelaInstrucoes tabelaInstrucoes;
    private int ponteiro;
    private int[] pilha;
    private int topo;

    public AMaquinaVirtual(ATabelaInstrucoes tabelaInstrucoes) {
        this.tabelaInstrucoes = tabelaInstrucoes;
        this.ponteiro = 0;
        this.pilha = new int[100]; // Tamanho da pilha pode ser ajustado conforme necessário
        this.topo = -1;
    }

    public void executar() {
        List<Instrucao> instrucoes = tabelaInstrucoes.getInstrucoes();
        while (ponteiro < instrucoes.size()) {
            Instrucao instrucao = instrucoes.get(ponteiro);
            executarInstrucao(instrucao);
        }
    }

    private void executarInstrucao(Instrucao instrucao) {
        switch (instrucao.getCodigo()) {
            case "ADD":
                executarADD();
                break;
            case "ALB":
                executarALB((int) instrucao.getParametro());
                break;
            case "ALI":
                executarALI((int) instrucao.getParametro());
                break;
            default:
                throw new RuntimeException("Instrução desconhecida: " + instrucao.getCodigo());
        }
    }

    private void executarADD() {
        if (topo < 1) {
            throw new RuntimeException("Erro de execução: pilha com elementos insuficientes para ADD");
        }
        pilha[topo - 1] = pilha[topo - 1] + pilha[topo];
        topo--;
        ponteiro++;
    }

    private void executarALB(int deslocamento) {
        for (int i = topo + 1; i <= topo + deslocamento; i++) {
            pilha[i] = 0; // Supondo que FALSE seja representado por 0
        }
        topo += deslocamento;
        ponteiro++;
    }

    private void executarALI(int deslocamento) {
        for (int i = topo + 1; i <= topo + deslocamento; i++) {
            pilha[i] = 0;
        }
        topo += deslocamento;
        ponteiro++;
    }

    public String exibirPilha() {
        StringBuilder sb = new StringBuilder();
        sb.append("Estado atual da pilha:\n");
        for (int i = 0; i <= topo; i++) {
            sb.append("pilha[" + i + "] = " + pilha[i] + "\n");
        }
        return sb.toString();
    }
}