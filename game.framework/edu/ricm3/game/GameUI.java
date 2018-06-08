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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import fenetre.EndPage;
import fenetre.HomeWindow;
import mvc.Model;

public class GameUI {

	static String license = "Copyright (C) 2017  Pr. Olivier Gruber "
			+ "This program comes with ABSOLUTELY NO WARRANTY. "
			+ "This is free software, and you are welcome to redistribute it "
			+ "under certain conditions; type `show c' for details.";

	static GameUI game;

	JFrame m_frame;
	GameView m_view;
	GameModel m_model;
	GameController m_controller;
	Timer m_timer;
	JLabel m_text;


	boolean timer;

	String m_msg;
	long m_start, m_elapsed, m_lastRepaint, m_lastTick;
	int m_nTicks, m_fps;

	public GameUI(Dimension d) {

		timer =true;
		System.out.println(license);

		createWindow(d);
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
		m_frame.add(c, BorderLayout.NORTH);
	}

	public void addSouth(Component c) {
		m_frame.add(c, BorderLayout.SOUTH);
	}

	public void addWest(Component c) {
		m_frame.add(c, BorderLayout.WEST);
	}

	public void addEast(Component c) {
		m_frame.add(c, BorderLayout.EAST);
	}

	// creates a new window for home page to welcome the user
	private void createWindow(Dimension d) {

		new HomeWindow(d, this);

	}

	/*
	 * Let's create a timer, it is the heart of the simulation, ticking
	 * periodically so that we can simulate the passing of time.
	 */

	public void createTimer() {

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

	public void setM_view(GameView m_view) {
		this.m_view = m_view;
	}

	public void setM_model(GameModel m_model) {
		this.m_model = m_model;
	}

	public void setM_controller(GameController m_controller) {
		this.m_controller = m_controller;
	}


	/*
	 * This is the period tick callback. We compute the elapsed time since the
	 * last tick. We inform the model of the current time.
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
			if(timer) {
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

				m_view.paint();
				
				m_lastRepaint = now;
				if (((Model) m_model).getTimer() == false) {
					timer = false;
					new EndPage(((Model) m_model),m_view,this);
					((Model)m_model).getM_frame().dispose();
					
				}
			}
		
		}
	}

	public void setFPS(int fps, String msg) {
		m_fps = fps;
		m_msg = msg;
	}

}
