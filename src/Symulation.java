import component.RightPanelComponent;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class Symulation extends JFrame {
    // static variables
    private static final int SLIDER_MIN = 0;
    private static final int SLIDER_MAX = 100;
    private static final int SLIDER_INIT = 0;

    private static final int SLIDER_MIN_EYE = 0;
    private static final int SLIDER_MAX_EYE = 6;
    private static final int SLIDER_INIT_EYE = 0;

    // variables
    private JPanel leftPanel;
    private JPanel visualImparimentPanel;
    private JPanel lenghtPanel;

    public void setRightPanelComponentColor(Color color) {
        SwingUtilities.invokeLater(() -> rightPanelComponent.getPanel().setBackground(color));
    }

    private RightPanelComponent rightPanelComponent = new RightPanelComponent();

    public JPanel rightPanelSymulation;

    private JPanel topPanel;
    private JPanel bottomPanel;
    private JLabel changeRefractiveErrorLabel;
    private JLabel visualImpairment;
    private JLabel lenghtLabel;
    private JLabel labelOdleglosc;
    private JLabel labelTitle;
    private JRadioButton nearSightedness;
    private JRadioButton farSightedness;
    private ButtonGroup buttonGroup;
    private JSlider eye;
    private JSlider lenghtSlider;
    private int odleglosc = 0;
    public boolean isPL = true;
    MenuCreator menuCreator;
    Translator translator;
    private JButton buttonRun;
    // main method

    public static void main(String[] args) {
        Symulation okno = new Symulation();
        okno.setVisible(true);
    }

    //getters
    public JPanel getRightPanelSymulation() {
        return rightPanelSymulation;
    }

    public JLabel getChangeRefractiveErrorLabel() {
        return changeRefractiveErrorLabel;
    }

    public JLabel getVisualImpairment() {
        return visualImpairment;
    }

    public JLabel getLenghtLabel() {
        return lenghtLabel;
    }

    public JLabel getLabelOdleglosc() {
        return labelOdleglosc;
    }

    public JRadioButton getNearSightedness() {
        return nearSightedness;
    }
    public JRadioButton getFarSightedness() {
        return farSightedness;
    }

    public JButton getButtonRun() {
        return buttonRun;
    }

    public int getOdleglosc() {
        return odleglosc;
    }

    public JLabel getLabelTitle() {
        return this.labelTitle;
    }

    public void updateText() {
        translator.updateText(isPL);
    }

    public Symulation() {
        this.setSize(1200, 800);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menuCreator = new MenuCreator(this);
        translator = new Translator(this);

        Menu();
        LeftPanel();
        RightSymulationPanel();
        TopPanel();
        BottomPanel();
        updateText();
    }

    private void Menu() {
        this.setJMenuBar(menuCreator.createJMenuBar());
    }

    private void LeftPanel() {
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(10, 1));
        leftPanel.setBackground(new Color(102, 204, 255));

        changeRefractiveErrorLabel = new JLabel(translator.LABEL_REFRACTIVE_ERROR_PL, SwingConstants.CENTER);
        changeRefractiveErrorLabel.setForeground(Color.BLUE);
        buttonGroup = new ButtonGroup();

        nearSightedness = new JRadioButton(translator.RADIOBUTTON_NEAR_SIGHTEDNESS_PL);
        nearSightedness.setBackground(new Color(102, 204, 255));
        farSightedness = new JRadioButton(translator.RADIOBUTTON_FAR_SIGHTEDNESS_PL);
        farSightedness.setBackground(new Color(102, 204, 255));
        buttonGroup.add(nearSightedness);
        buttonGroup.add(farSightedness);

        visualImpairment = new JLabel(translator.LABEL_WIELKOSC_WADY_PL, SwingConstants.CENTER);
        visualImpairment.setForeground(Color.BLUE);

        visualImparimentPanel = new JPanel();
        visualImparimentPanel.setLayout(new GridLayout(1, 2));

        eye = new JSlider();
        eye = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN_EYE, SLIDER_MAX_EYE, SLIDER_INIT_EYE);
        eye.setBackground(new Color(102, 204, 255));
        eye.setPreferredSize(new Dimension(100, 50)); // wielkosc suwaka
        eye.setMajorTickSpacing(1); //Co ile ma byc widoczna liczba
        eye.setPaintTicks(true); //podzia�ki
        eye.setMinorTickSpacing(1); //Co ile kreska
        eye.setPaintLabels(true); //liczby

        visualImparimentPanel.add(eye, BorderLayout.LINE_START);

        lenghtLabel = new JLabel(translator.LABEL_LENGHT_PL, SwingConstants.CENTER);
        lenghtLabel.setForeground(Color.BLUE);

        lenghtSlider = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN, SLIDER_MAX, SLIDER_INIT);
        lenghtSlider.setBackground(new Color(102, 204, 255));
        lenghtSlider.setPreferredSize(new Dimension(200, 50)); // wielkosc suwaka
        lenghtSlider.setMajorTickSpacing(20); //Co ile ma byc widoczna liczba
        lenghtSlider.setPaintTicks(true); //podzia�ki
        lenghtSlider.setMinorTickSpacing(5); //Co ile kreska
        lenghtSlider.setPaintLabels(true); //li 	czby
        lenghtSlider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent arg0) {
                odleglosc = lenghtSlider.getValue();
                labelOdleglosc.setText(translator.LABEL_LENGHT_PL + " " +  odleglosc + "m");
                if(odleglosc > 45) {
                    rightPanelComponent.updateSymulation(odleglosc, true);
                } else {
                    rightPanelComponent.updateSymulation(odleglosc, false);
                }
            }
        });

        lenghtPanel = new JPanel();
        lenghtPanel.setLayout(new GridLayout(1, 2));
        lenghtPanel.setBackground(new Color(102, 204, 255));
        labelOdleglosc = new JLabel(translator.LABEL_LENGHT_PL + " " + odleglosc + "m", SwingConstants.CENTER);
        labelOdleglosc.setForeground(Color.BLUE);

        lenghtPanel.add(labelOdleglosc);

        buttonRun = new JButton(translator.BUTTON_RUN_PL);

        leftPanel.add(changeRefractiveErrorLabel);
        leftPanel.add(nearSightedness);
        leftPanel.add(farSightedness);
        leftPanel.add(visualImpairment);
        leftPanel.add(visualImparimentPanel);
        leftPanel.add(lenghtLabel);
        leftPanel.add(lenghtSlider);
        leftPanel.add(lenghtPanel);
        leftPanel.add(buttonRun);

        this.add(leftPanel, BorderLayout.LINE_START);
    }

    private void RightSymulationPanel() {
        this.add(rightPanelComponent.getPanel());
    }

    private void TopPanel() {
        topPanel = new JPanel();
        labelTitle = new JLabel(translator.FRAME_TITLE_PL, SwingConstants.CENTER);
        labelTitle.setForeground(Color.BLUE);
        topPanel.add(labelTitle);
        topPanel.setBackground(new Color(102, 204, 255));

        this.add(topPanel, BorderLayout.PAGE_START);
    }

    private void BottomPanel() {
        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(102, 204, 255));

        this.add(bottomPanel, BorderLayout.PAGE_END);
    }
}