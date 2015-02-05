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

@SuppressWarnings("unused")
public class RecorderPauseSpace extends AbstractRecorderSpace {

	private static final long serialVersionUID = -8958765355776362631L;
	
	
	public RecorderPauseSpace(final String imgPath) {
		
		super.getLabel().setText("Pause");
		super.getButton().setText("||");
		super.getButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (RecorderPauseSpace.super.getLabel().getText().equals("Pause")) {
					RecorderPauseSpace.super.getLabel().setText("Start");
					RecorderPauseSpace.super.getButton().setText(">");
					// pause the recording
				} else {
					RecorderPauseSpace.super.getLabel().setText("Pause");
					RecorderPauseSpace.super.getButton().setText("||");
					// restart the recording
				}

			}
		});
		super.getButton().setEnabled(false);
	}
	
	public boolean isPaused(){
		return super.getLabel().getText().equals("Start");
	}
}
