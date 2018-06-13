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
package edu.ricm3.game.sample;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Square {
  Model m_model;
  Color m_color;
  int m_x, m_y;
  int m_size;
  int m_red, m_green, m_blue;
  int m_dsize, m_rsize;
  boolean m_up;
  long m_lastResize;
  long m_lastMove;
  int gap;

  Square(Model m, int x, int y) {
    m_model = m;
    m_x = x;
    m_y = y;
    m_dsize = 20;
    m_rsize = 20;
    m_up = (m_dsize > 0);
    Random rand = new Random();
    m_red = rand.nextInt(255);
    m_green = rand.nextInt(255);
    m_blue = rand.nextInt(255);
    m_color = new Color(m_red, m_green, m_blue);
  }

  /**
   * Simulation step.
   * 
   * @param now
   *          is the current time in milliseconds.
   */
  void step(long now) {
    long elapsed = now - m_lastMove;
    if (elapsed > 50L) {
      m_lastMove = now;
      if (m_up) {
        m_x += 1;
        m_y += 1;
      } else {
        m_x -= 1;
        m_y -= 1;
      }
      if (m_rsize > 0) {
        m_size++;
        m_rsize--;
      } else if (m_rsize < 0) {
        m_size--;
        m_rsize++;
      }
    }
    elapsed = now - m_lastResize;
    if (elapsed > 2000L) {
      m_lastResize = now;
      if (m_size <= 0) {
        m_dsize = +20;
        m_rsize = m_dsize;
      } else {
        if (m_size >= 200)
          m_dsize = -m_dsize;
        m_rsize=m_dsize;
      }
      m_up = (m_dsize > 0);
    }
  }

  /**
   * paints this square on the screen.
   * 
   * @param g
   */
  void paint(Graphics g) {
    if (true) {
      int dc = 2;
      m_red = (m_red + dc) % 255;
      m_green = (m_green + dc) % 255;
      m_blue = (m_blue + dc) % 255;
      m_color = new Color(m_red, m_green, m_blue);
    }
    g.setColor(m_color);
    g.drawRect(m_x, m_y, m_size, m_size);

    if (Options.STROBBING_SQUARES) {
      gap--;
      if (gap <= 0)
        gap = 20;

      int h = m_size;
      int w = m_size;
      g.setColor(Color.blue);
      g.fillRect(m_x, m_y, w, h);

      g.setColor(Color.red);
      for (int i = 0; i < w; i += gap)
        g.drawLine(m_x + i, m_y, m_x + w - i, m_y + h);
      for (int i = 0; i < h; i += gap)
        g.drawLine(m_x, m_y + i, m_x + w, m_y + h - i);
    }
  }

}
