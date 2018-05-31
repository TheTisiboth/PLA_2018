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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public abstract class GameController implements MouseListener, MouseMotionListener, KeyListener {

  protected GameUI m_game;

  protected GameController() {
  }
  
  public GameUI getGameUI() {
    return m_game;
  }

  public abstract void notifyVisible();

  /**
   * Simulation step. Warning: the model has already executed its step.
   * 
   * @param now
   *          is the current time in milliseconds.
   */
  public abstract void step(long now);

  @Override
  public abstract void keyTyped(KeyEvent e);

  @Override
  public abstract void keyPressed(KeyEvent e);

  @Override
  public abstract void keyReleased(KeyEvent e);

  @Override
  public abstract void mouseClicked(MouseEvent e);

  @Override
  public abstract void mousePressed(MouseEvent e);

  @Override
  public abstract void mouseReleased(MouseEvent e);

  @Override
  public abstract void mouseEntered(MouseEvent e);

  @Override
  public abstract void mouseExited(MouseEvent e);

  @Override
  public abstract void mouseDragged(MouseEvent e);

  @Override
  public abstract void mouseMoved(MouseEvent e);
}
