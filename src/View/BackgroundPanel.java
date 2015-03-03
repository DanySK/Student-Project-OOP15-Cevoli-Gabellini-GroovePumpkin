package View;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

/*
 *  Support custom painting on a panel in the form of
 *
 *  a) images - that can be scaled, tiled or painted at original size
 *  b) non solid painting - that can be done by using a Paint object
 *
 *  Also, any component added directly to this panel will be made
 *  non-opaque so that the custom painting can show through.
 */
/**
 * 
 * @author Rob Camick
 *
 */
public class BackgroundPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4807759456388406395L;
	/**
	 * 
	 */
	public static final int SCALED = 0;


	private Paint painter;
	private Image image;
	private int style = SCALED;
	private boolean isTransparentAdd = true;

	/*
	 *  Set image as the background with the SCALED style
	 */
	/**
	 * 
	 * @param nimage is the image to Add as BackGround
	 */
	public BackgroundPanel(final Image nimage) {
		this(nimage, SCALED);
	}

	/*
	 *  Set image as the background with the specified style
	 */
	/**
	 * 
	 * @param nimage is the Image to add as Background
	 * @param nstyle is the Style of the image
	 */
	public BackgroundPanel(final Image nimage, final int nstyle) {
		setImage(nimage);
		setStyle(nstyle);
		setLayout(new BorderLayout());
	}




	/*
	 *  Use the Paint interface to paint a background
	 */
	/**
	 * 
	 * @param npainter is the Painter
	 */
	public BackgroundPanel(final Paint npainter) {
		setPaint(painter);
		setLayout(new BorderLayout());
	}

	/*
	 *	Set the image used as the background
	 */
	/**
	 * 
	 * @param nimage is the image 
	 */
	public void setImage(final Image nimage) {
		this.image = nimage;
		repaint();
	}

	/*
	 *	Set the style used to paint the background image
	 */
	/**
	 * 
	 * @param nstyle is the style for the image
	 */
	public void setStyle(final int nstyle) {
		this.style = nstyle;
		repaint();
	}

	/*
	 *	Set the Paint object used to paint the background
	 */
	/**
	 * 
	 * @param npainter is the painter to add
	 */
	public void setPaint(final Paint npainter) {
		this.painter = npainter;
		repaint();
	}


	/*
	 *  Override method so we can make the component transparent
	 */
	/**
	 * 
	 * @param component is the component to add
	 */
	public void add(final JComponent component) {
		add(component, null);
	}

	/*
	 *  Override to provide a preferred size equal to the image size
	 */
	@Override
	public Dimension getPreferredSize() {
		if (image == null) {
			return super.getPreferredSize();
		} else {
			return new Dimension(image.getWidth(null), image.getHeight(null));
		}
	}

	/*
	 *  Override method so we can make the component transparent
	 */
	/**
	 * 
	 * @param component is the component to add
	 * @param constraints are the component costraints
	 */
	public void add(final JComponent component, final Object constraints) {
		if (isTransparentAdd) {
			makeComponentTransparent(component);
		}

		super.add(component, constraints);
	}

	/*
	 *  Controls whether components added to this panel should automatically
	 *  be made transparent. That is, setOpaque(false) will be invoked.
	 *  The default is set to true.
	 */
	/**
	 * 
	 * @param nisTransparentAdd boolean to set a trasparentadd
	 */
	public void setTransparentAdd(final boolean nisTransparentAdd) {
		this.isTransparentAdd = nisTransparentAdd;
	}

	/*
	 *	Try to make the component transparent.
	 *  For components that use renderers, like JTable, you will also need to
	 *  change the renderer to be transparent. An easy way to do this it to
	 *  set the background of the table to a Color using an alpha value of 0.
	 */
	private void makeComponentTransparent(final JComponent component) {
		component.setOpaque(false);

		if (component instanceof JScrollPane) {
			final JScrollPane scrollPane = (JScrollPane) component;
			final JViewport viewport = scrollPane.getViewport();
			viewport.setOpaque(false);
			final Component c = viewport.getView();

			if (c instanceof JComponent) {
				((JComponent) c).setOpaque(false);
			}
		}
	}

	/*
	 *  Add custom painting
	 */
	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);

		//  Invoke the painter for the background

		if (painter != null) {
			final Dimension d = getSize();
			final Graphics2D g2 = (Graphics2D) g;
			g2.setPaint(painter);
			g2.fill(new Rectangle(0, 0, d.width, d.height));
		}

		//  Draw the image

		if (image == null) {
			return;
		}

		switch (style) {
			case SCALED :
				drawScaled(g);
				break;
			default:
            	drawScaled(g);
		}
	}

	/*
	 *  Custom painting code for drawing a SCALED image as the background
	 */
	private void drawScaled(final Graphics g) {
		final Dimension d = getSize();
		g.drawImage(image, 0, 0, d.width, d.height, null);
	}

}