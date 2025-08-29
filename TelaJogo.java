import java.awt.*;
import java.util.*;
import javax.swing.*;

public class TelaJogo {
    private final Map<String, Double> setores = new LinkedHashMap<>();
    private final Map<String, String> dicas = new HashMap<>();
    private final Map<String, String> textosConscientizacao = new HashMap<>();
    private Iterator<String> iterator;
    private String setorAtual;
    private int tentativas;

    public TelaJogo(Jogador jogador) {
        setores.put("Agricultura", 11.7);
        setores.put("Processos Industriais", 6.5);
        setores.put("Lixo", 3.4);
        setores.put("Mudança do Uso da Terra", 2.7);
        setores.put("Energia", 75.7);

        dicas.put("Agricultura", "reduza o consumo de carne e apoie a agricultura sustentável.");
        dicas.put("Processos Industriais", "incentive a indústria a adotar tecnologias limpas e processos mais sustentáveis.");
        dicas.put("Lixo", "pratique a separação de lixo, recicle sempre que possível e evite descartes inadequados.");
        dicas.put("Mudança do Uso da Terra", "apoie projetos de reflorestamento e evite o consumo que estimula o desmatamento.");
        dicas.put("Energia", "economize energia e escolha fontes renováveis como solar e eólica sempre que possível.");

        textosConscientizacao.put("Agricultura", "O solo está em constante detrioração, tanto por fatores climáticos quanto pelo ser humano, a agricultura e outros tipos de uso do solo representam uma boa porcentagem dos emissores de gases do efeito estufa.");
        textosConscientizacao.put("Processos Industriais", "Inclue emissão de gases por produção de químicos e cimento (representando as principais fontes de emissão de gases do efeito estufa dentro do setor de processos industriais). ");
        textosConscientizacao.put("Lixo", "A queima de resíduos plásticos a céu aberto é uma das principais fontes de poluição do ar, onde a maior parte de todo o lixo do mundo é queimado. Liberando gases tóxicos na atmosfera, como dioxinas, furanos, mercúrio e bifenilos policlorados, a prática disso traz uma grande ameaça à vegetação e à saúde humana e animal.");
        textosConscientizacao.put("Mudança do Uso da Terra", "A alteração do uso da terra, especialmente impulsionada pela expansão agrícola e pelo crescimento urbano, representa um dos principais limites planetários que estamos excedendo. Essa transformação não apenas compromete os ecossistemas locais, como também provoca impactos em escala global, contribuindo para as mudanças climáticas por meio do desmatamento e da emissão de carbono armazenado no solo.");
        textosConscientizacao.put("Energia", "A maior parte da poluição energética provém do transporte(12,2%) e das atividades residenciais(12,5%). Desde 1990 as emissões de gases no setor de energia cresceu em 88%.");
        iterator = setores.keySet().iterator();
        mostrarProximaPergunta(jogador);
    }

    private void mostrarProximaPergunta(Jogador jogador) {
        if (!iterator.hasNext()) {
            JOptionPane.showMessageDialog(null, "Fim do jogo! Obrigado por jogar, " + jogador.getNome() + "!");
            return;
        }

        setorAtual = iterator.next();
        tentativas = 0;

        JFrame frame = new JFrame("Jogo de Emissões");
        frame.setSize(700, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(new Color(255, 245, 238));

        JLabel titulo = new JLabel("Adivinhe a porcentagem de emissão para:");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel setorLabel = new JLabel(setorAtual);
        setorLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        setorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea textoConscientizacao = new JTextArea(textosConscientizacao.get(setorAtual));
        textoConscientizacao.setWrapStyleWord(true);
        textoConscientizacao.setLineWrap(true);
        textoConscientizacao.setEditable(false);
        textoConscientizacao.setOpaque(false);
        textoConscientizacao.setFont(new Font("Arial", Font.ITALIC, 14));
        textoConscientizacao.setMaximumSize(new Dimension(600, 100));
        textoConscientizacao.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField campoResposta = new JTextField();
        campoResposta.setMaximumSize(new Dimension(200, 30));

        JButton enviarPalpite = new JButton("Enviar Palpite");
        enviarPalpite.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel dicaLabel = new JLabel("Você tem 3 tentativas, " + jogador.getNome() + "!");
        dicaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        enviarPalpite.addActionListener(e -> {
            try {
                double palpite = Double.parseDouble(campoResposta.getText().replace(",", "."));
                tentativas++;
                double valorCorreto = setores.get(setorAtual);

                if (Math.abs(palpite - valorCorreto) < 0.1) {
                    dicaLabel.setText("Acertou! Emissão correta: " + valorCorreto + "%");
                    enviarPalpite.setEnabled(false);
                    JOptionPane.showMessageDialog(frame,"Parabéns! Aqui vai uma dica: " + dicas.get(setorAtual));
                    frame.dispose();
                    mostrarProximaPergunta(jogador);
                } else {
                    textoConscientizacao.setText(textosConscientizacao.get(setorAtual));
                    if (tentativas == 1) {
                        dicaLabel.setText("Errou! A resposta esta entre " + String.format("%.2f", valorCorreto - 2) + "% e " + String.format("%.2f", valorCorreto + 5) + "%.");

                    }  
                    else if (tentativas == 2){
                        dicaLabel.setText("Errou! A resposta esta entre " + String.format("%.2f", valorCorreto - 1 ) + "% e " + String.format("%.2f", valorCorreto + 1) + "%.");
                    
                    }
                    else if (tentativas == 3) {
                        dicaLabel.setText("Errou! A resposta era " + valorCorreto + "%.");
                        enviarPalpite.setEnabled(false);
                        JOptionPane.showMessageDialog(frame, "Errou :-( "  +  "Aqui vai uma dica: " + dicas.get(setorAtual));
                        frame.dispose();
                        mostrarProximaPergunta(jogador);
                    }
                }
            } catch (NumberFormatException ex) {
                dicaLabel.setText("Digite um número válido.");
            }
        });

        painel.add(Box.createVerticalStrut(20));
        painel.add(titulo);
        painel.add(Box.createVerticalStrut(10));
        painel.add(setorLabel);
        painel.add(Box.createVerticalStrut(10));
        painel.add(textoConscientizacao);
        painel.add(Box.createVerticalStrut(10));
        painel.add(campoResposta);
        painel.add(Box.createVerticalStrut(10));
        painel.add(enviarPalpite);
        painel.add(Box.createVerticalStrut(20));
        painel.add(dicaLabel);

        frame.add(painel);
        frame.setVisible(true);
    }
}

