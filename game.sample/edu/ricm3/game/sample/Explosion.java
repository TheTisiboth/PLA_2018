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

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Explosion {

  BufferedImage m_sprite;
  int m_w, m_h;
  int m_x, m_y;
  int m_nrows, m_ncols;
  int m_idx;
  float m_scale = 1;
  long m_lastChange;
  BufferedImage[] m_sprites;
  Model m_model;
  
  Explosion(Model model, BufferedImage sprite, int rows, int columns) {
    m_model = model;
    m_sprite = sprite;
    m_ncols = columns;
    m_nrows = rows;
    int width = sprite.getWidth(null);
    int height = sprite.getHeight(null);

    m_sprites = new BufferedImage[rows * columns];
    m_w = width / columns;
    m_h = height / rows;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        int x = j * m_w;
        int y = i * m_h;
        m_sprites[(i * columns) + j] = m_sprite.getSubimage(x, y, m_w, m_h);
      }
    }
  }

  public boolean done() {
    return (m_idx == m_sprites.length);
  }

  public void setPosition(int x, int y, float scale) {
    m_x = (int)(x - scale * m_w / 2f);
    m_y = (int)(y - scale * m_h / 2f);
    m_scale = scale;
    m_idx = 0;
  }

  /**
   * Simulation step.
   * 
   * @param now
   *          is the current time in milliseconds.
   */
  public void step(long now) {
    long elapsed = now - m_lastChange;
    if (elapsed > 10L) {
      m_lastChange = now;
      if (m_idx < m_sprites.length)
        m_idx++;
    }
  }

  /**
   * paints this square on the screen.
   * 
   * @param g
   */
  void paint(Graphics g) {
    Image img;
    if (m_idx == m_sprites.length)
      img = m_sprites[m_idx - 1];
    else
      img = m_sprites[m_idx];
    int w = (int)(m_scale * m_w);
    int h = (int)(m_scale * m_h);
    g.drawImage(img, m_x, m_y, w, h, null);
  }

}
