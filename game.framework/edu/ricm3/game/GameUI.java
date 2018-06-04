/*
 * Educational software for a basic game development
 * Copyright (C) 2018  Pr. Olivier Gruber
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.ricm3.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class GameUI {

  static String license = "Copyright (C) 2017  Pr. Olivier Gruber " + "This program comes with ABSOLUTELY NO WARRANTY. "
      + "This is free software, and you are welcome to redistribute it "
      + "under certain conditions; type `show c' for details.";

  static GameUI game;

//  public static void main(String[] args) {
//
//    game = new Game();
//
//    // notice that the main thread will exit here,
//    // but not your program... hence the hooking
//    // of the window events to System.exit(0) when
//    // the window is closed. See class WindowListener.
//
//    /*
//     * *** WARNING *** WARNING *** WARNING *** WARNING ***
//     * If you do something here, on this "main" thread,
//     * you will have parallelism and thus race conditions.
//     * 
//     *           ONLY FOR ADVANCED DEVELOPERS
//     *           
//     * *** WARNING *** WARNING *** WARNING *** WARNING ***
//     */
//  }


  JFrame m_frame;
  GameView m_view;
  Timer m_timer;
  GameModel m_model;
  GameController m_controller;
  JLabel m_text;
  int m_fps;
  String m_msg;
  long m_start;
  long m_elapsed;
  long m_lastRepaint;
  long m_lastTick;
  int m_nTicks;

  public GameUI(GameModel m, GameView v, GameController c, Dimension d) {
    m_model = m; m_model.m_game = this;
    m_view = v; m_view.m_game = this;
    m_controller = c; m_controller.m_game = this;

    System.out.println(license);

    // create the main window and the periodic timer
    // to drive the overall clock of the simulation.
    createWindow(d);
    createTimer();
  }

  public GameModel getModel() {
    return m_model;
  }

  public GameView getView() {
    return m_view;
  }

  public GameController getController() {
    return m_controller;
  }

  public void addNorth(Component c) {
    m_frame.add(c,BorderLayout.NORTH);
  }
  public void addSouth(Component c) {
    m_frame.add(c,BorderLayout.SOUTH);
  }
  public void addWest(Component c) {
    m_frame.add(c,BorderLayout.WEST);
  }
  public void addEast(Component c) {
    m_frame.add(c,BorderLayout.EAST);
  }

  private void createWindow(Dimension d) {
    m_frame = new JFrame();
    m_frame.setTitle("Sample Game");
    m_frame.setSize(d);
    m_frame.setLayout(new BorderLayout());

    // Création du Label contenant l'image de fond, la view et tout les composants 
    JLabel img = new JLabel(new ImageIcon("images/background2.png"));
    img.setLayout(null);
    
    // On met la view au Centre
    m_view.setBounds(120, 120, 960, 480);
    img.add(m_view);
    
    
    // Création du Panel au Nord de la fenetre
    JPanel panNorth = new JPanel();
    panNorth.setLayout(new BorderLayout());
    
    JPanel panNorthLabel = new JPanel();
    panNorthLabel.setLayout(new GridLayout(1,9));
    
    JLabel l1 = new JLabel("Joueur 1", JLabel.CENTER);
    l1.setFont(new Font("Serif", Font.PLAIN, 20));
    
    JLabel l2 = new JLabel("Thunder", JLabel.CENTER);
    l2.setFont(new Font("Serif", Font.PLAIN, 20));
    
    JLabel l3 = new JLabel("Freeze", JLabel.CENTER);
    l3.setFont(new Font("Serif", Font.PLAIN, 20));
    
    JLabel l4 = new JLabel("Pourcentage", JLabel.CENTER);
    l4.setFont(new Font("Serif", Font.PLAIN, 20));
    
    JLabel l5 = new JLabel("Time", JLabel.CENTER);
    l5.setFont(new Font("Serif", Font.PLAIN, 20));
    
    JLabel l6 = new JLabel("Pourcentage", JLabel.CENTER);
    l6.setFont(new Font("Serif", Font.PLAIN, 20));
    
    JLabel l7 = new JLabel("Freeze", JLabel.CENTER);
    l7.setFont(new Font("Serif", Font.PLAIN, 20));
    
    JLabel l8 = new JLabel("Thunder", JLabel.CENTER);
    l8.setFont(new Font("Serif", Font.PLAIN, 20));
    
    JLabel l9 = new JLabel("Joueur 2", JLabel.CENTER);
    l9.setFont(new Font("Serif", Font.PLAIN, 20));
    
    panNorthLabel.add(l1);panNorthLabel.add(l2);panNorthLabel.add(l3);
    panNorthLabel.add(l4);panNorthLabel.add(l5);panNorthLabel.add(l6);
    panNorthLabel.add(l7);panNorthLabel.add(l8);panNorthLabel.add(l9);
    
    panNorthLabel.setOpaque(false);
    panNorth.add(panNorthLabel, BorderLayout.CENTER);
    
    JPanel panNorthBar = new JPanel();
    panNorthBar.setLayout(new GridLayout(1, 2));
    
    Dimension dimBar = new Dimension(m_frame.getWidth()/2, 40);
    
    JProgressBar barJ1 = new JProgressBar();
    barJ1.setMinimumSize(dimBar);
    barJ1.setMaximumSize(dimBar);
    barJ1.setPreferredSize(dimBar);
    barJ1.setValue(50);
    barJ1.setForeground(Color.BLUE);
    barJ1.setBorderPainted(false);
    barJ1.setOpaque(false);
    
    JProgressBar barJ2 = new JProgressBar();
    barJ2.setMinimumSize(dimBar);
    barJ2.setMaximumSize(dimBar);
    barJ2.setPreferredSize(dimBar);
    barJ2.setValue(50);
    barJ2.setForeground(Color.RED);
    barJ2.setBorderPainted(false);
    barJ2.setOpaque(false);
    
    panNorthBar.add(barJ1);panNorthBar.add(barJ2);
    panNorthBar.setOpaque(false);
    panNorth.add(panNorthBar, BorderLayout.SOUTH);
    
    panNorth.setOpaque(false);
    panNorth.setBounds(0, 0, m_frame.getWidth(), 120);
    img.add(panNorth);
    
    // Creation de Panel à l'Est
    JPanel panEast = new JPanel();
    panEast.setLayout(new GridLayout(4,1));
    
    JButton bE1 = new JButton("EAST 1");
    bE1.setBorderPainted(false);
    bE1.setContentAreaFilled(false);
    bE1.setFocusPainted(false);
    
    JButton bE2 = new JButton("EAST 2");
    bE2.setBorderPainted(false);
    bE2.setContentAreaFilled(false);
    bE2.setFocusPainted(false);
    
    JButton bE3 = new JButton("EAST 3");
    bE3.setBorderPainted(false);
    bE3.setContentAreaFilled(false);
    bE3.setFocusPainted(false);
    
    JButton bE4 = new JButton("EAST 4");
    bE4.setBorderPainted(false);
    bE4.setContentAreaFilled(false);
    bE4.setFocusPainted(false);
    
    panEast.add(bE1);panEast.add(bE2);panEast.add(bE3);panEast.add(bE4);    
    panEast.setOpaque(false);
    panEast.setBounds(0, 120, 120, 480);
    img.add(panEast);
    
    
    // Creation de Panel à l'Ouest
    JPanel panWest = new JPanel();
    panWest.setLayout(new GridLayout(4,1));
    
    JButton bW1 = new JButton("WEST 1");
    bW1.setBorderPainted(false);
    bW1.setContentAreaFilled(false);
    bW1.setFocusPainted(false);
    
    JButton bW2 = new JButton("WEST 2");
    bW2.setBorderPainted(false);
    bW2.setContentAreaFilled(false);
    bW2.setFocusPainted(false);
    
    JButton bW3 = new JButton("WEST 3");
    bW3.setBorderPainted(false);
    bW3.setContentAreaFilled(false);
    bW3.setFocusPainted(false);
    
    JButton bW4 = new JButton("WEST 4");
    bW4.setBorderPainted(false);
    bW4.setContentAreaFilled(false);
    bW4.setFocusPainted(false);
    
    panWest.add(bW1);panWest.add(bW2);panWest.add(bW3);panWest.add(bW4);    
    panWest.setOpaque(false);
    panWest.setBounds(1080, 120, 120, 480);
    img.add(panWest);
    
    // On ajoute le tout dans la fenetre
    m_frame.add(img);
    
    m_text = new JLabel();
    m_text.setText("Starting up...");
    //m_frame.add(m_text, BorderLayout.NORTH);

    m_frame.doLayout();
    m_frame.setResizable(false);
    m_frame.setVisible(true);

    // hook window events so that we exit the Java Platform
    // when the window is closed by the end user.
    m_frame.addWindowListener(new WindowListener(m_model));

    m_frame.pack();
    m_frame.setLocationRelativeTo(null);
    
    GameController ctr = getController();

    // let's hook the controller, 
    // so it gets mouse events and keyboard events.
    m_view.addKeyListener(ctr);
    m_view.addMouseListener(ctr);
    m_view.addMouseMotionListener(ctr);

    // grab the focus on this JPanel, meaning keyboard events
    // are coming to our controller. Indeed, the focus controls
    // which part of the overall GUI receives the keyboard events.
    m_view.setFocusable(true);
    m_view.requestFocusInWindow();

    m_controller.notifyVisible();
  }

  /* 
   * Let's create a timer, it is the heart of the simulation,
   * ticking periodically so that we can simulate the passing of time.
   */
  private void createTimer() {
    int tick = 1; // one millisecond
    m_start = System.currentTimeMillis();
    m_lastTick = m_start;
    m_timer = new Timer(tick, new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        tick();
      }
    });
    m_timer.start();
  }

  /*
   * This is the period tick callback.
   * We compute the elapsed time since the last tick.
   * We inform the model of the current time.
   */
  private void tick() {
    long now = System.currentTimeMillis() - m_start;
    long elapsed = (now - m_lastTick);
    m_elapsed += elapsed;
    m_lastTick = now;
    m_nTicks++;
    m_model.step(now);
    m_controller.step(now);
    
    elapsed = now - m_lastRepaint;
    if (elapsed > Options.REPAINT_DELAY) {
      double tick = (double) m_elapsed / (double) m_nTicks;
      long tmp = (long) (tick * 10.0);
      tick = tmp / 10.0;
      m_elapsed = 0;
      m_nTicks = 0;
      String txt = "Tick=" + tick + "ms";
      while (txt.length() < 15)
        txt += " ";
      txt = txt + m_fps + " fps   ";
      while (txt.length() < 25)
        txt += " ";
      if (m_msg != null)
        txt += m_msg;
      //      System.out.println(txt);
      m_text.setText(txt);
      m_text.repaint();
      m_view.paint();
      m_lastRepaint = now;
    }
  }

  public void setFPS(int fps, String msg) {
    m_fps = fps;
    m_msg = msg;
  }

}
