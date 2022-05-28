package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.URL;

public class StartWindow implements Runnable {

    JFrame frame; // само окно
    JButton buttonStartRand; // кнопка для рандомизированной игры
    JButton buttonStartUser; // кнопка для пользовательской игры
    JLabel label; // заголовок главного окна

    @Override
    public void run() {
        initFrame();
        initButton();
        initLabel();
    }

    // Задаём размер и текст заголовка
    private void initLabel() {
        label = new JLabel("Выберите режим игры", SwingConstants.CENTER);
        label.setBounds(50, 25, 300, 50);
        frame.add(label);
    }

    // Задаём местоположение и размер кнопок, их действия при клике
    private void initButton() {
        buttonStartRand = new JButton("Сгенерировать автоматически");
        buttonStartUser = new JButton("Задать вручную");

        buttonStartRand.setBounds(50, 100, 300, 50);
        buttonStartUser.setBounds(50, 175, 300, 50);

        buttonStartUser.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = new Window(false); // запускаем игру без рандомизированного расположения
                javax.swing.SwingUtilities.invokeLater(window);
            }
        });

        buttonStartRand.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window window = new Window(true); // генерируем случайное местоположение организмов
                javax.swing.SwingUtilities.invokeLater(window);
            }
        });

        frame.add(buttonStartRand);
        frame.add(buttonStartUser);
        frame.setResizable(false);
    }

    // Задаём характеристики окна меню
    private void initFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo((null));
        frame.setVisible(true);
        frame.setTitle("Life Game");
        String path = "icon.png";
        URL imgURL = Window.class.getResource(path);
        ImageIcon icon = new ImageIcon(imgURL);
        frame.setIconImage(icon.getImage());
    }
}
