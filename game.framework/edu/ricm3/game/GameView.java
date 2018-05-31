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

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public abstract class GameView extends Canvas {

  private static final long serialVersionUID = 1L;

  protected GameUI m_game;
  protected Color m_background = Color.gray;
  
  private Image m_buffer1, m_buffer2;
  private Image m_renderBuffer;
  private Image m_drawBuffer;
  private int m_width, m_height;
  private boolean m_swap;

  private void initDoubleBuffering(int width, int height) {

    if (width != m_width || height != m_height) {
      m_width = width;
      m_height = height;
      m_buffer1 = new BufferedImage(m_width, m_height, BufferedImage.TYPE_INT_RGB);
      if (Options.USE_DOUBLE_BUFFERING)
        m_buffer2 = new BufferedImage(m_width, m_height, BufferedImage.TYPE_INT_RGB);
      else
        m_buffer2 = m_buffer1;

      Graphics gc = m_buffer1.getGraphics();
      gc.setColor(Color.ORANGE);
      gc.fillRect(0, 0, m_width, m_height);
      gc.setColor(Color.BLACK);
      for(int i =1; i<32; i++) {
    	  gc.fillRect(1280/i, 0, 2, 720);
      }
      for(int i =1; i<18; i++) {
    	  gc.fillRect(0, 720/i, 1280, 2);
      }
      gc = m_buffer2.getGraphics();
      gc.setColor(Color.ORANGE);
      gc.setColor(Color.BLACK);
      for(int i =1; i<32; i++) {
    	  gc.fillRect(1280/i, 0, 2, 720);
      }
      for(int i =1; i<18; i++) {
    	  gc.fillRect(0, 720/i, 1280, 2);
      }
      gc.fillRect(0, 0, m_width, m_height);
      m_renderBuffer = m_buffer2;
      m_drawBuffer = m_buffer1;
    }
  }

  private void swap() {
    if (m_renderBuffer == m_buffer1) {
      m_renderBuffer = m_buffer2;
      m_drawBuffer = m_buffer1;
    } else {
      m_renderBuffer = m_buffer1;
      m_drawBuffer = m_buffer2;
    }
  }

  protected GameView() {
  }
  
  public GameUI getGameUI() {
    return m_game;
  }
  
  public int getWidth() {
    return m_width;
  }

  public int getHeight() {
    return m_height;
  }

  public void setBounds(int x, int y, int width, int height) {
    initDoubleBuffering(width, height);
    super.setBounds(x, y, width, height);
  }

  public GameModel getModel() {
    return m_game.getModel();
  }

  public GameController getController() {
    return m_game.getController();
  }

  public final void paint() {
    Graphics g = m_drawBuffer.getGraphics();
    _paint(g);
    m_swap = true;
    repaint();
  }

  @Override
  public final void paint(Graphics g) {
    if (m_swap) {
      swap();
      m_swap = false;
    }
    g.drawImage(m_renderBuffer, 0, 0, this);
    Toolkit.getDefaultToolkit().sync();
  }

  @Override
  public final void update(Graphics g) {
    paint(g);
  }

  protected abstract void _paint(Graphics g);
}
