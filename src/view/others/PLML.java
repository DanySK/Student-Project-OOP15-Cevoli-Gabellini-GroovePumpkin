package view.others;

import static view.config.Utility.DOUBLE_CLICK;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

import view.buttons.RemoveButton;
import controller.MusicPlayer;

public class PLML implements MouseListener {
	
	//private final RemoveButton remove;
	private final MusicPlayer controller;

	public PLML(final RemoveButton rb, final MusicPlayer mp) {
		//this.remove= rb;
		this.controller= mp;
	}

	@Override
	public void mouseReleased(final MouseEvent e) {
	}

	@Override
	public void mousePressed(final MouseEvent e) {
		
//			remove.setSelectedIndex(((JTable) e.getSource())
//					.getSelectedRows());
	}

	@Override
	public void mouseExited(final MouseEvent e) {
	}

	@Override
	public void mouseEntered(final MouseEvent e) {
	}

	@Override
	public void mouseClicked(final MouseEvent e) {
		if (e.getClickCount() == DOUBLE_CLICK ) {
				controller.stop();
				controller.goToSong(((JTable) e.getSource())
						.getSelectedRow());
				controller.play();
		}
	}

}
