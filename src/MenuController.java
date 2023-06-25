import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {

    JFrame frame;
    MenuFundament menuFundament;
    RateGamePanel rateGamePanel;
    ScoreboardPanel scoreboardPanel;
    SettingsPanel settingsPanel;
    GamePanel gamePanel;
    MenuPanel menuPanel;
    RateGameList rgList;
    ScoreboardList sbList;
    SettingsList settList;
    GameController gameController;
    private JPanel currentPanel;


    public MenuController() {

        frame = new JFrame();

        menuFundament = new MenuFundament();
        rateGamePanel = new RateGamePanel(this);
        scoreboardPanel = new ScoreboardPanel(this);
        settingsPanel = new SettingsPanel(this);
        gamePanel = new GamePanel(gameController, this);
        rgList = new RateGameList();
        sbList = new ScoreboardList();
        settList = new SettingsList();
        menuPanel = new MenuPanel();
        gameController = new GameController(gamePanel);

      /*  frame.add(rateGamePanel);
        frame.add(scoreboardPanel);
        frame.add(settingsPanel);
        frame.add(gamePanel);*/
//GEHT NICHT. MAN KANN NICHT ALLE AUFEINMAL HINZUFÜGEN
        frame.add(menuPanel);
        menuPanel.addController(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonLabel = ((JButton) e.getSource()).getText();

        if (buttonLabel.equals("Start Game")) {
            showPanel(gamePanel, "Infos zu Start Game");
        } else if (buttonLabel.equals("Scoreboard")) {
            showPanel(scoreboardPanel, "Infos zu Scoreboard");
        } else if (buttonLabel.equals("Settings")) {
            showPanel(settingsPanel, "Infos zu Settings");
        } else if (buttonLabel.equals("Rate Game")) {
            showPanel(rateGamePanel, "Infos zu Rate Game");
        } else if (buttonLabel.equals("Back to Menu")) {
            if (currentPanel == gamePanel) {
                System.out.println("Panel nach restart Game");
                gamePanel.restartTheGame();
                gameController = new GameController(gamePanel);//Hierdurch wird das Spiel immer schneller also noch verbesserungsfähig
            }
            showPanel(menuPanel, "Back to menu");
        }
    }

    private void showPanel(JPanel jPanel, String info) {
        frame.getContentPane().removeAll();
        currentPanel = jPanel;
        frame.getContentPane().add(currentPanel);
        jPanel.requestFocusInWindow();
        frame.revalidate();
        frame.repaint();
    }
}





