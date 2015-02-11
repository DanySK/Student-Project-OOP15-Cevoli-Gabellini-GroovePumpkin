package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Alessandro
 *
 */
@SuppressWarnings("unused")
public class RecSpace extends AbstractCompositeJSpace {

	private static final long serialVersionUID = -2653526539862883988L;
	
	public RecSpace(final PlayAndPauseSpace rps) {
		
		super("Rec", PersonalJButton.REC_IMG);
		
		super.setButtonActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (RecSpace.super.getLabel().getText().equals("Rec")) {
					RecSpace.super.getLabel().setText("Stop");
					RecSpace.super.getButton().setIcon(PersonalJButton.STOP_IMG);
					rps.getButton().setEnabled(true);
					
					// start recording the sound
					
				} else {
					RecSpace.super.getLabel().setText("Rec");
					RecSpace.super.getButton().setIcon(PersonalJButton.REC_IMG);
					rps.getLabel().setText("Pause");
					rps.getButton().setIcon(PersonalJButton.PAUSE_IMG);
					rps.getButton().setEnabled(false);
					
					// stop recording
					
				}

			}
		});
	}
	
	public boolean isRecording(){
		return super.getLabel().getText().equals("Stop");
	}
}
