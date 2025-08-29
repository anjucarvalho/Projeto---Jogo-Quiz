import java.awt.*;
import javax.swing.*;

public class TelaInicio {
    public TelaInicio(Jogador jogador) {
        JFrame frame = new JFrame("Clima");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(255, 245, 238));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 245, 238));

        JLabel label = new JLabel("Adivinhação de Gases Poluentes");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Arial", Font.BOLD, 28));

        JPanel nomePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nomePanel.setBackground(new Color(255, 245, 238));

        JLabel nomeLabel = new JLabel("Digite seu nome/apelido:");
        JTextField nomeField = new JTextField(20);

        nomePanel.add(nomeLabel);
        nomePanel.add(nomeField);

        JButton botaoIniciar = new JButton("Confirmar :D");
        botaoIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoIniciar.setEnabled(true);

        botaoIniciar.addActionListener(e -> {
            String nome = nomeField.getText().trim();
            if (!nome.isEmpty()) {
                jogador.setNome(nome);

                 JOptionPane.showMessageDialog(frame, "Bem-vindo, " + nome + "!", "Boas-vindas", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose(); 
                new TelaJogo(jogador);
            
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor, digite um nome!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        panel.add(Box.createVerticalStrut(20));
        panel.add(label);
        panel.add(Box.createVerticalStrut(20));
        panel.add(nomePanel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(botaoIniciar);
        panel.add(Box.createVerticalStrut(10));


        frame.add(panel);
        frame.setVisible(true);
    }
}