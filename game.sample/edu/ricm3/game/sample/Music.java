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

import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.media.CannotRealizeException;
import javax.media.Controller;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.Time;

/*
 * Music player.
 * 
 * You can play .wav files.
 * If like me, you have mp3 files, you can transcode them:
 * 
 *    sudo apt-get install mpg123
 * 
 * Then to convert mp3 to wav (using -w option)
 *    mpg123 -w output.wav input.mp3
 *
 * Title:   Future RPG                      Artist: Eric Matyas
 * Comment: QMAYT1400787                    Album:  
 * Year:    6                               Genre:  Soundtrack, 
*/

public class Music {
  Player m_player;
  File m_file;

  public Music(File file) throws IOException, NoPlayerException, CannotRealizeException {
    m_file = file;
    load();
  }

  private void load() throws IOException, NoPlayerException, CannotRealizeException {
    URL url;
    url = m_file.toURL();

    m_player = Manager.createRealizedPlayer(url);
    m_player.addControllerListener(new ControllerListener() {

      @Override
      public void controllerUpdate(ControllerEvent arg0) {
        Controller ctr = arg0.getSourceController();
        if (arg0 instanceof EndOfMediaEvent) {
          m_player.setMediaTime(new Time(0));
          m_player.start();
        }
      }
    });
  }

  public Component getControls() {
    Component c; 
    c = m_player.getControlPanelComponent();
    Dimension d = c.getPreferredSize();
    d.setSize(d.getWidth() + 100, d.getHeight());
    c.setPreferredSize(d);
    return c;
  }

}
