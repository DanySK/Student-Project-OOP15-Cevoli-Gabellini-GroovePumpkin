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
public class RecorderRecSpace extends AbstractRecorderSpace {

	private static final long serialVersionUID = -2653526539862883988L;
	private final String recImgPath= "data"+ System.getProperty("file.separator") + "Rec.png";
	private final String stopImgPath= "data"+ System.getProperty("file.separator") + "Stop.png";
	
	public RecorderRecSpace(final RecorderPauseSpace rps) {
		
		super.getLabel().setText("Rec");
		super.getButton().setText("O");
		super.getButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (RecorderRecSpace.super.getLabel().getText().equals("Rec")) {
					RecorderRecSpace.super.getLabel().setText("Stop");
					RecorderRecSpace.super.getButton().setText("[/]");
					rps.getButton().setEnabled(true);
					
					// start recording the sound
					
				} else {
					RecorderRecSpace.super.getLabel().setText("Rec");
					RecorderRecSpace.super.getButton().setText("O");
					rps.getLabel().setText("Pause");
					rps.getButton().setText("||");
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
